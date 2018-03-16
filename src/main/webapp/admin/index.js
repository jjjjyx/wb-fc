!+(function () {
    define(function(require){
        let api = require('js/api')
        let Vue = require('vue')
        let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT =  require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(VueRouter);
        Vue.use(ELEMENT)
        Vue.prototype.$ELEMENT = { size: 'small' };
        
        const store = require("./admin-store.js");
        const router = new VueRouter({
            routes:[
                {
                    path: "/",
                    component: require('./pages/user.js'),
                    meta: {
                        title: '用户管理',
                        subTitle: 'user'
                    }
                },
                {
                    path: "/news",
                    component: require('./pages/news.js'),
                    meta: {
                        title: '资讯管理',
                        subTitle: 'news'
                    }
                },
                
                {
                    path: "/group",
                    component: require('./pages/group.js'),
                    meta: {
                        title: '圈子管理',
                        subTitle: 'group'
                    }
                },
    
                {
                    path: "/activity",
                    component: require('./pages/activity.js'),
                    meta: {
                        title: '活动管理',
                        subTitle: 'activity'
                    }
                },
                {
                    path: "/lore",
                    component: require('./pages/lore.js'),
                    meta: {
                        title: '运动知识管理',
                        subTitle: 'lore'
                    }
                },
                {
                    path: "/data_m",
                    component: require('./pages/data.js'),
                    meta: {
                        title: '材料管理',
                        subTitle: 'data'
                    }
                },
                {
                    path: "/img",
                    component: require('./pages/img.js'),
                    meta: {
                        title: '运动图片管理',
                        subTitle: 'img'
                    }
                }
            ]
        })
        
        const app = new Vue({
            el: "#app",
            router,
            data: function () {
                return {
                    name:'hhhh'
                }
            },
            components: {
            },
            computed: {
            },
            methods: {
            },
            created(){
            },
            mounted() {
                console.log("hello world")
            }
        })
    })
})();