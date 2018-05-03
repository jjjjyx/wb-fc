<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="down"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="down"></c:set>
<html>
<head>
    <%@include file="../../assets/base/header.jsp" %>

</head>
<body>

<div id="app">
    <%@include file="../../assets/base/nav.jsp" %>
    <section class="am-g am-g-fixed blog-fixed blog-content activity-wrapper" style="">

        <div class="col-md-9 am-u-md-9 am-padding-0">
            <div class="cuk-layout-content">
                <header class="" style="border-bottom: 1px solid #d8dde4;flex-shrink:0; display: inline-flex;">
                    <span class="am-block" style="    margin-bottom: 15px;">${fn:length(files)} 文件</span>
                    <div style="margin-left: auto">
                        <el-button size="small" @click="dialogTableVisible = true">上传文件</el-button>
                    </div>
                </header>
                <div class="list-content">
                    <ul class="am-list am-list-static">
                        <c:forEach items="${files}" var="ad">

                            <li>
                                <img src="${path}/assets/img/user (${ad.uid.uid % 28}).png" alt=""
                                     class="avatar am-margin-left-xs">
                                <span class="am-badge am-margin-left-sm" title="上传时间"><i class="am-icon-calendar"></i>
                                        <fmt:formatDate value="${ad.createDate}" pattern=" yyyy/MM/dd"/></a></span>
                                <a href="javascript:void(0);">${ad.name}</a>
                                <span class="am-badge am-badge-warning" title="所需积分"
                                      style="cursor: default">${ad.integral}分</span>
                                <a href="javascript:void(0);" class="am-fr" @click="down('${ad.id}')">下载文件</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

    </section>
    <el-dialog title="上传文件" :visible.sync="dialogTableVisible" width="30%" style="    text-align: center;">
        <div class="block">
            <span class="demonstration" style="    font-size: 14px;
    color: #8492a6;
    line-height: 44px;">下载积分</span>
            <el-slider v-model="integral" style="float: right;
    width: 70%;
    margin-right: 20px;"></el-slider>
        </div>
        <el-upload class="upload-demo" drag :action="`file!uploadDate?integral=`+integral" ref="upload"
                   :auto-upload="false" :file-list="fileList" multiple="false" limit="1" :on-exceed="onExceed"
                   :on-success="handleSuccess">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip"></div>
        </el-upload>
        <span slot="footer" class="dialog-footer">
            <el-button @click="submitUpload">上传</el-button>
            <el-button @click="dialogTableVisible = false">取消</el-button>
        </span>
    </el-dialog>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>