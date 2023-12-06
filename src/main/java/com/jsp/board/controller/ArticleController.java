package com.jsp.board.controller;

import com.jsp.board.util.ServletResponseDto;
import com.jsp.board.util.db.MysqlUtil;
import com.jsp.board.util.db.SecSql;

import java.util.List;
import java.util.Map;

public class ArticleController {
    private final ServletResponseDto servletResponseDto;
    public ArticleController(ServletResponseDto servletResponseDto) {
        this.servletResponseDto = servletResponseDto;
    }
    public void getList() {
        int page = servletResponseDto.getIntParam("page", 1);
        int limitTo = 20;
        int limitFrom = (page - 1) * limitTo;

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*)");
        sql.append("FROM article");

        int totalCount = MysqlUtil.selectRowIntValue(sql);
        int totalPage = (int) Math.ceil((double)totalCount / limitTo);

        sql = new SecSql();
        sql.append("SELECT A.*, U.username AS writer");
        sql.append("FROM article AS A");
        sql.append("INNER JOIN `user` AS U");
        sql.append("ON A.userId = U.id");
        sql.append("ORDER BY A.id DESC");
        sql.append("LIMIT ?, ?", limitFrom, limitTo);

        final List<Map<String, Object>> articleList = MysqlUtil.selectRows(sql);
        servletResponseDto.setAttribute("articleList", articleList);
        servletResponseDto.setAttribute("page", page);
        servletResponseDto.setAttribute("totalPage", totalPage);

        servletResponseDto.jsp("article/list");
    }
}
