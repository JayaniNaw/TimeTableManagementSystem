/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import com.jfoenix.controls.JFXTimePicker;
import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import sun.rmi.runtime.Log;


/**
 * FXML Controller class
 *
 * @author user
 */
public class WorksheduleController implements Initializable {

    @FXML
    private Spinner<Integer> myspinner;
    SpinnerValueFactory<Integer> valueFactory
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);

     @FXML
    private JFXTimePicker startT;

    @FXML
    private JFXTimePicker endT;
    
    @FXML
    private Spinner<Integer> spin4;
    SpinnerValueFactory<Integer> valueFactory6
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(30, 60, 30, 30);

    @FXML
    private Spinner<Integer> spin5;

    SpinnerValueFactory<Integer> valueFactory5
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 5);
    
    @FXML
    private CheckBox chek1;
    
    @FXML
    private CheckBox chek2;

    @FXML
    private CheckBox chek3;

    @FXML
    private CheckBox chek4;

    @FXML
    private CheckBox chek5;
    
    //ObservableList<String> checkboxlist = FXCollections.observableArrayList();
    
    public String checkBoxes(){
    
        String checkboxlist = "";
        
        if(chek1.isSelected()){
            checkboxlist += chek1.getText()+"  ";
        }
        if(chek2.isSelected()){
            checkboxlist += chek2.getText()+"  ";
        }if(chek3.isSelected()){
            checkboxlist += chek3.getText()+"  ";
        }if(chek4.isSelected()){
            checkboxlist += chek4.getText()+"  ";
        }if(chek5.isSelected()){
            checkboxlist += chek5.getText();
        }
        
        return checkboxlist;
    
    }
    
    @FXML
    private TableView<WorkShedule> tvworkdays;
    @FXML
    private TableColumn<WorkShedule, Time> colst;
    @FXML
    private TableColumn<WorkShedule, Time> colet;
    @FXML
    private TableColumn<WorkShedule, String> colwday;
    @FXML
    private TableColumn<WorkShedule, Integer> colslot;
    
    @FXML
    private TableColumn<WorkShedule, Integer> colid;
    
    @FXML
    private Button btngenerate;
    
    @FXML
    private Button btnview;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private VBox vbday;
    @FXML
    private TableColumn<WorkShedule, Integer> colcountdays;
    @FXML
    private TableColumn<WorkShedule, Integer> colbreak;
   
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        myspinner.setValueFactory(valueFactory);
        spin5.setValueFactory(valueFactory5);
        spin4.setValueFactory(valueFactory6);
       showWorkSheule();
        
    }
    
    @FXML
     void handleButtonAction(ActionEvent event) {
         
        if(event.getSource() == btngenerate){
            if(countRecords() == 1){
              btngenerate.setDisable(true);
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Validation");
              alert.setHeaderText(null);
              alert.setContentText("There you have already existing time.You can create only one record.But you can update record.");
              alert.showAndWait();
             }
            if(countRecords() == 0){
             insertRecord();
             btngenerate.getId();
             btngenerate.setDisable(false);
            }
        }
        else if(event.getSource() == btnview){
              showWorkSheule();
        }
        else if(event.getSource() == btnupdate){
              Edit();
        }
        else if(event.getSource() == btndelete){
            delete();
            if(countRecords() == 0){
                btngenerate.setDisable(false);
            }
        }
       
    }

//    Connection conn;
//    
//    public Connection getConnection(){
//    
//        
//        try{
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ITPM", "root","");
//            return conn;
//        }catch(Exception ex){
//            System.out.println("Error: "+ex.getMessage());
//            return null;
//        }
//    }
    
    Connection conn = DbConnect.connectDB();
    private int countRecords(){
        
        int count = 0;
//        conn = getConnection();
        
        String sql = "Select count(distinct id) from ws ";
        try {
            pst=conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
          
          while(rs.next()){
              count = rs.getInt(1);
          }
          
        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        return count;
    }
    
    
    
    public ObservableList<WorkShedule> getShedules(){
    
        ObservableList<WorkShedule> sheduleList = FXCollections.observableArrayList();
//        Connection conn = getConnection();
        String query = "SELECT* FROM ws";
        Statement st ;
        ResultSet rs;
        
        try{
        
        st = conn.createStatement();
        rs = st.executeQuery(query);
        WorkShedule shedule;
        
        while(rs.next()){
            shedule =  new WorkShedule(rs.getInt("numberOfDays"),rs.getString("Days"),rs.getTime("startT"),rs.getTime("endT"),rs.getInt("slot"),rs.getInt("breakcount"),rs.getInt("id"));
            sheduleList.add(shedule);
        }
        }catch(Exception ex){
        ex.printStackTrace();
        
        }
        return sheduleList;
    }
    //read data
    public void showWorkSheule(){
    ObservableList<WorkShedule> shedulelist = getShedules();
    
    colcountdays.setCellValueFactory(new PropertyValueFactory<>("numberofDays")); //numberofDays came from the workshedule.java overloaded constructor parameters...
    colwday.setCellValueFactory(new PropertyValueFactory<WorkShedule,String>("wokringDays"));
    colst.setCellValueFactory(new PropertyValueFactory<WorkShedule,Time>("starttime"));
    colet.setCellValueFactory(new PropertyValueFactory<WorkShedule,Time>("endtime"));
    colslot.setCellValueFactory(new PropertyValueFactory<WorkShedule,Integer>("slot"));
    colbreak.setCellValueFactory(new PropertyValueFactory<WorkShedule,Integer>("breakcount"));
    colid.setCellValueFactory(new PropertyValueFactory<WorkShedule,Integer>("id"));
    
    tvworkdays.setItems(shedulelist);
    clearFields();
    
    }
    
    int index = -1;
    
    public void getSelected(MouseEvent event){
    
        index = tvworkdays.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
       
        SpinnerValueFactory<Integer> setvalue
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,colcountdays.getCellData(index),1);

        myspinner.setValueFactory(setvalue);
        
        
//        if(colwday.getCellData(index).equals("Monday")){
//            chek1.setSelected(false);
//            
//        }
//        else{
//            chek1.setSelected(true);
//            chek2.setSelected(true);
//        }
//       
//        if(colwday.getCellData(index) == "Tuesday"){
//           chek2.setSelected(true);
//          
//        }
//        if(colwday.getCellData(index).toString()== "Wednesday"){
//           chek3.setSelected(true);
//          
//          
//        }
//        if(colwday.getCellData(index) == "Thursday"){
//            chek4.setSelected(true);
//            
//        }
//        
//        if(colwday.getCellData(index).toString() == "Friday"){
//            chek5.setSelected(true);
//            
////        }
//        else{
//            chek5.setSelected(true);
//            
//        }
        
        startT.setValue(colst.getCellData(index).toLocalTime());
        
        endT.setValue(colet.getCellData(index).toLocalTime());
        
        SpinnerValueFactory<Integer> setvalue2
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(30,60,colslot.getCellData(index),30);

        spin4.setValueFactory(setvalue2);
        
        SpinnerValueFactory<Integer> setvalue3
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(4,5,colbreak.getCellData(index),1);

        spin5.setValueFactory(setvalue3);
        ArrayList<String> results = generateSlot();
            label1.setText(results.toString());
    }
    
    PreparedStatement pst = null;
    
      @FXML
    private Label label1;
    private void insertRecord(){
        
//        conn = getConnection();
        String query = "INSERT INTO ws(numberOfDays,days,startT,endT,slot,breakcount) VALUES(?,?,?,?,?,?)";

        if(validateGui()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully inserted Record");
        Optional <ButtonType> action = alert.showAndWait();
        
        
        if(action.get() == ButtonType.OK){
        try{

            pst = conn.prepareStatement(query);
            pst.setInt(1, myspinner.getValue());
            pst.setString(2, checkBoxes());
            pst.setString(3, startT.getValue().toString());
            pst.setString(4, endT.getValue().toString());
            pst.setInt(5,spin4.getValue());
            pst.setInt(6, spin5.getValue());
            
            pst.execute();
            clearFields();
            showWorkSheule();
            //generateSlot();
            //ArrayList<String> results = getTimeSet(false);
             ArrayList<String> results = generateSlot();
            label1.setText(results.toString());
            
        }catch(Exception e){

                e.printStackTrace();
        }
        }
        }
    }
    
    
    //clear fields
    public void clearFields(){
       myspinner.setValueFactory(valueFactory);
       startT.getEditor().setText("");
       endT.getEditor().setText("");
       if(chek1.isSelected()){
           chek1.setSelected(false);
       }
       if(chek2.isSelected()){
           chek2.setSelected(false);
       }
       if(chek3.isSelected()){
           chek3.setSelected(false);
       }
       if(chek4.isSelected()){
           chek4.setSelected(false);
       }
       if(chek5.isSelected()){
           chek5.setSelected(false);
       }
       spin4.setValueFactory(valueFactory6);
       spin5.setValueFactory(valueFactory5);
    }
    
    
    
    
//      String start = startT.getEditor().getText();
//        String stop = endT.getEditor().getText();
//       
//        List< String> slots = new ArrayList<>() ;
//        String ldt = start ;
//        while (ldt.startsWith(stop) )    
//        {
//            slots.add( ldt ) ;
//            // Prepare for the next loop. 
//            ldt +=2;
//            label1.setText(ldt);
//        }
//    }
//    
    private ArrayList<String> generateSlot(){
      
    ArrayList results = new ArrayList<String>();
    
    String timeValue = startT.getValue().toString();

    SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");

    try {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(sdf.parse(timeValue));
//
//        if (startCalendar.get(Calendar.MINUTE) < 30) {
//            startCalendar.set(Calendar.MINUTE, 30);
//        } else {
//            startCalendar.add(Calendar.MINUTE, 30); // overstep hour and clear minutes
//            startCalendar.clear(Calendar.MINUTE);
//
//        }

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startCalendar.getTime());

        // if you want dates for whole next day, uncomment next line
        //endCalendar.add(Calendar.DAY_OF_YEAR, 1);
        endCalendar.add(Calendar.HOUR_OF_DAY, 24 - startCalendar.get(Calendar.HOUR_OF_DAY));

    //  endCalendar.clear(Calendar.MINUTE);
    //  endCalendar.clear(Calendar.SECOND);
    //  endCalendar.clear(Calendar.MILLISECOND);

        SimpleDateFormat slotTime = new SimpleDateFormat("hh:mma");
        //SimpleDateFormat slotDate = new SimpleDateFormat(", dd/MM/yy");
       // while (endCalendar.after(startCalendar)) {
            for (int i = 0; i < 9; i++) {
            String slotStartTime = slotTime.format(startCalendar.getTime());
//            String slotStartDate = slotDate.format(startCalendar.getTime());
            //String slotStartTime = slotTime.format(timeValue);
            startCalendar.add(Calendar.MINUTE, spin4.getValue());
            String slotEndTime = slotTime.format(startCalendar.getTime());

            String day = slotStartTime + " - " + slotEndTime;
            results.add(i, day);
            }
        //}


    } catch (Exception e) {
        // date in wrong format
    }
    return results;
}
//    
//    private ArrayList<String> getTimeSet(boolean isCurrentDay) {
//        ArrayList results = new ArrayList<String>();
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//        Calendar calendar = new GregorianCalendar();
//        calendar.set(Calendar.AM_PM, 0);// what should be the default?
//        if(!isCurrentDay)
////            calendar.set(Calendar.AM_PM, (int) Time.parse(startT.getValue().toString()));
//            calendar.set(Calendar.AM_PM, 8);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        for (int i = 0; i < 9; i++) {
//            String  day1 = sdf.format(calendar.getTime());
//                //String day1 = sdf.format(calendar.startT.getValue().toString());
//            // add 15 minutes to the current time; the hour adjusts automatically!
//            calendar.add(Calendar.MINUTE, 60);
//
//            String day2 = sdf.format(calendar.getTime());
//
//            String day = day1 + " - " + day2;
//            results.add(i, day);
//            System.out.println("/n");
//        }
//        return results;
//    }

    
    
    
    public void Edit(){
        
        WorkShedule selected = tvworkdays.getSelectionModel().getSelectedItem();
        int ID = selected.getId();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Updated Record");
        Optional <ButtonType> action = alert.showAndWait();
            
        if(action.get() == ButtonType.OK){
        try{
//            conn = getConnection();
            int value1 = myspinner.getValue();
            String  value2 = (checkBoxes());
            String value3 = startT.getValue().toString();
            String value4 = endT.getValue().toString();
            int value5 = spin4.getValue();
            int value6 = spin5.getValue();

            
            String query = "UPDATE ws SET numberOfDays= '"+value1+"' ,days= '"+value2+"' ,startT= '"+value3+"' ,endT= '"+value4+"' ,slot= '"+value5+"' ,breakcount= '"+value6+"'  WHERE id= '"+ID+"'";
            pst = conn.prepareStatement(query);
            pst.execute();
            clearFields();
            showWorkSheule();
           // generateSlot();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }
    }
    
    public void delete(){
       
            WorkShedule selected = tvworkdays.getSelectionModel().getSelectedItem();
            int ID = selected.getId();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are You sure Do you Want to Delete the Record?");
            Optional <ButtonType> action = alert.showAndWait();
           
            if(action.get() == ButtonType.OK){
            try{
//            conn = getConnection();
            String sql = "DELETE FROM ws WHERE id = '"+ID+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            clearFields();
            showWorkSheule();
            label1.setText("");
            }catch(Exception e){
                e.printStackTrace();
                
            }
            }
    }
       
    private boolean validateGui(){
    
        if(!(chek1.isSelected()|chek2.isSelected()|chek3.isSelected()|chek4.isSelected()|chek5.isSelected())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select days");
            alert.showAndWait();
            
            return false;
        }
         else if(startT.getEditor().getText().isEmpty() | endT.getEditor().getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select time duration");
            alert.showAndWait();
            
            return false;
        }
        else if((!(chek1.isSelected()|chek2.isSelected()|chek3.isSelected()|chek4.isSelected()|chek5.isSelected()))&& (startT.getEditor().getText().isEmpty() | endT.getEditor().getText().isEmpty())){
         
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select start time and days");
            alert.showAndWait();
            
            return false;
            
        }
    
        return true;
        
 
    }
   
}
