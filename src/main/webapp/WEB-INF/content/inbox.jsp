<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="inbox"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="inbox"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
</head>
<body>
<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-8 am-u-sm-12">
            <%--<button type="button" class="am-btn am-btn-warning am-fr am-btn-xs" style="    margin-top: 28px;">发送私信</button>--%>
            <h6>最近联系</h6>
            <hr>
        </div>
        <div class="am-u-md-4">
            <h6>发送私信</h6>
            <form action="" class="am-form am-form-horizontal am-text-xs">
                <div class="am-form-group">
                    <label for="doc-ipt-4" class="am-u-sm-2 am-form-label">发送给</label>
                    <div class="am-u-sm-10">
                        <select id="doc-ipt-4">
                            <option value="">选择好友</option>
                            <option value="option2">选项二</option>
                            <option value="option3">选项三</option>
                        </select>
                        <span class="am-form-caret"></span>
                    </div>
                </div>
                <div class="am-form-group">
                    <label for="doc-ipt-3" class="am-u-sm-2 am-form-label">内容</label>
                    <div class="am-u-sm-10">
                        <textarea class="" rows="5" id="doc-ipt-3"></textarea>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>
