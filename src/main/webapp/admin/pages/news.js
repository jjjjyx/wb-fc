!+(function () {
    define(function (require) {
        let api = require('js/api')
        //let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        const store = require('../admin-store.js')
        return {
            template: require('dom!pages/news.html'),
            name: 'news.js',
            data: function () {
                return {
                    store,
                    currPage: 1,
                    dialogVisible: false,
                    currData: null,
                    active: '',
                    form: {
                        'news.id': '',
                        'news.title': '',
                        'news.content': '',
                        'news.author': store.states.user.username
                    },
                    multipleSelection: []
                }
            },
            components: {},
            computed: {
                newsList () {
                    let start = (this.currPage - 1) * 10
                    let end = start + 10
                    return this.store.states.news_data.slice(start, end)
                }
            },
            methods: {
                handleClose () {
                    this.resetFrom()
                },
                async deleteNews () {
                    let uids = this.multipleSelection.map(item => item.id)
                    
                    let result = await api.npost('./!delNews', {uids})
                    this.$message(result.msg)
                    if (result.code == 0) {
                        this.multipleSelection.forEach((item) => {
                            this.store.commit('deleteNews', item)
                        })
                    }
                    
                },
                resetFrom () {
                    this.form['news.id'] = ''
                    this.form['news.title'] = ''
                    this.form['news.content'] = ''
                    this.form['news.author'] = this.store.states.user.username
                },
                async save () {
                    let result = await api.npost('./!' + this.active, this.form)
                    if (result.code == 0) {
                        let obj = this.currData = this.currData || {}
                        obj.id = this.form['news.id']
                        obj.title = this.form['news.title']
                        obj.content = this.form['news.content']
                        obj.author = this.form['news.author']
                        
                        if (this.active == 'addNews') {
                            obj = result.data
                            this.store.commit('addNews', obj)
                        }
                        this.active = ''
                        this.$message(result.msg)
                    }
                    this.dialogVisible = false
                },
                newNews () {
                    this.active = 'addNews'
                },
                editNews (data) {
                    this.form['news.id'] = data.id
                    this.form['news.title'] = data.title
                    this.form['news.content'] = data.content
                    this.form['news.author'] = this.store.states.user.username
                    this.active = 'updateNews'
                }
            },
            watch: {},
            created () {
            },
            mounted () {
            }
        }
    })
})()