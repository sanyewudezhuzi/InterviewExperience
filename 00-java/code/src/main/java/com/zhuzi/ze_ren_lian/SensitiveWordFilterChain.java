package com.zhuzi.ze_ren_lian;

import com.zhuzi.utils.SpringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description SensitiveWordFilterChain
 * @Author zhuzi
 * @Date 2024/09/13
 */
@Component
public class SensitiveWordFilterChain {
    private static final List<SensitiveWordFilter> SENSITIVE_WORD_FILTERS = new ArrayList<>();

    @PostConstruct
    public void init() {
        // 检查黄色词语
        SENSITIVE_WORD_FILTERS.add(SpringUtils.getBeanByClass(YellowSensitiveWordFilter.class));
        // 检查政治敏感词
        SENSITIVE_WORD_FILTERS.add(SpringUtils.getBeanByClass(PoliticsSensitiveWordFilter.class));
        // 检查宗教敏感词
        SENSITIVE_WORD_FILTERS.add(SpringUtils.getBeanByClass(ReligionSensitiveWordFilter.class));
    }

    // 过滤敏感词
    public static boolean filterSensitiveWord(String content) {
        for (SensitiveWordFilter wordFilter : SENSITIVE_WORD_FILTERS) {
            if (!wordFilter.wordFilter(content)) {
                return false;
            }
        }
        return true;
    }
}
