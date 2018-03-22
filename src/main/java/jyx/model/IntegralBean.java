package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fc_integral")
@TypeDef(name = "json", typeClass = JsonType.class)
public class IntegralBean {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private String id;
    @Expose
    private Date createTime;
    @Expose
    @Type(type = "json",parameters = {@Parameter(name ="type",value="[Ljava.lang.Integer;")})
    @Column(name="post_ids", nullable=true, length = 65535)
    private Integer[] post_ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer[] getPost_ids() {
        return post_ids;
    }

    public void setPost_ids(Integer[] post_ids) {
        this.post_ids = post_ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegralBean that = (IntegralBean) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
