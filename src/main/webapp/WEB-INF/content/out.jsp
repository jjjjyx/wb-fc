<%--
  Created by IntelliJ IDEA.
  User: jjjjyx
  Date: 18/04/06
  Time: 下午 5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提示</title>
</head>
<body>
成功退出登录，<span id="a">3</span>秒后跳转到登录页面
<script>
    var $a = document.getElementById("a");
    var c = 3;
    function a () {
        $a.innerText = --c
        if(c==0) {
            location.href = "/fc";
        }else
            setTimeout(a,1000)
    }
    a();
</script>
</body>

</html>
