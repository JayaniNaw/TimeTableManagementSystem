/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import helpers.DbConnect;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.event.MouseEvent;
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
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class LecturerController implements Initializable {

    @FXML
    private ComboBox ddDepartment;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtEmpName;
    @FXML
    private TextField txtEmpID;
    @FXML
    private ComboBox ddFaculty;
    @FXML
    private CheckBox CSaturday;
    @FXML
    private CheckBox cSunday;
    @FXML
    private JFXTimePicker time;
    @FXML
    private JFXTimePicker time1;
    @FXML
    private ComboBox ddCenter;
    @FXML
    private ComboBox ddBuilding;
    @FXML
    private ComboBox ddLevel;
    @FXML
    private TextField txtRank;
    @FXML
    private HBox days;
    @FXML
    private CheckBox cMonday;
    @FXML
    private CheckBox cTuesday;
    @FXML
    private CheckBox cWednesday;
    @FXML
    private CheckBox cThursday;
    @FXML
    private CheckBox cFriday;
    @FXML
    private Button btnRank;

    Connection conn = DbConnect.connectDB();
    PreparedStatement pst;
    

    /**
     * Initializes the controller class.
     */
    ObservableList<String> MainFaculty = FXCollections.observableArrayList("Computing", "Buisness", "Engineering", "Architecture", "Humanities And Sciences");
    ObservableList<String> ComputingList = FXCollections.observableArrayList("IT", "DS", "CSNE", "SE");
    ObservableList<String> BuisnessList = FXCollections.observableArrayList("BM", "BA", "HRM");
    ObservableList<String> EngineeringList = FXCollections.observableArrayList("Civil", "Electrical", "Mechanical");
    ObservableList<String> ArchitectureList = FXCollections.observableArrayList("Arciture", "QS");
    ObservableList<String> HumanityList = FXCollections.observableArrayList("Pgysical", "Biology", "English");
    ObservableList<String> CenterList = FXCollections.observableArrayList("Kandy", "Malabe", "Jaffna", "Kurunegala", "Matara");
    ObservableList<String> KandyList = FXCollections.observableArrayList("K1_A Block", "K1_B Block", "K1_C Block", "K1_D Block", "K1_E Block");
    ObservableList<String> MalabeList = FXCollections.observableArrayList("M1_A Block", "M1_B Block", "M1_C Block", "M1_D Block", "M1_E Block");
    ObservableList<String> JaffnaList = FXCollections.observableArrayList("J_A Block", "J_B Block", "J_C Block", "J_D Block", "J_E Block");
    ObservableList<String> KurunegalaList = FXCollections.observableArrayList("K2_A Block", "K2_B Block", "K2_C Block", "K2_D Block", "K2_E Block");
    ObservableList<String> MataraList = FXCollections.observableArrayList("M2_A Block", "M2_B Block", "M2_C Block", "M2_D Block", "M2_E Block");
    ObservableList<String> LevelList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");

    final ObservableList<String> checkBoxList = FXCollections.observableArrayList("Tuesday", "Wednesday", "Saturday");

    @FXML
    private Label label;
    @FXML
    private Button btnDesc;

    String days1 = "";
    @FXML
    private Button btnManage;

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
        //ddFaculty.setValue("Computing");
        ddFaculty.setItems(MainFaculty);

        //ddDepartment.setValue("IT");
        ddDepartment.setItems(ComputingList);

        ddCenter.setItems(CenterList);

        //ddBuilding.setValue("K1_A Block");
        ddBuilding.setItems(KandyList);

        ddLevel.setItems(LevelList);

        /* cMonday = new CheckBox("Monday");
                      
                        cMonday.setOnAction(e ->{
                            checkBoxList.add(cMonday.getText());
                            
                         });
                        cTuesday = new CheckBox("Tuesday");
                        cTuesday.setOnAction(e ->{
                            checkBoxList.add(cTuesday.getText());
                         });
                        cWednesday = new CheckBox("Wednesday");
                        cWednesday.setOnAction(e ->{
                            checkBoxList.add(cWednesday.getText());
                         });
                        cThursday = new CheckBox("Thursday");
                        cThursday.setOnAction(e ->{
                            checkBoxList.add(cThursday.getText());
                         });
                        cFriday = new CheckBox("Friday");
                        cFriday.setOnAction(e ->{
                            checkBoxList.add(cFriday.getText());
                         });
                        CSaturday = new CheckBox("Saturday");
                        CSaturday.setOnAction(e ->{
                            checkBoxList.add(CSaturday.getText());
                         });
                        cSunday = new CheckBox("Sunday");
                        cSunday.setOnAction(e ->{
                            checkBoxList.add(cSunday.getText());
                         });*/
 /* cMonday = new CheckBox("Monday");  */
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnSave) {

            /**/
           // Connection con = connect();
            try {

                Statement stat = conn.createStatement();
                String selectQuery = "SELECT * From lecturers where empID = '" + txtEmpID.getText() + "'";

                ResultSet rs = stat.executeQuery(selectQuery);

                if (rs.next() == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Already Added" + " " + txtEmpID.getText());
                    alert.showAndWait();

                } else {

                    /**/
                    if (validateFields() && validateName() && validateNumber()) {
                        /*cMonday = new CheckBox("Monday");
                        cMonday.setOnAction(e ->{
                            checkBoxList.add(cMonday.getText());
                         });
                        cTuesday = new CheckBox("Tuesday");
                        cTuesday.setOnAction(e ->{
                            checkBoxList.add(cTuesday.getText());
                         });
                        cWednesday = new CheckBox("Wednesday");
                        cWednesday.setOnAction(e ->{
                            checkBoxList.add(cWednesday.getText());
                         });
                        cThursday = new CheckBox("Thursday");
                        cThursday.setOnAction(e ->{
                            checkBoxList.add(cThursday.getText());
                         });
                        cFriday = new CheckBox("Friday");
                        cFriday.setOnAction(e ->{
                            checkBoxList.add(cFriday.getText());
                         });
                        CSaturday = new CheckBox("Saturday");
                        CSaturday.setOnAction(e ->{
                            checkBoxList.add(CSaturday.getText());
                         });
                        cSunday = new CheckBox("Sunday");
                        cSunday.setOnAction(e ->{
                            checkBoxList.add(cSunday.getText());
                         });*/

                        insert();

                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LControll.fxml")));
                        stage.setScene(scene);
                        stage.show();

                    } else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setContentText("register failed");
                        alert.showAndWait();

                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(LecturerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LecturerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (event.getSource() == btnDesc) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Professor - 1" + "\n" + "Assistant Professor - 2" + "\n" + "Senior Lecturer(HG) - 3" + "\n" + "Senior Lecturer - 4" + "\n" + "Lecturer - 5" + "\n" + "Assistant Lecturer - 6");
            alert.showAndWait();
        } else if (event.getSource() == btnClear) {
            clear();

        } else if (event.getSource() == btnManage) {
            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LControll.fxml")));
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

    private boolean validateName() {
        Pattern p = Pattern.compile("^[a-z A-Z0-9.-]*$");
        //Pattern p = Pattern.compile("^[a-zA-Z\\s]*$");
        Matcher m = p.matcher(txtEmpName.getText());
        if (m.find() && m.group().equals(txtEmpName.getText())) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter valid name");
            alert.showAndWait();

            return false;
        }

    }

    private boolean validateNumber() {
        Pattern p = Pattern.compile("[0-9]{6}");
        Matcher m = p.matcher(txtEmpID.getText());
        if (m.find() && m.group().equals(txtEmpID.getText())) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Employee ID should be 6 digits ");
            alert.showAndWait();

            return false;
        }

    }

    @FXML
    public void computingChoice() {
        if (ddFaculty.getValue().equals("Computing")) {
            ddDepartment.setValue("IT");
            ddDepartment.setItems(ComputingList);
        } else if (ddFaculty.getValue().equals("Engineering")) {
            ddDepartment.setValue("Civil");
            ddDepartment.setItems(EngineeringList);
        } else if (ddFaculty.getValue().equals("Buisness")) {
            ddDepartment.setValue("BM");
            ddDepartment.setItems(BuisnessList);
        } else if (ddFaculty.getValue().equals("Architecture")) {
            ddDepartment.setValue("Architecture");
            ddDepartment.setItems(ArchitectureList);
        } else {
            ddDepartment.setValue("Physical");
            ddDepartment.setItems(HumanityList);
        }

    }

    @FXML
    public void buildingChoice() {
        if (ddCenter.getValue().equals("Kandy")) {
            ddBuilding.setValue("K1_A Block");
            ddBuilding.setItems(KandyList);
        } else if (ddCenter.getValue().equals("Malabe")) {
            ddBuilding.setValue("M1_A Block");
            ddBuilding.setItems(MalabeList);
        } else if (ddCenter.getValue().equals("Jaffna")) {
            ddBuilding.setValue("J_A Block");
            ddBuilding.setItems(JaffnaList);
        } else if (ddCenter.getValue().equals("Kurunegala")) {
            ddBuilding.setValue("K2_A Block");
            ddBuilding.setItems(KurunegalaList);
        } else {
            ddBuilding.setValue("M2_A Block");
            ddBuilding.setItems(MataraList);
        }
    }

    @FXML
    public void generateRank(ActionEvent event) {
        String empID = txtEmpID.getText();
        String level = (String) ddLevel.getValue();

        if (empID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill employee ID!");
            alert.showAndWait();
        }
        if (ddLevel.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill Level field");
            alert.showAndWait();
        } else {
            txtRank.setText(level + "." + empID);
            label.setVisible(false);
        }
    }

    public boolean validateFields() {
        String faculty = (String) ddFaculty.getValue();
        /*String department = (String)ddDepartment.getValue();
        String center = (String)ddCenter.getValue();
        String building = (String)ddBuilding.getValue();*/

        if (txtEmpName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter employee name!");
            alert.showAndWait();
            return false;
        } else if (ddFaculty.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose faculty!");
            alert.showAndWait();
            return false;
        } else if (ddDepartment.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose department!");
            alert.showAndWait();
            return false;
        } else if (!(cMonday.isSelected() | cWednesday.isSelected() | CSaturday.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please check your free days");
            alert.showAndWait();
            return false;

        } else if (time.getEditor().getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter time fields!");
            alert.showAndWait();
            return false;
        } else if (time1.getEditor().getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter time fields!");
            alert.showAndWait();
            return false;
        } else if (ddCenter.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose center!");
            alert.showAndWait();
            return false;
        } else if (ddBuilding.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please choose building!");
            alert.showAndWait();
            return false;
        } else if (txtRank.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please click on generate button!");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    public void insert() {
        //Connection con = connect();
        try {

            Statement stat = conn.createStatement();
            // String selectQuery = "SELECT * From lecturers where empID = '" + txtEmpID.getText() + "'";

            // ResultSet rs = stat.executeQuery(selectQuery);

            /*if (rs.next() == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Already Added" + " " + txtEmpID.getText());
                alert.showAndWait();

            } else {*/

 /**/
            if (validateFields() && validateName() && validateNumber()) {
                /*cMonday = new CheckBox("Monday");
                        cMonday.setOnAction(e ->{
                            checkBoxList.add(cMonday.getText());
                         });
                        cTuesday = new CheckBox("Tuesday");
                        cTuesday.setOnAction(e ->{
                            checkBoxList.add(cTuesday.getText());
                         });
                        cWednesday = new CheckBox("Wednesday");
                        cWednesday.setOnAction(e ->{
                            checkBoxList.add(cWednesday.getText());
                         });
                        cThursday = new CheckBox("Thursday");
                        cThursday.setOnAction(e ->{
                            checkBoxList.add(cThursday.getText());
                         });
                        cFriday = new CheckBox("Friday");
                        cFriday.setOnAction(e ->{
                            checkBoxList.add(cFriday.getText());
                         });
                        CSaturday = new CheckBox("Saturday");
                        CSaturday.setOnAction(e ->{
                            checkBoxList.add(CSaturday.getText());
                         });
                        cSunday = new CheckBox("Sunday");
                        cSunday.setOnAction(e ->{
                            checkBoxList.add(cSunday.getText());
                         });*/

                if (cMonday.isSelected()) {

                    days1 += "Monday" + " ";

                }

                if (cTuesday.isSelected()) {
                    days1 += "Tuesday" + " ";
                }
                if (cWednesday.isSelected()) {
                    days1 += "Wednesday" + " ";
                }
                if (cThursday.isSelected()) {
                    days1 += "Thursday" + " ";
                }
                if (cFriday.isSelected()) {
                    days1 += "Friday" + " ";
                }
                if (CSaturday.isSelected()) {
                    days1 += "Saturday" + " ";
                }
                if (cSunday.isSelected()) {
                    days1 += "Sunday" + " ";
                }

                String query = "INSERT INTO lecturers(name,empID,faculty,department,activeDays,activeHours1,activeHours2,center,building,level,rank) values('" + txtEmpName.getText() + "','" + txtEmpID.getText() + "','" + ddFaculty.getValue() + "','" + ddDepartment.getValue() + "','" + days1 + "','" + time.getEditor().getText() + "','" + time1.getEditor().getText() + "','" + ddCenter.getValue() + "','" + ddBuilding.getValue() + "'," + ddLevel.getValue() + ",'" + txtRank.getText() + "')";
                excecuteQuery(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Successfully Added");
                alert.showAndWait();
                clear();

                //loadStage("LControll.fxml");
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("register failed");
                alert.showAndWait();

            }

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

    public void clear() {
        txtEmpName.setText("");
        txtEmpID.setText("");
        ddFaculty.setValue("");
        ddDepartment.setValue("");
        ddCenter.setValue("");
        ddBuilding.setValue("");
        ddLevel.setValue("");
        txtRank.setText("");
        time.getEditor().setText("");
        time1.getEditor().setText("");
        cMonday.setSelected(false);
        cTuesday.setSelected(false);
        cWednesday.setSelected(false);
        cThursday.setSelected(false);
        cFriday.setSelected(false);
        CSaturday.setSelected(false);
        cSunday.setSelected(false);
        checkBoxList.clear();

    }

}
