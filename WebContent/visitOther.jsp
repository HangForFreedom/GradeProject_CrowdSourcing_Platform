<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
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
                    // todo 浏览他人主页
                    <div class="time">${queb.time} <span><a href="" style="font:16px/24px 'microsoft yahei';color:#09F;">${queb.username}</a> 提问</span></div>
                    <div class="Appreciation"><i></i><span>${queb.score}</span></div>
                </div>

                <div class="content">${queb.content}</div>

                <div class="vice-info">
                    <a class="MydaBut" id="MydaBut"><i>答</i><span>我来答</sapn></a>
                    <div class="hits">浏览 16094 次</div>
                </div>
            </c:forEach>
            <div class="AnswerForm" id="AnswerForm">
                <p>&hearts; 请认真作答，如牛头不对马嘴或发布违规及广告内容，发现一律封号处理！</p>
                <form id="form1" name="form1" method="post" action="visitOther.do" enctype="multipart/form-data">
                    <textarea name="ansContent" id="editor1" style="width:99.8%;height:200px;"></textarea>
                    <div class="button">
                        <a class="sq" id="sq">​&spades; 收起</a>
                        <input type="submit" class="button" value="提交回答">
                    </div>
                </form>
            </div>

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
                        <button id="divAgree${ansb.ansid}" onclick="agree(${ansb.ansid}, ${ub.userid})" class="Fabulous"><span>顶</span><em>${ansb.agnum}</em></button>
                        <button id="divDisagree${ansb.ansid}" class="Fabulous" onclick="disagree(${ansb.ansid}, ${ub.userid})"><span>踩</span><em>${ansb.disagnum}</em></button>

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
                    }
                    var agreedAnsList = "${agreedAnsList}";
                    var disagreeAnsList = "${disagreedAnsList}";
                    var answerAnsList = "${answerAnsList}";
                    var agids = agreedAnsList.split(",");
                    var disagids = disagreeAnsList.split(",");
                    var answerids = answerAnsList.split(",");
                    if(agids.contains(String(${ansb.ansid}))){
                        $('#divAgree${ansb.ansid}').addClass("FabulousActive");
                        $('#divDisagree${ansb.ansid}').prop("disabled", true);
                        $('#divDisagree${ansb.ansid}').css("cursor", "text");
                    }
                    if(disagids.contains(String(${ansb.ansid}))){
                        $('#divDisagree${ansb.ansid}').addClass("FabulousActive");
                        $('#divAgree${ansb.ansid}').prop("disabled", true);
                        $('#divAgree${ansb.ansid}').css("cursor", "text");
                    }
                    if (answerids.contains(String(${ansb.ansid}))){
                        $('#MydaBut').remove();
                    }
                </script>
            </c:forEach>
            <!---AnswerItemList E--->
        </div>
        <!-----详情信息 E-------->

    </div>



    <div class="amRight">
        <a href="raise.do" class="askBut">我有问题，我要提问！</a>
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

        <div style="width:100%; height:20px;"> </div>



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
                </div>s
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
