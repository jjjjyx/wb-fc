package jyx.action;

import jyx.model.NewsBean;
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
}
