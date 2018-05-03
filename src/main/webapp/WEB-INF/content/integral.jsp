<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="down"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="down"></c:set>
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
    <section class="am-g am-g-fixed blog-fixed blog-content activity-wrapper" style="">

        <div class="col-md-9 am-u-md-9 am-padding-0">
            <div class="cuk-layout-content" style="    overflow: auto;">
            <table class="am-table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>说明</th>
                    <th>变动</th>
                    <th>时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${integral_data}" var="i">
                <tr>
                    <td>${i.id}</td>
                    <td>${i.remark}</td>
                    <td>${i.integral}</td>
                    <td>${i.updateDate}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
        <div class="col-md-3 am-u-md-3 am-padding-0">
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