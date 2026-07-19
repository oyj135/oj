package com.yj.judge.codesndbox.impl;

import com.yj.judge.codesndbox.CodeSandbox;
import com.yj.judge.codesndbox.model.ExecuteCodeRequest;
import com.yj.judge.codesndbox.model.ExecuteCodeResponse;
import com.yj.model.dto.questionsubmit.JudgeInfo;
import com.yj.model.enums.JudgeInfoMessageEnum;
import com.yj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 * 示例代码沙箱（跑通业务逻辑使用）
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
