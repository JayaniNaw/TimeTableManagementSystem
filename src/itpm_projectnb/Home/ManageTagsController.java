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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adeesha
 */
public class ManageTagsController implements Initializable {

     @FXML
    private ComboBox TagNameComb;
    ObservableList<String> list1 = FXCollections.observableArrayList("Lec", "Tute", "Lab", "Lec + Tute");
    
    @FXML
    private ComboBox RelatedTagComb;
    ObservableList<String> list2 = FXCollections.observableArrayList("Lecture", "Tutorial", "Practical", "Lecture with Tutorial");
    
    @FXML
    private TableView<Tags> tagsTable;
    @FXML
    private TableColumn<Tags, Integer> tagID;
    @FXML
    private TableColumn<Tags, String> tagNameTab;
    @FXML
    private TableColumn<Tags, String> tagCodeTab;
    @FXML
    private TableColumn<Tags, String> relatedTagTab;
    @FXML
    private FontAwesomeIconView switchIcon;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private FontAwesomeIconView exitIcon;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewTags();                                                   //call this method by loading the scene
        TagNameComb.setItems(list1);                                 //set values in combobox 
        RelatedTagComb.setItems(list2);
    }  
     Connection con = DbConnect.connectDB();
     PreparedStatement preState = null;
     ResultSet rs = null;
     int ID; 
     String tagName;
     String tagCode;
     String relatedTag;
     
     
    //get tags from the database and add those to an arraylist called tagsList then pass those data to Tags class by the constructor
    public ObservableList<Tags> getTagsList()
    {
               ObservableList<Tags> tagsList = FXCollections.observableArrayList();
        Tags tags;
          
        try {
            String selectQuery= "SELECT * FROM tags";
            preState = con.prepareStatement(selectQuery);
            rs = preState.executeQuery(selectQuery);
            
            while ( rs.next())
            {
                tags = new Tags(rs.getInt("ID"), rs.getString("TagName"), rs.getString("RelatedTag"));
                tagsList.add(tags);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
         return tagsList;
    }
     
    
    //get those tags details from the tagsList then add those to the the Tags table 
    public void viewTags()
    {
        
        ObservableList<Tags> list = getTagsList();

        tagID.setCellValueFactory(new PropertyValueFactory("ID"));
        tagNameTab.setCellValueFactory(new PropertyValueFactory("TagName"));
        relatedTagTab.setCellValueFactory(new PropertyValueFactory("RelatedTag"));

        tagsTable.setItems(list);
    }

    
   //setup a mouseevent action to a table so by clicking any table coulmn of tags details the textfields are filled with those details so the user can edit if wants.
    @FXML
    private void handleMouseAction(MouseEvent event) {
        
        Tags tags= tagsTable.getSelectionModel().getSelectedItem();
         ID = tags.getID();
         TagNameComb.setValue(tags.getTagName());
         RelatedTagComb.setValue(tags.getRelatedTag());
    }
    
        // the related tag combobox according to the tag name for preventing having mistakes
    @FXML
    public void fillComboBox()
    {
           tagName = TagNameComb.getValue().toString();
            // tagCode= null;
           //  relatedTag= null;
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
    
    
   //edit the values in texfields then by clicking the update button those values will be updated
    public void updateTag()
    {
           
        try {
            String updateQuery = "UPDATE tags SET TagName= '"+ tagName+"' , RelatedTag= '"+ relatedTag+"' WHERE ID = '"+ ID +"' ";
            preState = con.prepareStatement(updateQuery);
            preState.executeUpdate();
            viewTags();

        } catch (SQLException ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    // by clicking the delete button the selected group will be deleted
    public void deleteTag()
    {                     
        try {
            String deleteQuery = "DELETE FROM tags WHERE ID = '"+ ID +"' ";
            preState = con.prepareStatement(deleteQuery);
            preState.executeUpdate();
            viewTags();

        } catch (SQLException ex) {
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @FXML
    private void setToolTip(MouseEvent event) {
    }

    @FXML
    private void switchScene(MouseEvent event) throws IOException {
       Parent pane = FXMLLoader.load(getClass().getResource("addTags.fxml"));
        Scene scene = new Scene(pane);
         //     rootPane.getChildren().setAll(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if (event.getSource() == updateBtn)
       {
           updateTag();
            AlertBox ab = new AlertBox(); 
           ab.displayInfo("Success", "Tag has been updated");
       }
       else if (event.getSource() == deleteBtn)
       {
            AlertBox ab = new AlertBox();  
            boolean isOk=  ab.deleteConfirmation("Are you sure you want to delete?");
            if (isOk)
              deleteTag();
       }
    }

    @FXML
    private void exitScene(MouseEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("home.fxml"));
   

        Scene scene = new Scene(pane);

       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(scene);
       window.centerOnScreen();
    }

}
