package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//**积分日志
@Entity
@Table(name = "fc_integral_log")
public class IntegralLogBean implements Bean<IntegralLogBean>,Serializable {

    private static final long serialVersionUID = -8728819726871300241L;

    @Override
    public void update(IntegralLogBean a) {

    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="uid")
    private UserBean uid;
    private int integral; // 积分变动大小
    private Date updateDate; // 变动时间
    private String remark;


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

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
