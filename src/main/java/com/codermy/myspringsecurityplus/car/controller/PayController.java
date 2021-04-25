package com.codermy.myspringsecurityplus.car.controller;

import com.codermy.myspringsecurityplus.car.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PayController {
    @Autowired
    @Qualifier("alipayService")
    private AlipayService alipayService;

    @RequestMapping("/pay")
    public String payController(@RequestParam("order number") String id,@RequestParam("order name") String name,@RequestParam("payment amount") String amount ) throws Exception {
        Integer pay=Integer.valueOf(amount);
        String pays = alipayService.webPagePay(id, pay, name);
        return pays;
    }

}
