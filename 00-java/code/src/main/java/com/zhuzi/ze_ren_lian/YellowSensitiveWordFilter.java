package com.zhuzi.ze_ren_lian;

/**
 * @Description YellowSensitiveWordFilter
 * @Author zhuzi
 * @Date 2024/09/13
 */
public class YellowSensitiveWordFilter implements SensitiveWordFilter {
    @Override
    public Boolean wordFilter(String content) {
        return true;
    }
}
