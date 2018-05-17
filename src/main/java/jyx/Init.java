package jyx;



import jyx.server.AdminServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class Init implements InitializingBean {
//    private static final String SOURCE_NAME = "/sign.properties";

    //
//
    @Autowired
    private AdminServer adminServer;
//    private ConfigDao configDao;
    @Override
    public void afterPropertiesSet() throws Exception {

        adminServer.init();
    }
}
