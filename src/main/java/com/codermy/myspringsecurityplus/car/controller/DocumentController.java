package com.codermy.myspringsecurityplus.car.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codermy.myspringsecurityplus.car.entity.Document;
import com.codermy.myspringsecurityplus.car.repository.DocumentRepository;
import com.codermy.myspringsecurityplus.car.utils.FileUtils;
import com.codermy.myspringsecurityplus.car.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/document")
public class DocumentController {
    @Resource
    private DocumentRepository documentRepository;

    //上传文件
    @PostMapping("/upload")
    @ResponseBody
    public JSON upload(@RequestParam("file") MultipartFile file, Long carId){
        Assert.notNull(file, "上传文件不能为空");
        // 拿到文件名
        String filename = System.currentTimeMillis()+file.getOriginalFilename();

        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());
        JSONObject json = new JSONObject();
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator +filename);
            String savepath = newFile.getAbsolutePath();
            System.out.println(savepath);
            //将上传的文件信息写入数据库
            Document documentFile=new Document();
            documentFile.setCarId(carId);
            documentFile.setName(file.getOriginalFilename());
            documentFile.setPath(savepath);
            documentFile.setTime(new Timestamp(new Date().getTime()));
            documentRepository.save(documentFile);
            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);

            json.put("msg","success");
            json.put("code",0);
            json.put("data",filename);

        } catch (Exception e) {
            json.put("msg","error");
            json.put("code",500);
            e.printStackTrace();
        }
       return json;
    }
    @RequestMapping(value="/down", produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String down(HttpServletResponse response, Integer id){
        try {
            //根据文件id查询文件路径
            Optional<Document> document = documentRepository.findById(id);
            String filePath = null;
            if(document.isPresent()){
                filePath=document.get().getPath();
            }

            //根据文件路径下载文件信息
            FileUtils.down(response, filePath);
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            return "下载成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "下载失败";
    }
    
}
