<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="base/base.jsp" %>
<c:set var="moduleName" value="sign"></c:set>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/x-icon" href="./favicon.ico" rel="shortcut icon"/>
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="${path}/css/amazeui.min.css" rel="stylesheet">
    <link href="${path}/css/animate.min.css" rel="stylesheet">
    <link href="${path}/assets/scss/sign.css" rel="stylesheet">

</head>
<body>
<app id="app">
    <div class="form-wrapper">
        <div class="form-panel">
            <ol class="am-breadcrumb am-breadcrumb-slash am-text-default">
                <li @click="form = 'in-form'" :class="{'am-active':form=='in-form'}">sign in</li>
                <li @click="form = 'up-form'" :class="{'am-active':form=='up-form' || form == 'info-form'}">sign up</li>
            </ol>
            <component :is="form" <c:if test="${sessionScope.errorCount>=3}">:show-code="true"</c:if>>
                <c:if test="${user!=null}">${user.email}</c:if>
                <%--<c:if test="${sessionScope.errorCount>=3}">--%>
                <%--</c:if>--%>
            </component>
            <%--<in-form></in-form>--%>
        </div>
    </div>
    <%--<div class="account">--%>
        <%--<ul>--%>
            <%--<li><p>Don't have an <a href="#">account?</a></p></li>--%>
            <%--<span>/</span>--%>
            <%--<li><p>Forgot <a href="#">password?</a></p></li>--%>
            <%--<div class="clear"></div>--%>
        <%--</ul>--%>
    <%--</div>--%>
    <%--<div class="copyright"></div>--%>
</app>
<%@include file="base/footer.jsp" %>
</body>
</html>
