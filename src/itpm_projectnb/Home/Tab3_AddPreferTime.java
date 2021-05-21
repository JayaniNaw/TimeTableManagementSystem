/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.sql.Time;

/**
 *
 * @author user
 */
public class Tab3_AddPreferTime {
    
    private int id;
    private String session;
    private String day;
    private String date;
    private Time starttime;
    private Time endtime;

    public Tab3_AddPreferTime(int id,String session, String day, String date, Time starttime, Time endtime) {
        this.id = id;
        this.session = session;
        this.day = day;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;
    }
    
    public String getSession() {
        return session;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public Time getStarttime() {
        return starttime;
    }

    public Time getEndtime() {
        return endtime;
    }

    
    
}
