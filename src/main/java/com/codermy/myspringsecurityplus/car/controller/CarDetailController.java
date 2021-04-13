package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarDetail;
import com.codermy.myspringsecurityplus.car.service.CarDetailService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    @GetMapping(value = "/toAdd")
    @ApiOperation(value = "添加汽车详情页面")
    public String addCarRecord(Model model,Car car) {
        Long id = car.getId();
        model.addAttribute("CarId",car.getId());
        return "system/car/car-detail-add";
    }
    @GetMapping(value = "/toEdit")
    @ApiOperation(value = "修改汽车页面")
    public String editCarRecord(Model model, CarDetail carDetail) {
        model.addAttribute("CarRecord",carDetailService.findById(carDetail.getId()));
        return "system/car/car-detail-edit";
    }

    //查询所有操作
    @GetMapping("/selectAll")
    @ResponseBody
    public Result listResource() {
        List<CarDetail> urlResource = carDetailService.list();

        return Result.ok().data(urlResource);
    }

   //  查询操作，分页展示
    @GetMapping("/selectResoures")
    public Result listResource(@RequestParam(defaultValue="1", required = false) int page,
                                  @RequestParam(defaultValue = "10", required = false) int limit,
                                  @RequestParam(defaultValue = "descending", required = false) String sort) {
        return Result.ok().data(carDetailService.list(page,limit,sort));
    }
    //  根据id查询操作
    @GetMapping("/selectById/{id}")
    public Result getResourceId(@PathVariable("id") Long id){
        CarDetail r = carDetailService.findById(id);
        return  Result.ok().data((List) r);
    }
    //  根据name查询操作
    @GetMapping("/selectByName")
    public Result getResourceName(@RequestParam(value = "name", required = false, defaultValue = "0") String name){
        CarDetail r = carDetailService.findByNumber(name);
        return  Result.ok().data((List) r);
    }
    //  新增操作
    @PostMapping("/add")
    public Result addResource(@Valid @ModelAttribute CarDetail r, BindingResult bindingResult) {
        //校验数据，判断是否符合参数注解要求
        if(bindingResult.hasErrors()){
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            return Result.ok().message(defaultMessage).data(carDetailService.list());
        }else{
            int status = carDetailService.addResourceData(r);
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
    ///编辑操作
    @PutMapping("/edit")
    public Result editResource(@Valid @ModelAttribute CarDetail c, BindingResult bindingResult) {
        //校验数据，判断是否符合参数注解要求
        if(bindingResult.hasErrors()){
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            return Result.ok().message(defaultMessage).data(carDetailService.list());
        }else{
            int status = carDetailService.editResourceData(c);
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
    //  删除操作
    @DeleteMapping("/delete/{id}")
    public Result deleteResource(@PathVariable("id") Long id){
        String status = carDetailService.deleteResourceData(id);
        switch (status) {
            case "0":
                return Result.ok().data(carDetailService.list());
            case "1":
                return Result.error().data(carDetailService.list());
            default:
                return Result.error().data(carDetailService.list());
        }
    }
}
