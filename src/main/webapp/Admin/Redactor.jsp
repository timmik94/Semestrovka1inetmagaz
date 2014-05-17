<%@ page import="java.util.ArrayList" %>
<%@ page import="org.javalab.inetmagaz.model.Item" %>

<%--
  Created by IntelliJ IDEA.
  User: тимур
  Date: 21.03.14
  Time: 15:50
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
    ArrayList<Item> items= (ArrayList<Item>) request.getAttribute("items");

    for (int i=0;i<items.size();i++){

        Item item=items.get(i);

        %><p><%=item.toString()%></p><%

    }
%>
<h2>Add new Item </h2>
<form method="post" action="/Admin/add" >
   <p>name: <input name="name" size="30" type="text" VALUE=" "></p>
   <p>coast: <input name="coast" size="5" type="number"></p>
    <p>about: <input name="description" type="text" value=" "></p>
    <button type="submit">add new item</button>
</form>
<h2>Redact existing item</h2>
<form method="post" action="/Admin/redact">
  <p>redact item id:  <input name="id" type="number"></p>
    <p> new name: <input name="name" type="text"></p>
   <p>new coast: <input name="coast" type="number"></p>
   <p>new describtion: <input name="text" type="text"></p>
    <button type="submit">redact</button>
</form>
<h2>Delete item</h2>
<form method="post" action="/Admin/delete">
  <p>del item id:  <input type="number" name="id"/></p>
    <button type="submit">delete</button>
</form>
</body>
</html>
