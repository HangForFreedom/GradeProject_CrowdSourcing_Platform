<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>问答</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>

<body>

<header>
    <div class="top">
        <a href="main.do" class="logo"></a>
        <a href="main.do" class="nav">首页</a>
        <a href="main.do" class="nav">全部问题</a>
        <a href="scoreQue.to" class="nav">高分悬赏</a>
        <a href="myQue.to" class="nav">我的问题</a>
        <div class="user">
            <!-- <a href="" class="log">登录</a>
            <a href="" class="log">注册</a> -->
            <a href="" class="log">个人中心</a>
            <a href="logout.do" class="log">注销</a>
        </div>
        <div class="so">
            <input type="text" name="key" class="key" placeholder="请输入关键词">
            <input type="submit" class="sobut" value="搜索答案">
        </div>

    </div>
</header>



<div class="ask_main">



    <div class="amVist">

        <div class="curNav">
            <a href="main.do">网站首页</a><span>>查看问题</span>
        </div>

        <!-----详情信息 S-------->
        <div class="vistInfo">
            <c:forEach items="${quebs}" var="queb">
                <h1><span>${queSolved}</span>${queb.title}</h1>

                <div class="fui">
                    <div class="time">${queb.time} <span>提问</span></div>
                    <div class="Appreciation"><i></i><span>${queb.score}</span></div>
                </div>

                <div class="content">${queb.content}</div>

                <div class="vice-info">
                    <%--<a class="MydaBut" id="MydaBut"><i>答</i><span>我来答</sapn></a>--%>
                    <div class="th">
                        <a href="" class="z"><span>点赞</span><em>2</em></a><i>|</i>
                        <a href="" class="z"><span>收藏</span><em>1</em></a><i>|</i>
                        <a href="" class="z"><span>举报</span></a>
                    </div>
                    <div class="hits">浏览 16094 次</div>
                </div>
            </c:forEach>
            <%--<div class="AnswerForm" id="AnswerForm">--%>
                <%--<p>&hearts; 请认真作答，如牛头不对马嘴或发布违规及广告内容，发现一律封号处理！</p>--%>
                <%--<form id="form1" name="form1" method="post" action="visitOther.do" enctype="multipart/form-data">--%>
                    <%--<textarea name="ansContent" id="editor1" style="width:99.8%;height:200px;"></textarea>--%>
                    <%--<div class="button">--%>
                        <%--<a class="sq" id="sq">​&spades; 收起</a>--%>
                        <%--<input type="submit" class="button" value="提交回答">--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>

            <div class="AnswerQuantity">
                <span>已有<em>${anssum2que}</em>个回答</span>
            </div>

            <!---AnswerItemList S--->
            <c:forEach items="${ansbs}" var="ansb">
                <div class="AnswerItemList">
                    <div class="userInfo">
                        <a href="" class="portrait"><img src="${ansb.role}"></a>
                        <div class="info">
                            <span><a href="">${ansb.username}</a></span>
                            <span><dl>回答时间：</dl><em>${ansb.time}</em></span>
                        </div>
                        <span class="Report" id="showSolve${ansb.ansid}"></span>
                    </div>
                    <div class="content" id="wrap${ansb.ansid}">
                        <p>${ansb.content}</p>
                        <%--<div class="gradient" id="gradient${ansb.ansid}"></div>--%>
                    </div>
                    <%--<div class="read-more" id="read-more${ansb.ansid}"></div>--%>
                    <div class="fuInfo">
                        <div id="divAgree${ansb.ansid}" onclick="agree(${ansb.ansid}, ${ub.userid})" class="Fabulous"><span>顶</span><em>${ansb.agnum}</em></div>
                        <div id="divDisagree${ansb.ansid}" class="Fabulous" onclick="disagree(${ansb.ansid}, ${ub.userid})"><span>踩</span><em>${ansb.disagnum}</em></div>
                        <a href="" class="Report solveBtn" id="solved${ansb.ansid}" onclick="solved(${ansb.ansid})">采纳</a>
                    </div>
                </div>
                <script>
                    if(agids.contains(String(${ansb.ansid}))){
                        $('#divAgree'+ansid).addClass('FabulousActive');
                    }
                    if(disagids.contains(String(${ansb.ansid}))){
                        $('#divDisagree'+ansid).addClass('FabulousActive');
                    }
                </script>
            </c:forEach>
            <!---AnswerItemList E--->



            <div class="pageType">
                <ul class="pagination">
                    <li class="disabled"><dl>上一页</dl></li>
                    <li class="active"><span>1</span></li>
                    <li><a href="">2</a></li>
                    <li><a href="">3</a></li>
                    <li><a href="">下一页</a></li>
                    <li class='pageRemark'>共<b>3</b>页 <b>43</b>条数据</li>
                </ul>
            </div>
        </div>
        <!-----详情信息 E-------->


        <!---itemlist S--->
        <div class="AskItemList">
            <div class="top">
                <div class="info">
                    <span style="color:#666;">2小时前&nbsp;</span><span>来自&nbsp;</span><a href="" class="uname">xiezhengyi1986</a><span>&nbsp;的提问</span>
                    <a href="" class="title">如何在dxf文件中添加新图层及在新添图层中添加实体信息？</a>
                </div>
                <div class="da">
                    <span><em>24</em><dl>已有回答</dl></span>
                </div>
            </div>
            <div class="desc">拦截所有的浏览器请求 access="ROLE_ADMIN" 只有ROLE_ADMIN角色的用才可以访问 规则角色名必须以ROLE_开头 为啥都得以ROLE_开头 还必须得大写 我试了小写role都不..</div>
            <div class="tags">
                <a href="">html</a>
                <div class="Appreciation">
                    <i></i><span>10</span>
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
        <!---itemlist E--->
    </div>



    <div class="amRight">
        <a href="questions.html" class="askBut">我有问题，我要提问！</a>
        <h2>最新公告</h2>
        <div class="titleList">
            <a href="">谁帮忙下载个文件呢，有偿，文件需要1个下载积分</a>
            <a href="">多个矩阵相乘程序的编写，我是新手</a>
        </div>

        <div class="userInfo">
            <div class="us">
                <a href="" class="portrait"><img src="${ub.role}"></a>
                <div class="info">
                    <a href="">${ub.username}</a>
                    <%--<span><dl>声望：</dl><em>2601</em></span>--%>
                </div>
            </div>
            <div class="wenda">
                <div class="txt"><i class="d">积</i><span>我还有 <a href="">${ub.score}</a> 积分</span></div>
                <div class="txt"><i class="w">问</i><span>提了 <a href="">${quesum}</a> 个问题 </span></div>
                <div class="txt"><i class="d">答</i><span>回答了 <a href="">${anssum}</a> 个问题</span></div>
            </div>
        </div>

        <h2>最新回答</h2>
        <div class="newAnswer">
            <!---item S--->
            <div class="item">
                <a href="" class="portrait"><img src="images/1.jpg"></a>
                <div class="info">
                    <a href="" class="uname">黑色幽默y</a> <dl>回答了：</dl><a href="" class="t">c# httpclient调用webapi获取json数</a>
                </div>
            </div>
            <!---item E--->
        </div>

        <div style="width:100%; height:20px;"> </div>



        <div class="floatRight" id="floatLeft2">

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

    //获取当前用户顶过的 回答id 列表
    var agreedAnsList = "${agreedAnsList}";
    var disagreeAnsList = "${disagreedAnsList}";
    var agids = agreedAnsList.split(",");
    var disagids = disagreeAnsList.split(",");
    // $(function(){
    //
    //     // 判断ansid是否在agids中，
    //     // 如果在，表示已顶过，要动态修改该回答的css未黄背景色；
    //     // todo 加入动态修改css的代码
    //     // if(agids.contains(ansid)){
    //     //
    //     // }
    //     // 如果不在，表示未顶过，采用默认的css（白背景色）即可，不用修改代码
    //
    // });

    Array.prototype.contains = function (obj) {
        var i = this.length;
        while (i--) {
            if (this[i] === obj) {
                return true;
            }
        }
        return false;
    }

    // “顶”按钮的单击事件
    function agree(ansid, userid)
    {
        var action = "agree";
        // console.log(agids.contains(String.valueOf(ansid)));
        // console.log(agids.contains(String(ansid)));
        if(agids.contains(String(ansid)))
        {//该回答已被顶过
            action = "disagree";
        }
        else
        {//该回答未被顶过
            //ignored
            $('#divAgree'+ansid).addClass('FabulousActive');
        }

        $.ajax({
            type: "post",
            url: 'agree.go?ansid=' + ansid + '&userid=' + userid + '&action=' + action
        });

        //页面刷新数据，目前先试用这个方法，但是有bug
        location.reload();
    }

    function disagree(ansid, userid)
    {
        var action = "deny";
        // console.log(agids.contains(String.valueOf(ansid)));
        // console.log(agids.contains(String(ansid)));
        if(disagids.contains(String(ansid)))
        {//该回答已被顶过
            action = "undeny";
        }
        else
        {//该回答未被顶过
            //ignored
            $('#divAgree'+ansid).addClass('FabulousActive');
        }

        $.ajax({
            type: "post",
            url: 'disagree.go?ansid=' + ansid + '&userid=' + userid + '&action=' + action
        });

        //页面刷新数据，目前先试用这个方法，但是有bug
        location.reload();
    }


    $('#AnswerForm').slideUp(0,'swing',function() {

    });
    $('#sq').click(function() {
        $('#AnswerForm').slideUp(500,'swing',function() {

        });
    });
    $('#MydaBut').click(function() {
        $('#AnswerForm').slideDown();
    });
</script>
<script type="text/javascript">
    function solved(ansid) {
        $.ajax({
            type: "post",
            url: 'answer.go?ansid=' + ansid
        });
    }
    var text = "${solve}";
    if(text === "已采纳")
    {
        $('#showSolve${solveansid}').text("${solve}");
        $('.solveBtn').remove();
    }
</script>
<script type="text/javascript">
    window.onload = function () {
        // offset() 获得div1当前的位置，左上角坐标(x,y)

        var posa =  $('#floatLeft2').offset();
        //滚动条滚动事件
        $(window).scroll(function () {


            if ($(this).scrollTop() > posa.top ) {
                $('#floatLeft2').css('top', $(this).scrollTop() - posa.top);
            }
            else if ($(this).scrollTop() <=  posa.top ) {
                $('#floatLeft2').css('top',0).css('position', 'relative');
            }
        });

    };
</script>
</body>
</html>
