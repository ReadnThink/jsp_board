package com.jsp.board.controller;

import com.jsp.board.util.ServletResponseDto;
import com.jsp.board.util.db.MysqlUtil;
import com.jsp.board.util.db.SecSql;
import jakarta.servlet.http.HttpSession;

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

    public void getDetail() {

        int id = servletResponseDto.getIntParam("id", 0);

        if (id == 0) {
            servletResponseDto.appendBody("<script>alert('잘못된 요청입니다.'); history.back(); </script>");
            return;
        }

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*)");
        sql.append("FROM article ");
        sql.append("WHERE id = ?", id);

        boolean articleIsExist = MysqlUtil.selectRowBooleanValue(sql);

        if (articleIsExist == false) {
            servletResponseDto.appendBody("<script>alert('해당 게시물은 없는 게시물입니다.'); history.back(); </script>");
            return;
        }

        sql = new SecSql();
        sql.append("SELECT A.*, U.username AS writer");
        sql.append("FROM article AS A");
        sql.append("INNER JOIN `user` AS U");
        sql.append("ON A.userId = U.id");
        sql.append("WHERE A.id = ?", id);
        sql.append("ORDER BY A.id DESC");

        final Map<String, Object> articleRow = MysqlUtil.selectRow(sql);
        servletResponseDto.setAttribute("articleRow", articleRow);

        servletResponseDto.jsp("article/detail");
    }

    public void write() {
        servletResponseDto.jsp("article/write");
    }

    public void doWrite() {
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
        HttpSession session = servletResponseDto.getSession();
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
    }

    public void modify() {
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
    }

    public void doModify() {
        int id = servletResponseDto.getIntParam("id", 0);
        String title = servletResponseDto.getParam("title", "");
        String content = servletResponseDto.getParam("content", "");

        if (title.length() == 0) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('제목을 입력해주세요.');
                        history.back();
                    </script>
                    """);
        }

        if (content.length() == 0) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('내용을 입력해주세요.');
                        history.back();
                    </script>
                    """);
        }

        SecSql sql = new SecSql();
        sql.append("UPDATE article ");
        sql.append("SET updateDate = NOW()");
        sql.append(", title = ?",title);
        sql.append(", content = ?",content);
        sql.append("WHERE id = ?",id);

        MysqlUtil.update(sql);

        servletResponseDto.appendBody("""
                    <script>
                        alert('%d번 게시물이 수정되었습니다.');
                        location.replace('detail?id=%d');
                    </script>
                    """.formatted(id,id));
    }

    public void delete() {
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
        sql.append("SELECT COUNT(*)");
        sql.append("FROM article ");
        sql.append("WHERE id = ?", id);

        boolean articleIsExist = MysqlUtil.selectRowBooleanValue(sql);

        if (articleIsExist == false) {
            servletResponseDto.appendBody("""
                    <script>
                        alert('해당 게시물은 없는 게시물입니다.'); 
                        location.replace('list'); 
                    </script>
                    """);
            return;
        }

        sql = new SecSql();
        sql.append("DELETE ");
        sql.append("FROM article ");
        sql.append("WHERE id = ?", id);

        MysqlUtil.delete(sql);

        servletResponseDto.appendBody("""
                    <script>
                        alert('%d번 게시물이 삭제되었습니다..'); 
                        location.replace('list'); 
                    </script>
                    """.formatted(id));
    }
}
