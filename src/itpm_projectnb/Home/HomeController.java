/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adeesha
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnLecturer;
    @FXML
    private Button btnSubjects;
    @FXML
    private Button btnDashBoard;
    @FXML
    private Button btnSession;
    @FXML
    private Button btnManageActivities;;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnLecturer){  
             loadStage("lecturer.fxml");  
             
        }else if(event.getSource() == btnSubjects){
            loadStage("subjects.fxml");
            
        }else if(event.getSource() == btnDashBoard){
            loadStage("dashboard.fxml");
        }
        else if(event.getSource() == btnSession){
            loadStage("Location.fxml");
        }
      //  else if(event.getSource() == btnManageActivities){
      //      loadStage("SessionMain.fxml");
       // }
    }
    public void loadStage(String fxml){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            //stage.close();
            stage.setScene(new Scene(root));
            stage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void loadManagestudents(ActionEvent event) {
    }

    @FXML
    private void loadAddStudentGroups(ActionEvent event) {
    }

    @FXML
    private void loadTags(ActionEvent event) {
    }
    
     @FXML
    void handlebtnTimetable(ActionEvent event) {

    }
    
    @FXML
    void handlebtnmanagesettings(ActionEvent event) {

    }
   
    
}
