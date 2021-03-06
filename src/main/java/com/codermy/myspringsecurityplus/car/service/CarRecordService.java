package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import com.codermy.myspringsecurityplus.car.repository.CarRecordRepository;
import com.codermy.myspringsecurityplus.car.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CarRecordService {
    @Resource
    private CarRecordRepository carRecordRepository;
    @Resource
    private CarRepository carRepository;

    //查询全部内容
    public List<Object> list(){
        List<Object> result = carRecordRepository.findCarRecords();
        List<Object> list = new ArrayList<>();
        for (Object row : result) {
            Object[] rowArray = (Object[]) row;
            Map<String, Object> mapArr = new HashMap<String, Object>();
            mapArr.put("id", rowArray[0]);
            mapArr.put("customerId", rowArray[1]);
            mapArr.put("carId", rowArray[2]);
            mapArr.put("timeLong", rowArray[3]);
            mapArr.put("status", rowArray[4]);
            mapArr.put("deposit", rowArray[5]);
            mapArr.put("name", rowArray[6]);
            mapArr.put("phone", rowArray[7]);
            mapArr.put("identity", rowArray[8]);
            mapArr.put("price", rowArray[9]);
            mapArr.put("rent", rowArray[10]);
            mapArr.put("userId", rowArray[11]);
            mapArr.put("createTime", rowArray[12]);
            mapArr.put("endTime", rowArray[13]);
            mapArr.put("carName", rowArray[14]);
            mapArr.put("carImgUrl", rowArray[15]);
            mapArr.put("userName", rowArray[16]);
            list.add(mapArr);
        }
        return list;
    }

    //根据id查询
    public CarRecord findById(Long id) {
        Optional<CarRecord> r = carRecordRepository.findById(id);
        return r.orElse(null);//存在返回值，不存在返回null
    }
    //根据顾客id查询
    public Page<CarRecord> findByCustomerId(int customerId,String status,int page, int limit, String sortType) {
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? Sort.by("id").ascending() : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page-1,limit, sort);
        Page<CarRecord> r = null;
        if(status.equals("null")){
            r = carRecordRepository.findByCustomerId(customerId,pageable);
        }else{
            r = carRecordRepository.findByCustomerIdAndStatus(customerId,status,pageable);
        }
        return r;
    }
    //修改订单状态以及还车时间数据
    public void updateStatusById(String status,String id ,Date actualTime) {
        carRecordRepository.updateStatusById(status, id,actualTime);

    }
    //修改订单状态以及是否需要赔钱等数据
    public void updateStatusById(String status, String id, int overTime,String isDamaged,int damages,int userId) {
        carRecordRepository.updateStatusById(status, id,overTime,isDamaged,damages,userId);
    }
    //修改订单状态
    public void updateStatusById(String status,String id ) {
        carRecordRepository.updateStatusById(status, id);

    }

    //新增操作
    public CarRecord add(CarRecord cr) {
        Optional<Car> car = carRepository.findById(cr.getCarId());
        long endTime = cr.getEndTime().getTime();
        long startTime = cr.getStartTime().getTime();
        int days = (int) ((endTime - startTime) / (1000*3600*24));
        CarRecord c = new CarRecord();
        c.setCustomerId(cr.getCustomerId());//客户Id
        c.setCarId(cr.getCarId());//汽车Id
        c.setCarName(car.get().getName());
        c.setCarImgUrl(car.get().getImgUrl());
        c.setTimeLong(days);//租赁时长
        c.setStatus("待付款");//此订单状态 已支付，申请中，已完成，待付款
        c.setDeposit(cr.getDeposit());//押金
        c.setName(cr.getName());//真实名称
        c.setPhone(cr.getPhone());//电话
        c.setIdentity(cr.getIdentity());//身份证
        c.setPrice(cr.getPrice()); //日租金
        int rent = cr.getPrice()*days;
        c.setRent(rent); //租金
        c.setUserId(cr.getUserId());//工作人员id
        c.setCreateTime(new Date());//订单创建时间
        c.setStartTime(cr.getStartTime());//租车时间
        c.setEndTime(cr.getEndTime());//还车时间
        carRecordRepository.save(c);
        return c;


    }

    /**
     * 编辑操作
     * @return
     */
    public CarRecord edit(CarRecord cr) {
        Optional<CarRecord> c = carRecordRepository.findById(cr.getId());
        Optional<Car> car = carRepository.findById(cr.getCarId());
        long endTime = cr.getEndTime().getTime();
        long startTime = cr.getStartTime().getTime();
        int days = (int) ((endTime - startTime) / (1000*3600*24));
        if (c.isPresent()) {
            c.get().setCustomerId(cr.getCustomerId());//客户Id
            c.get().setCarId(cr.getCarId());//汽车Id
            c.get().setCarName(car.get().getName());
            c.get().setCarImgUrl(car.get().getImgUrl());
            c.get().setTimeLong(days);//租赁时长
            c.get().setStatus("待付款");//此订单状态 待付款 已支付，申请中，已完成，
            c.get().setDeposit(cr.getDeposit());//押金
            c.get().setName(cr.getName());//真实名称
            c.get().setPhone(cr.getPhone());//电话
            c.get().setIdentity(cr.getIdentity());//身份证
            c.get().setPrice(cr.getPrice()); //日租金
            int rent = cr.getPrice()*days;
            c.get().setRent(rent); //租金
            c.get().setUserId(cr.getUserId());//工作人员id
            c.get().setCreateTime(new Date());//创建时间
            c.get().setStartTime(cr.getStartTime());//租车时间
            c.get().setEndTime(cr.getEndTime());//还车时间
            carRecordRepository.save(c.get());
        }else{
            return null;
        }
        return c.get();
    }

    //删除
    public int delete(Long id) {
        CarRecord r = findById(id);
        if (r == null) {
            return 1;
        } else {
            carRecordRepository.deleteById(r.getId());
            return 0;
        }

    }
}
