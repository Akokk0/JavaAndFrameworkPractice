package com.akokko.web.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取从页面传来的filename参数
        String filename = req.getParameter("filename");

        //通过本类方法获取ServletContext对象，也可以使用req.getServletContext
        //ServletContext servletContext = req.getServletContext();
        ServletContext context = this.getServletContext();

        //用ServletContext对象中的getRealPath方法获取图片的真实路径
        String path = context.getRealPath("/img/" + filename);
        //System.out.println(path);

        //创建字节输入流，传入获取的真实路径
        FileInputStream fis = new FileInputStream(path);

        //用ServletContext对象中的getMimeType方法获取图片的Mime类型
        String mimeType = context.getMimeType("filename");
        //设置响应头content-type类型为图片的Mime类型，设置响应头content-disposition参数为下载附件类型
        resp.setHeader("content-type",mimeType);
        resp.setHeader("content-disposition","attachment;filename=" + filename);

        //将图片用Servlet输出流写到页面上
        ServletOutputStream sos = resp.getOutputStream();
        byte[] bytes = new byte[1024 * 12];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            sos.write(bytes,0,len);
        }
    }
}
