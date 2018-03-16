<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="news"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="fill_info"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-md-8 am-u-sm-12">

            <el-form :model="form" ref="ruleForm" label-width="100px" class="demo-ruleForm" action="fill" method="post">
                <el-form-item label="账号">
                    <el-input value="${user.username}" disabled name="user.username"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input  value="${user.nickname}" name="user.nickname"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input  value="${user.email}" name="user.email"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group  value="${user.sex}">
                        <el-radio label="男" name="user.sex"></el-radio>
                        <el-radio label="女" name="user.sex"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="喜好">
                    <el-input  value="${user.love}" name="user.love"></el-input>
                </el-form-item>
                <el-form-item label="所在城市">
                    <el-input  value="${user.city}" name="user.city"></el-input>
                </el-form-item>
                <el-form-item>
                    <button href="#" class="am-btn am-btn-primary" type="submit">提交</button>
                </el-form-item>
            </el-form>
        </div>
    </section>

</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>

