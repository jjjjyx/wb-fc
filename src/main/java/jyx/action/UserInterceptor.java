package jyx.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import jyx.model.UserBean;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UserInterceptor  extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String,Object> session=actionInvocation.getInvocationContext().getSession();
        UserBean userBean = (UserBean) session.get("user");
//        System.out.println(session+"=======");
        if(userBean== null ) return "session-null";
        else if(userBean.getRole()>=0){
            // 正常访问
            return actionInvocation.invoke();
        }else if(userBean.getRole()==-50){
            // 完善信息
            return "completion-info";
        } else if(userBean.getRole()==-100) {
            // 禁止登陆
//            session.remove("user");
            return "session-null";
        }else {
            return "session-null";
        }

    }
}