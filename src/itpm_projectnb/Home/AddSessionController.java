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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Add Session Controller class
 *
 * @author IMAKA
 */
public class AddSessionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    @FXML
    private AnchorPane tv;

    @FXML
    private Label txtSelectSession;

    @FXML
    private Label txtSessionRoom;

    @FXML
    private Button btnSubmitSession;

    @FXML
    private ComboBox cmbSessionRoom;

    @FXML
    private Button btnClearSession;

    @FXML
    private ComboBox cmbSelectSession;

    @FXML
    private Button btnViewSession;

    @FXML
    private Label txtAddSessionRoom;

   

    @FXML
    void handleButtonAction(ActionEvent event) {

       if(event.getSource() == btnSubmitSession){
           insert();
       }
       else if(event.getSource() == btnClearSession){
            clear();
     
       }
       else if(event.getSource() == btnViewSession){
            
            loadStage("ManageSession.fxml");
     
       }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplaySession();
        DisplaySessionRoom();
    }  
    
    public Connection connect(){
        Connection con;
        try{
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root","");
            return con;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "can not connect");
            return null;
        }
        
    }
    public void loadStage(String fxml) {
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
    
    
    //combobox for sessions
    public void DisplaySession(){
        
        Connection con = connect();
       
        try{
                ObservableList<String> NormalSessionList = FXCollections.observableArrayList();
           
                Statement stmt;
                
                stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select sessionID from sessions");
               
                while(rs.next()){
                   // cmbSelectSession.getItems().addAll(rs.getString("sessionID"));
                   NormalSessionList.add(rs.getString("sessionID"));
                }
                cmbSelectSession.setItems(NormalSessionList);
        }catch(SQLException ex){
            Logger.getLogger(AddSessionController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void DisplaySessionRoom(){
    
        Connection con = connect();
            
        try {
           
             ObservableList<String> RoomList = FXCollections.observableArrayList();
           
            Statement stmt1;
            
            stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select RoomName from tbllocation");
            
            while(rs1.next()){
                //cmbSessionRoom.getItems().addAll(rs1.getString("RoomName"));
                 RoomList.add(rs1.getString("RoomName"));
            }
            cmbSessionRoom.setItems(RoomList);
        } catch (SQLException ex) {
            Logger.getLogger(AddSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void insert() {
      
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
       
            String query = "INSERT INTO sessionroom(sessionID,sessionRoom) values('" +cmbSelectSession.getValue() + "','" + cmbSessionRoom.getValue() + "')";
            excecuteQuery(query);
            
            alert.setTitle("Information Dialog");
            alert.setContentText("Successfully Added");
            alert.showAndWait();

          
    }
    
    //drop down list for session
     public void clear(){
        
        cmbSelectSession.setValue("");
        cmbSessionRoom.setValue("");
        
    }

    private void excecuteQuery(String query) {
        
        Connection con = connect();
        Statement st;
        try {
            st = con.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
        }
    }
        
}
