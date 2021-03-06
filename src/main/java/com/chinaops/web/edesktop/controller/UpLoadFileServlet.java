package com.chinaops.web.edesktop.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UpLoadFileServlet extends HttpServlet {
	
	private static final Log  log=LogFactory.getLog(UpLoadFileServlet.class);
	private static final long serialVersionUID = 7538795265560162574L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		log.debug("isMultipartContent" + isMultipartContent);
		if (!isMultipartContent) {
			//out.println("You are not trying to upload<br/>");
		}
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String classname = this.getClass().toString();
		String pathname = this.getClass()
		         .getResource(classname.substring(classname.lastIndexOf(".") + 1) + ".class")
		         .getPath();
		log.debug("pathname:" + pathname);
		String propath = pathname.substring(0, pathname.indexOf("/WEB-INF"))  +File.separatorChar + "download" +File.separatorChar+"excel"+File.separatorChar;
        
		SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
        SimpleDateFormat mm = new SimpleDateFormat("MM");
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        String haomiao = c.getTimeInMillis()+"";
        String savePath =  yyyy.format(c.getTime()) + File.separatorChar + mm.format(c.getTime()) + File.separatorChar + dd.format(c.getTime()) + File.separatorChar + haomiao + File.separatorChar;
		
		String result = "";
		String filename = "";
		String fpath = "";
		try {
			List<FileItem> fields = upload.parseRequest(request);
			log.debug("Number of fields: [" + fields.size() + "]");
			Iterator<FileItem> it = fields.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				
				// 判断是文件还是文本信息
				if (!item.isFormField()) {
					long max_size = (50 * 1024 * 1024);
					if(item.getSize() > max_size){
					    String message = "文件超过规定大小,上传失败,请重新上传。文件最大为" + 50 + "MB。";
					    this.outMessage(response, message);
					    return;
					}
					    
					String filepath = item.getName();
					filename = filepath.substring(filepath.lastIndexOf("\\")+1,filepath.length());
					fpath = propath + File.separatorChar + savePath  + File.separatorChar;

//					item.write(file);// 保存文件到服务器的硬盘上
					
					File file = new File(fpath);
					String nameOfFile = file.getName();
					if(!file.exists()){
						file.mkdirs();
					}
					File f = new File(file.getPath(), filename);
					item.write(f);// 保存文件到服务器的硬盘上
					
					result = "_"+yyyy.format(c.getTime())  + mm.format(c.getTime()) + dd.format(c.getTime())+filepath+"_"+haomiao;
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.print("{msg:\""+result+"\"}");
		out.flush();
		out.close();
	}
    public void outMessage(HttpServletResponse response, String message) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('" + message + "');history.reload();");
		    out.println("</script>");
		    return;
		} catch (IOException e) {
		    return;
		}
    }
}

