package com.itheima.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class DownloadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//文件名称是中文的怎样下载？
		
		
		//获得要下载的文件名称
		String filename=req.getParameter("filename");//美女.jpg（乱码）
		//解决获得中文参数的乱码
		filename=new String(filename.getBytes("ISO8859-1"),"UTF-8");//美女.jpg
		//获得请求头中的User-Agent
		String agent = req.getHeader("User-Agent");
		//根据不同的浏览器进行不同的编码
		String filenameEncoder="";
		if (agent.contains("MSIE")) {
			// IE浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");
			filenameEncoder = filenameEncoder.replace("+", " ");
		} else if (agent.contains("Firefox")) {
				// 火狐浏览器
		BASE64Encoder base64Encoder = new BASE64Encoder();
		filenameEncoder = "=?utf-8?B?"
						+ base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else {
				// 其它浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");				
		}
		
		//要下载的这个文件的类型-----------客户端通过文件的MIME类型去区分类型
		res.setContentType(this.getServletContext().getMimeType(filename));
		//告诉客户端改文件不是直接解析,而是以附件形式打开（下载）---filename="+filename  客户端默认对名字进行解码
		res.setHeader("Content-Disposition", "attachment;filename="+filenameEncoder);
		
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
