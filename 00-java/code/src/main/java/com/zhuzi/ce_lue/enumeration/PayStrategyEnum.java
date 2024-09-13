package com.zhuzi.ce_lue.enumeration;

import com.zhuzi.ce_lue.service.PayServiceStrategy;
import com.zhuzi.ce_lue.service.impl.AliPayServiceImpl;
import com.zhuzi.ce_lue.service.impl.UnionPayServiceImpl;
import com.zhuzi.ce_lue.service.impl.WeixinPayServiceImpl;
import com.zhuzi.utils.SpringUtils;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description PayStrategyEnum
 * @Author zhuzi
 * @Date 2024/09/09
 */
@Getter
public enum PayStrategyEnum {
    // 微信支付
    WEI_XIN_PAY("1", SpringUtils.getBeanByClass(WeixinPayServiceImpl.class)),
    // 支付宝支付
    ALI_PAY("2", SpringUtils.getBeanByClass(AliPayServiceImpl.class)),
    // 银联支付
    UNION_PAY("3", SpringUtils.getBeanByClass(UnionPayServiceImpl.class));

    private String payType;
    private PayServiceStrategy payServiceStrategy;

    PayStrategyEnum(String payType, PayServiceStrategy payServiceStrategy) {
        this.payType = payType;
        this.payServiceStrategy = payServiceStrategy;
    }

    public static PayServiceStrategy getByPayType(String payType) {
        if (StringUtils.isBlank(payType)) {
            return null;
        }
        for (PayStrategyEnum payStrategyEnum : PayStrategyEnum.values()) {
            if (payStrategyEnum.payType.equals(payType)) {
                return payStrategyEnum.getPayServiceStrategy();
            }
        }
        return null;
    }
}
