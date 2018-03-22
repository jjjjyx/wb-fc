!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        
        const app = new Vue({
            el: '#app',
            name: 'index',
            data: function () {
                return {
                    name: 'hello world!'
                }
            },
            components: {},
            computed: {},
            methods: {
                async acSsign(id){
                    let result = await api.npost("activity-sign",{id})
                    if(result.code==0){
                        alert("报名成功")
                        location.href="activity"
                    }
                }
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();