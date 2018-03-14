!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let ELEMENT = require('js/lib/element-ui')
        //let cookie = require('js/lib/js.cookie')
    
        let validator = require('js/lib/vue-validator.min')
        let cookie = require('js/lib/js.cookie')
        Vue.use(validator, {strict: true})
        
        const app = new Vue({
            el: '#app',
            name: 'up',
            data: function () {
                return {
                    name: 'hello world!',
                    username:'',
                    password:'',
                    c_p:'',
                }
            },
            components: {},
            computed: {},
            methods: {
                validateBeforeSubmit(){
                    this.$validator.validateAll().then((v)=>{
                        if(v) {
                            api.npost('./sign!up',{username:this.username, password:this.password}).then((v)=>{
                                if(v.code==0) {
                                    alert("注册成功,点击确定将跳转到首页")
                                    location.href="./!execute"
                                }else {
                                    alert(v.msg);
                                }
                            })
                        }
                    })
                }
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();