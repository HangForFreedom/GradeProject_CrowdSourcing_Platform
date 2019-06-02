##### 2019.4.15

1. web.xml添加`<servlet>`时候报错
	1. 首先创建一个servlet的java文件，再修改web.xml

> 标准支头信息引用错误

```
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">

//替换成

<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
```

**servlet**

```java
public class UpdatePwServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }
}
```

---
#### 发现问题点

+ **急需前台页面修改：**
    - [x] 注册成功时进行用户提示 (未成功)
    - [x] 验证码功能考虑
    - 系统中所有链接添加
    - 删除平台上没用的信息：标签、版权信息等
    - [x] 用户积分的默认值增加
    - [x] 修改排列方式，根据用户赞同数增加
+ **待解决的功问题：**
    - [x] 分类功能（数据库中表需要修改）
    - [x] 搜索功能
    - 用户信息修改功能
    - 问题和答案删除和修改功能
    - 代码重新整理
+ **答辩注意的地方**
    - 概念需要清楚
    - 流程顺序不能乱
    - 演示两个角色使用两个浏览器