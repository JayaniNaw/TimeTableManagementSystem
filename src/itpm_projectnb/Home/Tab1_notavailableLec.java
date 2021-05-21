/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author user
 */
public class Tab1_notavailableLec {
    
    private final int id;
    private final String lecture;
    private final String mgroup;
    private final String subgroup;
    private final String sessionId;
    private final String date;
    private final Time from;
    private final Time to;

    public Tab1_notavailableLec(int id, String lecture, String mgroup, String subgroup, String sessionId, String date, Time from, Time to) {
        this.id = id;
        this.lecture = lecture;
        this.mgroup = mgroup;
        this.subgroup = subgroup;
        this.sessionId = sessionId;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public String getLecture() {
        return lecture;
    }

    public String getMgroup() {
        return mgroup;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getDate() {
        return date;
    }

    public Time getFrom() {
        return from;
    }

    public Time getTo() {
        return to;
    }
    
    
    
}
