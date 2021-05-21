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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class SubManageController implements Initializable {

    @FXML
    private TableView<SubManage> tv;
    @FXML
    private TableColumn<SubManage, Integer> colID;
    @FXML
    private TableColumn<SubManage, Integer> colYear;
    @FXML
    private TableColumn<SubManage, Integer> colSem;
    @FXML
    private TableColumn<SubManage, String> colName;
    @FXML
    private TableColumn<SubManage, String> colCode;
    @FXML
    private TableColumn<SubManage, Integer> colLecture;
    @FXML
    private TableColumn<SubManage, Integer> colTute;
    @FXML
    private TableColumn<SubManage, Integer> colLab;
    @FXML
    private TableColumn<SubManage, Integer> colEva;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox ddYear;
    @FXML
    private ComboBox ddSem;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCode;
    @FXML
    private Spinner spLecture;
    @FXML
    private Spinner spTute;
    @FXML
    private Spinner spLab;
    @FXML
    private Spinner spEva;

    ObservableList<String> yearList = FXCollections.observableArrayList("1", "2", "3", "4");
    ObservableList<String> semList = FXCollections.observableArrayList("1", "2");

    Connection conn = DbConnect.connectDB();
    PreparedStatement pst;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ddYear.setItems(yearList);
        ddSem.setItems(semList);
        showSubjects();
        //configure the spinner with number 1-10 for lecture,tute,labs hours
        SpinnerValueFactory<Integer> hours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2);
        this.spLecture.setValueFactory(hours);
        SpinnerValueFactory<Integer> tute = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2);
        this.spTute.setValueFactory(tute);
        SpinnerValueFactory<Integer> lab = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2);
        this.spLab.setValueFactory(lab);
        SpinnerValueFactory<Integer> eva = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2);
        this.spEva.setValueFactory(eva);

    }

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

    public ObservableList<SubManage> getBooksList() {
        ObservableList<SubManage> subjectsList = FXCollections.observableArrayList();
        //Connection con = connect();

        String query = "select * from subjects";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SubManage subjects;

            while (rs.next()) {
                subjects = new SubManage(rs.getInt("ID"), rs.getInt("year"), rs.getInt("semester"), rs.getString("name"), rs.getString("code"), rs.getInt("noOfLecture"), rs.getInt("noOfTute"), rs.getInt("noOfLab"), rs.getInt("noOfEva"));
                subjectsList.add(subjects);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subjectsList;

    }

    public void showSubjects() {
        ObservableList<SubManage> list = getBooksList();

        colID.setCellValueFactory(new PropertyValueFactory<SubManage, Integer>("ID"));
        colYear.setCellValueFactory(new PropertyValueFactory<SubManage, Integer>("year"));
        colSem.setCellValueFactory(new PropertyValueFactory<SubManage, Integer>("semester"));
        colName.setCellValueFactory(new PropertyValueFactory<SubManage, String>("name"));
        colCode.setCellValueFactory(new PropertyValueFactory<SubManage, String>("code"));
        colLecture.setCellValueFactory(new PropertyValueFactory<SubManage, Integer>("noOfLecture"));
        colTute.setCellValueFactory(new PropertyValueFactory<SubManage, Integer>("noOfTute"));
        colLab.setCellValueFactory(new PropertyValueFactory<SubManage, Integer>("noOfLab"));
        colEva.setCellValueFactory(new PropertyValueFactory<SubManage, Integer>("noOfEva"));

        tv.setItems(list);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnBack) {
            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("subjects.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else if (event.getSource() == btnDelete) {
            delete();

        } else if (event.getSource() == btnClear) {
            clear();

        }else if (event.getSource() == btnUpdate) {
            update();

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
        Pattern p = Pattern.compile("[0-9]{6}");
        Matcher m = p.matcher(txtCode.getText());
        if (m.find() && m.group().equals(txtCode.getText())) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Subject code should be 4 digits");
            alert.showAndWait();

            return false;
        }

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
        tv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //Connection con = connect();
                try {
                    SubManage subject = (SubManage) tv.getSelectionModel().getSelectedItem();

                    String query = "SELECT * FROM subjects WHERE ID=?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setInt(1, subject.getID());
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        ddYear.setValue(rs.getString("year"));
                        ddSem.setValue(rs.getString("semester"));
                        txtName.setText(rs.getString("name"));
                        txtCode.setText(rs.getString("code"));

                        int index = -1;
                        index = tv.getSelectionModel().getSelectedIndex();
                        if (index <= -1) {
                            return;

                        }

                        SpinnerValueFactory<Integer> LectHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, colLecture.getCellData(index));
                        spLecture.setValueFactory(LectHours);
                        SpinnerValueFactory<Integer> TuteHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, colTute.getCellData(index));
                        spTute.setValueFactory(TuteHours);
                        SpinnerValueFactory<Integer> labHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, colLab.getCellData(index));
                        spLab.setValueFactory(labHours);
                        SpinnerValueFactory<Integer> EvaHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, colEva.getCellData(index));
                        spEva.setValueFactory(EvaHours);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void update() {
        btnUpdate.setOnAction(e -> {
            try {
                if (validateName()) {
                    String query = "UPDATE subjects SET year=" + ddYear.getValue() + ",semester=" + ddSem.getValue() + ",name='" + txtName.getText() + "',code='" + txtCode.getText() + "',noOflecture=" + spLecture.getValue() + ",noOfTute=" + spTute.getValue() + ",noOfLab=" + spLab.getValue() + ",noOfEva=" + spEva.getValue() + " where code='" + txtCode.getText() + "'";
                    excecuteQuery(query);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Updated successfully");
                    alert.showAndWait();
                    showSubjects();
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
        //int opt= JOptionPane.showConfirmDialog(null, "Are you sure to delete?","Delete",JOptionPane.YES_NO_OPTION);
        //if(opt==0){
        String query = "delete from subjects where code = '" + txtCode.getText() + "'";
        excecuteQuery(query);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Deleted Successfully!");
        alert.showAndWait();

        showSubjects();

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
        ddYear.setValue("");
        ddSem.setValue("");
        txtName.setText("");
        txtCode.setText("");

    }
}
