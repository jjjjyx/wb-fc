package jyx.action.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jyx.action.BaseAction;

import jyx.common.Code;
import jyx.common.ResultUtils;
import jyx.model.*;
import jyx.server.AdminServer;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Date;
import java.util.Map;

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
    private LoreBean lore;
    private int[] uids;
    @Override
    public String execute() throws Exception {
        Map data = this.adminServer.getData();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        data.put("user",userBean);
        StringBuffer cc = new StringBuffer("window.__INIT=");
        cc.append(gson.toJson(data)).append(";");
        request.setAttribute("admin.data", cc);
        return "index";
    }

    /* ======== UserBean*/

    public String resetPass(){
        ResultUtils.set(this.data, adminServer.resetPass(uids));
        return JSON;
    }
    public String getAllUser(){
        ResultUtils.set(this.data, adminServer.getAllUser());
        return JSON;
    }
    public String delUser(){
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        if(del_id==userBean.getUid()) {
            ResultUtils.set(data,-5,"无法删除自己");
        } else {
            ResultUtils.set(data, this.adminServer.delUser(this.del_id));
        }
        return JSON;
    }
    public String addUser(){
        Code code= adminServer.add(user);
        ResultUtils.set(data, code, user);
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
        ResultUtils.set(data, this.adminServer.delActivity(this.uids));
        return JSON;
    }

    public String addActivity(){
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        activity.setUid(userBean);
        Code code= adminServer.add(activity);
        ResultUtils.set(data, code, activity);
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
        ResultUtils.set(data, this.adminServer.delGroup(this.uids));
        return JSON;
    }

    public String addGroup(){
        Code code= adminServer.add(group);
        ResultUtils.set(data, code, group);
        return JSON;
    }
    public String updateGroup(){
        ResultUtils.set(data,adminServer.update(group));
        return JSON;
    }

    /* ======== LoreBean*/
    public String getAllLore(){
        ResultUtils.set(this.data, adminServer.getAllLore());
        return JSON;
    }

    public String delLore(){
        ResultUtils.set(data, this.adminServer.delLore(this.uids));
        return JSON;
    }

    public String addLore(){
        Code code= adminServer.add(lore);
        ResultUtils.set(data, code, lore);
        return JSON;
    }
    public String updateLore(){
        ResultUtils.set(data,adminServer.update(lore));
        return JSON;
    }

    /* ======== NewsBean*/
    public String getAllNews(){
        ResultUtils.set(this.data, adminServer.getAllNews());
        return JSON;
    }

    public String delNews(){
        ResultUtils.set(data, this.adminServer.delNews(this.uids));
        return JSON;
    }

    public String addNews(){
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        news.setUid(userBean);
        Code code= adminServer.add(news);
        ResultUtils.set(data, code, news);
        return JSON;
    }
    public String updateNews(){
        ResultUtils.set(data,adminServer.update(news));
        return JSON;
    }

    public int[] getUids() {
        return uids;
    }

    public void setUids(int[] uids) {
        this.uids = uids;
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

    public LoreBean getLore() {
        return lore;
    }

    public void setLore(LoreBean lore) {
        this.lore = lore;
    }
}
