!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT =  require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT)
        const app = new Vue({
            el: '#app',
            name: 'home',
            data: function () {
                return {
                    form: {
                        'post.content':'',
                        'post.group_type':'',
                    },
                    curr_title:''
                    
                }
            },
            components: {},
            computed: {},
            methods: {
                async release(){
                    if(this.form["post.content"]){
                        if(this.form["post.group_type"]){
                            let result = await api.npost('post',this.form)
                            console.log(result);
                            //刷新页面
                            if(result.code ==0) {
                                location.href =`'home?_=${this.form["post.group_type"]}`
                            }
                        }else {
                            this.$message('请选择类型');
                        }
                    }else {
                        this.$message('内容不可为空');
                    }
                },
                async star(id){
                    console.log(id)
                    let result = await api.npost("star",{id})
                    console.log(result)
                },
                handleCommand(command){
                    let [type,title] = command.split(":")
                    this.curr_title = title;
                    this.form["post.group_type"] = type;
                }
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();