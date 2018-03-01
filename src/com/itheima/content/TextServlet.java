package com.itheima.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置res查询的码表
		/*res.setCharacterEncoding("UTF-8");可以不设置*/
		//通过一个头Content-Type 告知客户端用何种码表
		//res.setHeader("Content-Type", "text/html;charset=UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		PrintWriter writer = res.getWriter();
		writer.write("hello response");
		writer.write("中国,你好吗saf");
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
