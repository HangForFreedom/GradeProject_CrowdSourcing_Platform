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
					<input type="text" name="verify" id="verify" class="ipt input-val" placeholder="输入验证码" required>
					<div class="imgcode">
						<canvas id="canvas" width="95" height="40"></canvas>
					</div>
				</div>
			</div>
			<div class="button">
				<button type="submit" class="btn login-btn register-btn" id="button">登录</button>
			</div>

			<div class="remember clearfix">
				<label class="remember-me">
					<%--<span class="icon"><span class="zt"></span></span>--%>
					<input type="checkbox" name="rempwd" id="remember-me" class="remember-mecheck" value="1" style="opacity: 1;">
						<label style="margin-left: 18px;">记住我</label>
				</label>
				<label class="forgot-password">
					<a href="updatePw.jsp">忘记密码？</a>
				</label>
			</div>
		</form>

	</div>
</div>

<div class="footer">
	<p>千言万语 - Thousands of Words</p>
	<p>Designed By Hang & <a href="https://hangforfreedom.github.io">Hang Blog</a> 2019</p>
</div>

<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/jquery.min.js' type="text/javascript"></script>
<script src='js/layer/layer.js' type="text/javascript"></script>
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
	$(function () {
		var show_num = [];
		draw(show_num);
		$('.imgcode').hover(function(){
			layer.tips("看不清？点击更换", '.verify', {
				time: 6000,
				tips: [2, "#3c3c3c"]
			})
		},function(){
			layer.closeAll('tips');
		}).click(function(){
			draw(show_num);
		});

		$("#button").on('click', function() {
			var val = $(".input-val").val().toLowerCase();
			var num = show_num.join("");
			if (val == '') {
				layer.open({
					title: '信息',
					icon: 2,
					content: '请输入完整信息！'
				});
				return false;
			} else if (val === num) {
				// layer.open({
				// 	title: '信息',
				// 	icon: 1,
				// 	content: '注册成功'
				// });
				// $(".input-val").val('');
				// draw(show_num);
				return true;
			} else {
				layer.open({
					title: '信息',
					icon: 2,
					content: '验证码错误！请重新输入！'
				});
				$(".input-val").val('');
				draw(show_num);
				return false;
			}
		})
	});

	//生成并渲染出验证码图形
	function draw(show_num) {
		var canvas_width = $('#canvas').width();
		var canvas_height = $('#canvas').height();
		var canvas = document.getElementById("canvas"); //获取到canvas的对象，演员
		var context = canvas.getContext("2d"); //获取到canvas画图的环境，演员表演的舞台
		canvas.width = canvas_width;
		canvas.height = canvas_height;
		var sCode = "a,b,c,d,e,f,g,h,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y,z,A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
		var aCode = sCode.split(",");
		var aLength = aCode.length; //获取到数组的长度

		for (var i = 0; i < 4; i++) { //这里的for循环可以控制验证码位数（如果想显示6位数，4改成6即可）
			var j = Math.floor(Math.random() * aLength); //获取到随机的索引值
			// var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
			var deg = Math.random() - 0.5; //产生一个随机弧度
			var txt = aCode[j]; //得到随机的一个内容
			show_num[i] = txt.toLowerCase();
			var x = 10 + i * 20; //文字在canvas上的x坐标
			var y = 20 + Math.random() * 8; //文字在canvas上的y坐标
			context.font = "bold 23px 微软雅黑";

			context.translate(x, y);
			context.rotate(deg);

			context.fillStyle = randomColor();
			context.fillText(txt, 0, 0);

			context.rotate(-deg);
			context.translate(-x, -y);
		}
		for (var i = 0; i <= 5; i++) { //验证码上显示线条
			context.strokeStyle = randomColor();
			context.beginPath();
			context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
			context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
			context.stroke();
		}
		for (var i = 0; i <= 30; i++) { //验证码上显示小点
			context.strokeStyle = randomColor();
			context.beginPath();
			var x = Math.random() * canvas_width;
			var y = Math.random() * canvas_height;
			context.moveTo(x, y);
			context.lineTo(x + 1, y + 1);
			context.stroke();
		}
	}

	//得到随机的颜色值
	function randomColor() {
		var r = Math.floor(Math.random() * 256);
		var g = Math.floor(Math.random() * 256);
		var b = Math.floor(Math.random() * 256);
		return "rgb(" + r + "," + g + "," + b + ")";
	}

	// $("#remember-me").click(function(){
	// 	var n = document.getElementById("remember-me").checked;
	// 	if(n){
	// 		$(".zt").hide();
	// 	}else{
	// 		$(".zt").show();
	// 	}
	// });
</script>
</body>
</html>