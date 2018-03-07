package jyx.server;

import jyx.common.Code;
import jyx.dao.UserDao;
import jyx.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * 用户服务
 * 前台服务模块
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, IOException.class}, propagation = Propagation.REQUIRED)
public class UserServer extends ServiceBase {

    @Autowired
    private UserDao userDao;

    public UserBean getUser(String username, String password){
        UserBean u = userDao.get("from UserBean where username=?0",username);
        if(u!=null) {
            if(u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * 用户是否被注册
     * @param username
     * @return true 没有
     *          false 已被注册
     */
    public boolean checkUserName(String username) {
        UserBean userBean =  userDao.get("from UserBean where username=?0",username);
        return userBean == null;
    }

    public UserBean signUp(String username, String password) {
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.setRegTime(new Date());
        userBean.setRole(0);
        userDao.save(userBean);
        return userBean;
    }

    public Code updateUser(int uid,UserBean u) {
        UserBean userBean =  userDao.get("from UserBean where uid=?0",uid);
        if(userBean!=null) {
            userBean.update(u);
            this.userDao.update(userBean);
            return Code.SUCCESS;
        }else {
            return Code.PARAMETER_FAIL;
        }
    }
    public Code changePass(int uid, String pass) {
        UserBean userBean =  userDao.get("from UserBean where uid=?0",uid);
        if(userBean!=null) {
            userBean.setPassword(pass);
            this.userDao.update(userBean);
            return Code.SUCCESS;
        }else {
            return Code.PARAMETER_FAIL;
        }
    }
}