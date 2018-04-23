!+(function () {
const template =
`<div>
   <hr>
    <send v-if="t_position=='top'" :form="form" url="comment"></send>
    <ul class="am-comments-list am-comments-list-flip">
        <li class="am-comment" v-for="(item,index) in comments">
            <a href="#link-to-user-home">
                <img :src="\`assets/img/user (\${item.uid.uid % 28}).png\`" alt="" class="am-comment-avatar" width="48" height="48"/>
            </a>
            <div class="am-comment-main">
                <header class="am-comment-hd">
                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                    <div class="am-comment-meta">
                        <a href="#link-to-user" class="am-comment-author" @click="at(item)">{{item.uid.nickname}}</a>
                        评论于 <time :datetime="item.releaseTime" title="">{{item.releaseTime}}</time>
                    </div>
                    <div class="am-comment-actions">
                    <a v-if="item.uid.uid==cur" @click="del(item,index)"><i class="am-icon-close"></i></a>
                </div>
                </header>
                <div class="am-comment-bd" v-html="f(item.content)"></div>
            </div>
        </li>
        <li v-if="!comments.length">
            暂无评论
        </li>
    </ul>
    <send v-if="t_position=='bottom'" :form="form" url="comment" ></send>
</div>
`;
    define(function (require) {
        let api = require('js/api')
        let Send = require('js/send')
        return {
            template,
            name: 'comment',
            data: function () {
                return {
                    t_position:this.position || 'top',
                    form:{
                        'content':'',
                        'c_id': this.commentId
                    },
                    comments:[]
                }
            },
            props:['commentId','cur','position'],
            components: {Send},
            computed: {},
            methods: {
                at(item){
                    this.form.content +=`@${item.uid.username} `
                },
                f(content){
                    content = content.replace(/(@\w+)/ig,"<a style='color: #095f8a;cursor: pointer'>$1</a>")
                    return content;
                },
                fetch(){
                    if (this.commentId)
                    api.nget("comment",{c_id:this.commentId}).then((data)=>{
                        this.comments = data.data;
                    })
                },
                del (item,index) {
                    api.npost("del-comment",{id:item.id}).then((data)=>{
                        console.log(data)
                        if (data.code==0) {
                            this.comments.splice(index, 1)
                            this.$message("删除成功")
                        }else {
                            this.$message("删除失败")
                        }
                    })
                    
                }
            },
            created () {
            },
            watch:{
                commentId(v){
                    this.fetch()
                }
            },
            mounted () {
                // 获取评论列表
                this.fetch();
            }
        }
    })
})();