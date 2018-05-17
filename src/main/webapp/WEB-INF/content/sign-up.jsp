<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="defaultjs" value="up"></c:set>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>注册</title>
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
<div class="am-g tpl-g " id="app">
    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-title">注册用户</div>
            <span class="tpl-login-content-info">
                  创建一个新的用户
              </span>
            <form class="am-form tpl-form-line-form" @submit.prevent="validateBeforeSubmit">
                <%--<div class="am-form-group">--%>
                <%--<input type="text" class="tpl-form-input" id="user-name" placeholder="邮箱">--%>
                <%--</div>--%>

                <div class="am-form-group">
                    <input type="text" v-model="username" placeholder="输入你的账号"
                           v-validate="'required|min:3'" maxlength="30"
                           data-vv-delay="1000" name="username" class="am-form-field">
                    <span v-show="errors.has('username')" class="am-text-danger">{{ errors.first('username') }}</span>
                </div>

                <div class="am-form-group">

                    <input type="password" class="am-form-field" placeholder="设置一个密码吧" name="password"
                           v-model="password"
                           v-validate="'required|alpha_num|min:6|max:18'">
                    <span v-show="errors.has('password')" class="am-text-danger">{{ errors.first('password') }}</span>
                </div>

                <div class="am-form-group">
                    <input type="password" class="am-form-field" placeholder="确认您的输入" name="c_p" v-model="c_p"
                           v-validate="'required|confirmed:password'">
                    <span v-show="errors.has('c_p')" class="am-text-danger">{{ errors.first('c_p') }}</span>
                </div>

               <!--  <div class="am-form-group tpl-login-remember-me">
                    <input id="remember-me" type="checkbox">
                    <label for="remember-me">

                        我已阅读并同意 <a href="javascript:;">《用户注册协议》</a>
                    </label>

                </div> -->
                <div class="am-form-group">
                    <button type="submit"
                            class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交
                    </button>
                </div>
                    <div class="am-form-group">
                        <a href="./sign" class="am-text-xs">已有账号</a>
                    </div>
            </form>
        </div>
    </div>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>
