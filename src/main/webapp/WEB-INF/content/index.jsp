<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="index"></c:set>
<c:set var="cssPath" value="index"></c:set>
<%--<c:set var="loading" value="false"></c:set>--%>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
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
                                        <a href="#" class="am-text-truncate">${n.title}</a>
                                        <div>
                                            <time style="font-size: 13px;"><fmt:formatDate value="${n.releaseTime}" pattern="yyyy-MM-dd HH:mm"/> </time>
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
                                    <a href="#" class="am-text-truncate">${n.title}</a>
                                    <div>
                                        <time style="font-size: 13px;"><fmt:formatDate value="${n.releaseTime}" pattern="yyyy-MM-dd HH:mm"/></time>
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
                                        <span class="am-badge am-badge-success">${n.type}</span>
                                        <a href="#" class="am-text-truncate"><i class="am-icon-fa"></i> ${n.title}</a>
                                        <div>
                                            <time style="font-size: 13px;">发布时间：<fmt:formatDate value="${n.releaseTime}" pattern="yyyy-MM-dd HH:mm"/></time>
                                            <button class="am-btn am-btn-primary am-btn-xs">报名</button>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-hd">运动图片 <i class="am-icon-picture-o"></i></div>
                        <div class="am-panel-bd">
                            <div class="am-g">
                                <c:forEach items="${fc_img}" var="n">
                                <div class="am-u-sm-4">
                                    <div class="am-thumbnail">
                                        <img src="${path}/img_upload/${n}" alt=""/>
                                        <h3 class="am-thumbnail-caption">图片标题 #1</h3>
                                    </div>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-hd">材料下载 <i class="am-icon-cloud-download"></i></div>
                        <div class="am-panel-bd">
                            <ul class="am-list">
                                <c:forEach items="${fc_data}" var="n">
                                    <li>
                                        <span class="am-badge am-badge-danger" style="cursor: pointer">点击下载</span>
                                        <a href="down">${n.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 am-u-md-3">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-hd">面板标题</div>
                        <div class="am-panel-bd">
                            热门资料推荐
                        </div>
                    </div>
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-hd">积分排行榜</div>
                        <div class="am-panel-bd">
                            面板内容
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@include file="../../assets/base/footer.jsp" %>
<script>
</script>
</body>
</html>
