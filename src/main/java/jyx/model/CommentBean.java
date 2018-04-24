package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fc_comments")
@TypeDef(name = "json", typeClass = JsonType.class)
public class CommentBean {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private Integer id;
    @Expose
    private String pid;
    // 内容中自带指向引用
    @Expose
    private String content;
    @Expose
    private Date releaseTime;

    @Type(type = "json", parameters = {@org.hibernate.annotations.Parameter(name = "type", value = "[Ljava.lang.String;")})
    @Column(name = "media", nullable = true, length = 65535)
    @Expose
    private String[] media;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
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

    public String[] getMedia() {
        return media;
    }

    public void setMedia(String[] media) {
        this.media = media;
    }
}
