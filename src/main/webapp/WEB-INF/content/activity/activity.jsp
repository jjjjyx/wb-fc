<%@ page import="jyx.model.ActivityBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="jyx.model.UserBean" %>
<%@ page import="java.util.Set" %>
<%@ page import="jyx.model.ActivityStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../../../assets/base/base.jsp" %>
<c:set var="moduleName" value="activity"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="activity"></c:set>
<html>
<head>
    <%@include file="../../../assets/base/header.jsp" %>
</head>
<body>

<div id="app">
    <%@include file="../../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content activity-wrapper" style="">
        <div class="col-md-3 am-u-md-3 am-padding-0">
            <div class="cuk-layout-aside">
                <div class="layout-menu-title am-text-lg">
                    <span>活动中心</span>
                    <i class="am-icon-plus am-fr" @click="dialogTableVisible = true" style="font-size: 14px; vertical-align: middle; margin-top: 3px;cursor: pointer"></i>
                </div>
                <ul class="cuk-menu am-list">
                    <%@include file="./a-nav.jsp" %>
                </ul>
            </div>
        </div>
        <div class="col-md-9 am-u-md-9 am-padding-0">
            <div class="cuk-layout-content">
                <header class="" style="border-bottom: 1px solid #d8dde4;flex-shrink:0; display: inline-flex;">
                    <span class="am-block" style="    margin-bottom: 15px;">${fn:length(activity_data)} 个活动</span>
                    <div style="margin-left: auto">

                    </div>
                </header>
                <div class="list-content">
                    <ul class="am-list am-list-static">
                        <%
                            UserBean userBean = (UserBean) request.getAttribute("userBean");
                            Set<ActivityBean> uas = userBean.getActivitys();
                        %>
                        <c:forEach items="${activity_data}" var="ad">
                            <%
                                ActivityBean activityBean = (ActivityBean) pageContext.getAttribute("ad");
                                Map<String, Object> si = activityBean.getSint();
                                String sex = "不限";
                                String age = "";
                                if (si!=null) {
                                    List arr = (List)si.get("sex");
                                    if (arr!=null && arr.size()>0)
                                        sex = (String) arr.get(0);
                                    arr = (List)si.get("age");
                                    if (arr!=null && arr.size()>0){
                                        age = "+"+ arr.get(0);
                                        age += " -"+ arr.get(1);
                                    }
                                }

                            %>
                            <li>
                                <i class="am-icon-fa am-icon-fw"></i>
                                <img src="${path}/assets/img/user (${ad.uid.uid % 28}).png" alt="" class="avatar am-margin-left-xs">
                                <span class="am-badge am-margin-left-sm" title="起始时间"><i class="am-icon-calendar"></i> <fmt:formatDate value="${ad.startTime}" pattern=" yyyy/MM/dd"/></a></span>
                                <span class="am-badge am-badge-success am-margin-left-sm" title="状态">${ad.status.label}</span>
                                <a href="./${ad.id}">${ad.title}</a> <span class="am-badge am-badge-danger am-margin-left-lg" title="性别限制"><%out.print(sex);%></span>
                                <span class="am-badge am-badge-warning" title="年龄限制"><%out.print(age);%></span>

                                <%

                                    out.print("<a class=\"am-fr\"");
                                    if (activityBean.getStatus()== ActivityStatus.end) {
                                        out.print(">活动结束</a>");
                                    }else if (uas.contains(activityBean)) {
                                        out.print("@click=\"unsign('");
                                        out.print(activityBean.getId());
                                        out.print("')\">取消报名</a>");
                                    }else {
                                        out.print("@click=\"sign('");
                                        out.print(activityBean.getId());
                                        out.print("')\">立即报名</a>");
                                    }
                                    if (userBean.equals(activityBean.getUid())) {
                                        out.print("<a @click=\"del('"+activityBean.getId()+"')\" class=\"am-text-danger am-fr\">删除活动</a>");
                                    }
                                %>
                                <%--<a class="am-fr" @click="sign('${ad.id}')">立即报名</a>--%>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <el-dialog title="发布新活动" :visible.sync="dialogTableVisible" width="30%">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="标题">
                <el-input v-model="form['activity.title']" ></el-input>
            </el-form-item>
            <el-form-item label="类型">
                <el-input v-model="form['activity.type']"></el-input>
            </el-form-item>
            <el-form-item label="活动地址">
                <el-input v-model="form['activity.address']" ></el-input>
            </el-form-item>
            <el-form-item label="活动时间">
                <el-date-picker
                        v-model="o_form.value4"
                        type="datetimerange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="仅限">
                <el-radio-group v-model="o_form.sex">
                    <el-radio label="">不限</el-radio>
                    <el-radio label="男">男</el-radio>
                    <el-radio label="女">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄限制">
                <el-col :span="20">
                    <el-slider v-model="o_form.age" range :min="12" :max="30" show-stops></el-slider>
                </el-col>
                <el-col :span="4">
                    <span style="padding-left: 15px">{{o_form.age}}</span>
                </el-col>
            </el-form-item>
            <el-form-item label="负责人">
                <el-input value="${user.nickname}" disabled></el-input>
            </el-form-item>
            <el-form-item label="负责人电话">
                <el-input v-model="form['activity.phone']"></el-input>
            </el-form-item>
            <el-form-item label="内容">
                <el-input v-model="form['activity.content']" type="textarea"></el-input>
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