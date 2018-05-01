package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 活动
 */
@Entity
@Table(name = "fc_activity")
@TypeDef(name = "json", typeClass = JsonType.class)
public class ActivityBean implements Bean<ActivityBean> {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;
    @Expose
    private String title;
    @Column(name = "content", nullable = true, length = 65535)
    // 活动内容
    @Expose
    private String content;
    // 类型
    @Expose
    private String type;
    // 地址
    @Expose
    private String address;
    @Expose
    private Date releaseTime;
    @Expose
    private Date startTime;
    @Expose
    private Date endTime;
    @Expose
    private String author;
    @Expose
    private String phone;

    @Expose
    private ActivityStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private UserBean uid;

    @Type(type = "json", parameters = {@org.hibernate.annotations.Parameter(name = "type", value = "[Ljava.lang.String;")})
    @Column(name = "media", nullable = true, length = 65535)
    @Expose
    // 活动过程
    private String[] media;



    @Type(type = "json", parameters = {@org.hibernate.annotations.Parameter(name = "type", value = "java.util.HashMap")})
    @Expose
    private Map<String, Object> sint;
    //    cascade={CascadeType.MERGE,CascadeType.PERSIST}
    @ManyToMany(mappedBy = "activitys", fetch = FetchType.EAGER, targetEntity = jyx.model.UserBean.class)
    @Cascade(value = {CascadeType.DELETE})
    private Set<UserBean> users; // 双向配置
    @Expose
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

    public Map<String, Object> getSint() {
        return sint;
    }

    public void setSint(Map<String, Object> sint) {
        this.sint = sint;
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
        if (a == null) return;
        this.title = a.getTitle();
        this.address = a.getAddress();
        this.content = a.getContent();
        this.startTime = a.getStartTime();
        this.endTime = a.getEndTime();
        this.phone = a.getPhone();
        if (a.getSint()!=null) {
            this.sint.putAll(a.getSint());
        }
//        this.author = a.getAuthor();
        this.type = a.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityBean that = (ActivityBean) o;

        return id == that.id;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getMedia() {
        return media;
    }

    public void setMedia(String[] media) {
        this.media = media;
    }
}
