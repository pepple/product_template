<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp"%>
<%@taglib prefix="u" uri="/WEB-INF/tld/userDefine.tld" %>
<html>
<head>
    <title>登陆页面113</title>
    <link rel="stylesheet" href="../css/login.css"/>
    <script src="../js/common.js"></script>
</head>
<body>
    this is login-index page.
    <img src="../image/dog.jpg"/>
    <u:userinfo userid="2" />
    ${userinfo.id},${userinfo.name},${userinfo.password}
</body>
</html>
