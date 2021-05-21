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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IMAKA
 */
public class AddParallelSessionRoomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Rectangle tv;

    @FXML
    private Button btnParallelSave;

    @FXML
    private Button btnParallelClear;

    @FXML
    private ComboBox cmbParallelSession;

    @FXML
    private ComboBox cmbParallelSessionRoom;

    @FXML
    private Button btnParallelBack;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnParallelSave){
           insert();
           
       }else if(event.getSource() == btnParallelClear){
            
            cmbParallelSession.setValue("");
            cmbParallelSessionRoom.setValue("");
            
        }else if(event.getSource() == btnParallelBack){
            
            loadStage("SessionMain.fxml");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayParallelSession();
        DisplayParallelSessionRoom();
    }    
    @FXML
    private void DisplayParallelSession() {
         Connection con = connect();
       
        try{
                ObservableList<String> sessionList = FXCollections.observableArrayList();
    
                Statement stmt;
                
                stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from parallel");
               
                while(rs.next()){
                   sessionList.add(rs.getString("sessionID"));
              
                }
                 cmbParallelSession.setItems(sessionList);
                
        }catch(SQLException ex){
            Logger.getLogger(AddParallelSessionRoomController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void DisplayParallelSessionRoom() {
          Connection con = connect();
            
        try {
            ObservableList<String> RoomList = FXCollections.observableArrayList();
           
            Statement stmt1;
            
            stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from tbllocation");
            
            while(rs1.next()){
                ;
                 RoomList.add(rs1.getString("RoomName"));
           
            }
             cmbParallelSessionRoom.setItems(RoomList);
        } catch (SQLException ex) {
            Logger.getLogger(AddNonOverlappingSessionRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insert() {
        
        String query ="INSERT INTO parallelsesionroom(SessionID,RoomName) values('"+cmbParallelSession.getValue()+"','"+cmbParallelSessionRoom.getValue()+"')";
        String query2 ="INSERT INTO allsessionsrooms(sessionID,sessionRoom) values('"+cmbParallelSession.getValue()+"','"+cmbParallelSessionRoom.getValue()+"')";

            excecuteQuery(query);
            excecuteQuery(query2);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Successfully Added");
            alert.showAndWait();
           
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

    private Connection connect() {
          Connection con;
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
            return con;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "can not connect");
            return null;
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
