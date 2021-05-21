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
public class Tab2_notavailableRoom {
    
    private int id;
    private String room;
    private String dfrom;
    private String dto;
    private Time start;
    private Time end;

    public Tab2_notavailableRoom(int id, String room, String dfrom, String dto, Time start, Time end) {
        this.id = id;
        this.room = room;
        this.dfrom = dfrom;
        this.dto = dto;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    public String getDfrom() {
        return dfrom;
    }

    public String getDto() {
        return dto;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }
    
    
    
}
