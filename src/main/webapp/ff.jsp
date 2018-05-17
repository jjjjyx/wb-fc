<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="./assets/base/base.jsp" %>
<c:set var="moduleName" value="ff"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="ff"></c:set>
<html>
<head>
    <%@include file="./assets/base/header.jsp" %>

</head>
<body>
<%@include file="./assets/base/nav.jsp" %>
<div id="app">
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff;height: 100%">
        您当前的积分为 <span>${user.integral}!</span>
    </section>
</div>
</body>
</html>
