/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.util.logging.Logger;

/**
 *
 * @author IMAKA
 */
public class sessionroom {

    private static final Logger LOG = Logger.getLogger(sessionroom.class.getName());

    private int id;
    private String sessionID;
    private String Duration;
    private String Lecturer1;
    private String Lecturer2;
    private String Subject;
    private String SubjectCode;
    private String Tag;
    private int NoOfStudents;
    
    private String sessionRoom;
    
    private int ID;

    private String conSessionID;
    private String conSessionRoom;
    private String SessionID;
    private String RoomName;
    
    public sessionroom(int id, String sessionID, String Lecturer1, String Lecturer2, String Subject,String SubjectCode,String Tag,int NoOfStudents,String Duration,String sessionRoom) {
        this.id = id;
        this.sessionID = sessionID;
        this.Lecturer1 = Lecturer1;
        this.Lecturer2 = Lecturer2;
        this.Subject = Subject;
        this.SubjectCode = SubjectCode;
        this.Tag = Tag;
        this.NoOfStudents = NoOfStudents;
        this.sessionRoom = sessionRoom;
        this.Duration=Duration;
    }

    sessionroom(int ID ,String conSessionID, String conSessionRoom) {
        this.ID = ID;
        this.conSessionID = conSessionID;
        this.conSessionRoom = conSessionRoom;
    }
/*    
    sessionroom(int ID ,String sessionID, String sessionRoom) {
        this.ID = ID;
        this.sessionID = sessionID;
        this.sessionRoom = sessionRoom;
    }
*/
    sessionroom(int ID, String conSessionID, String conSessionRoom, String SessionID, String RoomName) {
        this.ID = ID;
        this.conSessionID = conSessionID;
        this.conSessionRoom = conSessionRoom;
        
        this.SessionID = SessionID;
        this.RoomName = RoomName;
        
     }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

   
    public String getConSessionID() {
        return conSessionID;
    }

    public void setConSessionID(String conSessionID) {
        this.conSessionID = conSessionID;
    }

    public String getConSessionRoom() {
        return conSessionRoom;
    }

    public void setConSessionRoom(String conSessionRoom) {
        this.conSessionRoom = conSessionRoom;
    }

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getLecturer1() {
        return Lecturer1;
    }

    public void setLecturer1(String Lecturer1) {
        this.Lecturer1 = Lecturer1;
    }

    public String getLecturer2() {
        return Lecturer2;
    }

    public void setLecturer2(String Lecturer2) {
        this.Lecturer2 = Lecturer2;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public int getNoOfStudents() {
        return NoOfStudents;
    }

    public void setNoOfStudents(int NoOfStudents) {
        this.NoOfStudents = NoOfStudents;
    }

    public String getSessionRoom() {
        return sessionRoom;
    }

    public void setSessionRoom(String sessionRoom) {
        this.sessionRoom = sessionRoom;
    }

     
}

