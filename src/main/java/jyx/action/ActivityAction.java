package jyx.action;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import jyx.common.ResultUtils;
import jyx.model.ActivityBean;
import jyx.model.CommentBean;
import jyx.model.UserBean;
import jyx.server.UserServer;
import org.apache.struts2.convention.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("default-package")
@Namespace("/activity")
@InterceptorRefs(@InterceptorRef(value = "userStack"))
@Results({
        @Result(name = "activity", location = "./activity.jsp"),
        @Result(name = "all", location = "./all.jsp"),
        @Result(name = "join", location = "./activity.jsp"),
        @Result(name = "found", location = "./activity.jsp"),
        @Result(name = "finish", location = "./activity.jsp"),
        @Result(name = "info", location = "./a-info.jsp"),
        @Result(name = "del", location = "all", type = "redirectAction"),
        @Result(name = "end", location = "all/${id}", type = "redirectAction"),
//        @Result(name = "user-activity-list", location = "./my-ab.jsp"),
//        @Result(name = "my-ab", location = "./my-ab.jsp"),
})
public class ActivityAction extends BaseAction {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserServer userServer;
    private CommentBean comment;
    private Integer id;
    private ActivityBean activity;
    private String c_id;
    @Action(value = "/{id}")
    public String info() throws Exception {
//        UserBean u = (UserBean) session.getAttribute("user");
        ActivityBean activityBean = userServer.queryActivityBean(this.id);
        if (activityBean==null) return "del";
        request.setAttribute("activityBean",activityBean);
        return "info";
    }
    //    @Override
    @Action(value = "all")
    public String all() throws Exception {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("activity_data", userServer.getCurrActivity(0, u, false));
        request.setAttribute("userBean",userServer.queryUserBean(u.getUid()));
        return "activity";
    }

    @Action(value = "end/{id}")
    public String end() throws Exception {
        UserBean u = (UserBean) session.getAttribute("user");
        userServer.endActivity(u,this.id);
        return "end";
    }

    // 删除活动
    @Action(value = "del/{id}")
    public String delActivity() {
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data,userServer.delActivity(id));
        return JSON;
    }

    @Action(value = "comment")
    public String comment(){
        UserBean u = (UserBean) session.getAttribute("user");
//        userServer.endActivity(u,this.id);
        ResultUtils.set(data, userServer.activityComment(u, this.comment));
        return JSON;
    }

    @Action(value = "save")
    public String save(){
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data, userServer.releaseActivity(this.activity, u));
        return JSON;
    }

    @Action(value = "join")
    public String join() {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("activity_data", userServer.getUserJoinActivity(u));
        request.setAttribute("userBean",userServer.queryUserBean(u.getUid()));
        return "activity";
    }
    // 我创建的
    @Action(value = "found")
    public String found() {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("activity_data", userServer.getCurrActivity(0,u,true));
        request.setAttribute("userBean",userServer.queryUserBean(u.getUid()));
        return "activity";
    }

    @Action(value = "finish")
    public String finish() {
        UserBean u = (UserBean) session.getAttribute("user");
        request.setAttribute("activity_data", userServer.getFinishActivity());
        request.setAttribute("userBean",userServer.queryUserBean(u.getUid()));
        return "activity";
    }




    // 活动报名
    @Action(value = "sign")
    public String activitySign() {
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data, userServer.activitySign(this.id, u));
        return JSON;

    }

    // 取消报名
    @Action(value = "unsign")
    public String unActivitySign() {
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data, userServer.activityUnSign(this.id, u));
        return JSON;
    }

//    // 我参加的
//    @Action(value = "user")
//    public String user() {
//        UserBean userBean = (UserBean) this.session.getAttribute("user");
//        Object data = this.userServer.getMyAB(userBean);
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        StringBuffer cc = new StringBuffer("window.__INIT=");
//        cc.append(gson.toJson(data)).append(";");
//        request.setAttribute("ab.data",cc);
//        return "user-activity-list";
//    }

//    @Action(value = "my-aa")
//    public String maa() {
//        UserBean u =  (UserBean) session.getAttribute("user");
//        request.setAttribute("activity_data",userServer.getCurrActivity(0, u, true));
//        return "my-aa";
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ActivityBean getActivity() {
        return activity;
    }

    public void setActivity(ActivityBean activity) {
        this.activity = activity;
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
}
