!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        
        return {
            data: function () {
                return {
                    search_value: '',
                    search_result: [],
                    search_loading: false
                }
            },
            components: {},
            computed: {},
            methods: {
                async remoteMethod(query){
                    if (query) {
                        this.search_loading = true;
                        let result = await api.nget("search_user",{query})
                        if (result.code==0) {
                            this.search_result = result.data;
                        }
                        this.search_loading = false;
                    }else {
                        this.search_result = []
                    }
                }
            },
            created () {
            },
            mounted () {
            
            }
        }
    })
})();