package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    /**
     * 根据汽车id获得汽车
     *
     * @param id 汽车id
     * @return 汽车
     */
    Optional<Car> findById(Long id);
    /**
     * 根据名称获得汽车
     *
     * @param name 汽车名
     * @return 汽车
     */
    Optional<Car> findByName(String name);

    /**
     * 根据汽车id删除汽车
     *
     * @param id 汽车id
     * @return 汽车
     */
     @Modifying
     @Transactional
     Optional<Car> deleteById(Long id);
     //分页展示
     Page<Car> findAll(Pageable pageable);

}
