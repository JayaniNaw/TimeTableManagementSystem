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
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author IMAKA
 */
public class ManageSessionController implements Initializable {

    @FXML
    private Button btnSession;
    @FXML
    private Button btnConsecutiveSession;
    @FXML
    private Button btnNonOverlappingSession;
    @FXML
    private Button btnAddRoom;
    @FXML
    private Button btnRefresh;
    
    @FXML
    private Button btnParallelSession;
    
  
    @FXML
    private TableView<sessionroom> tv11;
    
     @FXML
    private TableView<sessionroom> tv;
    
     
    @FXML
    private TableColumn<sessionroom, String>  colLecture1;
    @FXML
    private TableColumn<sessionroom, String> colLecture2;
    @FXML
    private TableColumn<sessionroom, String> colSubjectName ;
    @FXML
    private TableColumn<sessionroom, String> colGroupID;
    @FXML
    private TableColumn<sessionroom, String> colTag;
    @FXML
    private TableColumn<sessionroom, Integer> colNoOfStudents ;
    @FXML
    private TableColumn<sessionroom, Integer> colDurations;
    @FXML
    private TableColumn<sessionroom, String> colRoom;
   
    
   @FXML
    private TableColumn<sessionroom,String> colSessionIDNew;

    @FXML
    private TableColumn<sessionroom,String> colRoomNew;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showRoom();
        //will implement
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
        
     if(event.getSource() == btnAddRoom){
            loadStage("SessionMain.fxml");
     
     }else if(event.getSource() == btnRefresh){
            refresh();
     
     }else if(event.getSource() == btnSession){
            loadStage("SessionMain.fxml");
            
     }else if(event.getSource() == btnConsecutiveSession){
            loadStage("ManageConsecutiveSessionRoom.fxml");
     
     }else if(event.getSource() == btnNonOverlappingSession){
            loadStage("AddNonOverlappingSessionRoom.fxml");
     
     }
     else if(event.getSource() == btnParallelSession){
            loadStage("AddParallelSessionRoom.fxml");
     
     }
    
}

    private void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public ObservableList<sessionroom> RoomList() {
        ObservableList<sessionroom> list = FXCollections.observableArrayList();
        Connection con = connect();

       //   String query = "select * from sessions";
//       String query = "select * from consecutivesessionroom";
        //String query = "SELECT  conSessionID AS Session, conSessionRoom As Room FROM consecutivesessionroom UNION ALL SELECT   SessionID AS Session, RoomName as Room FROM notoverlappingsessionroom";
        String query = "select sessionID, sessionRoom from allsessionsrooms";

        Statement st;
        ResultSet rs;
        Statement st2;
        ResultSet rs2;
        
//        ArrayList<sessionroom> list = new ArrayList<>();

        try {
            st = con.createStatement();
                                rs2 = st.executeQuery(query);

            sessionroom room;
            sessionroom room2;

            while (rs2.next()) {
                         String query1 = "Select * from sessions where sessionID = '"+rs2.getString("sessionID")+"'";

                    st2 = con.createStatement();
                                rs = st2.executeQuery(query1);

                    while(rs.next()){
                    
                     room2 = new sessionroom(rs.getInt("id"),rs.getString("sessionID"),rs.getString("Lecturer1"), rs.getString("Lecturer2"), rs.getString("Subject"), rs.getString("SubjectCode"), rs.getString("Tag"), rs.getInt("NoOfStudents"),rs.getString("Duration"),rs2.getString("sessionRoom"));
                     list.add(room2);
                    }

               
               room = new sessionroom(1,rs2.getString("sessionID"), rs2.getString("sessionRoom"));
              // room = new sessionroom(rs.getInt("ID"),rs.getString("conSessionID"), rs.getString("conSessionRoom"),rs.getString("SessionID"), rs.getString("RoomName"));
               
//               RoomList.add(room);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
   
    public void showRoom() {
       
        ObservableList<sessionroom> room = RoomList();

        //colSessionIDNew.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("conSessionID"));
        //colRoomNew.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("conSessionRoom"));   
        
        //colSessionIDNew.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("SessionID"));
        colRoomNew.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("RoomName"));   
        
        
        colLecture1.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("Lecturer1"));
        colLecture2.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("Lecturer2"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("Subject"));
        colGroupID.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("SubjectCode"));
        colTag.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("Tag"));
        colNoOfStudents.setCellValueFactory(new PropertyValueFactory<sessionroom, Integer>("NoOfStudents"));
        colDurations.setCellValueFactory(new PropertyValueFactory<sessionroom, Integer>("Duration"));
     
        colRoom.setCellValueFactory(new PropertyValueFactory<sessionroom, String>("sessionRoom"));
        
         tv.setItems(room);
          
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
    
}



