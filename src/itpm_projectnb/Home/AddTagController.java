/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adeesha
 */
public class AddTagController implements Initializable {

    @FXML
    private ComboBox TagNameComb;
    ObservableList<String> list1 = FXCollections.observableArrayList("Lec", "Tute", "Lab", "Lec + Tute");
    
    @FXML
    private ComboBox RelatedTagComb;
    ObservableList<String> list2 = FXCollections.observableArrayList("Lecture", "Tutorial", "Practical", "Lecture with Tutorial");
    
    @FXML
    private Button saveBtn;
    @FXML
    private FontAwesomeIconView switchIcon;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private FontAwesomeIconView exitIcon;
    @FXML
    private Button clearBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TagNameComb.setItems(list1);                                                    //set values in combobox 
        RelatedTagComb.setItems(list2);
    }    
     String tagName;
     String tagCode;
     String relatedTag;
     
    //save all data of a tag by clicking this button 
    @FXML
    public void saveTag(javafx.event.ActionEvent actionEvent) throws IOException {
        
           if (actionEvent.getSource() == saveBtn)
            {
                  insertTag();  
            }
       }

    // the related tag combobox according to the tag name for preventing having mistakes
    @FXML
    public void fillComboBox()
    {     
           tagName = TagNameComb.getValue().toString();
            if (tagName.equals("Lec"))
            {                       
                 RelatedTagComb.setValue(list2.get(0));
                 relatedTag =  RelatedTagComb.getValue().toString(); 
            
            }
             if (tagName.equals("Tute"))
            {
                 
                 RelatedTagComb.setValue(list2.get(1));
                 relatedTag =  RelatedTagComb.getValue().toString(); 

            }
              if (tagName.equals("Lab"))
            {
                 RelatedTagComb.setValue(list2.get(2));
                 relatedTag =  RelatedTagComb.getValue().toString(); 
             
            }
              
                if (tagName.equals("Lec + Tute"))
            {
                 RelatedTagComb.setValue(list2.get(3));
                 relatedTag =  RelatedTagComb.getValue().toString(); 
             
            }
               if (tagName.equals(""))
            {
                 RelatedTagComb.setValue("");
                 relatedTag =  RelatedTagComb.getValue().toString(); 
            
            }

    }
    
        //check whether the fielts are empty
      public boolean isEmpty()
     {              

         if ( TagNameComb.getSelectionModel().isEmpty() ||  RelatedTagComb.getSelectionModel().isEmpty() )
         {
                              return true;
         }
         else
        return false;
     }
      
      
        //after those validations then only the tag will be added to the database. Else relevant error messages regarding the errors will be prompt.  
   public void insertTag()
    {   Connection con = null;
        PreparedStatement preState = null;
        ResultSet rs = null;
        con = DbConnect.connectDB();                    // connect to the Dbhelper class
        AlertBox ab = new AlertBox();          //create alerbox object to prompt any alerts accordingly
 
               if (!isEmpty() )
               {
        try {
            String query = "INSERT INTO tags (TagName, RelatedTag) values ('"+ tagName+"' , '"+ relatedTag+"' )";
            preState= con.prepareStatement(query);
            preState.execute();
            
            if ( tagName.equals("Lec"))
            {
            ab.displayInfo("Success", "A Lecture tag has been added.");
            }
             
            if ( tagName.equals("Tute"))
            {
            ab.displayInfo("Success", "A Tutorial tag has been added.");
            } 
            if ( tagName.equals("Lab"))
            {
            ab.displayInfo("Success", "A Practical tag has been added.");
            }
            if ( tagName.equals("Lec + Tute"))
            {
            ab.displayInfo("Success", "A Lecture with Tutorial tag has been added.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddStudentGroupsController.class.getName()).log(Level.SEVERE, null, ex);
        }
               }
               else
                   ab.displayError("Error!", "Not any value can be empty. Please fill");

    }
   
   
       //set a tooltip text to indicate the action on the switch icon button when hover the icon button
    @FXML
     public void setToolTip()
        {
            Tooltip.install(switchIcon, new Tooltip("Switch to Manage Tags"));
        }

     
         //switch to manage tags scene from add tags.
    @FXML
    private void switchScene(MouseEvent event) throws IOException {
       Parent pane = FXMLLoader.load(getClass().getResource("manageTags.fxml"));
      // rootPane.getChildren().setAll(pane);
       
        Scene scene = new Scene(pane);
         //     rootPane.getChildren().setAll(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }

        //exit the current scene
    @FXML
    private void exitScene(MouseEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("home.fxml"));
   

        Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }

        // clear all fields by clicking clear button
    @FXML
    private void clearData(ActionEvent event) {
      
        if (event.getSource() == clearBtn)
            {
                  TagNameComb.setValue("");
                 
            }
            
    }
}
