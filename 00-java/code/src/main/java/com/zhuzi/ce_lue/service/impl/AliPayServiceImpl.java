package com.zhuzi.ce_lue.service.impl;

import com.zhuzi.ce_lue.service.PayServiceStrategy;

/**
 * @Description AliPayServiceImpl
 * @Author zhuzi
 * @Date 2024/09/09
 */
public class AliPayServiceImpl implements PayServiceStrategy {
    @Override
    public Boolean pay() {
        System.out.println("支付宝支付");
        return false;
    }
}
