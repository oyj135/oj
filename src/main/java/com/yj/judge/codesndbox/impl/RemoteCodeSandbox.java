package com.yj.judge.codesndbox.impl;

import com.yj.judge.codesndbox.CodeSandbox;
import com.yj.judge.codesndbox.model.ExecuteCodeRequest;
import com.yj.judge.codesndbox.model.ExecuteCodeResponse;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 * 远程代码沙箱 (实际调用接口的沙箱)
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱执行代码");
        return null;
    }
}
