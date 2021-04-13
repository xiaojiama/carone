package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CarRecordRepository extends JpaRepository<CarRecord, Integer> {
    /**
     * 根据汽车id获得汽车
     *
     * @param id 汽车id
     * @return 汽车
     */
    Optional<CarRecord> findById(Long id);
    /**
     * 根据名称获得汽车
     *
     * @param name 汽车名
     * @return 汽车
     */
   // Optional<CarRecord> findByName(String name);

    /**
     * 根据汽车id删除汽车
     *
     * @param id 汽车id
     * @return 汽车
     */
     @Modifying
     @Transactional
     Optional<CarRecord> deleteById(Long id);

}