package ru.javaops.masterjava.persist.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Project extends BaseEntity {

    @NonNull private String name;
    @NonNull private String description;

    public Project(Integer id, String name, String description) {
        this(name, description);
        this.id = id;
    }
}
