package com.target.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import javax.persistence.GenerationType;

@Entity
public class TargetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "User Initials")
    @Column(name = "user_initials")
    private String userID;

    @Column(name = "target_date")
    private Date targetDate;

    @NotBlank(message = "Service desk case")
    @Column(name = "case_number")
    private String caseNumber;

    @NotBlank(message = "What you going to do")
    @Column(name = "description")
    private String description;

    public TargetModel() {}

    public TargetModel(String userID, String caseNumber) {
        this.userID = userID;
        this.caseNumber = caseNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
