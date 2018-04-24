<%@ page import="jyx.model.ActivityBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="jyx.model.ActivityStatus" %>
<%@ page import="jyx.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <%
        ActivityBean activityBean = (ActivityBean) request.getAttribute("activityBean");
        UserBean userBean = (UserBean) session.getAttribute("user");
    %>
    <section class="am-g am-g-fixed blog-fixed blog-content activity-wrapper" style="">
        <div class="col-md-3 am-u-md-3 am-padding-0">
            <div class="cuk-layout-aside">
                <div class="layout-menu-title am-text-lg">
                    <span>活动中心</span>
                    <i class="am-icon-plus am-fr" @click="dialogTableVisible = true"
                       style="font-size: 14px; vertical-align: middle; margin-top: 3px;cursor: pointer"></i>
                </div>
                <ul class="cuk-menu am-list">
                    <%@include file="./a-nav.jsp" %>
                </ul>
            </div>
        </div>
        <div class="col-md-9 am-u-md-9 am-padding-0">
            <div class="cuk-layout-content">
                <header class="" style="border-bottom: 1px solid #d8dde4;flex-shrink:0; display: inline-flex;">
                    <span class="am-block" style="    margin-bottom: 15px;"><i
                            class="am-icon-fa am-icon-fw"></i> ${activityBean.title} - 活动详情 </span> <span><span
                        class="am-badge am-badge-success am-margin-left-sm"
                        title="状态">${activityBean.status.label}</span></span>
                    <div style="margin-left: auto">
                        <%
                            if (activityBean.getStatus() != ActivityStatus.end && activityBean.getUid().equals(userBean))
                                out.print("<a @click=\"end('" + activityBean.getId() + "')\" class=\"\">提前结束活动</a>");
                        %>
                    </div>
                </header>
                <div class="list-content">
                    <div class="col-md-9 am-u-md-9 am-padding-0">
                        <div class="a-info">
                            <article class="am-comment am-margin-bottom-sm"> <!-- 评论容器 -->
                                <a href="#">
                                    <div class="am-comment-avatar">
                                        <svg fill="currentColor" preserveAspectRatio="xMidYMid meet" height="1em"
                                             width="1em" viewBox="0 0 40 40" class="cuk-icon cuk-icon-align-left null"
                                             style="vertical-align: middle;">
                                            <g>
                                                <path d="m40 30v2.9q0 0.5-0.4 1t-1 0.4h-37.2q-0.6 0-1-0.4t-0.4-1v-2.9q0-0.6 0.4-1t1-0.4h37.2q0.5 0 1 0.4t0.4 1z m-8.6-8.6v2.9q0 0.6-0.4 1t-1 0.4h-28.6q-0.6 0-1-0.4t-0.4-1v-2.9q0-0.5 0.4-1t1-0.4h28.6q0.6 0 1 0.4t0.4 1z m5.7-8.5v2.8q0 0.6-0.4 1t-1 0.4h-34.3q-0.6 0-1-0.4t-0.4-1v-2.8q0-0.6 0.4-1t1-0.5h34.3q0.6 0 1 0.5t0.4 1z m-8.5-8.6v2.8q0 0.6-0.5 1t-1 0.5h-25.7q-0.6 0-1-0.5t-0.4-1v-2.8q0-0.6 0.4-1t1-0.4h25.7q0.6 0 1 0.4t0.5 1z"></path>
                                            </g>
                                        </svg>
                                    </div>
                                </a>

                                <div class="am-comment-main"> <!-- 评论内容容器 -->
                                    <header class="am-comment-hd">
                                        <!--<h3 class="am-comment-title">评论标题</h3>-->
                                        <div class="am-comment-meta"> <!-- 评论元数据 -->
                                            活动内容
                                        </div>
                                    </header>

                                    <div class="am-comment-bd">
                                        ${activityBean.content}
                                    </div> <!-- 评论内容 -->
                                </div>
                            </article>

                            <article class="am-comment am-margin-bottom-sm"> <!-- 评论容器 -->
                                <a href="#">
                                    <div class="am-comment-avatar">
                                        <svg fill="currentColor" preserveAspectRatio="xMidYMid meet" height="1em" width="1em" viewBox="0 0 40 40" class="cuk-icon cuk-icon-thumb-tack null" style="vertical-align: middle;"><g><path d="m17.7 19.3v-10q0-0.3-0.2-0.5t-0.5-0.2-0.5 0.2-0.2 0.5v10q0 0.3 0.2 0.5t0.5 0.2 0.5-0.2 0.2-0.5z m15 7.8q0 0.6-0.4 1t-1 0.5h-9.6l-1.1 10.8q-0.1 0.2-0.3 0.4t-0.4 0.2h0q-0.6 0-0.8-0.6l-1.7-10.8h-9q-0.6 0-1-0.5t-0.4-1q0-2.7 1.8-4.9t3.9-2.2v-11.4q-1.1 0-2-0.9t-0.8-2 0.8-2 2-0.8h14.3q1.2 0 2 0.8t0.9 2-0.9 2-2 0.9v11.4q2.2 0 4 2.2t1.7 4.9z"></path></g></svg>
                                    </div>
                                </a>

                                <div class="am-comment-main"> <!-- 评论内容容器 -->
                                    <header class="am-comment-hd">
                                        <!--<h3 class="am-comment-title">评论标题</h3>-->
                                        <div class="am-comment-meta"> <!-- 评论元数据 -->
                                            活动地点
                                        </div>
                                    </header>

                                    <div class="am-comment-bd">
                                        ${activityBean.address}
                                    </div> <!-- 评论内容 -->
                                </div>
                            </article>
                        </div>
                        <%-- 如果是完成的活动可以上评论，以及上传内容--%>
                        <%
                            pageContext.setAttribute("test",activityBean.getStatus() == ActivityStatus.end);
                            if (activityBean.getStatus() == ActivityStatus.end)
//                               ${activityBean.comment_id}
                                out.print("<fc-comment :comment-id=\"'" + activityBean.getComment_id() + "'\" style=\"margin-bottom: 30px;\" position=\"hide\"></fc-comment>");
                        %>
                        <c:if test="${test}">
                            <div class="send_activity">
                                <div class="title_area am-cf">
                                    <div class="num S_txt2 am-fr am-text-xs" v-if="form['comment.content']">
                                        已经输入 {{form['comment.content'].length}}个字
                                    </div>
                                </div>
                                <div class="input">
                                <textarea name="" id="" class="W_input" v-model="form['comment.content']"
                                          style="height: 68px; margin: 0px; padding: 0px; border-style: none; border-width: 0px; font-size: 14px; word-wrap: break-word; line-height: 18px; overflow: hidden; outline: none;"></textarea>
                                </div>
                                <div class="func_area am-cf">
                                    <div class="func">
                                            <%--{{private}}--%>
                                        <button class="am-btn am-btn-primary am-btn-sm" @click="release('${activityBean.comment_id}')">发布</button>
                                    </div>
                                    <el-popover ref="popover4" placement="bottom-start" width="400" trigger="click">
                                        <el-upload
                                                class="upload-demo" ref="upload" action="${path}/file!upload?dir=dist"
                                                :on-preview="handlePreview" :on-remove="handleRemove"
                                                :on-success="handleSuccess"
                                                :file-list="fileList" limit="9" multiple
                                                accept="image/png,image/jpeg,image/jpeg,video/mp4,video/x-msvideo">
                                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                            <div slot="tip" class="el-upload__tip">请选择图片，支持png,jpe,jpeg</div>
                                        </el-upload>
                                    </el-popover>
                                    <el-popover ref="popover5" placement="bottom-start" width="400" trigger="click">
                                        <el-upload
                                                class="upload-demo" ref="upload" action="${path}/file!upload?dir=dist"
                                                :on-preview="handlePreview" :on-remove="handleRemove"
                                                :on-success="handleSuccess"
                                                :file-list="fileList" limit="1" multiple accept="video/mp4">
                                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                            <div slot="tip" class="el-upload__tip">请选择视频，仅支持mp4格式</div>
                                        </el-upload>
                                    </el-popover>
                                    <div class="kind">
                                        <a href="#" v-popover:popover4><i class="am-icon-image"></i> 图片</a>
                                        <a href="#" v-popover:popover5><i class="am-icon-video"></i> 视频</a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="col-md-3 am-u-md-3 am-padding-0 am-text-xs">
                        <%
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
                            }%>
                        <b>负责人：</b>${activityBean.author}<br>
                        <b>负责人联系方式：</b>${activityBean.phone}<br>
                        <b>活动标题：</b>${activityBean.title}<br>
                        <b>活动时间：</b><br>
                           ${activityBean.startTime} -<br>
                           ${activityBean.endTime}<br>
                        <b>当前状态：</b> ${activityBean.status.label} <br>
                        <b>活动性别限制:</b> <%=sex %>  <br>
                        <b>年龄限制:</b> <%=age%>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <el-dialog title="发布新活动" :visible.sync="dialogTableVisible" width="30%">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="标题">
                <el-input v-model="form['activity.title']"></el-input>
            </el-form-item>
            <el-form-item label="类型">
                <el-input v-model="form['activity.type']"></el-input>
            </el-form-item>
            <el-form-item label="活动地址">
                <el-input v-model="form['activity.address']"></el-input>
            </el-form-item>
            <el-form-item label="活动时间">
                <el-date-picker v-model="o_form.value4" type="datetimerange" range-separator="至"
                                start-placeholder="开始日期" end-placeholder="结束日期">
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
            <el-form-item label="内容">
                <el-input v-model="form['activity.content']" type="textarea"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save()">保 存</el-button>
        </span>
    </el-dialog>
</div>
<%@include file="../../../assets/base/footer.jsp" %>
</body>
</html>