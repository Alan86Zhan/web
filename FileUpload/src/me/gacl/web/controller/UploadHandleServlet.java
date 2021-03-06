package me.gacl.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
public class UploadHandleServlet extends HttpServlet {
 
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
File file = new File(savePath);
//判断上传文件的保存目录是否存在
if (!file.exists() && !file.isDirectory()) {
System.out.println(savePath+"!!!!!!!!dir doesn't exist,!!!!!!!!!!!");
//创建目录
file.mkdirs();

}
String usertagPath = this.getServletContext().getRealPath("/usertag");
File usertagfile = new File(usertagPath);
//判断上传文件的保存目录是否存在
if (!usertagfile.exists() && !usertagfile.isDirectory()) {
System.out.println(usertagfile+"!!!!!!!!dir doesn't exist,!!!!!!!!!!!");
//创建目录
usertagfile.mkdirs();

}
//消息提示
String message = "";
String usermessage = "";

try{
//使用Apache文件上传组件处理文件上传步骤：
//1、创建一个DiskFileItemFactory工厂
DiskFileItemFactory factory = new DiskFileItemFactory();
//2、创建一个文件上传解析器
ServletFileUpload upload = new ServletFileUpload(factory);
//解决上传文件名的中文乱码
upload.setHeaderEncoding("UTF-8"); 
//3、判断提交上来的数据是否是上传表单的数据
if(!ServletFileUpload.isMultipartContent(request)){
//按照传统方式获取数据
	
	System.out.println("Error 3::::!ServletFileUpload.isMultipartContent(request)");
return;
}
//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
List<FileItem> list = upload.parseRequest(request);
String userPath = "";
String userPathupload = "";
String usernamevalue="";
String userpemPath="";
int i=0;
for(FileItem item : list){
	i++;
//如果fileitem中封装的是普通输入项的数据
if(item.isFormField()){
String name = item.getFieldName();
//解决普通输入项的数据的中文乱码问题
String value = item.getString("UTF-8");
usernamevalue =value;
//value = new String(value.getBytes("iso8859-1"),"UTF-8");
/**添加用户目录**/
String useruploadPath = this.getServletContext().getRealPath("/WEB-INF/upload")+"/"+value;
userPathupload = useruploadPath;
File useruploadfile = new File(useruploadPath);
if (!useruploadfile.exists() && !useruploadfile.isDirectory()) {
System.out.println(useruploadPath+"!!!!!!!!dir doesn't exist,!!!!!!!!!!!");
//创建目录
useruploadfile.mkdirs();

}
else
{
	//useruploadfile.delete();
	useruploadfile.mkdirs();
 System.out.println(useruploadPath+"~~~~~~dir is existed~~~~~");
}
/**添加用户目录**/


System.out.println(name + "=" + value);
if(value==null || value.trim().equals("")){
	System.out.println("you need to input a name::::::~~");
	
continue;
}

}else{//如果fileitem中封装的是上传文件
//得到上传的文件名称，
String filename = item.getName();
//System.out.println("upload file name is::::::~~"+filename);
if(filename==null || filename.trim().equals("")){
	System.out.println("you need to input a pemfile and file::::::~~");
	
continue;
}
//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
//处理获取到的上传文件的文件名的路径部分，只保留文件名部分

System.out.println("upload file name 1 is::::::~~"+filename);
filename = filename.substring(filename.lastIndexOf("\\")+1);
//filename = filename.substring(filename.lastIndexOf("\\")+1);
System.out.println("upload file name 2 is::::::~~"+filename);
//获取item中的上传文件的输入流
InputStream in = item.getInputStream();
//创建一个文件输出流
//FileOutputStream out = new FileOutputStream(savePath + "/" + filename);

//FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
System.out.println(userPath+"!!!!!!!!userpath as you can see!!!!!!!!!!!");


/**添加用户pem目录**/
if(i==2){
	
	
String usernamePath = this.getServletContext().getRealPath("/WEB-INF/userpem")+"/"+usernamevalue;

userpemPath = usernamePath;
File userpemfile = new File(usernamePath);
if (!userpemfile.exists() && !userpemfile.isDirectory()) {
//创建目录
	
userpemfile.mkdirs();
userPath =userpemPath;
System.out.println("userPath is "+userpemPath+"i =="+i+"!!!!!!!!!!!!!!!!!!!");
}
else
{
 //userpemfile.delete();
 userpemfile.mkdirs();
 userPath =userpemPath;
 System.out.println("userpemPath is "+userPath+"i =="+i+"!!!!!!!!!!dir is existed!!!!!!!!!");
}
/**添加用户pem目录**/
}
else 
{
	userPath = userPathupload;
System.out.println("userPath is "+userPath+"i =="+i+"!!!!!!!!!!!!!!!!!!!");
}

FileOutputStream out = new FileOutputStream(userPath + "/" + filename);
System.out.println("~~~~~~userPath dir is ~~~~~"+userPath);

//创建一个缓冲区
byte buffer[] = new byte[1024];
//判断输入流中的数据是否已经读完的标识
int len = 0;
//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
while((len=in.read(buffer))>0){
//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
out.write(buffer, 0, len);
}
//关闭输入流
in.close();
//关闭输出流
out.close();
//删除处理文件上传时生成的临时文件
item.delete();
message = "文件上传成功！";
usermessage=usernamevalue;
}
}
}catch (Exception e) {
message= "文件上传失败！";
e.printStackTrace();
 
}

request.setAttribute("message",message);
request.setAttribute("usermessage",usermessage);
request.getRequestDispatcher("/message.jsp").forward(request, response);
}
 
public void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
doGet(request, response);
}
}
