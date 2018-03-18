package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户发表的说说
 */
@Entity
@Table(name = "fc_post")
public class PostBean {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;
    // 所属用户
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private UserBean uid;
    // 所属圈子 不做外键了 直接存放group的type
    @Expose
    private String group_type;
    // 发布时间
    @Expose
    private Date releaseTime;
    // 内容
    @Expose
    private String content;

    @Expose
    private Integer thumbs_up;

    // 评论id
    @Expose
    private String comment_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserBean getUid() {
        return uid;
    }

    public void setUid(UserBean uid) {
        this.uid = uid;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getThumbs_up() {
        return thumbs_up;
    }

    public void setThumbs_up(Integer thumbs_up) {
        this.thumbs_up = thumbs_up;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }
}
