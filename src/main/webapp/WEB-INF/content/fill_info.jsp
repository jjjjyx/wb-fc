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
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-8 am-u-sm-12 am-padding-sm" >

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
                    <%--<el-radio-group  value="${user.sex}" @input="test">--%>
                        <%--<el-radio label="男" name="user.sex" border value="${user.sex}"></el-radio>--%>
                        <%--<el-radio label="女" name="user.sex" border value="${user.sex}"></el-radio>--%>
                    <%--</el-radio-group>--%>
                        <label class="am-radio-inline">
                            <input type="radio" value="男" name="user.sex"> 男
                        </label>
                        <label class="am-radio-inline">
                            <input type="radio" value="女" name="user.sex"> 女
                        </label>
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

