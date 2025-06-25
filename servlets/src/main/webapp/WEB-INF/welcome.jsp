<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/23/2022
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>choose your choice:</h2><br>
<h3>1.show your grades in All courses</h3><br>
<h3>2.show your grade in specific course and get the statistics of this course</h3><br>
<h3>3.Exit</h3><br>
<form action="/marks" method="post">
<input value="${id}" type="hidden" name="id">
<input type="text" name="choice"/>
</form>
${marks}
</body>
</html>
