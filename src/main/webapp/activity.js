!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        Vue.use(ELEMENT);
        let FcComment = require('js/comment')
        let headerFrom = require("js/header-from")
        Vue.prototype.$ELEMENT = { size: 'small' };
        const app = new Vue({
            mixins:[headerFrom],
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
                        "activity.type":"",
                        "activity.phone":"",
                    },
                    o_form:{
                        sex: '',
                        age:[18,30],
                        value4:[]
                    },
                    fileList:[],
                    form: {
                        'comment.content': '',
                        'comment.media': [],
                    },
                }
            },
            components: {FcComment},
            computed: {},
            methods: {
                async release(c_id){
                    let q = this.form;
                    q['comment.pid'] = c_id
                    if (q['comment.content']) {
                        let result = await api.npost('comment', q)
                        if (result.code == 0) {
                            alert('发布成功')
                            location.reload()
                        }
                    }

                },
                end(id){
                    this.$confirm('确认提前停止活动，此操作不可撤销?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        location.href = `end/${id}`
                    }).catch(() => {
                    
                    });
                },
                async save(){
                    let form = this.form;
                    if (this.o_form.sex) {
                        form['activity.sint.sex'] = this.o_form.sex
                    }
                    if (this.o_form.age) {
                        form['activity.sint.age'] = this.o_form.age
                    }
                    if (this.o_form.value4) {
                        form["activity.startTime"]= this.o_form.value4[0].format("yyyy-MM-dd hh:mm:ss")
                        form["activity.endTime"]=this.o_form.value4[1].format("yyyy-MM-dd hh:mm:ss");
                    }
                    
                    let result = await api.npost("save", form)
                    if(result.code==0) {
                        alert(result.msg)
                        location.reload()
                    }
                    this.dialogTableVisible = false
                },
                async sign(id){
                    let result = await api.npost("sign",{id})
                    if(result.code==0){
                        alert("报名成功")
                        location.reload()
                    }
                },
                async unsign(id){
                    let result = await api.npost("unsign",{id})
                    if(result.code==0){
                        alert("取消报名成功")
                        location.reload()
                    }
                },
                del(id){
                    this.$confirm('确认删除活动，此操作不可撤销?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        api.npost(`del/${id}`).then((result)=>{
                            if(result.code==0){
                                alert("删除成功")
                                location.reload()
                            }
                        })
                    }).catch(() => {
        
                    });
                },
                handleCommand (command) {
                    //let [type, title] = command.split(':')
                    //this.curr_title = title
                    //this.form['post.group_type'] = type
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
                    this.form['comment.media'].push(response.data.fn)
                    //console.log(response, file, fileList)
                }
                
            },
            created () {
            },
            mounted () {
            }
        })
    
    
        Date.prototype.format = function(fmt) {
            var o = {
                "M+" : this.getMonth()+1,                 //月份
                "d+" : this.getDate(),                    //日
                "h+" : this.getHours(),                   //小时
                "m+" : this.getMinutes(),                 //分
                "s+" : this.getSeconds(),                 //秒
                "q+" : Math.floor((this.getMonth()+3)/3), //季度
                "S"  : this.getMilliseconds()             //毫秒
            };
            if(/(y+)/.test(fmt)) {
                fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            }
            for(var k in o) {
                if(new RegExp("("+ k +")").test(fmt)){
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                }
            }
            return fmt;
        }
    })
})();