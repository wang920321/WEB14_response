package com.itheima.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获得要下载的文件名称
		String filename=req.getParameter("filename");
		//要下载的这个文件的类型-----------客户端通过文件的MIME类型去区分类型
		res.setContentType(this.getServletContext().getMimeType(filename));
		//告诉客户端改文件不是直接解析,而是以附件形式打开（下载）
		res.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		//获取文件的绝对路径
		String path=this.getServletContext().getRealPath("download/"+filename);
		//获得该文件的输入流
		InputStream in=new FileInputStream(path);
		//获得输出流--通过res获得输出流  用于向客户端写内容
		ServletOutputStream out=res.getOutputStream();
		//文件拷贝的模板代码
		int len=0;
		byte[] buffer=new byte[1024];
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
		}
		in.close();
		//out.close();可以不写    服务器会为你自动关闭，因为res来的
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
