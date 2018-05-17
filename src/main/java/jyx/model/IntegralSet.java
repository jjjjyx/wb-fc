package jyx.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "fc_integral_set")
public class IntegralSet implements Bean<IntegralSet>{
    @Override
    public void update(IntegralSet a) {
        this.rank = a.getRank();
        this.maxReward = a.getMaxReward();
        this.minReward = a.getMinReward();
        this.intervalNums = a.getIntervalNums();
        this.cycle = a.getCycle();
    }
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    @GeneratedValue(generator = "idGenerator")
    @Expose private Integer id;
    @Expose private Integer rank;
    @Expose private Integer maxReward;
    @Expose private Integer minReward;
    @Expose private Integer intervalNums;
    @Expose private Integer cycle;
    @Expose private Date lastGrantTime;
    @Expose private Date nextGrantTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getMaxReward() {
        return maxReward;
    }

    public void setMaxReward(Integer maxReward) {
        this.maxReward = maxReward;
    }

    public Integer getMinReward() {
        return minReward;
    }

    public void setMinReward(Integer minReward) {
        this.minReward = minReward;
    }

    public Integer getIntervalNums() {
        return intervalNums;
    }

    public void setIntervalNums(Integer intervalNums) {
        this.intervalNums = intervalNums;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Date getLastGrantTime() {
        return lastGrantTime;
    }

    public void setLastGrantTime(Date lastGrantTime) {
        this.lastGrantTime = lastGrantTime;
    }

    public Date getNextGrantTime() {
        return nextGrantTime;
    }

    public void setNextGrantTime(Date nextGrantTime) {
        this.nextGrantTime = nextGrantTime;
    }


}
