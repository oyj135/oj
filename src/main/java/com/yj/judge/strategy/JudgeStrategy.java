package com.yj.judge.strategy;

import com.yj.model.dto.questionsubmit.JudgeInfo;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 * 判题策略
 */
public interface JudgeStrategy {
    /**
     * 判题
     *
     * @param judgeContext 判题上下文
     * @return 判题结果
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
