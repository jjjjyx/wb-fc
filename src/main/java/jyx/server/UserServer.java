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
    @Autowired
    private InboxDao inboxDao;
    @Autowired
    private CommentDao commentDao;
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
    public List<Map<String, String>> getFCImg(int i) {
        List<Map<String, String>> list = dataDao.loadImgAll();

        if (i > 0) {
            list = list.stream().limit((long) i).collect(Collectors.toList());
        }
//        List<String> l = list.stream().map((item) -> item.get("fn")).collect(Collectors.toList());
        return list;
    }

    // 下载材料
    public List<Map<String, String>> getFCData(int i) {
        List<Map<String, String>> list = dataDao.loadDataAll();
        if (i > 0) {
            list = list.stream().limit((long) i).collect(Collectors.toList());
            Collections.shuffle(list);
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

//        List

        return null;
    }

    // 获取排行榜用户
    public List<Map<String, Object>> getLeaderboard(int i, UserBean user) {
        // 前10
        String hql = "from UserBean order by integral desc";
        List<UserBean> list = this.userDao.find(hql, 0, 10, null);
        // 查找出来的用户列表进行
        List lm = list.stream().map((UserBean item) -> {
//            Utils::transBean2Map
            Map ii = Utils.transBean2Map(item);

            UserBean userBean = this.userDao.get(user.getUid());
            ii.put("is_f", userBean.getFollows().contains(item));
            return ii;
        }).collect(Collectors.toList());
        return lm;
    }

    public NewsBean getNewsById(Integer id) {
        return newsDao.get(id);
    }

    public LoreBean getLoreById(Integer id) {
        return loreDao.get(id);
    }

    public Code releasePost(PostBean post, UserBean u) {
        post.setComment_id(UUID.randomUUID().toString());
        post.setReleaseTime(new Date());
        post.setThumbs_up(0);
//        UserBean userBean = userDao.get(u.getUid());
        post.setUid(u);
        // 增加积分
        Integer i = u.getIntegral();
        if (i == null) i = 0;
        i++;
        u.setIntegral(i);

        postDao.save(post);
        userDao.update(u);
        return Code.SUCCESS;
    }

    public List<Map> getPostData(UserBean u, String group, int i, Integer uid, PostType postType) {
        StringBuilder hql = new StringBuilder("from PostBean where 1=1 ");
        Map<String, Object> map = new HashMap<>();
        if (group != null && "" != group && !"all".equals(group)) {
            hql.append("and group_type = :gt");
            map.put("gt", group);
        }
        // 查询某个用户 要去掉私密
        if (uid != null) {
            hql.append(" and uid.id=:uid and group_type !='private'");
            map.put("uid", uid);
        }

        if (postType != null) { // == null 获取全部
            hql.append(" and type=:type");
            map.put("type", postType);
        }

        hql.append(" order by releaseTime desc");
//        System.out.println(hql);
//        String hql = "from PostBean order by releaseTime desc";
        List<PostBean> list = postDao.find(hql.toString(), 0, i, map);
        UserBean userBean = userDao.get(u.getUid());
        Set<PostBean> ps = userBean.getStars();
        Set<PostBean> ts = userBean.getThumbs_up();
        Set<UserBean> follows = userBean.getFollows();
        List<Map> list_m = list.stream().filter((item) -> {
            // 如果是私密的必须是当前用户的
            if ("private".equalsIgnoreCase(item.getGroup_type())) {
                if (item.getUid().equals(userBean)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }).map((item) -> {
            Map ii = Utils.transBean2Map(item);
            String[] m = (String[]) ii.get("media");
            if (m != null) {
                ii.remove("media");
                List<String> img = new ArrayList<>();
                List<String> mp4 = new ArrayList<>();
                for (String s : m) {
                    String ext = s.substring(s.lastIndexOf(".") + 1);
                    if (ext.equalsIgnoreCase("mp4")) {
                        mp4.add(s);
                    } else {
                        img.add(s);
                    }
                }
                if (mp4.size() > 0) {
                    ii.put("mp4s", mp4);
                    ii.put("media.length", mp4.size());
                } else {
                    ii.put("imgs", img);
                    ii.put("media.length", img.size());
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
            // 查询动态的评论个数
            Map<String, Object> p = new HashMap<>();
            p.put("pid", item.getComment_id());
            int comment_num = this.commentDao.count("from CommentBean where pid=:pid ", p);
            ii.put("comment_num", comment_num);
            // 检查当前用户是否与说说是好友 没有评论权限
            if (userBean.equals(item.getUid())) {
                ii.put("is_comment", true); // 自己
            } else if (follows.contains(item.getUid())) {// 用户关注了
                Set<UserBean> _f = item.getUid().getFollows();
                if (_f.contains(userBean)) {
                    // 互相关注 为好友
                    ii.put("is_comment", true);
                }
            }


            return ii;
        }).collect(Collectors.toList());
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
        if (i == null) {
            i = 0;
        }
        i++;
        postBean.setThumbs_up(i);
        ps.add(postBean);
        userDao.update(userBean);
        postDao.update(postBean);
        return Code.SUCCESS;
    }


    /**
     * 取消赞
     */
    public Code unThumbs_up(Integer id, UserBean u) {
        PostBean postBean = postDao.get(id);
        if (postBean == null) {
            return Code.PARAMETER_FAIL;
        }
        UserBean userBean = userDao.get(u.getUid());
        Set<PostBean> ps = userBean.getThumbs_up();
        if (ps == null) {
            return Code.PARAMETER_FAIL;
        }
        Integer i = postBean.getThumbs_up();
        if (i == null) {
            return Code.PARAMETER_FAIL;
        }
        i--;
        postBean.setThumbs_up(i);
        ps.remove(postBean);
        postDao.update(postBean);
        userDao.update(userBean);
        return Code.SUCCESS;
    }

    public Code comment(String c_id, UserBean u, CommentBean commentBean) {
        commentBean.setPid(c_id);
        UserBean userBean = userDao.get(u.getUid());
        commentBean.setUid(userBean);
        commentBean.setReleaseTime(new Date());

//        Integer i = u.getIntegral();
//        if(i==null) i = 0;
//        i++;
//        u.setIntegral(i);
        commentDao.save(commentBean);
//        userDao.update(u);
        return Code.SUCCESS;
    }

    public Code delComment(Integer c_id, UserBean u) {
        CommentBean commentBean = commentDao.get(c_id);
        // 查看是不是当前用户的
        if (commentBean.getUid().equals(u)) {
            commentDao.delete(commentBean);
            return Code.SUCCESS;
        } else {
            return Code.PARAME_ERROR;
        }
    }

    /**
     * @param c_id
     * @param u
     * @return
     */
    public List<CommentBean> getComments(String c_id, UserBean u) {
//        List<Map<String,Object>> list = ;
        List<CommentBean> l = this.commentDao.find("from CommentBean where pid=?0 order by releaseTime desc", new Object[]{c_id});
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        String s = gson.toJson(l);
//        Object o = gson.fromJson(s,List.class);

        return l;
    }

    public List getCurrActivity(int i, UserBean u, boolean b) {
        List<ActivityBean> list = null;
        if (b) {
            list = activityDao.find("from ActivityBean where uid=?0 and status != ?1 order by releaseTime desc", new Object[]{u, ActivityStatus.del});
        } else {
            list = this.activityDao.find("from ActivityBean where status != ?0 order by releaseTime desc", new Object[]{ActivityStatus.del});
        }
        return list;
    }
    public Set getUserJoinActivity(UserBean u){
        UserBean userBean = userDao.get(u.getUid());
        return userBean.getActivitys();
    }

    public List getFinishActivity(){
        List<ActivityBean> list = this.activityDao.find("from ActivityBean where status = ?0 order by releaseTime desc", new Object[]{ActivityStatus.end});
        return list;
    }

    public void endActivity(UserBean u, Integer id) {
        ActivityBean activityBean = activityDao.get(id);
        if (u.equals(activityBean.getUid())) {
            activityBean.setStatus(ActivityStatus.end);
            activityDao.update(activityBean);
        }
    }

//    public List<Map<String, Object>> getMyAB(UserBean userBean) {
//        UserBean u = userDao.get(userBean.getUid());
//        return u.getActivitys().stream().map((item) -> {
//            Map<String, Object> m = Utils.transBean2Map(item);
//            Object o = m.get("users");
//            if (o != null) {
//                o = o.toString();
//            }
//            return m;
//        }).collect(Collectors.toList());
//    }
    public ActivityBean queryActivityBean(Integer id) {
        return this.activityDao.get(id);
    }

    public Code releaseActivity(ActivityBean activity, UserBean u) {
        activity.setUid(u);
        activity.setReleaseTime(new Date());
        activity.setComment_id(UUID.randomUUID().toString());
        activity.setStatus(ActivityStatus.ready);

        this.activityDao.save(activity);
        return Code.SUCCESS;
    }

    public Code activitySign(Integer id, UserBean u) {
        ActivityBean activityBean = this.activityDao.get(id);
        UserBean userBean = userDao.get(u.getUid());
        Set<ActivityBean> ps = userBean.getActivitys();
        if (ps == null) {
            ps = new HashSet<>();
            userBean.setActivitys(ps);
        }
        ps.add(activityBean);
        userDao.update(userBean);
        return Code.SUCCESS;
    }

    public Code activityUnSign(Integer id, UserBean u) {
        ActivityBean activityBean = this.activityDao.get(id);
        UserBean userBean = userDao.get(u.getUid());
        Set<ActivityBean> ps = userBean.getActivitys();
        if (ps == null) {
            ps = new HashSet<>();
            userBean.setActivitys(ps);
        } else
            ps.remove(activityBean);
        userDao.update(userBean);
        return Code.SUCCESS;
    }

    public Code delActivity(Integer id) {
        ActivityBean activityBean = this.activityDao.get(id);
        activityBean.setUsers(null);
        this.activityDao.update(activityBean);
        this.activityDao.delete(activityBean);
        return Code.SUCCESS;
    }

    public Code downFile(UserBean userBean) {
        int ii = userBean.getIntegral();
        if (ii >= 5) {
            ii -= 5;
            userBean.setIntegral(ii);
            this.userDao.update(userBean);
            return Code.SUCCESS;
        }
        return Code.ACCESS_FAIL;
    }

    // 获取用户的详情
    public Map<String, Object> searchUser(Integer uid, UserBean u) {
        UserBean u1 = userDao.get(uid);
        Map<String, Object> map = Utils.transBean2Map(u1);
        UserBean userBean = this.userDao.get(u.getUid());
        map.put("is_f", userBean.getFollows().contains(u1));
        return map;
    }

    // 根据用户名查找用户
    public List searchUsers(UserBean u, String query) {
        UserBean userBean = userDao.get(u.getUid());
        List<UserBean> list = userDao.find("from UserBean where nickname like ?0", new Object[]{"%" + query + "%"});
        Set<UserBean> follows = userBean.getFollows();

        // 清除一下不需要的信息
//        for (UserBean userBean : list) {
//            userBean.setPassword("");
//        }
        return list.stream().map((item) -> {
//            item.setPassword("");
            // 判断是不是好友
            Map map = Utils.transBean2Map(item);
            // 是否关注
            map.put("is_f", follows.contains(item));
            map.remove("password");
            map.remove("follows");
            map.remove("activitys");
            map.remove("stars");
            map.remove("thumbs_up");
            return map;
        }).collect(Collectors.toList());
    }

    public Code follow(UserBean u, Integer uid) {
        u = this.userDao.get(u.getUid());
        Set<UserBean> su = u.getFollows();
        UserBean userBean = this.userDao.get(uid);
        if (su == null) {
            su = new HashSet<>();
            u.setFollows(su);
        }
        su.add(userBean);
        this.userDao.update(u);
        return Code.SUCCESS;
    }

    public Code unfollow(UserBean u, Integer uid) {
        u = this.userDao.get(u.getUid());
        Set<UserBean> su = u.getFollows();
        if (su != null) {
            UserBean userBean = this.userDao.get(uid);
            su.remove(userBean);
            this.userDao.update(u);
        }
        return Code.SUCCESS;
    }

    /**
     * 获取用户好友
     *
     * @return
     */
    public List<Map<String, Object>> getUserFollows() {

        return null;
    }


    public Code activityComment(UserBean u, CommentBean commentBean) {
        UserBean userBean = userDao.get(u.getUid());
        commentBean.setUid(userBean);
        commentBean.setReleaseTime(new Date());
        commentDao.save(commentBean);
        return Code.SUCCESS;
    }

    public UserBean queryUserBean(int uid) {
        return userDao.get(uid);
    }

    public List follows(UserBean u) {
        UserBean userBean = userDao.get(u.getUid());
        return userBean.getFollows().stream().map((item)->{
            Map map = Utils.transBean2Map(item);
            map.remove("password");
            map.remove("follows");
            map.remove("activitys");
            map.remove("stars");
            map.remove("thumbs_up");
            return map;
        }).collect(Collectors.toList());
    }


    public Code sendInbox(UserBean u, InboxBean inbox, Integer uid) {
        inbox.setCarateTime(new Date());
        inbox.setRead(false);
        inbox.setSender(userDao.get(u.getUid()));
        inbox.setReceive(userDao.get(uid));
        inboxDao.save(inbox);
        return Code.SUCCESS;
    }

    public List<InboxBean> getUserInbox(UserBean u){
        String sql = "from InboxBean where sender = ?0 or receive = ?1 order by carateTime desc";
        List<InboxBean> list = inboxDao.find(sql,new Object[]{u,u});
        for (InboxBean inboxBean : list) {
            if (u.equals(inboxBean.getReceive()) && !inboxBean.isRead()) {
                inboxBean.setRead(true);
                inboxDao.update(inboxBean);
            }
        }
        return list;
    }

    public Integer getNoReadInBox(UserBean user) {
        Map<String,Object> map = new HashMap<>();
        map.put("receive",user);
        Integer i = inboxDao.count("from InboxBean where receive =:receive and isRead = 0",map);
        return i;
    }
}