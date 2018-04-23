!+(function () {
    define(function (require) {
        let api = require('js/api')
        let Vue = require('vue')
        //let VueRouter = require('js/lib/vue-router.min')
        //let validator = require('js/lib/vue-validator.min')
        //let ELEMENT = require('ELEMENT')
        //let cookie = require('js/lib/js.cookie')
        function down(url){
            let ifa = document.createElement("iframe")
            ifa.setAttribute("id","down-file-iframe");
            
            let ifr = document.createElement("form")
            ifr.setAttribute("target","down-file-iframe")
            ifr.setAttribute("method","post");
            ifr.setAttribute("action",url);
            
            
            ifa.appendChild(ifr)
            document.body.appendChild(ifa)
            ifr.submit();
            document.body.removeChild(ifa);
            
        }
        let headerFrom = require("js/header-from")
        const app = new Vue({
            mixins:[headerFrom],
            el: '#app',
            name: 'down',
            data: function () {
                return {
                    name: 'hello world!'
                }
            },
            components: {},
            computed: {},
            methods: {
                down(fn){
                    api.npost("uf",{fn}).then((data)=>{
                        if(data.code==0){
                            //window.
                            down(`file!down?fn=${fn}`)
                        }else if(data.code==2){
                            alert("积分不足")
                        }else {
                            alert(data.msg)
                        }
                    })
                }
            },
            created () {
            },
            mounted () {
            }
        })
    })
})();