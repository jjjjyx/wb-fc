<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="news"></c:set>
<c:set var="cssPath" value="index"></c:set>

<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>

</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-8 am-u-sm-12">
            <c:forEach items="${new_date}" var="n" varStatus="loop">
                <article class="am-g blog-entry-article">
                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                        <img src="assets/i/f${n.id%16+4}.jpg" alt="" class="am-u-sm-12">
                    </div>
                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                        <span><a href="" class="blog-color">article &nbsp;</a></span>
                        <span> @${n.author} &nbsp;</span>
                        <span><fmt:formatDate value="${n.releaseTime}" pattern="yyyy/MM/dd"/></span>
                        <h1><a href="news?id=${n.id}"> ${n.title}</a></h1>
                        <c:if test="${fn:length(n.content)>80}">
                            <p style="text-indent: 2ch">${n.content.substring(0,80)}</p>
                        </c:if>
                                <c:if test="${fn:length(n.content)<=80}">
                        <p style="text-indent: 2ch">${n.content}</p>
                            </c:if>

                        <p><a href="" class="blog-continue">continue reading</a></p>
                    </div>
                </article>
            </c:forEach>
            <%--<ul class="am-pagination">--%>
                <%--<li class="am-pagination-prev"><a href="">&laquo; Prev</a></li>--%>
                <%--<li class="am-pagination-next"><a href="">Next &raquo;</a></li>--%>
            <%--</ul>--%>
        </div>
        <%--<div class="am-u-md-4 am-u-sm-12 blog-sidebar">--%>
            <%--<div class="blog-sidebar-widget blog-bor">--%>
                <%--<h2 class="blog-text-center blog-title"><span>About ME</span></h2>--%>
                <%--<img src="assets/img/user (${user.uid % 28}).png" alt="about me" class="blog-entry-img" >--%>
                <%--<p>--%>
                    <%--我是${user.nickname}--%>
                <%--</p><p>我不想成为一个庸俗的人。十年百年后，当我们死去，质疑我们的人同样死去，后人看到的是裹足不前、原地打转的你，还是一直奔跑、走到远方的我？</p>--%>
            <%--</div>--%>

            <%--<div class="blog-clear-margin blog-sidebar-widget blog-bor am-g ">--%>
                <%--<h2 class="blog-title"><span>TAG cloud</span></h2>--%>
                <%--<div class="am-u-sm-12 blog-clear-padding">--%>
                    <%--<a href="" class="blog-tag">运动</a>--%>
                    <%--<a href="" class="blog-tag">热血</a>--%>
                    <%--<a href="" class="blog-tag">文章</a>--%>
                    <%--<a href="" class="blog-tag">足球</a>--%>

                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="blog-sidebar-widget blog-bor">--%>
                <%--<h2 class="blog-title"><span>么么哒</span></h2>--%>
                <%--<ul class="am-list">--%>
                    <%--<li><a href="#">每个人都有一个死角， 自己走不出来，别人也闯不进去。</a></li>--%>
                    <%--<li><a href="#">我把最深沉的秘密放在那里。</a></li>--%>
                    <%--<li><a href="#">你不懂我，我不怪你。</a></li>--%>
                    <%--<li><a href="#">每个人都有一道伤口， 或深或浅，盖上布，以为不存在。</a></li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
    </section>

</div>

<%@include file="../../assets/base/footer.jsp" %>
<script>
</script>
</body>
</html>

