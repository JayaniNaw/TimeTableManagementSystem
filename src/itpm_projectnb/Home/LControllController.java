/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import helpers.DbConnect;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class LControllController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TableView<lecturers> tv;
    @FXML
    private TableColumn<lecturers, Integer> colID;
    @FXML
    private TableColumn<lecturers, String> colName;
    @FXML
    private TableColumn<lecturers, String> colEmpID;
    @FXML
    private TableColumn<lecturers, String> colFaculty;
    @FXML
    private TableColumn<lecturers, String> colDepartment;
    private TableColumn<lecturers, String> colDays;
    @FXML
    private TableColumn<lecturers, String> colTime;
    @FXML
    private TableColumn<lecturers, String> colCenter;
    @FXML
    private TableColumn<lecturers, String> colBuilding;
    @FXML
    private TableColumn<lecturers, Integer> colLevel;
    @FXML
    private TableColumn<lecturers, String> colRank;
    @FXML
    private TableColumn<lecturers, String> colTime1;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmpID;
    @FXML
    private ComboBox ddFaculty;
    @FXML
    private ComboBox ddDepartment;
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
    private CheckBox cSaturday;
    @FXML
    private CheckBox cSunday;
    @FXML
    private ComboBox ddCenter;
    @FXML
    private ComboBox ddBuilding;
    @FXML
    private ComboBox ddLevel;
    @FXML
    private TextField txtRank;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> checkBoxList = FXCollections.observableArrayList("Saturday", "Sunday");
    @FXML
    private JFXTimePicker time;
    @FXML
    private JFXTimePicker time1;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

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
    @FXML
    private Label label;
    @FXML
    private Button btnRank;
    @FXML
    private TableColumn<lecturers, String> colActiveDays;
    
    Connection conn = DbConnect.connectDB();
    PreparedStatement pst;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showLecturers();
        //ddFaculty.setValue("Computing");
        ddFaculty.setItems(MainFaculty);

        //ddDepartment.setValue("IT");
        ddDepartment.setItems(ComputingList);

        ddCenter.setItems(CenterList);

        //ddBuilding.setValue("K1_A Block");
        ddBuilding.setItems(KandyList);

        ddLevel.setItems(LevelList);
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnUpdate) {
            update();
        } else if (event.getSource() == btnDelete) {
            delete();

        } else if (event.getSource() == btnClear) {
            clear();

        } else if (event.getSource() == btnBack) {
            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("lecturer.fxml")));
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

    public ObservableList<lecturers> getLecturersList() {
        ObservableList<lecturers> lecturesList = FXCollections.observableArrayList();
        //Connection con = connect();

        String query = "select * from lecturers";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            lecturers lecture;

            while (rs.next()) {
                lecture = new lecturers(rs.getInt("ID"), rs.getString("name"), rs.getString("empID"), rs.getString("faculty"), rs.getString("department"), rs.getString("activeDays"), rs.getString("activeHours1"), rs.getString("activeHours2"), rs.getString("center"), rs.getString("building"), rs.getInt("level"), rs.getString("rank"));
                lecturesList.add(lecture);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lecturesList;

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

        if (empID.isEmpty() || level.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill employee ID and Level fields");
            alert.showAndWait();
        } else if (empID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please fill employee ID fields");
            alert.showAndWait();
        } else {
            txtRank.setText(level + "." + empID);
            label.setVisible(false);
        }
    }

    public boolean validateFields() {
        /*String faculty = (String) ddFaculty.getValue();
        String department = (String) ddDepartment.getValue();
        String center = (String) ddCenter.getValue();
        String building = (String) ddBuilding.getValue();*/

        if (txtName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter all fields!");
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
        } else if (!(cMonday.isSelected() | cWednesday.isSelected() | cSaturday.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please check your free days");
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
        } else if (txtRank.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please click on generate button!");
            alert.showAndWait();
            return false;
        }

        return true;

    }

    public void showLecturers() {
        ObservableList<lecturers> list = getLecturersList();

        colID.setCellValueFactory(new PropertyValueFactory<lecturers, Integer>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<lecturers, String>("name"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<lecturers, String>("empID"));
        colFaculty.setCellValueFactory(new PropertyValueFactory<lecturers, String>("faculty"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<lecturers, String>("department"));
        colActiveDays.setCellValueFactory(new PropertyValueFactory<lecturers, String>("activeDays"));

        colTime.setCellValueFactory(new PropertyValueFactory<lecturers, String>("activeHours1"));
        colTime1.setCellValueFactory(new PropertyValueFactory<lecturers, String>("activeHours2"));
        colCenter.setCellValueFactory(new PropertyValueFactory<lecturers, String>("center"));
        colBuilding.setCellValueFactory(new PropertyValueFactory<lecturers, String>("building"));
        colLevel.setCellValueFactory(new PropertyValueFactory<lecturers, Integer>("level"));
        colRank.setCellValueFactory(new PropertyValueFactory<lecturers, String>("rank"));

        tv.setItems(list);
    }

    @FXML
    public void retrieve() {
        /* cMonday = new CheckBox("Monday");
                        cMonday.setOnAction(e ->{
                            checkBoxList.add(cMonday.getText());
                         });
                        cTuesday = new CheckBox("Tueday");
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
                        cSaturday = new CheckBox("Saturday");
                        cSaturday.setOnAction(e ->{
                            checkBoxList.add(cSaturday.getText());
                         });
                        cSunday = new CheckBox("Sunday");
                        cSunday.setOnAction(e ->{
                            checkBoxList.add(cSunday.getText());
                         });*/
        tv.setOnMouseClicked(e -> {
            //Connection con = connect();
            try {
                lecturers lect = (lecturers) tv.getSelectionModel().getSelectedItem();

                String query = "SELECT * FROM lecturers WHERE ID=?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, lect.getID());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    txtName.setText(rs.getString("name"));
                    txtEmpID.setText(rs.getString("empID"));
                    ddFaculty.setValue(rs.getString("faculty"));
                    ddDepartment.setValue(rs.getString("department"));

                    time.getEditor().setText(rs.getString("ActiveHours1"));
                    time1.getEditor().setText(rs.getString("ActiveHours2"));
                    ddCenter.setValue(rs.getString("center"));
                    ddBuilding.setValue(rs.getString("building"));
                    ddLevel.setValue(rs.getString("level"));
                    txtRank.setText(rs.getString("rank"));

                    /* if(rs.getString("activeDays") != null){
                        
                        
                        
                        cMonday.setSelected(false);
                        cTuesday.setSelected(false);
                        cWednesday.setSelected(false);
                        cThursday.setSelected(false);
                        cFriday.setSelected(false);
                        cSaturday.setSelected(false);
                        cSunday.setSelected(false);
                        
                        
                        try {
                            System.out.println(rs.getString("activeDays"));
                        } catch (SQLException ex) {
                            Logger.getLogger(LControllController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        String checkBoxString = rs.getString("activeDays").replace("[", "").replace("[", "").replace("[", "");
                        System.out.println(checkBoxString);
                        
                        List<String> daysList = Arrays.asList(checkBoxString.split("\\s*,\\s*"));
                        System.out.println(daysList);
                        
                        for(String days : daysList){
                            switch(days){
                                case "Monday" : cMonday.setSelected(true);
                                                break;
                                case "Tuesday" : cTuesday.setSelected(true);
                                                break; 
                                case "Wednesday" : cWednesday.setSelected(true);
                                                break; 
                                case "Thursday" : cThursday.setSelected(true);
                                                break; 
                                case "Friday" : cFriday.setSelected(true);
                                                break; 
                                case "Saturday" : cSaturday.setSelected(true);
                                                break; 
                                case "Sunday" : cSunday.setSelected(true);
                                                break; 
                                default       :  
                                                   cMonday.setSelected(false);
                                                    cTuesday.setSelected(false);
                                                    cWednesday.setSelected(false);
                                                    cThursday.setSelected(false);
                                                    cFriday.setSelected(false);
                                                    cSaturday.setSelected(false);
                                                    cSunday.setSelected(false);  
                             
                                
                            }                    
                        }
                    } else{
                            cMonday.setSelected(false);
                            cTuesday.setSelected(false);
                            cWednesday.setSelected(false);
                            cThursday.setSelected(false);
                            cFriday.setSelected(false);
                            cSaturday.setSelected(false);
                            cSunday.setSelected(false); 
                             
                             
                             
                            }    */
                    try {
                        //String days1 = rs.getString("activeDays");

                        switch (rs.getString("activeDays")) {
                            case "Monday Wednesday Sunday":
                                cMonday.setSelected(true);
                                cWednesday.setSelected(true);
                                cSunday.setSelected(true);
                                break;
                            case "Tuesday":
                                cTuesday.setSelected(true);

                                break;
                            case "Wednesday":
                                cWednesday.setSelected(true);

                                break;
                            case "Thursday":
                                cThursday.setSelected(true);

                                break;
                            case "Friday":
                                cFriday.setSelected(true);

                                break;
                            case "Saturday":
                                cSaturday.setSelected(true);

                                cSunday.setSelected(false);
                                break;
                            case "Sunday":
                                cSunday.setSelected(true);

                                break;
                            default:
                                //cMonday.setSelected(true);
                                //cTuesday.setSelected(true);
                                //cWednesday.setSelected(true);
                                //cThursday.setSelected(true);
                            //cFriday.setSelected(true);
                            //cSaturday.setSelected(true);
                            //cSunday.setSelected(true);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LControllController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(LControllController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }

    private boolean validateName() {
        Pattern p = Pattern.compile("^[a-z A-Z0-9.-]*$");
        //Pattern p = Pattern.compile("^[a-zA-Z\\s]*$");
        Matcher m = p.matcher(txtName.getText());
        if (m.find() && m.group().equals(txtName.getText())) {
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

    public void update() {
        btnUpdate.setOnAction(e -> {
            try {
                if (validateFields() && validateName() && validateNumber()) {
                    String days1 = "";
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
                    if (cSaturday.isSelected()) {
                        days1 += "Saturday" + " ";
                    }
                    if (cSunday.isSelected()) {
                        days1 += "Sunday" + " ";
                    }

                    String query = "UPDATE lecturers SET name='" + txtName.getText() + "',empID='" + txtEmpID.getText() + "',faculty='" + ddFaculty.getValue() + "',department='" + ddDepartment.getValue() + "',activeDays='" + days1.toString() + "',activeHours1='" + time.getEditor().getText() + "',activeHours2='" + time1.getEditor().getText() + "',center='" + ddCenter.getValue() + "',building='" + ddBuilding.getValue() + "',level=" + ddLevel.getValue() + ",rank='" + txtRank.getText() + "'where empID='" + txtEmpID.getText() + "'";
                    excecuteQuery(query);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Updated successfully");
                    alert.showAndWait();
                    showLecturers();
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

        String query = "delete from lecturers where empID = '" + txtEmpID.getText() + "'";
        excecuteQuery(query);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Deleted successfully");
        alert.showAndWait();
        showLecturers();
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
        txtName.setText("");
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
        cSaturday.setSelected(false);
        cSunday.setSelected(false);
        checkBoxList.clear();

    }

}
