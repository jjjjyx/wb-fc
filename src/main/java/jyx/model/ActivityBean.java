package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 活动
 */
@Entity
@Table(name = "fc_activity")
public class ActivityBean implements Bean<ActivityBean> {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;
    @Expose private String title;
    @Column(name = "content", nullable = true, length = 65535)
    @Expose private String content;
    @Expose private String type;
    @Expose private String address;
    @Expose private Date releaseTime;
    @Expose private String author;
    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinColumn(name="uid")
    private UserBean uid;

    @ManyToMany(mappedBy="activitys")
    private Set<UserBean> users; // 双向配置

    private String comment_id;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public Set<UserBean> getUsers() {
        return users;
    }

    public void setUsers(Set<UserBean> users) {
        this.users = users;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public UserBean getUid() {
        return uid;
    }

    public void setUid(UserBean uid) {
        this.uid = uid;
    }

//    public Set<UserBean> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<UserBean> users) {
//        this.users = users;
//    }

    @Override
    public void update(ActivityBean a) {
        if (a==null) return ;
        this.title = a.getTitle();
        this.address = a.getAddress();
        this.content = a.getContent();

        // 作者不允许修改
//        this.author = a.getAuthor();
        this.type = a.getType();
    }
}
