package com.akokko.test;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/img")
public class ResponseTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = img.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0,0,width,height);

        g.setColor(Color.BLUE);
        g.drawRect(0,0,width - 1,height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random r = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = r.nextInt(str.length());
            char c = str.charAt(index);
            g.drawString(c+"",width / 5 * i,height / 2);
        }

        g.setColor(Color.YELLOW);
        for (int i = 0; i < 30; i++) {
            int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);

            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }


        ImageIO.write(img,"jpg",resp.getOutputStream());
    }
}
