package jyx.action;


import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Map;

public class BaseAction extends ActionSupport implements ServletRequestAware,
        ServletResponseAware, ServletContextAware {
    protected static final String JSON = "json";
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected ServletContext servletContext;
    protected Map<String, Object> data = new HashMap<String, Object>();

    public BaseAction() {
        super();

    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        if (this.request != null) {
            this.session = this.request.getSession();
        }
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
