package jyx.server;

import jyx.common.Code;
import jyx.dao.*;
import jyx.model.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 后台管理服务
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, IOException.class}, propagation = Propagation.REQUIRED)
public class AdminServer {
//    protected Logger logger = LoggerFactory.getLogger("jyx.task.file");
    @Autowired
    private UserServer userServer;
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
    private IntegralDao integralDao;
    @Autowired
    private IntegralLogDao integralLogDao;
    @Autowired
    private DataDao dataDao;

    private final String DEFAULT_PASS = "123456";


    // 不提供分页
    public List<GroupBean> getAllGroup(){
        return this.groupDao.loadAll();
    }
    public Code add(GroupBean groupBean) {
        groupBean.setCreteTime(new Date());
        this.groupDao.save(groupBean);
        return Code.SUCCESS;
    }
    public Code delGroup(int[] uids){
        for (int uid : uids) {
            GroupBean groupBean = this.groupDao.get(uid);
            if(groupBean==null)
               continue;
            this.groupDao.delete(groupBean);
        }
        return Code.SUCCESS;
    }

    public Code update(GroupBean g){
        GroupBean groupBean = this.groupDao.get(g.getId());
        if(groupBean==null)
            return Code.PARAMETER_FAIL;
        groupBean.update(g);
        this.groupDao.update(groupBean);
        return Code.SUCCESS;
    }


    public List<NewsBean> getAllNews(){
        return this.newsDao.loadAll();
    }
    public Code add(NewsBean newsBean) {
        newsBean.setReleaseTime(new Date());
        newsBean.setComment_id(UUID.randomUUID().toString());
        this.newsDao.save(newsBean);
        return Code.SUCCESS;
    }
    public Code delNews(int[] ids){
        for (int id : ids) {
            NewsBean newsBean = this.newsDao.get(id);
            if(newsBean==null)
               continue;
            this.newsDao.delete(newsBean);
        }

        return Code.SUCCESS;
    }

    public Code update(NewsBean g){
        NewsBean newsBean = this.newsDao.get(g.getId());
        if(newsBean==null)
            return Code.PARAMETER_FAIL;
        newsBean.update(g);
        this.newsDao.update(newsBean);
        return Code.SUCCESS;
    }


    public List<LoreBean> getAllLore(){
        return this.loreDao.loadAll();
    }
    public Code add(LoreBean loreBean) {
        loreBean.setReleaseTime(new Date());
        this.loreDao.save(loreBean);
        return Code.SUCCESS;
    }
    public Code delLore(int[] ids){
        for (int id : ids) {
            LoreBean loreBean = this.loreDao.get(id);
            if(loreBean==null)
                continue;
            this.loreDao.delete(loreBean);
        }

        return Code.SUCCESS;
    }

    public Code update(LoreBean g){
        LoreBean loreBean = this.loreDao.get(g.getId());
        if(loreBean==null)
            return Code.PARAMETER_FAIL;
        loreBean.update(g);
        this.loreDao.update(loreBean);
        return Code.SUCCESS;
    }

    public List<ActivityBean> getAllActivity(){
        return this.activityDao.loadAll();
    }
    public Code add(ActivityBean activityBean) {
        activityBean.setReleaseTime(new Date());
        activityBean.setComment_id(UUID.randomUUID().toString());
        activityBean.setStatus(ActivityStatus.ready);
        this.activityDao.save(activityBean);
        return Code.SUCCESS;
    }
    public Code delActivity(int[] ids){
        for (int id : ids) {
            ActivityBean activityBean = this.activityDao.get(id);
            if(activityBean==null)
                continue;
            this.activityDao.delete(activityBean);
        }

        return Code.SUCCESS;
    }

    public Code update(ActivityBean g){
        ActivityBean activityBean = this.activityDao.get(g.getId());
        if(activityBean==null)
            return Code.PARAMETER_FAIL;
        activityBean.update(g);
        this.activityDao.update(activityBean);
        return Code.SUCCESS;
    }

    public List<UserBean> getAllUser(){
        return this.userDao.loadAll();
    }
    public Code add(UserBean userBean) {
//        userBean.setReleaseTime(new Date());
        userBean.setRegTime(new Date());
        this.userDao.save(userBean);
        return Code.SUCCESS;
    }
    public Code delUser(int id){
        UserBean userBean = this.userDao.get(id);
        if(userBean==null)
            return Code.PARAMETER_FAIL;
        this.userDao.delete(userBean);
        return Code.SUCCESS;
    }

    public Code update(UserBean g){
        UserBean userBean = this.userDao.get(g.getUid());
        if(userBean==null)
            return Code.PARAMETER_FAIL;
        userBean.update(g);
        this.userDao.update(userBean);
        return Code.SUCCESS;
    }

    /**
     * 重置密码
     * @param uids
     * @return
     */
    public Code resetPass(int[] uids){
        for (int uid : uids) {
            UserBean userBean = userDao.get(uid);
            if(userBean==null)
                continue;
            userBean.setPassword(this.DEFAULT_PASS);
            userDao.update(userBean);
        }
        return Code.SUCCESS;
    }

    public Map<String,Object> getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_data",this.getAllUser());
        map.put("activity_data",this.getAllActivity());
        map.put("group_data",this.getAllGroup());
        map.put("news_data",this.getAllNews());
        map.put("lore_data",this.getAllLore());
//        ServletContext rel= ServletActionContext.getServletContext();
//        File uploadFile = new File(rel.getRealPath( "upload"));
        // 废弃这个
//        map.put("data_data",dataDao.loadDataAll());
        map.put("data_data",dataDao.loadAll());
        map.put("img_data",dataDao.loadImgAll());
        map.put("leaderboard_data",this.getLastLeaderboard());
        return map;
    }

    public List<PostBean> getLastLeaderboard(){
        // 查看本周的投票排行榜
        Calendar calendar=Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date start = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date end = calendar.getTime();
//        System.out.println(start);
//        System.out.println(end);
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("end",end);
        String hql = "from PostBean where releaseTime > :start and releaseTime <= :end and thumbs_up >0 order by thumbs_up desc";
        List<PostBean> list = postDao.find(hql,0,10,map);
        return list;
    }


    public Code issue() {
        Calendar calendar=Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date start = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date end = calendar.getTime();

        String id = calendar.get(Calendar.YEAR)+""+calendar.get(Calendar.MONDAY)+""+calendar.get(Calendar.WEEK_OF_YEAR);
        IntegralBean integralBean = integralDao.get(id);
        if(integralBean !=null) {
            return Code.PARAME_ERROR;
        }
        integralBean = new IntegralBean();

//        System.out.println(start);
//        System.out.println(end);
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("end",end);
        String hql = "from PostBean where releaseTime > :start and releaseTime <= :end and thumbs_up >0 order by thumbs_up desc";
        List<PostBean> list = postDao.find(hql,0,10,map);
        Integer ids[] = new Integer[list.size()];
        Integer i = 100;
        int index =0;
        for (PostBean postBean : list) {
            UserBean u = postBean.getUid();
            ids[index++] = postBean.getId();
            userServer.issueLog(u,i);
            i-=10;
            userDao.update(u);
        }
        // 生成报告
        // id= 年+月+当年星期数
        integralBean.setId(id);
        integralBean.setCreateTime(new Date());
        integralBean.setPost_ids(ids);
        integralDao.save(integralBean);
        return Code.SUCCESS;
    }

    public Code addActivityMedia(int id, String media) {
        ActivityBean activityBean = this.activityDao.get(id);
        if (activityBean==null) {
            return Code.PARAME_ERROR;
        } else {
            String [] ms = activityBean.getMedia();
            if (ms ==null) {
                ms = new String[]{media};
            }else {
                ms = (String[]) ArrayUtils.add(ms,media);
            }
            activityBean.setMedia(ms);
            this.activityDao.update(activityBean);
            return Code.SUCCESS;
        }
    }

    public Code delFCData(int[] uids) {
        ServletContext rel= ServletActionContext.getServletContext();
        File uploadFile = new File(rel.getRealPath("/"));
        for (int fn : uids) {
            DataBean dataBean = this.dataDao.get(fn);
            this.dataDao.delete(dataBean);
            File file = new File(uploadFile, dataBean.getSrc());
            if(file.exists()) {
                file.delete();
            }
        }
        return Code.SUCCESS;
    }
}
