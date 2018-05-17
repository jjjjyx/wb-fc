!+(function () {
    define(function (require) {
        let api = require('js/api')
        //let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        const store = require("../admin-store.js");
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
       return {
            template: require('dom!pages/activity.html'),
            name: 'activity.js',
            data: function () {
                return {
                    store,
                    currPage: 1,
                    dialogVisible: false,
                    currData:null,
                    active:'',
                    form:{
                        "activity.id":"",
                        "activity.title":"",
                        "activity.content":"",
                        "activity.author":"",
                        "activity.type":"",
                        "activity.address":"",
                        "activity.phone":"",
                        "activity.nums":""
                    },
                    o_form:{
                        sex: '',
                        age:[18,30],
                        value4: []
                    },
                    multipleSelection:[]
                }
            },
            components: {},
            computed: {
                activityList(){
                    let start = (this.currPage -1) *10;
                    let end = start +10
                    return this.store.states.activity_data.slice(start,end)
                }
            },
            methods: {
                handleClose() {
                    this.resetFrom()
                },
                async deleteActivity(){
                    let uids = this.multipleSelection.map(item=>item.id);
                    
                    let result = await api.npost("./!delActivity",{uids})
                    this.$message(result.msg);
                    if(result.code==0){
                        this.multipleSelection.forEach((item)=>{
                            this.store.commit("deleteActivity",item)
                        })
                    }
                },
                resetFrom(){
                    this.form['activity.id'] = "";
                    this.form['activity.title'] = "";
                    this.form['activity.content'] ="";
                    this.form['activity.type'] = "";
                    this.form['activity.address'] = "";
                    this.form['activity.phone'] = "";
                    this.form['activity.nums'] = "";
                    this.form['activity.author'] = this.store.states.user.username;
                    this.o_form.sex = '';
                    this.o_form.age = [18,30];
                    this.o_form.value4 = [];
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
                    
                    let result = await api.npost("./!"+this.active, form)
                    if(result.code==0) {
                        let obj = this.currData = this.currData || {}
                        obj.id = this.form['activity.id']
                        obj.title = this.form['activity.title']
                        obj.content = this.form['activity.content']
                        obj.type = this.form['activity.type']
                        obj.author = this.form['activity.author']
                        obj.address = this.form['activity.address']
                        obj.startTime = this.form['activity.startTime']
                        obj.endTime = this.form['activity.endTime']
                        obj.nums = this.form['activity.nums']
                        obj.sint = {
                            sex: [this.form['activity.sint.sex']],
                            age: this.form['activity.sint.age']
                        }
                        if(this.active=='addActivity'){
                            obj = result.data;
                            this.store.commit("addActivity",obj)
                        }
                        this.active ="";
                        this.$message(result.msg);
                    }
                    this.dialogVisible = false
                },
                newActivity(){
                    this.active = "addActivity"
                },
                editActivity(data){
                    this.form['activity.id'] = data.id;
                    this.form['activity.title'] = data.title;
                    this.form['activity.content'] = data.content;
                    this.form['activity.type'] = data.type;
                    this.form['activity.address'] = data.address;
                    this.form['activity.phone'] = data.phone;
                    this.form['activity.nums'] = data.nums;
                    this.form['activity.author'] = this.store.states.user.username;
                    if (data.sint) {
                        if (data.sint.sex && data.sint.sex.length)
                            this.o_form.sex = data.sint.sex[0];
                        this.o_form.age = data.sint.age;
                    }
                    if (typeof data.startTime==='string') {
                        this.o_form.value4 = [new Date(data.startTime), new Date(data.endTime)];
                    }else
                        this.o_form.value4 = [data.startTime, data.endTime];
                    this.active ="updateActivity";
                }
            },
            watch:{
            },
            created () {
            },
            mounted () {
                console.log(this.activityList)
            }
        }
    })
})();