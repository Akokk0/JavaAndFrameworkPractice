<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginSuccess</title>
</head>
<body>
  <h1>欢迎您，<%= request.getSession().getAttribute("username") %> ！</h1>
</body>
</html>
