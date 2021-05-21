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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
public class ViewAllSessionsController implements Initializable {
    
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
    private TableColumn<Session, String> selectSession;
  
    private TextField txtgrpID;
    private TextField txtsubgrpID;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private FontAwesomeIconView switchIcon;
    @FXML
    private FontAwesomeIconView exitIcon;
    @FXML
    private RadioButton consecSession;
    @FXML
    private RadioButton parallelSession;
    @FXML
    private RadioButton noOverlapSession;
    @FXML
    private Button addSessionBtn;
    
    @FXML
    private ToggleGroup sessions;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       sessions.getToggles().get(0).setSelected(true);                          //set the conseecutive sessions radio button checked when loading the scene
       viewSessions();                                                          //call this method by loading the scene
    }    
    
    @FXML
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        
     if (actionEvent.getSource() == addSessionBtn)
       {
           addSession();         
       }
    }
    
    
        Connection con = DbConnect.connectDB();                                 // connect to the Dbhelper class
        PreparedStatement preState, preState2, preState3;
        ResultSet rs, rs2, rs3;
        String query, query2, query3;
        String sessionId;
        String startT;
        String endT;
        String time;
        
        
     //get sessions from the database and add those to an arraylist called sessionsList then pass those data to Session class by the constructor
     public ObservableList<Session> getSessionsList()
    {
        ObservableList<Session> sessionsList = FXCollections.observableArrayList();
        Session session;
           
        try {
           String selectQuery=  "SELECT s.ID, s.Lecturer1, s.Lecturer2, s.SubjectCode, s.Subject, s.GroupID, s.Tag, p.startT, p.endT, s.sessionID FROM sessions s INNER JOIN prefertime p ON s.sessionID = p.sessionID";
           preState = con.prepareStatement(selectQuery);
           rs = preState.executeQuery(selectQuery);
                      
            while ( rs.next())
                
            {  
                startT =rs.getTime("startT").toString();
                endT = rs.getTime("endT").toString();
                time = startT.substring(0, startT.length()-3) + " - "+ endT.substring(0, endT.length()-3);
                session = new Session(rs.getInt("s.ID"), rs.getString("s.Lecturer1"), rs.getString("s.Lecturer2"), rs.getString("s.SubjectCode"), rs.getString("s.Subject"), rs.getString("s.GroupID"), rs.getString("s.Tag"), time, rs.getString("s.sessionID"));
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
        selectSession.setCellValueFactory(new PropertyValueFactory("select"));
        sessionsTable.setItems(list);       
    }
    
    
    ObservableList<Session> selectedList = FXCollections.observableArrayList();                                                    // create another arraylist to add all selected sessions by checking the checkbox
       

    //setup a mouseevent action to a table so by clicking any table coulmn of sessions details the checkbox field of that session will be checked. So the user can add those sessions by clicking add session button.
    @FXML
    private void handleMouseAction(MouseEvent event) {
               Session session= (Session) sessionsTable.getSelectionModel().getSelectedItem();    
               session.getSelect().setSelected(true);
               selectedList.add(session);         
    }
    
    // by checking the radio button of the relavant session type then check the sessions that user wants to add as either consecutive, parallel or non overalapping session by clicking add session button. Then those sessions will be added to the relevant tables acording to the session type.
    public void addSession()
    {
      AlertBox ab = new AlertBox(); 
      String title = "Success";
      String message ="";
        for ( Session session : selectedList )
        { 
            
         String  lecturer1 = session.getLecturer1();
         String  lecturer2 = session.getLecturer2();
         String  subjectCode = session.getSubjectCode();
         String  subject = session.getSubject();
         String  grpID = session.getGroupID();
         String  tag = session.getTag();
         String  duration = session.getDuration();
         String  sessionID = session.getSessionID();
         sessionId= sessionID;
           
            if ( !isSessionAlreadyAdded())
               {
                  if ( sessions.getSelectedToggle().equals(consecSession))
                  {
                     query = "INSERT INTO consecutive (Lecturer1, Lecturer2, SubjectCode, Subject, GroupID, Tag, Duration, sessionID) values ('"+ lecturer1+"' , '"+ lecturer2+"' , '"+ subjectCode+"' , '"+ subject+"', '"+ grpID+"', '"+ tag+"', '"+ duration+"', '"+ sessionID+"' )";
                                           message= "Consecutive sessions has been added";

                  }
                    else if ( sessions.getSelectedToggle().equals(parallelSession))
                  {
                     query = "INSERT INTO parallel (Lecturer1, Lecturer2, SubjectCode, Subject, GroupID, Tag, Duration, sessionID) values ('"+ lecturer1+"' , '"+ lecturer2+"' , '"+ subjectCode+"' , '"+ subject+"', '"+ grpID+"', '"+ tag+"', '"+ duration+"', '"+ sessionID+"' )";
                                           message= "Parallel sessions has been added";

                  }
                   else if ( sessions.getSelectedToggle().equals(noOverlapSession))
                  {
                     query = "INSERT INTO notoverlapping (Lecturer1, Lecturer2, SubjectCode, Subject, GroupID, Tag, Duration, sessionID) values ('"+ lecturer1+"' , '"+ lecturer2+"' , '"+ subjectCode+"' , '"+ subject+"', '"+ grpID+"', '"+ tag+"', '"+ duration+"', '"+ sessionID+"' )";
                                           message= "Non overlapping sessions has been added";
                  }                
                     
         if ( session.getSelect().isSelected() )
        {

         try {
            preState= con.prepareStatement(query);
            preState.execute();
            
                       session.getSelect().setSelected(false);

        } 
         catch (SQLException ex) {
            Logger.getLogger(AddStudentGroupsController.class.getName()).log(Level.SEVERE, null, ex);
            
                }

        }
        }          
        }
        if (!message.isEmpty())
         ab.displayInfo(title, message);
         
          else if ( isSessionAlreadyAdded())
                ab.displayError("Error!", "Selected session/sessions already added!");
        else if ( message.isEmpty())
        {
            ab.displayWarning("Warning", "Please first check a session to add");
        }
    }
    
    //checking that the sessions are already added as any of a session type in the database before adding it.
    public boolean isSessionAlreadyAdded()
    { 
         if ( sessions.getSelectedToggle().equals(consecSession))
                  {
                         query = "SELECT COUNT(*) AS recordCount FROM consecutive WHERE sessionID = '"+ sessionId +"' ";
                         query2 = "SELECT COUNT(*) AS recordCount FROM parallel WHERE sessionID = '"+ sessionId +"' ";
                         query3 = "SELECT COUNT(*) AS recordCount FROM notoverlapping WHERE sessionID = '"+ sessionId +"' ";
                  }
         else if ( sessions.getSelectedToggle().equals(parallelSession))
                  {                    
                         query = "SELECT COUNT(*) AS recordCount FROM consecutive WHERE sessionID = '"+ sessionId +"' ";
                         query2 = "SELECT COUNT(*) AS recordCount FROM parallel WHERE sessionID = '"+ sessionId +"' ";
                         query3 = "SELECT COUNT(*) AS recordCount FROM notoverlapping WHERE sessionID = '"+ sessionId +"' ";                  }
         else if ( sessions.getSelectedToggle().equals(noOverlapSession))
                  {
                         query = "SELECT COUNT(*) AS recordCount FROM consecutive WHERE sessionID = '"+ sessionId +"' ";
                         query2 = "SELECT COUNT(*) AS recordCount FROM parallel WHERE sessionID = '"+ sessionId +"' ";
                         query3 = "SELECT COUNT(*) AS recordCount FROM notoverlapping WHERE sessionID = '"+ sessionId +"' ";                  }                             
         try {

            preState= con.prepareStatement(query);
            preState2= con.prepareStatement(query2);
            preState3= con.prepareStatement(query3);

            rs = preState.executeQuery();            
            rs2 = preState2.executeQuery();            
            rs3 = preState3.executeQuery();            

                if(rs.next() && rs2.next() && rs3.next())
                {
                    if ( rs.getInt(1) + rs2.getInt(1) + rs3.getInt(1) == 0 )                
                        return false;
                } 
         }
         catch (SQLException ex) {
            Logger.getLogger(AddStudentGroupsController.class.getName()).log(Level.SEVERE, null, ex);
            }
                 return true;     
    }
    @FXML
    private void switchScene(MouseEvent event) throws IOException {
          Parent pane = FXMLLoader.load(getClass().getResource("viewCategorizedSessions.fxml"));
   

        Scene scene = new Scene(pane);
         //     rootPane.getChildren().setAll(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }
    
    @FXML
    private void setToolTip()
        {
            Tooltip.install(switchIcon, new Tooltip("View categorized sessions"));

        }

    @FXML
    private void exitScene(MouseEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("home.fxml"));
   

        Scene scene = new Scene(pane);
         //     rootPane.getChildren().setAll(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
}

}
  