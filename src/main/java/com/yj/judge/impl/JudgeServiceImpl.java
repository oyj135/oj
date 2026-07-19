package com.yj.judge.impl;

import cn.hutool.json.JSONUtil;
import com.yj.common.ErrorCode;
import com.yj.exception.BusinessException;
import com.yj.judge.JudgeService;
import com.yj.judge.codesndbox.CodeSandbox;
import com.yj.judge.codesndbox.CodeSandboxFactory;
import com.yj.judge.codesndbox.CodeSandboxProxy;
import com.yj.judge.codesndbox.model.ExecuteCodeRequest;
import com.yj.judge.codesndbox.model.ExecuteCodeResponse;
import com.yj.judge.strategy.DefaultJudgeStrategy;
import com.yj.judge.strategy.JudgeContext;
import com.yj.judge.strategy.JudgeStrategy;
import com.yj.model.dto.question.JudgeCase;
import com.yj.model.dto.questionsubmit.JudgeInfo;
import com.yj.model.entity.Question;
import com.yj.model.entity.QuestionSubmit;
import com.yj.model.enums.QuestionSubmitLanguageEnum;
import com.yj.model.enums.QuestionSubmitStatusEnum;
import com.yj.service.QuestionService;
import com.yj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 */
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Value("${codesandbox.type}")
    private String type;

    /**
     * 判题服务实现
     *
     * @param questionSubmitId 题目id
     * @return
     */
    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        // 1. 传入题目的提交id，获取到对应的题目，提交信息（包含代码，编程语言)
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        // 2.如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        // 3. 更改判题的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新判题状态失败");
        }

        // 4. 调用沙箱，获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.createCodeSandbox(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest codeRequestBuilder = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(codeRequestBuilder);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 5. 根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);

        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        JudgeInfo judgeInfo = judgeStrategy.doJudge(judgeContext);
        // 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新判题状态失败");
        }
        return questionSubmitService.getById(questionId);
    }
}
