package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import com.codermy.myspringsecurityplus.car.service.AlipayService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PayController {
    @Autowired
    @Qualifier("alipayService")
    private AlipayService alipayService;

    @RequestMapping("/pay")
    public Result payController(@RequestBody CarRecord c) throws Exception {
        String orderNum = String.valueOf(c.getId());
        String pays = alipayService.webPagePay(orderNum,c.getRent(), c.getCarName());
        return Result.ok().data("<div>aaaa</div>");
    }

}
