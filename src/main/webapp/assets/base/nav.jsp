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
                            {"!execute.action","index","首页"},
                            {"news","news","资讯"},
                            {"mood","mood","运动动态"},
                            {"activity","activity","活动"},
                            {"group?_=all","group","圈子"},
                            {"down","down","材料下载"},
                    };
//                    out.print(pageContext.getAttribute("moduleName"));
                    String active = (String) pageContext.getAttribute("moduleName");
                    if(active==null) {
                        active = "index";
                    }
                    for (String[] strings : nav) {
                        if(strings[1].equals(active)){
                            out.print("<li class=\"am-active\"><a href=\""+strings[0]+"\">"+strings[2]+"</a></li>");
                        } else {
                            out.print("<li><a href=\""+strings[0]+"\">"+strings[2]+"</a></li>");
                        }
                    }
                %>
            </ul>

            <el-select style="margin-top: 8px;"v-model="search_value" filterable remote reserve-keyword placeholder="搜索用户" popper-class="user-result-popper"
                       :remote-method="remoteMethod" :loading="search_loading" size="small">
                <el-option  v-for="item in search_result" :key="item.uid" :label="item.nickname" :value="item.username">
                    <div class="user-item">
                        <img :src="`assets/img/user (\${item.uid % 28}).png`" alt="">
                        <div class="user-item-info">
                            <span class="am-badge am-fr am-text-xs" :class="{'am-badge-secondary':item.is_f}">{{item.is_f?'取消':''}}关注</span>
                            <span class="am-block">{{item.nickname}}</span>
                            <span class="am-text-xs">积分：{{item.integral}} <i class="am-icon-database"></i></span>
                        </div>
                    </div>
                </el-option>
            </el-select>
            <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right">
                <li ><a href="inbox"><i class="am-icon-comments"></i></a></li>
                <li ><a href="people?uid=${user.uid}"><img src="assets/img/user (${user.uid % 28}).png" style="    width: 28px; border-radius: 50%; margin-right: 5px;" alt=""><span>${user.username}</span></a></li>
                <li><a href="${path}/ff.jsp"><i class="am-icon-database"></i> <span>${user.integral}</span></a></li>
                <c:if test="${user.role==100}">
                    <li ><a href="admin/"><i class="am-icon-gear"></i></a></li>
                </c:if>
                <li><a href="${path}/sign!out"><i class="am-icon-sign-out"></i> </a></li>
            </ul>

            <%--<div class="am-topbar-right">--%>
            <%--<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">登录</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</header>