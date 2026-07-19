package com.yj.judge.codesndbox;

import com.yj.judge.codesndbox.impl.ExampleCodeSandbox;
import com.yj.judge.codesndbox.impl.RemoteCodeSandbox;
import com.yj.judge.codesndbox.impl.ThirdPartyCodeSandbox;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/16
 * 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例）
 */
public class CodeSandboxFactory {

    /**
     * 创建代码沙箱实例
     *
     * @param sandboxType 沙箱类型
     * @return
     */
    public static CodeSandbox createCodeSandbox(String sandboxType) {
        switch (sandboxType) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                throw new IllegalArgumentException("Unknown sandbox type: " + sandboxType);
        }
    }
}
