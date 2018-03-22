!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        let FcComment = require('js/comment')
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
                        'post.media':[]
                    },
                    curr_title:'',
                    fileList:[],
                    dialogTableVisible: false,
                    currPostId: null
                    
                }
            },
            components: {FcComment},
            computed: {},
            methods: {
                comment(id){
                    this.currPostId = id;
                    this.dialogTableVisible = true;
                },
                async release(){
                    if(this.form["post.content"]){
                        if(this.form["post.group_type"]){
                            //this.form['post.media'] = this.fileList.map((f)=>{return f.response.data})
                            let result = await api.npost('post', this.form)
                            //刷新页面
                            if(result.code ==0) {
                                //
                                alert("发布成功，积分+1")
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
                    
                    let result = await api.npost("star",{id})

                    if(result.code==0) {
                        this.$message('收藏成功');
                        location.reload()
                    }
                },
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
                handleCommand(command){
                    let [type,title] = command.split(":")
                    this.curr_title = title;
                    this.form["post.group_type"] = type;
                },
                submitUpload() {
                    this.$refs.upload.submit();
                },
                handleRemove(file, fileList) {
                    console.log(file, fileList);
                },
                handlePreview(file) {
                    console.log(file);
                },
                handleSuccess(response, file, fileList){
                    this.form['post.media'].push(response.data);
                    //console.log(response, file, fileList)
                }
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();