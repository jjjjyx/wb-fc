package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fc_data")
public class DataBean implements Bean<DataBean>, Serializable {


    private static final long serialVersionUID = -7968142296722904504L;

    @Override
    public void update(DataBean a) {

    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;
    @Expose
    private String name; // 文件名
    @Expose
    private String originName; // 上传文件名称
    @Expose
    private String type; // 文件类型
    @Expose
    private String ext; // 文件后缀
    @Expose
    private String size; // 文件大小
    @Expose
    private int integral; // 文件积分
    @Expose
    private Date createDate;
    @Expose
    private int num; // 下载量
    @Expose
    private String src; // 文件路径
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="uid")
    @Expose
    private UserBean uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public UserBean getUid() {
        return uid;
    }

    public void setUid(UserBean uid) {
        this.uid = uid;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
