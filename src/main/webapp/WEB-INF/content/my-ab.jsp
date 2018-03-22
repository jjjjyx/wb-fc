<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="my-ab"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="my-ab"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>

</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <h1 style="padding: 10px" class="am-text-center">
            <%--<button class="am-btn am-btn-primary am-btn-xl">发布新活动</button>--%>
            <%--<el-button type="primary" @click="dialogTableVisible = true">发布新活动</el-button>|--%>
            <%--<el-button plain type="primary">我发布的活动</el-button>--%>
            <a href="activity" class="am-text-default am-btn am-btn-link">发布新活动</a> |
            <a href="my-aa" class="am-text-default am-btn am-btn-link">我发布的活动</a>   |
            <a href="my-ab" class="am-text-default am-btn am-btn-link">我参加的活动</a>
            <%--<el-button plain type="warning">我参加的活动</el-button>--%>
        </h1>
        <div>
            <el-table ref="multipleTable" :data="activityList" tooltip-effect="dark" style="width: 100%">
                <el-table-column type="expand">
                    <template scope="props">
                        <h2>活动详情</h2>
                        {{props.row.content}}
                    </template>
                </el-table-column>
                <el-table-column prop="id" label="id"></el-table-column>
                <el-table-column prop="title" label="标题"></el-table-column>
                <!--<el-table-column prop="content" label="内容"></el-table-column>-->
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column prop="address" label="活动地址"></el-table-column>
                <el-table-column prop="author" label="负责人">
                    <template scope="props">
                        {{props.row.uid.nickname}}
                    </template>
                </el-table-column>
                <el-table-column prop="releaseTime" label="发布时间"></el-table-column>

            </el-table>
        </div>
    </section>
</div>
<%
    Object c = request.getAttribute("ab.data");
    out.print("<script>"+c+"</script>");
%>

<%@include file="../../assets/base/footer.jsp" %>

</body>
</html>