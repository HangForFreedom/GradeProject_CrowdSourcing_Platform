
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>搜索问题</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
</head>

<body>

<header>
    <div class="top">
        <a href="main.do" class="logo">千言万语</a>
        <a href="main.do" class="nav">首页</a>
        <a href="main.do" class="nav">全部问题</a>
        <a href="scoreQue.to" class="nav">高分悬赏</a>
        <a href="myQue.to" class="nav">我的问题</a>
        <div class="user">
            <a href="myPage.do" class="log">个人中心</a>
            <a href="logout.do" class="log">注销</a>
        </div>
        <div class="so">
            <form action="main.do" method="post" accept-charset="UTF-8">
                <input type="text" name="key" class="key" placeholder="请输入关键词">
                <input type="submit" class="sobut" value="搜索问题">
            </form>
        </div>

    </div>
</header>



<div class="ask_main">

    <div class="amLeft" id="floatLeft">
        <h2>全部分类</h2>
        <ul>
            <c:forEach items="${classbs}" var="classb">
                <li><a href="classQue.to?classId=${classb.classid}">${classb.className}</a></li>
            </c:forEach>
        </ul>
    </div>

    <div class="amIn">
        <!---itemlist S--->
        <c:forEach items="${quebs}" var="queb">
            <div class="AskItemList">
                <div class="top">
                    <div class="info">
                        <span style="color:#666;">${queb.time}&nbsp;</span><span>来自&nbsp;</span><a href="otherPage.do?username=${queb.username}" class="uname">${queb.username}</a><span>&nbsp;的提问</span>
                        <a href="visitOther.do?queid=${queb.queid}" class="title">${queb.title}</a>
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
                    <a href="classQue.to?classId=${queb.classid}">${queb.className}</a>
                    <div class="Appreciation">
                        <i></i><span>${queb.score}</span>
                    </div>
                    <div class="share_bar_con">
                        <span id="updateQue${queb.queid}">

                        </span>
                    </div>
                </div>
            </div>
            <script>
                Array.prototype.contains = function (obj) {
                    var i = this.length;
                    while (i--) {
                        if (this[i] === obj) {
                            return true;
                        }
                    }
                    return false;
                };
                var questionQueList = "${questionQueList}";
                var queids = questionQueList.split(",");
                if (queids.contains(String(${queb.queid}))){
                    $('#updateQue${queb.queid}').append('<a href="updateQue.do?queid=${queb.queid}">修改</a>');
                }
            </script>
        </c:forEach>

    </div>

    <div class="amRight">
        <a href="raise.do" class="askBut">我有问题，我要提问！</a>

        <div class="userInfo">
            <div class="us">
                <a href="" class="portrait"><img src="${ub.role}"></a>
                <div class="info">
                    <a href="myPage.do">${ub.username}</a>
                </div>
            </div>
            <div class="wenda">
                <div class="txt"><i class="d">积</i><span>我还有 <a href="">${ub.score}</a> 积分</span></div>
                <div class="txt"><i class="w">问</i><span>提了 <a href="">${quesum}</a> 个问题 </span></div>
                <div class="txt"><i class="d">答</i><span>回答了 <a href="">${anssum}</a> 个问题</span></div>
            </div>
        </div>

        <%--<h2>最新回答</h2>--%>
        <%--<div class="newAnswer">--%>
            <%--<!---item S--->--%>
            <%--<div class="item">--%>
                <%--<a href="" class="portrait"><img src="images/1.jpg"></a>--%>
                <%--<div class="info">--%>
                    <%--<a href="" class="uname">黑色幽默y</a> <dl>回答了：</dl><a href="" class="t">c# httpclient调用webapi获取json数</a>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<!---item E--->--%>
        <%--</div>--%>

        <div class="floatRight" id="floatLeft2">
            <div class="footer">
                <h2><span>联系我们</span></h2>
                <div class="qrc">
                    <span><img src="img/ewm.png"><dl>微信客服</dl></span>
                    <span><img src="img/ewm.png"><dl>QQ客服</dl></span>
                </div>
                <div class="qq">
                    <span>客服QQ：951308338</span>
                    <span>联系电话：17853556210</span>
                    <span>E-mail：hang6210@qq.com</span>
                </div>
                <div class="nlink">
                    <span><a href="">关于我们</a><i>|<i></span>
                    <span><a href="">网站协议</a><i>|<i></span>
                    <span><a href="">地图</a><i>|<i></span>
                    <span><a href="">联系我们</a></span>
                </div>
                <div class="copyright">
                    <p>&copy; 2019  Hang</p>
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
