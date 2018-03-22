!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT);
        
        const app = new Vue({
            el: '#app',
            name: 'activity',
            data: function () {
                return {
                    dialogTableVisible: false,
                    form:{
                        "activity.id":"",
                        "activity.title":"",
                        "activity.content":"",
                        "activity.author":"",
                        "activity.type":""
                    }
                }
            },
            components: {},
            computed: {},
            methods: {
                async save(){
                    let result = await api.npost("activity",this.form)
                    if(result.code==0) {
                        alert(result.msg)
                        location.reload();
                    }
                    this.dialogTableVisible = false
                },
                async sign(id){
                    let result = await api.npost("activity-sign",{id})
                    if(result.code==0){
                        alert("报名成功")
                        location.reload()
                    }
                },
                async unsign(id){
                    let result = await api.npost("as2",{id})
                    if(result.code==0){
                        alert("取消报名成功")
                        location.reload()
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