package com.zhuzi.ce_lue.service.impl;

import com.zhuzi.ce_lue.service.PayServiceStrategy;

/**
 * @Description WeixinPayServiceImpl
 * @Author zhuzi
 * @Date 2024/09/09
 */
public class WeixinPayServiceImpl implements PayServiceStrategy {
    @Override
    public Boolean pay() {
        System.out.println("微信支付");
        return false;
    }
}
