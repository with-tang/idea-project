<%--
  Created by IntelliJ IDEA.
  User: tang
  Date: 2017/4/18
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html";charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>微信授权登录测试</title>
  </head>
  <body style="font-size: 15px;text-align: center">
  <div>登陆成功</div>
  <div>用户昵称 ${info.nickname}</div>
  <div>用户头像：<img style="vertical-align:top" width="100" height="100" src="${info.headimgurl}"></div>
  </body>
</html>
