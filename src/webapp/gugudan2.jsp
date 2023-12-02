<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jsp.board.util.ServletResponseDto"%>

<%
    ServletResponseDto servletResponseDto = new ServletResponseDto(request, response);
    int dan = (int)servletResponseDto.getAttribute("dan");
    int limit = (int)servletResponseDto.getAttribute("limit");
%>

<h1><%=dan%>단</h1>

<% for(int i = 1; i <= limit; i++) { %>
    <div><%=dan%> * <%=i%> = <%=dan * i%></div>
<% } %>