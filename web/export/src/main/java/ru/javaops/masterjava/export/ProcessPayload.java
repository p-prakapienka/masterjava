package ru.javaops.masterjava.export;


import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProcessPayload {
    private final UserExport userExport = new UserExport();

    public GroupResult process(InputStream is, int chunkSize) throws XMLStreamException {
        final StaxStreamProcessor processor = new StaxStreamProcessor(is);
        return userExport.process(processor, chunkSize);
    }

    public static class Result {
        private static final String OK = "OK";

        public String result = OK;

        public boolean isOk() {
            return OK.equals(result);
        }
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class ChunkResult extends Result {
        String startEmail;
        String endEmail;
        int size;

        public void setFail(String message) {
            result = message;
        }

        @Override
        public String toString() {
            return "Chunk (startEmail='" + startEmail + '\'' + ", endEmail='" + endEmail + "', size:'" + size + "):" + result;
        }
    }

    public static class GroupResult extends Result {
        public List<ChunkResult> chunkResults = new ArrayList<>();
        public int successful;
        public int failed;

        protected void add(ChunkResult chunkResult) {
            chunkResults.add(chunkResult);
            if (chunkResult.isOk()) {
                successful += chunkResult.size;
            } else {
                failed += chunkResult.size;
                result = isOk() ? chunkResult.toString() : "------------------------\n" + chunkResult.toString();
            }
        }

        @Override
        public String toString() {
            return "Result (successful=" + successful + ", failed=" + failed + "): " + result;
        }
    }
}
