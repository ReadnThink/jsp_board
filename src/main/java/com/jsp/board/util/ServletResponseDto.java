package com.jsp.board.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ServletResponseDto {
    final HttpServletRequest req;
    final HttpServletResponse resp;

    public ServletResponseDto(final HttpServletRequest req, final HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8"); //들어오는 데이터를 UTF-8로 해석하겠다.
            resp.setCharacterEncoding("UTF-8"); // 반환하는 HTML의 인코딩의 UTF-8로 하겠다.
            resp.setContentType("text/html; charset-utf-8"); // 브라우저에게 우리가 만든 결과물이 UTF-8이라고 알려줌
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    public int getIntParam(String key, int defaultValue) {
        String value = req.getParameter(key);

        if (value == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }

    public String getParam(String key, String defaultValue) {
        final String value = req.getParameter(key);

        if (value == null) {
            return defaultValue;
        }

        return value;
    }

    public void appendBody(String str) {
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getAttribute(String name) {
        return req.getAttribute(name);
    }

    public void setAttribute(String name, Object value) {
        req.setAttribute(name, value);
    }

    public void jsp(final String jspPath) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/" + jspPath + ".jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
