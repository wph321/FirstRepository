<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>用户登录</title>
        <script language="javascript" type="text/javascript" src="jquery-2.1.4.js"></script>
        
        <style type="text/css">
            body{font-size:12px;text-align:center;}
            .divFrame{margin-top:100px}
            #spnMsg{position:absolute;border:solid 1px #cc3300;padding:2px;background-color:#ffe0a3;display:none}
        </style>

        <script language="javascript" type="text/javascript">
            $(function(){
                $("#login").click(function(){
                    var $name=$("#txtName").val();
                    var $pwd=$("#txtPwd").val();
                    UserLogin($name,$pwd);
                    $("#spnMsg").html("正在登录...").show().css({"top":"20%","left":"50%"});
                });
                function UserLogin(name,pwd){
                    $.ajax({
                        type:"POST",
                        url:"DealData.jsp",
                        data:"action=login&d="+new Date()+"&name="+name+"&pwd="+pwd,
                        success:function(data){
                            if(data.indexOf("True")>0){
                                window.location="ChatMain.html";
                            }else{
                                alert("用户名或者密码错误");
                                $("#spnMsg").hide();
                                return false;
                            }
                        }
                    });
                }
            });
        </script>
    </head>
    <body>
        <div class="divFrame">
            <h3>用户登录</h3>
            用户  ：<input id="txtName" type="text" name="name"> </br></br>
            密码  ：<input id="txtPwd" type="password" name="pwd"> </br></br>
            <input id="login" type="button" value="登录">
           <a href = "ZhuCe.jsp"> <input id="zhuce" type="button" value="注册"> </a></br>
            
            <span id="spnMsg">Tips</span>
        </div>
        
    </body>
</html>