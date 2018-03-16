!+(function () {
    define(function (require) {
        let api = require('js/api')
        
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('js/lib/element-ui')
        //let cookie = require('js/lib/js.cookie')
        const store = require("../admin-store.js");
       return {
            template: require('dom!./img.html'),
            name: 'img',
            data: function () {
                return {
                    store,
                    currPage: 1,
                    dialogVisible: false,
                    currData:null,
                    active:'',
                }
            },
            components: {},
            computed: {
                dataList(){
                    let start = (this.currPage -1) *10;
                    let end = start +10
                    return this.store.states.img_data.slice(start,end)
                }
            },
            methods: {
                handleRemove(file, fileList) {
                    console.log(file, fileList);
                },
                handlePreview(file) {
                    console.log(file);
                },
                handleExceed(files, fileList) {
                    this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
                },
                beforeRemove(file, fileList) {
                    return this.$confirm(`确定移除 ${ file.name }？`);
                }
            },
            created () {
            },
            mounted () {
            }
        }
    })
})();