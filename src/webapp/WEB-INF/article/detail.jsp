<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.jsp.board.util.ServletResponseDto"%>
<%@ page import="java.util.Map"%>

<%
    ServletResponseDto servletResponseDto = new ServletResponseDto(request, response);
    Map<String, Object> articleRow = (Map<String, Object>)servletResponseDto.getAttribute("articleRow");
%>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>게시물 상세보기</title>
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
        <h1>게시물 상세보기</h1>
        <%@include file="/parts/navbar.jspf"%>
        <table border="1" style="border-collapse: collapse; text-align:center;">
            <colgroup>
                <col width="50">
                <col width="200">
                <col width="200">
                <col width="200">
                <col width="200">
                <col width="150">
            </colgroup>
            <thead>
            <tr>
                <th>번호</th>
                <th>작성날짜</th>
                <th>수정날짜</th>
                <th>제목</th>
                <th>내용</th>
                <th>작성자</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= articleRow.get("id") %></td>
                    <td><%= articleRow.get("createDate") %></td>
                    <td><%= articleRow.get("updateDate") %></td>
                    <td><%= articleRow.get("title") %></td>
                    <td><%= articleRow.get("content") %></td>
                    <td><%= articleRow.get("writer") %></td>
                </tr>
            </tbody>
        </table>
        <div class="btn-group">
            <a href="list">리스트</a>
            &nbsp;
            <a href="delete?id=${param.id}">삭제</a>
            &nbsp;
            <a href="modify?id=${param.id}">수정</a>
        </div>
    </div>
</section>

</body>
</html>