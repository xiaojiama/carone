package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 具体门店
 */

@Entity
@Data
@Table(name = "t_location")
public class Location {
    @Id
    private Integer id;
    //门店名称
    private String name;
    //所在城市
    private Integer cId;


}
