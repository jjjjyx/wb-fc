package jyx.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "fc_group")
/**
 * 圈子 标签
 */
public class GroupBean implements Bean<GroupBean>{
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    private int id;
    private String title;
    private String content;
    private String type;
    private Date creteTime;

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

    public Date getCreteTime() {
        return creteTime;
    }

    public void setCreteTime(Date creteTime) {
        this.creteTime = creteTime;
    }

    @Override
    public void update(GroupBean a) {
        if(a ==null) return ;
        this.title = a.getTitle();
        this.content = a.getContent();
        this.type = a.getType();
    }
}
