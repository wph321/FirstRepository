<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="zy.DealData" %>
<%
	String actions=request.getParameter("actions");
	String action=request.getParameter("action");
	DealData dd=new DealData();
	//用户登录
	if("login".equals(action)){
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		out.println(dd.userLogin(name,pwd,session,application));
	}
	//获取所有聊天消息
	else if("ChatList".equals(action)){
		String usernameInSession=(String)session.getAttribute("username");
		if(usernameInSession==null){
			out.print("unLogin");
			//out.print(dd.AllChatList(application));
		}else{
			out.print(dd.AllChatList(application));
		}
	}
	//发送消息
	else if("SendContent".equals(action)){
		String content=request.getParameter("content");
		out.println(dd.addContent(content,application,session));
		
	}
	//获取在线人员列表
	else if("onLineList".equals(action)){
		out.println(dd.GetOnlineUsers(application));
	}
	//下线
	else if("OffLine".equals(action)){
		out.print(dd.OffLine(application,session));
	}
	//实现注册
	if("zhuce".equals(actions)){
	
	String username=request.getParameter("name");
	String usernumbers=request.getParameter("unumber");
	String passwol=request.getParameter("pass");
		out.println(dd.zhucepanduan(username, usernumbers, passwol));	
	}

%>
