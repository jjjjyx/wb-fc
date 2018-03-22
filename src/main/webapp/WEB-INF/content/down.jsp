<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="down"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="down"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>

</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-12 am-u-sm-12">
            <table class="am-table">
                <thead>
                    <tr>
                        <th>文件名称</th>
                        <th>文件类型</th>
                        <th>文件大小</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${files}" var="item">
                        <tr>
                            <td>${item['name']}</td>
                            <td>${item['type']}</td>
                            <td>${item['size']}</td>
                            <td>
                                <a @click="down('${item['fn']}')">下载</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>