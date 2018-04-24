!+(function () {
const template =
`<div>
   <hr>
    <send v-if="t_position=='top'" :form="form" url="comment"></send>
    <ul class="am-comments-list am-comments-list-flip">
        <li class="am-comment" v-for="(item,index) in comments">
            <a href="#link-to-user-home">
                <img :src="\`\${path}/assets/img/user (\${item.uid.uid % 28}).png\`" alt="" class="am-comment-avatar" width="48" height="48"/>
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
                <div class="am-comment-bd" >
                    <div class="WB_text W_f14" v-html="f(item.content)"></div>
                    <div class="WB_media_wrap am-cf" v-if="item.imgs">
                         <div class="media_box">
                             <ul :class="'WB_media_a_mn  am-cf WB_media_a_m'+item.imgs.length">
                                    <li class="WB_pic li_1 S_bg1 S_line2 bigcursor li_n_mix_w" v-for="img in item.imgs">
                                        <img :src="\`\${path}/dist/\${img}\`" alt="">
                                    </li>
                             </ul>
                         </div>
                    </div>
                    <div class="WB_media_wrap am-cf" v-if="item.mp4s">
                         <div class="media_box">
                             <ul :class="'WB_media_a_mn  am-cf WB_media_a_m'+item.mp4s.length">
                                    <li class="WB_video  S_bg1 WB_video_mini WB_video_h5" v-for="img in item.mp4s">
                                        <div class="WB_h5video">
                                            <video alt="" controls="controls">
                                                <source :src="path+'dist/'+img" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'>
                                            </video>
                                        </div>
                                    </li>
                             </ul>
                         </div>
                    </div>
                </div>
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
        let path = window.config.remote.root +"/";
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
                    comments:[],
                    path
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
                    api.nget(`${path}/comment`, {c_id:this.commentId}).then((data)=>{
                        this.comments = data.data.map((item)=>{
                            if (item.media && item.media.length) {
                                let imgs = []
                                let mp4s = []
                                item.media.forEach((s)=>{
                                    if (s.endsWith(".mp4") || s.endsWith(".MP4")) {
                                        mp4s.push(s)
                                    }else {
                                        imgs.push(s)
                                    }
                                })
                                item.mp4s = mp4s
                                item.imgs = imgs
                            }
                            return item;
                        });
                        console.log(this.comments)
                    })
                },
                del (item,index) {
                    api.npost(`${path}/del-comment`,{id:item.id}).then((data)=>{
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