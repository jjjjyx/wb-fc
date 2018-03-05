package jyx.model.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "fc_user")
public class UserBean {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    private int uid;
    /**
     * 基本信息
     */
    private String email;
    private String username;
    private String password;
    private String nickname;
    private String sex;
    private String age;
    private String phone;
    private String qq;
    private int status;
    // 注册时间
    private Date regTime;

    // 登录时间
    private Date lastTime;

//    // 角色
//    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
//    @JoinColumn(name="role")
//    private Role role;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "uid=" + uid +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", status=" + status +
                ", regTime=" + regTime +
                ", lastTime=" + lastTime +
                '}';
    }
}
