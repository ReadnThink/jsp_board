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
    <title>게시물 수정</title>
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
        <h1>글 수정</h1>
        <form action="doModify" method="POST">
            <input type="hidden" name="id" value="${param.id}">
            <div>제목 : <input autocomplete="off" name="title" placeholder="제목을 입력해 주세요" type="text" value="<%=(String)articleRow.get("title")%>"></div>
            <div>내용 : <textarea autocomplete="off" name="content" placeholder="내용을 입력해 주세요" type="text"><%=(String)articleRow.get("content")%></textarea></div>
            <div class="btn-group">
                <button type="submit">수정</button>
                <br>
                <a href="detail?id=${param.id}">취소</a>
                <a href="list">리스트</a>
            </div>
        </form>
    </div>
</section>

</body>
</html>