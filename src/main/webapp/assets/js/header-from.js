!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        let path = window.config.remote.root;
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
                        let result = await api.nget(`${path}/search_user`,{query})
                        if (result.code==0) {
                            this.search_result = result.data;
                        }
                        this.search_loading = false;
                    }else {
                        this.search_result = []
                    }
                },
                nav_follow(e,item){
                    e.preventDefault()
                    e.stopPropagation();
                    if (item.is_f) {// 取消关注
                        api.npost("unfollow",{uid:item.uid}).then((data)=>{
                            if(data.code==0) {
                                this.$message("取消关注");
                                item.is_f = false;
                            }else {
                                this.$message("操作失败")
                            }
                        })
                    }else {
                        api.npost(`${path}/follow`,{uid:item.uid}).then((data)=>{
                            if(data.code==0) {
                                this.$message("成功关注");
                                item.is_f = true;
                            }else {
                                this.$message("操作失败")
                            }
                        })
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