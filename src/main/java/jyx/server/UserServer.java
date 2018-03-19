package jyx.server;

import jyx.common.Code;
import jyx.dao.*;
import jyx.model.*;
import jyx.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
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
    @Autowired
    private PostDao postDao;
    private DataDao dataDao = DataDao.getInstance();

    public UserBean getUser(String username, String password) {
        UserBean u = userDao.get("from UserBean where username=?0", username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * 用户是否被注册
     *
     * @param username
     * @return true 没有
     * false 已被注册
     */
    public boolean checkUserName(String username) {
        UserBean userBean = userDao.get("from UserBean where username=?0", username);
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

    public UserBean updateUser(int uid, UserBean u) {
        UserBean userBean = userDao.get("from UserBean where uid=?0", uid);
        if (userBean != null) {
            userBean.update(u);
            this.userDao.update(userBean);
            return userBean;
        } else {
            return null;
        }
    }

    public Code changePass(int uid, String pass) {
        UserBean userBean = userDao.get("from UserBean where uid=?0", uid);
        if (userBean != null) {
            userBean.setPassword(pass);
            this.userDao.update(userBean);
            return Code.SUCCESS;
        } else {
            return Code.PARAMETER_FAIL;
        }
    }

    public List<NewsBean> getNews(int i) {
//        if(i==0)
        String hql = "from NewsBean order by releaseTime desc";
        List<NewsBean> list = newsDao.find(hql, 0, i, null);

        return list;
    }

    // 运动知识
    public List<LoreBean> getLore(int i) {
        String hql = "from LoreBean order by releaseTime desc";
        List<LoreBean> list = loreDao.find(hql, 0, i, null);
        return list;
    }

    // 活动通知
    public List<ActivityBean> getRecentActivity(int i) {
        String hql = "from ActivityBean order by releaseTime desc";
        List<ActivityBean> list = activityDao.find(hql, 0, i, null);
        return list;
    }

    // 运动图片
    public List<String> getFCImg(int i) {
        List<Map<String, String>> list = dataDao.loadImgAll();

        if (i > 0) {
            list = list.stream().limit((long) i).collect(Collectors.toList());
        }
        List<String> l = list.stream().map((item) -> item.get("fn")).collect(Collectors.toList());
        return l;
    }

    // 下载材料
    public List<Map<String, String>> getFCData(int i) {
        List<Map<String, String>> list = dataDao.loadDataAll();
        if (i > 0) {
            list = list.stream().limit((long) i).collect(Collectors.toList());
        }
        return list;
    }

    // 获取全部圈子
    public List<GroupBean> getGroup(int i) {
        List<GroupBean> list = groupDao.loadAll();
        return list;
    }

    // 热门资料
    public List<Map<String, String>> getHotData(int i) {
        return null;
    }

    // 获取排行榜用户
    public List<Map<String, String>> getLeaderboard(int i) {
        return null;
    }

    public NewsBean getNewsById(Integer id) {
        return newsDao.get(id);
    }


    public Code releasePost(PostBean post, UserBean u) {
        post.setComment_id(UUID.randomUUID().toString());
        post.setReleaseTime(new Date());
        post.setThumbs_up(0);
        post.setUid(u);

        postDao.save(post);

        return Code.SUCCESS;
    }

    public List<Map> getPostData(UserBean u, String group, int i) {
        StringBuilder hql = new StringBuilder("from PostBean ");
        Map<String, Object> map = new HashMap<>();
        if (group != null && "" != group && !"all".equals(group)) {
            hql.append("where group_type = :gt");
            map.put("gt", group);
        }
        hql.append(" order by releaseTime desc");
//        System.out.println(hql);
//        String hql = "from PostBean order by releaseTime desc";
        List<PostBean> list = postDao.find(hql.toString(), 0, i, map);
        UserBean userBean = userDao.get(u.getUid());
        Set<PostBean> ps = userBean.getStars();
        Set<PostBean> ts = userBean.getThumbs_up();
//        if(ps==null) {
//            ps = new HashSet<>();
//            userBean.setStars(ps);
//        }
        List<Map> list_m = list.stream().map((item) -> {
            Map ii = Utils.transBean2Map(item);
            String[] m = (String[]) ii.get("media");
            if(m!=null) {
                ii.remove("media");
                List<String> img = new ArrayList<>();
                List<String> mp4 = new ArrayList<>();
                for (String s : m) {
                    String ext = s.substring(s.lastIndexOf(".") + 1);
                    if(ext.equalsIgnoreCase("mp4")){
                        mp4.add(s);
                    }else {
                        img.add(s);
                    }
                }
                if(mp4.size()>0){
                    ii.put("mp4s",mp4);
                    ii.put("media.length",mp4.size());
                } else {
                    ii.put("imgs",img);
                    ii.put("media.length",img.size());
                }

            }
            // 查询到 当前用户是否有收藏
            if (ps != null) {
                for (PostBean p : ps) {
                    if (p.getId() == (Integer) ii.get("id")) {
                        ii.put("isStar", true);
                        break;
                    }
                }
            }
            if (ts != null) {
                for (PostBean p : ts) {
                    if (p.getId() == (Integer) ii.get("id")) {
                        ii.put("isThumbs_up", true);
                        break;
                    }
                }
            }
            return ii;
        }).collect(Collectors.toList());
//        for (PostBean postBean : list) {
//            System.out.println(Utils.transBean2Map(postBean));
//        }
        return list_m;
    }

    /**
     * 收藏
     */
    public Code star(Integer id, UserBean u) {
        PostBean postBean = postDao.get(id);
        if (postBean == null) {
            return Code.PARAMETER_FAIL;
        }
        UserBean userBean = userDao.get(u.getUid());
        Set<PostBean> ps = userBean.getStars();
        if (ps == null) {
            ps = new HashSet<>();
            userBean.setStars(ps);
        }
        ps.add(postBean);
        userDao.update(userBean);
        return Code.SUCCESS;
    }

    /**
     * 赞
     */
    public Code thumbs_up(Integer id, UserBean u) {
        PostBean postBean = postDao.get(id);
        if (postBean == null) {
            return Code.PARAMETER_FAIL;
        }
        UserBean userBean = userDao.get(u.getUid());
        Set<PostBean> ps = userBean.getThumbs_up();
        if (ps == null) {
            ps = new HashSet<>();
            userBean.setThumbs_up(ps);
        }
        Integer i = postBean.getThumbs_up();
        if(i==null) {
            i = 0;
        }
        i++;
        postBean.setThumbs_up(i);
        ps.add(postBean);
        userDao.update(userBean);
        postDao.update(postBean);
        return Code.SUCCESS;
    }

    public Code comment(String c_id, UserBean u, CommentBean commentBean) {

        return Code.SUCCESS;
    }
}