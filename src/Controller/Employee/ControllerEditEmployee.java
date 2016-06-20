package Controller.Employee;


import Controller.Main;
import Model.ConnectSql.DriverSqlCathedral;
import Model.ConnectSql.DriverSqlEmployee;
import Model.Employee.Employee;
import View.MessagePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEditEmployee implements Initializable {

    @FXML
    private TextField fieldName;

    @FXML
    private ToggleGroup gender;

    @FXML
    private ToggleGroup manager;

    @FXML
    private RadioButton choiceManagerFalse;

    @FXML
    private ChoiceBox<String> choiceCathedral;

    @FXML
    private TextField fieldSurname;

    @FXML
    private DatePicker bornDate;

    @FXML
    private RadioButton choiceManagerTrue;

    @FXML
    private RadioButton choiceGenderFemale;

    @FXML
    private Button btnSave;

    @FXML
    private RadioButton choiceGenderMale;

    @FXML
    private Button btnExit;

    @FXML
    private TextField fieldPensum;

    @FXML
    private ChoiceBox<String> choicePosition;

    @FXML
    private ChoiceBox<String> choiceAcademicDegree;

    @FXML
    void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
        Main.showMainWindow();
    }

    @FXML
    void editEmployee(ActionEvent event) {
        editEmployee();
    }

    DriverSqlEmployee driverSqlEmployee = new DriverSqlEmployee();
    DriverSqlCathedral driverSqlCathedral = new DriverSqlCathedral();
    ArrayList<String> cathedralListChoiceBox;
    Employee employee;
    MessagePanel messagePanel = new MessagePanel();
    ControllerSearchEmployee controllerSearchEmployee = new ControllerSearchEmployee();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeWindow();
    }

    public void initializeWindow(){
        employee = driverSqlEmployee.getEmployeeById(controllerSearchEmployee.idEditEmployee);
        fieldName.setText(employee.getName());
        fieldSurname.setText(employee.getSurname());


        if(employee.isManager() == true){
            choiceManagerTrue.setSelected(true);
            choiceManagerFalse.setSelected(false);
        } else {
            choiceManagerTrue.setSelected(false);
            choiceManagerFalse.setSelected(true);
        }

        addAcademicDegree(employee.getAcademicDegree());
        addCathedralToChoiceBox();
        initializePosition(employee.getPosition());
        fieldPensum.setText(String.valueOf(employee.getPensum()));

    }



    /**
     * metoda pobieraj¹ca imie dodawanego pracownika
     * je¿eli pole jest puste wyœwietlane jest okno z ostrze¿eniem o niewype³nieniu pola
     * @return employe name
     */
    public String getFieldName(){
        String nameEmploye = null;
        nameEmploye = fieldName.getText();
        if(nameEmploye == null){
            messagePanel.addNoChoice("Nie wpisano imienia!");
        }
        return nameEmploye;
    }


    /**
     * metoda pobieraj¹ca nazwisko dodawanego pracownika
     * je¿eli pole jest puste wyœwietlane jest okno z ostrze¿eniem o niewype³nieniu pola
     * @return employe surname
     */
    private String getFieldSurname(){
        String surnameEmplye = null;
        surnameEmplye = fieldSurname.getText();
        if(surnameEmplye == null){
            messagePanel.addNoChoice("Nie wpisano nazwiska!");
        }
        return surnameEmplye;
    }


    /**
     * Metoda pobierajaca pensum pracownika
     * je¿eli puste wyœwietla siê okno z komunikatem
     * zwracany typ int
     */
    private int getFieldPensum(){
        int pensum = Integer.parseInt(fieldPensum.getText());
        return pensum;
    }


    /**
     * metoda pobierajaca jaka katedra zosta³a wybrana
     * zwrana int
     */
    private int getCathedral(){
        String choiceCathedralTemp = choiceCathedral.getValue();
        int idCathedral = driverSqlCathedral.getIdCathedral(choiceCathedralTemp);
        return idCathedral;
    }




    /**
     * Metoda sprawdza wybór czy dodawany pracownij jest kierownikiem czy nie.
     * Je¿eli nic nie wybrano wyœwietla komunikat z ostrze¿eniem, ¿e nie wybrano opcji.
     * Nie mo¿na zapisaæ bez wybrou jednej z dwóch opcji.
     * @return managerChoice
     */
    private boolean getManagerChoice(){
        boolean managerChoice = false;
        if(choiceManagerTrue.isSelected()) managerChoice = true;
        else if(choiceManagerFalse.isSelected()) managerChoice = false;
        else  messagePanel.addNoChoice("Ooops, czy nowy pracownik jest kierownikiem?");
        return managerChoice;
    }


    private String getAcademicDegree(){
        return choiceAcademicDegree.getValue();
    }

    private String getPosition(){
        return choicePosition.getValue();
    }


    /**
     * metoda pobiera z bazy listê katedr to choiceBoxa
     */
    private void addCathedralToChoiceBox(){
        cathedralListChoiceBox = new ArrayList<>();
        choiceCathedral.setTooltip(new Tooltip("Wybierz katedre"));
        cathedralListChoiceBox = driverSqlCathedral.getCathedralList();
        ObservableList<String> cathedralObservableList = FXCollections.observableArrayList(cathedralListChoiceBox);
        choiceCathedral.setItems(cathedralObservableList);
        String choiceValue = driverSqlCathedral.getCathedralById(employee.getIdCathedral());
        choiceCathedral.setValue(choiceValue);
    }

    private void addAcademicDegree(String academicDegree){
        ArrayList<String> academicDegreeList = new ArrayList<>();
        academicDegreeList.add("mgr");
        academicDegreeList.add("dr");
        academicDegreeList.add("dr hab");
        academicDegreeList.add("prof");
        ObservableList<String> observableList = FXCollections.observableList(academicDegreeList);
        choiceAcademicDegree.setItems(observableList);
        choiceAcademicDegree.setValue(academicDegree);
    }

    private void initializePosition(String position){
        ArrayList<String> posiotionList = new ArrayList<>();
        posiotionList.add("asystent");
        posiotionList.add("wyk³adowca");
        posiotionList.add("starszy wyk³adowca");
        posiotionList.add("adiunkt");
        posiotionList.add("prof. nadzwyczajny");
        ObservableList<String> observableList = FXCollections.observableList(posiotionList);
        choicePosition.setItems(observableList);
        choicePosition.setValue(position);
    }

    private void editEmployee(){
        Employee employee = new Employee(
                getFieldName()
                , getFieldSurname()
                , getAcademicDegree()
                , getPosition()
                , getManagerChoice()
                , getCathedral()
                , getFieldPensum());
        driverSqlEmployee.updateEmployee(employee);
        messagePanel.showInformationMessage("Pracownik zosta³ zapisany!");
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
        Main.showMainWindow();
    }

}