package com.codermy.myspringsecurityplus.car.entity;

import com.codermy.myspringsecurityplus.admin.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 汽车类型
 */

@Entity
@Data
@Table(name = "t_car_type")
public class CarType{
    @Id
    private Integer id;
    //汽车类型名称
    private String name;


}
