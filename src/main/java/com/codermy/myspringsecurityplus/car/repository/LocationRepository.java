package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    /**
     * 根据城市id获得该城市下所有门店
     *
     * @param cityId 城市id
     * @return 城市
     */
    @Query(value = "SELECT  * FROM t_location  where city_Id = ?1", nativeQuery=true )
    List<Location> findLocationData(int cityId);

}
