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
        c.setCustomerId(cr.getCustomerId());
        c.setCarId(cr.getCarId());
        c.setTimeLong(days);
        c.setStatus(cr.getStatus());
        c.setDeposit(cr.getDeposit());
        c.setRent(cr.getRent());
        c.setUserId(cr.getUserId());
        c.setCreateTime(cr.getCreateTime());
        c.setEndTime(cr.getEndTime());
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
            c.get().setCustomerId(cr.getCustomerId());
            c.get().setCarId(cr.getCarId());
            c.get().setTimeLong(days);
            c.get().setStatus(cr.getStatus());
            c.get().setDeposit(cr.getDeposit());
            c.get().setRent(cr.getRent());
            c.get().setUserId(cr.getUserId());
            c.get().setCreateTime(cr.getCreateTime());
            c.get().setEndTime(cr.getEndTime());
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
