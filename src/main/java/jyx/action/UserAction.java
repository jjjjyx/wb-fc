package jyx.action;

import jyx.common.ResultUtils;
import jyx.model.CommentBean;
import jyx.model.NewsBean;
import jyx.model.PostBean;
import jyx.model.UserBean;
import jyx.server.AdminServer;
import jyx.server.UserServer;
import org.apache.struts2.convention.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("default-package")
@Namespace("/")
@InterceptorRefs(@InterceptorRef(value = "userStack"))
@Results({
        @Result(name = "down", location = "./down.jsp"),
        @Result(name = "news", location = "./news.jsp"),
        @Result(name = "home", location = "./home.jsp"),
        @Result(name = "fill_info", location = "./fill_info.jsp"),
        @Result(name = "fill_info_success", location = "/fill_info_success.jsp", type = "redirect"),
        @Result(name = "news_article", location = "./news_article.jsp")
})
public class UserAction extends BaseAction {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserServer userServer;

    private Integer id;
    private UserBean user;
    private PostBean post;
    private CommentBean commentBean;
    private String _;
    private String c_id;
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

    @Action(value = "down")
    public String down() {
        return "down";
    }
    // 运动动态
    @Action(value = "home")
    public String home() {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("group_data",userServer.getGroup(0));
        request.setAttribute("post_data",userServer.getPostData(u, _,10));
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
        UserBean u =  (UserBean) session.getAttribute("user");

        ResultUtils.set(data,userServer.comment(this.c_id, u, this.commentBean));
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

    public CommentBean getCommentBean() {
        return commentBean;
    }

    public void setCommentBean(CommentBean commentBean) {
        this.commentBean = commentBean;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
