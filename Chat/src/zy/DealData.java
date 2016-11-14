package zy;
import com.dealdata.*;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.dealdata.User;

@SuppressWarnings("unused")
public class DealData {
	//用户登录的判读
	@SuppressWarnings("deprecation")
	public String userLogin(String usernamber,String password,HttpSession session,ServletContext application) throws IOException{
		String resource = "conf.xml"; 
		//加载mybatis的配置文件（它也加载关联的映射文件）
		Reader reader = Resources.getResourceAsReader(resource); 
		//构建sqlSession的工厂
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//创建能执行映射文件中sql的sqlSession
		SqlSession session1 = sessionFactory.openSession();
		//映射sql的标识字符串
	String statement = "com.dealdata.UserMapper.getUser";
	User user = session1.selectOne(statement, usernamber);
	session1.close();
//	System.out.println(user.getId());
//	System.out.println(user.getUser_namber());
//	System.out.println(user.getUser_passworld());
//	System.out.println(user.getUser_name());
//	System.out.println(usernamber);
//	System.out.println(password);
	if(user.getUser_namber().equals(usernamber)){
	
		if(user.getUser_passworld().equals(password)){
			//如果密码正确就将用户名放入session
			session.setAttribute("username",user.getUser_name());
//			System.out.println(session.getValue("username"));
			
			//获取在线用户列表
			@SuppressWarnings("unchecked")
			List<String> users=(List<String>)application.getAttribute("users");
			if(users==null){
				users=new ArrayList<String>();
			}
			//将当前用户加入用户列表
			users.add(user.getUser_name());
			//更新application中的对象
			application.setAttribute("users", users);
			return "True";
		}else{
			return "False";
		}
	}else{
		return "False";
	}
	}
	
	//添加消息
	public String addContent(String content,ServletContext application,HttpSession session){
		@SuppressWarnings("unchecked")
		List<String> strList=(List<String>) application.getAttribute("MessageList");
		if(strList==null){
			strList=new ArrayList<String>();
		}
		//获取session中的用户名
		String username=(String)session.getAttribute("username");
		//获取时间
		Date date=new Date();
		content=content.replace("<:", "<img src='QQface/");
		content=content.replace(":>", ".gif' />");
		//组装消息
		String message=username+":."+date.toLocaleString()+"说："+content;
		//添加到集合中
		strList.add(message);
		//放入application对象中
		application.setAttribute("MessageList", strList);
		return "True";
	}
	//获取所有的消息
	public String AllChatList(ServletContext application){
		StringBuffer sb=new StringBuffer();
		List<String> strList=(List<String>) application.getAttribute("MessageList");
		if(strList!=null){
			for(String s:strList){
				sb.append(s+"<br />");
			}
		} 
		return sb.toString();
	}
	//获取在线用户列表
	public String GetOnlineUsers(ServletContext application){
		StringBuffer sb=new StringBuffer();
		List<String> strList=(List<String>) application.getAttribute("users");
		if(strList!=null){
			for(String s:strList){
				sb.append(s+"<br />");
			}
		} 
		return sb.toString();
	}
	//下线
	public String OffLine(ServletContext application,HttpSession session){
		//先取出用户名
		String username=(String)session.getAttribute("username");
		//移除session中的内容
		session.removeAttribute("username");
		//移除用户列表中的用户名
		@SuppressWarnings("unchecked")
		List<String> strList=(List<String>) application.getAttribute("users");
		if(strList!=null){
			strList.remove(username);
		} 
		return "True";
	}
	@Test
	public String zhucepanduan(String usernames,String usernumberss,String passwold) throws IOException{
		
		String resource = "conf.xml"; 
		//加载mybatis的配置文件（它也加载关联的映射文件）
		Reader reader = Resources.getResourceAsReader(resource); 
		//构建sqlSession的工厂
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//创建能执行映射文件中sql的sqlSession
		SqlSession session2 = sessionFactory.openSession();
		//映射sql的标识字符串
//				String userse = "123456789";
			   List<User> list = session2
			     .selectList("com.dealdata.UserMapper.selectUserAll");
			   session2.close();
			   String back = null;
			   for(int i=0;i<list.size();i++){
			   	if(list.get(i).getUser_name().equals(usernames)){
			   		if(list.get(i).getUser_namber().equals(usernumberss)){
			   			if(list.get(i).getUser_passworld().equals(passwold)){
			   				back = "True";
			   				SqlSession session3 = sessionFactory.openSession();
			   				//映射sql的标识字符串
			   			String statement = "com.dealdata.UserMapper.insertUser";
			   			int insert = session3.insert(statement,new User(-1,usernumberss,passwold,null,usernames));
			   				session3.close();
			   				System.out.println(insert);
			   			}else{
			   				back = "false";
			   				
			   			}
			   			
			   		}else{
			   			
			   			back = "numberfalse";
			   		}
			   				
			   	}else{
			   		back = "namefalse";
			   	}
			   }
			return back;
			
			   }
}
