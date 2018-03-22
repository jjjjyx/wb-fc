package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fc_comments")
public class CommentBean {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose private Integer id;
    @Expose
    // uuid
    private String pid;
    // 内容中自带指向引用
    @Expose private String content;
    @Expose private Date releaseTime;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="uid")
    @Expose
    private UserBean uid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public UserBean getUid() {
        return uid;
    }

    public void setUid(UserBean uid) {
        this.uid = uid;
    }
}
