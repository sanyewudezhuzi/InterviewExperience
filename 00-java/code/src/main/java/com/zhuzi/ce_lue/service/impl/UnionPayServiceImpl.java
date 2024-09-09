package com.zhuzi.ce_lue.service.impl;

import com.zhuzi.ce_lue.service.PayServiceStrategy;

/**
 * @Description UnionPayServiceImpl
 * @Author zhuzi
 * @Date 2024/09/09
 */
public class UnionPayServiceImpl implements PayServiceStrategy {
    @Override
    public Boolean pay() {
        System.out.println("银联支付");
        return false;
    }
}
