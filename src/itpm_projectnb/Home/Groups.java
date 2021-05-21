/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

/**
 *
 * @author Adeesha
 */
public class Groups {
    
    private int ID;
    private String YearAndSem;
    private String program;
    private String groupNo;
    private String subgroupNo;
    private String groupID;
    private String subgroupID;

    public Groups(int ID, String YearAndSem, String program, String groupNo, String subgroupNo, String groupID, String subgroupID) {
        this.ID = ID;
        this.YearAndSem = YearAndSem;
        this.program = program;
        this.groupNo = groupNo;
        this.subgroupNo = subgroupNo;
        this.groupID = groupID;
        this.subgroupID = subgroupID;
    }

    public int getID() {
        return ID;
    }

    public String getYearAndSem() {
        return YearAndSem;
    }

    public String getProgram() {
        return program;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public String getSubgroupNo() {
        return subgroupNo;
    }

    public String getGroupID() {
        return groupID;
    }

    public String getSubgroupID() {
        return subgroupID;
    }
    
    
    
}
