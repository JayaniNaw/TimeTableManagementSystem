/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.DbConnect;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adeesha
 */
public class ViewSessionsController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private ComboBox sessionTypes;
    ObservableList<String> list1 = FXCollections.observableArrayList("Consecutive sessions", "Parallel sessions", "Non overlapping sessions");
    
   @FXML
    private TableView<Session> sessionsTable;
    
    @FXML
    private TableColumn<Session, Integer> id;
    @FXML
    private TableColumn<Session, String> lecturer1;
    @FXML
    private TableColumn<Session, String> lecturer2;
    @FXML
    private TableColumn<Session, String> subjectCode;
    @FXML
    private TableColumn<Session, String> subject;
    @FXML
    private TableColumn<Session, String> grpID;
    @FXML
    private TableColumn<Session, String> tag;
    @FXML
    private TableColumn<Session, String> duration;
    @FXML
    private TableColumn<Session, String> sessionID;
    
    @FXML
    private Button deleteBtn;
    @FXML
    private Text sessionTypeText;
    @FXML
    private FontAwesomeIconView switchIcon;
    

        Connection con = DbConnect.connectDB();    
        PreparedStatement preState = null;
        ResultSet rs = null;
        String selectQuery;
        String deleteQuery;
        String sessionName; 
        int ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                sessionTypes.setItems(list1);
                sessionTypes.setValue(list1.get(0));
                sessionName = sessionTypes.getSelectionModel().getSelectedItem().toString();
                viewSessions();                                                                             //call this method by loading the scene
    }    
    
    //setup a mouseevent action to a table to get the session id of a selected session   
    @FXML
    private void handleMouseAction(MouseEvent event) {
        Session session= sessionsTable.getSelectionModel().getSelectedItem();
               ID = session.getID();
    }
    
    //get the selected session type from the checkbox of session types
    @FXML
    private void getSessionType(ActionEvent event) {
         sessionName = sessionTypes.getSelectionModel().getSelectedItem().toString();
                        viewSessions();
    }
  
    
    //get each sessions from the database according to the session type then and add those to an arraylist called sessionsList then pass those data to Session class by the constructor
     public ObservableList<Session> getSessionsList()
    {
        ObservableList<Session> sessionsList = FXCollections.observableArrayList();
        Session session;
                 
        try {
            if ( sessionName.equals("Parallel sessions") )  
                    {
                        sessionTypeText.setText("Parallel sessions");
                        selectQuery   = "SELECT * FROM parallel";     
                    }  
            else if ( sessionName.equals("Non overlapping sessions") )
                    {  
                                                sessionTypeText.setText("Non overlapping sessions");
                        selectQuery= "SELECT * FROM notoverlapping";}
            else
                    {  
                        sessionTypeText.setText("Consecutive sessions");
                    selectQuery= "SELECT * FROM consecutive";}

    
            preState = con.prepareStatement(selectQuery);
            rs = preState.executeQuery(selectQuery);
            
            while ( rs.next())
            {
                session = new Session(rs.getInt("ID"), rs.getString("Lecturer1"), rs.getString("Lecturer2"), rs.getString("SubjectCode"), rs.getString("Subject"), rs.getString("GroupID"), rs.getString("Tag"), rs.getString("Duration"), rs.getString("sessionID"));
                sessionsList.add(session);

            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
         return sessionsList;
        }
        
     
    //get those sessions details from the sessionsList then add those to the the Sessions table 
    public void viewSessions()
    {
        
        ObservableList<Session> list = getSessionsList();

        id.setCellValueFactory(new PropertyValueFactory("ID"));
        lecturer1.setCellValueFactory(new PropertyValueFactory("lecturer1"));
        lecturer2.setCellValueFactory(new PropertyValueFactory("lecturer2"));
        subjectCode.setCellValueFactory(new PropertyValueFactory("subjectCode"));
        subject.setCellValueFactory(new PropertyValueFactory("subject"));
        grpID.setCellValueFactory(new PropertyValueFactory("groupID"));
        tag.setCellValueFactory(new PropertyValueFactory("tag"));
        duration.setCellValueFactory(new PropertyValueFactory("duration"));
        sessionID.setCellValueFactory(new PropertyValueFactory("sessionID"));
        sessionsTable.setItems(list);

    }
    
    
    // by clicking the delete button the selected session will be deleted
    public void deleteSession()
        {  
          
        try {
             if ( sessionName.equals("Parallel sessions") )  
                    {
                        sessionTypeText.setText("Parallel sessions");
                        deleteQuery = "DELETE FROM parallel WHERE ID = '"+ ID +"' ";                    }  
            else if ( sessionName.equals("Non overlapping sessions") )
                    {  
                        sessionTypeText.setText("Non overlapping sessions");
                        deleteQuery = "DELETE FROM notoverlapping WHERE ID = '"+ ID +"' ";                    }  
            else
                    {  
                        sessionTypeText.setText("Consecutive sessions");
                        deleteQuery = "DELETE FROM consecutive WHERE ID = '"+ ID +"' ";                    }  
                
            preState = con.prepareStatement(deleteQuery);
            preState.executeUpdate();
            viewSessions();
        
        } catch (SQLException ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    
   ///handle button actions of the delete button
    @FXML
    private void handleButtonAction(ActionEvent event) {
     if (event.getSource() == deleteBtn)
     {
       AlertBox ab = new AlertBox();  
       boolean isOk=  ab.deleteConfirmation("Are you sure you want to delete this session?");
         if (isOk)
           deleteSession();  
     }
    }

    @FXML
    private void setToolTip() {
                    Tooltip.install(switchIcon, new Tooltip("View all sessions"));
 
    }

    @FXML
    private void switchScene(MouseEvent event) throws IOException {
        
        Parent pane = FXMLLoader.load(getClass().getResource("viewallSessions.fxml"));
   

        Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }

    private void exitScene(MouseEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("home.fxml"));
   

        Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }
 
}
