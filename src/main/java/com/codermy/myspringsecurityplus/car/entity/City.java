package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Integer pId;


}
