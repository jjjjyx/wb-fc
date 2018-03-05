<%@ page contentType="text/html;charset=UTF-8" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>运动交友系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/x-icon" href="./favicon.ico" rel="shortcut icon" />
<meta name="mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link href="${path}/css/amazeui.min.css" rel="stylesheet">
<link rel="stylesheet" href="${path}/css/element-ui.min.css">
<link href="${path}/css/${cssPath == null?'index':cssPath}.css" rel="stylesheet">
<link href="${path}/css/animate.min.css" rel="stylesheet">
<c:if test="${loading}">
    <link href="${path}/css/preloader.min.css" rel="stylesheet">
</c:if>
