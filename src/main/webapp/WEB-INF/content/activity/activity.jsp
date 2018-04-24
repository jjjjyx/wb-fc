
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../../assets/base/base.jsp" %>
<c:set var="moduleName" value="activity"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="activity"></c:set>
<html>
<head>
    <%@include file="../../../assets/base/header.jsp" %>
    <style>
        .timeline-span {
            border-left: 2px solid #10D07A;
        }
    </style>
</head>
<body>

<div id="app">
    <%@include file="../../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="">
        <div class="col-md-3 am-u-md-3 am-padding-0">
            <div class="cuk-layout-aside">
                <div class="layout-menu-title am-text-lg">
                    <span>活动中心</span>
                    <i class="am-icon-plus am-fr" style="    font-size: 14px; vertical-align: middle; margin-top: 3px;cursor: pointer"></i>
                </div>
                <ul class="cuk-menu am-list">
                    <%@include file="./a-nav.jsp" %>
                </ul>
            </div>
        </div>
        <div class="col-md-9 am-u-md-9 am-padding-0">
            <div class="cuk-layout-content">

            </div>
        </div>
    </section>
    <el-dialog title="发布新活动" :visible.sync="dialogTableVisible" width="30%">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="标题">
                <el-input v-model="form['activity.title']" ></el-input>
            </el-form-item>
            <el-form-item label="内容">
                <el-input v-model="form['activity.content']" type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="类型">
                <el-input v-model="form['activity.type']"></el-input>
            </el-form-item>
            <el-form-item label="活动地址">
                <el-input v-model="form['activity.address']" ></el-input>
            </el-form-item>
            <el-form-item label="负责人">
                <el-input value="${user.nickname}" disabled></el-input>
            </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save()">保存</el-button>
        </span>
    </el-dialog>
</div>
<%@include file="../../../assets/base/footer.jsp" %>
</body>
</html>