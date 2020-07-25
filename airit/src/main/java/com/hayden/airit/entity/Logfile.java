package com.hayden.airit.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "tb_log")
public class Logfile {
    private static final long serialVersionUID = 3976790993684535676L;

    private Integer id;
    private String logInfo;
    private Date logTime;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) { this.id = id; }
    public String getLogInfo() {
        return logInfo;
    }
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }
    public Date getLogTime() {
        return logTime;
    }
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @ManyToOne
    @JoinColumn(name="logUser")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
