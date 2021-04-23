package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarBrand;
import com.codermy.myspringsecurityplus.car.service.CarBrandService;
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
@RequestMapping("/api/carBrand")
public class CarBrandController {
    @Resource
    private CarBrandService carBrandService;
    

    @GetMapping("/index")
    @ApiOperation(value = "汽车列表页面")
    public String index(){
        return "system/car/car-brand";
    }
    @GetMapping(value = "/toAdd")
    @ApiOperation(value = "添加汽车品牌页面")
    public String addCarBrand(Model model) {
        model.addAttribute("CarBrand",new CarBrand());
        return "/system/car/car-brand-add";
    }
    @GetMapping(value = "/toEdit")
    @ApiOperation(value = "修改汽车品牌页面")
    public String editCarBrand(Model model, CarBrand cd) {
        model.addAttribute("CarBrand",carBrandService.findById(cd.getId()));
        return "system/car/car-brand-edit";
    }
    //查询所有操作
    @GetMapping("/selectAll")
    @ResponseBody
    public Result listResource() {
        List<CarBrand> CarRecordList = carBrandService.list();

        return Result.ok().code(0).data(CarRecordList);
    }

    //  根据id查询操作
    @GetMapping("/selectById/{id}")
    public Result getResourceId(@PathVariable("id") int id){
        CarBrand r = carBrandService.findById(id);
        return  Result.ok().data((List) r);
    }
    //  根据name查询操作
    @GetMapping("/selectByName/{name}")
    public Result getResourceName(@PathVariable("name") String name){
        CarBrand r = carBrandService.findByName(name);
        return  Result.ok().data(r);
    }

    //  新增操作
    @PostMapping("/add")
    @ResponseBody
    public Result addCarBrand(@RequestBody CarBrand c) throws IOException {
        //新增
        int status = carBrandService.add(c);
        return Result.ok().data(carBrandService.list());

    }
    ///编辑操作
    @PutMapping("/edit")
    @ResponseBody
    public Result editCarBrand(@RequestBody CarBrand c) {
        int status = carBrandService.edit(c);
        switch (status) {
            case 0:
                return Result.ok().data(carBrandService.list());
            case 1:
                return Result.error().data(carBrandService.list());
            default:
                return Result.error().data(carBrandService.list());
        }

    }
    //  删除操作
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deleteCarBrand(@PathVariable("id") int id){
        int status = carBrandService.delete(id);
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
