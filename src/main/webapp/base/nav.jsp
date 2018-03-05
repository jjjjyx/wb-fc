<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="am-topbar am-topbar-inverse">
    <h1 class="am-topbar-brand">
        <a href="#">###</a>
    </h1>
    <ul class="am-topbar-right am-nav am-nav-pills am-topbar-nav">
        <li>
            <a href="#">
                <i class="am-icon-rss"></i>
            </a>
        </li>

        <el-dropdown class="user-profile" trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    <img src="../assets/img/2.png" alt="">admin <i class="am-icon-angle-down"></i>
                </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>今日任务</el-dropdown-item>
                <el-dropdown-item>已完成</el-dropdown-item>
                <el-dropdown-item>个人设置</el-dropdown-item>

                <el-dropdown-item divided><a href="${path}/sign!out">退出</a></el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </ul>
    <form class="am-topbar-form am-topbar-left am-form-inline am-topbar-right" role="search">
        <div class="am-form-group">
            <input type="text" class="am-form-field" placeholder="搜索">
        </div>
    </form>
</header>