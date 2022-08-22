<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
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
          <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
      </table>
    </form>

    <div><%= request.getAttribute("cc_nullerror") == null ? "" : request.getAttribute("cc_nullerror") %></div>
    <div><%= request.getAttribute("user_error") == null ? "" : request.getAttribute("user_error") %></div>
    <div><%= request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error") %></div>

  </body>

<script>
  window.onload = function () {
    document.getElementById("img").onclick = function () {
      this.src = "/Login/checkCode?time=" + new Date().getTime();
    }
  }
</script>
</html>
