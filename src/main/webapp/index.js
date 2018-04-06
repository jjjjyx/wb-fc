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
            name: 'index',
            data: function () {
                return {
                    name: 'hello world!',
                    dialogVisible: false,
                    imgSrc:'',
                }
            },
            components: {},
            computed: {},
            methods: {
                async acSsign(id){
                    let result = await api.npost("activity-sign",{id})
                    if(result.code==0){
                        alert("报名成功")
                        location.href="activity"
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
                sb_yao_de_yun_dong_zhi_shi(title,content){
                    this.$alert(content, title, {
                        confirmButtonText: '确定'
                    });
                },
                viewImg(src){
                    console.log(src)
                    this.dialogVisible = true;
                    this.imgSrc = src;
                }
                
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();