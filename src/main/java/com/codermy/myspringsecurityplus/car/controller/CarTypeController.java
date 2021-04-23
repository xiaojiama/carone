package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.car.entity.CarType;
import com.codermy.myspringsecurityplus.car.service.CarTypeService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/carType")
public class CarTypeController {
    @Resource
    private CarTypeService carTypeService;
    

    @GetMapping("/index")
    @ApiOperation(value = "汽车列表页面")
    public String index(){
        return "system/car/car-type";
    }
    @GetMapping(value = "/toAdd")
    @ApiOperation(value = "添加汽车品牌页面")
    public String addCarType(Model model) {
        model.addAttribute("carType",new CarType());
        return "/system/car/car-type-add";
    }
    @GetMapping(value = "/toEdit")
    @ApiOperation(value = "修改汽车品牌页面")
    public String editCarType(Model model, CarType cd) {
        model.addAttribute("carType",carTypeService.findById(cd.getId()));
        return "system/car/car-type-edit";
    }
    //查询所有操作
    @GetMapping("/selectAll")
    @ResponseBody
    public Result list() {
        List<CarType> CarRecordList = carTypeService.list();

        return Result.ok().code(0).data(CarRecordList);
    }

    //  根据id查询操作
    @GetMapping("/selectById/{id}")
    public Result getResourceId(@PathVariable("id") int id){
        CarType r = carTypeService.findById(id);
        return  Result.ok().data((List) r);
    }
    //  根据name查询操作
    @GetMapping("/selectByName/{name}")
    public Result getResourceName(@PathVariable("name") String name){
        CarType r = carTypeService.findByName(name);
        return  Result.ok().data(r);
    }

    //  新增操作
    @PostMapping("/add")
    @ResponseBody
    public Result addcarType(@RequestBody CarType c) throws IOException {
        //新增
        int status = carTypeService.add(c);
        return Result.ok().data(carTypeService.list());

    }
    ///编辑操作
    @PutMapping("/edit")
    @ResponseBody
    public Result editcarType(@RequestBody CarType c) {
        int status = carTypeService.edit(c);
        switch (status) {
            case 0:
                return Result.ok().data(carTypeService.list());
            case 1:
                return Result.error().data(carTypeService.list());
            default:
                return Result.error().data(carTypeService.list());
        }

    }
    //  删除操作
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deletecarType(@PathVariable("id") int id){
        int status = carTypeService.delete(id);
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
