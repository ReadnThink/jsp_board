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

@WebServlet("/user/doLogin")
public class UserDoLogin extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtil.setDBInfo("localhost", "root", "1234", "jspboard");
        MysqlUtil.setDevMode(true);

        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        String loginId = servletResponseDto.getParam("loginId", "");
        String loginPw = servletResponseDto.getParam("loginPw", "");

        SecSql sql = new SecSql();
        sql.append("SELECT * ");
        sql.append("FROM USER");
        sql.append("WHERE loginId = ?", loginId);

        Map<String, Object> userRow = MysqlUtil.selectRow(sql);
        if (userRow.isEmpty()) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('아이디가 일치하지 않습니다.');
                        history.back();
                    </script>
                    """);
        }

        if (((String) userRow.get("loginPw")).equals(loginPw) == false) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('패스워드가 일치하지 않습니다.');
                        history.back();
                    </script>
                    """);
        }

        HttpSession session = req.getSession();
        session.setAttribute("loginUserId", userRow.get("id"));
        session.setAttribute("loginUserName", userRow.get("username"));
        servletResponseDto.appendBody("""
                    <script>
                        alert('로그인 되었습니다.');
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

