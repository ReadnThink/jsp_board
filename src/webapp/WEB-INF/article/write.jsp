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
    <title>게시물 작성</title>
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

<section class="write_section">
    <div class="con">
        <h1>글 작성</h1>
        <%@include file="/parts/navbar.jspf"%>
        <form action="doWrite" method="POST">
            <div>제목 : <input autocomplete="off" name="title" placeholder="제목을 입력해 주세요" type="text"></div>
            <div>내용 : <textarea autocomplete="off" name="content" placeholder="내용을 입력해 주세요" type="text"></textarea></div>
            <div class="btn-group">
                <button type="submit">글 작성</button>
                <br>
                <a href="list">취소</a>
            </div>
        </form>
    </div>
</section>

</body>
</html>