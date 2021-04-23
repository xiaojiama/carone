package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.CarBrand;
import com.codermy.myspringsecurityplus.car.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarTypeRepository extends JpaRepository<CarType, Integer> {

    /**
     * 根据类型名称获得汽车类型
     *
     * @param name 汽车类型名
     * @return 汽车类型
     */
    Optional<CarType> findByName(String name);
}
