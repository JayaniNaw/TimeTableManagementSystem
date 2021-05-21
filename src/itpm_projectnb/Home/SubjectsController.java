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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class SubjectsController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private Button btnSave;

    ObservableList<String> yearList = FXCollections.observableArrayList("1", "2", "3", "4");
    ObservableList<String> semList = FXCollections.observableArrayList("1", "2");
    @FXML
    private ComboBox ddYear;
    @FXML
    private Button btnClear;
    @FXML
    private Spinner spLecture;
    @FXML
    private Spinner spTute;
    @FXML
    private Spinner spLabs;
    @FXML
    private Spinner spEva;

    //Connection con;
    PreparedStatement pst;
    @FXML

    private TextField txtName;
    @FXML
    private TextField txtCode;

    @FXML
    private ComboBox ddSem;
    @FXML
    private VBox rbSem;
    @FXML
    private Button btnManage;
    
    Connection conn = DbConnect.connectDB();
    //PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ddYear.setItems(yearList);
        ddSem.setItems(semList);

        //configure the spinner with number 1-10 for lecture,tute,labs hours
        SpinnerValueFactory<Integer> hours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        this.spLecture.setValueFactory(hours);
        SpinnerValueFactory<Integer> tute = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        this.spTute.setValueFactory(tute);
        SpinnerValueFactory<Integer> lab = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        this.spLabs.setValueFactory(lab);
        SpinnerValueFactory<Integer> eva = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        this.spEva.setValueFactory(eva);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnSave) {
            //Connection con = connect();
            try {

                Statement stat = conn.createStatement();
                String selectQuery = "SELECT * From subjects where code = '" + txtCode.getText() + "'";

                ResultSet rs = stat.executeQuery(selectQuery);

                if (rs.next() == true) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Already Added" + " " + txtCode.getText());
                    alert.showAndWait();

                } else {
                    if (validateFields() && validateName() && validateNumber()) {

                        insert();
                        try {

                            Node node = (Node) event.getSource();
                            Stage stage = (Stage) node.getScene().getWindow();
                            stage.close();
                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SubManage.fxml")));
                            stage.setScene(scene);
                            stage.show();

                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Information Dialog");
                        alert.setContentText("Error in inserting");
                        alert.showAndWait();

                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event.getSource() == btnClear) {
            clear();
        } else if (event.getSource() == btnManage) {
            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SubManage.fxml")));
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

    public boolean validateFields() {
        if (ddYear.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please Choose offered year!");
            alert.showAndWait();
            return false;
        } else if (ddSem.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please Choose offered Semster!");
            alert.showAndWait();
            return false;

        } else if (txtName.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter subject name!");
            alert.showAndWait();
            return false;

        } else if (txtCode.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setContentText("Please enter subject code!");
            alert.showAndWait();
            return false;

        }

        return true;
    }

    private boolean validateName() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
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
        Pattern p = Pattern.compile("[a-zA-Z]{2}[0-9]{4}");
        Matcher m = p.matcher(txtCode.getText());
        if (m.find() && m.group().equals(txtCode.getText())) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Subject code should be 6  with 2 letters and 4 digits");
            alert.showAndWait();

            return false;
        }

    }

    public void insert() {
        //Connection con = connect();
        try {

            Statement stat = conn.createStatement();
            String selectQuery = "SELECT * From subjects where code = '" + txtCode.getText() + "'";

            ResultSet rs = stat.executeQuery(selectQuery);

            if (rs.next() == true) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Already Added" + " " + txtCode.getText());
                alert.showAndWait();

            } else {

                //if(validateFields() && validateName() && validateNumber()){
                String query = "INSERT INTO subjects(Year,Semester,Name,Code,NoOfLecture,NoOfTute,NoOfLab,NoOfEva) values(" + ddYear.getValue() + "," + ddSem.getValue() + ",'" + txtName.getText() + "','" + txtCode.getText() + "'," + spLecture.getValue() + "," + spTute.getValue() + "," + spLabs.getValue() + "," + spEva.getValue() + ")";
                excecuteQuery(query);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Successfully Added");
                alert.showAndWait();
                clear();
                //loadStage("SubManage.fxml");

            }//
            // else{
            /* Alert alert = new Alert(AlertType.WARNING);
                 alert.setTitle("Information Dialog");
                 alert.setContentText("Error in inserting");
                 alert.showAndWait();*/

            // }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
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
        txtName.setText("");
        txtCode.setText("");
        ddYear.setValue("");
        ddSem.setValue("");

    }

}
