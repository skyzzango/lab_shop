package net.shop.controller;

import net.shop.error.BoardNotFoundException;
import net.shop.service.CommentService;
import net.shop.service.UserService;
import net.shop.util.Util;
import net.shop.vo.CommentVO;
import net.shop.vo.PagingVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Description
 * Donghyun Seo (egaoneko@naver.com)
 * 2015-03-25
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version
 */

@Controller
public class CommentController {

    @Resource(name = "commentService")
    private CommentService commentService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "util")
    private Util util;

    /*
    댓글 리스트
     */
    @RequestMapping(value = "/comment/listAll.do")
    public ModelAndView commentListAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        /*
        웹 브라우저가 게시글 목록을 캐싱하지 않도록 캐시 관련 헤더를 설정
         */
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.setDateHeader("Expire", 1L);

        String boardNumberString = request.getParameter("boardNumber");

        if(boardNumberString == null || boardNumberString.equals("")) {
            throw new BoardNotFoundException("게시글이 존재하지 않음 : " + boardNumberString);
        }

        int boardNumber = Integer.parseInt(boardNumberString);

        if(boardNumber < 0){
            throw new IllegalArgumentException("board number < 1 : " + boardNumber);
        }

        int totalCount = commentService.selectCount(boardNumber);

        modelAndView.setViewName("/comment/listAll");

        if(totalCount == 0){
            modelAndView.addObject("commentVOList", Collections.<CommentVO>emptyList());
            request.setAttribute("hasComment", new Boolean(false));
            return modelAndView;
        }

        List<CommentVO> commentVOList = commentService.selectList(boardNumber);
        request.setAttribute("commentVOList", commentVOList);
        request.setAttribute("hasComment", new Boolean(true));

        return modelAndView;
    }

    /*
    댓글 리스트
     */
    @RequestMapping(value = "/comment/list.do")
    public ModelAndView commentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        /*
        웹 브라우저가 게시글 목록을 캐싱하지 않도록 캐시 관련 헤더를 설정
         */
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.setDateHeader("Expire", 1L);

        String requestPageString = request.getParameter("cp");

        if(requestPageString == null || requestPageString.equals("")) {
            requestPageString = "1";
        }

        int requestPage = Integer.parseInt(requestPageString);

        if(requestPage <= 0){
            throw new IllegalArgumentException("requestPage <= 0 : " + requestPage);
        }

        String boardNumberString = request.getParameter("boardNumber");

        if(boardNumberString == null || boardNumberString.equals("")) {
            throw new BoardNotFoundException("게시글이 존재하지 않음 : " + boardNumberString);
        }

        int boardNumber = Integer.parseInt(boardNumberString);

        if(boardNumber < 0){
            throw new IllegalArgumentException("board number < 1 : " + boardNumber);
        }

        int totalCount = commentService.selectCount(boardNumber);

        /*Paging 메소드의 사용 */
        PagingVO pagingVO = util.paging(requestPage, 10, totalCount);
        modelAndView.addObject("pagingVO", pagingVO);
        modelAndView.setViewName("/comment/listAll");

        if(totalCount == 0){
            modelAndView.addObject("commentVOList", Collections.<CommentVO>emptyList());
            request.setAttribute("hasComment", new Boolean(false));
            return modelAndView;
        }

        List<CommentVO> commentVOList = commentService.selectList(boardNumber, pagingVO.getFirstRow(), pagingVO.getEndRow());
        request.setAttribute("commentVOList", commentVOList);
        request.setAttribute("hasComment", new Boolean(true));

        return modelAndView;
    }

}
