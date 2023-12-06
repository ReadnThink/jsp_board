package com.jsp.board;

import com.jsp.board.controller.ArticleController;
import com.jsp.board.controller.HomeController;
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

@WebServlet("/user/*")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtil.setDBInfo("localhost", "root", "1234", "jspboard");
        MysqlUtil.setDevMode(true);

        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        addSession(req, servletResponseDto);

        String requestUri = req.getRequestURI();
        String[] requestUriBits = requestUri.split("/");

        int minBitsCount = 3;
        System.out.println(requestUri);
        if (requestUriBits.length < minBitsCount) {
            servletResponseDto.appendBody("올바른 요청이 아닙니다.");
            return;
        }

        int controllerTypeNameIndex = 1;
        int controllerNameIndex = 2;
        int methodIndex = 3;

        String controllerTypeName = requestUriBits[controllerTypeNameIndex];
        String controllerName = requestUriBits[controllerNameIndex];
        String methodName = requestUriBits[methodIndex];
        System.out.println(requestUri);
        if (controllerName.equals("home")) {
            HomeController homeController = new HomeController(servletResponseDto);
            switch (methodName) {
                case "main" -> homeController.getMain();
            }
        }

        if (controllerName.equals("article")) {
            ArticleController articleController = new ArticleController(servletResponseDto);
            switch (methodName) {
                case "list" -> articleController.getList();
                case "detail" -> articleController.getDetail();
                case "write" -> articleController.write();
                case "doWrite" -> articleController.doWrite();
                case "modify" -> articleController.modify();
                case "doModify" -> articleController.doModify();
                case "delete" -> articleController.delete();
            }
        }


        MysqlUtil.closeConnection();
    }

    private static void addSession(final HttpServletRequest req, final ServletResponseDto servletResponseDto) {
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
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
