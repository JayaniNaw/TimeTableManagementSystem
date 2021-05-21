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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML location Controller class
 *
 * @author Sudarshana
 */
public class LocationController implements Initializable {
 
    @FXML
    private TextField txtBuildingName;

    @FXML
    private TextField txtRoomName;

    @FXML
    private TextField txtCapacity;

    @FXML
    private Button btnsave;

    @FXML
    private ComboBox  cmbRoomType;
    
    ObservableList<String> hallList = FXCollections.observableArrayList("Lab", "Lecture Hall");
    
    @FXML
    private Button btnClear;
    
    @FXML
    private Button btnViewLocation;
     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            cmbRoomType.setItems(hallList);     
    }  
    
    @FXML
    void handleButtonAction(ActionEvent event) {
        
        if(event.getSource() == btnsave){
           insert();
        }else if(event.getSource() == btnClear){
           clear();
        }
        else if(event.getSource() == btnViewLocation){
           loadStage("ManageLocation.fxml");
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
    private void insert(){
       
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
       if (txtBuildingName.getText().isEmpty() ||  txtRoomName.getText().isEmpty() ||   txtCapacity.getText().isEmpty()){
            alert.setTitle("Error");
            alert.setContentText("Not any value can be empty. Please fill");
            alert.showAndWait();
       }
     
       else {
       
            String query = "INSERT INTO tbllocation(BuildingName,RoomName,RoomType,Capacity) values('" + txtBuildingName.getText() + "','" + txtRoomName.getText() + "','" + cmbRoomType.getValue() + "'," + txtCapacity.getText() + ")";
            excecuteQuery(query);
            
            alert.setTitle("Information Dialog");
            alert.setContentText("Successfully Added");
            alert.showAndWait();

            loadStage("ManageLocation.fxml");
            
            txtBuildingName.setText("");
            txtRoomName.setText("");
            cmbRoomType.setValue("");
            txtCapacity.setText("");
      
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

    private void clear() {
         
            txtBuildingName.setText("");
            txtRoomName.setText("");
            cmbRoomType.setValue("");
            txtCapacity.setText("");
    
    }

  
}
