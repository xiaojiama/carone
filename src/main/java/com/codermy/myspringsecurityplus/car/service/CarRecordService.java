package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import com.codermy.myspringsecurityplus.car.repository.CarRecordRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CarRecordService {
    @Resource
    private CarRecordRepository carRecordRepository;

    //查询全部内容
    public List<CarRecord> list(){
        return  carRecordRepository.findAll();
    }

    //根据id查询
    public CarRecord findById(Long id) {
        Optional<CarRecord> r = carRecordRepository.findById(id);
        return r.orElse(null);//存在返回值，不存在返回null
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
    public int add(CarRecord cr) {
        long endTime = cr.getEndTime().getTime();
        long createTime = cr.getCreateTime().getTime();
        int days = (int) ((endTime - createTime) / (1000*3600*24));
        CarRecord c = new CarRecord();
        c.setCustomerId(cr.getCustomerId());//客户Id
        c.setCarId(cr.getCarId());//汽车Id
        c.setTimeLong(days);//租赁时长
        c.setStatus(cr.getStatus());//此订单状态 进行中，申请中，已完成
        c.setDeposit(cr.getDeposit());//押金
        c.setName(cr.getName());//真实名称
        c.setPhone(cr.getPhone());//电话
        c.setIdentity(cr.getIdentity());//身份证
        c.setPrice(cr.getPrice()); //日租金
        Double rent = cr.getPrice()*days;
        c.setRent(rent); //租金
        c.setUserId(cr.getUserId());//工作人员id
        c.setCreateTime(cr.getCreateTime());//租车时间
        c.setEndTime(cr.getEndTime());//还车时间
        carRecordRepository.save(c);
        return 0;


    }

    /**
     * 编辑操作
     * @return
     */
    public int edit(CarRecord cr) {
        Optional<CarRecord> c = carRecordRepository.findById(cr.getId());
        long endTime = cr.getEndTime().getTime();
        long createTime = cr.getCreateTime().getTime();
        int days = (int) ((endTime - createTime) / (1000*3600*24));
        if (c.isPresent()) {
            c.get().setCustomerId(cr.getCustomerId());//客户Id
            c.get().setCarId(cr.getCarId());//汽车Id
            c.get().setTimeLong(days);//租赁时长
            c.get().setStatus(cr.getStatus());//此订单状态 进行中，申请中，已完成
            c.get().setDeposit(cr.getDeposit());//押金
            c.get().setName(cr.getName());//真实名称
            c.get().setPhone(cr.getPhone());//电话
            c.get().setIdentity(cr.getIdentity());//身份证
            c.get().setPrice(cr.getPrice()); //日租金
            Double rent = cr.getPrice()*days;
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
