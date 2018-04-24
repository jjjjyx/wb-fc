package jyx.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import jyx.model.UserBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.impl.StrutsActionProxy;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UserInterceptor  extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
//        ActionProxy strutsActionProxy = actionInvocation.getProxy();
//        System.out.println("strutsActionProxy.getActionName() = " + strutsActionProxy.getActionName());
//        System.out.println("strutsActionProxy.getNamespace() = " + strutsActionProxy.getNamespace());
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