<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../assets/base/base.jsp" %>
<c:set var="moduleName" value="admin"></c:set>
<c:set var="cssPath" value="admin-index"></c:set>
<%--<c:set var="loading" value="false"></c:set>--%>
<html>
<head>
    <%@include file="../assets/base/header.jsp" %>
</head>
<body>
    <div id="app">
        {{name}}
    </div>
    <%@include file="../assets/base/footer.jsp" %>
</body>
</html>

