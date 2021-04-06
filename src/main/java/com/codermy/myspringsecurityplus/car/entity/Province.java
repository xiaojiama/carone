package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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



}
