package com.zhuzi.ze_ren_lian;

import org.springframework.stereotype.Service;

/**
 * @Description ArticleService
 * @Author zhuzi
 * @Date 2024/09/13
 */
@Service
public class ArticleService {

    // 新增文章
    public Boolean addArticle(String articleContent) {
        /* // 检查文章是否包含黄色词语
        if (!filterYellowKeyword(articleContent)) {
            return false;
        }

        // 检查文章是否包含政治敏感词
        if (!filterPoliticsKeyword(articleContent)) {
            return false;
        }

        // 检查文章是否包含宗教敏感词
        if (!filterReligionKeyword(articleContent)) {
            return false;
        } */

        boolean sensitiveWordResult = SensitiveWordFilterChain.filterSensitiveWord(articleContent);
        if (!sensitiveWordResult) {
            return false;
        }

        // 其它操作...
        return true;
    }

    private Boolean filterYellowKeyword(String articleContent) {
        return true;
    }

    private Boolean filterPoliticsKeyword(String articleContent) {
        return true;
    }

    private Boolean filterReligionKeyword(String articleContent) {
        return true;
    }

}
