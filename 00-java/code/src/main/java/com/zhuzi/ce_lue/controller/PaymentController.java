package com.zhuzi.ce_lue.controller;

import com.zhuzi.ce_lue.enumeration.PayStrategyEnum;
import com.zhuzi.ce_lue.service.PayService;
import com.zhuzi.ce_lue.service.PayServiceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Description PaymentCOntroller
 * @Author zhuzi
 * @Date 2024/09/09
 */
@RestController
@RequestMapping("/celue")
public class PaymentController {

    @Autowired
    private PayService payService;

    /**
     * 普通流程支付
     *
     * @param payType 支付方式
     * @return
     */
    @GetMapping("/pay1")
    public Boolean pay1(@RequestParam String payType) {
        return payService.pay(payType);
    }

    /**
     * 策略模式支付
     *
     * @param payType 支付方式
     * @return
     */
    @GetMapping("/pay2")
    public Boolean pay2(@RequestParam String payType) {
        // 1. 根据payType选择一个Bean对象执行
        PayServiceStrategy payServiceStrategy = PayStrategyEnum.getByPayType(payType);
        if (Objects.isNull(payServiceStrategy)) {
            return false;
        }
        // 2. 调用不同的策略支付
        return payServiceStrategy.pay();
    }

}
