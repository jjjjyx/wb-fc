package jyx.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fc_inbox")
public class InboxBean implements Bean<InboxBean>{
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose
    private int id;
    // 私信内容
    @Column(name = "content", nullable = true, length = 65535)
    private String content;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "sender")
    private UserBean sender;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "receive")
    private UserBean receive;
    private Date carateTime;
    private boolean isRead;


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

    public UserBean getSender() {
        return sender;
    }

    public void setSender(UserBean sender) {
        this.sender = sender;
    }

    public UserBean getReceive() {
        return receive;
    }

    public void setReceive(UserBean receive) {
        this.receive = receive;
    }

    public Date getCarateTime() {
        return carateTime;
    }

    public void setCarateTime(Date carateTime) {
        this.carateTime = carateTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public void update(InboxBean a) {

    }
}
