!+(function () {
    const template =
`
<div class="send_activity">
    <div class="title_area am-cf">
        <div class="num S_txt2 am-fr am-text-xs" v-if="form['content']">已经输入{{form['content'].length}}个字</div>
    </div>
    <div class="input">
        <textarea name="" v-model="form['content']" class="W_input" style="height: 68px; margin: 0px; padding: 0px; border-style: none; border-width: 0px; font-size: 14px; word-wrap: break-word; line-height: 18px; overflow: hidden; outline: none;"></textarea>
    </div>
    <div class="func_area am-cf">
        <div class="func">
            <button class="am-btn am-btn-primary am-btn-sm" @click="release">评论</button>
        </div>
    </div>
</div>
`
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        
        return {
            name: 'send.js',
            template,
            data: function () {
                return {
                    //form: {
                    //    content:''
                    //}
                }
            },
            props:['prefix','form','url'],
            components: {},
            computed: {},
            methods: {
                async release(){
                    if(this.form["content"]){
                        let result = await api.npost(this.url, this.form)
                        //刷新页面
                        if(result.code ==0) {
                            alert("操作成功")
                            location.reload()
                        }
                    }else {
                        this.$message('内容不可为空');
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