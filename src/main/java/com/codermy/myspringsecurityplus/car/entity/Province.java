package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * 省份
 */

@Entity
@Data
@Table(name = "t_province")
public class Province {
    @Id
    private Integer id;
    //省份名称
    private String name;

    @OneToMany(mappedBy = "province",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<City> citySet;


}
