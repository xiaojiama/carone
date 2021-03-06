package com.codermy.myspringsecurityplus.car.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 租赁记录
 */
@Entity
@Data
@Table(name = "t_car_record")
public class CarRecord {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 客户Id
     */
    private int customerId;
    //汽车id
    private Long carId;

    /**
     * 租赁时长
     */
    private int timeLong;

    /**
     * 此订单状态 进行中，申请中，已完成
     */
    private String status;

    /**
     * 押金
     */
    private int deposit;
    //真实名称
    private String name;
    //电话
    private String phone;
    //身份证
    private String identity;
    //日租金
    private int price;
    /**
     * 租金
     */
    private int rent;
    /**
     * 工作人员id
     */
    private Integer userId;

    @JSONField(format = "yyyy-MM-dd")  //FastJson包使用注解
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") //Jackson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd")   //格式化前台日期参数注解
    private Date startTime;

    @JSONField(format = "yyyy-MM-dd")  //FastJson包使用注解
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") //Jackson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd")   //格式化前台日期参数注解
    private Date endTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //Jackson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")   //格式化前台日期参数注解
    private Date createTime;

    //实际还车时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //Jackson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")   //格式化前台日期参数注解
    private Date actualTime;

    /**
     * 超时/天数
     */
    private int overTime;

    /**
     * 是否损坏
     */
    private String isDamaged;
    /**
     * 赔偿金
     */
    private int damages;

    private String carName;

    private String carImgUrl;

    @Transient
    private int sign;
}
