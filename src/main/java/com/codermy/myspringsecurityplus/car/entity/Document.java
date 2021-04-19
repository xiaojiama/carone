package com.codermy.myspringsecurityplus.car.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 汽车相关文件
 */
@Entity
@Data
@Table(name = "t_document")
public class Document {
    @Id
    @GeneratedValue
    private Integer id;
    private Long carId;
    private Long carDetailId;
    private String name;
    private String saveName;
    private String path;
    private Date time;

}
