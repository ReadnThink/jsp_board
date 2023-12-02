package com.jsp.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
    private int id;
    private String title;
    private String content;
    private String createDate;
    private String updateDate;
}
