<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 11.05.2014
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>registration</h1>

<%String m= (String) request.getAttribute("message");
        if(m!=null){%><h2><%=m%></h2><%}%>
<form action="registry" method="post">
    <p>login:    <input name="login" size="25" value="" type="text"/> </p>
    <p>password: <input name="password" size="25" value="" type="password"/> </p>
    <button type="submit">registration</button>
</form>
<form action="index.jsp" method="get">
    <button type="submit">try again</button>
</form>
</body>
</html>
