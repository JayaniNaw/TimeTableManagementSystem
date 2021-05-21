/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import helpers.DbConnect;
import helpers.DbConnect;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class SessionManageController implements Initializable {

    @FXML
    private TableView<sessions> tv;
    @FXML
    private TableColumn<sessions, Integer> colID;
    @FXML
    private TableColumn<sessions, String> colLecturer1;
    @FXML
    private TableColumn<sessions, String> colLecturer2;
    @FXML
    private TableColumn<sessions, String> colAdditional1;
    @FXML
    private TableColumn<sessions, String> colAdditional2;
    @FXML
    private TableColumn<sessions, String> colTag;
    @FXML
    private TableColumn<sessions, String> colGroupID;
    @FXML
    private TableColumn<sessions, String> colSubGroup;
    @FXML
    private TableColumn<sessions, String> colSubject;
    @FXML
    private TableColumn<sessions, String> colNumber;
    @FXML
    private TableColumn<sessions, String> colDuration;
    @FXML
    private TableColumn<sessions, String> colSessionID;
    @FXML
    private TableColumn<sessions, String> colCode;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnAdd;
    @FXML
    private ComboBox ddLecturers1;
    @FXML
    private ComboBox ddAdditional1;
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtSessionID;
    @FXML
    private ComboBox ddLecturers;
    @FXML
    private ComboBox ddAdditional;
    @FXML
    private ComboBox ddTags;
    @FXML
    private ComboBox ddGroup;
    @FXML
    private ComboBox ddSubGroup;
    @FXML
    private ComboBox ddCode;
    @FXML
    private Button btnGenerate;
    @FXML
    private Label txtID;
    
    ObservableList<String> columnDetails = FXCollections.observableArrayList("ID", "Lecturer1", "Lecturer2", "AdditionalLecturer1", "AdditionalLecturer2", "Tag","GroupID","SubGroupID","SubjectCode","Subject","NoOfStudents","Duration","sessionID");

    Connection conn = DbConnect.connectDB();
    PreparedStatement pst;
    
    //ObservableList<sessions>sessionList=FXCollections.observableArrayList();
    private TextField search;
    sessions session;
    private TextField txtSearch;
    @FXML
    private ComboBox ddSearch;
    @FXML
    private ComboBox ddSearch1;
    /**
     * Initializes the controller class.
     */
    /*public Connection connect() {
        Connection con;
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
            return con;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "can not connect");
            return null;
        }

    }*/
    @FXML
    public void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnGenerate) {
            generateSessionID();
        }else if(event.getSource() == btnAdd){
            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sessions.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }if (event.getSource() == btnUpdate) {
            update();
            
        } else if (event.getSource() == btnDelete) {
            delete();

        }else if(event.getSource()==btnClear){
            clear();
        }

     }
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showSessions();
        retrieveLecturer1();
        retrieveLecturer2();
        retrieveAdditionalLecturer1();
        retrieveAdditionalLecturer2();
        retrieveTags();
        retrieveGroups();
        retrieveSubGroups();
        /*retrieveSubjectCode();*/

       // retrieveSubjectCode();
      //search();
       ddSearch1.setItems(columnDetails);
        
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
    
    public ObservableList<sessions> getSessionsList() {
        ObservableList<sessions> sessionsList = FXCollections.observableArrayList();
        //Connection con = connect();

        String query = "select * from sessions";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            sessions session;

            while (rs.next()) {
                session = new sessions(rs.getInt("ID"), rs.getString("Lecturer1"), rs.getString("Lecturer2"), rs.getString("AdditionalLecturer1"), rs.getString("AdditionalLecturer2"), rs.getString("Tag"), rs.getString("GroupID"), rs.getString("SubGroupID"), rs.getString("SubjectCode"), rs.getString("Subject"), rs.getString("NoOfStudents"), rs.getString("Duration"),rs.getString("sessionID"));
                sessionsList.add(session);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sessionsList;

    }
    
     public void showSessions() {
        ObservableList<sessions> list = getSessionsList();

        colID.setCellValueFactory(new PropertyValueFactory<sessions, Integer>("ID"));
        colLecturer1.setCellValueFactory(new PropertyValueFactory<sessions, String>("Lecturer1"));
        colLecturer2.setCellValueFactory(new PropertyValueFactory<sessions, String>("Lecturer2"));
        colAdditional1.setCellValueFactory(new PropertyValueFactory<sessions, String>("AdditionalLecturer1"));
        colAdditional2.setCellValueFactory(new PropertyValueFactory<sessions, String>("AdditionalLecturer2"));
        colTag.setCellValueFactory(new PropertyValueFactory<sessions, String>("Tag"));
        colGroupID.setCellValueFactory(new PropertyValueFactory<sessions, String>("GroupID"));
        colSubGroup.setCellValueFactory(new PropertyValueFactory<sessions, String>("SubGroupID"));
        colCode.setCellValueFactory(new PropertyValueFactory<sessions, String>("SubjectCode"));
        colSubject.setCellValueFactory(new PropertyValueFactory<sessions, String>("Subject"));
        colNumber.setCellValueFactory(new PropertyValueFactory<sessions, String>("NoOfStudents"));
        colDuration.setCellValueFactory(new PropertyValueFactory<sessions, String>("Duration"));
        colSessionID.setCellValueFactory(new PropertyValueFactory<sessions, String>("sessionID"));
        

        tv.setItems(list);
    }

    @FXML
    public void retrieveLecturer1() {
        //Connection con = connect();
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            String query = "select  name from lecturers";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            ddLecturers.setItems(list);

        } catch (Exception e) {

        }

    }
    
    @FXML
    public void retrieveLecturer2() {
        //Connection con = connect();
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            String query = "select  name from lecturers";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            ddLecturers1.setItems(list);

        } catch (Exception e) {

        }

    }

    @FXML
    public void retrieveAdditionalLecturer1() {
        //Connection con = connect();
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            String query = "select  name from lecturers";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            ddAdditional.setItems(list);

        } catch (Exception e) {
            retrieveAdditionalLecturer2();

        }

    }

    @FXML
    public void retrieveAdditionalLecturer2() {
        //Connection con = connect();
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            String query = "select  name from lecturers";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            ddAdditional1.setItems(list);

        } catch (Exception e) {

        }

    }

    @FXML
    public void retrieveTags() {
       // Connection con = connect();

        try {

            ObservableList<String> list = FXCollections.observableArrayList();
            //we will get all names from lectuters table
            String query = "select TagName from tags";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            //now we get all names and add into combobox
            while (rs.next()) {
                list.add(rs.getString("TagName"));
            }
            ddTags.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void retrieveGroups() {
        //Connection con = connect();

        try {

            ObservableList<String> list = FXCollections.observableArrayList();

            //we will get all names from lectuters table
            String query = "select GroupID from studentgroups";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            //now we get all names and add into combobox
            list.add("null");
            while (rs.next()) {
                list.add(rs.getString("GroupID"));

            }
            ddGroup.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void retrieveSubGroups() {
        //Connection con = connect();

        try {

            ObservableList<String> list = FXCollections.observableArrayList();
            //we will get all names from lectuters table
            String query = "select SubGroupID from studentgroups";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            //now we get all names and add into combobox
            list.add("null");
            while (rs.next()) {
                list.add(rs.getString("SubGroupID"));
            }
            ddSubGroup.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void retrieveSubjectCode() {
        //Connection con = connect();

        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            //we will get all names from lectuters table
            String query = "select code from subjects";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            //now we get all names and add into combobox
            while (rs.next()) {
                list.add(rs.getString("code"));
            }
            ddCode.setItems(list);
        } catch (Exception e) {
        }
    }

    @FXML
    public void retrieveSubject() {
       // Connection con = connect();
        String code = ddCode.getValue().toString();
        try {
            String query = "select name from subjects where code='" + code + "'";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            //now we get all names and add into combobox
            while (rs.next()) {
                txtSubject.setText(rs.getString("name"));
            }

        } catch (Exception e) {
        }
    }

    public void generateSessionID() {
        String LecturerName = ddLecturers.getValue().toString();
        String subjectCode = ddCode.getValue().toString();
        String subjectName = txtSubject.getText();
        String groupID = ddGroup.getValue().toString();
        String SubgroupID = ddSubGroup.getValue().toString();
        String students = txtNumber.getText();
        String duration = txtDuration.getText();

        if (LecturerName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill Lecturer name");
            alert.showAndWait();
        }
        if (subjectCode.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill Subject Code");
            alert.showAndWait();
        }
        if (subjectName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill Subject name");
            alert.showAndWait();

        }
        if (groupID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill group id");
            alert.showAndWait();
        }if(ddGroup.getValue().equals("null")){
            txtSessionID.setText(LecturerName + "-" + subjectCode + "-" + subjectName + "-" + SubgroupID + "-" + students + "-" + duration);
        } else {
            txtSessionID.setText(LecturerName + "-" + subjectCode + "-" + subjectName + "-" + groupID + "-" + students + "-" + duration);
        }

    }

    
    public boolean validateFields() {
        

        if (ddLecturers.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose Lecturer1!");
            alert.showAndWait();
            
            return false;
        } else if (ddLecturers1.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose Lecturer2!");
            alert.showAndWait();
            
            return false;
        } else if (ddAdditional.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose Additional Lecture1!");
            alert.showAndWait();
            return false;
        } else if (ddAdditional1.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose Additional Lecture2");
            alert.showAndWait();
            return false;

        } else if (ddTags.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose tags!");
            alert.showAndWait();
            return false;
        } else if (ddGroup.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose Group ID!");
            alert.showAndWait();
            return false;
        } else if(ddSubGroup.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose null or sub Group ID!");
            alert.showAndWait();
            return false;
        }else if (ddCode.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose subject code!");
            alert.showAndWait();
            return false;
        } else if (txtSubject.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter subject!");
            alert.showAndWait();
            return false;
        } else if (txtNumber.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter number of students!");
            alert.showAndWait();
            return false;
        }else if (txtDuration.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter duration!");
            alert.showAndWait();
            return false;
        }else if (txtSessionID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please click on session ID!");
            alert.showAndWait();
            return false;
        }

        return true;
    }
    
     private boolean validateNoOfStudents() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtNumber.getText());
        if (m.find() && m.group().equals(txtNumber.getText())) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Number of students should be digits ");
            alert.showAndWait();
            
            return false;
        }

    }
     private boolean validateDuration() {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(txtDuration.getText());
        if (m.find() && m.group().equals(txtDuration.getText())) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Duration should be digits ");
            alert.showAndWait();
            

            return false;
        }

    }
     
    @FXML
    public void retrieve() {
        
        tv.setOnMouseClicked(e -> {
            //Connection con = connect();
            try {
                sessions session = (sessions) tv.getSelectionModel().getSelectedItem();

                String query = "SELECT * FROM sessions WHERE ID=?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, session.getID());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    ddLecturers.setValue(rs.getString("Lecturer1"));
                    ddLecturers1.setValue(rs.getString("Lecturer2"));
                    ddAdditional.setValue(rs.getString("AdditionalLecturer1"));
                    ddAdditional1.setValue(rs.getString("AdditionalLecturer2"));
                    ddTags.setValue(rs.getString("Tag"));
                    ddGroup.setValue(rs.getString("GroupID"));
                    ddSubGroup.setValue(rs.getString("SubGroupID"));
                    ddCode.setValue(rs.getString("SubjectCode"));
                    ddSubGroup.setValue(rs.getString("SubGroupID"));
                    txtSubject.setText(rs.getString("Subject"));
                    txtNumber.setText(rs.getString("NoOfStudents"));
                    txtDuration.setText(rs.getString("Duration"));
                    txtSessionID.setText(rs.getString("sessionID"));
                    txtID.setText(rs.getString("ID"));

                    
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LControllController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
        }
                
        );
    }
    
    public void update() {
        btnUpdate.setOnAction(e -> {
            try {
                if (validateFields() && validateNoOfStudents() && validateDuration()) {
                    

                    String query = "UPDATE sessions SET Lecturer1='" + ddLecturers.getValue() + "',Lecturer2='" + ddLecturers1.getValue() + "',AdditionalLecturer1='" + ddAdditional.getValue() + "',AdditionalLecturer2='" + ddAdditional1.getValue() + "',Tag='" + ddTags.getValue() + "',GroupID='" + ddGroup.getValue() + "',SubGroupID='" + ddSubGroup.getValue() + "',SubjectCode='" + ddCode.getValue() + "',Subject='" + txtSubject.getText() + "',NoOfStudents=" + txtNumber.getText() + ",Duration='" + txtDuration.getText() +"',sessionID='" + txtSessionID.getText()+ "'where ID='" + txtID.getText() + "'";
                    excecuteQuery(query);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Updated successfully");
                    alert.showAndWait();
                    showSessionsGivenID();
                    clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Updated failed");
                    alert.showAndWait();
                }

            } catch (Exception ex) {

            }

        });

    }
    
    public void delete() {

        String query = "delete from sessions where ID = '" + txtID.getText() + "'";
        excecuteQuery(query);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Deleted successfully");
        alert.showAndWait();
        showSessions();
        clear();

    }

    private void excecuteQuery(String query) {
        //Connection con = connect();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
   
   
    
     public void clear() {
        txtNumber.setText("");
        txtDuration.setText("");
        ddLecturers.setValue("");
        ddLecturers1.setValue("");
        ddAdditional.setValue("");
        ddAdditional1.setValue("");
        ddCode.setValue("");
        txtSubject.setText("");
        txtDuration.setText("");
        txtSessionID.setText("");
        ddTags.setValue("");
        ddGroup.setValue("");
        ddSubGroup.setValue("");
        txtID.setText("");

    }
     
     public ObservableList<sessions> getSessionsListForGivenID() {
         
        ObservableList<sessions> sessionsList = FXCollections.observableArrayList();
       // Connection con = connect();

        String query = "select * from sessions where Lecturer1 = '"+ddSearch.getValue()+"' or ID='"+ddSearch.getValue()+"' or Lecturer2 = '"+ddSearch.getValue()+"' or AdditionalLecturer1='"+ddSearch.getValue()+"' or AdditionalLecturer2 ='"+ddSearch.getValue()+"' or Tag= '"+ddSearch.getValue()+"' or GroupID='"+ddSearch.getValue()+"' or SubGroupID ='"+ddSearch.getValue()+"' or SubjectCode='"+ddSearch.getValue()+"' or Subject='"+ddSearch.getValue()+"' or NoOfStudents='"+ddSearch.getValue()+"' or Duration='"+ddSearch.getValue()+"' or sessionID='"+ddSearch.getValue()+"' ";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            sessions session;

            while (rs.next()) {
                session = new sessions(rs.getInt("ID"), rs.getString("Lecturer1"), rs.getString("Lecturer2"), rs.getString("AdditionalLecturer1"), rs.getString("AdditionalLecturer2"), rs.getString("Tag"), rs.getString("GroupID"), rs.getString("SubGroupID"), rs.getString("SubjectCode"), rs.getString("Subject"), rs.getString("NoOfStudents"), rs.getString("Duration"),rs.getString("sessionID"));
                sessionsList.add(session);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sessionsList;

    }
     
    @FXML
      public void showSessionsGivenID() {
        ObservableList<sessions> list = getSessionsListForGivenID();
        ObservableList<sessions> list1 = getSessionsList();

        colID.setCellValueFactory(new PropertyValueFactory<sessions, Integer>("ID"));
        colLecturer1.setCellValueFactory(new PropertyValueFactory<sessions, String>("Lecturer1"));
        colLecturer2.setCellValueFactory(new PropertyValueFactory<sessions, String>("Lecturer2"));
        colAdditional1.setCellValueFactory(new PropertyValueFactory<sessions, String>("AdditionalLecturer1"));
        colAdditional2.setCellValueFactory(new PropertyValueFactory<sessions, String>("AdditionalLecturer2"));
        colTag.setCellValueFactory(new PropertyValueFactory<sessions, String>("Tag"));
        colGroupID.setCellValueFactory(new PropertyValueFactory<sessions, String>("GroupID"));
        colSubGroup.setCellValueFactory(new PropertyValueFactory<sessions, String>("SubGroupID"));
        colCode.setCellValueFactory(new PropertyValueFactory<sessions, String>("SubjectCode"));
        colSubject.setCellValueFactory(new PropertyValueFactory<sessions, String>("Subject"));
        colNumber.setCellValueFactory(new PropertyValueFactory<sessions, String>("NoOfStudents"));
        colDuration.setCellValueFactory(new PropertyValueFactory<sessions, String>("Duration"));
        colSessionID.setCellValueFactory(new PropertyValueFactory<sessions, String>("sessionID"));
        

        tv.setItems(list);
        
         
        
    }
      public void retrieveDetails() {
          ObservableList<sessions> list1 = getSessionsList();
        if (ddSearch1.getValue().equals("ID")) {
            tv.setItems(list1);
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct  ID from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("ID"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }  
        else if (ddSearch1.getValue().equals("Lecturer1")) {
            
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = " select  distinct Lecturer1 from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("Lecturer1"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("Lecturer2")) {
            
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select  distinct Lecturer2 from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("Lecturer2"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("AdditionalLecturer1")) {
           
           // Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select  distinct AdditionalLecturer1 from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("AdditionalLecturer1"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("AdditionalLecturer2")) {
            tv.setItems(list1);
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select  distinct AdditionalLecturer2 from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("AdditionalLecturer2"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }
        
        
        
        else if (ddSearch1.getValue().equals("Tag")) {
            
           // Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct  Tag from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("Tag"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("GroupID")) {
            
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct   GroupID from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("GroupID"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("SubGroupID")) {
            tv.setItems(list1);
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct  SubGroupID from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("SubGroupID"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("SubjectCode")) {
            tv.setItems(list1);
           // Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct  SubjectCode from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("SubjectCode"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("Subject")) {
            tv.setItems(list1);
           // Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct  Subject from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("Subject"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("NoOfStudents")) {
            tv.setItems(list1);
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct  NoOfStudents from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("NoOfStudents"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("Duration")) {
            tv.setItems(list1);
           // Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select  distinct Duration from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("Duration"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("sessionID")) {
            tv.setItems(list1);
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select distinct  sessionID from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("sessionID"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }else if (ddSearch1.getValue().equals("GroupID")) {
            
            //Connection con = connect();
            try {
                ObservableList<String> list = FXCollections.observableArrayList();
                String query = "select  distinct GroupID from sessions";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("GroupID"));
                }
                ddSearch.setItems(list);

            } catch (Exception e) {

            }
        }
        tv.setItems(list1);

    }
     
     
     
    
}
