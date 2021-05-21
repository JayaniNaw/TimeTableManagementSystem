/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Adeesha
 */
class AlertBox {
    
ManageStudentController msc = new ManageStudentController();

    public void displayInfo(String title, String message)
    {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle(title);
                      alert.setContentText(message);
                      alert.setHeaderText(null);
                      alert.showAndWait();
    }
    
     public void displayError(String title, String message)
    {
       Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle(title);
                      alert.setContentText(message);
                      alert.setHeaderText(null);
                      alert.showAndWait();
        
    }
     
      public void displayWarning(String title, String message)
    {
       Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle(title);
                      alert.setContentText(message);
                      alert.setHeaderText(null);
                      alert.showAndWait();
        
    }
   
     public boolean deleteConfirmation(String msg) {
	Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
	dialog.setTitle("Confirmation");
	dialog.setResizable(true);
	dialog.setContentText(msg);
	dialog.setHeaderText(null);
	dialog.showAndWait();
	 if (dialog.getResult() == ButtonType.OK)
         {
           return true;
         }
         return false;
     }
 }


