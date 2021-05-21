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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IMAKA
 */
public class AddNonOverlappingSessionRoomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Rectangle tv;
    @FXML
    private Button btnOverSave;
    @FXML
    private Button btnOverClear;
    @FXML
    private ComboBox cmbOverSession;
    @FXML
    private ComboBox cmbOverSelectRoom;
    @FXML
    private Button btnOverBack;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayNonOverlappingSession();
        DisplayNonOverlappingSessionRoom();
    } 
    
    @FXML
    private void handleButtonAction(ActionEvent event){
       if(event.getSource() == btnOverSave){
           insert();
           
       }else if(event.getSource() == btnOverClear){
            
            cmbOverSession.setValue("");
            cmbOverSelectRoom.setValue("");
            
        }else if(event.getSource() == btnOverBack){
            
            loadStage("SessionMain.fxml");
        }
         
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
    private void insert(){
        
        String query ="INSERT INTO notoverlappingsessionroom(SessionID,RoomName	) values('"+cmbOverSession.getValue()+"','"+cmbOverSelectRoom.getValue()+"')";
        String query2 ="INSERT INTO allsessionsrooms(sessionID,sessionRoom) values('"+cmbOverSession.getValue()+"','"+cmbOverSelectRoom.getValue()+"')";

        
            excecuteQuery(query);
            excecuteQuery(query2);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Successfully Added");
            alert.showAndWait();
                
           
    }
    
    //combobox for non overlapping sessions
    public void DisplayNonOverlappingSession(){
        
        Connection con = connect();
       
        try{
                 ObservableList<String> sessionList = FXCollections.observableArrayList();
    
                Statement stmt;
                
                stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from notoverlapping");
               
                while(rs.next()){
                    //cmbOverSession.getItems().addAll(rs.getString("sessionID"));
                    sessionList.add(rs.getString("sessionID"));
              
                }
                 cmbOverSession.setItems(sessionList);
                
        }catch(SQLException ex){
            Logger.getLogger(AddNonOverlappingSessionRoomController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    //combobox for non overlapping session Rooms
    public void DisplayNonOverlappingSessionRoom(){
    
        Connection con = connect();
            
        try {
            ObservableList<String> RoomList = FXCollections.observableArrayList();
           
            Statement stmt1;
            
            stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from tbllocation");
            
            while(rs1.next()){
                //cmbOverSelectRoom.getItems().addAll(rs1.getString("RoomName"));
                 RoomList.add(rs1.getString("RoomName"));
           
            }
             cmbOverSelectRoom.setItems(RoomList);
        } catch (SQLException ex) {
            Logger.getLogger(AddNonOverlappingSessionRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
