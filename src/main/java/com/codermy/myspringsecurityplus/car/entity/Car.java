package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 汽车型号
 */
@Entity
@Data
@Table(name = "t_car")
public class Car{
    @Id
    private Long id;
    //汽车名称
    private String name;
    //汽车类型
    private Integer typeId;
    //汽车类型名称
    private String typeName;
    //汽车品牌
    private Integer brandId;
    //汽车品牌名称
    private String brandName;

    private String plate;
    //汽车图片地址
    private String imgUrl;
    //详情
    private String detail;
    //价格
    private BigDecimal price;

    private Integer isonline;

    private Integer isdeleted;

    //位置
    private Long locationId;

    private Date createTime;

    private Date updateTime;

    @OneToMany(mappedBy = "car",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除汽车品牌，会级联删除该汽车品牌的所有汽车
    //拥有mappedBy注解的实体类为关系被维护端
    private Set<CarDetail> carDetailSet;//汽车列表

}
