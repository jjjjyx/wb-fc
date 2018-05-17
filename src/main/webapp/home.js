!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        let FcComment = require('js/comment')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT)
        let headerFrom = require("js/header-from")
        const app = new Vue({
            mixins:[headerFrom],
            el: '#app',
            name: 'home',
            data: function () {
                return {
                    form: {
                        'post.content': '',
                        'post.title': '',
                        'post.group_type': '',
                        'post.media': [],
                    },
                    curr_title: '',
                    fileList: [],
                    dialogTableVisible: false,
                    currPostId: null,
                    private: false,
                    comment_position: 'top'
                }
            },
            components: {FcComment},
            computed: {},
            methods: {
            	
                comment (post_type,id,is) {
                    if (post_type == 'mood'&& (is == null || is == undefined || is == '' || is == false)) {
                    	this.$message("他还不是您的好友，您无权评论")
                        this.comment_position = 'hide'
                    }else { // 圈子只能好友评论 ，
                        this.comment_position = 'top'
                    }
                    this.currPostId = id
                    this.dialogTableVisible = true
                },
                async commentmy (id,is) {
            		this.comment_position = 'top';
            		this.currPostId = id;
                    this.dialogTableVisible = true;
            	},
                async release () {
                    let q = this.form;
                    let post_type = this.$refs.post_type.value
                    q['post.type'] = post_type
                    if (q['post.content']) {
                        if (post_type == 'mood' && this.private) {
                            q['post.group_type'] = 'private'
                        }
                        if (post_type == 'mood' || q['post.group_type']) {
                            let result = await api.npost('post', q)
                            //刷新页面
                            if (result.code == 0) {
                                //
                                alert('发布成功，积分+5')
                                if (post_type == 'mood') {
                                    location.href = post_type
                                } else {
                                    location.href = `${post_type}?_=${q['post.group_type']}`
                                }
                            }
                        } else {
                            this.$message('请选择类型')
                        }
                    } else {
                        this.$message('内容不可为空')
                    }
                },
                async delPost (id) {
                    this.$confirm('确认删除此动态，此操作不可撤销?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        api.npost(`delPost/${id}`).then((result)=>{
                            if(result.code==0){
                                alert("删除成功")
                                location.reload()
                            }
                        })
                    }).catch(() => {
        
                    });
                },
                async star (id) {
                    
                    let result = await api.npost('star', {id})
                    
                    if (result.code == 0) {
                        this.$message('收藏成功')
                        location.reload()
                    }
                },
                async thumbs_up (id, e) {
                    if (e) {
                        //this.$message('您已经赞过了');
                        let result = await api.npost('un-thumbs_up', {id})
                        if (result.code == 0) {
                            this.$message('取消点赞')
                            location.reload()
                        }
                    } else {
                        let result = await api.npost('thumbs_up', {id})
                        if (result.code == 0) {
                            this.$message('点赞成功')
                            location.reload()
                        }
                    }
                },
                handleCommand (command) {
                    let [type, title] = command.split(':')
                    this.curr_title = title
                    this.form['post.group_type'] = type
                },
                submitUpload () {
                    this.$refs.upload.submit()
                },
                handleRemove (file, fileList) {
                    console.log(file, fileList)
                },
                handlePreview (file) {
                    console.log(file)
                },
                handleSuccess (response, file, fileList) {
                    this.form['post.media'].push(response.data.fn)
                    //console.log(response, file, fileList)
                },
                async priv(id){
                    console.log('private',id)
                    let result = await api.npost('priv', {id})
                    if (result.code == 0) {
                        this.$message('权限设置成功')
                        location.reload()
                    }
                },
                async public(id){
                    let result = await api.npost('pub', {id})
                    if (result.code == 0) {
                        this.$message('权限设置成功')
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
})()