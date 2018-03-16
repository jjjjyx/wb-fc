package jyx.server;

import jyx.common.Code;
import jyx.dao.*;
import jyx.model.ActivityBean;
import jyx.model.LoreBean;
import jyx.model.NewsBean;
import jyx.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户服务
 * 前台服务模块
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, IOException.class}, propagation = Propagation.REQUIRED)
public class UserServer extends ServiceBase {

    @Autowired
    private UserDao userDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private LoreDao loreDao;
    private DataDao dataDao = DataDao.getInstance();
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

    public UserBean updateUser(int uid,UserBean u) {
        UserBean userBean =  userDao.get("from UserBean where uid=?0",uid);
        if(userBean!=null) {
            userBean.update(u);
            this.userDao.update(userBean);
            return userBean;
        }else {
            return null;
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

    public List<NewsBean> getNews(int i) {
//        if(i==0)
        String hql = "from NewsBean order by releaseTime desc";
        List<NewsBean> list = newsDao.find(hql,0,i,null);

        return list;
    }
    // 运动知识
    public List<LoreBean> getLore(int i) {
        String hql = "from LoreBean order by releaseTime desc";
        List<LoreBean> list = loreDao.find(hql,0, i,null);
        return list;
    }
    // 活动通知
    public List<ActivityBean> getRecentActivity(int i) {
        String hql = "from ActivityBean order by releaseTime desc";
        List<ActivityBean> list = activityDao.find(hql,0, i,null);
        return list;
    }
    // 运动图片
    public List<String> getFCImg(int i) {
        List<Map<String,String>> list = dataDao.loadImgAll();

        if(i>0) {
            list = list.stream().limit((long) i).collect(Collectors.toList());
        }
        List<String> l = list.stream().map((item)-> item.get("fn")).collect(Collectors.toList());
        return l;
    }
    // 下载材料
    public List<Map<String,String>> getFCData(int i) {
        List<Map<String,String>> list = dataDao.loadDataAll();
        if(i>0) {
            list = list.stream().limit((long) i).collect(Collectors.toList());
        }
        return list;
    }
    // 热门资料
    public List<Map<String,String>> getHotData(int i) {
        return null;
    }
    // 获取排行榜用户
    public List<Map<String,String>> getLeaderboard(int i) {
        return null;
    }

    public NewsBean getNewsById(Integer id) {
        return newsDao.get(id);
    }
}