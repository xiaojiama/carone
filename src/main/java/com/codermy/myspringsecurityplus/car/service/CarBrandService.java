package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.*;
import com.codermy.myspringsecurityplus.car.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarBrandService {
    @Resource
    private CarBrandRepository carBrandRepository;

    //根据id查询
    public CarBrand findById(int id) {
        Optional<CarBrand> r = carBrandRepository.findById(id);
        return r.orElse(null);//存在返回值，不存在返回null
    }

    //根据name查询
    public CarBrand findByName(String name) {
        Optional<CarBrand> r = carBrandRepository.findByName(name);
        return r.orElse(null);
    }
    //查询全部内容
    public List<CarBrand> list(){
       return  carBrandRepository.findAll();
    }

    //判断该品牌是否存在
    public boolean isExist(String name) {
        Optional<CarBrand> r = carBrandRepository.findByName(name);
        if (r.isPresent()) {
            return true;
        }
        return false;
    }

    //新增操作
    public int add(CarBrand carBrand) {
        boolean exist = isExist(carBrand.getName());
        if(!exist){
            CarBrand c = new CarBrand();
            c.setName(carBrand.getName());//汽车品牌名称
            c.setImgUrl(carBrand.getImgUrl());

            carBrandRepository.save(c);
            return 0;
        }
        return 1;

    }

    /**
     * 编辑操作
     * @return
     */
    public int edit(CarBrand carBrand) {
        Optional<CarBrand> c = carBrandRepository.findById(carBrand.getId());
        if (c.isPresent()) {
            c.get().setName(carBrand.getName());//汽车品牌名称
            c.get().setImgUrl(carBrand.getImgUrl());//图片\
            //保存
            carBrandRepository.save(c.get());
        }else{
            return 1;
        }

        return 0;
    }

    //删除
    public int delete(int id) {
        CarBrand c = findById(id);
        if (c == null) {
            return 1;
        } else {
            carBrandRepository.deleteById(c.getId());
            return 0;
        }

    }

}
