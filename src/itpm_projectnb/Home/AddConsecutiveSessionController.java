/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Add consecutive session controller class
 *
 * @author IMAKA
 */
public class AddConsecutiveSessionController implements Initializable {

    @FXML
    private AnchorPane tv;

    @FXML
    private Button btnConSave;

    @FXML
    private Button btnConClear;

    @FXML
    private ComboBox cmbConSession;

    @FXML
    private ComboBox cmbConSelectRoom;

    @FXML
    private Button btnConBack;

    
    @FXML
    void handleButtonAction(ActionEvent event) {
        
       if(event.getSource() == btnConSave){
           insert();
       }
       else if(event.getSource() == btnConClear){
            clear();
     
       }
       else if(event.getSource() == btnConBack){
            
            loadStage("SessionMain.fxml");
      }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayConsecutiveSession();
        DisplayConsecutiveSessionRoom();
    } 
    
    public Connection connect() {
        Connection con;
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
            return con;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "can not connect");
            return null;
        }
    }
     
    //combobox for consecutive sessions
    public void DisplayConsecutiveSession(){
        
        Connection con = connect();
       
        try{
                ObservableList<String> sessionList = FXCollections.observableArrayList();
    
                Statement stmt;
                
                stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from consecutive");
               
                while(rs.next()){
                    sessionList.add(rs.getString("sessionID"));
                }
                cmbConSession.setItems(sessionList);
                
        }catch(SQLException ex){
            Logger.getLogger(AddConsecutiveSessionController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void DisplayConsecutiveSessionRoom(){
    
        Connection con = connect();
            
        try {
            
            ObservableList<String> RoomList = FXCollections.observableArrayList();
           
            Statement stmt1;
            
            stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from tbllocation");
            
            while(rs1.next()){
                
                RoomList.add(rs1.getString("RoomName"));
            }
            cmbConSelectRoom.setItems(RoomList);
        } catch (SQLException ex) {
            Logger.getLogger(AddConsecutiveSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    private void insert() {
        
      //   String query = "INSERT INTO consecutivesessionroom(conSessionID,conSessionRoom ) values('"+cmbConSession.getValue()+"','"+cmbConSelectRoom.getValue()+"')";
            
      String query ="INSERT INTO consecutivesessionroom(conSessionID,conSessionRoom) values('"+cmbConSession.getValue()+"','"+cmbConSelectRoom.getValue()+"')";
      String query2 ="INSERT INTO allsessionsrooms(sessionID,sessionRoom) values('"+cmbConSession.getValue()+"','"+cmbConSelectRoom.getValue()+"')";

      
            excecuteQuery(query);
            excecuteQuery(query2);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Successfully Added");
            alert.showAndWait();
     
    }

    private void clear() {
       
        cmbConSession.setValue("");
        cmbConSelectRoom.setValue("");
    
    }
     private void loadStage(String fxml) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));

            Stage stage = new Stage();
            stage.close();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    private void excecuteQuery(String query) {
        
        Connection con = connect();
        Statement st;
       
        try {
            st = con.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }   
}
