package com.codermy.myspringsecurityplus.car.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 汽车型号
 */
@Entity
@Data
@Table(name = "t_document")
public class Document {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String path;
    private int createBy;//创建人
    private Date time;

}
