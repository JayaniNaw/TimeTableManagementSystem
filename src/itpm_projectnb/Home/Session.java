/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Adeesha
 */
public class Session {
    
    private int ID;
    private String lecturer1;
    private String lecturer2;
    private String subjectCode;
    private String subject;
    private String groupID;
    private String tag;
    private String duration;
    private String sessionID;
    private CheckBox select;


    public Session(int ID, String lecturer1, String lecturer2, String subjectCode, String subject, String groupID, String tag, String duration, String sessionID) {
        this.ID = ID;
        this.lecturer1 = lecturer1;
        this.lecturer2 = lecturer2;
        this.subjectCode = subjectCode;
        this.subject = subject;
        this.groupID = groupID;
        this.tag = tag;
        this.duration = duration;
        this.sessionID = sessionID;
        this.select = new CheckBox();
    }

    public int getID() {
        return ID;
    }

    public String getLecturer1() {
        return lecturer1;
    }

    public String getLecturer2() {
        return lecturer2;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubject() {
        return subject;
    }

    public String getGroupID() {
        return groupID;
    }

    public String getTag() {
        return tag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSessionID() {
        return sessionID;
    }

    public CheckBox getSelect() {
        return select;
    }

 
    
    
    
    
}
