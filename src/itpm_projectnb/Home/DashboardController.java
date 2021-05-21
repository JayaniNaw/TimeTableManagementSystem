/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class DashboardController implements Initializable {

    private TextField txtLecture;
    @FXML
    private Label lblLecture;
    @FXML
    private Label lblStudents;
    @FXML
    private Label lblSubjects;
    @FXML
    private Label lblRooms;
    @FXML
    private Label lblLect;
    @FXML
    private Label lblGroup;
    @FXML
    private Label lblSubject;
    @FXML
    private BarChart graph;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        retrieveLectureCount();
        retrieveStudentsCount();
        retrieveSubjectCount();
        retrieveRoomsCount();
        retrieveLatestLecture();
        retrieveLatestGroup();
        retrieveLatestSubjects();
        retrieveLabCount();
        retrieveLecturersCount();
        
    }
    public Connection connect(){
        Connection con;
        try{
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root","");
            return con;
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "can not connect");
            return null;
        }
        
    }
    public void retrieveLectureCount(){
           Connection con = connect();
           
           try{
               String sql = "Select count(*) from lecturers";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               if(rs.next()){
                   String count = rs.getString("count(*)");
                   lblLecture.setText(count);
               
               }
           
           }catch(SQLException e){
           
           
           }
    }
    public void retrieveStudentsCount(){
           Connection con = connect();
           
           try{
               String sql = "Select count(*) from students";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               if(rs.next()){
                   String count = rs.getString("count(*)");
                   lblStudents.setText(count);
               
               }
           
           }catch(Exception e){
           
           
           }
    }
    public void retrieveSubjectCount(){
           Connection con = connect();
           
           try{
               String sql = "Select count(*) from subjects";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               if(rs.next()){
                   String count = rs.getString("count(*)");
                   lblSubjects.setText(count);
               
               }
           
           }catch(Exception e){
           
           
           }
    }
    public void retrieveRoomsCount(){
           Connection con = connect();
           
           try{
               String sql = "Select count(*) from tbllocation";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               if(rs.next()){
                   String count = rs.getString("count(*)");
                   lblRooms.setText(count);
                   
               
               }
           
           }catch(Exception e){
           
           
           }
    }
    public void retrieveLatestLecture(){
           Connection con = connect();
           
           try{
               String sql = "select name from lecturers where ID =(Select max(ID) from lecturers)";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               if(rs.next()){
                   String count = rs.getString("name");
                   lblLect.setText(count);
                   
                   
               
               }
           }catch(Exception e){
           
           
           }
    }
    public void retrieveLatestGroup(){
           Connection con = connect();
           
           try{
               String sql = "select GroupID from studentgroups where ID =(Select max(ID) from studentgroups)";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               if(rs.next()){
                   String count = rs.getString("GroupID");
                   lblGroup.setText(count);
                   
                   
               
               }
           }catch(Exception e){
           
           
           }
    }
    public void retrieveLatestSubjects(){
           Connection con = connect();
           
           try{
               String sql = "select name from subjects where ID =(Select max(ID) from subjects)";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               if(rs.next()){
                   String count = rs.getString("name");
                   lblSubject.setText(count);
                   
                   
               
               }
           }catch(Exception e){
           
           
           }
    }
    public void retrieveLabCount(){
          try{
             Connection con = connect();
               String sql = "select count(*) from tbllocation where RoomType='Lab'";
               
               PreparedStatement pst = con.prepareStatement(sql);
               
               ResultSet rs = pst.executeQuery();
               
               if(rs.next()){
                   int count = rs.getInt("count(*)");
                   
                   XYChart.Series set1 = new XYChart.Series<>();
                   
                    set1.getData().add(new XYChart.Data("Lab",count));
                    
                    
                    graph.getData().addAll(set1);
                   
               
               }
           }catch(Exception e){
           
           
           }
           
           
    }
    public void retrieveLecturersCount(){
          try{
             Connection con = connect();
               String sql = "select count(*) from tbllocation where RoomType='Lecture Hall'";
               
               PreparedStatement pst = con.prepareStatement(sql);
               
               ResultSet rs = pst.executeQuery();
               
               if(rs.next()){
                   int count = rs.getInt("count(*)");
                   
                   XYChart.Series set1 = new XYChart.Series<>();
                   
                    set1.getData().add(new XYChart.Data("Lecture Hall",count));
                    
                    
                    graph.getData().addAll(set1);
                   
               
               }
           }catch(Exception e){
           
           
           }
           
           
    }
    
}
