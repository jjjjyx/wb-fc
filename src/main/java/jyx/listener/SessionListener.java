package jyx.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private ServletContext application = null;
    private static final Logger logger = LoggerFactory
            .getLogger("jyx.action");


    // 这个方法在Web应用服务被移除，没有能力再接受请求的时候被调用。
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.warn("ServletContext 过期,将无法继续服务");

    }

    // 这个方法在Web应用服务做好接受请求的时候被调用。
    public void contextInitialized(ServletContextEvent arg0) {
        this.application = arg0.getServletContext();
//this.application.setAttribute("allusers", new ArrayList<UserBean>());
        logger.info("服务已就绪");

    }

    //增加属性
    public void attributeAdded(HttpSessionBindingEvent arg0) {
//@SuppressWarnings("unchecked")
//List<UserBean> l = (List<UserBean>) this.application.getAttribute("allusers");
//if (arg0.getValue() instanceof UserBean) {
//l.add((UserBean) arg0.getValue());
//this.application.setAttribute("allusers", l);
//}
    }

    //删除属性
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
//System.out.println("attributeRemoved()调用");
    }

    //属性被替换触发
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
//System.out.println("attributeReplaced()调用");

    }

    public void sessionCreated(HttpSessionEvent arg0) {

    }

    public void sessionDestroyed(HttpSessionEvent arg0) {
//ServletContext sc =arg0.getSession().getServletContext();
//ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
//UserService userService=(UserService)ctx.getBean("userService");
////System.out.println(userDao);
//@SuppressWarnings("unchecked")
//List<UserBean> l = (List<UserBean>) this.application.getAttribute("allusers");
//
//UserBean value = (UserBean) arg0.getSession().getAttribute("user");
//if (null != value) {
//logger.info("监听器：" + value.getUsername() + "用户离线");
//l.remove(value);
////try {
////userService.exit(value,ulb);
////} catch (ParseException e) {
////e.printStackTrace();
////}
//this.application.setAttribute("allusers", l);
//}

    }

}
