package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Long customerId;

    private Long carId;

    /**
     * 租赁时长
     */
    private String timeLong;

    /**
     * 此订单状态 进行中，申请中，已完成
     */
    private int status;

    /**
     * 押金
     */
    private Double deposit;

    /**
     * 租金
     */
    private Double rent;
    /**
     * 工作人员id
     */
    private Integer userId;


    private Date createTime;

    private Date endTime;


}
