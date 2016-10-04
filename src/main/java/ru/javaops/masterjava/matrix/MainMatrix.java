package ru.javaops.masterjava.matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * gkislin
 * 03.07.2016
 */
public class MainMatrix {
    // Multiplex matrix
    private static final int MATRIX_SIZE = 1000;
    private static final int THREAD_NUMBER = 10;

    public static void main(String[] args) {
        final int[][] matrixA = new int[MATRIX_SIZE][MATRIX_SIZE];
        final int[][] matrixB = new int[MATRIX_SIZE][MATRIX_SIZE];

        MatrixUtil.fillMatrix(matrixA);
        MatrixUtil.fillMatrix(matrixB);

        long start = System.currentTimeMillis();
        final int[][] matrixC =  MatrixUtil.singleThreadMultiply(matrixA, matrixB);
        System.out.println("Single thread multiplication time, sec: " + (System.currentTimeMillis() - start)/1000.);

        start = System.currentTimeMillis();

        final int[][] matrixD = new int[MATRIX_SIZE][MATRIX_SIZE];

        ExecutorService matrixExecutor = Executors.newFixedThreadPool(THREAD_NUMBER);
        List<Future<RowResult>> futures = new LinkedList<>();
        final CompletionService<RowResult> completionService = new ExecutorCompletionService<>(matrixExecutor);
        for (int i = 0; i < MATRIX_SIZE; i++) {
            final int row = i;
            futures.add(completionService.submit(new Callable<RowResult>() {
                private final RowResult rowResult = new RowResult(row, MATRIX_SIZE);

                @Override
                public RowResult call() throws Exception {
                    for (int j = 0; j < MATRIX_SIZE; j++) {
                        int sum = 0;
                        for (int k = 0; k < MATRIX_SIZE; k++) {
                            sum += matrixA[row][k] * matrixB[k][j];
                        }
                        rowResult.getRow()[j] = sum;
                    }
                    return rowResult;
                }
            }));
        }
        while (!futures.isEmpty()) {
            try {
                Future<RowResult> future = completionService.poll(5, TimeUnit.SECONDS);
                if (future == null) {
                    System.out.println("Error!!!");
                    throw new InterruptedException();
                }
                futures.remove(future);
                RowResult rowResult = future.get();
                matrixD[rowResult.getRowNumber()] = rowResult.getRow();
            } catch (ExecutionException ee) {
                System.out.println("Execution exception!!!");
            } catch (InterruptedException ie) {
                System.out.println("Interrupted exception!!!");
            }
        }
        matrixExecutor.shutdown();

        System.out.println("Multiple threads multiplication time, sec: " + (System.currentTimeMillis() - start)/1000.);

        boolean multResult = MatrixUtil.compareMatrices(matrixC, matrixD);
        System.out.println(multResult ? "Matrices are equal." : "Matrices are not equal.");
    }

    private static class RowResult {
        private final int rowNumber;
        private final int[] row;

        RowResult(int rowNumber, int matrixSize) {
            this.rowNumber = rowNumber;
            this.row = new int[matrixSize];
        }

        int getRowNumber() {
            return rowNumber;
        }

        int[] getRow() {
            return row;
        }
    }
}
