<%@ page import="org.javalab.inetmagaz.model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 11.05.2014
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>userpage</h1>
<%

    HttpSession sesion=request.getSession();
    ArrayList<Item> items= (ArrayList<Item>) request.getAttribute("items");

    for (int i=0;i<items.size();i++){

        Item item=items.get(i);

%><form action="/User/buy" method="post"><%=item.toString()%> <input type="hidden" value="<%=item.getId()%>" name="id"/>  <button type="submit">buy</button></form><%

    }
%>

<form action="/User/corsina.jsp" method="get">
    <button type="submit">to my corsin</button>
</form>
<form action="/User/getzakaz" method="get">
    <button type="submit">my zakazes</button>
</form>
<form action="/exit" method="get">
<button type="submit">exit</button>
</form>
</body>
</html>
