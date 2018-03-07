!+(function () {
    define(function(require){
        let api = require('js/api')
        let Vue = require('js/lib/vue.min')
        let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('js/lib/element-ui')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(VueRouter);
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