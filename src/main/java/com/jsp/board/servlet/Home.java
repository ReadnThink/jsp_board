package com.jsp.board.servlet;

import com.jsp.board.util.ServletResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        servletResponseDto.jsp("home/main");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
