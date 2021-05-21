/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Adeesha
 */
public class ManageStudentGroupsController implements Initializable {

    @FXML
    private ComboBox<?> AcYrComb;
    @FXML
    private ComboBox<?> programComb;
    @FXML
    private ComboBox<?> groupNoComb;
    @FXML
    private ComboBox<?> subgroupNoComb;
    @FXML
    private TableView<?> stdGroupsTable;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> yearAndSem;
    @FXML
    private TableColumn<?, ?> program;
    @FXML
    private TableColumn<?, ?> grpNo;
    @FXML
    private TableColumn<?, ?> subGrpNo;
    @FXML
    private TableColumn<?, ?> grpID;
    @FXML
    private TableColumn<?, ?> subGrpID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
