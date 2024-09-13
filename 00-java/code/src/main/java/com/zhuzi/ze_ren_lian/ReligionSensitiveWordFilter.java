package com.zhuzi.ze_ren_lian;

/**
 * @Description ReligionSensitiveWordFilter
 * @Author zhuzi
 * @Date 2024/09/13
 */
public class ReligionSensitiveWordFilter implements SensitiveWordFilter {
    @Override
    public Boolean wordFilter(String content) {
        return true;
    }
}
