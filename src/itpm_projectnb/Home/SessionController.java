/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class SessionController implements Initializable {

    @FXML
    private ComboBox ddLecturers;
    @FXML
    private ComboBox ddAdditional;
    @FXML
    private Label lblLecture;
    @FXML
    private ComboBox ddLecturers1;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnClear;

    ObservableList lecurer;
    @FXML
    private Button btnGenerate;
    @FXML
    private ComboBox ddAdditional1;
    @FXML
    private ComboBox ddTags;
    @FXML
    private ComboBox ddGroup;

    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private ComboBox ddCode;
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtSessionID;
    @FXML
    private ComboBox ddSubGroup;
    @FXML
    private Button btnmanage;

    Connection conn = DbConnect.connectDB();
    PreparedStatement pst;
    /**
     * Initializes the controller class.
     */
   /* public Connection connect() {
        Connection con;
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
            return con;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "can not connect");
            return null;
        }

    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        retrieveLecturer1();
        retrieveLecturer2();
        retrieveAdditionalLecturer1();
        retrieveAdditionalLecturer2();
        retrieveTags();
        retrieveGroups();
        retrieveSubGroups();
        /*retrieveSubjectCode();*/

        retrieveSubjectCode();
        lecturers();
        
        
        

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnGenerate) {
            generateSessionID();
        } else if (event.getSource() == btnSubmit) {
            if (validateFields() && validateNoOfStudents() && validateDuration()) {
                insert();
                try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sessionManage.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Added failed");
                alert.showAndWait();
            }
        }else if(event.getSource() == btnClear){
            clear();
        }else if (event.getSource() == btnmanage) {
            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sessionManage.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

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
       // Connection con = connect();
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
        //Connection con = connect();

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
       // Connection con = connect();

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
        //Connection con = connect();
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

    @FXML
    public void lecturers() {
        try {
            String lecturer1 = ddLecturers.getValue().toString();
            String lecturer2 = ddLecturers1.getValue().toString();
            String lecturer3 = ddAdditional.getValue().toString();
            String lecturer4 = ddAdditional1.getValue().toString();

            lblLecture.setText(lecturer1 + "," + lecturer2 + "," + lecturer3 + "," + lecturer4);

        } catch (Exception e) {
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

    public void insert() {
        //Connection con = connect();
        try {

            Statement stat = conn.createStatement();
            
                String query = "INSERT INTO sessions(Lecturer1,Lecturer2,AdditionalLecturer1,AdditionalLecturer2,Tag,GroupID,SubGroupID,SubjectCode,Subject,NoOfStudents,Duration,sessionID) values('" + ddLecturers.getValue() + "','" + ddLecturers1.getValue() + "','" + ddAdditional.getValue() + "','" + ddAdditional1.getValue() + "','" + ddTags.getValue() + "','" + ddGroup.getValue()+"','"+ddSubGroup.getValue() + "','" + ddCode.getValue() + "','" + txtSubject.getText() + "','" + txtNumber.getText() + "'," + txtDuration.getText() + ",'" + txtSessionID.getText() + "')";
                excecuteQuery(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Successfully Added");
                alert.showAndWait();
                clear();
                
            
            //loadStage("LControll.fxml");
        } catch (SQLException ex) {
            Logger.getLogger(LecturerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    @FXML
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

    }

}
