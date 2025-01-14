package com.training.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CaptchaServlet
 */
@WebServlet("/captcha.jpg")
public class CaptchaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		
		String str = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMOPQRSTUVWXYZ0123456789";
		String captcha = "";
		for(int i=0; i<6; i++) {
			int rno = (int) (Math.random() * str.length());
			captcha += str.charAt(rno);
		}
		
		BufferedImage img = new BufferedImage(150, 60, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 150, 60);
		g.setColor(Color.RED);
		g.setFont(new Font("Harrington", Font.BOLD, 32));
		g.drawString(captcha, 20, 40);
		
		ImageIO.write(img, "jpeg", out);
	}

}
