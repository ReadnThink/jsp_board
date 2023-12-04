package com.jsp.board.servlet.article;

import com.jsp.board.util.ServletResponseDto;
import com.jsp.board.util.db.MysqlUtil;
import com.jsp.board.util.db.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/doJoin")
public class ArticleDoJoin extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtil.setDBInfo("localhost", "root", "1234", "jspboard");
        MysqlUtil.setDevMode(true);

        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        String loginId = servletResponseDto.getParam("loginId", "");
        String loginPw = servletResponseDto.getParam("loginPw", "");
        String username = servletResponseDto.getParam("username", "");
        String email = servletResponseDto.getParam("email", "");

        SecSql sql = new SecSql();
        sql.append("INSERT INTO user ");
        sql.append("SET createDate = NOW()");
        sql.append(", updateDate = NOW()");
        sql.append(", loginId = ?",loginId);
        sql.append(", loginPw = ?",loginPw);
        sql.append(", username = ?",username);
        sql.append(", email = ?",email);

        int id = MysqlUtil.insert(sql);
        System.out.println(id);
        servletResponseDto.appendBody("""
                    <script>
                        alert('회원가입이 완료되었습니다.');
                        location.replace('../home');
                    </script>
                    """.formatted(id,id));

        MysqlUtil.closeConnection();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

