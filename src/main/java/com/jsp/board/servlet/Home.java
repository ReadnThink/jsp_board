package com.jsp.board.servlet;

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

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
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

        servletResponseDto.jsp("home/main");
        MysqlUtil.closeConnection();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
