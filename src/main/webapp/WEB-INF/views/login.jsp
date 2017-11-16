<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理平台</title>
		
		<style>
			body{
				background-image: url("${ctx}/images/bg.jpg");
				background-size: 100%;
			}
			
			.h1_style{
				color: #fff;
				text-align: center;
			    margin-top: 200px;
			    clear: both;
			}
			
			.input_div_bg{
				width: 500px;
				height: 300px;
				top: 0px;
			    left: 0px;
			    right: 0px;
			    bottom: 0px;
			    margin: auto;
				position: fixed;
				clear: both;
				background-color: #ffffff;
  				border: 1px solid rgba(0,0,0,0.1);
			}
			
			.input_div_sytle{
				width: 300px;
				height: 150px;
			    margin: auto;
            	position: absolute;
            	box-sizing:border-box;
            	top: 0; left: 0; bottom: 0; right: 0;
			}
			
			.input{
				width: 100%;
				height: 40px;
				border: 1px solid #D3D3D3;
				background: #fff;
				text-indent: 10px;
				margin-bottom: 10px;
			}
			
			.submit_style{
				width: 100%;
				height: 40px;
				background: #0683B1;
				border: 1px solid #D3D3D3;
				color: #fff;
			}
		</style>
	</head>
	<body>
		<script type="text/javascript" color="0,0,255" opacity='0.8' zIndex="-2" count="200" src="//cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
		
		<h1 class="h1_style">管理平台</h1>
		
		<div class="input_div_bg">
			<div class="input_div_sytle">
				<form action="login" method="post">
					<input name="userName" class="input" type="text" placeholder="用户名" required="" autofocus="">
					<input name="password" class="input" type="password" placeholder="密码" required="">
					<button class="submit_style" type="submit">登录</button>
				</form>
			</div>
			<h4 style="color: red; width: 100%; text-align: center;">${error}</h4>
		</div>
	</body>
</html>

