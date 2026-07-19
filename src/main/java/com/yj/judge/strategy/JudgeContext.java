package com.yj.judge.strategy;

import com.yj.model.dto.question.JudgeCase;
import com.yj.model.dto.questionsubmit.JudgeInfo;
import com.yj.model.entity.Question;
import com.yj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 * 上下文（用于定义在策略中传递的参数）
 */

@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
