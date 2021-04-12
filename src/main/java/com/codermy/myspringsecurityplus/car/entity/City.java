package com.codermy.myspringsecurityplus.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
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
    /*@JoinColumn(name="p_id")
    private int pId;*/

    @JsonIgnore
    @OneToMany(mappedBy = "city",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Location> locationList;

}
