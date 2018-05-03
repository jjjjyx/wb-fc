<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="news"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="fill_info"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
    <style>
        .leader-board li:first-child span.am-badge {
            background-color: #dd514c;
        }

        .leader-board li:nth-child(2) span.am-badge {
            background-color: #F37B1D;
        }

        .leader-board li:nth-child(3) span.am-badge {
            background-color: #3bb4f2;
        }

        .leader-board img {
            border-radius: 50%;
            width: 30px;
            float: left;
            margin-top: 5px;
            margin-right: 10px;
        }

        .leader-board a {
            margin-left: 15px;
            /*display: inline-block !important;*/
        }

        .leader-board .am-badge {
            margin-right: 5px;
            /*list-style: beige;*/
            /*padding-left: 1em;*/
        }

        .leader-board li {
            display: list-item;
            /*list-style: inherit;*/
        }

        .list-stlt {
            list-style: inherit;
            padding-left: 1em;
        }

        .list-stlt li {
            display: list-item;
            list-style: inherit;
        }

        .img-view {
            width: 100%;
            display: flex;;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }

        .img-view img {
            max-width: 100%;
        }

    </style>
</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-8 am-u-sm-12 am-padding-sm" >
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd">基本资料</div>
                <div class="am-panel-bd">
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
                            <s:radio label="sex" name="user.sex" list="#{'男':'男','女':'女'}" value="%{#session.user.sex}" />
                            <%--<label class="am-radio-inline">--%>
                            <%--<input type="radio" value="男" name="user.sex"> 男--%>
                            <%--</label>--%>
                            <%--<label class="am-radio-inline">--%>
                            <%--<input type="radio" value="女" name="user.sex"> 女--%>
                            <%--</label>--%>
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
            </div>


        </div>
        <div class="am-u-md-4 am-u-sm-4 am-padding-sm">
            <div class="cuk-layout-aside">
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">好友积分排行榜</div>
                    <div class="am-panel-bd">
                        <ol class="am-list leader-board ">
                            <c:forEach items="${leader_board}" var="n">
                                <li>
                                    <img src="assets/img/user (${n["uid"] % 28}).png" alt="">
                                    <a href="user/${n['uid']}">${n['nickname']}
                                        <span class="am-badge am-fr"> ${n['integral']} 分</span>
                                    </a>

                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </section>

</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>

