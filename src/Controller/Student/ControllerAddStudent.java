package Controller.Student;


import Controller.Main;
import Model.ConnectSql.DriverSqlStudent;
import Model.Student.Student;
import View.MessagePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerAddStudent implements Initializable {


    @FXML
    private TextField fieldName;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField fieldSurname;

    @FXML
    private RadioButton radioBtnMale;

    @FXML
    private DatePicker bornDate;

    @FXML
    private ChoiceBox<Integer> choiceBoxPeriod;

    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    private ChoiceBox<String> choiceBoxTitleStudy;


    @FXML
    private Button btnSave;

    @FXML
    private Button btnClearFields;

    @FXML
    private Button btnExit;

    @FXML
    private ChoiceBox<String> choiceBoxTryb;



    @FXML
    void saveStudent(ActionEvent event) {
        setDataInBase();
    }


    @FXML
    void clearFields(ActionEvent event) {
        clearFields();
    }

    /**
     * metoda wywołana po wyborze przycisku btnExit
     * przechodzi do okna StudentWindowPane
     * czysci pola w oknie AddStudent
     */
    @FXML
    void skipToMainStudentPanel(ActionEvent event) {
        Main.setSceneStudentWindow();
        clearFields();
    }

    MessagePanel messagePanel = new MessagePanel();
    DriverSqlStudent driverSqlStudent = new DriverSqlStudent();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListaKierunkow();
        setListaTrybow();
        setSemestr();
    }

    /**
     * metoda pobierająca imie dodawanego pracownika
     * jeżeli pole jest puste wyświetlane jest okno z ostrzeżeniem o niewypełnieniu pola
     * @return employe name
     */
    public String getFieldName(){
        return fieldName.getText();
    }

    /**
     * metoda pobierająca nazwisko dodawanego pracownika
     * @return employe surname
     */
    public String getStudentSurname(){
        return fieldSurname.getText();
    }


    /**
     * Metoda zwraca datę urodzenia dodawanego pracownika
     * @return birthDate (YYYY-MM-DD)
     */
    public String getBornDate(){
        return bornDate.getValue().toString();
    }


    /**
     * Metoda sprawdza wybór czy dodawany pracownij jest kobietą czy mężczyzną.
     * Jeżeli nic nie wybrano wyświetla komunikat z ostrzeżeniem, że nie wybrano opcji.
     * Nie można zapisać bez wybrou jednej z dwóch opcji.
     * @return genderChoice
     */
    private String getGenderChoice(){
        String gender = null;
        if (radioBtnFemale.isSelected()) gender = "K";
        else if (radioBtnMale.isSelected()) gender = "M";
        return gender;
    }


    /**
     * metoda czyszcząca pole po wybraniu przycisku btnClearFields
     */
    private void clearFields(){
        fieldName.setText("");
        fieldSurname.setText("");
        radioBtnMale.setSelected(false);
        radioBtnFemale.setSelected(false);
        choiceBoxTitleStudy.setValue(null);
        choiceBoxTryb.setValue(null);
        choiceBoxPeriod.setValue(null);
        bornDate.setValue(null);
    }




    /**
     * metoda zwracająca id kierunku na podstawie wybranej nazwy
     * @return idKierunku
     */
    private int getIdKierunku() {
        int idKierunku = 0;
        String nazwaKierunku = choiceBoxTitleStudy.getValue();
        idKierunku = driverSqlStudent.getIdKierunku(nazwaKierunku);
        return idKierunku;
    }

    /**
     *
     * @return
     */
    private String getTryb(){
        return choiceBoxTryb.getValue();
    }


    private int getSemestr(){
        return choiceBoxPeriod.getValue();
    }


    /**
     * metoda wykonująca czynność jeżeli użytkownik wybierze przycisk "Zapisz"
     */
    private void setDataInBase(){
        if(checkFieldsIsComplete()){
            Student student = new Student(getFieldName()
                    ,getStudentSurname()
                    ,getBornDate()
                    ,getGenderChoice()
                    ,getTryb()
                    ,getIdKierunku()
                    ,getSemestr());
            driverSqlStudent.insertStudent(student);
            clearFields();
            messagePanel.showInformationMessage("Student został dodany");
        }
    }



    private void setListaKierunkow(){
        ArrayList<String> listaKierunkow;
        choiceBoxTitleStudy.setTooltip(new Tooltip("Wybierz kierunek dla studenta"));
        listaKierunkow = driverSqlStudent.getListeKierunkow();
        ObservableList<String> kierunkiObservableList = FXCollections.observableList(listaKierunkow);
        choiceBoxTitleStudy.setItems(kierunkiObservableList);
    }

    private void setListaTrybow(){
        ArrayList<String> listaTrybow = new ArrayList<>();
        listaTrybow.add("Stacjonarne");
        listaTrybow.add("Niestacjonarne");
        choiceBoxTryb.setTooltip(new Tooltip("Wybierz tryb"));
        ObservableList<String> trybyObservableList = FXCollections.observableList(listaTrybow);
        choiceBoxTryb.setItems(trybyObservableList);
    }


    private void setSemestr(){
        ArrayList<Integer> semestr = new ArrayList<>();
        semestr.add(1);
        semestr.add(2);
        semestr.add(3);
        semestr.add(4);
        semestr.add(5);
        semestr.add(6);
        semestr.add(7);
        semestr.add(8);
        choiceBoxPeriod.setTooltip(new Tooltip("wybierz semestr"));
        ObservableList<Integer> semestrObservableList = FXCollections.observableList(semestr);
        choiceBoxPeriod.setItems(semestrObservableList);

    }

    private boolean checkFieldsIsComplete(){
        boolean isComplete = true;

        if(fieldName.getText().isEmpty()){
            isComplete = false;
            messagePanel.addNoChoice("Wpisz imie!");
        } else if(fieldSurname.getText().isEmpty()){
            isComplete = false;
            messagePanel.addNoChoice("Wpisz nazwisko!");
        } else if(bornDate.getValue() == null){
            isComplete = false;
            messagePanel.addNoChoice("Nie wybrano daty urodzenia");
        } else if(!(radioBtnMale.isSelected() || radioBtnFemale.isSelected())){
            isComplete = false;
            messagePanel.addNoChoice("Nie wybrano płci");
        } else if(choiceBoxTitleStudy.getValue() == null){
            isComplete = false;
            messagePanel.addNoChoice("Nie wybrano kierunku");
        } else if(choiceBoxTryb.getValue() == null){
            isComplete = false;
            messagePanel.addNoChoice("Nie wybrano trybu");
        } else if(choiceBoxPeriod.getValue() == null){
            isComplete = false;
            messagePanel.addNoChoice("Nie wybrano semestru");
        }

        return isComplete;
    }

}
