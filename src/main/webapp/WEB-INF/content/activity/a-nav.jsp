<%@ page import="org.apache.struts2.dispatcher.mapper.ActionMapping" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String[][] a_nav = {
            {"all","all","全部活动"},
            {"join","join","我参与的"},
            {"found","found","我创建的"},
            {"finish","finish","已结束的"},
    };
    ActionMapping m = (ActionMapping) request.getAttribute("struts.actionMapping");
    String mn = m.getName();
    String modelName = (String) pageContext.getAttribute("moduleName");
    String prefix = path+"/" +modelName +"/";
    for (String[] strings : a_nav) {
        out.print("<li");
        if (strings[1].equalsIgnoreCase(mn)) {
            out.print(" class=am-active");
        }
        out.print(">");out.print("<a href=");out.print(prefix);out.print(strings[0]);out.print(">");out.print(strings[2]);
        out.print("</a></li>");
    }
%>