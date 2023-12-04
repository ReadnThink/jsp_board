package com.jsp.board.servlet.article;

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

@WebServlet("/article/doWrite")
public class ArticleDoWrite extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtil.setDBInfo("localhost", "root", "1234", "jspboard");
        MysqlUtil.setDevMode(true);

        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        String title = servletResponseDto.getParam("title", "");
        String content = servletResponseDto.getParam("content", "");

        if (title.length() == 0) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('제목을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }

        if (content.length() == 0) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('내용을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }
        HttpSession session = req.getSession();
        if (session.getAttribute("loginUserId") == null) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('로그인 후 이용해 주세요');
                        location.replace('../user/login')
                    </script>
                    """);
            return;
        }

        int loginUserId = (int) session.getAttribute("loginUserId");

        SecSql sql = new SecSql();
        sql.append("INSERT INTO article ");
        sql.append("SET createDate = NOW()");
        sql.append(", updateDate = NOW()");
        sql.append(", title = ?",title);
        sql.append(", content = ?",content);
        sql.append(", userId = ?", loginUserId);

        int id = MysqlUtil.insert(sql);
        System.out.println(id);
        servletResponseDto.appendBody("""
                    <script>
                        alert('%d번 게시물이 생성되었습니다.');
                        location.replace('detail?id=%d');
                    </script>
                    """.formatted(id,id));

        MysqlUtil.closeConnection();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

