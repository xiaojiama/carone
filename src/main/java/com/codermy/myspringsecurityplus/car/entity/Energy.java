package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 燃料类型
 */

@Entity
@Data
@Table(name = "t_energy")
public class Energy {
    @Id
    private Integer id;
    //燃料类型
    private String name;



}
