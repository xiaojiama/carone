package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.admin.entity.MyUser;
import com.codermy.myspringsecurityplus.admin.service.UserService;
import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import com.codermy.myspringsecurityplus.car.service.CarRecordService;
import com.codermy.myspringsecurityplus.car.service.CarService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
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

    //获取当前登录的用户信息
    public MyUser getUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        MyUser user = userService.getUserByName(name);
        return  user;
    }
    @GetMapping("/index")
    @ApiOperation(value = "订单订单页面")
    public String index(){
        return "system/car/car-record";
    }

    @GetMapping(value = "/toAdd/{carId}")
    @ApiOperation(value = "添加订单详情页面")
    public String addCarRecord(@PathVariable("carId") Long carId,Model model) {
        MyUser user = getUser();
        Car car = carService.findById(carId);
        model.addAttribute("car",car);
        model.addAttribute("user",user);
        return "../static/detail.html";
    }
    @GetMapping(value = "/toUpdate/{id}")
    @ApiOperation(value = "修改订单页面")
    public String editCar(@PathVariable("id") Long id,Model model) {
        CarRecord carRecord = carRecordService.findById(id);
        model.addAttribute("CarRecord",carRecord);
        return "system/car/car-record-edit";
    }
    @GetMapping(value = "/toEdit/{carRecordId}")
    @ApiOperation(value = "修改订单页面")
    public String editCarRecord(@PathVariable("carRecordId") Long carRecordId,Model model) {
        CarRecord carRecord = carRecordService.findById(carRecordId);
        MyUser user = getUser();
        Car car = carService.findById(carRecord.getCarId());
        model.addAttribute("CarRecord",carRecord);
        model.addAttribute("car",car);
        model.addAttribute("user",user);
        return "../static/detail.html";
    }
    //查询所有操作
    @GetMapping("/selectAll")
    @ResponseBody
    public Result list() {
        List<Object> CarRecordList = carRecordService.list();

        return Result.ok().code(0).data(CarRecordList);
    }

    //  根据id查询操作
    @GetMapping("/selectById/{id}")
    public Result getId(@PathVariable("id") Long id){
        CarRecord r = carRecordService.findById(id);
        return  Result.ok().data((List) r);
    }
    //  根据顾客id查询操作
    @GetMapping("/selectByCustomerId")
    @ResponseBody
    public Result getCustomerId(@RequestParam(defaultValue="1", required = false) int page,
                                @RequestParam(defaultValue = "10", required = false) int size,
                                @RequestParam(defaultValue = "null", required = false) String status){
        MyUser user = getUser();
        Page<CarRecord> r = carRecordService.findByCustomerId(user.getUserId(),status,page,size,"descending");
        return  Result.ok().code(0).data( r);
    }

    // 修改订单状态
    @PutMapping("/updateStatusById")
    @ResponseBody
    public Result updateStatusById(@RequestBody CarRecord c){
        //sign : 1为只需要向数据库插入状态的；2.需要插入状态和工作人员id,赔偿金额等数据；3，需要插入状态状态和还车时间；
        MyUser user = getUser();
        switch (c.getSign()){
            case 1:
                carRecordService.updateStatusById(c.getStatus(),c.getId().toString());
                break;
            case 2:
                carRecordService.updateStatusById(c.getStatus(),c.getId().toString(),c.getOverTime(),c.getIsDamaged(),c.getDamages(),user.getUserId());
                break;
            case 3:
                carRecordService.updateStatusById(c.getStatus(),c.getId().toString(),new Date());
                break;
        }
        return  Result.ok();
    }
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
        CarRecord carRecord = carRecordService.edit(c);
        return Result.ok().data(carRecord);
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
                return Result.error().message("未找到该订单信息");
            default:
                return Result.error().message("未知错误");
        }
    }
}
