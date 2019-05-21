<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>忘记密码 - 千言万语 - Thousands of Words</title>
    <link rel="stylesheet" type="text/css" href="css/register-login.css">
</head>
<body>
<div id="box"></div>
<div class="cent-box register-box">
    <div class="cent-box-header">
        <h1 class="main-title">千言万语</h1>
        <h2 class="sub-title">Thousands of Words</h2>
    </div>

    <div class="cont-main clearfix">
        <div class="index-tab">
            <div class="index-slide-nav">
                <a href="register.jsp" class="active">忘记密码</a>
                <div class="slide-bar"></div>
            </div>
        </div>

        <form class="login form" action="updatePw.do" method="post" accept-charset="utf-8">
            <div class="group">
                <div class="group-ipt email">
                    <input type="phone" name="phone" id="phone" class="ipt" placeholder="手机号码" required>
                    <span class="errorTip">${phoNull}</span>
                </div>
                <div class="group-ipt password">
                    <input type="password" name="newpassword" id="password" class="ipt" placeholder="设置新的密码" required>
                </div>
                <div class="group-ipt password1">
                    <input type="password" name="newpassword2" id="password1" class="ipt" placeholder="确认新的密码" required>
                    <span class="errorTip">${pswError}</span>
                </div>
                <div class="group-ipt verify">
                    <input type="text" name="security" id="verify" class="ipt" placeholder="输入验证码" required>
                    <img src="http://zrong.me/home/index/imgcode?id=" class="imgcode">
                </div>
            </div>
            <div class="button">
                <button class="btn login-btn register-btn" style="width: 45%;float: left;" onclick="window.location.href='index.jsp'">返回</button>
                <button type="submit" class="btn login-btn register-btn" style="width: 45%;float: right;">确认</button>
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
    $('.imgcode').hover(function(){
        layer.tips("看不清？点击更换", '.verify', {
            time: 6000,
            tips: [2, "#3c3c3c"]
        })
    },function(){
        layer.closeAll('tips');
    }).click(function(){
        $(this).attr('src','http://zrong.me/home/index/imgcode?id=' + Math.random());
    })
</script>
</body>
</html>