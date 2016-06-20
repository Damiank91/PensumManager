package Controller.ScheduleSubject;


import Controller.Main;
import Model.ConnectSql.*;
import Model.Employee.Employee;
import Model.ScheduleSubject.ScheduleSubject;
import View.MessagePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerCreateSchedule implements Initializable {


    @FXML
    private ChoiceBox<String> choiceBoxDay;

    @FXML
    private ChoiceBox<String> choiceBoxRoom;

    @FXML
    private Label employeeName;

    @FXML
    private ChoiceBox<String> choiceBoxTime;

    @FXML
    private Button btnSave;

    @FXML
    private GridPane gridEmploye;

    @FXML
    private ChoiceBox<String> choiceBoxEmployee;

    @FXML
    private GridPane gridRoom;

    @FXML
    private Button btnDelete;

    @FXML
    private ChoiceBox<String> choiceBoxSubject;

    @FXML
    private Button btnExit;


    Label[][] employeeLabel;
    Label[][] roomLabel;
    DriverSqlEmployee driverSqlEmployee = new DriverSqlEmployee();
    DriverSqlScheduleSubject driverSqlScheduleSubject = new DriverSqlScheduleSubject();
    DriverSqlRoom driverSqlRoom = new DriverSqlRoom();
    DriverSqlSubject driverSqlSubject = new DriverSqlSubject();
    DriverSqlDaysWeek driverSqlDaysWeek = new DriverSqlDaysWeek();
    DriverSqlTimeWork driverSqlTimeWork = new DriverSqlTimeWork();
    MessagePanel messagePanel = new MessagePanel();
    ArrayList<String> employeeList = null;
    ArrayList<String> roomList = null;
    ObservableList<String> employeeObservableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initEmployeeLabel();
        initRoomLabel();
        initEmployeeChoiceBox();
        initRoomChoiceBox();
        choiceBoxEmployee.setOnAction(event -> addEmployeeToLabel(choiceBoxEmployee.getValue()));
        choiceBoxRoom.setOnAction(event -> addRoomToLabel(choiceBoxRoom.getValue()));
        btnSave.setOnAction(event -> saveEmployeeSchedule());
        btnExit.setOnAction(event -> backToScheduleMain());
        btnDelete.setOnAction(event -> deleteChoiceSchedule());
    }

    private void initEmployeeLabel(){
        employeeLabel = new Label[6][8];
        ObservableList<Node> children = gridEmploye.getChildren();
        int t = 0;
        for(int i=0; i < 6; i++){
            for(int j=0; j < 8; j++){
                employeeLabel[i][j]= (Label) children.get(t);
                t++;

            }
        }
    }

    private void initRoomLabel(){
        roomLabel = new Label[6][8];
        ObservableList<Node> children = gridRoom.getChildren();
        int t = 0;
        for(int i=0; i < 6; i++){
            for(int j=0; j < 8; j++){
                roomLabel[i][j]= (Label) children.get(t);
                t++;
            }
        }
    }

    public void initEmployeeChoiceBox(){
        employeeList = new ArrayList<>();
        choiceBoxEmployee.setTooltip(new Tooltip("Wybierz pracownika"));
        employeeList = driverSqlEmployee.getEmployee();
        employeeObservableList = FXCollections.observableList(employeeList);
        choiceBoxEmployee.setItems(employeeObservableList);
    }



    private void initRoomChoiceBox(){
        roomList = new ArrayList<>();
        choiceBoxRoom.setTooltip(new Tooltip("Wybierz sale"));
        roomList = driverSqlRoom.getRoomNameList();
        ObservableList<String> roomObservableList = FXCollections.observableList(roomList);
        choiceBoxRoom.setItems(roomObservableList);

    }




    private void addEmployeeToLabel(String choiceEmployee){
        addNameToLabel(choiceEmployee);
        int idEmployee = getEmployeeIdByNameSurname(choiceEmployee);
        ArrayList<ScheduleSubject> scheduleSubjectArrayList = driverSqlScheduleSubject.getScheduleSubjectForEmployeeById(idEmployee);
        addSubjectToTable(scheduleSubjectArrayList);
        addSubjectToChoiceBox(idEmployee);
        addDaysWeekToChoiceBox();
        addTimeWorkToChoiceBox();

    }

    private void addRoomToLabel(String choiceRoom){
        int idRoom = driverSqlRoom.getIdRoomByName(choiceRoom);
        ArrayList<ScheduleSubject> scheduleRoomArrayList = driverSqlScheduleSubject.getScheduleSubjectForRoomById(idRoom);
        addRoomToTable(scheduleRoomArrayList);
    }

    private void addNameToLabel(String name){
        employeeName.setText(name);
    }

    private int getEmployeeIdByNameSurname(String choiceEmployee){
        int idEmployee = driverSqlEmployee.getEmployeeByNameSurname(choiceEmployee);
        return idEmployee;
    }

    private void addRoomToTable(ArrayList<ScheduleSubject> roomValue){
        clearRoomTable();
        for(int i=0; i <roomValue.size(); i++){
            int xPosition = roomValue.get(i).getIdDaysWeek();
            int yPosition = roomValue.get(i).getIdTimeWork();
            String subjectName = driverSqlSubject.getSubjectNameById(roomValue.get(i).getIdSubject());
            Employee employee = driverSqlEmployee.getEmployeeById(roomValue.get(i).getIdEmployee());
            String value = subjectName + " -" + employee.getName() + " " + employee.getSurname();
            roomLabel[xPosition][yPosition].setText(value);
        }

    }

    private void addSubjectToTable(ArrayList<ScheduleSubject> scheduleSubjects){
        clearEmployeeTable();
        for(int i=0; i <scheduleSubjects.size(); i++){
            int xPosition = scheduleSubjects.get(i).getIdDaysWeek();
            int yPosition = scheduleSubjects.get(i).getIdTimeWork();
            String subjectName = driverSqlSubject.getSubjectNameById(scheduleSubjects.get(i).getIdSubject());
            String roomName = driverSqlRoom.getRoomById(scheduleSubjects.get(i).getIdRoom());

            employeeLabel[xPosition][yPosition].setText(subjectName + "-" + roomName);
        }

    }

    private void addSubjectToChoiceBox(int idEmployee){
        ArrayList<String> subjectArrayList = driverSqlSubject.getEmployeeSubjectByIdEmployee(idEmployee);
        addListToChoiceBox(subjectArrayList);
    }

    private void addListToChoiceBox(ArrayList<String> subjectList){
        choiceBoxSubject.setTooltip(new Tooltip("Wybierz przedmiot"));
        ObservableList<String> subjectObservableList = FXCollections.observableList(subjectList);
        choiceBoxSubject.setItems(subjectObservableList);
    }

    private void addDaysWeekToChoiceBox(){
        ArrayList<String> daysWeekList = driverSqlDaysWeek.getDayWeekName();
        choiceBoxDay.setTooltip(new Tooltip("Wybierz dzień"));
        ObservableList<String> dayWeekObservableList = FXCollections.observableList(daysWeekList);
        choiceBoxDay.setItems(dayWeekObservableList);
    }

    private void addTimeWorkToChoiceBox(){
        ArrayList<String> timeWorkList  = driverSqlTimeWork.getTimeWorkName();
        choiceBoxTime.setTooltip(new Tooltip("Wybierz godzine"));
        ObservableList<String> timeWorkObservableList = FXCollections.observableList(timeWorkList);
        choiceBoxTime.setItems(timeWorkObservableList);
    }

    private void clearRoomTable(){
        for(int i=1; i < 5; i++){
            for(int j=1; j < 7; j++) {
                roomLabel[i][j].setText("");
            }
        }
    }

    private void clearEmployeeTable(){
        for(int i=1; i < 5; i++){
            for(int j=1; j < 7; j++) {
                employeeLabel[i][j].setText("");
            }
        }
    }

    private void saveEmployeeSchedule(){
        ScheduleSubject scheduleSubject = getChoiceSchedule();
        int pensumEmployee = driverSqlEmployee.getEmployeePensumById(scheduleSubject.getIdEmployee());
        if(!(scheduleSubject.getIdDaysWeek() == 0) && !(scheduleSubject.getIdTimeWork() == 0)) {
            if(checkExistSchedule(scheduleSubject.getIdDaysWeek(), scheduleSubject.getIdTimeWork())){
                if(checkNumberSchedule(scheduleSubject.getIdEmployee(),pensumEmployee)){
                    driverSqlScheduleSubject.saveScheduleSubject(scheduleSubject);
                } else {
                    messagePanel.showErrorMessage("Przekroczono limit godzin dla wybranego pracownika!");
                }

            } else {
                messagePanel.showErrorMessage("Wybrany termin jest już zajęty!");
            }
            addEmployeeToLabel(choiceBoxEmployee.getValue());
            addRoomToLabel(choiceBoxRoom.getValue());
        } else if (scheduleSubject.getIdDaysWeek() == 0){
            messagePanel.showErrorMessage("Nie wybrano dnia");
        } else if (scheduleSubject.getIdTimeWork() == 0){
            messagePanel.showErrorMessage("Nie wybrano godziny");
        }
    }

    private ScheduleSubject getChoiceSchedule(){
        int idEmployee = driverSqlEmployee.getEmployeeByNameSurname(choiceBoxEmployee.getValue());
        int idRoom = driverSqlRoom.getIdRoomByName(choiceBoxRoom.getValue());
        int idSubject = driverSqlSubject.getSubjectNameByName(choiceBoxSubject.getValue());
        int idDay = driverSqlDaysWeek.getDayWeekByName(choiceBoxDay.getValue());
        int idTime = driverSqlTimeWork.getTimeWorkByName(choiceBoxTime.getValue());
        ScheduleSubject scheduleSubject = new ScheduleSubject(idEmployee, idRoom, idSubject, idDay, idTime);
        return scheduleSubject;
    }

    private boolean checkExistSchedule(int idDay, int idTime){
        boolean isNoExist = true;
        int idSchedule = driverSqlScheduleSubject.checkIsExist(idDay, idTime);
        if(idSchedule != 0){
            isNoExist = false;
        }
        return isNoExist;
    }

    private void deleteChoiceSchedule(){
        ScheduleSubject scheduleSubject = getChoiceSchedule();
        int idScheduleSubject = driverSqlScheduleSubject.getIdScheduleByChoice(scheduleSubject);
        if(idScheduleSubject != 0){
            driverSqlScheduleSubject.deleteScheduleSubject(idScheduleSubject);
            addEmployeeToLabel(choiceBoxEmployee.getValue());
            addRoomToLabel(choiceBoxRoom.getValue());
        } else {
            messagePanel.showErrorMessage("Brak wyznaczonego terminu");
        }

    }

    private boolean checkNumberSchedule(int idEmployee, int employeePensum){
        boolean noTooMuch = true;
        int sumEmployeeSchedule = driverSqlScheduleSubject.getSumSchedule(idEmployee);
        if(!((sumEmployeeSchedule*2) < employeePensum)){
            noTooMuch = false;
        }
        return noTooMuch;
    }


    private void backToScheduleMain(){
        Main.setSceneMainWindow();
    }

}
