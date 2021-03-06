!+(function () {
    define(function (require) {
        let api = require('js/api')
        let FcComment = require('js/comment')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        //let CommentConstructor = Vue.extend(comment)
        let headerFrom = require("js/header-from")
        const app = new Vue({
            mixins:[headerFrom],
            el: '#app',
            name: 'new_article',
            data: function () {
                return {
                    form: {
                        "comment.content":  ''
                    }
                }
            },
            components: {FcComment},
            computed: {},
            methods: {
                async release(id){
                    if(this.form["comment.content"]){
                        let p = this.form;
                        p['c_id'] = id;
                        let result = await api.npost('comment',p)
                        //刷新页面
                        if(result.code ==0) {
                            location.reload();
                        }else
                            this.$message("评论失败");
                    }else {
                        this.$message('内容不可为空');
                    }
                },
            },
            created () {
            },
            mounted () {
                console.log(111);
                //let el = this.$refs.comment;
                //let option = {
                //    comment_id:el.dataset.commentId,
                //    position: 'top'
                //}
                //let instance = new CommentConstructor({
                //    data:option
                //})
                //instance.$mount();
                //el.parentNode.replaceChild(instance.$el,el)
            }
        })
    })
})();