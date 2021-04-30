package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * 根据汽车id获得汽车
     *
     * @param id 汽车id
     * @return 汽车
     */
    List<Object> findByCustomerId(Long id);
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

     @Query(value = "select cr.id ,cr.customer_id,cr.car_id,cr.time_long,cr.`status`,cr.deposit,cr.`name`,cr.phone,cr.identity,cr.price ,cr.rent ,cr.user_id,DATE_FORMAT(cr.create_time,\"%Y-%m-%d\"),DATE_FORMAT(cr.end_time,\"%Y-%m-%d\"),cr.car_name,cr.car_img_url,u.user_name from t_car_record cr left join my_user u on u.user_id=cr.customer_id" ,nativeQuery = true)
     List<Object> findCarRecords();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update t_car_record c set c.status =?1 where c.id = ?2",nativeQuery = true)
    int updateStatusById(String status,String id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update t_car c set c.status =?1 where c.id = (select cr.car_id from t_car_record cr where cr.id=?2)",nativeQuery = true)
    int updateCarStatusByCarRecordId(String status,String id);

}
