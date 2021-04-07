package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.car.entity.Car;
import com.codermy.myspringsecurityplus.car.entity.CarBrand;
import com.codermy.myspringsecurityplus.car.repository.CarBrandRepository;
import com.codermy.myspringsecurityplus.car.service.CarService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    //  新增操作
    @PostMapping("/add")
    public Result addResource(@Valid @ModelAttribute Car r,
                              @RequestParam(value="file") MultipartFile uploadFile,
                              HttpSession session, BindingResult bindingResult) throws IOException {
        //校验数据，判断是否符合参数注解要求
        if(bindingResult.hasErrors()){
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            return Result.ok().message(defaultMessage).data(carService.list());
        }else{
            //获取上传文件名称
            String fileName = uploadFile.getOriginalFilename();
            //uuid+后缀名，给文件重新取名
            String finalFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            //最后存储的路径，是本地的服务器中
            String path = session.getServletContext().getRealPath("img")+ File.separator + finalFileName;
            System.out.println(path);
            //上传
            File file = new File(path);
            uploadFile.transferTo(file);

            //上传成功后，将地址存入数据库，让前端可以直接拿图片
            String idPic = "/img/" + finalFileName;
            r.setImgUrl(idPic);
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
