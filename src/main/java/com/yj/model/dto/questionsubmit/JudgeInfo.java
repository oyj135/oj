package com.yj.model.dto.questionsubmit;

import lombok.Data;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/7
 * 为了更方便的处理 json 字段中的某个字段，需要给对应的 json 字段编写独立的类
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存(kB)
     */
    private Long memory;

    /**
     * 消耗时间(kB)
     */
    private Long time;
}
