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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class ManageLocationController implements Initializable {

    @FXML
    private TextField txtBuildingName;

    @FXML
    private TextField txtRoomName;

    @FXML
    private TextField txtCapacity;
    
    @FXML
    private ComboBox cmbRoomType;
    ObservableList<String> hallList = FXCollections.observableArrayList("Lab", "Lecture hall");

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private TableView<tbllocation> tv;
    @FXML
    private TableColumn<tbllocation, String> colBuildingName;
    @FXML
    private TableColumn<tbllocation, String> colRoomName;
    @FXML
    private TableColumn<tbllocation, String> colRoomType;
    @FXML
    private TableColumn<tbllocation, Integer> colCapacity;
    @FXML
    private TableColumn<tbllocation, Integer> colLocationID;

    @FXML
    void handleButtonAction(ActionEvent event) {
     
        if(event.getSource() == btnUpdate){
            update();

        }else if(event.getSource() == btnDelete){
            delete();

        }else if(event.getSource() == btnClear){
            clear();

        }
        else if(event.getSource() == btnBack){
            loadStage("home.fxml");

        }
    
    }

    /**
     * Initializes the controller class.
     */
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbRoomType.setItems(hallList);
        
        showLocations();
        retrieve();
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
   
    public ObservableList<tbllocation> getLocationList() {
        ObservableList<tbllocation> locationList = FXCollections.observableArrayList();
        Connection con = connect();

        String query = "select * from tbllocation";
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            tbllocation location;

            while (rs.next()) {
                
                location = new tbllocation(rs.getInt("LocationID"), rs.getString("BuildingName"), rs.getString("RoomName"), rs.getString("RoomType"), rs.getInt("Capacity"));
                locationList.add(location);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return locationList;

    }
    public void showLocations() {
       
        ObservableList<tbllocation> list = getLocationList();

        colLocationID.setCellValueFactory(new PropertyValueFactory<tbllocation, Integer>("LocationID"));
        colBuildingName.setCellValueFactory(new PropertyValueFactory<tbllocation, String>("BuildingName"));
        colRoomName.setCellValueFactory(new PropertyValueFactory<tbllocation, String>("RoomName"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<tbllocation, String>("RoomType"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<tbllocation, Integer>("Capacity"));
        
        tv.setItems(list);
    }

     @FXML
    public void retrieve() {
    
        tv.setOnMouseClicked(e -> {
            Connection con = connect();
            try {
                tbllocation location = (tbllocation) tv.getSelectionModel().getSelectedItem();

                String query = "SELECT * FROM tbllocation WHERE LocationID=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, location.getLocationID());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    txtBuildingName.setText(rs.getString("BuildingName"));
                    txtRoomName.setText(rs.getString("RoomName"));
                    cmbRoomType.setValue(rs.getString("RoomType"));
                    txtCapacity.setText(rs.getString("Capacity"));
                    
                }
            }catch(Exception ex){
            
            }

                });
    }   
    public void update() {
        btnUpdate.setOnAction(e -> {
            

               
             if (txtBuildingName.getText().isEmpty() ||  txtRoomName.getText().isEmpty() ||   txtCapacity.getText().isEmpty()){
           
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setContentText("Please fill all fields");
                    alert.showAndWait();
                    
                }
                else {
                   // UPDATE Cats SET name=?, race=?, gender=?, coat_color=?, age=? WHERE ID=? 
                 //   String query = "UPDATE tbllocation SET BuildingName=?,RoomName=?,RoomType=?,Capacity=? where LocationID =? ";
                    String query = "UPDATE tbllocation SET BuildingName='" + txtBuildingName.getText() + "',RoomName='" + txtRoomName.getText() + "',RoomType='" + cmbRoomType.getValue() + "',Capacity=" + txtCapacity.getText() + " where RoomName= '"+txtRoomName.getText()+"'";
                   
                   // String query = "UPDATE tbllocation SET BuildingName='" + txtBuildingName.getText() + "',RoomType='" + cmbRoomType.getValue() + "',RoomName='" + txtRoomName.getText() + "',Capacity=" + txtCapacity.getText()+"'where LocationID='"+colLocationID.getText()+"'";
                    
                    excecuteQuery(query);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Updated successfully");
                    alert.showAndWait();
                    showLocations();
                    clear();
                    
                    
                    
                }
                


        });

    }

    public void delete() {

        String query = "delete from tbllocation where RoomName = '" + txtRoomName.getText() + "'";
        excecuteQuery(query);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Deleted successfully");
        alert.showAndWait();
        showLocations();
        

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
