!+(function () {
    define(function(require){
        let api = require('js/api')
        let Vue = require('js/lib/vue.min')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('js/lib/element-ui')
        //let cookie = require('js/lib/js.cookie')
    
        const app = new Vue({
            el: "#app",
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