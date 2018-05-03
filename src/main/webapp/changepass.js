!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        let validator = require('js/lib/vue-validator.min');
        let cookie = require('js/lib/js.cookie');
        let ELEMENT = require('ELEMENT')
        Vue.use(validator, {strict: true});
        let headerFrom = require("js/header-from")
        Vue.use(ELEMENT)
        const app = new Vue({
            mixins:[headerFrom],
            el: '#app',
            name: 'changepass',
            data: function () {
                return {
                    name: 'hello world!',
                    cpass: '',
                    password: '',
                }
            },
            components: {},
            computed: {},
            methods: {
                validateBeforeSubmit(){
                    this.$validator.validateAll().
                        then((v) => {
                            if (v) {
                                let p = {
                                    password: this.password,
                                    cpass: this.cpass
                                };
                                api.npost(`changepass`, p).then(({code,data})=>{
                                    if (code===0) {
                                        alert("修改成功，请重新登录")
                                        location.href = './login'
                                    }else {
                                        alert("修改失败")
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