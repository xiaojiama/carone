package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    /**
     * 根据省级id获得该省下所有城市
     *
     * @param pid 省级id
     * @return 城市
     */
    @Query(value = " SELECT * FROM t_city where p_id= ?1", nativeQuery=true)
    List<City> findCityData(int pid);


}
