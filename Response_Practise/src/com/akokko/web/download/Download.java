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

@WebServlet("/download")
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");

        ServletContext context = this.getServletContext();

        String realPath = context.getRealPath("/img/" + filename);

        System.out.println(realPath);

        FileInputStream fis = new FileInputStream(realPath);

        String mimeType = context.getMimeType(filename);
        resp.setHeader("content-type",mimeType);
        resp.setHeader("content-disposition","attachment;filename=" + filename);

        ServletOutputStream sos = resp.getOutputStream();

        byte[] bytes = new byte[1024 * 12];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            sos.write(bytes,0,len);
        }
    }
}
