!+(function () {
    define(function (require) {
        let api = require('js/api')
        
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('js/lib/element-ui')
        //let cookie = require('js/lib/js.cookie')
        const store = require("../admin-store.js");
       return {
            template: require('dom!pages/img2.html'),
            name: 'img',
            data: function () {
                return {
                    store,
                    currPage: 1,
                    dialogVisible: false,
                    currData:null,
                    active:'',
                    multipleSelection: [],
                    currItem: null
                }
            },
            components: {},
            computed: {
                dataList(){
                    let start = (this.currPage -1) *10;
                    let end = start +10
                    return this.store.states.img_data.slice(start,end)
                },
                activityList(){
                    let start = (this.currPage -1) *10;
                    let end = start +10
                    return this.store.states.activity_data.slice(start,end)
                }
            },
            methods: {
                beforeAdd(currItem){
                    this.currItem = currItem;
                },
                handleRemove(file, fileList) {
                    console.log(file, fileList);
                },
                handlePreview(file) {
                    console.log(file);
                },
                async handleSuccess (response, file, fileList) {
                    this.store.commit('addImg', response.data)
                    // 上次成功了
                    //在当前的 对象中添加路径
                    //保存
                    console.log(response.data)
                    if (this.currItem) {
                        let result = await api.npost("!addActivityMedia",{id:this.currItem.id, media: response.data.fn})
                        if (result.code==0){
                            if (!this.currItem.media)
                                this.currItem.media = []
                            this.currItem.media.push(response.data.fn)
                            this.currItem = null;
                        }
                    }
                },
                handleExceed(files, fileList) {
                    this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
                },
                beforeRemove(file, fileList) {
                    return this.$confirm(`确定移除 ${ file.name }？`);
                },
                async deleteImg(){
                    let fns = this.multipleSelection.map(item=>item.fn);
    
                    let result = await api.npost("./!delImg",{fns})
                    this.$message(result.msg);
                    if(result.code==0){
                        this.multipleSelection.forEach((item)=>{
                            this.store.commit("deleteImg",item)
                        })
                    }
                }
            },
            created () {
            },
            mounted () {
            }
        }
    })
})();