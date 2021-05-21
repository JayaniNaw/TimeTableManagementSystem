/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Sudarshana
 */
public class lecturers {

    private int ID;
    private String name;
    private String empID;
    private String faculty;
    private String department;
    private String activeDays;

    private String activeHours1;
    private String activeHours2;
    private String center;
    private String building;
    private int level;
    private String rank;

    public lecturers(int ID, String name, String empID, String faculty, String department, String activeDays, String activeHours1, String activeHours2, String center, String building, int level, String rank) {
        this.ID = ID;
        this.name = name;
        this.empID = empID;
        this.faculty = faculty;
        this.department = department;
        this.activeDays = activeDays;
        this.activeHours1 = activeHours1;
        this.activeHours2 = activeHours2;
        this.center = center;
        this.building = building;
        this.level = level;
        this.rank = rank;
    }

    public String getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(String activeDays) {
        this.activeDays = activeDays;
    }

    lecturers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getActiveHours1() {
        return activeHours1;
    }

    public void setActiveHours1(String activeHours1) {
        this.activeHours1 = activeHours1;
    }

    public String getActiveHours2() {
        return activeHours2;
    }

    public void setActiveHours2(String activeHours2) {
        this.activeHours2 = activeHours2;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

}
