/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.DbConnect;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adeesha
 */
public class ManageStudentController implements Initializable {

    @FXML
    private ComboBox AcYrComb;    
    ObservableList<String> list1 = FXCollections.observableArrayList("Y1S1", "Y1S2", "Y2S1","Y2S2","Y3S1","Y3S2","Y4S1","Y4S2");
   
    @FXML
    private ComboBox programComb;
    ObservableList<String> list2 = FXCollections.observableArrayList("IT", "SE","CS","DS","ISE","IM","CSNE");
    
    @FXML
    private ComboBox groupNoComb;
    ObservableList<String> list3 = FXCollections.observableArrayList("1", "2","3","4","5","6","7","8", "9", "10");
    
    @FXML
    private ComboBox subgroupNoComb;
    ObservableList<String> list4 = FXCollections.observableArrayList("1", "2","3","4","5","6","7","8", "9", "10");    
    
    
    @FXML
    private TableView<Groups> stdGroupsTable;
    @FXML
    private TableColumn<Groups, Integer> id;
    @FXML
    private TableColumn<Groups, String> yearAndSem;
    @FXML
    private TableColumn<Groups, String> program;
    @FXML
    private TableColumn<Groups, String> grpNo;
    @FXML
    private TableColumn<Groups, String> subGrpNo;
    @FXML
    private TableColumn<Groups, String> grpID;
    @FXML
    private TableColumn<Groups, String> subGrpID;
    @FXML
    private TextField txtgrpID;
    @FXML
    private TextField txtsubgrpID;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private FontAwesomeIconView switchIcon;
    @FXML
    private FontAwesomeIconView exitIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       viewGroups();                                                            //call this method by loading the scene
       AcYrComb.setItems(list1);                                                //set values in combobox 
       programComb.setItems(list2);
       groupNoComb.setItems(list3);
       subgroupNoComb.setItems(list4);
    }    
    
    ///handle button actions of update and delete buttons
    @FXML
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
     if (actionEvent.getSource() == updateBtn)
       {
           updateGroup();
            AlertBox ab = new AlertBox(); 
           ab.displayInfo("Success", "Student group has been updated");
       }
     else if (actionEvent.getSource() == deleteBtn)
     {
       AlertBox ab = new AlertBox();  
       boolean isOk=  ab.deleteConfirmation("Are you sure you want to delete?");
         if (isOk)
           deleteGroup();  
     }
    }
    
    
     Connection con = DbConnect.connectDB();            // connect to the Dbhelper class
     PreparedStatement preState = null;
     ResultSet rs = null;
     int ID;                                            //declare a global variable ID
        
        
     //get groups from the database and add those to an arraylist called groupslist then pass those data to Groups class by the constructor
     public ObservableList<Groups> getGroupsList()
    {
        ObservableList<Groups> groupsList = FXCollections.observableArrayList();
        Groups groups;
          
        try {
            String selectQuery= "SELECT * FROM studentgroups";
            preState = con.prepareStatement(selectQuery);
            rs = preState.executeQuery(selectQuery);
            
            while ( rs.next())
            {
                groups = new Groups(rs.getInt("ID"), rs.getString("YearAndSem"), rs.getString("Programme"), rs.getString("GroupNo"), rs.getString("SubGroupNo"), rs.getString("GroupID"), rs.getString("SubGroupID"));
                groupsList.add(groups);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
         return groupsList;
    }
     
    //get those groups details from the grouplist then add those to the the Student Groups table 
    public void viewGroups()
    {
        
        ObservableList<Groups> list = getGroupsList();

        id.setCellValueFactory(new PropertyValueFactory("ID"));
        yearAndSem.setCellValueFactory(new PropertyValueFactory("YearAndSem"));
        program.setCellValueFactory(new PropertyValueFactory("program"));
        grpNo.setCellValueFactory(new PropertyValueFactory("groupNo"));
        subGrpNo.setCellValueFactory(new PropertyValueFactory("subgroupNo"));
        grpID.setCellValueFactory(new PropertyValueFactory("groupID"));
        subGrpID.setCellValueFactory(new PropertyValueFactory("subgroupID"));

        stdGroupsTable.setItems(list);
        
    }

    //setup a mouseevent action to a table so by clicking any table coulmn of group details the textfields are filled with those details so the user can edit if wants.
    @FXML
    private void handleMouseAction(MouseEvent event) {
               Groups group= stdGroupsTable.getSelectionModel().getSelectedItem();
               ID = group.getID();
               AcYrComb.setValue(group.getYearAndSem());
               programComb.setValue(group.getProgram());
               groupNoComb.setValue(group.getGroupNo());
               subgroupNoComb.setValue(group.getSubgroupNo());
               txtgrpID.setText(group.getGroupID());
               txtsubgrpID.setText(group.getSubgroupID());
    }
    
    //edit the values in texfields then by clicking the update button those values will be updated
    public void updateGroup()
    {
          String  YearAndSem = AcYrComb.getValue().toString();
          String   program = programComb.getValue().toString();
          String   groupNo = groupNoComb.getValue().toString();
          String  subgroupNo = subgroupNoComb.getValue().toString();
          String  groupID = YearAndSem+"."+groupNo;
          String  subgroupID = groupID+"."+subgroupNo;
          txtgrpID.setText(groupID);
          txtsubgrpID.setText(subgroupID);
        try {
            String updateQuery = "UPDATE studentgroups SET YearAndSem= '"+ YearAndSem+"' , Programme= '"+ program+"' , GroupNo= '"+ groupNo+"' , SubGroupNo= '"+ subgroupNo+"', GroupID= '"+ groupID+"', SubGroupID= '"+ subgroupID+"' WHERE ID = '"+ ID +"' ";
            preState = con.prepareStatement(updateQuery);
            preState.executeUpdate();
            viewGroups();

        } catch (SQLException ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
   // by clicking the delete button the selected group will be deleted
    public void deleteGroup()
    {  
          
        try {
            String deleteQuery = "DELETE FROM studentgroups WHERE ID = '"+ ID +"' ";
            preState = con.prepareStatement(deleteQuery);
            preState.executeUpdate();
            viewGroups();

        } catch (SQLException ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @FXML
    private void switchScene(MouseEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("addStudentGroups.fxml"));

        Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }
    @FXML
        public void setToolTip()
        {
            Tooltip.install(switchIcon, new Tooltip("Switch to Add Student Groups."));

        }

    @FXML
    private void exitScene(MouseEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("home.fxml"));
   

        Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
}
   
}
  