!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT)
        const app = new Vue({
            el: '#app',
            name: 'user-info',
            data: function () {
                return {
                    name: 'hello world!'
                }
            },
            components: {},
            computed: {},
            methods: {
                async thumbs_up(id,e){
                    if(e) {
                        this.$message('您已经赞过了');
                        return;
                    }
                    let result = await api.npost("thumbs_up",{id})
                    if(result.code==0) {
                        this.$message('点赞成功');
                        location.reload()
                    }
                },
                g(uid){
                    api.npost("follow",{uid}).then((data)=>{
                        if(data.code==0) {
                            alert("成功关注");
                            location.reload()
                        }else {
                            this.$message("操作失败")
                        }
                    })
                },
                ung(uid){
                    api.npost("unfollow",{uid}).then((data)=>{
                        if(data.code==0) {
                            alert("成功取消关注");
                            location.reload()
                        }else {
                            this.$message("操作失败")
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