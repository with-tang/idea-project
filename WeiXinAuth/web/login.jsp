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
    <title>login</title>
  </head>
  <body style="font-size: 15px;text-align: center">
  <form action="/wxCallBack" method="post">
  account:<input type="text" name="account"><br><br>
    password:<input type="password" name="password"><br>
    <input type="hidden" name="openid" value="${openid}"><br>
    <input type="submit" value="登陆并绑定"><br>
  </form>
  </body>
</html>
