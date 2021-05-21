/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import static com.sun.deploy.config.JREInfo.clear;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IMAKA
 */
public class ManageConsecutiveSessionRoomController implements Initializable {

    @FXML
    private ComboBox cmbConSession;

    @FXML
    private ComboBox cmbConSelectRoom;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private TableView tv;

    @FXML
    private TableColumn<sessionroom,String> colSessionID;

    @FXML
    private TableColumn<sessionroom,String> colRoomName;
    
    
    @FXML
    private TableColumn<sessionroom,Integer> colID;

    @FXML
    private Button btnBack;

      @FXML
    private TextField txtID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          showLocations();
          retrieveRoom();
    } 
    
    public Connection connect() {
        Connection con;
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
            return con;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "can not connect");
            return null;
        }

    }
    @FXML
    public void handleButtonAction(ActionEvent event) {
        
        if(event.getSource() == btnBack){
            loadStage("SessionMain.fxml");
        }else if(event.getSource()==btnUpdate){
            update();
        
        }else if(event.getSource()==btnDelete){
            delete();
        }
        else if(event.getSource() == btnClear){
            clear();

        }
    }
    
     public ObservableList<sessionroom> getSessionList() {
        ObservableList<sessionroom> sessionList = FXCollections.observableArrayList();
        Connection con = connect();

        String query = "select * from consecutivesessionroom";
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            sessionroom room;

            while (rs.next()) {
                
                room = new sessionroom(rs.getInt("ID"), rs.getString("conSessionID"), rs.getString("conSessionRoom"));
                sessionList.add(room);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sessionList;

    }
     
    public void showLocations() {
       
        ObservableList<sessionroom> list = getSessionList();

        colID.setCellValueFactory(new PropertyValueFactory<sessionroom, Integer>("ID"));
        colSessionID.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("conSessionID"));
        colRoomName.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("conSessionRoom"));
       
        
        tv.setItems(list);
    }
   
    @FXML
    public void DisplayConsecutiveSession() {
         Connection con = connect();
       
        try{
                ObservableList<String> sessionList = FXCollections.observableArrayList();
    
                Statement stmt;
                
                stmt=con.createStatement();
              //  ResultSet rs=stmt.executeQuery("select * from consecutive");
               ResultSet rs=stmt.executeQuery("select * from consecutivesessionroom");
               
                while(rs.next()){
                    sessionList.add(rs.getString("conSessionID"));
                }
                cmbConSession.setItems(sessionList);
                
        }catch(SQLException ex){
            Logger.getLogger(ManageConsecutiveSessionRoomController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    public void DisplayConsecutiveSessionRoom() {
       
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
            Logger.getLogger(ManageConsecutiveSessionRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    public void retrieveRoom() {
          tv.setOnMouseClicked(e -> {
            Connection con = connect();
            try {
                sessionroom room = (sessionroom) tv.getSelectionModel().getSelectedItem();

                String query = "SELECT * FROM consecutivesessionroom WHERE ID=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, room.getID());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    txtID.setText(rs.getString("ID"));
                    cmbConSession.setValue(rs.getString("conSessionID"));
                    cmbConSelectRoom.setValue(rs.getString("conSessionRoom"));
                    
                }
            }catch(Exception ex){
            
            }

        });
    }
    
     public void update() {
        btnUpdate.setOnAction(e -> {
            
               String query = "UPDATE consecutivesessionroom SET conSessionID='" + cmbConSession.getValue() + "',conSessionRoom='" + cmbConSelectRoom.getValue() + "' where conSessionID= '"+cmbConSession.getValue()+"'";
                  
               excecuteQuery(query);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Updated successfully");
                    alert.showAndWait();
                    showLocations();
                    clear();
            cmbConSession.setValue("");
            cmbConSelectRoom.setValue("");

        });

    }

    public void delete() {

        String query = "delete from consecutivesessionroom where  ID = '" + txtID.getText() + "'";
        excecuteQuery(query);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Deleted successfully");
        alert.showAndWait();
        showLocations();
        

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
