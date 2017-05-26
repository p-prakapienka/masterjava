package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.mail.EmailException;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.service.mail.persist.MailCase;
import ru.javaops.masterjava.service.mail.persist.MailCaseDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class MailSender {
    private static final MailCaseDao MAIL_CASE_DAO = DBIProvider.getDao(MailCaseDao.class);

    private static final String OK = "OK";

    private static final String INTERRUPTED_BY_FAULTS_NUMBER = "+++ Interrupted by faults number";
    private static final String INTERRUPTED_BY_TIMEOUT = "+++ Interrupted by timeout";
    private static final String INTERRUPTED_EXCEPTION = "+++ InterruptedException";

    private final ExecutorService mailExecutor = Executors.newFixedThreadPool(8);

    public GroupResult sendToList(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        final CompletionService<MailResult> completionService = new ExecutorCompletionService<>(mailExecutor);

        List<Future<MailResult>> futures = to.stream()
                .map(addressee -> completionService.submit(() -> sendToUser(addressee, cc, subject, body)))
                .collect(Collectors.toList());

        return new Callable<GroupResult>() {
            private int success = 0;
            private List<MailResult> failed = new ArrayList<>();

            @Override
            public GroupResult call() {
                while (!futures.isEmpty()) {
                    try {
                        Future<MailResult> future = completionService.poll(10, TimeUnit.SECONDS);
                        if (future == null) {
                            return cancelWithFail(INTERRUPTED_BY_TIMEOUT);
                        }
                        futures.remove(future);
                        MailResult mailResult = future.get();
                        if (mailResult.isOk()) {
                            success++;
                        } else {
                            failed.add(mailResult);
                            if (failed.size() >= 5) {
                                return cancelWithFail(INTERRUPTED_BY_FAULTS_NUMBER);
                            }
                        }
                    } catch (ExecutionException e) {
                        return cancelWithFail(e.getCause().toString());
                    } catch (InterruptedException e) {
                        return cancelWithFail(INTERRUPTED_EXCEPTION);
                    }
                }
/*
                for (Future<MailResult> future : futures) {
                    MailResult mailResult;
                    try {
                        mailResult = future.get(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        return cancelWithFail(INTERRUPTED_EXCEPTION);
                    } catch (ExecutionException e) {
                        return cancelWithFail(e.getCause().toString());
                    } catch (TimeoutException e) {
                        return cancelWithFail(INTERRUPTED_BY_TIMEOUT);
                    }
                    if (mailResult.isOk()) {
                        success++;
                    } else {
                        failed.add(mailResult);
                        if (failed.size() >= 5) {
                            return cancelWithFail(INTERRUPTED_BY_FAULTS_NUMBER);
                        }
                    }
                }
*/
                return new GroupResult(success, failed, null);
            }

            private GroupResult cancelWithFail(String cause) {
                futures.forEach(f -> f.cancel(true));
                return new GroupResult(success, failed, cause);
            }
        }.call();
    }

    public MailResult sendToUser(Addressee to, List<Addressee> cc, String subject, String body) throws Exception {
        String state = "OK";
        try {
            val email = MailConfig.createHtmlEmail();
            email.setSubject(subject);
            email.setHtmlMsg(body);
            email.addTo(to.getEmail(), to.getName());
            for (Addressee addressee : cc) {
                email.addCc(addressee.getEmail(), addressee.getName());
            }

            email.setHeaders(ImmutableMap.of("List-Unsubscribe", "<mailto:masterjava@javaops.ru?subject=Unsubscribe&body=Unsubscribe>"));

            email.send();
        } catch (EmailException e) {
            log.error(e.getMessage(), e);
            state = e.getMessage();
        }
        return OK.equals(state) ? MailResult.ok(to.getEmail()) : MailResult.error(to.getEmail(), state);
    }

    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        GroupResult result = new MailSender().sendToList(to, cc, subject, body);

        MAIL_CASE_DAO.insert(MailCase.of(to, cc, subject, body, result.failed.isEmpty() ? OK : result.failedCause));
    }

    public static class MailResult {
        private final String email;
        private final String result;

        private static MailResult ok(String email) {
            return new MailResult(email, OK);
        }

        private static MailResult error(String email, String error) {
            return new MailResult(email, error);
        }

        public boolean isOk() {
            return OK.equals(result);
        }

        private MailResult(String email, String cause) {
            this.email = email;
            this.result = cause;
        }

        @Override
        public String toString() {
            return '(' + email + ',' + result + ')';
        }
    }

    public static class GroupResult {
        private final int success; // number of successfully sent email
        private final List<MailResult> failed; // failed emails with causes
        private final String failedCause;  // global fail cause

        public GroupResult(int success, List<MailResult> failed, String failedCause) {
            this.success = success;
            this.failed = failed;
            this.failedCause = failedCause;
        }

        @Override
        public String toString() {
            return "Success: " + success + '\n' +
                    "Failed: " + failed.toString() + '\n' +
                    (failedCause == null ? "" : "Failed cause" + failedCause);
        }
    }
}
