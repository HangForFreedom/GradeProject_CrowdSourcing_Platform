<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登陆</title>
	<link href="./css/style_log.css" rel="stylesheet" type="text/css">
</head>

<body class="login" onload="url()">
	<div class="login_m">
		<div class="login_logo"><img src="./css/logo.png" width="196" height="46"></div>
		<div class="login_boder">
			<div class="login_padding" id="login_model">
			<!-- servlet的映射路径(url-pattern) -->
				<form action="login.do" method="post" accept-charset="utf-8" onsubmit="return checkForm(this)">
					<h2>Your phone</h2>
					<label>
						<input type="text" id="phone" class="txt_input txt_input2" name="phone" value="${cookie.u.value}">
					</label>
					<h2>Your password</h2>
					<label>
						<input type="password" id="userpwd" class="txt_input" name="password" value="${cookie.p.value}">
					</label>
					<p class="forgot"><span style="color:red; float:left;">${msg}</span><a id="iforget" href="updatePw.jsp">忘记密码？</a></p>
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" id="save_me" value="1" name="rempwd">
							<label for="checkbox">记住密码</label>
						</div>
						<label>
							<input type="submit" class="sub_button" style="margin-left: 10px; opacity: 0.7;" name="button" id="button" value="登录">
							<input type="button" class="sub_button" name="button" value="注册" style="opacity: 0.7;" onclick="window.location.href='register.jsp'">
						</label>
					</div>
				</form>
			</div>
		<!--login_padding  Sign up end-->
		</div><!--login_boder end-->
	</div><!--login_m end-->
	<br> <br>
	<p align="center"> More Templates <a href="" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="" title="网页模板" target="_blank">网页模板</a></p>

</body>
<script type="text/javascript"> 
	function url(){
		var str = "${cookie.u.value}";
		var newstr = decodeURI(str);
		document.getElementById("username").value = newstr;
	}
</script>
<script type="text/javascript">
	function checkForm(){
		if(this.username.value == ''){
			alert("用户名不能为空");
			return false;
		}else if(this.userpwd.value == ''){
			alert("密码不能为空");
			return false;
		}
		return true;
	}

</script>
</html>