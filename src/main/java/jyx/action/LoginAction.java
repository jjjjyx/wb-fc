package jyx.action;

import jyx.common.Code;
import jyx.common.ResultUtils;
import jyx.model.UserBean;
import jyx.server.UserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

@Controller
@ParentPackage("default-package")
@Namespace("/sign")
@Results({
        @Result(name = "success", location = "./login.jsp"),
        @Result(name = "up", location = "./sign-up.jsp"),
        @Result(name = "out", location = "../out.jsp"),
})
public class LoginAction extends BaseAction {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserServer userService;
    private String username;
    private String password;
//    private UserBean userBean;


    public String execute() throws Exception {
        System.out.println("访问登录页面");
        return SUCCESS;
    }

//    public String signup() {
//        return "up";
//    }
    @Action(value = "in")
    public String in() {
        if (logger.isDebugEnabled())
            logger.debug("用户[{}] 请求登录", this.getUsername());

        UserBean userBean = userService.getUser(username, password);
        data = new HashMap<String, Object>();

        if (userBean == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("用户[{}] 登录失败", this.getUsername());
            }
            data.put("code", 3);
            data.put("msg", "登录失败");
        } else {
            // 用户登录成功
            // 输出到日志
            logger.info("user [{}] 登录成功", userBean.getUsername());
            session.setAttribute("user", userBean);
            data.put("code", 0);
            data.put("msg", "登录成功");
        }
        return JSON;
    }
    @Action(value = "out")
    public String out() {
        session.removeAttribute("user");
        session.invalidate();
        return "out";
    }
    @Action(value = "up")
    public String up() {
        if (userService.checkUserName(getUsername())) {
            UserBean userBean = userService.signUp(this.getUsername(), this.getPassword());
            session.setAttribute("user", userBean);
            ResultUtils.set(data, Code.SUCCESS);
        } else {
            ResultUtils.set(data, 3, "账号已被注册");
        }
        return JSON;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
