<%@ page import="jyx.model.GroupBean" %>
<%@ page import="java.util.List" %>
<%@ page import="jyx.model.PostType" %>
<%@ page import="java.util.Map" %>
<%@ page import="jyx.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../../assets/base/base.jsp" %>
<%
    PostType postType = (PostType) request.getAttribute("mode");
    pageContext.setAttribute("moduleName", postType.name());
    String moduleName = (String) pageContext.getAttribute("moduleName");

%>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="home"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
</head>
<body>
<div id="app" class="WB_miniblog">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-md-8 am-u-sm-12">
            <div class="send_activity">
                <div class="title_area am-cf">
                    <div class="title am-cf">
                        <p class="W_swficon ficon_swtxt"><em class="spac1">有什么新</em><em class="spac2">鲜</em><em
                                class="spac3">事想告诉大家</em><em class="spac4">?</em></p>
                    </div>
                    <div class="num S_txt2 am-fr am-text-xs" v-if="form['post.content']">
                        已经输入{{form['post.content'].length}}个字
                    </div>
                </div>
                <div class="input">
                    <textarea name="" id="" class="W_input" v-model="form['post.content']"
                              style="height: 68px; margin: 0px; padding: 0px; border-style: none; border-width: 0px; font-size: 14px; word-wrap: break-word; line-height: 18px; overflow: hidden; outline: none;"></textarea>
                </div>
                <div class="func_area am-cf">
                    <div class="func">
                        <%--判断类型显示不同--%>
                        <%
                            if (postType==PostType.group) { // 如果是圈子 圈子有 类型
                                out.print("<el-dropdown @command=\"handleCommand\">");
                                out.print("<span class=\"el-dropdown-link\">");
                                out.print("{{curr_title?curr_title:\"类别\"}}<i class=\"el-icon-arrow-down el-icon--right\"></i>");
                                out.print("</span>");
                                out.print("<el-dropdown-menu slot=\"dropdown\">");
                                List<GroupBean> list = (List<GroupBean>) request.getAttribute("group_data");
                                for (GroupBean groupBean : list) {
                                    out.print("<el-dropdown-item command=\"");
                                    out.print(groupBean.getType());
                                    out.print(":");
                                    out.print(groupBean.getTitle());
                                    out.print("\">");
                                    out.print(groupBean.getTitle());
                                    out.print("</el-dropdown-item>");
                                }
                                    out.print("</el-dropdown-menu></el-dropdown>");
                            } else if (postType==PostType.mood){ // 如果是运动动态
                                out.print("<label class=\"am-checkbox-inline\"><input type=\"checkbox\" v-model=\"private\" value=\"private\">仅自己可见</label>");
                            }
                            out.print("<input type=\"hidden\" name=\"post_type\" ref=\"post_type\" value=\"");
                            out.print(postType.name());
                            out.print("\">");
                        %>
                        <%--{{private}}--%>
                        <button class="am-btn am-btn-primary am-btn-sm" @click="release">发布</button>
                    </div>
                    <el-popover ref="popover4" placement="bottom-start" width="400" trigger="click">
                        <el-upload
                                class="upload-demo" ref="upload" action="file!upload?dir=img_upload"
                                :on-preview="handlePreview" :on-remove="handleRemove" :on-success="handleSuccess"
                                :file-list="fileList" limit="9" multiple
                                accept="image/png,image/jpeg,image/jpeg">
                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                            <div slot="tip" class="el-upload__tip">请选择图片，支持png,jpe,jpeg</div>
                        </el-upload>
                    </el-popover>
                    <el-popover ref="popover5" placement="bottom-start" width="400" trigger="click">
                        <el-upload
                                class="upload-demo" ref="upload" action="file!upload?dir=img_upload"
                                :on-preview="handlePreview" :on-remove="handleRemove" :on-success="handleSuccess"
                                :file-list="fileList" limit="1" multiple accept="video/mp4">
                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                            <div slot="tip" class="el-upload__tip">请选择视频，仅支持mp4格式</div>
                        </el-upload>
                    </el-popover>
                    <div class="kind">
                        <a href="#" v-popover:popover4><i class="am-icon-image"></i> 图片</a>
                        <a href="#" v-popover:popover5><i class="am-icon-video"></i> 视频</a>
                    </div>
                </div>
            </div>

            <div class="homefeed">
                <%
                    if (postType==PostType.group) {
                        String curr = request.getParameter("_");
                        if (curr == null) {
                            curr = "all";
                        }
                        out.print("<div class=\"WB_tab_a\"><div class=\"tab_box am-cf\"><ul class=\"tab am-fl\">");
                        List<GroupBean> list = (List<GroupBean>) request.getAttribute("group_data");
                        out.print("<li class=" + (curr.equals("all") ? "curr" : "") + "><a href=\"" + moduleName + "?_=all\">全部</a></li>");
                        for (GroupBean groupBean : list) {
                            if (curr.equalsIgnoreCase(groupBean.getType())) {
                                out.print("<li class=curr><a href=\"" + moduleName + "?_=" + groupBean.getType() + "\">" + groupBean.getTitle() + "</a></li>");
                            } else {
                                out.print("<li><a href=\"" + moduleName + "?_=" + groupBean.getType() + "\">" + groupBean.getTitle() + "</a></li>");
                            }
                        }
                        out.print("</ul></div></div>");
                    }
                %>
                <div class="WB_feed">
                    <c:forEach items="${post_data}" var="n">
                        <%
                            Map n = (Map) pageContext.getAttribute("n");
                        %>
                        <div class="WB_cardwrap">
                            <div class="WB_feed_detail am-cf">
                                <div class="WB_face">
                                    <div class="face">
                                        <a href="people?uid=${n['uid'].uid}">
                                            <img src="assets/img/user (${n['uid'].uid % 28}).png" alt="" class="W_face_radius">
                                        </a>
                                    </div>
                                </div>
                                <div class="WB_detail">
                                    <div class="WB_info">
                                        <a href="people?uid=${n['uid'].uid}" class="W_f14 W_fb S_txt1">${n['uid'].username}</a>
                                        <c:if test="${n['group_type']=='private'}">
                                            <span class="am-badge am-radius">私密</span>
                                        </c:if>
                                        <c:if test="${n['uid'].uid==user.uid}">
                                            <span class="am-fr" @click="delPost('${n['id']}')"><i class="am-icon-close"></i></span>
                                            <%
                                                if (postType==PostType.mood){
                                                    if ("private".equalsIgnoreCase((String) n.get("group_type"))) {
                                                        out.print("<a class=\"am-fr\" style=\"margin-right: 5px;\" @click=\"public('"+n.get("id")+"')\">公开</a>");
                                                    }else {
                                                        out.print("<a class=\"am-fr\" style=\"margin-right: 5px;\" @click=\"priv('"+n.get("id")+"')\">转私密</a>");
                                                    }
                                                }
                                            %>

                                        </c:if>
                                    </div>
                                    <div class="WB_from S_txt2">
                                        <a href="#" class="S_txt2" style="font-size: 12px"><fmt:formatDate
                                                value="${n['releaseTime']}" pattern="yyyy-MM-dd HH:mm"/></a>
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
                                                        <img src="img_upload/${img}" alt="">
                                                    </li>
                                                </c:forEach>
                                                <c:forEach items="${n['mp4s']}" var="mp4">
                                                    <li class="WB_video  S_bg1 WB_video_mini WB_video_h5">
                                                        <div class="WB_h5video">
                                                            <video alt="" controls="controls">
                                                                <source src="img_upload/${mp4}" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'>
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
                                                <%--<span class="pos">--%>
                                                    <span class="line S_line1 ">
                                                        <c:if test="${!n['isStar']}">
                                                            <i class="am-icon-star"></i> <em>收藏</em>
                                                        </c:if>
                                                        <c:if test="${n['isStar']}">
                                                            <i class="am-icon-star S_spetxt"></i> <em>已收藏</em>
                                                        </c:if>
                                                    </span>
                                                <%--</span>--%>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="S_txt2" @click="comment('${n['comment_id']}','${n['is_comment']}')">
                                                <span class="pos">
                                                    <span class="line S_line1">
                                                        <i class="am-icon-commenting"></i> <em>${n['comment_num']}</em>

                                                    </span>
                                                </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="S_txt2" @click="thumbs_up('${n['id']}', ${n['isThumbs_up']})">
                                                <span class="pos">
                                                    <span class="line S_line1">
                                                         <c:if test="${!n['isThumbs_up']}">
                                                            <i class="am-icon-thumbs-up"></i> <em>${n['thumbs_up']}</em>
                                                         </c:if>
                                                        <c:if test="${n['isThumbs_up']}">
                                                            <i class="am-icon-thumbs-up S_spetxt"></i> <em>${n['thumbs_up']}</em>
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
    </section>
    <el-dialog title="评论" :visible.sync="dialogTableVisible">
        <fc-comment :comment-id="currPostId" cur="${user.uid}" :position="comment_position"></fc-comment>
    </el-dialog>

</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>

