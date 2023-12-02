package com.jsp.board.servlet;

import com.jsp.board.util.ServletResponseDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        int dan = servletResponseDto.getIntParam(("dan"), 9);
        int limit = servletResponseDto.getIntParam(("limit"), 9);

        servletResponseDto.setAttribute("dan", dan);
        servletResponseDto.setAttribute("limit", limit);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/gugudan2.jsp");
        requestDispatcher.forward(req, resp);
    }
}
