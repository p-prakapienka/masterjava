package ru.javaops.masterjava.service.mail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailResult {
    public static final String OK = "OK";

    private String email;
    private String result;

    public boolean isOk() {
        return OK.equals(result);
    }

    public MailResult(String email, String cause) {
        this.email = email;
        this.result = cause;
    }

    @Override
    public String toString() {
        return '(' + email + ',' + result + ')';
    }
}
