package com.codermy.myspringsecurityplus.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "province",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private List<City> cityList;


}
