package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 汽车型号
 */
@Entity
@Data
@Table(name = "t_car")
public class Car{
    @Id
    private Integer id;
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

}
