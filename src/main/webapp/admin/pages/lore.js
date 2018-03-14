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
            template: require('dom!./lore.html'),
            name: 'lore.js',
            data: function () {
                return {
                    store,
                    currPage: 1,
                    dialogVisible: false,
                    currData:null,
                    active:'',
                    form:{
                        "lore.id":"",
                        "lore.title":"",
                        "lore.content":""
                    },
                    multipleSelection:[]
                }
            },
            components: {},
            computed: {
                loreList(){
                    let start = (this.currPage -1) *10;
                    let end = start +10
                    return this.store.states.lore_data.slice(start,end)
                }
            },
            methods: {
                handleClose() {
                    this.resetFrom()
                },
                async deleteLore(){
                    let uids = this.multipleSelection.map(item=>item.id);
                    
                    let result = await api.npost("./!delLore",{uids})
                    this.$message(result.msg);
                    if(result.code==0){
                        this.multipleSelection.forEach((item)=>{
                            this.store.commit("deleteLore",item)
                        })
                    }
                    
                },
                resetFrom(){
                    this.form['lore.id'] = "";
                    this.form['lore.title'] = "";
                    this.form['lore.content'] ="";

                },
                async save(){
                    let result = await api.npost("./!"+this.active,this.form)
                    if(result.code==0) {
                        let obj = this.currData = this.currData || {}
                        obj.id = this.form['lore.id']
                        obj.title = this.form['lore.title']
                        obj.content = this.form['lore.content']

                        if(this.active=='addLore'){
                            obj = result.data;
                            this.store.commit("addLore",obj)
                        }
                        this.active ="";
                        this.$message(result.msg);
                    }
                    this.dialogVisible = false
                },
                newLore(){
                    this.active = "addLore"
                },
                editLore(data){
                    this.form['lore.id'] = data.id;
                    this.form['lore.title'] = data.title;
                    this.form['lore.content'] = data.content;
                    this.active ="updateLore";
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