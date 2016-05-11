package Controller.Employee;


import Controller.Main;
import Model.ConnectSql.DriverSqlEmployee;
import Model.Employee.Employee;
import View.Employee.MessagePanelEmployee;
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
    ArrayList<String> cathedralListChoiceBox;
    Employee employee;
    MessagePanelEmployee messagePanelEmployee = new MessagePanelEmployee();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeWindow();
    }

    public void initializeWindow(){

        employee = driverSqlEmployee.getEmployeeById(1);
        fieldName.setText(employee.getName());
        fieldSurname.setText(employee.getSurname());
        bornDate.setValue(LocalDate.now());
        if(employee.getGender() == 'M'){
            choiceGenderMale.setSelected(true);
            choiceGenderFemale.setSelected(false);
        } else {
            choiceGenderMale.setSelected(false);
            choiceGenderFemale.setSelected(true);
        }

        if(employee.isManager() == true){
            choiceManagerTrue.setSelected(true);
            choiceManagerFalse.setSelected(false);
        } else {
            choiceManagerTrue.setSelected(false);
            choiceManagerFalse.setSelected(true);
        }


        addCathedralToChoiceBox();

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
            messagePanelEmployee.addEmployeNoChoice("Nie wpisano imienia!");
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
            messagePanelEmployee.addEmployeNoChoice("Nie wpisano nazwiska!");
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
        int idCathedral = driverSqlEmployee.getIdCathedral(choiceCathedralTemp);
        return idCathedral;
    }


    /**
     * Metoda sprawdza wybór czy dodawany pracownij jest kobiet¹ czy mê¿czyzn¹.
     * Je¿eli nic nie wybrano wyœwietla komunikat z ostrze¿eniem, ¿e nie wybrano opcji.
     * Nie mo¿na zapisaæ bez wybrou jednej z dwóch opcji.
     * @return genderChoice
     */
    private char getGenderChoice(){
        char gender = 0;

        if (choiceGenderMale.isSelected()) gender = 'M';
        else if (choiceGenderFemale.isSelected()) gender = 'K';
        else messagePanelEmployee.addEmployeNoChoice("Ooops, nie wybra³eœ p³ci" );
        return gender;
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
        else messagePanelEmployee.addEmployeNoChoice("Ooops, czy nowy pracownik jest kierownikiem?");
        return managerChoice;
    }


    /**
     * Metoda zwraca datê urodzenia dodawanego pracownika
     * Je¿eli nic nie wybrano wyœwietla komunikat z ostrze¿eniem, ¿e nie wybrano opcji.
     * Nie mo¿na zapisaæ bez wyboru daty.
     * @return birthDate (YYYY-MM-DD)
     */
    private String getBirthDate(){

        String birthDateEmploye = null;

        if(bornDate.getValue() == null){
            messagePanelEmployee.addEmployeNoChoice("Nie wybrano daty urodzenia!");
        } else {
            birthDateEmploye = bornDate.getValue().toString();
        }
        return birthDateEmploye;
    }



    /**
     * metoda pobiera z bazy listê katedr to choiceBoxa
     */
    private void addCathedralToChoiceBox(){
        cathedralListChoiceBox = new ArrayList<>();
        choiceCathedral.setTooltip(new Tooltip("Wybierz katedre"));
        cathedralListChoiceBox = driverSqlEmployee.getCathedralList();
        ObservableList<String> cathedralObservableList = FXCollections.observableArrayList(cathedralListChoiceBox);
        choiceCathedral.setItems(cathedralObservableList);
        String choiceValue = driverSqlEmployee.getCathedralById(employee.getIdCathedral());
        choiceCathedral.setValue(choiceValue);

    }

    private void editEmployee(){
        Employee editEmployee = new Employee(employee.getIdEmploye(),
                getFieldName(),
                getFieldSurname(),
                getBirthDate(),
                getGenderChoice(),
                getManagerChoice(),
                getCathedral(),
                getFieldPensum());
        driverSqlEmployee.updateEmployee(editEmployee);
        messagePanelEmployee.showInformationMessage("Pracownik zosta³ zapisany!");
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
        Main.showMainWindow();
    }

}