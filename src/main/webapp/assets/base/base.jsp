
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="static" value="${path}/assets"></c:set>
<c:set var="basePath"
       value="${pageContext.request.scheme}://${pageContext.request.serverName }${path }"></c:set>
<c:set var="defaultjs" value="index"></c:set>
<c:set var="moduleName" value=""></c:set>
<%
    String path = request.getContextPath();
%>