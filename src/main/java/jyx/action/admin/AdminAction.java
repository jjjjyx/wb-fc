package jyx.action.admin;

import jyx.action.BaseAction;

import jyx.common.ResultUtils;
import jyx.model.ActivityBean;
import jyx.model.GroupBean;
import jyx.model.NewsBean;
import jyx.model.UserBean;
import jyx.server.AdminServer;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * 管理员 相关操作
 */
@Controller
@ParentPackage("default-package")
@Namespace("/admin")
@Action(value = "",
        results = {@Result(name = "index", location = "/admin/index.jsp")
        }, interceptorRefs = {@InterceptorRef("adminStack")
})
public class AdminAction extends BaseAction {
    @Autowired
    private AdminServer adminServer;
    private int del_id;
    private UserBean user;
    private ActivityBean activity;
    private GroupBean group;
    private NewsBean news;

    @Override
    public String execute() throws Exception {
        System.out.println("访问后台管理界面");
        return "index";
    }

    /* ======== UserBean*/
    public String getAllUser(){
        ResultUtils.set(this.data, adminServer.getAllUser());
        return JSON;
    }
    public String delUser(){
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        if(del_id==userBean.getUid()) {
            ResultUtils.set(data,-5,"无法删除自己");
        }else {
            ResultUtils.set(data, this.adminServer.delUser(this.del_id));
        }
        return JSON;
    }
    public String addUser(){
        ResultUtils.set(data,adminServer.add(user));
        return JSON;
    }
    public String updateUser(){
        ResultUtils.set(data,adminServer.update(user));
        return JSON;
    }
    /* ======== ActivityBean*/
    public String getAllActivity(){
        ResultUtils.set(this.data, adminServer.getAllActivity());
        return JSON;
    }

    public String delActivity(){
        ResultUtils.set(data, this.adminServer.delActivity(this.del_id));
        return JSON;
    }

    public String addActivity(){
        ResultUtils.set(data,adminServer.add(activity));
        return JSON;
    }
    public String updateActivity(){
        ResultUtils.set(data,adminServer.update(activity));
        return JSON;
    }

    /* ======== GroupBean*/
    public String getAllGroup(){
        ResultUtils.set(this.data, adminServer.getAllGroup());
        return JSON;
    }

    public String delGroup(){
        ResultUtils.set(data, this.adminServer.delGroup(this.del_id));
        return JSON;
    }

    public String addGroup(){
        ResultUtils.set(data,adminServer.add(group));
        return JSON;
    }
    public String updateGroup(){
        ResultUtils.set(data,adminServer.update(group));
        return JSON;
    }

    /* ======== NewsBean*/
    public String getAllNews(){
        ResultUtils.set(this.data, adminServer.getAllNews());
        return JSON;
    }

    public String delNews(){
        ResultUtils.set(data, this.adminServer.delNews(this.del_id));
        return JSON;
    }

    public String addNews(){
        ResultUtils.set(data,adminServer.add(news));
        return JSON;
    }
    public String updateNews(){
        ResultUtils.set(data,adminServer.update(news));
        return JSON;
    }

    public int getDel_id() {
        return del_id;
    }

    public void setDel_id(int del_id) {
        this.del_id = del_id;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public ActivityBean getActivity() {
        return activity;
    }

    public void setActivity(ActivityBean activity) {
        this.activity = activity;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }
}
