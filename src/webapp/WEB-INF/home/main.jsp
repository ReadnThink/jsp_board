<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.jsp.board.util.ServletResponseDto"%>

<%
ServletResponseDto servletResponseDto = new ServletResponseDto(request, response);
%>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>메인 페이지</title>
</head>
<body>
    <style>
        body, ul, li {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .section {
            display: flex;
            justify-content: center;
        }
    </style>
    <h1>메인 페이지</h1>
    <nav class="menu-box-1">
        <ul style="display: flex; gap: 0 10px">
            <li><a href="/article/list">게시물 리스트</a></li>
            <li><a href="/user/join">회원가입</a></li>
            <li><a href="/user/login">로그인</a></li>
        </ul>
    </nav>
</body>
</html>