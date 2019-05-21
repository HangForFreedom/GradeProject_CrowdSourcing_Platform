<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>问答</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
</head>

<body>

<header>
    <div class="top">
        <a href="index.jsp" class="logo"></a>
        <a href="index.jsp" class="nav">首页</a>
        <a href="index.jsp" class="nav">全部问题</a>
        <a href="index.html" class="nav">高分悬赏</a>
        <a href="myQue.jsp" class="nav">我的问题</a>
        <div class="user">
            <!-- <a href="" class="log">登录</a>
            <a href="" class="log">注册</a> -->
            <a href="" class="log">个人中心</a>
            <a href="" class="log">注销</a>
        </div>
        <div class="so">
            <input type="text" name="key" class="key" placeholder="请输入关键词">
            <input type="submit" class="sobut" value="搜索答案">
        </div>

    </div>
</header>


<div class="ask_main">


    <div class="amIn">
        <!---itemlist S--->
        <c:forEach items="${quebs}" var="queb">
            <div class="AskItemList">
                <div class="top">
                    <div class="info">
                        <span style="color:#666;">${queb.time}&nbsp;</span><span>来自&nbsp;</span><a href="" class="uname">${queb.username}</a><span>&nbsp;的提问</span>
                        <a href="vist.html" class="title">${queb.title}</a>
                    </div>
                    <div class="da">
                        <span><em>${queb.anssum2que}</em><dl>已有回答</dl></span>
                    </div>
                </div>
                <div class="desc">${queb.content}
                    <c:if test="${queb.image!=none }">
                        <img class="mypic" src="${queb.image}" >
                    </c:if>
                </div>
                <div class="tags">
                    <a href="">html</a>
                    <div class="Appreciation">
                        <i></i><span>${queb.score}</span>
                    </div>
                    <div class="share_bar_con">
                        <span>
                            <dl>浏览量</dl><em>(16)</em><i>|</i>
                            <dl>点赞</dl><em class="cur">(16)</em><i>|</i>
                            <dl>收藏</dl><em class="cur">(8)</em>
                        </span>
                    </div>
                </div>
            </div>
        </c:forEach>
        <!---itemlist E--->
        <!---itemlist S--->
        <%--<div class="AskItemList">--%>
        <%--<div class="top">--%>
        <%--<div class="info">--%>
        <%--<span style="color:#666;">2小时前&nbsp;</span><span>来自&nbsp;</span><a href="" class="uname">xiezhengyi1986</a><span>&nbsp;的提问</span>--%>
        <%--<a href="vist.html" class="title">如何在dxf文件中添加新图层及在新添图层中添加实体信息？</a>--%>
        <%--</div>--%>
        <%--<div class="da">--%>
        <%--<span><em>24</em><dl>已有回答</dl></span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="desc">--%>
        <%--<img src="images/1.jpg" class="cover">--%>
        <%--<div class="c">拦截所有的浏览器请求 access="ROLE_ADMIN" 只有ROLE_ADMIN角色的用才可以访问 规则角色名必须以ROLE_开头 为啥都得以ROLE_开头 还必须得大写 我试了小写role都不..--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="tags">--%>
        <%--<a href="">html</a>--%>
        <%--<div class="share_bar_con">--%>
        <%--<span>--%>
        <%--<dl>浏览量</dl><em>(16)</em><i>|</i>--%>
        <%--<dl>点赞</dl><em class="cur">(16)</em><i>|</i>--%>
        <%--<dl>收藏</dl><em class="cur">(8)</em>--%>
        <%--</span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <!---itemlist E--->
    </div>

    <div class="amRight">
        <div class="userInfo">
            <div class="us">
                <a href="" class="portrait"><img src="${ub.role}"></a>
                <div class="info">
                    <a href="">${ub.username}</a>
                </div>
            </div>
            <div class="wenda">
                <div class="txt"><i class="w">问</i><span>提了 <a href="">${quesum}</a> 个问题， <a href="">349</a> 人进行了回答</span></div>
                <div class="txt"><i class="d">答</i><span>回答了 <a href="">${anssum}</a> 个问题</span></div>
            </div>
        </div>


        <div style="width:100%; height:20px;"> </div>


            <div class="footer">
                <h2><span>联系我们</span></h2>
                <div class="qrc">
                    <span><img src="images/ewm.png"><dl>微信客服</dl></span>
                    <span><img src="images/ewm.png"><dl>QQ客服</dl></span>
                </div>
                <div class="qq">
                    <span>客服QQ：373604177</span>
                    <span>联系电话：18758036615</span>
                    <span>E-mail：xiezhengyi@126.com</span>
                </div>
                <div class="nlink">
                    <span><a href="">关于我们</a><i>|<i></span>
                    <span><a href="">网站协议</a><i>|<i></span>
                    <span><a href="">地图</a><i>|<i></span>
                    <span><a href="">联系我们</a></span>
                </div>
                <div class="copyright">
                    <p>&copy; 1999-2019 江苏乐知网络技术有限公司江苏知之为计算机有限公司 北京创新乐知信息技术有限公司版权所有</p>
                </div>
            </div>

        </div>



    </div>



</div>


<script type="text/javascript">
    window.onload = function () {
        // offset() 获得div1当前的位置，左上角坐标(x,y)
        var pos =  $('#floatLeft').offset();
        var posa =  $('#floatLeft2').offset();
        //滚动条滚动事件
        $(window).scroll(function () {
            if ($(this).scrollTop() > pos.top ) {
                $('#floatLeft').css('top', $(this).scrollTop() - pos.top);
            }
            else if ($(this).scrollTop() <=  pos.top ) {
                $('#floatLeft').css('top',0).css('position', 'relative');
            }

            if ($(this).scrollTop() > posa.top ) {
                $('#floatLeft2').css('top', $(this).scrollTop() - posa.top);
            }
            else if ($(this).scrollTop() <=  posa.top ) {
                $('#floatLeft2').css('top',0).css('position', 'relative');
            }
        })
    };
</script>
</body>
</html>
