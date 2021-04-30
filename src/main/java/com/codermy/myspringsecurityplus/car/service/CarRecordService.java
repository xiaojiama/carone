package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import com.codermy.myspringsecurityplus.car.repository.CarRecordRepository;
import com.codermy.myspringsecurityplus.car.repository.CarRepository;
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
    public List<Object> findByCustomerId(Long id) {
        List<Object> r = carRecordRepository.findByCustomerId(id);
        return r;
    }
    //根据name查询
   /* public CarRecord findByName(String number) {
        Optional<CarRecord> r = carRecordRepository.findByName(number);
        return r.orElse(null);
    }*/
    //判断是否存在
  /*  public boolean isExist(String number) {
        Optional<CarRecord> r = carRecordRepository.findByName(number);
        if (r.isPresent()) {
            return true;
        }
        return false;
    }*/

    //新增操作
    public CarRecord add(CarRecord cr) {
        Optional<Car> car = carRepository.findById(cr.getCarId());
        long endTime = cr.getEndTime().getTime();
        long createTime = cr.getCreateTime().getTime();
        int days = (int) ((endTime - createTime) / (1000*3600*24));
        CarRecord c = new CarRecord();
        c.setCustomerId(cr.getCustomerId());//客户Id
        c.setCarId(cr.getCarId());//汽车Id
        c.setCarName(car.get().getName());
        c.setCarImgUrl(car.get().getImgUrl());
        c.setTimeLong(days);//租赁时长
        c.setStatus("申请中");//此订单状态 进行中，申请中，已完成
        c.setDeposit(cr.getDeposit());//押金
        c.setName(cr.getName());//真实名称
        c.setPhone(cr.getPhone());//电话
        c.setIdentity(cr.getIdentity());//身份证
        c.setPrice(cr.getPrice()); //日租金
        int rent = cr.getPrice()*days;
        c.setRent(rent); //租金
        c.setUserId(cr.getUserId());//工作人员id
        c.setCreateTime(cr.getCreateTime());//租车时间
        c.setEndTime(cr.getEndTime());//还车时间
        carRecordRepository.save(c);
        return c;


    }

    /**
     * 编辑操作
     * @return
     */
    public int edit(CarRecord cr) {
        Optional<CarRecord> c = carRecordRepository.findById(cr.getId());
        Optional<Car> car = carRepository.findById(cr.getCarId());
        long endTime = cr.getEndTime().getTime();
        long createTime = cr.getCreateTime().getTime();
        int days = (int) ((endTime - createTime) / (1000*3600*24));
        if (c.isPresent()) {
            c.get().setCustomerId(cr.getCustomerId());//客户Id
            c.get().setCarId(cr.getCarId());//汽车Id
            c.get().setCarName(car.get().getName());
            c.get().setCarImgUrl(car.get().getImgUrl());
            c.get().setTimeLong(days);//租赁时长
            c.get().setStatus(cr.getStatus());//此订单状态 进行中，申请中，已完成
            c.get().setDeposit(cr.getDeposit());//押金
            c.get().setName(cr.getName());//真实名称
            c.get().setPhone(cr.getPhone());//电话
            c.get().setIdentity(cr.getIdentity());//身份证
            c.get().setPrice(cr.getPrice()); //日租金
            int rent = cr.getPrice()*days;
            c.get().setRent(rent); //租金
            c.get().setUserId(cr.getUserId());//工作人员id
            c.get().setCreateTime(cr.getCreateTime());//租车时间
            c.get().setEndTime(cr.getEndTime());//还车时间
            carRecordRepository.save(c.get());
        }else{
            return 1;
        }

        return 0;
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
