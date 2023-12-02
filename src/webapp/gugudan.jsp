<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jsp.board.util.ServletResponseDto"%>

<%
    int dan = Integer.parseInt(request.getParameter("dan"));
    int limit = Integer.parseInt(request.getParameter("limit"));
%>

<h1><%=dan%>단</h1>

<% for(int i = 1; i <= limit; i++) { %>
    <div><%=dan%> * <%=i%> = <%=dan * i%></div>
<% } %>