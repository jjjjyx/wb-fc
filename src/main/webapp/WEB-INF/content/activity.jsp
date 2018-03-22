<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="activity"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="activity"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
    <style>
        ul,
        li {
            /* list-style: none; */
            padding-left: 2em !important;
        }
        .timeline-span {
            border-left: 2px solid #10D07A;
        }
    </style>
</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-12 am-u-sm-12">
            <h1 style="padding: 10px" class="am-text-center">
                <%--<button class="am-btn am-btn-primary am-btn-xl">发布新活动</button>--%>
                <%--<el-button type="primary" @click="dialogTableVisible = true">发布新活动</el-button>|--%>
                    <%--<el-button plain type="primary">我发布的活动</el-button>--%>
                    <a @click="dialogTableVisible = true" class="am-text-default am-btn am-btn-link">发布新活动</a> |
                    <a href="my-aa" class="am-text-default am-btn am-btn-link">我发布的活动</a>   |
                    <a href="my-ab" class="am-text-default am-btn am-btn-link">我参加的活动</a>
                <%--<el-button plain type="warning">我参加的活动</el-button>--%>
            </h1>
            <c:forEach var="yy" items="${activity_data}">
                <div class="timeline-year">
                    <h1>${yy.key}</h1>
                    <hr>
                    <c:forEach var="mm" items="${yy.value}">
                    <div style="padding-left: 2em">
                        <h3>${mm.key} 月</h3>
                        <hr>
                        <table style="width: 100%;">
                        <c:forEach items="${mm.value}" var="item">
                            <tr>
                                <td class="timeline-span"><fmt:formatDate value="${item['releaseTime']}" pattern="yyyy/MM/dd"/></td>
                                <td>${item['content']}</td>
                                <td>${item['title']}</td>
                                <td>${item['uid'].nickname}</td>
                                <c:if test="${item['isB']}">
                                    <td><el-button plain size="mini"  @click="unsign('${item['id']}')">取消报名</el-button></td>
                                </c:if>
                                <c:if test="${!item['isB']}">
                                    <td><el-button plain size="mini" @click="sign('${item['id']}')">报名</el-button></td>
                                </c:if>

                            </tr>


                        <%--<li class="am-cf">--%>
                            <%--<span class="am-u-sm-4 am-u-md-2 timeline-span"></span>--%>
                            <%--<span class="am-u-sm-8 am-u-md-4">${item.content}</span>--%>
                            <%--<span class="am-u-sm-3 am-u-md-2 am-hide-sm-only">${item.title}</span>--%>
                            <%--<span class="am-u-sm-3 am-u-md-2 am-hide-sm-only">${item.uid.nickname}</span>--%>
                            <%--<span class="am-u-sm-2 am-u-md-2 am-hide-sm-only"><el-button plain size="mini">报名</el-button></span>--%>
                        <%--</li>--%>
                        </c:forEach>
                        </table>
                    </div>
                    <br>
                    </c:forEach>
                </div>
            </c:forEach>
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
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>