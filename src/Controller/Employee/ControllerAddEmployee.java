package Controller.Employee;


import Controller.Main;
import Model.ConnectSql.DriverSqlEmployee;
import Model.Employee.Employee;
import View.Employee.MessagePanelEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAddEmployee implements Initializable {

    /**
     * co trzeba jeszcze dodac:
     * 1. metoda wyświetlająca katedre
     * 2. metoda wyświetlająca przedmioty
     * 3. metoda potrafiąca dodać przedmioty do tabeli (tabela powinna miec przycis "usun" jak ktos zle doda przedmiot
     * 4. metoda zapisująca dane w bazie
     */


    @FXML
    private ListView<?> subjectList;

    @FXML
    private Button btnClearField;

    @FXML
    private TextField fieldName;

    @FXML
    private ChoiceBox<String> choiceCathedral;

    @FXML
    private ToggleGroup manager;

    @FXML
    private RadioButton choiceManagerFalse;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField fieldSurname;

    @FXML
    private RadioButton choiceManagerTrue;

    @FXML
    private DatePicker bornDate;

    @FXML
    private RadioButton choiceGenderFemale;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnAddSubjectToTable;

    @FXML
    private ChoiceBox<String> choiceSubject;

    @FXML
    private RadioButton choiceGenderMale;

    @FXML
    private Button btnExit;

    @FXML
    private TextField fieldPensum;

    MessagePanelEmployee messagePanelEmployee = new MessagePanelEmployee();
    DriverSqlEmployee driverSqlEmployee = new DriverSqlEmployee();
    ArrayList<String> subjectListChoiceBox;
    ArrayList<String> cathedralListChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCathedralToChoiceBox();
        addSubjectToChoiceBox();
        btnSave.setOnAction(event -> setDataInBase());
        btnClearField.setOnAction(event -> clearFields());
        btnExit.setOnAction(event -> backToMainWindowScene());
    }

    /**
     * metoda wykonująca czynność jeżeli użytkownik wybierze przycisk "Zapisz"
     */
    private void setDataInBase(){
        Employee employee = new Employee(
                getFieldName()
                ,getFieldSurname()
                ,getBirthDate()
                ,getGenderChoice()
                ,getManagerChoice()
                ,getCathedral()
                ,getFieldPensum());
        driverSqlEmployee.insertEmployee(employee);
        clearFields();
        messagePanelEmployee.showInformationMessage("Nowy pracownik został zapisany!");
    }


    /**
     * metoda pobierająca imie dodawanego pracownika
     * jeżeli pole jest puste wyświetlane jest okno z ostrzeżeniem o niewypełnieniu pola
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
     * metoda pobierająca nazwisko dodawanego pracownika
     * jeżeli pole jest puste wyświetlane jest okno z ostrzeżeniem o niewypełnieniu pola
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
     * jeżeli puste wyświetla się okno z komunikatem
     * zwracany typ int
     */
    private int getFieldPensum(){
        int pensum = Integer.parseInt(fieldPensum.getText());
        return pensum;
    }


    /**
     * metoda pobierajaca jaka katedra została wybrana
     * zwrana int
     */
    private int getCathedral(){
        String choiceCathedralTemp = choiceCathedral.getValue();
        int idCathedral = driverSqlEmployee.getIdCathedral(choiceCathedralTemp);
        return idCathedral;
    }


    /**
     * Metoda sprawdza wybór czy dodawany pracownij jest kobietą czy mężczyzną.
     * Jeżeli nic nie wybrano wyświetla komunikat z ostrzeżeniem, że nie wybrano opcji.
     * Nie można zapisać bez wybrou jednej z dwóch opcji.
     * @return genderChoice
     */
    private char getGenderChoice(){
        char gender = 0;
        
            if (choiceGenderMale.isSelected()) gender = 'M';
            else if (choiceGenderFemale.isSelected()) gender = 'K';
            else messagePanelEmployee.addEmployeNoChoice("Ooops, nie wybrałeś płci" );
        return gender;
    }


    /**
     * Metoda sprawdza wybór czy dodawany pracownij jest kierownikiem czy nie.
     * Jeżeli nic nie wybrano wyświetla komunikat z ostrzeżeniem, że nie wybrano opcji.
     * Nie można zapisać bez wybrou jednej z dwóch opcji.
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
     * Metoda zwraca datę urodzenia dodawanego pracownika
     * Jeżeli nic nie wybrano wyświetla komunikat z ostrzeżeniem, że nie wybrano opcji.
     * Nie można zapisać bez wyboru daty.
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
     * metoda pobiera z bazy listę katedr to choiceBoxa
     */
    private void addCathedralToChoiceBox(){
        cathedralListChoiceBox = new ArrayList<>();
        choiceCathedral.setTooltip(new Tooltip("Wybierz katedre"));
        cathedralListChoiceBox = driverSqlEmployee.getCathedralList();
        ObservableList<String> cathedralObservableList = FXCollections.observableArrayList(cathedralListChoiceBox);
        choiceCathedral.setItems(cathedralObservableList);

    }


    /**
     * metoda pobiera z bazy przedmioty do choiceBox
     */
    private void addSubjectToChoiceBox(){
        subjectListChoiceBox = new ArrayList<>();
        choiceSubject.setTooltip(new Tooltip("Wybierz przedmiot"));
        subjectListChoiceBox = driverSqlEmployee.getSubjectList();
        ObservableList<String> subjectObservableList = FXCollections.observableArrayList(subjectListChoiceBox);
        choiceSubject.setItems(subjectObservableList);
    }


    /**
     * metoda czyszcząca pola po wybraniu przycisku "wyczyść pola"
     */
    private void clearFields(){
        fieldName.setText("");
        fieldSurname.setText("");
        bornDate.setValue(null);
        choiceSubject.setValue(null);
        choiceCathedral.setValue(null);
        choiceGenderMale.setSelected(false);
        choiceGenderFemale.setSelected(false);
        choiceManagerFalse.setSelected(false);
        choiceManagerTrue.setSelected(false);
        fieldPensum.setText("");
    }


    private void backToMainWindowScene(){
        clearFields();
        Main.setSceneEmployeeWindow();
    }

}
