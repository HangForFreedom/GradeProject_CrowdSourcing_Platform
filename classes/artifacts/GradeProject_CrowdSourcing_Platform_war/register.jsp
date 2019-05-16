<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <link href="./css/style_log.css" rel="stylesheet" type="text/css">
</head>

<body class="login">
<div class="login_m">
    <div class="login_logo"><img src="./css/logo.png" width="196" height="46"></div>
    <div class="login_boder">
        <div class="login_padding" id="login_model">
            <!-- servlet的映射路径(url-pattern) -->
            <form action="register.do" method="post" accept-charset="utf-8">
                <label>
                    <input type="text" id="username" class="txt_input txt_input2" name="username" placeholder="用户名">
                </label>
                <label>
                    <input type="password" id="userpwd" class="txt_input" name="password" placeholder="密码">
                </label>
                <label>
                    <input type="password" class="txt_input txt_input2" name="password2" placeholder="确认密码">
                </label>
                <label>
                    <input type="text" id="phone" class="txt_input" name="phone" value="" placeholder="电话号码">
                </label>
                <div class="rem_sub">
                    <label>
                        <input type="submit" class="sub_button" name="button" id="button" value="提交" style="opacity: 0.7;">
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
