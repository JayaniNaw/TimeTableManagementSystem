/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

/**
 *
 * @author Sudarshana
 */
public class subjects {
    private int ID;
    private int year;
    private int semester;
    private String name;
    private String code;
    private int noOfLecture;
    private int noOfTute;
    private int noOfLab;
    private int noOfEva;

    public subjects( int ID,int year, int semester, String name, String code, int noOfLecture, int noOfTute, int noOfLab, int noOfEva) {
        this.ID = ID;
        this.year = year;
        this.semester = semester;
        this.name = name;
        this.code = code;
        this.noOfLecture = noOfLecture;
        this.noOfTute = noOfTute;
        this.noOfLab = noOfLab;
        this.noOfEva = noOfEva;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNoOfLecture() {
        return noOfLecture;
    }

    public void setNoOfLecture(int noOfLecture) {
        this.noOfLecture = noOfLecture;
    }

    public int getNoOfTute() {
        return noOfTute;
    }

    public void setNoOfTute(int noOfTute) {
        this.noOfTute = noOfTute;
    }

    public int getNoOfLab() {
        return noOfLab;
    }

    public void setNoOfLab(int noOfLab) {
        this.noOfLab = noOfLab;
    }

    public int getNoOfEva() {
        return noOfEva;
    }

    public void setNoOfEva(int noOfEva) {
        this.noOfEva = noOfEva;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
