<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PCG Game Union</title>
</head>
<body>
    <%
        //获取所有cookies,返回cookies数组
        Cookie[] cookies = request.getCookies();

        //设置默认为LastTime的cookie不存在
        boolean flag = false;

        //判断cookies数组是否为空或长度为0
        if (cookies != null && cookies.length > 0) {
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String formatDate = sdf.format(date);
            formatDate = URLEncoder.encode(formatDate, "utf-8");

            //遍历cookies数组
            for (Cookie cookie : cookies) {
                //获取每个cookies的名字
                String cookieName = cookie.getName();

                //判断cookies的名字是否为LastTime
                if ("LastTime".equals(cookieName)) {
                    //设置名为LastTime的cookie存在
                    flag = true;

                    //获取上一次保存的时间，并输出到页面上
                    String lastTime = cookie.getValue();

                    //解码并在页面输出一句话
                    lastTime = URLDecoder.decode(lastTime,"utf-8");
                    response.getWriter().write("<h1>欢迎回来，你上次访问的时间为：" + lastTime + "</h1>");

                    //将获取的时间再包装为cookie再打包回去
                    cookie.setValue(formatDate);
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);

                    //找到了名为LastTime的cookie，运行完程序后跳出循环
                    break;
                }
            }
        }

        if (cookies == null || cookies.length == 0 || flag == false) {
            //若cookies数组为空或长度为0,输出一段话
            response.getWriter().write("<h1>欢迎您访问该页面!</h1>");

            //获取系统时间,并编码为URL格式
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String formatDate = sdf.format(date);
            formatDate = URLEncoder.encode(formatDate,"utf-8");

            //创建一个cookie对象，设置cookie的名字和值，并设置存活时间
            Cookie cook = new Cookie("LastTime","" + formatDate);
            cook.setMaxAge(60 * 60 * 24 * 30);

            //将cookie添加到resp对象中
            response.addCookie(cook);
        }
    %>
</body>
</html>
