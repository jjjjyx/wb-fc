!+(function () {
    //const modelList = ['vue', 'js/api', 'js/dom', 'validator', 'cookie']
    //require(modelList,
    //    function (Vue, api, {on, once, addClass, removeClass}, validator, cookie) {
    define(function(require){
        let api = require('js/api')
        let Vue = require('js/lib/vue.min')
        let {on, once, addClass, removeClass} = require('js/utils')
        let validator = require('js/lib/vue-validator.min')
        let cookie = require('js/lib/js.cookie')
        Vue.use(validator, {strict: true})
        
        const app = new Vue({
            el: '#app',
            data: function () {
                return {
                    currentEmail: '',
                    username:'', password:'',code:'',loading:true,errorCount:0,keep: false
                }
            },
            components: {},
            computed: {},
            created () {
                let hash = location.hash.replace('#', '') || ''
                const views = ['in-form', 'up-form', 'info-form']
                
                if (hash && views.indexOf(hash) >= 0) {
                    this.form = hash
                }
                
            },
            methods: {
                validateBeforeSubmit(){
                    this.$validator.validateAll().then((v) => {
                        if (v) {
                            let p = {
                                username: this.username,
                                password: this.password
                            };
                            api.npost('./sign!in', p).then(({code, data}) => {
                                if (code == 0) {
                                    if(this.keep) {
                                        cookie.set("keep_me",true,{ expires: 7, path: "/" })
                                        cookie.set("un",this.username,{ expires: 7, path: "/" })
                                        cookie.set("up",this.password,{ expires: 7, path: "/" })
                                    }else {
                                        cookie.remove("keep_me")
                                        cookie.remove("un")
                                        cookie.remove("up")
                                    }
                                    window.location.href = "./"
                                } else {/* */
                                    alert("账号密码错误");
                                    document.querySelector('[name="password"]').focus();
                                }
                            });
                        } else {
                            alert("请完成登陆验证")
                        }
                    })
                },
            },
            mounted () {
                const checkEmail = {
                    getMessage: (field, args) => {
                        return `The ${args} has been registered !`
                    },
                    validate: function (value, check) {
                        return new Promise((resolve) => {
                            api.nget('./sign!checkAccount', {value, check}).
                                then(({code, data}) => {
                                    if (code == 0) {
                                        resolve(false)
                                    } else {
                                        resolve(true)
                                    }
                                })
                        })
                    }
                }
                validator.Validator.extend('checkAccount', checkEmail)
            }
        })
    })
    
})()