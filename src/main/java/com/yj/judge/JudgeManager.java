package com.yj.judge;

import com.yj.judge.strategy.DefaultJudgeStrategy;
import com.yj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yj.judge.strategy.JudgeContext;
import com.yj.judge.strategy.JudgeStrategy;
import com.yj.model.dto.questionsubmit.JudgeInfo;
import com.yj.model.entity.QuestionSubmit;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 * 判题管理（简化调用）
 */
public class JudgeManager {

    /**
     * 判题
     *
     * @param judgeContext 判题上下文
     * @return 判题结果
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
