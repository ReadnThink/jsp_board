package com.jsp.board.servlet.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //들어오는 데이터를 UTF-8로 해석하겠다.
        resp.setCharacterEncoding("UTF-8"); // 반환하는 HTML의 인코딩의 UTF-8로 하겠다.
        resp.setContentType("text/html; charset-utf-8"); // 브라우저에게 우리가 만든 결과물이 UTF-8이라고 알려줌
        resp.getWriter().append("안녕!");
    }
}
