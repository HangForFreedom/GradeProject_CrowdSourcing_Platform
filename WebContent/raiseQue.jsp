<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发布提问</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="css/tagsinput/jquery.tagsinput.css" />
    <script type="text/javascript" src="css/tagsinput/jquery.tagsinput.js"></script>
    <script type="text/javascript">
        //添加标签
        $(function() {
            //$('#tags').tagsInput({width:'auto'});
            //$('#tags').importTags('foo,bar,baz');
            $('#tags').importTags('');
            $('#tags').tagsInput({
                height:'auto', //设置高度
                width:'auto',  //设置宽度
                interactive:true, //是否允许添加标签，false为阻止
                //'onAddTag':callback_function, //增加标签的回调函数
                //'onRemoveTag':callback_function, //删除标签的回调函数
                //'onChange' : callback_function, //改变一个标签时的回调函数
                removeWithBackspace : true, //是否允许使用退格键删除前面的标签，false为阻止
                minChars : 0, //每个标签的小最字符
                maxChars : 20, //每个标签的最大字符，如果不设置或者为0，就是无限大
                placeholderColor : '#666666', //设置defaultText的颜色
                onChange: function(elem, elem_tags)
                {
                    var languages = ['php','ruby','javascript'];
                    $('.tag', elem_tags).each(function()
                    {
                        if($(this).text().search(new RegExp('\\b(' + languages.join('|') + ')\\b')) >= 0)
                            $(this).css('background-color', 'yellow');
                    });
                }
            });
        });
    </script>
</head>

<body>

<header>
    <div class="top">
        <a href="main.do" class="logo">千言万语</a>
        <a href="main.do" class="nav">首页</a>
        <a href="main.do" class="nav">全部问题</a>
        <a href="scoreQue.to" class="nav">高分悬赏</a>
        <a href="" class="nav">我的问题</a>
        <div class="user">
            <%--<a href="" class="log">登录</a>--%>
            <%--<a href="" class="log">注册</a>--%>
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



    <div class="amVist">

        <div class="curNav">
            <a href="main.do">网站首页</a><span>></span><a>发布提问</a>
        </div>

        <!--发布提问 S--->
        <div class="AnswerQuestions">
            <form id="form1" name="form1" method="post" action="raise.do" enctype="multipart/form-data">

                <div class="text">
                    <input type="text" name="queTitle" class="input" placeholder="标题：一句话描述清楚问题（最多可输入40个中文字）" required/>
                </div>

                <div class="text">
                    <select name="queClasses" id="">
                        <%--<option value="">请选择分类</option>--%>
                        <%--<option value="">社会民生</option>--%>
                        <c:forEach items="${classbs}" var="classb">
                            <option value="${classb.classid}">${classb.className}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="text">
                    <textarea name="queContent" id="editor" style="width:99.8%;height:300px;" placeholder="清楚的描述问题细节，让回答员清楚的了解原因（请勿发布违规内容）"></textarea>
                </div>
                <%--<div class="text">--%>
                    <%--<h2>添加标签(<span>最多添加5个标签</span>)</h2>--%>
                    <%--<input id="tags" type="text" class="tags" value="PHP,JS"  placeholder="输入后按回车添加标签">--%>
                <%--</div>--%>

                <div class="xuans">
                    <span> 悬赏</span>
                    <select name="queScore" id="select">
                        <option value="0">0</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                        <option value="40">40</option>
                        <option value="50">50</option>
                        <option value="60">60</option>
                        <option value="70">70</option>
                    </select>
                    <span style="color:#999">积分</span>
                    <span class="warning">${msg}</span>
                </div>

                <div class="text">
                    <h2><span>悬赏会提高问题的曝光度和获取答案的速度.</span> 注意：提问被删悬赏不退</h2>
                </div>

                <div class="tijiao">
                    <input id="send" type="submit" class="button" value="确定提问">
                </div>
                <div class="text">
                    <h3><span>提交表示您已经同意本站</span><a href="">协议</a></h3>
                </div>

            </form>
        </div>
        <!--发布提问 E--->
    </div>

    <div class="amRight" id="floatLeft2">
        <div class="userInfo">
            <div class="us">
                <a href="" class="portrait"><img src="${ub.role}"></a>
                <div class="info">
                    <a href="myPage.do">${ub.username}</a>
                    <%--<span><dl>声望：</dl><em>2601</em></span>--%>
                </div>
            </div>
            <div class="wenda">
                <div class="txt"><i class="d">积</i><span>我还有 <a href="">${ub.score}</a> 积分</span></div>
                <div class="txt"><i class="w">问</i><span>提了 <a href="">${quesum}</a> 个问题 </span></div>
                <div class="txt"><i class="d">答</i><span>回答了 <a href="">${anssum}</a> 个问题</span></div>
            </div>
        </div>
    </div>


</div>

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
        })
    };
</script>
</body>
</html>
