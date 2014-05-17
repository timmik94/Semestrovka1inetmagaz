<%@ page import="org.javalab.inetmagaz.model.Zakaz" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 12.05.2014
  Time: 15:01
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
List<Zakaz> zakazList= (List<Zakaz>) sesion.getAttribute("zakaz");

for (int i=0;i<zakazList.size();i++){
    Zakaz zakaz=zakazList.get(i);
    %><form method="post" action="/User/zakaz"><%=zakaz.toString()%><input type="hidden" value="<%=zakaz.getId()%>" name="id"/><button type="submit">cancel</button></form><%
}
%>
<form action="/data">
    <button type="submit">to item list</button>
</form>
</body>
</html>
