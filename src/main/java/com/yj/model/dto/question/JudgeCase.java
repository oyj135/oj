package com.yj.model.dto.question;

import lombok.Data;

/**
 * @author <a href="https://www.ouyangjian.com/">YJ.渔夫.星辰</a>
 * @Date 2026/7/7
 * 为了更方便的处理 json 字段中的某个字段，需要给对应的 json 字段编写独立的类
 * 题目用例
 */
@Data
public class JudgeCase {

        /**
         * 输入用例
         */
        private String input;

        /**
         * 输出用例
         */
        private String output;
}
