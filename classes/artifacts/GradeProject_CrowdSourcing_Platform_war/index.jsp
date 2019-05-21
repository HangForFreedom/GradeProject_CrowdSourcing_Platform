<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录 - 千言万语 - Thousands of Words</title>
	<link rel="stylesheet" type="text/css" href="css/register-login.css">
</head>
<body onload="url()">
<div id="box"></div>
<div class="cent-box">
	<div class="cent-box-header">
		<h1 class="main-title">千言万语</h1>
		<h2 class="sub-title">Thousands of Words</h2>
	</div>

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="index.jsp" class="active">登录</a>
				<a href="register.jsp">注册</a>
				<div class="slide-bar"></div>
			</div>
		</div>

		<form class="login form" action="login.do" method="post" accept-charset="utf-8" onsubmit="return checkForm(this)">
			<div class="group">
				<div class="group-ipt email">
					<input type="phone" name="phone" id="phone" class="ipt" placeholder="手机号码" value="${cookie.u.value}" required>
					<span class="errorTip">${phoneNull}</span>
				</div>
				<div class="group-ipt password">
					<input type="password" name="password" id="password" class="ipt" value="${cookie.p.value}" placeholder="输入您的登录密码" required>
					<span class="errorTip">${msg}</span>
				</div>
				<div class="group-ipt verify">
					<input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required>
					<img src="http://zrong.me/home/index/imgcode?id=" class="imgcode">
				</div>
			</div>
			<div class="button">
				<button type="submit" class="btn login-btn register-btn" id="button">登录</button>
			</div>

			<div class="remember clearfix">
				<label class="remember-me">
					<span class="icon"><span class="zt"></span></span>
					<input type="checkbox" name="rempwd" id="remember-me" class="remember-mecheck" value="1">记住我</label>
				<label class="forgot-password">
					<a href="updatePw.jsp">忘记密码？</a>
				</label>
			</div>
		</form>

	</div>
</div>

<div class="footer">
	<p>千言万语 - Thousands of Words</p>
	<p>Designed By ZengRong & <a href="zrong.me">zrong.me</a> 2016</p>
</div>

<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/jquery.min.js' type="text/javascript"></script>
<script src='js/layer/layer.js' type="text/javascript"></script>
<script src='js/index.js' type="text/javascript"></script>
<script>
	function url(){
		var str = "${cookie.u.value}";
		var newstr = decodeURI(str);
		document.getElementById("phone").value = newstr;
	}

	function checkForm(){
		if(this.phone.value == ''){
			alert("手机号不能为空");
			return false;
		}else if(this.userpwd.value == ''){
			alert("密码不能为空");
			return false;
		}
		return true;
	}
</script>
<script>
	$('.imgcode').hover(function(){
		layer.tips("看不清？点击更换", '.verify', {
			time: 6000,
			tips: [2, "#3c3c3c"]
		})
	},function(){
		layer.closeAll('tips');
	}).click(function(){
		$(this).attr('src','http://zrong.me/home/index/imgcode?id=' + Math.random());
	});
	$("#remember-me").click(function(){
		var n = document.getElementById("remember-me").checked;
		if(n){
			$(".zt").show();
		}else{
			$(".zt").hide();
		}
	});
</script>
</body>
</html>