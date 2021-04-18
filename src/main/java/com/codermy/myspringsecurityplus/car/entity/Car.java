package com.codermy.myspringsecurityplus.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 汽车型号
 */
@Entity
@Setter
@Getter
@EqualsAndHashCode// 自动生成get、set、toString、equals方法
@AllArgsConstructor // 全参构造方法
@NoArgsConstructor // 无参构造方法
@Table(name = "t_car")
public class Car{
    @Id
    @GeneratedValue
    private Long id;
    //汽车名称
    private String name;
    //汽车类型名称
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="type_id")
    private CarType carType;

    //汽车品牌名称
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="brand_id")
    private CarBrand carBrand;

    //位置
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="location_id")
    private Location location;

    //详情
    @OneToOne(cascade=CascadeType.ALL)
    //级联保存、更新、删除、刷新;延迟加载。当删除汽车品牌，会级联删除该汽车品牌的所有汽车
    //拥有mappedBy注解的实体类为关系被维护端
    @JoinColumn(name="detail_id")
    private CarDetail carDetail;

    //汽车图片地址
    private String imgUrl;
    //文件
    private String files;
    //描述
    private String description;
    //价格
    private BigDecimal price;

    private Integer isonline;

    private Integer isdeleted;

    private Integer userId;

    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    //不存入表,类型保存时用
    @Transient
    private int typeId;
    //不存入表
    @Transient
    private int brandId;
    //不存入表
    @Transient
    private int locationId;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", files='" + files + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isonline=" + isonline +
                ", isdeleted=" + isdeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
