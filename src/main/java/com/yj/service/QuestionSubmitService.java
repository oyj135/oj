package com.yj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.model.dto.question.QuestionQueryRequest;
import com.yj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yj.model.entity.Question;
import com.yj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.model.entity.User;
import com.yj.model.vo.QuestionSubmitVO;
import com.yj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author OuYJ
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2026-07-06 19:06:28
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交题目
     * @param questionSubmitAddRequest 题目提交请求
     * @param loginUser 登录用户
     * @return 提交结果
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取題目提交封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取題目提交封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmit, User loginUser);
}
