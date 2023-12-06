package com.jsp.board.controller;

import com.jsp.board.util.ServletResponseDto;

public class HomeController {
    ServletResponseDto servletResponseDto;
    public HomeController(final ServletResponseDto servletResponseDto) {
        this.servletResponseDto = servletResponseDto;
    }

    public void getMain() {
        servletResponseDto.jsp("home/main");
    }
}
