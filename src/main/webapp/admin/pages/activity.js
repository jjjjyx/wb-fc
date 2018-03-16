!+(function () {
    define(function (require) {
        let api = require('js/api')
        //let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        const store = require("../admin-store.js");
       return {
            template: require('dom!./activity.html'),
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
                        "activity.type":""
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
                    this.form['activity.author'] = this.store.states.user.username;
                },
                async save(){
                    let result = await api.npost("./!"+this.active,this.form)
                    if(result.code==0) {
                        let obj = this.currData = this.currData || {}
                        obj.id = this.form['activity.id']
                        obj.title = this.form['activity.title']
                        obj.content = this.form['activity.content']
                        obj.type = this.form['activity.type']
                        obj.author = this.form['activity.author']
                       
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
                    this.form['activity.author'] = this.store.states.user.username;
                    this.active ="updateActivity";
                }
            },
            watch:{
            },
            created () {
            },
            mounted () {
            }
        }
    })
})();