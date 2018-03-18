package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 动态
 */
public class MoodBean {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;
    @Column(name = "content", nullable = true, length = 65535)
    @Expose private String content;
    @Expose private Date releaseTime;

    // 动态类型
    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinColumn(name="gid")
    private GroupBean gid;
    // 发布者
    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinColumn(name="uid")
    private UserBean uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public GroupBean getGid() {
        return gid;
    }

    public void setGid(GroupBean gid) {
        this.gid = gid;
    }

    public UserBean getUid() {
        return uid;
    }

    public void setUid(UserBean uid) {
        this.uid = uid;
    }
}
