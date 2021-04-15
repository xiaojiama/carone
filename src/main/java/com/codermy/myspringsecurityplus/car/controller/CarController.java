package com.codermy.myspringsecurityplus.car.controller;

import cn.hutool.core.lang.Assert;
import com.codermy.myspringsecurityplus.car.entity.*;
import com.codermy.myspringsecurityplus.car.repository.CarDetailRepository;
import com.codermy.myspringsecurityplus.car.repository.DocumentRepository;
import com.codermy.myspringsecurityplus.car.service.CarDetailService;
import com.codermy.myspringsecurityplus.car.service.CarService;
import com.codermy.myspringsecurityplus.car.utils.UploadUtils;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    private CarDetailRepository carDetailRepository;
    @Resource
    private DocumentRepository documentRepository;

    //查询所有汽车品牌
    @GetMapping("/brand")
    @ResponseBody
    public Result brand(){
        List<CarBrand> carBrands = carService.brandList();
        return Result.ok().code(0).data(carBrands);
    }
    //查询所有汽车类型
    @GetMapping("/type")
    @ResponseBody
    public Result type(){
        List<CarType> carTypes = carService.typeList();
        return Result.ok().code(0).data(carTypes);
    }
    //查询所有省份
    @GetMapping("/province")
    @ResponseBody
    public Result province(){
        List<Province> provinceList = carService.provinceList();
        return Result.ok().code(0).data(provinceList);
    }
    //  查询该省级下城市的全部内容
    @GetMapping("/city/{provinceId}")
    @ResponseBody
    public Result getCity(@PathVariable("provinceId") int provinceId){
        List<City> cityList = carService.cityList(provinceId);
        return  Result.ok().data(cityList);
    }
    //  查询该城市下门店的全部内容
    @GetMapping("/location/{cityId}")
    @ResponseBody
    public Result getLocation(@PathVariable("cityId") int cityId){
        List<Location> locationList = carService.locationList(cityId);
        return  Result.ok().data(locationList);
    }

    @GetMapping("/index")
    @ApiOperation(value = "汽车列表页面")
    public String index(){
        return "system/car/car";
    }

    @GetMapping("/home")
    @ApiOperation(value = "首页")
    public String tohome(){
        return "redirect:index.html";
    }

    @GetMapping(value = "/toAdd")
    @ApiOperation(value = "添加汽车页面")
    public String addCar(Model model) {
        model.addAttribute("Car",new Car());
        return "/system/car/car-add";
    }
    @GetMapping(value = "/toEdit")
    @ApiOperation(value = "修改汽车页面")
    public String editCar(Model model, Car car) {
        model.addAttribute("Car",carService.findById(car.getId()));
        return "system/car/car-edit";
    }
    //  查询该汽车下详情的全部内容
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "汽车+详情页面")
    public String getCarDetail(@PathVariable("id") Long id,Model model){
        Car car = carService.findById(id);
        Optional<CarDetail> det = carDetailRepository.findByCarId(id);

        CarDetail carDetail = new CarDetail();

        if(det.isPresent()){
            carDetail = det.get();
        }


        model.addAttribute("car",car);
        model.addAttribute("detail",carDetail);
        return "system/car/car-detail";
    }
    //查询所有操作
    @GetMapping("/document/{id}")
    @ResponseBody
    public Result listDocument(@PathVariable("id") Long id) {
        List<Document> documentList = documentRepository.findByCarId(id);

        return Result.ok().code(0).data(documentList);
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
    public Result getResourceName(Car car){
        Car r = carService.findByName(car.getName());
        return  Result.ok().data(r);
    }
    @ResponseBody
    @RequestMapping("/upload")
    public Map<String,Object> addPicture(@RequestParam("file") MultipartFile file) {
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
    public Result addCar(@RequestBody Car c) throws IOException {
        //新增
        int status = carService.addCarData(c);
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
    @ResponseBody
    public Result editCar(@RequestBody Car c) {
        int status = carService.editCarData(c);
        switch (status) {
            case 0:
                return Result.ok().data(carService.list());
            case 1:
                return Result.error().data(carService.list());
            default:
                return Result.error().data(carService.list());
        }

    }
    //  删除操作
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deleteCar(@PathVariable("id") Long id){
        int status = carService.deleteCarData(id);
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
