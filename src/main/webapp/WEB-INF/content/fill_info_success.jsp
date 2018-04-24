<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="news"></c:set>
<c:set var="cssPath" value="index"></c:set>

<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-md-8 am-u-sm-12">

            <h1>修改完成</h1>
            <span id="a">5</span>秒后跳转到首页 <a href="!execute">立即跳转</a>
        </div>
    </section>

</div>
<script>
    +(function  () {
        var m = 5;
        var a = document.getElementById("a");
        function next(){
            a.innerText = m--;
            if(!m) {
                location.href= "!execute"
            }
            setTimeout(next,1000)
        }
        setTimeout(next,1000)
    })()
</script>
</body>
</html>

