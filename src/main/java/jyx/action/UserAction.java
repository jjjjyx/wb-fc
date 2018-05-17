package jyx.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jyx.common.Code;
import jyx.common.ResultUtils;
import jyx.model.*;
import jyx.server.UserServer;
import org.apache.struts2.convention.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@ParentPackage("default-package")
@Namespace("/")
@InterceptorRefs(@InterceptorRef(value = "userStack"))
@Results({
        @Result(name = "down", location = "./down.jsp"),
        @Result(name = "news", location = "./news.jsp"),
        @Result(name = "mood", location = "./home.jsp"),
        @Result(name = "user-info", location = "./user-info.jsp"),
        @Result(name = "people", location = "./people.jsp"),
        @Result(name = "inbox", location = "./inbox.jsp"),
        @Result(name = "integral", location = "./integral.jsp"),
        @Result(name = "noUser", location = "./",type = "redirectAction"),
        @Result(name = "activity", location = "./activity/all",type = "redirectAction"),
        @Result(name = "lore", location = "./lore.jsp"),
        @Result(name = "fill_info", location = "./fill_info.jsp"),
        @Result(name = "fill_info_success", location = "./fill_info_success", type = "redirect"),
        @Result(name = "news_article", location = "./news_article.jsp")
})
public class UserAction extends BaseAction {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserServer userServer;

    private Integer id;
    private Integer uid;
    private UserBean user;
    private PostBean post;
    private CommentBean comment;
    private String content;
    private String _;
    private String c_id;
    private String fn;
    private String query;
    private InboxBean inbox;
    private String password;
    private String cpass;


    @Action("changepass")
    public String changepass(){
        String method = request.getMethod();
        UserBean userBean = (UserBean) session.getAttribute("user");
        if ("post".equalsIgnoreCase(method)) {
            ResultUtils.set(data,userServer.changePass(userBean.getUid(),password));
            session.removeAttribute("user");
            session.invalidate();
            return JSON;
        }
        return "user";
    }

    // 查看用户信息
    @Action(value = "user/{uid}")
    public String user() {
        UserBean u = (UserBean) session.getAttribute("user");
        Map<String, Object> userBean = this.userServer.searchUser(this.uid,u);
        if(userBean !=null){
            request.setAttribute("userData",userBean);
            request.setAttribute("post_data",userServer.getPostData(u, _,10, (Integer) userBean.get("uid"), null));
        }else {
            return "noUser";
        }
        return "user-info";
    }
    @Action("activity")
    public String activity(){
        return "activity";
    }
    @Action(value = "search_user")
    public String searchUser(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,this.userServer.searchUsers(u,this.query));
        return JSON;
    }



    @Action(value = "user_follow")
    public String userFollow(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,this.userServer.follows(u));
        return JSON;
    }
    @Action(value = "follow")
    public String follow(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,this.userServer.follow(u,this.uid));
        return JSON;
    }
    @Action(value = "unfollow")
    public String unFollow(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,this.userServer.unfollow(u,this.uid));
        return JSON;
    }
    // 运动知识页面
    @Action(value = "lore")
    public String lore(){
        if (id != null) {
            LoreBean news = userServer.getLoreById(id);
            if (news != null) {
                request.setAttribute("lore_date", news);
                return "lore";
            }
        }
        return "noUser";
    }
    @Action(value = "integral")
    public String integral(){
        UserBean userBean = (UserBean) session.getAttribute("user");
        //request.setAttribute("leader_board",userServer.getFollowLeaderboard(userBean));
        request.setAttribute("leader_board",this.userServer.follows(userBean));
        request.setAttribute("integral_data",userServer.getAllintegral(userBean));
        return "integral";
    }

    @Action(value = "people")
    public String people(){
        return "people";
    }

    @Action(value = "inbox")
    public String inbox(){
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("inboxs_data",userServer.getUserInbox(u));
        return "inbox";
    }

    @Action(value = "send-private")
    public String sendInbox(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,this.userServer.sendInbox(u,this.inbox,this.uid));
        return JSON;
    }

    /**
     * 完善用户信息
     *
     * @return
     */
    @Action(value = "fill")
    public String fill_info() {
        String m = request.getMethod();
            UserBean u = (UserBean) session.getAttribute("user");
        if ("Post".equalsIgnoreCase(m)) {
            u = userServer.updateUser(u.getUid(), user);
            if (u != null) {
                // 将更新后的值重新放入
                session.setAttribute("user", u);
            }
            return "fill_info_success";
        }
        request.setAttribute("leader_board",this.userServer.follows(u));
        //request.setAttribute("leader_board",userServer.getFollowLeaderboard(u));
        return "fill_info";
    }

    // 进入下载页面
    @Action(value = "down")
    public String down() {
        request.setAttribute("files",userServer.getFCData(0));
        return "down";
    }

    // 用户下载文件
    @Action(value = "uf")
    public String uf(){
        // 检查当前用户
        UserBean userBean = (UserBean) session.getAttribute("user");
        Code code = this.userServer.downFile(userBean, id);
        ResultUtils.set(data,code);
        return JSON;
    }


    // 运动动态
    @Action(value = "mood")
    public String mood() {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("post_data",userServer.getPostData(u, _,10, null,PostType.mood));
        request.setAttribute("mode", PostType.mood);
        return "mood";
    }
    // 圈子
    @Action(value = "group")
    public String group() {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("post_data",userServer.getPostData(u, this._,10, null, PostType.group));
        request.setAttribute("group_data",userServer.getGroup(0));
        request.setAttribute("mode", PostType.group);
        return "mood";
    }

    @Action(value = "delPost/{id}")
    public String delPost(){
//      删除动态
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.delPost(id, u));

        return JSON;
    }
    @Action(value = "post")
    public String post(){
//      发表动态
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.releasePost(post, u));

        return JSON;
    }

    // 私密
    @Action(value = "priv")
    public String priv(){
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.priv(this.id, u));
        return JSON;
    }


    // 公开
    @Action(value = "pub")
    public String pub(){
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.pub(this.id, u));
        return JSON;
    }

    // 收藏 动态
    @Action(value = "star")
    public String star(){
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.star(this.id, u));
        return JSON;
    }

    // 点赞动态
    @Action(value = "thumbs_up")
    public String thumbs_up(){
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.thumbs_up(this.id, u));
        return JSON;
    }

    // 点赞动态
    @Action(value = "un-thumbs_up")
    public String unThumbs_up(){
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.unThumbs_up(this.id, u));
        return JSON;
    }
    // 评论
    @Action(value = "comment")
    public String comment(){
        String m = request.getMethod();
        UserBean u =  (UserBean) session.getAttribute("user");
        if ("Post".equalsIgnoreCase(m)) {
            CommentBean commentBean = new CommentBean();
            commentBean.setContent(this.content);
            ResultUtils.set(data,userServer.comment(this.c_id, u, commentBean));
        } else {
            ResultUtils.set(data, userServer.getComments(this.c_id, u));
        }
        return JSON;
    }
    @Action(value = "del-comment")
    public String delComment(){
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.delComment(this.id, u));
        return JSON;
    }
    @Action(value = "news")
    public String news() {
        if (id != null) {
            NewsBean news = userServer.getNewsById(id);
            if (news != null) {
                request.setAttribute("new_date", news);
                return "news_article";
            }
        }
        request.setAttribute("new_date", userServer.getNews(0));

        return "news";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public String get_() {
        return _;
    }

    public void set_(String _) {
        this._ = _;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public InboxBean getInbox() {
        return inbox;
    }

    public void setInbox(InboxBean inbox) {
        this.inbox = inbox;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
