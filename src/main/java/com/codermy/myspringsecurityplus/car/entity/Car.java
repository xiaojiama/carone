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

    //汽车图片地址
    private String imgUrl;
    //文件
    private String files;
    //详情
    private String detail;
    //价格
    private BigDecimal price;

    private Integer isonline;

    private Integer isdeleted;


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
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", isonline=" + isonline +
                ", isdeleted=" + isdeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
