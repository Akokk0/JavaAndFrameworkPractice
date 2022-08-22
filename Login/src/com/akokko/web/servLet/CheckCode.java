package com.akokko.web.servLet;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = img.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0,0,width,height);

        g.setColor(Color.BLUE);
        g.drawRect(0,0,width - 1,height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = r.nextInt(str.length());
            char c = str.charAt(index);
            sb.append(c);
            g.drawString(c+"",width / 5 * i,height / 2);
        }

        String ch = sb.toString();
        request.getSession().setAttribute("checkcode_session",ch);

        g.setColor(Color.YELLOW);
        for (int i = 0; i < 20; i++) {
            int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);

            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        ImageIO.write(img,"jpg",response.getOutputStream());
    }
}
