<%@ page import="jyx.model.UserBean" %>
<%@ page import="jyx.model.InboxBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="inbox"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="inbox"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
</head>
<body>
<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-8 am-u-sm-12 am-padding-bottom-lg">
            <%--<button type="button" class="am-btn am-btn-warning am-fr am-btn-xs" style="    margin-top: 28px;">发送私信</button>--%>
            <h6>最近联系</h6>
            <hr>
            <ul class="am-comments-list am-comments-list-flip">
                <%
                    UserBean curr = (UserBean) session.getAttribute("user");
                %>
                <c:forEach items="${inboxs_data}" var="inbox">
                <%
                    InboxBean inbox = (InboxBean) pageContext.getAttribute("inbox");
                    UserBean sender = inbox.getSender();
                    UserBean receive = inbox.getReceive();
                %>
                <li class="am-comment">
                    <a href="#link-to-user-home">
                        <img :src="`${path}/assets/img/user (${inbox.sender.uid % 28}).png`" alt="" class="am-comment-avatar" width="48" height="48"/>
                    </a>
                    <div class="am-comment-main">
                        <header class="am-comment-hd">
                            <div class="am-comment-meta">
                            <%
                                if (curr.equals(sender)) {
                                    out.print("我发送给 <a href=\"user/"+receive.getUid()+"\" class=\"am-comment-author\" >"+receive.getNickname()+" :</a>");
                                } else {
                                    out.print("<a href=\"user/"+receive.getUid()+"\" class=\"am-comment-author\" >"+sender.getNickname()+":</a>");
                                }
                            %>
                            </div>
                        </header>
                        <div class="am-comment-bd" >
                            <div class="WB_text W_f14" >
                                ${inbox.content}
                            </div>
                        </div>
                        <div class="am-comment-footer">
                                ${inbox.carateTime}
                            <%
                                if (!curr.equals(sender)) {
                                    out.print("<span class=\"am-fr\" style=\"cursor: pointer\" @click=\"reply('"+sender.getUid()+"')\">回复</span>");
                                }
                            %>

                        </div>
                    </div>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="am-u-md-4">
            <h6>发送私信</h6>
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="发给" prop="desc">
                    <el-select v-model="value" placeholder="请选择" popper-class="user-result-popper" value-key="uid">
                        <el-option  v-for="item in options" :key="item.uid" :label="item.nickname" :value="item" >
                        <div class="user-item">
                            <img :src="`${path}/assets/img/user (\${item.uid % 28}).png`" alt="">
                            <div class="user-item-info">
                                <span class="am-block">{{item.nickname}}</span>
                                <span class="am-text-xs">积分：{{item.integral}} <i class="am-icon-database"></i></span>
                            </div>
                        </div>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="内容" prop="desc">
                    <el-input type="textarea" v-model="form['inbox.content']"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">立即发送</el-button>
                </el-form-item>
            </el-form>

            <%--<form action="" class="am-form am-form-horizontal am-text-xs">--%>
                <%--<div class="am-form-group">--%>
                    <%--<label for="doc-ipt-4" class="am-u-sm-2 am-form-label">发送给</label>--%>
                    <%--<div class="am-u-sm-10">--%>
                        <%--<select id="doc-ipt-4">--%>
                            <%--<option value="">选择好友</option>--%>
                            <%--<option value="option2">选项二</option>--%>
                            <%--<option value="option3">选项三</option>--%>
                        <%--</select>--%>
                        <%--<span class="am-form-caret"></span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="am-form-group">--%>
                    <%--<label for="doc-ipt-3" class="am-u-sm-2 am-form-label">内容</label>--%>
                    <%--<div class="am-u-sm-10">--%>
                        <%--<textarea class="" rows="5" id="doc-ipt-3"></textarea>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</form>--%>
        </div>
    </section>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>
