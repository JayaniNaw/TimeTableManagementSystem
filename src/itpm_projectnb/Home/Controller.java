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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable{

    @FXML
    private Button btnLecturer;
    @FXML
    private Button btnSession;
    @FXML
    private Button btnSubjects;
    
     @FXML
    private Button btn_dashboard;

  
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnLecturer){
            loadStage("lecturer.fxml");
        
        }else if(event.getSource() == btnSubjects){
            loadStage("subjects.fxml");
        }
        else if(event.getSource() == btn_dashboard){
            loadStage("dashboard.fxml");
        }
        else if(event.getSource() == btnSession){
         loadStage("Location.fxml");
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
    private void handlebtnwork(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Workshedule.fxml"));//new fxml for a new window
           
        Stage stage = new Stage();//object for a new window
        stage.setTitle("Working Days and Hours");
        stage.setScene(new Scene(root, 1400, 662));
        //specifies modality for a new window
        
        //application_modal means you cannot interact with either window until this new window is closed.
       stage.initModality(Modality.APPLICATION_MODAL);//default is none
//       stage.initOwner(btnwshedule.getScene().getWindow());
//        stage.resizableProperty().setValue(Boolean.FALSE);
         stage.initStyle(StageStyle.UTILITY);//hides minimize and maximize
        stage.show();
        
    }
    
    @FXML
    private void handlebtnmanagesettings(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("managesetting_page1.fxml"));//new fxml for a new window
           
        Stage stage = new Stage();//object for a new window
        stage.setTitle("Manage Settings");
        stage.setScene(new Scene(root, 800, 600));
        //specifies modality for a new window
        
        //application_modal means you cannot interact with either window until this new window is closed.
       stage.initModality(Modality.APPLICATION_MODAL);//default is none
//       stage.initOwner(btnwshedule.getScene().getWindow());
//        stage.resizableProperty().setValue(Boolean.FALSE);
         stage.initStyle(StageStyle.UTILITY);//hides minimize and maximize
        stage.show();
        
    }
    
     @FXML
    private void handlebtnTimetable(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Timetable.fxml"));//new fxml for a new window
           
        Stage stage = new Stage();//object for a new window
        stage.setTitle("TimeTable");
        stage.setScene(new Scene(root, 1087, 701));
        //specifies modality for a new window
        
        //application_modal means you cannot interact with either window until this new window is closed.
       stage.initModality(Modality.APPLICATION_MODAL);//default is none
//       stage.initOwner(btnwshedule.getScene().getWindow());
//        stage.resizableProperty().setValue(Boolean.FALSE);
         stage.initStyle(StageStyle.UTILITY);//hides minimize and maximize
        stage.show();
         
    }
    
  
    @FXML
    public void loadAddStudentGroups(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("addStudentGroups.fxml"));
       Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }
    
    @FXML
    public void loadTags(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("addTags.fxml"));
       Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }   
}

