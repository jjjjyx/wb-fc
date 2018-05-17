<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../../assets/base/base.jsp" %>
<c:set var="moduleName" value="down"></c:set>
<c:set var="cssPath" value="index"></c:set>
<c:set var="defaultjs" value="changepass"></c:set>
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
                    <span class="am-block" style="    margin-bottom: 15px;">修改密码</span>
                </header>
                <div class="list-content">
                    <form @submit.prevent="validateBeforeSubmit">
                        <div class="form-item form-type-password form-item-pass">
                            <label for="edit-pass">&nbsp;&nbsp;&nbsp;&nbsp;新密码 <span
                                    class="form-required" title="This field is required.">*</span> </label>
                            <input type="password" id="edit-pass" v-model="password"
                                   name="password"
                                   placeholder="输入密码"
                                   maxlength="18"
                                   :class="{'text-danger': errors.has('password')}"
                                   v-validate="'required|alpha_num|max:18'" class="form-text required">
                            <span v-show="errors.has('password')" class="text-danger">输入你的密码</span>
                        </div>

                        <div class="form-item form-type-password form-item-pass">
                            <label for="c-pass">确认密码 <span
                                    class="form-required" title="This field is required.">*</span> </label>
                            <input type="password" id="c-pass" v-model="cpass"
                                   name="cpass"
                                   placeholder="输入密码"
                                   maxlength="18"
                                   :class="{'text-danger': errors.has('cpass')}"
                                   v-validate="'required|confirmed:password'" class="form-text required">
                            <span v-show="errors.has('cpass')" class="text-danger">2次密码不一致</span>
                        </div>

                        <div class="form-actions">
                            <input type="submit" id="edit-submit" name="op" value="提交"
                                   class="btn_1 submit">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<%@include file="../../assets/base/footer.jsp" %>
</body>
</html>