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
	//�û���¼���ж�
	@SuppressWarnings("deprecation")
	public String userLogin(String usernamber,String password,HttpSession session,ServletContext application) throws IOException{
		String resource = "conf.xml"; 
		//����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
		Reader reader = Resources.getResourceAsReader(resource); 
		//����sqlSession�Ĺ���
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//������ִ��ӳ���ļ���sql��sqlSession
		SqlSession session1 = sessionFactory.openSession();
		//ӳ��sql�ı�ʶ�ַ���
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
			//���������ȷ�ͽ��û�������session
			session.setAttribute("username",user.getUser_name());
//			System.out.println(session.getValue("username"));
			
			//��ȡ�����û��б�
			@SuppressWarnings("unchecked")
			List<String> users=(List<String>)application.getAttribute("users");
			if(users==null){
				users=new ArrayList<String>();
			}
			//����ǰ�û������û��б�
			users.add(user.getUser_name());
			//����application�еĶ���
			application.setAttribute("users", users);
			return "True";
		}else{
			return "False";
		}
	}else{
		return "False";
	}
	}
	
	//�����Ϣ
	public String addContent(String content,ServletContext application,HttpSession session){
		@SuppressWarnings("unchecked")
		List<String> strList=(List<String>) application.getAttribute("MessageList");
		if(strList==null){
			strList=new ArrayList<String>();
		}
		//��ȡsession�е��û���
		String username=(String)session.getAttribute("username");
		//��ȡʱ��
		Date date=new Date();
		content=content.replace("<:", "<img src='QQface/");
		content=content.replace(":>", ".gif' />");
		//��װ��Ϣ
		String message=username+":."+date.toLocaleString()+"˵��"+content;
		//��ӵ�������
		strList.add(message);
		//����application������
		application.setAttribute("MessageList", strList);
		return "True";
	}
	//��ȡ���е���Ϣ
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
	//��ȡ�����û��б�
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
	//����
	public String OffLine(ServletContext application,HttpSession session){
		//��ȡ���û���
		String username=(String)session.getAttribute("username");
		//�Ƴ�session�е�����
		session.removeAttribute("username");
		//�Ƴ��û��б��е��û���
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
		//����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
		Reader reader = Resources.getResourceAsReader(resource); 
		//����sqlSession�Ĺ���
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//������ִ��ӳ���ļ���sql��sqlSession
		SqlSession session2 = sessionFactory.openSession();
		//ӳ��sql�ı�ʶ�ַ���
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
			   				//ӳ��sql�ı�ʶ�ַ���
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
