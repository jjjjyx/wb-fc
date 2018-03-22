<%@ page import="jyx.model.GroupBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="home"></c:set>
<%
    Boolean b = (Boolean) request.getAttribute("hide_send");
    if(b!=null && b){
        pageContext.setAttribute("moduleName","group");
    }
%>

<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="home"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>
</head>
<body>
<%
    List<GroupBean> list= (List<GroupBean>) request.getAttribute("group_data");
    String moduleName = (String) pageContext.getAttribute("moduleName");
    String curr = request.getParameter("_");
    if(curr==null) {
        curr = "all";
    }

%>
<div id="app" class="WB_miniblog">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-md-8 am-u-sm-12">
            <c:if test="${!hide_send}">
                <div class="send_activity">
                    <div class="title_area am-cf">
                        <div class="title am-cf">
                            <p class="W_swficon ficon_swtxt"><em class="spac1">有什么新</em><em class="spac2">鲜</em><em
                                    class="spac3">事想告诉大家</em><em class="spac4">?</em></p>
                        </div>
                        <div class="num S_txt2 am-fr am-text-xs" v-if="form['post.content']">已经输入{{form['post.content'].length}}个字</div>
                    </div>
                    <div class="input">
                        <textarea name="" id="" class="W_input" v-model="form['post.content']"
                                  style="height: 68px; margin: 0px; padding: 0px; border-style: none; border-width: 0px; font-size: 14px; word-wrap: break-word; line-height: 18px; overflow: hidden; outline: none;"></textarea>
                    </div>
                    <div class="func_area am-cf">
                        <div class="func">
                            <el-dropdown @command="handleCommand">
                              <span class="el-dropdown-link">
                                {{curr_title?curr_title:"类别"}}<i class="el-icon-arrow-down el-icon--right"></i>
                              </span>
                                <el-dropdown-menu slot="dropdown">
                                    <c:forEach items="${group_data}" var="d">
                                        <el-dropdown-item command="${d.type}:${d.title}">${d.title}</el-dropdown-item>
                                    </c:forEach>
                                </el-dropdown-menu>
                            </el-dropdown>
                            <button class="am-btn am-btn-primary am-btn-sm" @click="release">发布</button>
                        </div>
                        <el-popover
                                ref="popover4"
                                placement="bottom-start"
                                width="400"
                                trigger="click">
                            <el-upload
                                    class="upload-demo" ref="upload" action="file!upload?dir=dist" :on-preview="handlePreview" :on-remove="handleRemove" :on-success="handleSuccess" :file-list="fileList" limit="9" multiple accept="image/png,image/jpeg,image/jpeg,video/mp4,video/x-msvideo">
                                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                <div slot="tip" class="el-upload__tip">请选择图片，支持png,jpe,jpeg</div>
                            </el-upload>
                        </el-popover>
                        <el-popover
                                ref="popover5"
                                placement="bottom-start"
                                width="400"
                                trigger="click">
                            <el-upload
                                    class="upload-demo" ref="upload" action="file!upload?dir=dist" :on-preview="handlePreview" :on-remove="handleRemove" :on-success="handleSuccess" :file-list="fileList" limit="1" multiple accept="video/mp4">
                                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                <div slot="tip" class="el-upload__tip">请选择视频，仅支持mp4格式</div>
                            </el-upload>
                        </el-popover>
                        <div class="kind" >

                            <a href="#" v-popover:popover4><i class="am-icon-image"></i> 图片</a>
                            <a href="#" v-popover:popover5><i class="am-icon-video"></i> 视频</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="homefeed">
                <div class="WB_tab_a">
                    <div class="tab_box am-cf">
                        <ul class="tab am-fl">
                            <%
                                out.print("<li class="+(curr.equals("all")?"curr":"")+"><a href=\""+moduleName+"?_=all\">全部</a></li>");
                                for (GroupBean groupBean : list) {
                                    if(curr.equalsIgnoreCase(groupBean.getType())){
                                        out.print("<li class=curr><a href=\""+moduleName+"?_="+groupBean.getType()+"\">"+groupBean.getTitle()+"</a></li>");
                                    } else {
                                        out.print("<li><a href=\""+moduleName+"?_="+groupBean.getType()+"\">"+groupBean.getTitle()+"</a></li>");
                                    }
                                }
                            %>
                        </ul>
                    </div>
                </div>
                <div class="WB_feed">
                    <c:forEach items="${post_data}" var="n">
                    <div class="WB_cardwrap">
                        <div class="WB_feed_detail am-cf">
                            <div class="WB_face">
                                <div class="face">
                                    <a href="#">
                                        <img src="assets/img/user (${n['uid'].uid % 28}).png" alt="" class="W_face_radius">
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
                                                <img src="dist/${img}" alt="">
                                            </li>
                                        </c:forEach>
                                        <%--<c:forEach items="${n['mp4s']}" var="mp4">--%>
                                            <%--<li class="WB_video  S_bg1 WB_video_mini WB_video_h5">--%>
                                                <%--<div class="WB_h5video">--%>
                                                    <%--<video alt="" controls="controls">--%>
                                                        <%--<source src="dist/${mp4}" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'>--%>
                                                    <%--</video>--%>
                                                <%--</div>--%>
                                            <%--</li>--%>
                                        <%--</c:forEach>--%>
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
    </section>
    <el-dialog title="评论动态" :visible.sync="dialogTableVisible">
        <fc-comment :comment-id="currPostId"></fc-comment>
    </el-dialog>

</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>

