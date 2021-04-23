package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {

    /**
     * 根据品牌名称获得汽车品牌
     *
     * @param name 汽车品牌名
     * @return 汽车品牌
     */
    Optional<CarBrand> findByName(String name);
}
