package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "t_car")
public class Car {
    @Id
    private String id;

    private Integer carid;

    private String carname;

    private Integer ctypeid;

    private Integer brandid;

    private String plate;

    private String pictures;

    private String detail;

    private BigDecimal price;

    private Integer isonline;

    private Integer isdeleted;

    private String files;

    private Integer lid;

    /*@DBRef
    private CarBrand carBrand;

    @DBRef
    private CarType carType;

    @Transient
    private User owner;

    @DBRef
    private Location location;*/

    private Date recordDate;
}
