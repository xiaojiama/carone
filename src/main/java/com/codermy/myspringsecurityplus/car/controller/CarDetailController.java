package com.codermy.myspringsecurityplus.car.controller;

import com.alibaba.fastjson.JSONObject;
import com.codermy.myspringsecurityplus.car.entity.*;
import com.codermy.myspringsecurityplus.car.service.CarDetailService;
import com.codermy.myspringsecurityplus.car.utils.COSClientUtil;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/*
 * Resource controller
 *
 * @author ma
 * @date 2021/02
 */
@Controller
@RequestMapping("/api/carDetail")
public class CarDetailController {
    @Resource
    private CarDetailService carDetailService;

    //查询燃料类型全部内容
    @GetMapping("/energy")
    @ResponseBody
    public Result brand(){
        List<Energy> energy = carDetailService.energyList();
        return Result.ok().code(0).data(energy);
    }
    //查询变速类型全部内容
    @GetMapping("/gear")
    @ResponseBody
    public Result type(){
        List<Gear> gears = carDetailService.gearList();
        return Result.ok().code(0).data(gears);
    }

    @GetMapping(value = "/toAdd")
    @ApiOperation(value = "添加汽车详情页面")
    public String addCarRecord(Model model,Car car) {
        CarDetail carDetail = carDetailService.findByCarId(car.getId());
        if(carDetail==null){
            carDetail= new CarDetail();
        }
        model.addAttribute("CarDetail",carDetail);
        model.addAttribute("CarId",car.getId());
        return "system/car/car-detail-add";
    }

    //查询所有操作
    @GetMapping("/selectAll")
    @ResponseBody
    public Result carDetailList() {
        List<CarDetail> carDetailList = carDetailService.list();

        return Result.ok().data(carDetailList);
    }

   //  查询操作，分页展示
    @GetMapping("/selectResoures")
    public Result listResource(@RequestParam(defaultValue="1", required = false) int page,
                                  @RequestParam(defaultValue = "10", required = false) int limit,
                                  @RequestParam(defaultValue = "descending", required = false) String sort) {
        return Result.ok().data(carDetailService.list(page,limit,sort));
    }
    //  根据id查询操作
    @GetMapping("/selectByCarId/{id}")
    @ResponseBody
    public Result getCarDetail(@PathVariable("id") Long id){
        CarDetail r = carDetailService.findByCarId(id);
        return  Result.ok().data(r);
    }
    //  新增操作
    @RequestMapping("/add")
    @ResponseBody
    public Result addCarDetail(@RequestBody CarDetail c) {
        int status = carDetailService.addCarDetailData(c);
        switch (status) {
            case 0:
                return Result.ok().data(carDetailService.list());
            case 1:
                return Result.error().data(carDetailService.list());
            default:
                return Result.error().data(carDetailService.list());
        }
    }

    //  删除操作
    @DeleteMapping("/delete/{id}")
    public Result deleteResource(@PathVariable("id") int id){
        int status = carDetailService.deleteCarDetailData(id);
        switch (status) {
            case 0:
                return Result.ok().data(carDetailService.list());
            case 1:
                return Result.error().data(carDetailService.list());
            default:
                return Result.error().data(carDetailService.list());
        }
    }

}
