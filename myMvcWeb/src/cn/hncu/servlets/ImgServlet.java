package cn.hncu.servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码！
 * ---4个随机数字，8条随机干扰线-数字，线的颜色随机
 * @author 陈浩翔
 * 2016-8-2
 */
public class ImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("imag/jpg");
		
		int width = 80;
		int height= 30;
		int lines = 8;
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = img.getGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		g.setFont(new Font("黑体", Font.BOLD, 18));
		
		long time = new Date().getTime();
		
		String realCode="";
		
		Random r = new Random(time);
		for(int i=0;i<4;i++){
			Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			g.setColor(c);
			
			int y=r.nextInt(10);
			
			int a = r.nextInt(10);
			g.drawString(a+"",5+i*18,y+12);
			realCode+=a;
		}
		
		Cookie cook = new Cookie("realCode", realCode);
		cook.setMaxAge(60*60);
		cook.setPath(request.getContextPath());
		response.addCookie(cook);
		
		//request.getServletContext().setAttribute("realCode",realCode);
		//System.out.println("imgServlet:"+realCode);
		
		for(int i=0;i<lines;i++){
			Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			g.setColor(c);
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}
		
		//刷入img对象
		g.dispose();
		
		ImageIO.write(img, "jpg", response.getOutputStream());
		
		
	}

}
