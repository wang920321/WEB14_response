package com.itheima.header;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Date date=new Date();
		//设置响应头
		res.addHeader("name", "zhangsan");
		/*res.addIntHeader("age", 28);
		res.addDateHeader("birthday", date.getTime());*/
		res.addHeader("name", "lisi");
		res.setHeader("age", "28");
		res.setHeader("age", "50");
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
