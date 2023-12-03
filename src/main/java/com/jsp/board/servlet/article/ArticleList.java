package com.jsp.board.servlet.article;

import com.jsp.board.util.ServletResponseDto;
import com.jsp.board.util.db.MysqlUtil;
import com.jsp.board.util.db.SecSql;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/article/list")
public class ArticleList extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtil.setDBInfo("localhost", "root", "1234", "jspboard");
        MysqlUtil.setDevMode(true);

        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);
        int page = servletResponseDto.getIntParam("page", 1);
        int limitTo = 20;
        int limitFrom = (page - 1) * limitTo;

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*)");
        sql.append("FROM article");

        int totalCount = MysqlUtil.selectRowIntValue(sql);
        int totalPage = (int) Math.ceil((double)totalCount / limitTo);

        sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article ");
        sql.append("ORDER BY id DESC");
        sql.append("LIMIT ?, ?", limitFrom, limitTo);

        final List<Map<String, Object>> articleList = MysqlUtil.selectRows(sql);
        servletResponseDto.setAttribute("articleList", articleList);
        servletResponseDto.setAttribute("page", page);
        servletResponseDto.setAttribute("totalPage", totalPage);

        servletResponseDto.jsp("article/list");

        MysqlUtil.closeConnection();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

