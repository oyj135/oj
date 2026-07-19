package com.yj.judge.codesndbox;

import com.yj.judge.codesndbox.model.ExecuteCodeRequest;
import com.yj.judge.codesndbox.model.ExecuteCodeResponse;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest 请求
     * @return 响应结果
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
