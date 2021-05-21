/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

import helpers.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author user
 */
public class TimetableController implements Initializable {

    @FXML
    private ComboBox<String> T_lecture;

    @FXML
    private Button btngen_tab2;

    @FXML
    private TableColumn<TimeTable, String> colSlot_2;

    @FXML
    private Button btngen_tab3;

    @FXML
    private TableColumn<TimeTable, String> colSlot_tab3;

    @FXML
    private Button btngen_tab1;

    @FXML
    private TableView<TimeTable> tbltime_3;

    @FXML
    private TableView<TimeTable> tbltime_2;

    @FXML
    private TableColumn<TimeTable, String> colmon;

    @FXML
    private TableColumn<TimeTable, String> colltue;

    @FXML
    private TableColumn<TimeTable, String> colwed;

    @FXML
    private TableColumn<TimeTable, String> colthur;

    @FXML
    private TableColumn<TimeTable, String> colfri;

    @FXML
    private ComboBox<String> T_location;
    @FXML
    private TableColumn<TimeTable, String> colMon_tab3;
    @FXML
    private TableColumn<TimeTable, String> colTue_tab3;
    @FXML
    private TableColumn<TimeTable, String> colWed_tab3;
    @FXML
    private TableColumn<TimeTable, String> colThur_tab3;
    @FXML
    private TableColumn<TimeTable, String> colFri_tab3;

    @FXML
    private TableColumn<TimeTable, String> colMonday;

    @FXML
    private TableColumn<TimeTable, String> colTuesday;

    @FXML
    private TableColumn<TimeTable, String> colWednesday;

    @FXML
    private TableColumn<TimeTable, String> colThursday;

    @FXML
    private TableColumn<TimeTable, String> colFriday;

    @FXML
    private ComboBox<String> ddGroup;

    Connection conn = DbConnect.connectDB();

    String sessionRoom;
    String sessionID;
    String preferDay;
    String roomNumber;
    String sessionByLocation;
    String startT;
    String endT;
    String time;
    String lecturer;
    String groups;
    @FXML
    private Button btnPrint;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//     labelslot.setText(generateSlot().toString());
        displayLecturers();//fill combobox tab1
            generateSlot_3();

        // viewSessions();
//           colmon.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(getSession()));
    }

    @FXML
    private void handlebtnTimetable(ActionEvent event) {

        if (event.getSource() == btngen_tab1) {
            if (validateTab1()) {
                //generateSlot();
                //viewSessions_1();
                generateLecturerTimetable();
            }
        } else if (event.getSource() == btngen_tab2) {
            generateSlot_2();
            viewGroupSessions();
        } else if (event.getSource() == btngen_tab3) {
            viewSessions();
        }

    }

    //lectures combobox
    @FXML
    public void displayLecturers() {

        //conn = getConnection();
        try {
            ObservableList<String> leclist = FXCollections.observableArrayList();
            String sql = "select name from lecturers";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                leclist.add(rs.getString("name"));
            }
            T_lecture.setItems(leclist);

        } catch (SQLException ex) {
            Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //fill location combobox
    @FXML
    public void displayLocations() {
        ObservableList<String> locationsList = FXCollections.observableArrayList();

        // conn = getConnection();
        try {
            String sql = "select s.sessionRoom from allsessionsrooms s, prefertime p where s.sessionID = p.sessionID";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                locationsList.add(rs.getString("sessionRoom"));
            }
            T_location.setItems(locationsList);

        } catch (SQLException ex) {
            //Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //variable declaration
    // Connection conn;
    PreparedStatement pst;

//    public Connection getConnection(){
//    
//        
//        try{
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root","");
//            return conn;
//        }catch(Exception ex){
//            System.out.println("Error: "+ex.getMessage());
//            return null;
//        }
//    }
    @FXML
    private TableColumn<TimeTable, String> colSlot;

    @FXML
    private TableView<TimeTable> tbltime;

    private String getStartTime() {
        String value = "";
        // conn = getConnection();
        String timeValue = "Select* from ws where id= '" + getID() + "'";
        try {
            pst = conn.prepareStatement(timeValue);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                value = rs.getString(4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;

    }

    private int getSlotcount() {
        int slot = 0;
        // conn = getConnection();

        String timeValue = "Select * from ws where id= '" + getID() + "'";
        try {
            pst = conn.prepareStatement(timeValue);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                slot = rs.getInt(6);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slot;

    }

    private int getID() {
        int val = 0;
        //conn = getConnection();

        String timeValue = "Select * from ws ";
        try {
            pst = conn.prepareStatement(timeValue);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                val = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }

    private int getInterval() {
        int val = 0;
        // conn = getConnection();

        String sql = "Select * from ws where id= '" + getID() + "'";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                val = rs.getInt(7);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;

    }

    private String getSession() {
        String val = "";
        //conn = getConnection();

        String sql = "Select * from sessions where Lecturer1 = '" + T_lecture.getValue() + "'";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                val = rs.getString(12);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }

    private String gettstart() {
        String val = "";
        //conn = getConnection();

        String sql = "Select * from prefertime where sessionID = '" + getSession() + "'";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                val = rs.getString(5);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }

    private String getpday() {
        String val = "";
        // conn = getConnection();

        String sql = "Select * from prefertime where sessionID = '" + getSession() + "'";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                val = rs.getString(3);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }

    private boolean validateTab1() {
        if (T_lecture.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation");
            alert.setHeaderText(null);
            alert.setContentText("Please select the lecturer name.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    //generate timeslot for tab1
    private void generateSlot() {
        ObservableList<TimeTable> slotlist = FXCollections.observableArrayList();
        // ObservableList<TimeTable> sessionlist = getSession();

        String starttime = getStartTime();
        int slotcount = getSlotcount();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(sdf.parse(starttime));

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(startCalendar.getTime());

            endCalendar.add(Calendar.HOUR_OF_DAY, 24 - startCalendar.get(Calendar.HOUR_OF_DAY));

            SimpleDateFormat slotTime = new SimpleDateFormat("hh:mma");

            TimeTable tslot;

            for (int i = 0; i < 9; i++) {

                String slotStartTime = slotTime.format(startCalendar.getTime());

                startCalendar.add(Calendar.MINUTE, slotcount);
                String slotEndTime = slotTime.format(startCalendar.getTime());

                String day = slotStartTime + " - " + slotEndTime;

                if (i == getInterval()) {
                    day = "Interval";
                    tslot = new TimeTable(day);

                    slotlist.add(tslot);
                    colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));

                } else {
                    tslot = new TimeTable(day);

                    slotlist.add(tslot);
                    colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));

                    tbltime.setItems(slotlist);

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void generateLecturerTimetable() {
        
        ObservableList<TimeTable> slotlist = FXCollections.observableArrayList();
        
        String day1 = null;
        String day2 = null;
        String day3 = null;
        String day4 = null;
        String day5 = null;
        lecturer = T_lecture.getSelectionModel().getSelectedItem();

        String selectQuery = "SELECT  p.preferDay, p.startT, p.endT, p.sessionID, sr.sessionRoom FROM prefertime p, sessions s, sessionroom sr where p.sessionID = s.sessionID and s.sessionID = sr.sessionID and s.Lecturer1 = '" + lecturer + "' ";

        try {
            
            pst = conn.prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                startT = rs.getTime("p.startT").toString();
                endT = rs.getTime("p.endT").toString();
                time = startT.substring(0, startT.length() - 3) + " - " + endT.substring(0, endT.length() - 3);
                sessionID = rs.getString("p.sessionID");
                roomNumber = rs.getString("sr.sessionroom");
                preferDay = rs.getString("p.preferDay");
                sessionByLocation = sessionID + ", \n" + roomNumber+", \n"+ time;

                if (preferDay.equals("Monday")) {
                    day1 = sessionByLocation;
                    colmon.setCellValueFactory(new PropertyValueFactory("day1"));
                }
                if (preferDay.equals("Tuesday")) {
                    day2 = sessionByLocation;
                    colltue.setCellValueFactory(new PropertyValueFactory("day2"));
                }
                if (preferDay.equals("Wednesday")) {
                    day3 = sessionByLocation;
                    colwed.setCellValueFactory(new PropertyValueFactory("day3"));
                }
                if (preferDay.equals("Thursday")) {
                    day4 = sessionByLocation;
                    colthur.setCellValueFactory(new PropertyValueFactory("day4"));
                }
                if (preferDay.equals("Friday")) {
                    day5 = sessionByLocation;
                    colfri.setCellValueFactory(new PropertyValueFactory("day5"));
                }
                      
            }

            
            String starttime = getStartTime();
            int slotcount = getSlotcount();

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            Calendar startCalendar = Calendar.getInstance();
            try {
                startCalendar.setTime(sdf.parse(starttime));
            } catch (ParseException ex) {
                Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(startCalendar.getTime());

            endCalendar.add(Calendar.HOUR_OF_DAY, 24 - startCalendar.get(Calendar.HOUR_OF_DAY));

            SimpleDateFormat slotTime = new SimpleDateFormat("hh:mma");

            TimeTable tslot, tslot2, tslot3;
            String day = "";
            for (int i = 0; i < 9; i++) {

                String slotStartTime = slotTime.format(startCalendar.getTime());

                startCalendar.add(Calendar.MINUTE, slotcount);
                String slotEndTime = slotTime.format(startCalendar.getTime());

                day = slotStartTime + " - " + slotEndTime;

                if (i == getInterval()) {
                    day = "Interval";
                    tslot = new TimeTable(day);

                    slotlist.add(tslot);
                    colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));

                } else {
                    tslot = new TimeTable(day);

                    slotlist.add(tslot);
                    colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));

                }
            }

            tslot2 = new TimeTable(day1, day2, day3, day4, day5);
            slotlist.add(tslot2);
            tbltime.setItems(slotlist);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    //generate time slot for tab2
    private void generateSlot_2() {
        ObservableList<TimeTable> slotlist = FXCollections.observableArrayList();

        String starttime = getStartTime();
        int slotcount = getSlotcount();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(sdf.parse(starttime));

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(startCalendar.getTime());

            endCalendar.add(Calendar.HOUR_OF_DAY, 24 - startCalendar.get(Calendar.HOUR_OF_DAY));

            SimpleDateFormat slotTime = new SimpleDateFormat("hh:mma");

            TimeTable tslot, lecsession;

            for (int i = 0; i < 9; i++) {
                String slotStartTime = slotTime.format(startCalendar.getTime());

                startCalendar.add(Calendar.MINUTE, slotcount);
                String slotEndTime = slotTime.format(startCalendar.getTime());

                String day = slotStartTime + " - " + slotEndTime;
                if (i == getInterval()) {
                    day = "Interval";
                    tslot = new TimeTable(day);

                    slotlist.add(tslot);
                    colSlot_2.setCellValueFactory(new PropertyValueFactory<>("slot"));

                } else {
                    tslot = new TimeTable(day);

                    slotlist.add(tslot);
                    colSlot_2.setCellValueFactory(new PropertyValueFactory<>("slot"));
                    tbltime_2.setItems(slotlist);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
 @FXML
    public void displayGroups() {
        ObservableList<String> GroupList = FXCollections.observableArrayList();

        //conn = getConnection();
        try {
            String sql = "select GroupID from studentgroups";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                GroupList.add(rs.getString("GroupID"));
            }
            ddGroup.setItems(GroupList);

        } catch (SQLException ex) {
            //Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public ObservableList<TimeTable> generateGroupsTimetable() {
        ObservableList<TimeTable> sessionsList = FXCollections.observableArrayList();
        TimeTable session;
        String day1 = null;
        String day2 = null;
        String day3 = null;
        String day4 = null;
        String day5 = null;
        groups = ddGroup.getSelectionModel().getSelectedItem();

        String selectQuery = "SELECT  p.preferDay, p.startT, p.endT, p.sessionID FROM prefertime p INNER JOIN sessions s ON p.sessionID = s.sessionID where s.GroupID = '" + ddGroup.getValue() + "' ";

        try {
            // String sql="select preferDay from prefertime";
            pst = conn.prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                startT = rs.getTime("p.startT").toString();
                endT = rs.getTime("p.endT").toString();
                time = startT.substring(0, startT.length() - 3) + " - " + endT.substring(0, endT.length() - 3);
                sessionID = rs.getString("p.sessionID");

                preferDay = rs.getString("p.preferDay");
                sessionByLocation = sessionID + ", \n" + roomNumber;
                if (preferDay.equals("Monday")) {
                    day1 = sessionByLocation;
                }
                if (preferDay.equals("Tuesday")) {
                    day2 = sessionByLocation;
                }
                if (preferDay.equals("Wednesday")) {
                    day3 = sessionByLocation;
                }
                if (preferDay.equals("Thursday")) {
                    day4 = sessionByLocation;
                }
                if (preferDay.equals("Friday")) {
                    day5 = sessionByLocation;
                }

                session = new TimeTable(time, day1, day2, day3, day4, day5);
                sessionsList.add(session);

            }

        } catch (SQLException ex) {
            //Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessionsList;
    }

    public void viewGroupSessions() {
        ObservableList<TimeTable> list = generateGroupsTimetable();
        colSlot_2.setCellValueFactory(new PropertyValueFactory("slot"));
        colMonday.setCellValueFactory(new PropertyValueFactory("day1"));
        colTuesday.setCellValueFactory(new PropertyValueFactory("day2"));
        colWednesday.setCellValueFactory(new PropertyValueFactory("day3"));
        colThursday.setCellValueFactory(new PropertyValueFactory("day4"));
        colFriday.setCellValueFactory(new PropertyValueFactory("day5"));
        tbltime_2.setItems(list);

    }

   

    //generate time slot for tab3
    private void generateSlot_3() {
        ObservableList<TimeTable> slotlist = FXCollections.observableArrayList();

        String starttime = getStartTime();
        int slotcount = getSlotcount();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(sdf.parse(starttime));

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(startCalendar.getTime());

            endCalendar.add(Calendar.HOUR_OF_DAY, 24 - startCalendar.get(Calendar.HOUR_OF_DAY));

            SimpleDateFormat slotTime = new SimpleDateFormat("hh:mma");

            TimeTable tslot;

            for (int i = 0; i < 9; i++) {
                String slotStartTime = slotTime.format(startCalendar.getTime());

                startCalendar.add(Calendar.MINUTE, slotcount);
                String slotEndTime = slotTime.format(startCalendar.getTime());

                String day = slotStartTime + " - " + slotEndTime;
             
                if (i == getInterval()) {
                    day = "Interval";
                    tslot = new TimeTable(day);

                    slotlist.add(tslot);
                    colSlot_tab3.setCellValueFactory(new PropertyValueFactory<>("slot"));

                } else {
                    tslot = new TimeTable(day);
                    slotlist.add(tslot);
                    colSlot_tab3.setCellValueFactory(new PropertyValueFactory<>("slot"));
                    tbltime_3.setItems(slotlist);

                }
            }
            //viewSessions();   

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public ObservableList<TimeTable> generateLocationTimetable() {
        ObservableList<TimeTable> sessionsList = FXCollections.observableArrayList();
        TimeTable session;
        String day1 = null;
        String day2 = null;
        String day3 = null;
        String day4 = null;
        String day5 = null;
        sessionRoom = T_location.getSelectionModel().getSelectedItem();

        String selectQuery = "SELECT  p.preferDay, p.startT, p.endT, p.sessionID, s.sessionRoom FROM prefertime p INNER JOIN allsessionsrooms s ON p.sessionID = s.sessionID where s.sessionRoom = '" + sessionRoom + "' ";

        try {
            // String sql="select preferDay from prefertime";
            pst = conn.prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                startT = rs.getTime("p.startT").toString();
                endT = rs.getTime("p.endT").toString();
                time = startT.substring(0, startT.length() - 3) + " - " + endT.substring(0, endT.length() - 3);
                sessionID = rs.getString("p.sessionID");
                roomNumber = rs.getString("s.sessionRoom");
                preferDay = rs.getString("p.preferDay");
                sessionByLocation = sessionID.replaceFirst("-", "\n") + " \n" + roomNumber;
                if (preferDay.equals("Monday")) {
                    day1 = sessionByLocation;
                }
                if (preferDay.equals("Tuesday")) {
                    day2 = sessionByLocation;
                }
                if (preferDay.equals("Wednesday")) {
                    day3 = sessionByLocation;
                }
                if (preferDay.equals("Thursday")) {
                    day4 = sessionByLocation;
                }
                if (preferDay.equals("Friday")) {
                    day5 = sessionByLocation;
                }

                session = new TimeTable(time, day1, day2, day3, day4, day5);
                sessionsList.add(session);

            }

        } catch (SQLException ex) {
            //Logger.getLogger(ManageNotAvailableTimesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessionsList;
    }

    public void viewSessions() {
        //generateSlot_3();
        ObservableList<TimeTable> list = generateLocationTimetable();
        colSlot_tab3.setCellValueFactory(new PropertyValueFactory("slot"));
        colMon_tab3.setCellValueFactory(new PropertyValueFactory("day1"));
        colTue_tab3.setCellValueFactory(new PropertyValueFactory("day2"));
        colWed_tab3.setCellValueFactory(new PropertyValueFactory("day3"));
        colThur_tab3.setCellValueFactory(new PropertyValueFactory("day4"));
        colFri_tab3.setCellValueFactory(new PropertyValueFactory("day5"));
        tbltime_3.setItems(list);

    }

    @FXML
    private TabPane getLecStdLoc;

    @FXML
    private Tab lecturersTab;

    @FXML
    private Tab locationTab;

    @FXML
    private Tab studentTab;

    @FXML
    private void switchTab(MouseEvent event) {
         if (getLecStdLoc.getSelectionModel().getSelectedItem().equals(locationTab)) {
            displayLocations();
        } else if (getLecStdLoc.getSelectionModel().getSelectedItem().equals(lecturersTab)) {
            displayLecturers();

        } else if (getLecStdLoc.getSelectionModel().getSelectedItem().equals(studentTab)) {
            displayGroups();
        }
    }

    @FXML
    private void viewDetails(MouseEvent event) {
                 
    }

   
               
       
}
