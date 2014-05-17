<%@ page import="org.javalab.inetmagaz.model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 11.05.2014
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%

    HttpSession sesion=request.getSession();
    ArrayList<Item> items= (ArrayList<Item>) sesion.getAttribute("byuitems");

    for (int i=0;i<items.size();i++){

        Item item=items.get(i);

%><p><form action="/User/delitem" method="post"><%=item.toString()%><input type="hidden"value="<%=item.getId()%>" name="id"/> <button type="submit">del</button></form></p><%

    }
%>
<form action="/User/zakaz" method="get">
    <button type="submit">oformit</button>
</form>
</body>
</html>
