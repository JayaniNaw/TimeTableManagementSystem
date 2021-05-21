/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Adeesha
 */
public class StudentGroupsController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadAddStudentGroups(ActionEvent event) throws IOException {
        
       AnchorPane pane = FXMLLoader.load(getClass().getResource("addStudentGroups.fxml"));
       rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadManageGroups(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("manageStudentGroups.fxml"));
       rootPane.getChildren().setAll(pane);
    }
    
}
