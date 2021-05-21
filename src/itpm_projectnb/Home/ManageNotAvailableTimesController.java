/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import helpers.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author user
 */
public class ManageNotAvailableTimesController implements Initializable {

    //ObservableList<String> listacombo1= FXCollections.observableArrayList();
        
    @FXML
    private ComboBox<String> lecId;
    
     @FXML
    private ComboBox<String> groupId;

    @FXML
    private ComboBox<String> subgId;

    @FXML
    private ComboBox<String> ssId;
    
     @FXML
    private ComboBox<String> sessionRoom;

     @FXML
    private ComboBox<String> day;
     
     @FXML
    private JFXDatePicker not_a_date;

    @FXML
    private JFXTimePicker tfrom;

    @FXML
    private JFXTimePicker tto;
    
     @FXML
    private Button btngenerate;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnview;
    
    @FXML
    private TableColumn<Tab1_notavailableLec, Integer> colId;

    @FXML
    private TableColumn<Tab1_notavailableLec, String> colLec;

    @FXML
    private TableColumn<Tab1_notavailableLec, String> colgroup;

    @FXML
    private TableColumn<Tab1_notavailableLec, String> colsgroup;

    @FXML
    private TableColumn<Tab1_notavailableLec, String> colSid;

    @FXML
    private TableColumn<Tab1_notavailableLec, String> coldate;

    @FXML
    private TableColumn<Tab1_notavailableLec, Time> coltfrom;

    @FXML
    private TableColumn<Tab1_notavailableLec, Time> coltto;
    
    @FXML
    private TableView<Tab1_notavailableLec> tblview1;
    
    PreparedStatement pst=null;
    Connection conn = DbConnect.connectDB();
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayLecturers();//fill lecture combo
        displayMainGroups();//fillgeoups combo
        displaySessions();//fill session id
        displaySessionRoom(); //fill session room -tab2
        displaySessions_3();//fill tab3 combo
        day.setItems(list1);
        showNotAvailableLec();//retrieve to table
        showNotAvailableLocation();//retrieve not available locations
        showAddedTimes();//retrieve all the added prefer tmes to tableview
    }
    
    @FXML//handling tab1
     void handleButtonAction(ActionEvent event) {
        
        if(event.getSource() == btngenerate){
            
             insertRecord();
             
             
        }
        else if(event.getSource() == btnview){
              showNotAvailableLec();
             
        }
        else if(event.getSource() == btnupdate){
              Edit();
        }
        else if(event.getSource() == btndelete){
            delete();
        }
        else if(event.getSource() == btn2_add){
            insertRecord_tab2();
        }
        else if(event.getSource() == btn2_update){
            Edit_tab2();
        }
        else if(event.getSource() == btn2_del){
            delete_tab2();
        }
        else if(event.getSource() == btn2_view){
              showNotAvailableLocation();
              
        }
        else if(event.getSource() == btn3_add){
            insertRecord_tab3();
        }
        else if(event.getSource() == btn3_update){
            Edit_tab3();
        }
        else if(event.getSource() == btn3_del){
            delete_tab3();
        }
        else if(event.getSource() == btn3_view){
              showAddedTimes();
             
        }
        
       
    }
    
   
    public ObservableList<Tab1_notavailableLec> getNotAvailableLec(){
    
        ObservableList<Tab1_notavailableLec> lecList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        String query = "SELECT* FROM notavailablelecture";
        Statement st ;
        ResultSet rs;
        
        try{
        
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Tab1_notavailableLec lec;
        
        while(rs.next()){
            lec =  new Tab1_notavailableLec(rs.getInt("id"),rs.getString("Lecturer"),rs.getString("mgroup"),rs.getString("SubGroup"),rs.getString("SessionID"),rs.getString("Date"),rs.getTime("Timefrom"),rs.getTime("Timeto"));
            lecList.add(lec);
        }
        }catch(Exception ex){
        ex.printStackTrace();
        
        }
        return lecList;
    }
    //read data
    public void showNotAvailableLec(){
    ObservableList<Tab1_notavailableLec> lecList = getNotAvailableLec();
    
    colId.setCellValueFactory(new PropertyValueFactory<>("id")); //numberofDays came from the workshedule.java overloaded constructor parameters...
    colLec.setCellValueFactory(new PropertyValueFactory<>("lecture"));
    colgroup.setCellValueFactory(new PropertyValueFactory<>("mgroup"));
    colsgroup.setCellValueFactory(new PropertyValueFactory<>("subgroup"));
    colSid.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
    coldate.setCellValueFactory(new PropertyValueFactory<>("date1"));
    coltfrom.setCellValueFactory(new PropertyValueFactory<>("from"));
    coltto.setCellValueFactory(new PropertyValueFactory<>("to"));
     
    tblview1.setItems(lecList);
    clearFields();
    
    }
    
    
    
    public void getSelected(MouseEvent event){
        int index = -1;
        index = tblview1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
    
        lecId.setValue(colLec.getCellData(index));
        groupId.setValue(colgroup.getCellData(index));
        subgId.setValue(colsgroup.getCellData(index));
        ssId.setValue(colSid.getCellData(index));
        not_a_date.setValue(LocalDate.parse(coldate.getCellData(index)));
        tfrom.setValue(coltfrom.getCellData(index).toLocalTime());
        tto.setValue(coltto.getCellData(index).toLocalTime());
        
    }
    
    //insert a record
    private void insertRecord(){
        
        //conn = getConnection();
        String query = "INSERT INTO notavailablelecture(Lecturer,mgroup,SubGroup,SessionID,Date,Timefrom,Timeto) VALUES(?,?,?,?,?,?,?)";

        if(validateGui_tab1()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully inserted Record");
        Optional <ButtonType> action = alert.showAndWait();
        
        
        if(action.get() == ButtonType.OK){
        try{

            pst = conn.prepareStatement(query);
            pst.setString(1,lecId.getValue());
            pst.setString(2, groupId.getValue());
            pst.setString(3, subgId.getValue());
            pst.setString(4, ssId.getValue());
            pst.setString(5, not_a_date.getValue().toString());
            pst.setString(6, tfrom.getValue().toString());
            pst.setString(7, tto.getValue().toString());
            pst.execute();
            clearFields();
            showNotAvailableLec();
            
        }catch(Exception e){

                e.printStackTrace();
        }
        }
        }
    }
    //edit record
    public void Edit(){
        
        Tab1_notavailableLec selected = tblview1.getSelectionModel().getSelectedItem();
        int ID = selected.getId();
        
        if(validateGui_tab1()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Updated Record");
        Optional <ButtonType> action = alert.showAndWait();
            
        if(action.get() == ButtonType.OK){
        try{
           // conn = getConnection();
            String value1 = lecId.getValue();
            String  value2 = groupId.getValue();
            String value3 = subgId.getValue();
            String value4 = ssId.getValue();
            LocalDate value5 = LocalDate.parse(not_a_date.getValue().toString());
            String value6 = tfrom.getValue().toString();
            String value7 = tto.getValue().toString();

            
            String query = "UPDATE notavailablelecture SET Lecturer= '"+value1+"' ,mgroup= '"+value2+"' ,subgroup= '"+value3+"' ,SessionID= '"+value4+"' ,Date= '"+value5+"' ,TimeFrom= '"+value6+"', TimeTo='"+value7+"'  WHERE id= '"+ID+"'";
            pst = conn.prepareStatement(query);
            pst.execute();
            clearFields();
            showNotAvailableLec();
           
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }}
    }
    
    public void delete(){
       
            Tab1_notavailableLec selected = tblview1.getSelectionModel().getSelectedItem();
            int ID = selected.getId();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are You sure Do you Want to Delete the Record?");
            Optional <ButtonType> action = alert.showAndWait();
           
            if(action.get() == ButtonType.OK){
            try{
            //conn = getConnection();
            String sql = "DELETE FROM notavailablelecture WHERE id = '"+ID+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            clearFields();
            showNotAvailableLec();
            }catch(Exception e){
                e.printStackTrace();
                
            }
            }
    }
    
    //clear fields
    public void clearFields(){
       lecId.setValue("");
       groupId.setValue("");
       subgId.setValue("");
       ssId.setValue("");
       not_a_date.getEditor().setText("");
       tfrom.getEditor().setText("");
       tto.getEditor().setText("");
    }
    
   //lectures combobox
    public void displayLecturers(){
        
      //conn = getConnection();
      try {
          ObservableList<String> leclist = FXCollections.observableArrayList();
          String sql="select empID from lecturers";
          pst=conn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          
          while(rs.next()){
              leclist.add(rs.getString("empID"));
          }
          lecId.setItems(leclist);
          
        } catch (SQLException ex) {
            Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
    
    //fill group combo
    public void displayMainGroups(){
        
      //conn = getConnection();
      try {
           ObservableList<String> sessionGrouplist1 = FXCollections.observableArrayList();
           ObservableList<String> sessionGrouplist2 = FXCollections.observableArrayList();
          String sql="select GroupNo, SubGroupNo from studentgroups";
          pst=conn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          
          while(rs.next()){
              sessionGrouplist1.add(rs.getString("GroupNo"));
              sessionGrouplist2.add(rs.getString("SubGroupNo"));
          }
          
          groupId.setItems(sessionGrouplist1);
          subgId.setItems(sessionGrouplist2);
          
        } catch (SQLException ex) {
            Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
    
    //fill group combo
    public void displaySessions(){
        
      //conn = getConnection();
      try {
          ObservableList<String> sessionIdlist = FXCollections.observableArrayList();
          String sql="select sessionID from sessions";
          pst=conn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          
          while(rs.next()){
              sessionIdlist.add(rs.getString("sessionID"));
          }
          ssId.setItems(sessionIdlist);
          
        } catch (SQLException ex) {
            Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void displaySessions_3(){
        
      //conn = getConnection();
      try {
          ObservableList<String> sessionIdlist = FXCollections.observableArrayList();
          String sql="select sessionID from sessions";
          pst=conn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          
          while(rs.next()){
              sessionIdlist.add(rs.getString("sessionID"));
          }
          sesID.setItems(sessionIdlist);
          
        } catch (SQLException ex) {
            Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //fill tab2 sessionid
     
    //fill group combo
    public void displaySessionRoom(){
        
      //conn = getConnection();
      try {
          ObservableList<String> sessionlist = FXCollections.observableArrayList();
          String sql="select RoomName from tbllocation";
          pst=conn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          
          while(rs.next()){
              sessionlist.add(rs.getString("RoomName"));
          }
          sessionRoom.setItems(sessionlist);
        } catch (SQLException ex) {
            Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
    //set days into combo
        ObservableList<String> list1 = FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday");
        
     //handling tab2

    @FXML
    private JFXDatePicker dfrom;

    @FXML
    private JFXDatePicker dto;

    @FXML
    private JFXTimePicker stTime;

    @FXML
    private JFXTimePicker edtime;

    @FXML
    private Button btn2_add;

    @FXML
    private TableView<Tab2_notavailableRoom> tblview2;

    @FXML
    private TableColumn<Tab2_notavailableRoom, String> colrid;

    @FXML
    private TableColumn<Tab2_notavailableRoom, String> coldfrom;

    @FXML
    private TableColumn<Tab2_notavailableRoom,String> coldto;

    @FXML
    private TableColumn<Tab2_notavailableRoom,Time> colstart;

    @FXML
    private TableColumn<Tab2_notavailableRoom,Time> coletime;
    
     @FXML
    private TableColumn<Tab2_notavailableRoom,Integer> colLid;


    @FXML
    private Button btn2_update;

    @FXML
    private Button btn2_del;

    @FXML
    private Button btn2_view;
    
    
    public ObservableList<Tab2_notavailableRoom> getNotAvailableRoom(){
    
        ObservableList<Tab2_notavailableRoom> roomList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        String query = "SELECT* FROM notavailablelocations";
        Statement st ;
        ResultSet rs;
        
        try{
        
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Tab2_notavailableRoom location;
        
        while(rs.next()){
            location =  new Tab2_notavailableRoom(rs.getInt("id"),rs.getString("roomName"),rs.getString("datefrom"),rs.getString("dateto"),rs.getTime("startT"),rs.getTime("endT"));
            roomList.add(location);
        }
        }catch(Exception ex){
        ex.printStackTrace();
        
        }
        return roomList;
    }
    //read data
    public void showNotAvailableLocation(){
    ObservableList<Tab2_notavailableRoom> roomlist = getNotAvailableRoom();
    
    colLid.setCellValueFactory(new PropertyValueFactory<>("id")); //numberofDays came from the workshedule.java overloaded constructor parameters...
    colrid.setCellValueFactory(new PropertyValueFactory<>("room"));
    coldfrom.setCellValueFactory(new PropertyValueFactory<>("dfrom"));
    coldto.setCellValueFactory(new PropertyValueFactory<>("dto"));
    colstart.setCellValueFactory(new PropertyValueFactory<>("start"));
    coletime.setCellValueFactory(new PropertyValueFactory<>("end"));
     
    tblview2.setItems(roomlist);
    clearFields2();
    
    }
    
    public void getSelected_tab2(MouseEvent event){
        int index = -1;
        index = tblview2.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
    
        sessionRoom.setValue(colrid.getCellData(index));
        dfrom.setValue(LocalDate.parse(coldfrom.getCellData(index)));
        dto.setValue(LocalDate.parse(coldto.getCellData(index)));
        stTime.setValue(colstart.getCellData(index).toLocalTime());
        edtime.setValue(coletime.getCellData(index).toLocalTime());
        
    }
    
    //insert a record
    private void insertRecord_tab2(){
        
        //conn = getConnection();
        String query = "INSERT INTO notavailablelocations(roomName,datefrom,dateto,startT,endT) VALUES(?,?,?,?,?)";

        if(validateGui_tab2()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully inserted Record");
        Optional <ButtonType> action = alert.showAndWait();
        
        
        if(action.get() == ButtonType.OK){
        try{

            pst = conn.prepareStatement(query);
            pst.setString(1,sessionRoom.getValue());
            pst.setString(2, dfrom.getValue().toString());
            pst.setString(3, dto.getValue().toString());
            pst.setString(4, stTime.getValue().toString());
            pst.setString(5, edtime.getValue().toString());
            pst.execute();
            clearFields2();
            showNotAvailableLocation();
            
        }catch(Exception e){

                e.printStackTrace();
        }
        }
        }
    }
    //edit record
    public void Edit_tab2(){
        
        Tab2_notavailableRoom selected = tblview2.getSelectionModel().getSelectedItem();
        int ID = selected.getId();
        
        if(validateGui_tab2()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Updated Record");
        Optional <ButtonType> action = alert.showAndWait();
        
        if(validateGui_tab2()){    
        if(action.get() == ButtonType.OK){
        try{
           // conn = getConnection();
            String value1 = sessionRoom.getValue();
            LocalDate value2 = LocalDate.parse(dfrom.getValue().toString());
            LocalDate value3 = LocalDate.parse(dto.getValue().toString());
            String value4 = stTime.getValue().toString();
            String value5 = edtime.getValue().toString();

            
            String query = "UPDATE notavailablelocations SET roomName= '"+value1+"' ,datefrom= '"+value2+"' ,dateto= '"+value3+"' ,startT= '"+value4+"' ,endT= '"+value5+"'  WHERE id= '"+ID+"'";
            pst = conn.prepareStatement(query);
            pst.execute();
            clearFields2();
            showNotAvailableLocation();
           
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }}
        }
    }
    
    public void delete_tab2(){
       
            Tab2_notavailableRoom selected = tblview2.getSelectionModel().getSelectedItem();
            int ID = selected.getId();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are You sure Do you Want to Delete the Record?");
            Optional <ButtonType> action = alert.showAndWait();
           
            if(action.get() == ButtonType.OK){
            try{
            //conn = getConnection();
            String sql = "DELETE FROM notavailablelocations WHERE id = '"+ID+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            clearFields2();
            showNotAvailableLocation();
            }catch(Exception e){
                e.printStackTrace();
                
            }
            }
    }
    
    //clear fields
    public void clearFields2(){
       sessionRoom.setValue("");
       dfrom.getEditor().setText("");
       dto.getEditor().setText("");
       stTime.getEditor().setText("");
       edtime.getEditor().setText("");
       
    }
    
    //tab3
    @FXML
    private JFXDatePicker preferdate;

    @FXML
    private JFXTimePicker startt;

    @FXML
    private JFXTimePicker endt;
    
    @FXML
    private ComboBox<String> sesID;

    @FXML
    private TableView<Tab3_AddPreferTime> tblview3;

    @FXML
    private Button btn3_add;
    
    @FXML
    private Button btn3_update;

    @FXML
    private Button btn3_del;

    @FXML
    private Button btn3_view;
    
    @FXML
    private TableColumn<Tab3_AddPreferTime,String> colId3;

    @FXML
    private TableColumn<Tab3_AddPreferTime,String> colpreferday;

    @FXML
    private TableColumn<Tab3_AddPreferTime,Time> colstt;

    @FXML
    private TableColumn<Tab3_AddPreferTime,Time> colett;
    
    @FXML
    private TableColumn<Tab3_AddPreferTime,String> colsessID;

    
    public ObservableList<Tab3_AddPreferTime> getpreferTime(){
    
        ObservableList<Tab3_AddPreferTime> timelist = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        String query = "SELECT* FROM prefertime";
        Statement st ;
        ResultSet rs;
        
        try{
        
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Tab3_AddPreferTime prefertime;
        
        while(rs.next()){
           
           prefertime =  new Tab3_AddPreferTime(rs.getInt("id"),rs.getString("sessionID"),rs.getString("preferDay"),rs.getString("preferDate"),rs.getTime("startT"),rs.getTime("endT"));
            timelist.add(prefertime);
        }
        }catch(Exception ex){
        ex.printStackTrace();
        
        }
        return timelist;
    }
    //read data
    public void showAddedTimes(){
    ObservableList<Tab3_AddPreferTime> timelist = getpreferTime();
    
    colId3.setCellValueFactory(new PropertyValueFactory<>("id")); 
    colsessID.setCellValueFactory(new PropertyValueFactory<>("session"));
    colpreferday.setCellValueFactory(new PropertyValueFactory<>("day"));
    coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
    colstt.setCellValueFactory(new PropertyValueFactory<>("starttime"));
    colett.setCellValueFactory(new PropertyValueFactory<>("endtime"));
     
    tblview3.setItems(timelist);
    clearFields3();
    
    }
    
    
    public void getSelected_tab3(MouseEvent event){
        int index = -1;
        index = tblview3.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
    
        sesID.setValue(colsessID.getCellData(index));
        day.setValue(colpreferday.getCellData(index));
        preferdate.setValue(LocalDate.parse(coldate.getCellData(index)));
        startt.setValue(colstt.getCellData(index).toLocalTime());
        endt.setValue(colett.getCellData(index).toLocalTime());
        
    }
    
    //insert a record
    private void insertRecord_tab3(){
        
        //conn = getConnection();
        String query = "INSERT INTO prefertime(sessionID,preferDay,preferDate,startT,endT) VALUES(?,?,?,?,?)";

        if(validateGui_tab3()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully inserted Record");
        Optional <ButtonType> action = alert.showAndWait();
        
        
        if(action.get() == ButtonType.OK){
        try{

            pst = conn.prepareStatement(query);
            pst.setString(1,sesID.getValue());
            pst.setString(2,day.getValue());
            pst.setString(3, preferdate.getValue().toString());
            pst.setString(4, startt.getValue().toString());
            pst.setString(5, endt.getValue().toString());
            pst.execute();
            clearFields3();
            showAddedTimes();
            
        }catch(Exception e){

                e.printStackTrace();
        }
        }
        }
    }
    //edit record
    public void Edit_tab3(){
        
        Tab3_AddPreferTime selected = tblview3.getSelectionModel().getSelectedItem();
        int ID = selected.getId();
        
        if(validateGui_tab3()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Updated Record");
        Optional <ButtonType> action = alert.showAndWait();
          
        
        if(action.get() == ButtonType.OK){
        try{
            //conn = getConnection();
            String value5=sesID.getValue();
            String value1 = day.getValue();
            LocalDate value2 = LocalDate.parse(preferdate.getValue().toString());
            String value3 = startt.getValue().toString();
            String value4 = endt.getValue().toString();

            
            String query = "UPDATE prefertime SET sessionID =  '"+value5+"', preferDay= '"+value1+"' ,preferDate= '"+value2+"' ,startT= '"+value3+"' ,endT= '"+value4+"'  WHERE id= '"+ID+"'";
            pst = conn.prepareStatement(query);
            pst.execute();
            clearFields3();
           showAddedTimes();
           
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }}
    }
    
    public void delete_tab3(){
       
            Tab3_AddPreferTime selected = tblview3.getSelectionModel().getSelectedItem();
            int ID = selected.getId();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are You sure Do you Want to Delete the Record?");
            Optional <ButtonType> action = alert.showAndWait();
           
            if(action.get() == ButtonType.OK){
            try{
           // conn = getConnection();
            String sql = "DELETE FROM prefertime WHERE id = '"+ID+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            clearFields3();
            showAddedTimes();
            }catch(Exception e){
                e.printStackTrace();
                
            }
            }
    }
    
    //clear fields
    public void clearFields3(){
        sesID.setValue("");
       day.setValue("");
       preferdate.getEditor().setText("");
       startt.getEditor().setText("");
       endt.getEditor().setText("");
       
    }
    
    private boolean validateGui_tab1(){
    
        if(lecId.getSelectionModel().isEmpty()|groupId.getSelectionModel().isEmpty()|subgId.getSelectionModel().isEmpty()|ssId.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select required values.");
            alert.showAndWait();
            
            return false;
        }
         if(not_a_date.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select the not available date");
            alert.showAndWait();
            
            return false;
        
         }
         if(tfrom.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select not available from time");
            alert.showAndWait();
            
            return false;
        }
         if(tto.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select not available to time");
            alert.showAndWait();
            
            return false;
        }
    
        return true;
        
 
    }
    
    private boolean validateGui_tab2(){
    
        if(sessionRoom.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select session room ID.");
            alert.showAndWait();
            
            return false;
        }
         if(dfrom.getEditor().getText().isEmpty() | dto.getEditor().getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select the not avaialable date from and date to");
            alert.showAndWait();
            
            return false;
        
         }
         if(stTime.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select not available from time");
            alert.showAndWait();
            
            return false;
        }
         if(edtime.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select not available to time");
            alert.showAndWait();
            
            return false;
        }
    
        return true;
        
 
    }
    
    private String returnNotAvailableDate(){
    
       // conn = getConnection();
        String value="";
        String timeValue = "Select* from notavailablelecture where sessionID=?";
        try {
            pst=conn.prepareStatement(timeValue);
            ResultSet rs = pst.executeQuery();
          
          while(rs.next()){
              value = rs.getString(6);
          }
          
        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
          
    }
    
    private boolean validateGui_tab3(){
        
        if(sesID.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select the Session.");
            alert.showAndWait();
            
            return false;
        }
    
        if(day.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select the day.");
            alert.showAndWait();
            
            return false;
        }
        if(preferdate.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select the date");
            alert.showAndWait();
            
            return false;
        
         }
         if(startt.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select start time");
            alert.showAndWait();
            
            return false;
        }
         if(endt.getEditor().getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select end time");
            alert.showAndWait();
            
            return false;
        }
      //  String val=returnNotAvailableDate(sesID.getValue());
        if(preferdate.getEditor().getText().equals(returnNotAvailableDate())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select available day.Lecturer is not avaialable.");
            alert.showAndWait();
            
            return false;
        }
         
    
        return true;
        
 
    }
    
}
