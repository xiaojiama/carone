package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * 城市
 */

@Entity
@Data
@Table(name = "t_city")
public class City {
    @Id
    private Integer id;
    //城市名称
    private String name;
    //省份
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="p_id")
    private Province province;

    @OneToMany(mappedBy = "city",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Location> locationSet;

}
