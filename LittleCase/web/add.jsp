<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<style>
    .c_error {
        color: red;
    }
</style>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" method="post" id="form">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
            <span id="c_name" class="c_error"></span>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男" checked="checked"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
            <span id="c_age" class="c_error"></span>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address">
                <option value="广东">广东</option>
                <option value="广西">广西</option>
                <option value="湖南">湖南</option>
                <option value="成都理工大学">成都理工大学</option>
                <option value="中国地质大学（武汉）">中国地质大学（武汉）</option>
                <option value="四川大学">四川大学</option>
                <option value="沈阳医学院">沈阳医学院</option>
                <option value="垃圾学校">垃圾学校</option>
                <option value="四川">四川</option>
                <option value="会理">会理</option>
                <option value="攀枝花">攀枝花</option>

            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" placeholder="请输入QQ号码" id="qq"/>
            <span id="c_qq" class="c_error"></span>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" placeholder="请输入邮箱地址" id="email"/>
            <span id="c_email" class="c_error"></span>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
</div>
</body>
<script>
    window.onload = function () {
        document.getElementById("form").onsubmit = function () {
            return checkName() && checkQq() && checkEmail();
        }

        document.getElementById("name").onblur = checkName;
        document.getElementById("age").onblur = checkAge;
        document.getElementById("qq").onblur = checkQq;
        document.getElementById("email").onblur = checkEmail;
    }

    function checkName() {
        var name = document.getElementById("name").value;

        var reg = /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/;

        var flag = reg.test(name);

        var c_name = document.getElementById("c_name");

        if (flag) {
            c_name.innerHTML = "";
            return flag;
        } else {
            c_name.innerHTML = "姓名输入不正确，请检查后重试";
            return flag;
        }
    }

    function checkAge() {
        var age = document.getElementById("age").value;

        var reg = /^[0-9]{1,3}$/;

        var flag = reg.test(age);

        var c_age = document.getElementById("c_age");

        if (flag) {
            c_age.innerHTML = "";
            return flag;
        } else {
            c_age.innerHTML = "年龄输入不正确，请检查后重试";
            return flag;
        }
    }

    function checkQq() {
        var qq = document.getElementById("qq").value;

        var reg = /^[0-9]{5,11}$/;

        var flag = reg.test(qq);

        var c_qq = document.getElementById("c_qq");

        if (flag) {
            c_qq.innerHTML = "";
            return flag;
        } else {
            c_qq.innerHTML = "QQ输入不正确，请检查后重试";
            return flag;
        }
    }

    function checkEmail() {
        var email = document.getElementById("email").value;

        var reg = /^[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+$/;

        var flag = reg.test(email);

        var c_email = document.getElementById("c_email");

        if (flag) {
            c_email.innerHTML = "";
            return flag;
        } else {
            c_email.innerHTML = "Email输入不正确，请检查后重试";
            return flag;
        }
    }
</script>
</html>