package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Resource
    private CarRepository carRepository;

    //根据id查询
    public Car findById(Long id) {
        Optional<Car> r = carRepository.findById(id);
        return r.orElse(null);//存在返回值，不存在返回null
    }

    //根据name查询
    public Car findByNumber(String name) {
        Optional<Car> r = carRepository.findByName(name);
        return r.orElse(null);
    }
    //查询全部内容
    public List<Car> list(){
       return  carRepository.findAll();
    }
    //查询全部内容,实现分页
    public Page<Car> list(int page, int limit, String sortType) {
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? Sort.by("id").ascending() : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page-1,limit, sort);
        Page<Car> resources = carRepository.findAll(pageable);

        return resources;
    }

    //判断该权限是否存在
    public boolean isExist(String name) {
        Optional<Car> r = carRepository.findByName(name);
        if (r.isPresent()) {
            return true;
        }
        return false;
    }

    //新增操作
    public int addCarData(Car car) {
        boolean exist = isExist(car.getName());
        if(!exist){
            Car c = new Car();
            c.setName(car.getName());//汽车名称
            c.setDetail(car.getDetail());//车辆描述
            c.setPrice(car.getPrice());//价格
            c.setTypeName(car.getTypeName());//汽车类型
            c.setBrandName(car.getBrandName()); //汽车品牌名称
            c.setImgUrl(car.getImgUrl());
            /*c.setColor(car.getColor()); //车辆颜色
            c.setNumber(car.getNumber());//车牌号
            c.setLevel(car.getLevel());//配置款
            c.setSeats(car.getSeats());//座位数
            c.setDoors(car.getDoors());//门数
            c.setDrive(c.getDrive());//驱动方式：1/前驱 2/后驱 3/四驱
            c.setDisplacement(car.getDisplacement());//排量
            c.setEnergyName(car.getEnergyName());//燃料类型名称
            c.setGearName(car.getGearName());//变速箱类型名称
            c.setUpWindow(car.getUpWindow());//是否有天窗
            c.setRadar(car.getRadar());//是否有雷达
            c.setGps(car.getGps());//是否有gps
            c.setStatus(0);//出租状态：0/未出租 1/已出租*/

            carRepository.save(c);
            return 0;
        }
        return 1;

    }

    /**
     * 编辑操作
     * @return
     */
    public int editResourceData(Car car) {
        Optional<Car> p = carRepository.findById(car.getId());
        if (p.isPresent()) {
            p.get().setName(car.getName());
            carRepository.save(p.get());
        }else{
            return 1;
        }

        return 0;
    }

    //删除
    public String deleteResourceData(Long id) {
        Car r = findById(id);
        if (r == null) {
            return "1";
        } else {
            carRepository.deleteById(r.getId());
            return "0";
        }

    }

}
