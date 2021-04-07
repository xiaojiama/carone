package com.codermy.myspringsecurityplus.car.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * 具体门店
 */

@Entity
@Data
@Table(name = "t_location")
public class Location {
    @Id
    private Integer id;
    //门店名称
    private String name;
    //所在城市
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="city_id")
    private City city;

    /*@OneToMany(mappedBy = "location",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Car> carSet;
*/

}
