package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

/**
 * Created by Restrictor on 17.11.2016.
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Group extends BaseEntity {
    @Column("group_name")
    private @NonNull String groupName;
    @Column("project_name")
    private @NonNull String projectName;

    public Group(Integer id, String groupName, String projectName) {
        this(groupName, projectName);
        this.id = id;
    }
}
