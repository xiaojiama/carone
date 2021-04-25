package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.admin.entity.MyUser;
import com.codermy.myspringsecurityplus.admin.service.UserService;
import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import com.codermy.myspringsecurityplus.car.service.CarRecordService;
import com.codermy.myspringsecurityplus.car.service.CarService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/*
 * CarRecord controller
 *
 * @author ma
 * @date 2021/02
 */
@Controller
@RequestMapping("/api/carRecord")
public class CarRecordController {
    @Resource
    private CarRecordService carRecordService;
    @Resource
    private CarService carService;
    @Resource
    private UserService userService;

    @GetMapping("/index")
    @ApiOperation(value = "汽车订单页面")
    public String index(){
        return "system/car/car-record";
    }

    @GetMapping(value = "/toAdd/{carId}")
    @ApiOperation(value = "添加汽车详情页面")
    public String addCarRecord(@PathVariable("carId") Long carId,Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        MyUser user = userService.getUserByName(name);
        Car car = carService.findById(carId);
        model.addAttribute("car",car);
        model.addAttribute("user",user);
        return "../static/detail.html";
    }
    @GetMapping(value = "/toEdit")
    @ApiOperation(value = "修改汽车页面")
    public String editCarRecord(Model model, CarRecord CarRecord) {
        model.addAttribute("CarRecord",carRecordService.findById(CarRecord.getId()));
        return "system/car/carRecord-edit";
    }
    //查询所有操作
    @GetMapping("/selectAll")
    @ResponseBody
    public Result listResource() {
        List<CarRecord> CarRecordList = carRecordService.list();

        return Result.ok().code(0).data(CarRecordList);
    }

    //  根据id查询操作
    @GetMapping("/selectById/{id}")
    public Result getResourceId(@PathVariable("id") Long id){
        CarRecord r = carRecordService.findById(id);
        return  Result.ok().data((List) r);
    }
    /*//  根据name查询操作
    @GetMapping("/selectByName")
    public Result getResourceName(CarRecord CarRecord){
        CarRecord r = carRecordService.findByName(CarRecord.getName());
        return  Result.ok().data(r);
    }*/

    //  新增操作
    @PostMapping("/add")
    @ResponseBody
    public Result addCarRecord(@RequestBody CarRecord c) throws IOException {
        //新增
        CarRecord carRecord = carRecordService.add(c);
        return Result.ok().data(carRecord);

    }
    ///编辑操作
    @PutMapping("/edit")
    @ResponseBody
    public Result editCarRecord(@RequestBody CarRecord c) {
        int status = carRecordService.edit(c);
        switch (status) {
            case 0:
                return Result.ok().data(carRecordService.list());
            case 1:
                return Result.error().data(carRecordService.list());
            default:
                return Result.error().data(carRecordService.list());
        }

    }
    //  删除操作
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deleteCarRecord(@PathVariable("id") Long id){
        int status = carRecordService.delete(id);
        switch (status) {
            case 0:
                return Result.ok();
            case 1:
                return Result.error().message("未找到该汽车信息");
            default:
                return Result.error().message("未知错误");
        }
    }
}
