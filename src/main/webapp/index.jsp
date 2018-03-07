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
12312
    <div id="app">

    </div>

    <%@include file="./assets/base/footer.jsp" %>
<script>
    var xhr = new XMLHttpRequest();
    xhr.open("GET","!test")
    xhr.send()
</script>
</body>
</html>

