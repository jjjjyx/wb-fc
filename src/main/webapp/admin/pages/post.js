!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        const store = require('../admin-store.js')
        return {
            template: require('dom!pages/post.html'),
            name: 'post',
            data: function () {
                return {
                    store,currPage: 1,
                    f_model:false,
                    f_form:{},
                    leader_setting:store.states.leader_setting
                    //currData: null
                }
            },
            components: {},
            computed: {
                postList(){
                    let start = (this.currPage - 1) * 10
                    let end = start + 10
                    return this.store.states.leaderboard_data.slice(start, end)
                },
            },
            methods: {
                async saveSetting(){
                    for(let a in this.leader_setting) {
                        this.leader_setting[`leaderSetting.${a}`] = this.leader_setting[a]
                    }
                    let result = await api.npost('./!saveLeaderSetting',this.leader_setting)
                    if (result.code===0) {
                        alert("设置成功");
                    }else {
                        alert("设置失败");
                    }
                },
                issue(){
                    this.$confirm('此操作将停止本周的统计, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        api.npost("./!issue").then((data)=>{
                            if(data.code==0) {
                                this.$message("发放成功")
                            }else if(data.code==2) {
                                this.$message("本周已发放")
                            }else  {
                                this.$message("发放失败")
                            }
                        })
                    }).catch(() => {
                    
                    });
                }
            },
            created () {
            },
            mounted () {
                console.log(11)
            }
        }
    })
})();