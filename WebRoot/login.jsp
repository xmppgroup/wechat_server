<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	body{
		font-family:微软雅黑;
		background-color: #255182;
	}
	.div1{height:100%;}
	.login{
		width:370px;
		height:220px;
		border:1px solid #FFF;
		margin:0 auto;
		padding:20px;
		border-radius:15px;
		position:relative;
		background:rgb(78,112,150);
		z-index:10;
	}
	.logo img{
		width:350px;height:50px;
	}
	.input .tip{
		color:red;
		line-height:20px;
		text-align:center;
		font-size:14px;
	}
	.input .name{
		line-height:45px;
	}
	.input .pwd{ line-height:45px; }
	.input label{display:inline-block;width:70px;text-align:right;padding-right:20px;vertical-align:middle; font-size:14px;}
	.input .text{filter:alpha(opacity=80);-moz-opacity:0.8;opacity:0.8;}
	.text{border:1px solid #CCC;padding:5px;background-color:#FCFCFC;line-height:14px;width:220px;font-size:12px;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;-webkit-box-shadow: #CCC 0px 0px 5px;-moz-box-shadow: #CCC 0px 0px 5px;box-shadow: #CCC 0px 0px 5px;border:1px solid #CCC;font-size:12px;}
.text:focus{border:1px solid #31b6e7;background-color:#FFF;-webkit-box-shadow: #CCC 0px 0px 5px;-moz-box-shadow: #CCC 0px 0px 5px;box-shadow: #0178a4 0px 0px 5px;}
.text:hover{background-color:#FFF;}
	.input .button{border:none;font-weight:bold;color:#FFF;margin:15px 0 0 150px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;background: #31b6e7;cursor: pointer;padding:8px 20px}
	.input .button:hover{background:#ff9229;}	
	.div2{ position:absolute; width:100%; height:100%; top:0; left:0}
	.reqiqiu1{width:150px; height:150px;position:absolute;}
	.reqiqiu1 img{width:150px; height:150px;}
	.reqiqiu2{width:100px; height:120px;position:absolute;}
	.reqiqiu2 img{width:100px; height:120px;}
	.reqiqiu3{width:150px; height:150px;position:absolute;}
	.reqiqiu3 img{width:150px; height:150px;}
</style>
<script type="text/javascript" src="resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("body").height($(document).height());
		$(".login").css("top",$("body").height()/2-$(".login").height()/2-50);
		$(".div2").height($("body").height());
		AirInit();
		fly();
	});
	function AirInit(){
		$(".reqiqiu1").css({"right":"300px","top":"300px"});
		$(".reqiqiu2").css({"left":"300px","top":"400px"});
		$(".reqiqiu3").css({"left":"500px","top":"500px"});
	}
	function fly(){
		$(".reqiqiu1").animate({"top":"50px","right":"160px","width":"60px","height":"60px"},12000);
		$(".reqiqiu1 img").animate({"width":"60px","height":"60px"},12000);
		$(".reqiqiu2").animate({"top":"260px","left":"100px","width":"50px","height":"60px"},5000);
		$(".reqiqiu2 img").animate({"width":"50px","height":"60px"},5000);
		$(".reqiqiu3").animate({"top":"30px","left":"800px"},9000);
	}
</script>
</head>

<body>
	
	<div class="div1">
		
        <div class="login">
            <div class="logo">
                <img src="resources/img/logo-login.png" />
            </div>
           <form action="login" method="post" name="user">
            <div class="input" style="margin-top: 20px;">
          
                <div class="name">
                    <label>用户名</label><input name="userName" type="text" class="text" placeholder="用户名"/>
                </div>
                <div class="pwd">
                    <label>密&nbsp;&nbsp;&nbsp;码</label><input name="passWord" type="password" class="text" placeholder="密码"/>
                </div>
                <input type="submit" value="登录" class="button"/>
            </div>
           </form>
        </div>
    </div>
    <div class="div2">
    	<div class="reqiqiu1"><img src="resources/img/reqiqiu1.png"/></div>
        <div class="reqiqiu2"><img src="resources/img/reqiqiu2.png"/></div>
        <div class="reqiqiu3"><img src="resources/img/reqiqiu3.png"/></div>
    </div>
</body>
</html>
