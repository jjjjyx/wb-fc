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
        @Result(name = "home", location = "./home.jsp"),
        @Result(name = "user-info", location = "./user-info.jsp"),
        @Result(name = "my-ab", location = "./my-ab.jsp"),
        @Result(name = "my-ab", location = "./my-ab.jsp"),
        @Result(name = "adel", location = "/activity",type = "redirectAction"),
        @Result(name = "noUser", location = "/!execute.action",type = "redirectAction"),
        @Result(name = "activity", location = "./activity.jsp"),
        @Result(name = "fill_info", location = "./fill_info.jsp"),
        @Result(name = "fill_info_success", location = "/fill_info_success.jsp", type = "redirect"),
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
    private ActivityBean activity;
    private String fn;

    @Action(value = "user")
    public String user() {
        UserBean u = (UserBean) session.getAttribute("user");
        Map<String, Object> userBean = this.userServer.searchUser(uid,u);
        if(userBean !=null){
            request.setAttribute("userData",userBean);
            request.setAttribute("post_data",userServer.getPostData(u, _,10, (Integer) userBean.get("uid")));
        }else {
            return "noUser";
        }
        return "user-info";
    }
    @Action(value = "follow")
    public String follow(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,this.userServer.follow(u,this.uid));
        return JSON;
    }
    @Action(value = "unfollow")
    public String unfollow(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,this.userServer.unfollow(u,this.uid));
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
        if ("Post".equalsIgnoreCase(m)) {
            UserBean u = (UserBean) session.getAttribute("user");
            u = userServer.updateUser(u.getUid(), user);
            if (u != null) {
                // 将更新后的值重新放入
                session.setAttribute("user", u);
            }
            return "fill_info_success";
        }
        return "fill_info";
    }

    @Action(value = "activity")
    public String activity() {
        String m = request.getMethod();
        UserBean u =  (UserBean) session.getAttribute("user");
        if ("Post".equalsIgnoreCase(m)) {
            ResultUtils.set(data, userServer.releaseActivity(this.activity,u));
            return JSON;
        }else {
            request.setAttribute("activity_data",userServer.getCurrActivity(0,u,false));
        }
        return "activity";
    }

    @Action(value = "activity-sign")
    public String activitySign() {
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data, userServer.activitySign(this.id,u));
        return JSON;

    }
    @Action(value = "as2")
    public String unActivitySign() {
        UserBean u =  (UserBean) session.getAttribute("user");
        ResultUtils.set(data, userServer.activityUnSign(this.id,u));
        return JSON;
    }

    @Action(value = "adel")
    public String delActivity() {
//        UserBean u =  (UserBean) session.getAttribute("");
        userServer.delActivity(this.id);
        return "adel";
    }
    @Action(value = "down")
    public String down() {
        request.setAttribute("files",userServer.getFCData(0));
        return "down";
    }
    // 我参加的
    @Action(value = "my-ab")
    public String mbb() {
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        Object data = this.userServer.getMyAB(userBean);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        StringBuffer cc = new StringBuffer("window.__INIT=");
        cc.append(gson.toJson(data)).append(";");
        request.setAttribute("ab.data",cc);
        return "my-ab";
    }
    // 用户下载文件
    @Action(value = "uf")
    public String uf(){
        // 检查当前用户
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        Code code = this.userServer.downFile(userBean);
        ResultUtils.set(data,code);
        return JSON;
    }

    @Action(value = "my-aa")
    public String maa() {

        UserBean u =  (UserBean) session.getAttribute("user");
        request.setAttribute("activity_data",userServer.getCurrActivity(0,u, true));
        return "my-aa";
    }
    // 运动动态
    @Action(value = "home")
    public String home() {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("group_data",userServer.getGroup(0));
        request.setAttribute("post_data",userServer.getPostData(u, _,10, null));
        return "home";
    }
    @Action(value = "group")
    public String group() {
        request.setAttribute("hide_send",true);
        return this.home();
    }
    @Action(value = "post")
    public String post(){
//      发表动态
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.releasePost(post,u));

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
    // 评论
    @Action(value = "comment")
    public String comment(){
        String m = request.getMethod();
        UserBean u =  (UserBean) session.getAttribute("user");
        if ("Post".equalsIgnoreCase(m)) {
            CommentBean commentBean = new CommentBean();
            commentBean.setContent(this.content);
            ResultUtils.set(data,userServer.comment(this.c_id, u, commentBean));
        }else {
            ResultUtils.set(data, userServer.getComments(this.c_id, u));
        }
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

    public ActivityBean getActivity() {
        return activity;
    }

    public void setActivity(ActivityBean activity) {
        this.activity = activity;
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
}
