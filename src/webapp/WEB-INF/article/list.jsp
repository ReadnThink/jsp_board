<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.jsp.board.util.ServletResponseDto"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%
    ServletResponseDto servletResponseDto = new ServletResponseDto(request, response);
    List<Map<String, Object>> articleList = (List<Map<String, Object>>)servletResponseDto.getAttribute("articleList");
    int curPage = (int)servletResponseDto.getAttribute("page");
    int totalPage = (int)servletResponseDto.getAttribute("totalPage");
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
<style >
    body, ul, li {
        margin: 0;
    }

    .section {
        display: flex;
        justify-content: center;
    }

    .page > a.red {
        coler: red;
    }
</style>

<section class="section">
    <div class="con">
        <h1>게시물 리스트</h1>
        <%@include file="/parts/navbar.jspf"%>
        <table border="1" style="border-collapse: collapse; text-align:center;">
            <colgroup>
                <col width="50">
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
                <th>작성자</th>
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
                <td><%= articleRow.get("writer") %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <div class="btn-group">
            <a href="write">게시물 작성</a>
        </div>
        <div class="page" style="display:flex; justify-content: center; gap: 0 10px;">
            <% if(curPage > 1){%>
                <a href="list?page=1">첫페이지</a>
            <% }%>

            <%
            int pageMenuSize = 5;
            int from = curPage - pageMenuSize;

            if (from < 1){
            from = 1;
            }

            int end = curPage + pageMenuSize;
            if (end > totalPage){
                end = totalPage;
            }

            for (int i = from; i <= end; i++) {%>
                <a class="<%= curPage == i ? "red" : "" %>" href="list?page=<%=i%>"><%=i%></a>
            <% } %>
            <% if(curPage < totalPage){%>
                <a href="list?page=<%=totalPage%>">마지막페이지</a>
            <% }%>
        </div>
    </div>
</section>

</body>
</html>