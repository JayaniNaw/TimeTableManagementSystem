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
public class WorkShedule{
    
    private final int numberOfDays;
    private final String Days;
    private final Time startT;
    private final Time endT;
    private final int slot;
    private final int breakcount;
    private final int id;


    public WorkShedule(int numberofDays, String wokringDays, Time starttime, Time endtime, int slot, int breakcount,int id) {
        this.numberOfDays = numberofDays;
        this.Days = wokringDays;
        this.startT = starttime;
        this.endT = endtime;
        this.slot = slot;
        this.breakcount = breakcount;
        this.id = id;
    }

    public int getNumberofDays() {
        return numberOfDays;
    }

    public String getWokringDays() {
        return Days;
    }

    public Time getStarttime() {
        return startT;
    }

    public Time getEndtime() {
        return endT;
    }

    public int getSlot() {
        return slot;
    }

    public int getBreakcount() {
        return breakcount;
    }

    public int getId() {
        return id;
    }
    
    
    
    
}
