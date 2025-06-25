<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/23/2022
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<p><font color="red">${errorMessage}</font> </p>
<form action="/login" method="post">
    Id:  <input type="text" name="id"/> Password: <input type="text" name="password"/><input type="submit" value="login"/>
</form>
</body>
</html>
