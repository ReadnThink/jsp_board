<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.jsp.board.util.ServletResponseDto"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%
    ServletResponseDto servletResponseDto = new ServletResponseDto(request, response);
    List<Map<String, Object>> articleList = (List<Map<String, Object>>)servletResponseDto.getAttribute("articleList");
%>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>게시물 리스트</title>
</head>
<body>
<style>
    body, ul, li {
        margin: 0;
    }

    .section {
        display: flex;
        justify-content: center;
    }
</style>

<section class="section">
    <div class="con">
        <h1>게시물 리스트</h1>

        <table border="1" style="border-collapse: collapse; text-align:center;">
            <colgroup>
                <col width="50">
                <col width="200">
                <col width="200">
                <col width="200">
            </colgroup>
            <thead>
            <tr>
                <th>번호</th>
                <th>작성날짜</th>
                <th>수정날짜</th>
                <th>제목</th>
            </tr>
            </thead>
            <tbody>
            <% for(Map<String, Object> articleRow : articleList) {
            %>
            <tr>
                <td><%= articleRow.get("id") %></td>
                <td><%= articleRow.get("createDate") %></td>
                <td><%= articleRow.get("updateDate") %></td>
                <td>
                    <a href="detail?id=<%= articleRow.get("id") %> "><%= articleRow.get("title") %></a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <div class="btn-group">
            <a href="write">게시물 작성</a>
        </div>
    </div>
</section>

</body>
</html>