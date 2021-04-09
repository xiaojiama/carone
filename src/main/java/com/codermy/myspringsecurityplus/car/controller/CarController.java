package com.codermy.myspringsecurityplus.car.controller;

import cn.hutool.core.lang.Assert;
import com.codermy.myspringsecurityplus.admin.entity.MyRole;
import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarBrand;
import com.codermy.myspringsecurityplus.car.repository.CarBrandRepository;
import com.codermy.myspringsecurityplus.car.service.CarService;
import com.codermy.myspringsecurityplus.car.utils.UploadUtils;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*
 * car controller
 *
 * @author ma
 * @date 2021/02
 */
@Controller
@RequestMapping("/api/car")
public class CarController {
    @Resource
    private CarService carService;
    @Resource
    private CarBrandRepository carBrandRepository;

    @GetMapping("/brand")
    @ResponseBody
    public Result brand(){
        List<CarBrand> brandList = carBrandRepository.findAll();
        return Result.ok().code(0).data(brandList);
    }

    @GetMapping("/index")
    public String index(){
        return "system/car/car";
    }

    @GetMapping(value = "/toAdd")
    @ApiOperation(value = "添加汽车页面")
    public String addCar(Model model) {
        model.addAttribute("Car",new Car());
        return "/system/car/car-add";
    }
    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改汽车页面")
    public String editRole(Model model, Car car) {
        model.addAttribute("Car",carService.findById(car.getId()));
        return "system/car/car-edit";
    }
    //查询所有操作
    @GetMapping("/selectAll")
    @ResponseBody
    public Result listResource() {
        List<Car> carList = carService.list();

        return Result.ok().code(0).data(carList);
    }

   //  查询操作，分页展示
    @GetMapping("/selectCars")
    public Result listResource(@RequestParam(defaultValue="1", required = false) int page,
                                  @RequestParam(defaultValue = "10", required = false) int limit,
                                  @RequestParam(defaultValue = "descending", required = false) String sort) {
        return Result.ok().data(carService.list(page,limit,sort));
    }
    //  根据id查询操作
    @GetMapping("/selectById/{id}")
    public Result getResourceId(@PathVariable("id") Long id){
        Car r = carService.findById(id);
        return  Result.ok().data((List) r);
    }
    //  根据name查询操作
    @GetMapping("/selectByName")
    public Result getResourceName(@RequestParam(value = "name", required = false, defaultValue = "0") String name){
        Car r = carService.findByNumber(name);
        return  Result.ok().data((List) r);
    }
    @ResponseBody
    @RequestMapping("/upload")
    public Map<String,Object> addlunbo(@RequestParam("file") MultipartFile file) {
        Assert.notNull(file, "上传文件不能为空");
        // 拿到文件名
        String filename = System.currentTimeMillis()+file.getOriginalFilename();

        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());
        Map map = new HashMap<String,Object>();
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator +filename);
            String savepath = newFile.getAbsolutePath();
            System.out.println(savepath);

            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);

            map.put("msg","ok");
            map.put("code",0);
            map.put("data",filename);

        } catch (Exception e) {
            map.put("msg","error");
            map.put("code",500);
            e.printStackTrace();
        }

        return map;
    }
    //  新增操作
    @PostMapping("/add")
    @ResponseBody
    public Result addResource(@RequestBody Car r) throws IOException {
        //新增
        int status = carService.addCarData(r);
        switch (status) {
            case 0:
                return Result.ok().data(carService.list());
            case 1:
                return Result.error().data(carService.list());
            default:
                return Result.error().data(carService.list());
        }
    }
    ///编辑操作
    @PutMapping("/edit")
    public Result editResource(@Valid @ModelAttribute Car c, BindingResult bindingResult) {
        //校验数据，判断是否符合参数注解要求
        if(bindingResult.hasErrors()){
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            return Result.ok().message(defaultMessage).data(carService.list());
        }else{
            int status = carService.editResourceData(c);
            switch (status) {
                case 0:
                    return Result.ok().data(carService.list());
                case 1:
                    return Result.error().data(carService.list());
                default:
                    return Result.error().data(carService.list());
            }
        }
    }
    //  删除操作
    @DeleteMapping("/delete/{id}")
    public Result deleteResource(@PathVariable("id") Long id){
        String status = carService.deleteResourceData(id);
        switch (status) {
            case "0":
                return Result.ok().data(carService.list());
            case "1":
                return Result.error().data(carService.list());
            default:
                return Result.error().data(carService.list());
        }
    }
}
