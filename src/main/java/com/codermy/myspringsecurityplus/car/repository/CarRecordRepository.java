package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CarRecordRepository extends JpaRepository<CarRecord, Integer> {
    /**
     * 根据订单id获得订单
     *
     * @param id 订单id
     * @return 订单
     */
    Optional<CarRecord> findById(Long id);
    /**
     * 根据顾客id获得订单
     *
     * @param id 顾客id
     * @return 订单
     */
    @Query(value = "select r.* from t_car_record r where r.customer_id=:customerId " ,
            countQuery = "SELECT count(*) from (select r.* from t_car_record r where r.customer_id=:customerId) cr"
            , nativeQuery=true)
    Page<CarRecord> findByCustomerId(@Param("customerId")int customerId, Pageable pageable);

    /**
     * 
     * @param customerId
     * @param status
     * @param pageable
     * @return
     */
    @Query(value = "select r.* from t_car_record r where r.customer_id=:customerId and r.`status`=:status " ,
            countQuery = "SELECT count(*) from (select r.* from t_car_record r where r.customer_id=:customerId and r.`status`=:status) cr"
            , nativeQuery=true)
    Page<CarRecord> findByCustomerIdAndStatus(@Param("customerId")int customerId, @Param("status")String status, Pageable pageable);

    /**
     * 根据名称获得订单
     *
     * @param name 订单名
     * @return 订单
     */
   // Optional<CarRecord> findByName(String name);

    /**
     * 根据订单id删除订单
     *
     * @param id 订单id
     * @return 订单
     */
     @Modifying
     @Transactional
     Optional<CarRecord> deleteById(Long id);

     @Query(value = "select cr.id ,cr.customer_id,cr.car_id,cr.time_long,cr.`status`,cr.deposit,cr.`name`,cr.phone,cr.identity,cr.price ,cr.rent ,cr.user_id,DATE_FORMAT(cr.create_time,\"%Y-%m-%d\"),DATE_FORMAT(cr.end_time,\"%Y-%m-%d\"),cr.car_name,cr.car_img_url,u.user_name from t_car_record cr left join my_user u on u.user_id=cr.customer_id" ,nativeQuery = true)
     List<Object> findCarRecords();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update t_car_record c set c.status =?1,c.actual_time=?3 where c.id = ?2",nativeQuery = true)
    int updateStatusById(String status, String id, Date actualTime);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update t_car_record c set c.status =?1,c.over_time=?3,c.is_damaged=?4,c.damages=?5,c.user_Id=?6 where c.id = ?2",nativeQuery = true)
    int updateStatusById(String status, String id, int overTime,String isDamaged,int damages,int userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update t_car_record c set c.status =?1 where c.id = ?2",nativeQuery = true)
    int updateStatusById(String status,String id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update t_car c set c.status =?1 where c.id = (select cr.car_id from t_car_record cr where cr.id=?2)",nativeQuery = true)
    int updateCarStatusByCarRecordId(String status,String id);

}
