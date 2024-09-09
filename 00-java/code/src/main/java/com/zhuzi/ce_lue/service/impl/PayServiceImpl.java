package com.zhuzi.ce_lue.service.impl;

import com.zhuzi.ce_lue.service.PayService;
import org.springframework.stereotype.Service;

/**
 * @Description PayServiceImpl
 * @Author zhuzi
 * @Date 2024/09/09
 */
@Service
public class PayServiceImpl implements PayService {

    // 支付方法
    @Override
    public Boolean pay(String payType) {
        if ("1".equals(payType)) {
            // 微信支付
            return weiXinPay();
        } else if ("2".equals(payType)) {
            // 支付宝支付
            return aliPay();
        }
        return false;
    }

    // 微信支付
    private Boolean weiXinPay() {
        return false;
    }

    // 支付宝支付
    private Boolean aliPay() {
        return false;
    }

}
