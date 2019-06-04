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
    <title>个人中心</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/gridex.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-gridex.min.js"></script>
    <style>
        header .top .so .sobut {
            border: 0;
            border-bottom-right-radius: 3px;
            border-top-right-radius: 3px;
            outline: none;
            height: 34px;
            margin-top: 12px;
            padding: 0 10px;
            background-color: #39F;
            font: 15px/36px 'microsoft yahei';
            float: left;
            color: #fff;
            cursor: pointer;
        }
        .userInfoBack{
            position: relative;
            width: 1200px;
            margin: 0 0 12px;
            background-color: #b3b0b0;
            height: 200px;
            box-shadow: 0 2px 6px rgba(26,26,26,.1);
            -webkit-box-shadow: 0 2px 6px rgba(26,26,26,.1);
            -moz-box-shadow: 0 2px 6px rgba(26,26,26,.1);
        }
        .userInfoBack .userInfo{
            position: absolute;
            width: 100%;
            height: 100px;
            bottom: 0;
            background: #fff;
        }
        .userInfoBack .userInfo .us .portrait{
            position: absolute;
            left: 25px;
            bottom: 40%;
            display: block;
            width: 10%;
        }
        .userInfoBack .userInfo .us .portrait img{
            width: 100%;
            border-radius: 10px;
        }
        .userInfoBack .userInfo .us .info{
            float: right;
            width: 85%;
        }
        .userInfoBack .userInfo .us .info span{
            font-size: 2.3em;
            font-weight: 600;
        }
        .userInfoBack .userInfo .wenda{
            float: right;
            width: 84%;
            margin-top: 15px;
        }
        .userInfoBack .userInfo .wenda .txt span{
            font-size: 13px;
        }
        .userInfoBack .userInfo .wenda .txt span a{
            font-size: 16px;
            color:red;
        }
        .userInfoBack .updateDetail{
            position: absolute;
            top: 50%;
            right: 0;
            width: 18%;
        }
        .userInfoBack .updateDetail .detalBtn{
            display:block;
            width:50%;
            height:37px;
            background-color:#fff;
            font:16px/37px 'microsoft yahei';
            color:#0f88eb;
            text-align:center;
            border-radius:3px;
            border:1px solid #0f88eb;
        }
        .userInfoBack .updateDetail .detalBtn:hover{
            background-color:rgba(0,132,255,.1);
        }
        .AnswerItemList {
            width: 100%;
            height: auto;
            /*overflow: visible;*/
            margin-top: 10px;
        }
        .AnswerItemList .userInfo .info {
            width: 400px;
            height: 60px;
            margin: 4px 0 0;
            float: left;
            overflow: hidden;
        }
        .AnswerItemList .userInfo .info span {
            display: block;
            width: 100%;
            float: right;
            height: 22px;
        }
        .AnswerItemList .userInfo .info .title {
            display: block;
            float: left;
            width: 100%;
            height: auto;
            font: 24px/32px 'microsoft yahei';
            color: #555;
            transition-duration: 0.2s;
        }
        /****** 选项卡 *******/
        .tab .nav-tabs{
            border: none;
            border-bottom: 2px solid #079fc9;
            margin: 0;
        }
        .tab .nav-tabs li a{
            padding: 10px 20px;
            margin: 0 10px -1px 0;
            font-size: 17px;
            font-weight: 600;
            color: #293241;
            text-transform: uppercase;
            border: 2px solid #e6e5e1;
            border-bottom: none;
            border-radius: 5px 5px 0 0;
            z-index: 1;
            position: relative;
            transition: all 0.3s ease 0s;
        }
        .tab .nav-tabs li a:hover,
        .tab .nav-tabs li.active a{
            background: #fff;
            color: #079fc9;
            border: 2px solid #079fc9;
            border-bottom-color: transparent;
        }
        .tab .nav-tabs li a:before{
            content: "";
            display: block;
            height: 2px;
            background: #fff;
            position: absolute;
            bottom: -2px;
            left: 0;
            right: 0;
            transform: scaleX(0);
            transition: all 0.3s ease-in-out 0s;
        }
        .tab .nav-tabs li.active a:before,
        .tab .nav-tabs li a:hover:before{ transform: scaleX(1); }
        .tab .tab-content{
            padding: 10px;
            font-size: 17px;
            color: #6f6f6f;
            line-height: 30px;
            letter-spacing: 1px;
            position: relative;
        }
        @media only screen and (max-width: 479px){
            .tab .nav-tabs{ border: none; }
            .tab .nav-tabs li{
                width: 100%;
                text-align: center;
                margin-bottom: 15px;
            }
            .tab .nav-tabs li a{
                margin: 0;
                border-bottom: 2px solid transparent;
            }
            .tab .nav-tabs li a:before{
                content: "";
                width: 100%;
                height: 2px;
                background: #079fc9;
                position: absolute;
                bottom: -2px;
                left: 0;
            }
        }
    </style>
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
            <!-- <a href="" class="log">登录</a>
            <a href="" class="log">注册</a> -->
            <a href="myPage.do" class="log">个人中心</a>
            <a href="logout.do" class="log">注销</a>
        </div>
        <div class="so">
            <input type="text" name="key" class="key" placeholder="请输入关键词">
            <input type="submit" class="sobut" value="搜索问题">
        </div>

    </div>
</header>

<div class="ask_main">
    <div class="userInfoBack">
        <div class="userInfo">
            <div class="us">
                <a href="" class="portrait"><img src="${ub.role}"></a>
                <div class="info">
                    <span href="myPage.do">${ub.username}</span>
                    <%--<span><dl>声望：</dl><em>2601</em></span>--%>
                </div>
            </div>
            <div class="wenda">
                <div class="txt"><span>我的积分 <a href="">${ub.score}</a> </span></div>
                <%--<div class="txt"><i class="w">问</i><span>提了 <a href="">${quesum}</a> 个问题 </span></div>--%>
                <%--<div class="txt"><i class="d">答</i><span>回答了 <a href="">${anssum}</a> 个问题</span></div>--%>
            </div>
            <%--<div class="updateDetail"><a class="detalBtn" href="">编辑个人资料</a></div>--%>
        </div>
    </div>

    <div class="amVist">


        <div class="tab" role="tabpanel">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#Section1" aria-controls="home" role="tab" data-toggle="tab">我的问题 (${quesum})</a></li>
                <li role="presentation"><a href="#Section2" aria-controls="profile" role="tab" data-toggle="tab">我的回答 (${anssum})</a></li>
                <%--<li role="presentation"><a href="#Section3" aria-controls="messages" role="tab" data-toggle="tab">Section 3</a></li>--%>
            </ul>
            <!-- Tab panes -->
            <div class="tab-content tabs">
                <div role="tabpanel" class="tab-pane fade in active" id="Section1">
                    <!---我的问题 S--->
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
                                        <a href="updateQue.do?queid=${queb.queid}">修改</a>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <%
                        Object obj1 = request.getAttribute("queTotalPage");
                        String queTotalPage = obj1.toString();
                        List<Integer> quePageList = new ArrayList<>();
                        int quePageNum =0;
                        for (int i=1;i<=Integer.parseInt(queTotalPage); i++){
                            quePageNum += 1;
                            quePageList.add(quePageNum);
                        }
                    %>
                    <div class="pageType">
                        <ul class="pagination">
                            <li class="disabled"><a href="myPage.do?quePage=1">首页</a></li>
                            <c:forEach items="<%=quePageList%>" var="pageNum">
                                <li><a id="quePageNum${pageNum}" href="myPage.do?quePage=${pageNum}">${pageNum}</a></li>
                            </c:forEach>
                            <li><a href="myPage.do?quePage=${queTotalPage}">尾页</a></li>
                            <li class='pageRemark'>共<b>${queTotalPage}</b>页 <b>${queSum}</b>条数据</li>
                        </ul>
                    </div>
                    <script>
                        $(function () {
                            var quePageNum = parseInt(GetRequest().quePage);
                            $('#quePageNum'+quePageNum).addClass("active");
                        });
                        //获取url中"?"符后的字串
                        function GetRequest() {
                            var url = location.search;
                            var theRequest = new Object();
                            if (url.indexOf("?") != -1) {
                                var str = url.substr(1);
                                strs = str.split("&");
                                for(var i = 0; i < strs.length; i ++) {
                                    theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
                                }
                            }
                            //返回的是一个对象
                            return theRequest;
                        }
                    </script>

                    <!---我的问题 S--->
                </div>
                <div role="tabpanel" class="tab-pane fade" id="Section2">
                    <!-----我的回答 S-------->
                    <div class="vistInfo">
                        <!---AnswerItemList S--->
                        <c:forEach items="${ansbs}" var="ansb">
                            <div class="AnswerItemList">
                                <div class="userInfo">
                                    <div class="info">
                                        <span><dl>回答时间：</dl><em>${ansb.time}</em></span>
                                        <a class="title" href="visitOther.do?queid=${ansb.queid}">${ansb.queTitle}</a>
                                    </div>
                                    <span class="Report" id="showSolve${ansb.ansid}"></span>
                                </div>
                                <div class="content" id="wrap${ansb.ansid}">
                                    <p>${ansb.content}</p>
                                        <%--<div class="gradient" id="gradient${ansb.ansid}"></div>--%>
                                </div>
                                    <%--<div class="read-more" id="read-more${ansb.ansid}"></div>--%>
                                <div class="fuInfo" style="position: relative;">
                                    <button id="divAgree${ansb.ansid}" onclick="agree(${ansb.ansid}, ${ub.userid})" class="Fabulous"><span>顶</span><em>${ansb.agnum}</em></button>
                                    <button id="divDisagree${ansb.ansid}" class="Fabulous" onclick="disagree(${ansb.ansid}, ${ub.userid})"><span>踩</span><em>${ansb.disagnum}</em></button>
                                    <a class="Fabulous" id="deleteBtn${ansb.ansid}" onclick="doDelete(${ansb.ansid})" style="float: right;">删除</a>
                                    <div style="float: right;">
                                        <ul class="thumbnails gridex" style="margin-bottom: 0;">
                                            <li class="span3 clearfix">
                                                <button class="Fabulous thumbnail">编辑</button>
                                                <div class="gd-expander" style="width: 100%!important;height: 0px!important;">
                                                    <!-- gd-inner optional -->
                                                    <div class="gd-inner">
                                                        <form id="form1" name="form1" method="post" action="myPage.do?ansid=${ansb.ansid}" enctype="multipart/form-data">
                                                            <textarea name="ansContent" id="editor1" style="width:99.8%;height:200px;">${ansb.content}</textarea>
                                                            <div class="button" style="width: 100%;height: 34px;">
                                                                <input type="submit" style="float: right;" class="button" value="修改回答">
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                    <%--<div class="AnswerForm" id="AnswerForm" style="height: 300px;">--%>
                                    <%--</div>--%>
                                    <!-- gd-expander required -->
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
                                var agreedAnsList = "${agreedAnsList}";
                                var disagreeAnsList = "${disagreedAnsList}";
                                var agids = agreedAnsList.split(",");
                                var disagids = disagreeAnsList.split(",");
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
                                <%--var text = "${solve}";--%>
                                <%--if(text === "已采纳")--%>
                                <%--{--%>
                                    <%--$('deleteBtn${ansb.ansid}').remove();--%>
                                <%--}--%>
                            </script>
                        </c:forEach>
                        <!---AnswerItemList E--->
                        <%
                            Object obj2 = request.getAttribute("ansTotalPage");
                            String ansTotalPage = obj2.toString();
                            List<Integer> ansPageList = new ArrayList<>();
                            int ansPageNum =0;
                            for (int i=1;i<=Integer.parseInt(ansTotalPage); i++){
                                ansPageNum += 1;
                                ansPageList.add(ansPageNum);
                            }
                        %>
                        <div class="pageType">
                            <ul class="pagination">
                                <li class="disabled"><a href="myPage.do?anPage=1">首页</a></li>
                                <c:forEach items="<%=ansPageList%>" var="pageNum">
                                    <li><a id="ansPageNum${pageNum}" href="myPage.do?anPage=${pageNum}">${pageNum}</a></li>
                                </c:forEach>
                                <li><a href="myPage.do?anPage=${ansTotalPage}">尾页</a></li>
                                <li class='pageRemark'>共<b>${ansTotalPage}</b>页 <b>${ansSum}</b>条数据</li>
                            </ul>
                        </div>
                        <script>
                            $(function () {
                                var ansPageNum = parseInt(GetRequest().anPage);
                                $('#ansPageNum'+ansPageNum).addClass("active");
                            });
                            //获取url中"?"符后的字串
                            function GetRequest() {
                                var url = location.search;
                                var theRequest = new Object();
                                if (url.indexOf("?") != -1) {
                                    var str = url.substr(1);
                                    strs = str.split("&");
                                    for(var i = 0; i < strs.length; i ++) {
                                        theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
                                    }
                                }
                                //返回的是一个对象
                                return theRequest;
                            }
                        </script>
                    </div>
                    <!-----我的回答 E-------->
                </div>
                <%--<div role="tabpanel" class="tab-pane fade" id="Section3">--%>
                    <%--<h3>Section 3</h3>--%>
                    <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce semper, magna a ultricies volutpat, mi eros viverra massa, vitae consequat nisi justo in tortor. Proin accumsan felis ac felis dapibus, non iaculis mi varius.</p>--%>
                <%--</div>--%>
            </div>
        </div>





    </div>



    <div class="amRight">

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
<script type="text/javascript" src="js/layer/layer.js"></script>
<script>
    $(function() {
        $('.gridex').gridex();
    })
</script>
<script type="text/javascript">
    function doDelete(ansid) {
        var result = layer.confirm('确定删除此回答吗？', {
                btn: ["确定", "取消"]
            },function(index){
                $.ajax({
                    type: 'post',
                    url: "deleteAns.go?ansid=" + ansid
                });
                layer.close(index);
                location.reload();
        });
    }

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
        $('#deleteBtn${solveansid}').remove();
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
