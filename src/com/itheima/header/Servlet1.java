package com.itheima.header;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		/*//没有响应  告知客户端重定向到servlet2
		//1设置状态码302
		res.setStatus(302);
		//2设置响应头location
		res.setHeader("Location", "/WEB14_response/servlet2");*/
		
		//封装成一个重定向的方法sendRedirect(url)
		res.sendRedirect("/WEB14_response/servlet2");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
