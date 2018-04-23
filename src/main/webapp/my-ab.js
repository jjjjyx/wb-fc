!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT)
        let headerFrom = require("js/header-from")
        const app = new Vue({
            mixins:[headerFrom],
            el: '#app',
            name: 'my-ab',
            data: function () {
                return {
                    name: 'hello world!',
                    activityList:window.__INIT
                }
            },
            components: {},
            computed: {},
            methods: {},
            created () {
            },
            mounted () {
            }
        })
    })
})();