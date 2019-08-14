package org.cool.zoo.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_log")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "process")
    private String process;

    @Column(name = "processFunction")
    private String processFunction;

    @Column(name = "userRoleId")
    private Long userRoleId;

    @Column(name = "userRoleText")
    private String userRoleText;

    @Column(name = "userIp")
    private String userIp;

    @Column(name = "userAgent")
    private String userAgent;

    @Column(name = "agentString")
    private String agentString;

    @Column(name = "platform")
    private String platform;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDtm")
    private Date createDtm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProcessFunction() {
        return processFunction;
    }

    public void setProcessFunction(String processFunction) {
        this.processFunction = processFunction;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleText() {
        return userRoleText;
    }

    public void setUserRoleText(String userRoleText) {
        this.userRoleText = userRoleText;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAgentString() {
        return agentString;
    }

    public void setAgentString(String agentString) {
        this.agentString = agentString;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getCreateDtm() {
        return createDtm;
    }

    public void setCreateDtm(Date createDtm) {
        this.createDtm = createDtm;
    }
}
