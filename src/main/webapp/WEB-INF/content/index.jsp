<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="jyx.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="index"></c:set>
<c:set var="cssPath" value="index"></c:set>
<%--<c:set var="loading" value="false"></c:set>--%>
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
<%--<c:if test="${user.role==100}">--%>
<%--<a href="./admin/">管理员界面</a>--%>
<%--</c:if>--%>
<div id="app">

    <%@include file="../../assets/base/nav.jsp" %>
    <section>
        <div class="am-g am-g-fixed blog-fixed">
            <div class="col-md-3 am-u-md-3">
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">资讯 <i class="am-icon-globe"></i></div>
                    <div class="am-panel-bd" style="min-height: 180px">
                        <ul class="am-list">
                            <c:forEach items="${news}" var="n">
                                <li>
                                    <a href="news?id=${n.id}" class="am-text-truncate">${n.title}</a>
                                    <div>
                                        <time style="font-size: 13px;"><fmt:formatDate value="${n.releaseTime}"
                                                                                       pattern="yyyy-MM-dd HH:mm"/></time>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">运动知识 <i class="am-icon-bicycle"></i></div>
                    <div class="am-panel-bd">
                        <ul class="am-list">
                            <c:forEach items="${lore}" var="n">
                                <li>
                                    <%--@click="sb($event,'<c:out value="${n.title}"/>')"--%>
                                    <%--data-content="<c:out value="${n.content}"/>"--%>
                                    <a href="lore?id=${n.id}" class="am-text-truncate">${n.title}</a>
                                    <div>
                                        <time style="font-size: 13px;"><fmt:formatDate value="${n.releaseTime}"
                                                                                       pattern="yyyy-MM-dd HH:mm"/></time>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6 am-u-md-6">
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">活动通知 <i class="am-icon-bullhorn"></i></div>
                    <div class="am-panel-bd">
                        <ul class="am-list">
                            <c:forEach items="${recent_activity}" var="n">
                                <li>

                                    <a href="activity" class="am-text-truncate"><i class="am-icon-fa"></i> ${n.title}
                                        <span class="am-badge am-badge-success am-fr">${n.type}</span>
                                    </a>
                                    <div>
                                        <time class="am-text-xs am-vertical-align-middle">发布时间：<fmt:formatDate
                                                value="${n.releaseTime}" pattern="yyyy-MM-dd HH:mm"/></time>
                                        <span class="am-btn am-btn-link am-btn-xs am-padding-0"
                                              @click="acSsign('${n.id}')">报名</span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">运动图片 <i class="am-icon-picture-o"></i></div>
                    <div class="am-panel-bd">
                        <ul class="am-gallery am-avg-sm-2 am-avg-md-4 am-gallery-default">
                            <c:forEach items="${fc_img}" var="n">
                                <li>
                                    <div class="am-gallery-item" @click="viewImg('${path}/img_upload/${n.fn}')">
                                        <img src="${path}/img_upload/${n.fn}" alt=""/>
                                        <h3 class="am-gallery-title">${n.name}</h3>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">材料下载 <i class="am-icon-cloud-download"></i></div>
                    <div class="am-panel-bd">
                        <ul class="am-list">
                            <c:forEach items="${fc_data}" var="n">
                                <li>
                                    <a href="down">${n.name}
                                        <span class="am-badge am-badge-danger am-fr" style="cursor: pointer">点击下载</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-3 am-u-md-3">
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">热门资料下载</div>
                    <div class="am-panel-bd">
                        <ul class="am-list list-stlt">
                            <c:forEach items="${hot_data}" var="n">
                                <li>
                                        <%--<span class="am-badge am-badge-danger" style="cursor: pointer">点击下载</span>--%>
                                    <a href="down">${n.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="am-panel am-panel-default">
                    <div class="am-panel-hd">积分排行榜</div>
                    <div class="am-panel-bd">
                        <ol class="am-list leader-board ">
                            <c:forEach items="${leader_board}" var="n">
                                <li>
                                    <img src="assets/img/user (${n["uid"] % 28}).png" alt="">
                                    <a href="user/${n['uid']}">${n['nickname']}
                                        <span class="am-badge am-fr"> ${n['integral']} 分</span>
                                        <c:if test="${n['is_f']}">
                                            <span class="am-badge am-fr" @click="ung('${n['uid']}')">已关注</span>
                                        </c:if>
                                        <c:if test="${!n['is_f']}">
                                            <span class="am-badge am-fr" @click="g('${n['uid']}')">关注</span>
                                        </c:if>
                                    </a>

                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <el-dialog
            title="查看图片"
            :visible.sync="dialogVisible"
            width="60%">
        <div class="img-view">
            <img :src="imgSrc" alt="">
        </div>

    </el-dialog>
</div>

<%@include file="../../assets/base/footer.jsp" %>
<script>
</script>
</body>
</html>

<%--<%--%>
<%--List<Map> leader_board = (List<Map>) request.getAttribute("leader_board");--%>
<%--for (Map map : leader_board) {--%>
<%--Integer uid = (Integer) map.get("uid");--%>
<%--Object nickname = map.get("nickname");--%>
<%--Object integral = map.get("integral");--%>
<%--out.print("<li><img src=\"assets/img/user (");--%>
<%--out.print(uid % 28);--%>
<%--out.print(").png\" alt=\"avatar\"><a href=\"user?uid=");--%>
<%--out.print(uid);--%>
<%--out.print("\">");--%>
<%--out.print(nickname);--%>
<%--out.print("<span class=\"am-badge am-fr\">");--%>
<%--out.print(map.get("integral"));--%>
<%--out.print("分</span>");--%>
<%--Boolean is_f = (Boolean) map.get("is_f");--%>
<%--if(is_f!=null && is_f) {--%>
<%--out.print(" <span  class=\"am-badge am-fr\">已关注</span>");--%>
<%--}else {--%>
<%--out.print("<span  class=\"am-badge am-fr\" @click=\"g('");--%>
<%--out.print(${n['uid']});--%>
<%--out.print("')\">关注</span>");--%>


<%--}--%>
<%--out.print("");--%>
<%--out.print("");--%>

<%--}--%>
<%--%>--%>