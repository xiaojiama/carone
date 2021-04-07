package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.CarDetail;
import com.codermy.myspringsecurityplus.car.repository.CarDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CarDetailService {
    @Resource
    private CarDetailRepository carDetailRepository;

    //根据id查询
    public CarDetail findById(Long id) {
        Optional<CarDetail> r = carDetailRepository.findById(id);
        return r.orElse(null);//存在返回值，不存在返回null
    }

    //根据name查询
    public CarDetail findByNumber(String number) {
        Optional<CarDetail> r = carDetailRepository.findByNumber(number);
        return r.orElse(null);
    }
    //查询全部内容
    public List<CarDetail> list(){
       return  carDetailRepository.findAll();
    }
    //查询全部内容,实现分页
    public Page<CarDetail> list(int page, int limit, String sortType) {
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? Sort.by("id").ascending() : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page-1,limit, sort);
        Page<CarDetail> resources = carDetailRepository.findAll(pageable);

        return resources;
    }

    //判断该权限是否存在
    public boolean isExist(String number) {
        Optional<CarDetail> r = carDetailRepository.findByNumber(number);
        if (r.isPresent()) {
            return true;
        }
        return false;
    }

    //新增操作
    public int addResourceData(CarDetail r) {
        boolean exist = isExist(r.getNumber());
        if(!exist){
            CarDetail ur = new CarDetail();
            ur.setNumber(r.getNumber());

            carDetailRepository.save(r);
            return 0;
        }
        return 1;

    }

    /**
     * 编辑操作
     * @return
     */
    public int editResourceData(CarDetail cd) {
        Optional<CarDetail> p = carDetailRepository.findById(cd.getId());
        if (p.isPresent()) {
            p.get().setNumber(cd.getNumber());
            carDetailRepository.save(p.get());
        }else{
            return 1;
        }

        return 0;
    }

    //删除
    public String deleteResourceData(Long id) {
        CarDetail r = findById(id);
        if (r == null) {
            return "1";
        } else {
            carDetailRepository.deleteById(r.getId());
            return "0";
        }

    }

}
