package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 资讯
 *
 */
@Entity
@Table(name = "fc_news")
public class NewsBean implements Bean<NewsBean>{
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;

    @Expose private String title;
    @Column(name = "content", nullable = true, length = 65535)
    @Expose private String content;
    @Expose private Date releaseTime;
    @Expose private String author;
//    private String
    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinColumn(name="uid")
    private UserBean uid;

    @Expose private String comment_id;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
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

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
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

    @Override
    public void update(NewsBean a) {
        if(a==null) return;
        this.title = a.getTitle();
        this.content = a.getContent();
    }
}
