package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 变速类型
 */

@Entity
@Data
@Table(name = "t_gear")
public class Gear {
    @Id
    private Integer id;
    //变速箱类型
    private String name;



}
