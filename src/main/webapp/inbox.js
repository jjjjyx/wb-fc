!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT)
        Vue.prototype.$ELEMENT = { size: 'small' };
        let headerFrom = require("js/header-from")
        api.nget('user_follow').then((result)=>{
            if (result.code!=0) {
                alert("获取信息失败")
                location.href = "./"
            }else {
                newVue(result.data)
            }
        }).catch(()=>{
            alert("获取信息失败")
            location.href = "./"
        })
        
        function newVue(user_list = []){
            const app = new Vue({
                mixins:[headerFrom],
                el: '#app',
                name: 'inbox',
                data: function () {
                    return {
                        options: user_list,
                        value:null,
                        form:{
                            'inbox.content':''
                        }
                    }
                },
                components: {},
                computed: {},
                methods: {
                    async remoteMethod2(query){
                        if (query) {
                            this.user_follow_loading = true;
                            let result = await api.nget(`${path}/user_follow`)
                            if (result.code==0) {
                                this.options = result.data;
                            }
                            this.user_follow_loading = false;
                        }else {
                            this.options = []
                        }
                    },
                    reply(id){
                        console.log(id)
                        let obj = this.options.find((item)=>item.uid==id);
                        console.log(obj,this.options,id)
                        if (obj) this.value = obj
                        else this.$message("您还没有关注对方")
                    },
                    async onSubmit(){
                        let form = this.form;
                        console.log(this.value)
                        if (form['inbox.content']) {
                            if (this.value) {
                                form['uid'] = this.value.uid;
                                let result = await api.npost("send-private", form)
                                if (result.code==0) {
                                    alert("发送成功");
                                    location.reload()
                                }else {
                                    this.$message("发送失败")
                                }
                            }else {
                                this.$message("发送对象不可为空")
                            }
                        }else {
                            this.$message("消息不可为空")
                        }
                    }
                },
                created () {
                },
                mounted () {
                }
            })
        }
    })

})();