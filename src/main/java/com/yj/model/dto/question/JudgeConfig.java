package com.yj.model.dto.question;

import lombok.Data;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/7
 * 判题配置
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制(ms)
     */
    private Long timeLimit;

    /**
     * 内存限制(kB)
     */
    private Long memoryLimit;

    /**
     * 栈大小限制(kB)
     */
    private Long stackLimit;
}
