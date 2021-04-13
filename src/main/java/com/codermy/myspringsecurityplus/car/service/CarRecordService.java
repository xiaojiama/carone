package com.codermy.myspringsecurityplus.car.service;

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
    public int addCarRecordData(CarRecord r) {
        /*boolean exist = isExist(r.getNumber());
        if(!exist){
            CarDetail ur = new CarDetail();
            //ur.setNumber(r.getNumber());

            carRecordRepository.save(r);
            return 0;
        }*/
        return 1;

    }

    /**
     * 编辑操作
     * @return
     */
    public int editCarRecordData(CarRecord cd) {
        Optional<CarRecord> p = carRecordRepository.findById(cd.getId());
        if (p.isPresent()) {
            //p.get().setName(cd.getNumber());
            carRecordRepository.save(p.get());
        }else{
            return 1;
        }

        return 0;
    }

    //删除
    public int deleteCarRecordData(Long id) {
        CarRecord r = findById(id);
        if (r == null) {
            return 1;
        } else {
            carRecordRepository.deleteById(r.getId());
            return 0;
        }

    }
}
