!+(function () {
    define(function (require) {
        
        
        
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        let headerFrom = require("js/header-from")
    
        function down(url){
            let ifa = document.createElement("iframe")
            ifa.setAttribute("id","down-file-iframe");
        
            let ifr = document.createElement("form")
            ifr.setAttribute("target","down-file-iframe")
            ifr.setAttribute("method","post");
            ifr.setAttribute("action",url);
        
            ifa.appendChild(ifr)
            document.body.appendChild(ifa)
            ifr.submit();
            document.body.removeChild(ifa);
        
        }
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT)
        const app = new Vue({
            mixins:[headerFrom],
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
                down(fn){
                    api.npost("uf",{id:fn}).then((data)=>{
                        if(data.code==0){
                            //window.
                            down(`file!down?id=${fn}`).then(()=>{
                                location.reload()
                            })
                        }else if(data.code==2){
                            alert("积分不足")
                        }else {
                            alert(data.msg)
                        }
                    })
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
                },
                sb(e,title){
                    let content = e.target.dataset.content
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