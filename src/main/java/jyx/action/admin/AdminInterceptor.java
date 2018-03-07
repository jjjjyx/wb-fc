package jyx.action.admin;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import jyx.model.UserBean;

import java.util.Map;

public class AdminInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String,Object> session=actionInvocation.getInvocationContext().getSession();
        UserBean userBean = (UserBean) session.get("user");
//        System.out.println(session+"=======");
        if(userBean== null ) return "session-null";
        if(userBean.getRole()==100) {
            return actionInvocation.invoke();
        }else {
            return "access-fail";
        }

    }
}
