<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="news"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="new_article"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content" style="background: #fff">
        <div class="am-u-md-8 am-u-sm-12">
            <article class="am-article blog-article-p">
                <div class="am-article-hd">
                    <h1 class="am-article-title blog-text-center">${new_date.title}</h1>
                    <p class="am-article-meta blog-text-center">
                        <span><a href="#" class="blog-color">article &nbsp;</a></span>-
                        <span><a href="#">@${new_date.author} &nbsp;</a></span>-
                        <span><a href="#"><fmt:formatDate value="${new_date.releaseTime}" pattern="yyyy/MM/dd"/></a></span>
                    </p>
                </div>
                <div class="am-article-bd">
                    <img src="assets/i/f${n.id%20+4}.jpg" alt="" class="blog-entry-img blog-article-margin">
                    <p style="text-indent: 2ch">
                        ${new_date.content}
                    </p>
                </div>
            </article>
            <div>
                <hr>
                <div class="send_activity">
                    <div class="title_area am-cf">
                        <div class="num S_txt2 am-fr am-text-xs" v-if="form['comment.content']">已经输入{{form['comment.content'].length}}个字</div>
                    </div>
                    <div class="input">
                        <textarea name="" id="" v-model="form['comment.content']" class="W_input" style="height: 68px; margin: 0px; padding: 0px; border-style: none; border-width: 0px; font-size: 14px; word-wrap: break-word; line-height: 18px; overflow: hidden; outline: none;"></textarea>
                    </div>
                    <div class="func_area am-cf">
                        <div class="func">
                            <button class="am-btn am-btn-primary am-btn-sm" @click="release('${new_date.comment_id}')">评论</button>
                        </div>
                    </div>
                </div>
                <ul class="am-comments-list am-comments-list-flip">
                    <li class="am-comment">
                        <a href="#link-to-user-home">
                            <img src="" alt="" class="am-comment-avatar" width="48" height="48"/>
                        </a>

                        <div class="am-comment-main">
                            <header class="am-comment-hd">
                                <!--<h3 class="am-comment-title">评论标题</h3>-->
                                <div class="am-comment-meta">
                                    <a href="#link-to-user" class="am-comment-author">某人</a>
                                    评论于 <time datetime="2013-07-27T04:54:29-07:00" title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-12 15:30</time>
                                </div>
                            </header>

                            <div class="am-comment-bd">
                                ...
                            </div>
                        </div>
                    </li>

                    <li class="am-comment">
                        ...
                    </li>

                    ...

                    <li class="am-comment am-comment-flip"></li>

                    <li class="am-comment am-comment-highlight"></li>
                </ul>
            </div>
        </div>
        <div class="am-u-md-4 am-u-sm-12 blog-sidebar">
            <div class="blog-sidebar-widget blog-bor">
                <h2 class="blog-text-center blog-title"><span>About ME</span></h2>
                <img src="assets/i/f14.jpg" alt="about me" class="blog-entry-img" >
                <p>
                    我是${user.nickname}
                </p><p>我不想成为一个庸俗的人。十年百年后，当我们死去，质疑我们的人同样死去，后人看到的是裹足不前、原地打转的你，还是一直奔跑、走到远方的我？</p>
            </div>
            <div class="blog-sidebar-widget blog-bor">
                <h2 class="blog-text-center blog-title"><span>Contact ME</span></h2>
                <p>
                    <a href=""><span class="am-icon-qq am-icon-fw am-primary blog-icon"></span></a>
                    <a href=""><span class="am-icon-github am-icon-fw blog-icon"></span></a>
                    <a href=""><span class="am-icon-weibo am-icon-fw blog-icon"></span></a>
                    <a href=""><span class="am-icon-reddit am-icon-fw blog-icon"></span></a>
                    <a href=""><span class="am-icon-weixin am-icon-fw blog-icon"></span></a>
                </p>
            </div>
            <div class="blog-clear-margin blog-sidebar-widget blog-bor am-g ">
                <h2 class="blog-title"><span>TAG cloud</span></h2>
                <div class="am-u-sm-12 blog-clear-padding">
                    <a href="" class="blog-tag">运动</a>
                    <a href="" class="blog-tag">热血</a>
                    <a href="" class="blog-tag">文章</a>
                    <a href="" class="blog-tag">足球</a>

                </div>
            </div>
            <div class="blog-sidebar-widget blog-bor">
                <h2 class="blog-title"><span>么么哒</span></h2>
                <ul class="am-list">
                    <li><a href="#">每个人都有一个死角， 自己走不出来，别人也闯不进去。</a></li>
                    <li><a href="#">我把最深沉的秘密放在那里。</a></li>
                    <li><a href="#">你不懂我，我不怪你。</a></li>
                    <li><a href="#">每个人都有一道伤口， 或深或浅，盖上布，以为不存在。</a></li>
                </ul>
            </div>
        </div>
    </section>

</div>

<%@include file="../../assets/base/footer.jsp" %>
<script>
</script>
</body>
</html>

