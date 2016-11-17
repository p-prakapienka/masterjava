package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity {
    @Column("full_name")
    private @NonNull String fullName;
    private @NonNull String email;
    private @NonNull UserFlag flag;
    private @NonNull Integer cityId;
    private Integer groupId;

    public User(Integer id, String fullName, String email, UserFlag flag, Integer cityId, Integer groupId) {
        this(fullName, email, flag, cityId);
        this.id = id;
        this.groupId = groupId;
    }

    public User(String fullName, String email, UserFlag flag, Integer cityId, Integer groupId) {
        this(fullName, email, flag, cityId);
        this.groupId = groupId;
    }
}