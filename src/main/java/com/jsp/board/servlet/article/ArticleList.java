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
import jakarta.servlet.http.HttpSession;

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

        //로그인, 세션 로직
        HttpSession session = req.getSession();
        boolean isLogined = false;
        int loginedUserId = -1;
        Map<String, Object> loginUserRow = null;
        if (session.getAttribute("loginUserId") != null) {
            loginedUserId = (int) session.getAttribute("loginUserId");
            isLogined = true;

            SecSql sql = new SecSql();
            sql.append("SELECT * FROM user");
            sql.append("WHERE id = ?", loginedUserId);

            loginUserRow = MysqlUtil.selectRow(sql);
        }


        servletResponseDto.setAttribute("isLogined", isLogined);
        servletResponseDto.setAttribute("loginedUserId", loginedUserId);
        servletResponseDto.setAttribute("loginUserRow", loginUserRow);
        // 로그인 로직 종료

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

        MysqlUtil.closeConnection();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

