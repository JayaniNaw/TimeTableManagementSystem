package itpm_projectnb.Home;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sudarshana
 */
public class sessions {

    int ID;
    String Lecturer1;
    String Lecturer2;
    String AdditionalLecturer1;
    String AdditionalLecturer2;
    String Tag;
    String GroupID;
    String SubGroupID;
    String SubjectCode;
    String Subject;
    String NoOfStudents;
    String Duration;
    String sessionID;

    public sessions(int ID, String Lecturer1, String Lecturer2, String AdditionalLecturer1, String AdditionalLecturer2, String Tag, String GroupID, String SubGroupID, String SubjectCode, String Subject, String NoOfStudents, String Duration, String sessionID) {
        this.ID = ID;
        this.Lecturer1 = Lecturer1;
        this.Lecturer2 = Lecturer2;
        this.AdditionalLecturer1 = AdditionalLecturer1;
        this.AdditionalLecturer2 = AdditionalLecturer2;
        this.Tag = Tag;
        this.GroupID = GroupID;
        this.SubGroupID = SubGroupID;
        this.SubjectCode = SubjectCode;
        this.Subject = Subject;
        this.NoOfStudents = NoOfStudents;
        this.Duration = Duration;
        this.sessionID = sessionID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getAdditionalLecturer1() {
        return AdditionalLecturer1;
    }

    public void setAdditionalLecturer1(String AdditionalLecturer1) {
        this.AdditionalLecturer1 = AdditionalLecturer1;
    }

    public String getAdditionalLecturer2() {
        return AdditionalLecturer2;
    }

    public void setAdditionalLecturer2(String AdditionalLecturer2) {
        this.AdditionalLecturer2 = AdditionalLecturer2;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String GroupID) {
        this.GroupID = GroupID;
    }

    public String getSubGroupID() {
        return SubGroupID;
    }

    public void setSubGroupID(String SubGroupID) {
        this.SubGroupID = SubGroupID;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getNoOfStudents() {
        return NoOfStudents;
    }

    public void setNoOfStudents(String NoOfStudents) {
        this.NoOfStudents = NoOfStudents;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    
    
    




    
}
