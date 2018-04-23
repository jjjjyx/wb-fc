!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT =  require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')

        Vue.use(ELEMENT)
        Vue.prototype.$ELEMENT = { size: 'small' };
    
        let headerFrom = require("js/header-from")
        const app = new Vue({
            mixins:[headerFrom],
            el: '#app',
            name: 'fill_info.js',
            data: function () {
                return {
                    form:{
                        "user.uid":"",
                        "user.username":"",
                        "user.password":"",
                        "user.nickname":"",
                        "user.email":"",
                        "user.sex":"",
                        "user.love":"",
                        "user.city":""
                    }
                }
            },
            components: {},
            computed: {},
            methods: {
                test(aa){
                    console.log(aa)
                }
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();