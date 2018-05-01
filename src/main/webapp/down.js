!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
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
        let headerFrom = require("js/header-from")
        Vue.use(ELEMENT)
        const app = new Vue({
            mixins:[headerFrom],
            el: '#app',
            name: 'down',
            data: function () {
                return {
                    name: 'hello world!',
                    dialogTableVisible:false,
                    integral: 15,
                    fileList:[]
                }
            },
            components: {},
            computed: {},
            methods: {
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
                handleRemove (file, fileList) {
                    console.log(file, fileList)
                },
                handleSuccess (response, file, fileList) {
                    //this.store.commit('addData', response.data)
                    alert("上传成功")
                    location.reload()
                },
                handlePreview (file) {
                    console.log(file)
                },
                onExceed(){
                    this.$message("只能选择一个文件")
                },
                submitUpload(){
                    this.$refs.upload.submit();
                }
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();