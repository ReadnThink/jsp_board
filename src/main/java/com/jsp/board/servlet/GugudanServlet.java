package com.jsp.board.servlet;

import com.jsp.board.util.ServletResponseDto;
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

        servletResponseDto.appendBody("<h1>%d단</h1>\n".formatted(dan));
        for (int i = 1; i <= limit; i++) {
            servletResponseDto.appendBody(("<div>%d * %d = %d <div>\n".formatted(dan, i, dan * i)));
        }
    }
}
