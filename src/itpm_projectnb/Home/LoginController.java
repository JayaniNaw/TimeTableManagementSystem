/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import java.awt.event.MouseEvent;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sudarshana
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label txtLabel;

    /**
     * Initializes the controller class.
     * @param event
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }   
    @FXML
    private void handleButtonAction(ActionEvent event) {
         String username = txtUsername.getText();
         String password = txtPassword.getText();
         
         if(username.equals("admin") && password.equals("admin")){
             txtLabel.setTextFill(Color.GREEN);
             txtLabel.setText("Successfully Login....!");
             
             try {
                  
                 Node node = (Node) event.getSource();
                 Stage stage = (Stage) node.getScene().getWindow();
                 stage.close();
                 Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")));
                 stage.setScene(scene);
                 stage.show();
                 
             } catch (IOException ex) {
                 System.out.println(ex.getMessage());
             }
         
         }else if(username.equals("") || password.equals("")){
             txtLabel.setTextFill(Color.TOMATO);
             txtLabel.setText("Can not be blank");
         }else{
             txtLabel.setTextFill(Color.TOMATO);
             txtLabel.setText("Enter correct username and password");
         }
    }
            
        
}
    

