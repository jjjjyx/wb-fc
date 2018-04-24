package jyx.action;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import jyx.common.ResultUtils;
import jyx.model.ActivityBean;
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
        @Result(name = "join", location = "./join.jsp"),
        @Result(name = "found", location = "./found.jsp"),
        @Result(name = "finish", location = "./finish.jsp"),
        @Result(name = "del", location = "all", type = "redirectAction"),
//        @Result(name = "user-activity-list", location = "./my-ab.jsp"),
//        @Result(name = "my-ab", location = "./my-ab.jsp"),
})
public class ActivityAction extends BaseAction {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserServer userServer;

    private Integer id;
    private ActivityBean activity;

    //    @Override
    @Action(value = "all")
    public String all() throws Exception {
        String m = request.getMethod();
        UserBean u = (UserBean) session.getAttribute("user");
        if ("Post".equalsIgnoreCase(m)) {
            ResultUtils.set(data, userServer.releaseActivity(this.activity, u));
            return JSON;
        } else {
            request.setAttribute("activity_data", userServer.getCurrActivity(0, u, false));
        }
        return "activity";
    }

    @Action(value = "join")
    public String join() {
        return "join";
    }

    @Action(value = "found")
    public String found() {
        return "found";
    }

    @Action(value = "finish")
    public String finish() {
        return "finish";
    }


    // 删除活动
    @Action(value = "del/{id}")
    public String delActivity() {
        System.out.println("del/id = " + id);
//        UserBean u =  (UserBean) session.getAttribute("");
//        userServer.delActivity(this.id);
        return "del";
    }

    // 活动报名
    @Action(value = "sign")
    public String activitySign() {
        UserBean u = (UserBean) session.getAttribute("user");
        ResultUtils.set(data, userServer.activitySign(this.id, u));
        return JSON;

    }

    // 取消报名
    @Action(value = "un_sign")
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
//        request.setAttribute("activity_data",userServer.getCurrActivity(0,u, true));
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
}
