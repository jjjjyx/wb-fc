<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${loading}"><div id="preloader"><div id="preloader-inner" class="battery"></div></div></c:if>
<script>const config = {remote:{API_SERVER:'', root:'${path}',}};window.config = config</script>
<script src="${path}/assets/js/lib/require.min.js"></script>
<script>!+(function () {
    requirejs.config({
        baseUrl: './',
        paths: {
            js: '${path}/assets/js',
            vue: '${path}/assets/js/lib/vue.min',
            ELEMENT: '${path}/assets/js/lib/element-ui',
            dom: '${path}/assets/js/lib/dom',
            text: '${path}/assets/js/lib/text',
        }
    });

//    requirejs(['vue','assets/js/header-from.js'],function (vue,headerFrom) {
        requirejs(['./${defaultjs}']);
//    });
})();</script>