package ru.javaops.masterjava.service.mail;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GroupResult {
    private int success; // number of successfully sent email
    private List<MailResult> failed; // failed emails with causes
    private String failedCause;  // global fail cause

    public GroupResult(Exception e) {
        this(-1, ImmutableList.of(), Throwables.getRootCause(e).toString());
    }

    public GroupResult(int success, List<MailResult> failed, String failedCause) {
        this.success = success;
        this.failed = failed;
        this.failedCause = failedCause;
    }

    @Override
    public String toString() {
        return "Success: " + success + '\n' +
                "Failed: " + failed.toString() + '\n' +
                (failedCause == null ? "" : "Failed cause: " + failedCause);
    }
}
