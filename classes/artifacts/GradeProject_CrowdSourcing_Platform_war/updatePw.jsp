<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>忘记密码</title>
    <link href="./css/style_log.css" rel="stylesheet" type="text/css">
</head>
<body class="login">
<div class="login_m">
    <div class="login_logo"><img src="./css/logo.png" width="196" height="46"></div>
    <div class="login_boder">
        <div class="login_padding" id="login_model">
            <!-- servlet的映射路径(url-pattern) -->
            <form action="update.do" method="post" accept-charset="utf-8">
                <label>
                    <input type="text" id="phone" class="txt_input" name="phone" value="" placeholder="电话号码">
                </label>
                <label>
                    <input type="password" id="newpassword" class="txt_input" name="newpassword" value="" placeholder="新密码">
                </label>
                <label>
                    <input type="password" id="newpassword2" class="txt_input" name="newpassword2" value="" placeholder="确认新密码">
                </label>
                <label>
                    <input type="text" id="security" class="txt_input" name="security" value="" placeholder="验证码">
                </label>
                <div class="rem_sub">
                    <label>
                        <input type="submit" class="sub_button" name="button" id="button" value="更改" style="opacity: 0.7;">
                        <input type="button" class="sub_button" style="margin-right: 105px; opacity: 0.7;" name="button" value="返回" onclick="window.location.href='index.jsp'">
                    </label>
                    <span style="color:red; float:left;">${msg}</span>
                </div>
            </form>
        </div>
        <!--login_padding  Sign up end-->
    </div><!--login_boder end-->
</div><!--login_m end-->
<br> <br>
<p align="center"> More Templates <a href="" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="" title="网页模板" target="_blank">网页模板</a></p>

</body>
</html>