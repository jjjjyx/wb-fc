<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./assets/base/base.jsp" %>
<c:set var="moduleName" value="js/index"></c:set>
<c:set var="cssPath" value="index"></c:set>
<%--<c:set var="loading" value="false"></c:set>--%>
<html>
<head>
    <%@include file="./assets/base/header.jsp" %>
</head>
<body>
<c:if test="${user.role==100}">
    <a href="./admin/">管理员界面</a>
</c:if>
    <div id="app">

    </div>

    <%@include file="./assets/base/footer.jsp" %>
<script>
</script>
</body>
</html>

