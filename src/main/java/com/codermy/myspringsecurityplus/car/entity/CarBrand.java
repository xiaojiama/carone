package com.codermy.myspringsecurityplus.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 汽车品牌
 */

@Entity
@Data
@Table(name = "t_car_brand")
public class CarBrand{
    @Id
    private Integer id;
    //汽车品牌名称
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "carBrand",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Car> carList;


}
