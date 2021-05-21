
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Managesetting_page1Controller implements Initializable {

    @FXML
    private Button btnSession;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleAddLecSession(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("sessions.fxml"));//new fxml for a new window
           
        Stage stage = new Stage();//object for a new window
        stage.setTitle("Manage Lecture sessions");
        stage.setScene(new Scene(root, 1340, 700));
        //specifies modality for a new window
        
        //application_modal means you cannot interact with either window until this new window is closed.
       stage.initModality(Modality.APPLICATION_MODAL);//default is none
//       stage.initOwner(btnwshedule.getScene().getWindow());
//        stage.resizableProperty().setValue(Boolean.FALSE);
         stage.initStyle(StageStyle.UTILITY);//hides minimize and maximize
        stage.show();
        
    
    
    }
    
    @FXML
    private void handleAddSession(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("viewallSessions.fxml"));//new fxml for a new window
           
        Stage stage = new Stage();//object for a new window
        stage.setTitle("Manage Add Sessions");
        stage.setScene(new Scene(root, 1291, 820));
        //specifies modality for a new window
        
        //application_modal means you cannot interact with either window until this new window is closed.
       stage.initModality(Modality.APPLICATION_MODAL);//default is none
//       stage.initOwner(btnwshedule.getScene().getWindow());
//        stage.resizableProperty().setValue(Boolean.FALSE);
         stage.initStyle(StageStyle.UTILITY);//hides minimize and maximize
        stage.show();
        
    
    
    }
    
     @FXML
    private void handleAddSessionRoom(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("SessionMain.fxml"));//new fxml for a new window
           
        Stage stage = new Stage();//object for a new window
        stage.setTitle("Manage Add Sessions");
        stage.setScene(new Scene(root, 1340, 700));
        //specifies modality for a new window
        
        //application_modal means you cannot interact with either window until this new window is closed.
       stage.initModality(Modality.APPLICATION_MODAL);//default is none
//       stage.initOwner(btnwshedule.getScene().getWindow());
//        stage.resizableProperty().setValue(Boolean.FALSE);
         stage.initStyle(StageStyle.UTILITY);//hides minimize and maximize
        stage.show();
        
    
    
    }
    @FXML
    private void handlebtnnotavailable(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("ManageNotAvailableTimes.fxml"));//new fxml for a new window
           
        Stage stage = new Stage();//object for a new window
        stage.setTitle("Manage Not Available Settings");
        stage.setScene(new Scene(root, 1200, 662));
        //specifies modality for a new window
        
        //application_modal means you cannot interact with either window until this new window is closed.
       stage.initModality(Modality.APPLICATION_MODAL);//default is none
//       stage.initOwner(btnwshedule.getScene().getWindow());
//        stage.resizableProperty().setValue(Boolean.FALSE);
         stage.initStyle(StageStyle.UTILITY);//hides minimize and maximize
        stage.show();
        
    }
    
}
