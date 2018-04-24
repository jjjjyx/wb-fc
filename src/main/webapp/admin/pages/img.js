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
                    multipleSelection: []
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
                handleRemove(file, fileList) {
                    console.log(file, fileList);
                },
                handlePreview(file) {
                    console.log(file);
                },
                handleSuccess (response, file, fileList) {
                    this.store.commit('addImg', response.data)
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