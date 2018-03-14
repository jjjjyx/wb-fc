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
            template: require('dom!./group.html'),
            name: 'group.js',
            data: function () {
                return {
                    store,
                    currPage: 1,
                    dialogVisible: false,
                    currData:null,
                    active:'',
                    form:{
                        "group.id":"",
                        "group.title":"",
                        "group.content":"",
                        "group.type":""
                    },
                    multipleSelection:[]
                }
            },
            components: {},
            computed: {
                groupList(){
                    let start = (this.currPage -1) *10;
                    let end = start +10
                    return this.store.states.group_data.slice(start,end)
                }
            },
            methods: {
                handleClose() {
                    this.resetFrom()
                },
                async deleteGroup(){
                    let uids = this.multipleSelection.map(item=>item.id);
                    
                    let result = await api.npost("./!delGroup",{uids})
                    this.$message(result.msg);
                    if(result.code==0){
                        this.multipleSelection.forEach((item)=>{
                            this.store.commit("deleteGroup",item)
                        })
                    }
                    
                },
                resetFrom(){
                    this.form['group.id'] = "";
                    this.form['group.title'] = "";
                    this.form['group.content'] ="";
                    this.form['group.type'] = "";
                },
                async save(){
                    let result = await api.npost("./!"+this.active,this.form)
                    if(result.code==0) {
                        let obj = this.currData = this.currData || {}
                        obj.id = this.form['group.id']
                        obj.title = this.form['group.title']
                        obj.content = this.form['group.content']
                        obj.type = this.form['group.type']
                       
                        if(this.active=='addGroup'){
                            obj = result.data;
                            this.store.commit("addGroup",obj)
                        }
                        this.active ="";
                        this.$message(result.msg);
                    }
                    this.dialogVisible = false
                },
                newGroup(){
                    this.active = "addGroup"
                },
                editGroup(data){
                    this.form['group.id'] = data.id;
                    this.form['group.title'] = data.title;
                    this.form['group.content'] = data.content;
                    this.form['group.type'] = data.type;
                    this.active ="updateGroup";
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