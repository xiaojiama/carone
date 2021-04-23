package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.CarType;
import com.codermy.myspringsecurityplus.car.repository.CarTypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CarTypeService {
    @Resource
    private CarTypeRepository carTypeRepository;

    //根据id查询
    public CarType findById(int id) {
        Optional<CarType> r = carTypeRepository.findById(id);
        return r.orElse(null);//存在返回值，不存在返回null
    }

    //根据name查询
    public CarType findByName(String name) {
        Optional<CarType> r = carTypeRepository.findByName(name);
        return r.orElse(null);
    }
    //查询全部内容
    public List<CarType> list(){
       return  carTypeRepository.findAll();
    }

    //判断该类型是否存在
    public boolean isExist(String name) {
        Optional<CarType> r = carTypeRepository.findByName(name);
        if (r.isPresent()) {
            return true;
        }
        return false;
    }

    //新增操作
    public int add(CarType ct) {
        boolean exist = isExist(ct.getName());
        if(!exist){
            CarType c = new CarType();
            c.setName(ct.getName());//汽车类型名称
            c.setImgUrl(ct.getImgUrl());

            carTypeRepository.save(c);
            return 0;
        }
        return 1;

    }

    /**
     * 编辑操作
     * @return
     */
    public int edit(CarType ct) {
        Optional<CarType> c = carTypeRepository.findById(ct.getId());
        if (c.isPresent()) {
            c.get().setName(ct.getName());//汽车类型名称
            c.get().setImgUrl(ct.getImgUrl());//图片
            //保存
            carTypeRepository.save(c.get());
        }else{
            return 1;
        }

        return 0;
    }

    //删除
    public int delete(int id) {
        CarType c = findById(id);
        if (c == null) {
            return 1;
        } else {
            carTypeRepository.deleteById(c.getId());
            return 0;
        }

    }

}
