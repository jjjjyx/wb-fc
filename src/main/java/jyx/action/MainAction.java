package jyx.action;


import jyx.common.Code;
import jyx.common.ResultUtils;

//import jyx.model.user.UserBean;
import jyx.model.UserBean;
import jyx.server.UserServer;
import org.apache.struts2.convention.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;

@Controller
@ParentPackage("default-package")
@Namespace("/")
@Action(value = "/", results = {
            @Result(name = "login", location = "./login.jsp"),
            @Result(name = "index", location = "./index.jsp"),
            @Result(name = "error", location = "./error.jsp")
        },
        interceptorRefs = {
            @InterceptorRef("userStack")
        }
)
public class MainAction extends BaseAction {
    private Exception exception;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserServer userServer;
    @Override
    public String execute() throws Exception {
        logger.info("欢迎访问 xx系统 v{}", 1.0);

        UserBean user = (UserBean) session.getAttribute("user");

        if (user != null) { //&& user.getRole() ==100//管理员
            if (logger.isDebugEnabled()) {
                logger.debug("欢迎您，用户 {} 已登录，将为您跳转到首页界面！", user.getUsername());
            }
            // 填充各项消息 5条
            request.setAttribute("news",userServer.getNews(5));
            // 运动知识 8 条
            request.setAttribute("lore",userServer.getLore(8));
            // 活动通知 最近3条
            request.setAttribute("recent_activity",userServer.getRecentActivity(3));
            // 运动图片 5张
            request.setAttribute("fc_img",userServer.getFCImg(5));
            // 材料 最近5个
            request.setAttribute("fc_data",userServer.getFCData(6));
            // 热门资料 6
            request.setAttribute("hot_data",userServer.getFCData(3));
            // 排行榜
            request.setAttribute("leader_board",userServer.getLeaderboard(10));
            return "index";
        }
//        Date start = (Date) session.getAttribute("vcStarrTime");
//        Date current = new Date();
//        if (start == null) {
//            start = current;
//        }

//        if (Utils.isAllow(start, 1)) {
//            if(logger.isDebugEnabled()) {
//                logger.debug("解除频繁登录限制");
//            }
//            session.removeAttribute("errorCount");
//        }
        return "login";
    }




    public String errorJson() {
        exception.printStackTrace();

        UserBean user = (UserBean) session.getAttribute("user");

        if (logger.isErrorEnabled()) {
            String action = request.getRequestURI();
            if (user == null) {
                logger.error("execution action [{}] => {} error: {}", action, request.getQueryString(), exception.getMessage(), exception);
            } else {
                logger.error("user [{}] execution action [{}] => {} error: {}", user.getUsername(), action, request.getQueryString(), exception.getMessage(), exception);
            }
        }
        data = new HashMap<String, Object>();
        // isSendErrorDetails() ? exception.getMessage() :
        ResultUtils.set(data, Code.ERROR);
        return JSON;
    }

    public String error403() {
        data = new HashMap<String, Object>();
        response.setStatus(403);
        ResultUtils.set(data, Code.LIMITER_ERROR);
        return JSON;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }


}
