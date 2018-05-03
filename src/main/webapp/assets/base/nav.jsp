<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="am-topbar">
    <div class="am-container am-cf blog-fixed">
        <h1 class="am-topbar-brand">
            <a href="#">运动交友系统</a>
        </h1>
        <%--<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>--%>
        <%--<div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">--%>
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <%
                    String[][] nav = {
                            {"index","index","首页"},
                            {"news","news","资讯"},
                            {"group?_=all","group","运动动态"},
                            {"activity/all","activity","活动"},
                            {"mood","mood","圈子"},
                            {"down","down","材料下载"},
                    };
//                    out.print(pageContext.getAttribute("moduleName"));
                    String active = (String) pageContext.getAttribute("moduleName");
                    if(active==null) {
                        active = "index";
                    }
                    for (String[] strings : nav) {
                        if(strings[1].equals(active)){
                            out.print("<li class=\"am-active\"><a href=\""+path+"/"+strings[0]+"\">"+strings[2]+"</a></li>");
                        } else {
                            out.print("<li><a href=\""+path+"/"+strings[0]+"\">"+strings[2]+"</a></li>");
                        }
                    }
                %>
            </ul>

            <el-select style="margin-top: 8px;"v-model="search_value" filterable remote reserve-keyword placeholder="搜索用户" popper-class="user-result-popper"
                       :remote-method="remoteMethod" :loading="search_loading" size="small">
                <el-option  v-for="item in search_result" :key="item.uid" :label="item.nickname" :value="item.username">
                    <div class="user-item">
                        <img :src="`${path}/assets/img/user (\${item.uid % 28}).png`" alt="">
                        <div class="user-item-info">
                            <span class="am-badge am-fr am-text-xs" :class="{'am-badge-secondary':item.is_f}" @click="nav_follow($event,item)">{{item.is_f?'取消':''}}关注</span>
                            <span class="am-block">{{item.nickname}}</span>
                            <span class="am-text-xs">积分：{{item.integral}} <i class="am-icon-database"></i></span>
                        </div>
                    </div>
                </el-option>
            </el-select>
        <el-popover
                ref="popoveruser"
                popper-class=""
                placement="top-start"
                width="200"
                trigger="click"
                >
            <%--<ul class="am-dropdown-content">--%>
                <li><a href="${path}/fill">个人资料</a></li>
                <li><a href="${path}/changepass">修改密码</a></li>
            <%--</ul>--%>
        </el-popover>
            <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right">
                <%--${inbox_msg}--%>
                <li ><a href="inbox"><i class="am-icon-comments am-text-md"></i><c:if test="${inbox_msg>0}"><span class="am-badge am-badge-success am-round item-feed-badge">${inbox_msg}</span></c:if></a></li>
                <li >
                    <%----%>
                    <a href="javascript:void(0);" v-popover:popoveruser>
                        <img src="${path}/assets/img/user (${user.uid % 28}).png" style="    width: 28px; border-radius: 50%; margin-right: 5px;" alt="">
                        <span>${user.nickname}&nbsp;</span>
                    </a>
                </li>
                <li><a href="${path}/integral"><i class="am-icon-database"></i> <span>${user.integral}</span></a></li>
                <c:if test="${user.role==100}">
                    <li ><a href="${path}/admin/"><i class="am-icon-gear"></i></a></li>
                </c:if>
                <li><a href="${path}/sign/out"><i class="am-icon-sign-out"></i> </a></li>
            </ul>

            <%--<div class="am-topbar-right">--%>
            <%--<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">登录</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</header>