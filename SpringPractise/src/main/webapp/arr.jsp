<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Arr</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        var userList = new Array();
        userList.push({name : "zhangsan", age : 18});
        userList.push({name : "lisi", age : 20});

        $.ajax({
            type : "POST",
            url : "quick2",
            data : JSON.stringify(userList),
            contentType : "application/json;charset=utf-8"
        });
    </script>
</head>
<body>

</body>
</html>
