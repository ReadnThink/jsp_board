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
import java.util.Map;

@WebServlet("/user/article/modify")
public class ArticleModify extends HttpServlet {
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

        int id = servletResponseDto.getIntParam("id", 0);

        if (id == 0) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('잘못된 요청입니다.'); 
                        history.back(); 
                    </script>
                    """);
            return;
        }

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article ");
        sql.append("WHERE id = ?", id);

        final Map<String, Object> articleRow = MysqlUtil.selectRow(sql);
        servletResponseDto.setAttribute("articleRow", articleRow);

        servletResponseDto.jsp("article/modify");

        MysqlUtil.closeConnection();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

