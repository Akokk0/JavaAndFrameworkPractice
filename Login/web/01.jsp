<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<style>
    div {
        color: red;
    }
</style>
<body>
<form action="/Login/loginServlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkcode"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="img" src="/Login/checkCode"></td>
        </tr>
        <tr>
            <td colspan="2"><input class="btn btn-default" type="submit" value="登录"></td>
        </tr>
    </table>
</form>

<div><%= request.getAttribute("cc_nullerror") == null ? "" : request.getAttribute("cc_nullerror") %></div>
<div><%= request.getAttribute("user_error") == null ? "" : request.getAttribute("user_error") %></div>
<div><%= request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error") %></div>

<script>
    window.onload = function () {
        document.getElementById("img").onclick = function () {
            this.src = "/Login/checkCode?time=" + new Date().getTime();
        }
    }
</script>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery-3.2.1.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
