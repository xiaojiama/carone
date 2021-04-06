package com.codermy.myspringsecurityplus.car.entity;

import com.codermy.myspringsecurityplus.admin.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

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


}
