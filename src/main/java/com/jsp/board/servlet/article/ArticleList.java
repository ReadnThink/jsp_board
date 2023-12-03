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
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article ");
        sql.append("ORDER BY id DESC");

        final List<Map<String, Object>> articleList = MysqlUtil.selectRows(sql);
        servletResponseDto.setAttribute("articleList", articleList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/article/list.jsp");
        requestDispatcher.forward(req, resp);

        MysqlUtil.closeConnection();
    }
}

