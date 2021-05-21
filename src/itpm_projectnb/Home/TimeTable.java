/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

/**
 *
 * @author user
 */
public class TimeTable {
    
    private String slot;
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;

    public String getDay2() {
        return day2;
    }

    public String getDay3() {
        return day3;
    }

    public String getDay4() {
        return day4;
    }

    public String getDay5() {
        return day5;
    }

    public TimeTable(String slot, String day1, String day2, String day3, String day4, String day5) {
        this.slot = slot;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
    }

    public TimeTable(String day1, String day2, String day3, String day4, String day5) {
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
    }

    
    public TimeTable(String slot,String day1) {
        this.slot = slot;
        this.day1 = day1;
    }

   
    
     public TimeTable(String slot) {
        this.slot = slot;
    }
    
    public String getSlot() {
        return slot;
    }

    public String getDay1() {
        return day1;
    }
    
    
}
