<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/24/2022
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/marks" method="post">
    <input value="${id}" type="hidden" name="id">
    <input value=4 type="hidden" name="choice">
    Enter course Id:  <input type="text" name="courseId"/>


</form>
statistics of this course: ${statistics}
</body>
</html>
