package Controller.Employee;


import Controller.Main;
import Model.ConnectSql.DriverSqlCathedral;
import Model.ConnectSql.DriverSqlEmployee;
import Model.ConnectSql.DriverSqlSubject;
import Model.Employee.Employee;
import Model.Subject.Subject;
import View.MessagePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAddEmployee implements Initializable {


    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TableColumn<Subject, Integer> subjectIdTable;

    @FXML
    private TableColumn<Subject, String> subjectNameTable;

    @FXML
    private Button btnDeleteValueTable;

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

    @FXML
    void addSubjectTable(ActionEvent event) {
        if(choiceSubject.getValue() == null){
            messagePanel.showErrorMessage("Nie wybrano prezdmiotu dla prowadzącego!");
        } else {
            addSubjectToTable();

        }
    }

    @FXML
    void deleteSubjectTable(ActionEvent event) {
        Subject selectSubject = subjectTable.getSelectionModel().getSelectedItem();

        if(selectSubject == null){
            messagePanel.showErrorMessage("Nie wybrano przedmiotu do usunięcia");
        } else {

            subjectArrayList.remove(selectSubject);
            refreshTable();
        }
    }

    MessagePanel messagePanel = new MessagePanel();
    DriverSqlEmployee driverSqlEmployee = new DriverSqlEmployee();
    DriverSqlCathedral driverSqlCathedral = new DriverSqlCathedral();
    DriverSqlSubject driverSqlSubject = new DriverSqlSubject();
    ArrayList<String> subjectListChoiceBox = new ArrayList<>();
    ArrayList<String> cathedralListChoiceBox = new ArrayList<>();
    ArrayList<Subject> subjectArrayList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCathedralToChoiceBox();
        btnSave.setOnAction(event -> setDataInBase());
        btnClearField.setOnAction(event -> clearFields());
        btnExit.setOnAction(event -> backToMainWindowScene());
        choiceCathedral.setOnAction(event -> addSubjectToChoiceBox(choiceCathedral.getValue()));

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
        int idEmployee = driverSqlEmployee.getEmployeeByValue(employee);
        driverSqlEmployee.saveEmloyeeSubject(subjectArrayList, idEmployee);
        clearFields();
        messagePanel.showInformationMessage("Nowy pracownik został zapisany!");
    }


    /**
     * metoda pobierająca imie dodawanego pracownika
     * jeżeli pole jest puste wyświetlane jest okno z ostrzeżeniem o niewypełnieniu pola
     * @return employe name
     */
    public String getFieldName(){
        String nameEmployee = null;
        nameEmployee = fieldName.getText();
        if(nameEmployee == null){
            messagePanel.addNoChoice("Nie wpisano imienia!");
        }
        return nameEmployee;
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
            messagePanel.addNoChoice("Nie wpisano nazwiska!");
        }
        return surnameEmplye;
    }


    /**
     * Metoda pobierajaca pensum pracownika
     * jeżeli puste wyświetla się okno z komunikatem
     * zwracany typ int
     */
    private int getFieldPensum(){
        int pensum = 0;
        pensum = Integer.parseInt(fieldPensum.getText());
        if(pensum == 0){
            messagePanel.addNoChoice("nie wybrano pensum");
        }
        return pensum;
    }


    /**
     * metoda pobierajaca jaka katedra została wybrana
     * zwrana int
     */
    private int getCathedral(){
        String choiceCathedralTemp = choiceCathedral.getValue();
        int idCathedral = driverSqlCathedral.getIdCathedral(choiceCathedralTemp);
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
        messagePanel.addNoChoice("Ooops, nie wybrałeś płci" );
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
        else messagePanel.addNoChoice("Ooops, czy nowy pracownik jest kierownikiem?");
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
            messagePanel.addNoChoice("Nie wybrano daty urodzenia!");
        } else {
            birthDateEmploye = bornDate.getValue().toString();
        }
        return birthDateEmploye;
    }


    /**
     * metoda pobiera z bazy listę katedr to choiceBoxa
     */
    private void addCathedralToChoiceBox(){

        choiceCathedral.setTooltip(new Tooltip("Wybierz katedre"));
        cathedralListChoiceBox = driverSqlCathedral.getCathedralList();
        ObservableList<String> cathedralObservableList = FXCollections.observableArrayList(cathedralListChoiceBox);
        choiceCathedral.setItems(cathedralObservableList);

    }


    /**
     * metoda pobiera z bazy przedmioty do choiceBox
     */
    private void addSubjectToChoiceBox(String choiceCathedral){

        choiceSubject.setTooltip(new Tooltip("Wybierz przedmiot"));
        subjectListChoiceBox = driverSqlSubject.getSubjectListByCathedral(choiceCathedral);
        ObservableList<String> subjectObservableList = FXCollections.observableArrayList(subjectListChoiceBox);
        choiceSubject.setItems(subjectObservableList);
    }


    private void addSubjectToTable(){

        Subject subject = driverSqlSubject.getSubjectListByName(choiceSubject.getValue());
        subjectArrayList.add(subject);
        ObservableList<Subject> data = FXCollections.observableArrayList(subjectArrayList);
        subjectTable.setItems(data);
        subjectIdTable.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("id_subject"));
        subjectNameTable.setCellValueFactory(new PropertyValueFactory<Subject, String>("name_subject"));
    }


    private void refreshTable(){
        ObservableList<Subject> data = FXCollections.observableArrayList(subjectArrayList);
        subjectTable.setItems(data);
        subjectIdTable.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("id_subject"));
        subjectNameTable.setCellValueFactory(new PropertyValueFactory<Subject, String>("name_subject"));
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
        subjectArrayList = null;
        subjectTable.setItems(null);
    }


    private void backToMainWindowScene(){
        clearFields();
        Main.setSceneEmployeeWindow();
    }

}
