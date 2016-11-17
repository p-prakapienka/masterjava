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
public class City extends BaseEntity {
    @Column("city_name")
    private @NonNull String cityName;

    public City(Integer id, String cityName) {
        this(cityName);
        this.id = id;
    }
}
