package jyx.model;

import jyx.dao.ActivityDao;
import jyx.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ActivityDaoTest {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void test() throws Exception {
        ActivityBean activityBean = activityDao.get(1);
        activityDao.delete(activityBean);

    }

    @Test
    public void test1() throws Exception {
        UserBean userBean = userDao.get(2);
        UserBean userBean2 = userDao.get(3);

        Set<UserBean> su = userBean.getFollows();
        if(su==null) {
            su = new HashSet<>();
            userBean.setFollows(su);
        }
        su.add(userBean2);
        userDao.update(userBean);


    }


}