package com.zhuzi.ze_ren_lian;

/**
 * @Description PoliticsSensitiveWordFilter
 * @Author zhuzi
 * @Date 2024/09/13
 */
public class PoliticsSensitiveWordFilter implements SensitiveWordFilter {
    @Override
    public Boolean wordFilter(String content) {
        return true;
    }
}
