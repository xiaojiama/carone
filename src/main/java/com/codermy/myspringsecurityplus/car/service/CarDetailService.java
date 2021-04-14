package com.codermy.myspringsecurityplus.car.service;

import com.codermy.myspringsecurityplus.car.entity.*;
import com.codermy.myspringsecurityplus.car.repository.CarDetailRepository;
import com.codermy.myspringsecurityplus.car.repository.EnergyRepository;
import com.codermy.myspringsecurityplus.car.repository.GearRepository;
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
public class CarDetailService {
    @Resource
    private CarDetailRepository carDetailRepository;
    @Resource
    private EnergyRepository energyRepository;
    @Resource
    private GearRepository gearRepository;

    //查询燃料类型全部内容
    public List<Energy> energyList(){
        return  energyRepository.findAll();
    }
    //查询变速类型全部内容
    public List<Gear> gearList(){
        return  gearRepository.findAll();
    }


    //根据id查询
    public CarDetail findById(int id) {
        Optional<CarDetail> r = carDetailRepository.findById(id);
        return r.orElse(null);//存在返回值，不存在返回null
    }

    //根据汽车id获得汽车详情信息
    public CarDetail findByCarId(Long id) {
        Optional<CarDetail> r = carDetailRepository.findByCarId(id);
        return r.orElse(null);//存在返回值，不存在返回null
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

    //新增操作
    public int addCarDetailData(CarDetail car) {
        CarDetail c;
        //如果id为空则为新增操作，定义一个新的对象，如果不为空，则为编辑，该对象存储的是从数据库查出来的数据
        if(car.getId()!=0){//编辑
            c = findById(car.getId());
        }else{//新增
            c = new CarDetail();
            c.setCreateTime(new Date());//创建时间
        }
        c.setCarId(car.getCarId());//汽车id
        c.setProductYear(car.getProductYear());//汽车年款
        c.setColor(car.getColor()); //车辆颜色
        c.setNumber(car.getNumber());//车牌号
        c.setLevel(car.getLevel());//配置款
        c.setSeats(car.getSeats());//座位数
        c.setDoors(car.getDoors());//门数
        c.setDrive(car.getDrive());//驱动方式：1/前驱 2/后驱 3/四驱
        c.setDisplacement(car.getDisplacement());//排量
        c.setUpdateTime(new Date());//更新时间
        //根据燃料类型id，获取燃料类型对象
        Optional<Energy> e = energyRepository.findById(car.getEnergyId());
        if(e.isPresent()){
            //添加汽车燃料类型
            c.setEnergy(e.get());
        }
        //变速箱类型名称
        Optional<Gear> g = gearRepository.findById(car.getGearId());
        if(e.isPresent()){
            //添加汽车变速箱类型
            c.setGear(g.get());
        }
        c.setUpWindow(car.getUpWindow());//是否有天窗
        c.setRadar(car.getRadar());//是否有雷达
        c.setGps(car.getGps());//是否有gps

        carDetailRepository.save(c);
        return 0;
    }

    //删除
    public int deleteCarDetailData(int id) {
        CarDetail r = findById(id);
        if (r == null) {
            return 1;
        } else {
            carDetailRepository.deleteById(r.getId());
            return 0;
        }

    }

}
