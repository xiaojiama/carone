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

    //汽车品牌
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="car_id")
    private Car car;

    //车牌号
    private String number;

    //汽车年款
    private String productYear;

    //配置款
    private String level;

    //座位数
    private Integer seats;

    //门数
    private Integer doors;

    //燃料类型
    private Integer energyId;

    //燃料类型名称
    private String energyName;

    //变速箱类型
    private Integer gearId;

    //变速箱类型名称
    private String gearName;

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

    //日租价格
    private Double dayPrice;

    //月租价格
    private Double monthPrice;

    //管理人员
    private Integer userId;


    private Date createTime;

    private Date updateTime;

}