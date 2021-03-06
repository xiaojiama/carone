package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    /**
     * 根据汽车id获得文件信息
     *
     * @param carId 汽车id
     * @return 汽车
     */
    List<Document> findByCarId(Long carId);
    /**
     * 根据汽车详情id获得图片信息
     *
     * @param carDetailId 汽车详情id
     * @return 汽车
     */
    List<Document> findByCarDetailId(Long carDetailId);

}
