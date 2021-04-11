package com.codermy.myspringsecurityplus.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "carType",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Car> carList;


}
