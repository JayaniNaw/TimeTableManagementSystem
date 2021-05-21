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
import javafx.stage.Stage;

/**
 * FXML  main session room Controller class
 *
 * @author IMAKA
 */
public class SessionMainController implements Initializable {

    @FXML
    private Button btnMainNotOverlapping;

     @FXML
    private Button btnMainParallel;

    @FXML
    private Button btnMainAddSession;

    @FXML
    private Button btnMainConsecutive;

    @FXML
    private Button btnMainManageSession;
    
    @FXML
    private Button btnManageLocation;

    @FXML
    void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnMainNotOverlapping){
             loadStage("AddNonOverlappingSessionRoom.fxml");
            
        }
        else if(event.getSource() == btnMainParallel){
            loadStage("AddParallelSessionRoom.fxml");
            
        }
        else if(event.getSource() == btnMainAddSession){
            loadStage("AddSession.fxml");
        }
        else if(event.getSource() == btnMainConsecutive){
            loadStage("AddConsecutiveSession.fxml");
        }
        else if(event.getSource() == btnMainManageSession){
            loadStage("ManageSession.fxml");
        }
        else if(event.getSource() == btnManageLocation){
            loadStage("ManageLocation.fxml");
        }
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
     public void loadStage(String fxml){
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
    
    
}
