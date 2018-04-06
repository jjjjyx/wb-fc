<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../assets/base/base.jsp" %>
<c:set var="moduleName" value="admin"></c:set>
<c:set var="cssPath" value="admin-index"></c:set>
<%--<c:set var="loading" value="false"></c:set>--%>
<html>
<head>
    <%@include file="../assets/base/header.jsp" %>

</head>
<body class="theme-white">
<div class="am-g tpl-g" id="app">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;">
                运动交友系统-后台管理
            </a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎你, <span> ${user.username}</span> </a>
                    </li>

                    <%--<!-- 新邮件 -->--%>
                    <%--<li class="am-dropdown tpl-dropdown" data-am-dropdown>--%>
                        <%--<a href="javascript:;" class="am-dropdown-toggle tpl-dropdown-toggle" data-am-dropdown-toggle>--%>
                            <%--<i class="am-icon-envelope"></i>--%>
                            <%--<span class="am-badge am-badge-success am-round item-feed-badge">4</span>--%>
                        <%--</a>--%>
                        <%--<!-- 弹出列表 -->--%>
                        <%--<ul class="am-dropdown-content tpl-dropdown-content">--%>
                            <%--<li class="tpl-dropdown-menu-messages">--%>
                                <%--<a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">--%>
                                    <%--<div class="menu-messages-ico">--%>
                                        <%--<img src="assets/img/user04.png" alt="">--%>
                                    <%--</div>--%>
                                    <%--<div class="menu-messages-time">--%>
                                        <%--3小时前--%>
                                    <%--</div>--%>
                                    <%--<div class="menu-messages-content">--%>
                                        <%--<div class="menu-messages-content-title">--%>
                                            <%--<i class="am-icon-circle-o am-text-success"></i>--%>
                                            <%--<span>夕风色</span>--%>
                                        <%--</div>--%>
                                        <%--<div class="am-text-truncate"> Amaze UI 的诞生，依托于 GitHub 及其他技术社区上一些优秀的资源；Amaze UI 的成长，则离不开用户的支持。 </div>--%>
                                        <%--<div class="menu-messages-content-time">2016-09-21 下午 16:40</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>

                            <%--<li class="tpl-dropdown-menu-messages">--%>
                                <%--<a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">--%>
                                    <%--<div class="menu-messages-ico">--%>
                                        <%--<img src="assets/img/user02.png" alt="">--%>
                                    <%--</div>--%>
                                    <%--<div class="menu-messages-time">--%>
                                        <%--5天前--%>
                                    <%--</div>--%>
                                    <%--<div class="menu-messages-content">--%>
                                        <%--<div class="menu-messages-content-title">--%>
                                            <%--<i class="am-icon-circle-o am-text-warning"></i>--%>
                                            <%--<span>禁言小张</span>--%>
                                        <%--</div>--%>
                                        <%--<div class="am-text-truncate"> 为了能最准确的传达所描述的问题， 建议你在反馈时附上演示，方便我们理解。 </div>--%>
                                        <%--<div class="menu-messages-content-time">2016-09-16 上午 09:23</div>--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li class="tpl-dropdown-menu-messages">--%>
                                <%--<a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">--%>
                                    <%--<i class="am-icon-circle-o"></i> 进入列表…--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>

                    <%--<!-- 新提示 -->--%>
                    <%--<li class="am-dropdown" data-am-dropdown>--%>
                        <%--<a href="javascript:;" class="am-dropdown-toggle" data-am-dropdown-toggle>--%>
                            <%--<i class="am-icon-bell"></i>--%>
                            <%--<span class="am-badge am-badge-warning am-round item-feed-badge">5</span>--%>
                        <%--</a>--%>

                        <%--<!-- 弹出列表 -->--%>
                        <%--<ul class="am-dropdown-content tpl-dropdown-content">--%>
                            <%--<li class="tpl-dropdown-menu-notifications">--%>
                                <%--<a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">--%>
                                    <%--<div class="tpl-dropdown-menu-notifications-title">--%>
                                        <%--<i class="am-icon-line-chart"></i>--%>
                                        <%--<span> 有6笔新的销售订单</span>--%>
                                    <%--</div>--%>
                                    <%--<div class="tpl-dropdown-menu-notifications-time">--%>
                                        <%--12分钟前--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li class="tpl-dropdown-menu-notifications">--%>
                                <%--<a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">--%>
                                    <%--<div class="tpl-dropdown-menu-notifications-title">--%>
                                        <%--<i class="am-icon-star"></i>--%>
                                        <%--<span> 有3个来自人事部的消息</span>--%>
                                    <%--</div>--%>
                                    <%--<div class="tpl-dropdown-menu-notifications-time">--%>
                                        <%--30分钟前--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li class="tpl-dropdown-menu-notifications">--%>
                                <%--<a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">--%>
                                    <%--<div class="tpl-dropdown-menu-notifications-title">--%>
                                        <%--<i class="am-icon-folder-o"></i>--%>
                                        <%--<span> 上午开会记录存档</span>--%>
                                    <%--</div>--%>
                                    <%--<div class="tpl-dropdown-menu-notifications-time">--%>
                                        <%--1天前--%>
                                    <%--</div>--%>
                                <%--</a>--%>
                            <%--</li>--%>


                            <%--<li class="tpl-dropdown-menu-notifications">--%>
                                <%--<a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">--%>
                                    <%--<i class="am-icon-bell"></i> 进入列表…--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>

                    <!-- 退出 -->
                    <li class="am-text-sm">
                        <a href="../sign!out">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>

    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img src="${path}/assets/img/user (${user.uid%28}).png" alt="">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              ${user.username}
          </span>
                <%--<a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>--%>
            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-heading">Function <span class="sidebar-nav-heading-info"> 功能</span></li>
            <li class="sidebar-nav-link">

                <router-link to="/" tag="a"> <i class="am-icon-home sidebar-nav-link-logo"></i> 用户管理</router-link>
            </li>
            <li class="sidebar-nav-link">
                <router-link to="/news" tag="a"><i class="am-icon-table sidebar-nav-link-logo"></i> 资讯管理</router-link>
            </li>
            <li class="sidebar-nav-link">
                <router-link to="/group" tag="a"><i class="am-icon-calendar sidebar-nav-link-logo"></i> 圈子管理</router-link>
            </li>
            <li class="sidebar-nav-link">
                <router-link to="/activity" tag="a"><i class="am-icon-wpforms sidebar-nav-link-logo"></i> 活动管理</router-link>
            </li>
            <li class="sidebar-nav-link">
                <router-link to="/lore" tag="a"><i class="am-icon-wpforms sidebar-nav-link-logo"></i> 运动知识管理</router-link>
            </li>
            <li class="sidebar-nav-link">
                <router-link to="/post" tag="a"><i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 动态管理</router-link>
            </li>
            <li class="sidebar-nav-link">
                <router-link to="/data_m" tag="a"><i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 材料管理</router-link>
            </li>
            <li class="sidebar-nav-link">
                <router-link to="/img" tag="a"><i class="am-icon-wpforms sidebar-nav-link-logo"></i> 运动图片管理</router-link>
            </li>

        </ul>
    </div>


    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="container-fluid am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-9">
                    <div class="page-header-heading"><span class="am-icon-home page-header-heading-icon"></span> 运动交友系统-管理 </div>
                    <p class="page-header-description">运动能够达到健美的目的，不断美化身体外貌。消耗身体多余的脂肪。紧实身体肌肉，美化身体线条。增强体内新陈代谢，排出体内毒素，自然是精力充肺，面色红润有光泽。冶情操。舒缓身心，经常参加体育运动锻炼的人，能够充分享受在体育运动中流汗带来的幸福感。在运动中，全身的血液循环不断加快，能够尽情地释放自身的压力，这样身心能够得到前所未有的放松。</p>
                </div>
                <div class="am-u-lg-3 tpl-index-settings-button">
                    <%--<button type="button" class="page-header-button"><span class="am-icon-paint-brush"></span> 设置</button>--%>
                </div>
            </div>
        </div>
        <router-view class="main row-content am-cf"></router-view>
    </div>
</div>
</div>
    <%@include file="../assets/base/footer.jsp" %>
<%
    Object c = request.getAttribute("admin.data");
    out.print("<script>"+c+"</script>");
%>
</body>
</html>

