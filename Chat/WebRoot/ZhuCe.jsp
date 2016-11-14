<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账号注册</title>
    <style type="text/css">
            body{font-size:12px;text-align:center;}
            #divFrame{margin-top:100px}
            #spnMsg{position:absolute;border:solid 1px #cc3300;padding:2px;background-color:#ffe0a3;display:none}
        </style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" src="jquery-2.1.4.js"></script> 
<script type="text/javascript"> 


                $("#zhuce").click(function(){
                    var $name=$("#uName").val();
                    var $unumber=$("#uNum").val();
                    var $passworld=$("#tPwd").val();
                    UserLogin($name,$unumber,$passworld);
                    }); 
                    
function UserLogin(name,unumber,passworld){
                    $.ajax({
                        type:"POST",
                        url:"DealData.jsp",
                        data:"actions=zhuce&d="+new Date()+"&name="+name+"&unumber="+unumber+"&pass="+passworld,
                        success:function(data){
                        if(data.indexOf("True")>0){
                        alert("注册成功");
                         window.location="ChatMain.html";
                        }else{if(data.equls("namefalse")){
                        	$("#spnMsg1").html("用户名已被占用").css({"color":"red"});
                        }else{if(data.equls("numberfalse")){
                        	$("#spnMsg2").html("账号已被占用").css({"color":"red"});
                        }else{
                        	$("#spnMsg3").html("密码不可用").css({"color":"red"});
                        }
                        }
                        }
                        }
                        });
};


</script> 
  </head>
  
  <body>
    <div id="divFrame" > 
    <form id="fram" action="/Chat/WebRoot/DealData.jsp" method="post">
  <font size="4" color="blue"><b>注册</b></font> </br>
  &nbsp用户名：<input type="text" name="unName" id="uName"><span id="spnMsg1"></span></br>
   &nbsp账  号   ：<input type="text" name="unumber" id="uNum">&nbsp;<span id="spnMsg2"></span>  </br>
  密 码  ：<input id="tPwd" type="password" name="pswd"><span id="spnMsg3"></span> </br></br></br>&nbsp&nbsp&nbsp&nbsp&nbsp
  		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input id = "zhuce" type="button" value="提交" align="middle">&nbsp&nbsp&nbsp&nbsp&nbsp
  		<input id = "重置" type="reset" align ="middle">
  </form>
  </div> 
  </body>
</html>
