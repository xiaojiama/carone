package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 汽车详情
 */
@Entity
@Data
@Table(name = "t_car_detail")
public class CarDetail {
    @Id
    @GeneratedValue
    private Long id;

    //该条详情对应的汽车id
    private Long carId;

    //汽车年款
    private String productYear;

    //车辆颜色
    private String color;

    //车牌号
    private String number;

    //配置款
    private String level;

    //座位数
    private Integer seats;

    //门数
    private Integer doors;

    //燃料类型
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="energy_id")
    private Energy energy;

    //变速箱类型
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="gear_id")
    private Gear gear;

    //排量
    private String displacement;

    //驱动方式：1/前驱 2/后驱 3/四驱
    private Integer drive;

    //是否有天窗
    private Integer upwindow;

    //是否有雷达
    private Integer radar;

    //是否有gps
    private Integer gps;

    //证件
    private Integer lisence;

    //保养记录
    private Integer fsfile;

    private Integer isdeleted;

    private Integer isonline;


    //管理人员
    private Integer userId;

    //创建时间
    private Date createTime;

    private Date updateTime;

}
