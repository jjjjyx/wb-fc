<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="defaultjs" value="sign"></c:set>
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
    <link href="${path}/assets/css/amazeui.min.css" rel="stylesheet">
    <link href="${path}/assets/css/animate.min.css" rel="stylesheet">
    <link href="${path}/assets/css/app.css" rel="stylesheet">
</head>
<body class="theme-black">
<div class="am-g tpl-g " id="app" >
    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-logo">
            </div>
            <h2 style="text-align: center;color: #fff">登录-运动交友系统</h2>

            <form class="am-form tpl-form-line-form" @submit.prevent="validateBeforeSubmit">
                <div class="am-form-group">
                    <input type="text" v-model="username" placeholder="输入你的账号"
                           v-validate="'required|min:3'" maxlength="30"
                           data-vv-delay="1000" name="username" class="am-form-field">
                    <span v-show="errors.has('username')" class="am-text-danger">{{ errors.first('username') }}</span>
                </div>

                <div class="am-form-group">
                    <input type="password"
                           v-model="password"
                           name="password"
                           placeholder="输入密码"
                           maxlength="18"
                           :class="{'am-form-error':errors.has('password')}"
                           v-validate="'required|alpha_num|max:18'"
                           class="am-form-field">
                    <span v-show="errors.has('password')" class="am-text-danger">{{ errors.first('password') }}</span>
                </div>
                <%--<div class="am-form-group tpl-login-remember-me">--%>
                    <%--<div class="checkbox"><label> <input type="checkbox" v-model="keep" > 记住十万年 </label></div>--%>
                <%--</div>--%>
                <div class="am-form-group">
                    <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交</button>
                </div>
                <div class="am-form-group">
                    <a href="./sign!signup" class="am-text-xs">没有账号？去注册</a>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>
