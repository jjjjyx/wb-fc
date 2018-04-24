<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="user"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="user-info"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>

</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>

    <section class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-md-12 am-u-sm-12 am-padding-0 WB_frame am-margin-bottom-sm">
            <div class="Pl_Official_Headerv6__1">
                <div class="PCD_header">
                    <div class="pf_wrap">
                        <div class="cover_wrap banner_transition" style="background-image: url(//img.t.sinajs.cn/t5/skin/public/profile_cover/029.jpg);">

                        </div>
                        <div class="shadow  S_shadow">
                            <div class="pf_photo">
                                <p class="photo_wrap">
                                    <img src="${path}/assets/img/user (${userData['uid'] % 28}).png" alt="" class="photo">
                                </p>
                            </div>
                            <div class="pf_username">
                                <h1 class="username">${userData['nickname']}</h1>
                                <span class="icon_bed">
                                    ${useData['sex']}
                                    <c:if test="${useData['sex']=='男'}">
                                        <i class="am-icon-mars W_icon icon_pf_female"></i>
                                    </c:if>
                                    <c:if test="${useData['sex']=='女'}">
                                        <i class="am-icon-venus W_icon icon_pf_female"></i>
                                    </c:if>
                                </span>
                            </div>
                            <div class="pf_intro">
                                所在城市 ：${userData['city']}  喜好：${userData['love']}
                            </div>
                            <div class="pf_opt">
                                <div class="opt_box am-cf">
                                    <%
                                        Map<String,Object> b = (Map<String, Object>) request.getAttribute("userData");
//                                        System.out.println(b.get("is_f"));
                                        Boolean is = (Boolean) b.get("is_f");
                                        if(is!=null && is) {
                                            out.print("<el-button type=\"primary\" @click=\"ung('"+b.get("uid")+"')\">取消关注</el-button>");
                                        }else {
                                            out.print("<el-button type=\"primary\" @click=\"g('"+b.get("uid")+"')\">关注</el-button>");
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-u-md-8 am-u-sm-12">
            <div class="homefeed">
                <div class="WB_feed">
                    <c:forEach items="${post_data}" var="n">
                        <div class="WB_cardwrap">
                            <div class="WB_feed_detail am-cf">
                                <div class="WB_face">
                                    <div class="face">
                                        <a href="#">
                                            <img src="${path}/assets/img/user (${n['uid'].uid % 28}).png" alt="" class="W_face_radius">
                                        </a>
                                    </div>
                                </div>
                                <div class="WB_detail">
                                    <div class="WB_info">
                                        <a href="#" class="W_f14 W_fb S_txt1">${n['uid'].nickname}</a>
                                    </div>
                                    <div class="WB_from S_txt2">
                                        <a href="#" class="S_txt2" style="font-size: 12px"><fmt:formatDate value="${n['releaseTime']}" pattern="yyyy-MM-dd HH:mm"/></a>
                                    </div>
                                    <div class="WB_text W_f14">
                                            ${n['content']}
                                    </div>
                                    <div class="WB_media_wrap am-cf">

                                    </div>
                                    <div class="WB_media_wrap am-cf">
                                        <div class="media_box">
                                            <ul class="WB_media_a_mn WB_media_a_m${n['media.length']} am-cf">
                                                <c:forEach items="${n['imgs']}" var="img">
                                                    <li class="WB_pic li_1 S_bg1 S_line2 bigcursor li_n_mix_w">
                                                        <img src="${path}/img_upload/${img}" alt="">
                                                    </li>
                                                </c:forEach>
                                                    <c:forEach items="${n['mp4s']}" var="mp4">
                                                    <li class="WB_video  S_bg1 WB_video_mini WB_video_h5">
                                                    <div class="WB_h5video">
                                                    <video alt="" controls="controls">
                                                    <source src="${path}/img_upload/${mp4}" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'>
                                                    </video>
                                                    </div>
                                                    </li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="WB_feed_handle">
                                <div class="WB_handle">
                                    <ul class="WB_row_line WB_row_r4 S_line2">
                                        <ul class="WB_row_line WB_row_r4 clearfix S_line2">
                                            <li>
                                                <a class="S_txt2" href="javascript:void(0);" @click="star('${n['id']}')">
                                                <span class="pos">
                                                    <span class="line S_line1 ">
                                                        <c:if test="${!n['isStar']}">
                                                            <span><i class="am-icon-star " ></i> <em>收藏</em></span>
                                                        </c:if>
                                                        <c:if test="${n['isStar']}">
                                                            <span><i class="am-icon-star S_spetxt" ></i> <em>已收藏</em></span>
                                                        </c:if>
                                                    </span>
                                                </span>
                                                </a>
                                            </li>
                                                <%--<li>--%>
                                                <%--<a class="S_txt2" href="javascript:void(0);">--%>
                                                <%--<span class="pos">--%>
                                                <%--<span class="line S_line1">--%>
                                                <%--<span><i class="am-icon-share-square"></i> <em>60</em>--%>
                                                <%--</span>--%>
                                                <%--</span>--%>
                                                <%--</span>--%>
                                                <%--</a>--%>
                                                <%--</li>--%>
                                            <li>
                                                <a class="S_txt2" href="javascript:void(0);" @click="comment('${n['comment_id']}')">
                                                <span class="pos">
                                                    <span class="line S_line1">
                                                        <span><i class="am-icon-commenting"></i> <em>${n['comment_num']}</em>
                                                        </span>
                                                    </span>
                                                </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="S_txt2" href="javascript:void(0);" @click="thumbs_up('${n['id']}',${n['isThumbs_up']})">
                                                <span class="pos">
                                                    <span class="line S_line1">
                                                         <c:if test="${!n['isThumbs_up']}">
                                                             <span>投票 <em>${n['thumbs_up']}</em></span>
                                                         </c:if>
                                                        <c:if test="${n['isThumbs_up']}">
                                                            <span>已投票 <em>${n['thumbs_up']}</em></span>
                                                        </c:if>
                                                    </span>
                                                </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="am-u-md-4 am-u-sm-12">
            <%--${userData}11--%>
            <%--${userDate['isF']}222--%>
        </div>

    </section>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>