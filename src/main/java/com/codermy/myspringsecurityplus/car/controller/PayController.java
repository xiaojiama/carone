package com.codermy.myspringsecurityplus.car.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.codermy.myspringsecurityplus.car.config.AlipayConfig;
import com.codermy.myspringsecurityplus.car.entity.CarRecord;
import com.codermy.myspringsecurityplus.car.repository.CarRecordRepository;
import com.codermy.myspringsecurityplus.car.service.AlipayService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
public class PayController {
    @Autowired
    @Qualifier("alipayService")
    private AlipayService alipayService;
    @Resource
    private CarRecordRepository crRepository;


    @RequestMapping("/pay")
    public String payController(@RequestParam("order_number") String id,
                                @RequestParam("order_name") String name,
                                @RequestParam("payment_amount") String amount ) throws Exception {
        Integer pay=Integer.valueOf(amount);
        String pays = alipayService.webPagePay(id, pay, name);
        return pays;
    }
    @RequestMapping(value = "/alipay/alipayReturnNotice")

    public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {


        System.out.println("支付成功, 进入同步通知接口...");
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //璋冪敤SDK楠岃瘉绛惧悕

        ModelAndView mv = new ModelAndView("../static/index.html");

        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            // 修改叮当状态，改为 支付成功，已付款; 同时修改汽车状态
            crRepository.updateStatusById("已支付", out_trade_no);
            crRepository.updateCarStatusByCarRecordId("已出租",out_trade_no);


            //Orders order = orderService.getOrderById(out_trade_no);
            //Product product = productService.getProductById(order.getProductId());
            //
            // log.info("********************** 支付成功(支付宝同步通知) **********************");
            // log.info("* 订单号: {}", out_trade_no);
            // log.info("* 支付宝交易号: {}", trade_no);
            // log.info("* 实付金额: {}", total_amount);
            // log.info("* 购买产品: {}", product.getName());
            // log.info("***************************************************************");
            mv.addObject("out_trade_no", out_trade_no);
            mv.addObject("trade_no", trade_no);
            mv.addObject("total_amount", total_amount);
            mv.addObject("flag", "success");

        }else {
            System.out.println("未知错误");
        }

        return mv;
    }

}
