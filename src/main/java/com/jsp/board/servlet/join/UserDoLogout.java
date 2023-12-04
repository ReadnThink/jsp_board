package com.jsp.board.servlet.join;

import com.jsp.board.util.ServletResponseDto;
import com.jsp.board.util.db.MysqlUtil;
import com.jsp.board.util.db.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/user/doLogout")
public class UserDoLogout extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtil.setDBInfo("localhost", "root", "1234", "jspboard");
        MysqlUtil.setDevMode(true);

        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        HttpSession session = req.getSession();
        session.removeAttribute("loginUserId");
        session.removeAttribute("loginUserName");
        servletResponseDto.appendBody("""
                    <script>
                        alert('로그아웃 되었습니다.');
                        location.replace('../home');
                    </script>
                    """);

        MysqlUtil.closeConnection();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

