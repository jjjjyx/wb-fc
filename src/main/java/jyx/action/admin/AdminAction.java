package jyx.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jyx.action.BaseAction;
import jyx.common.Code;
import jyx.common.ResultUtils;
import jyx.dao.DataDao;
import jyx.dao.IntegralSetDao;
import jyx.model.ActivityBean;
import jyx.model.GroupBean;
import jyx.model.IntegralSet;
import jyx.model.LoreBean;
import jyx.model.NewsBean;
import jyx.model.UserBean;
import jyx.server.AdminServer;

/**
 * 管理员 相关操作
 */
@Controller
@ParentPackage("default-package")
@Namespace("/admin")
@Action(value = "",
        results = {
                @Result(name = "index", location = "/admin/index.jsp"),
                @Result(name = "integralSet", location = "/admin/pages/integralSet.jsp")
        }, interceptorRefs = {@InterceptorRef("adminStack")
})
public class AdminAction extends BaseAction {
    @Autowired
    private AdminServer adminServer;
    private int id;
    private int del_id;
    private UserBean user;
    private ActivityBean activity;
    private GroupBean group;
    private NewsBean news;
    private LoreBean lore;
    private int[] uids;
    private String[] fns;
    private String media;
    private DataDao dataDao = DataDao.getInstance();

    private IntegralSet integralSet;
    private IntegralSet leaderSetting;

    @Autowired
    public IntegralSetDao integralSetDao;

    public String integralSet() {
        request.setAttribute("integralSet", integralSetDao.getIntegralSet());
        return "integralSet";
    }

    public String saveLeaderSetting(){
        ResultUtils.set(this.data, adminServer.saveLeaderSetting(leaderSetting));
        return JSON;
    }

    public void addIntegralSet() throws Exception {
        Map<String, Object> oldIntegralSet = integralSetDao.getIntegralSet();
        boolean b = true;
        if (oldIntegralSet != null) {
            b = integralSetDao.updateIntegralSet(integralSet);
        } else {
            b = integralSetDao.addIntegralSet(integralSet);
        }
        Map<String, Object> msg = new HashMap<String, Object>();
        if (b) {
            msg.put("msg", "操作成功！！！");
        } else {
            msg.put("msg", "操作失败！！！");
        }
        Gson gson = new Gson();
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.write(gson.toJson(gson));
        out.flush();
        out.close();
    }

    public String execute() throws Exception {
        Map data = this.adminServer.getData();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        data.put("user", userBean);
        StringBuffer cc = new StringBuffer("window.__INIT=");
        cc.append(gson.toJson(data)).append(";");
        request.setAttribute("admin.data", cc);
        return "index";
    }

    public String addActivityMedia() {
        ResultUtils.set(this.data, adminServer.addActivityMedia(this.id, media));
        return JSON;
    }

    /* ======== UserBean*/
    public String resetPass() {
        ResultUtils.set(this.data, adminServer.resetPass(uids));
        return JSON;
    }

    public String getAllUser() {
        ResultUtils.set(this.data, adminServer.getAllUser());
        return JSON;
    }

    public String delUser() {
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        if (del_id == userBean.getUid()) {
            ResultUtils.set(data, -5, "无法删除自己");
        } else {
            ResultUtils.set(data, this.adminServer.delUser(this.del_id));
        }
        return JSON;
    }

    public String addUser() {
        Code code = adminServer.add(user);
        ResultUtils.set(data, code, user);
        return JSON;
    }

    public String updateUser() {

        ResultUtils.set(data, adminServer.update(user));
        return JSON;
    }

    /* ======== ActivityBean*/
    public String getAllActivity() {
        ResultUtils.set(this.data, adminServer.getAllActivity());
        return JSON;
    }

    public String delActivity() {
        ResultUtils.set(data, this.adminServer.delActivity(this.uids));
        return JSON;
    }

    public String addActivity() {
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        activity.setUid(userBean);
        Code code = adminServer.add(activity);
        ResultUtils.set(data, code, activity);
        return JSON;
    }

    public String updateActivity() {
        ResultUtils.set(data, adminServer.update(activity));
        return JSON;
    }

    /* ======== GroupBean*/
    public String getAllGroup() {
        ResultUtils.set(this.data, adminServer.getAllGroup());
        return JSON;
    }

    public String delGroup() {
        ResultUtils.set(data, this.adminServer.delGroup(this.uids));
        return JSON;
    }

    public String addGroup() {
        Code code = adminServer.add(group);
        ResultUtils.set(data, code, group);
        return JSON;
    }

    public String updateGroup() {
        ResultUtils.set(data, adminServer.update(group));
        return JSON;
    }

    /* ======== LoreBean*/
    public String getAllLore() {
        ResultUtils.set(this.data, adminServer.getAllLore());
        return JSON;
    }

    public String delLore() {
        ResultUtils.set(data, this.adminServer.delLore(this.uids));
        return JSON;
    }

    public String addLore() {
        Code code = adminServer.add(lore);
        ResultUtils.set(data, code, lore);
        return JSON;
    }

    public String updateLore() {
        ResultUtils.set(data, adminServer.update(lore));
        return JSON;
    }

    /* ======== NewsBean*/
    public String getAllNews() {
        ResultUtils.set(this.data, adminServer.getAllNews());
        return JSON;
    }

    public String delNews() {
        ResultUtils.set(data, this.adminServer.delNews(this.uids));
        return JSON;
    }

    public String addNews() {
        UserBean userBean = (UserBean) this.session.getAttribute("user");
        news.setUid(userBean);
        Code code = adminServer.add(news);
        ResultUtils.set(data, code, news);
        return JSON;
    }

    public String updateNews() {
        ResultUtils.set(data, adminServer.update(news));
        return JSON;
    }

    public String delFiles() {
        ResultUtils.set(data, adminServer.delFCData(this.uids));
        return JSON;
    }

    public String issue() {
        ResultUtils.set(data, adminServer.issue());
        return JSON;
    }

    public String delImg() {
        ResultUtils.set(data, dataDao.delImg(this.fns));
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

    public String[] getFns() {
        return fns;
    }

    public void setFns(String[] fns) {
        this.fns = fns;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IntegralSet getIntegralSet() {
        return integralSet;
    }

    public void setIntegralSet(IntegralSet integralSet) {
        this.integralSet = integralSet;
    }


    public IntegralSet getLeaderSetting() {
        return leaderSetting;
    }

    public void setLeaderSetting(IntegralSet leaderSetting) {
        this.leaderSetting = leaderSetting;
    }
}
