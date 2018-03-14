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
            template: require('dom!./user.html'),
            name: 'user.js',
            data: function () {
                return {
                    name: 'hello world!',
                    store,
                    currPage: 1,
                    dialogVisible: false,
                    currData:null,
                    active:'',
                    form:{
                        "user.uid":"",
                        "user.username":"",
                        "user.password":"",
                        "user.nickname":"",
                        "user.email":"",
                        "user.sex":"",
                        "user.love":"",
                        "user.city":""
                    },
                    multipleSelection:[]
                }
            },
            components: {},
            computed: {
                userList(){
                    let start = (this.currPage -1) *10;
                    let end = start +10
                    return this.store.states.user_data.slice(start,end)
                }
            },
            methods: {
                handleClose() {
                    this.resetFrom()
                },
                async resetPassword(){
                    let uids = this.multipleSelection.map(item=>item.uid);
                    let result = await api.npost("./!resetPass",{uids})
                    this.$message(result.msg);
                },
                async deleteUser(data){
                    //let uids = this.multipleSelection.map(item=>item.uid);
                    let del_id = data.uid;
                    let result = await api.npost("./!delUser",{del_id})
                    this.$message(result.msg);
                    if(result.code==0)
                        this.store.commit("deleteUser",data)
                },
                resetFrom(){
                    this.form['user.uid'] = "";
                    this.form['user.username'] = "";
                    this.form['user.password'] ="";
                    this.form['user.nickname'] = "";
                    this.form['user.sex'] ="";
                    this.form['user.email'] ="";
                    this.form['user.love'] = "";
                    this.form['user.city'] = "";
                },
                async save(){
                    let result = await api.npost("./!"+this.active,this.form)
                    if(result.code==0) {
                        let obj = this.currData = this.currData || {}
                        obj.uid = this.form['user.uid']
                        obj.username = this.form['user.username']
                        obj.password = this.form['user.password']
                        obj.email = this.form['user.email']
                        obj.nickname = this.form['user.nickname']
                        obj.sex = this.form['user.sex']
                        obj.love = this.form['user.love']
                        obj.city = this.form['user.city']
                        if(this.active=='addUser'){
                            obj = result.data;
                            this.store.commit("addUser",obj)
                        }
                        this.active ="";
                        this.$message(result.msg);
                    }
                    this.dialogVisible = false
                },
                newUser(){
                    this.active = "addUser"
                },
                editUser(data){
                    this.form['user.uid'] = data.uid;
                    this.form['user.username'] = data.username;
                    this.form['user.password'] = data.password;
                    this.form['user.nickname'] = data.nickname;
                    this.form['user.email'] = data.email;
                    this.form['user.sex'] = data.sex || "男";
                    this.form['user.love'] = data.love;
                    this.form['user.city'] = data.city;
                    this.active ="updateUser";
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