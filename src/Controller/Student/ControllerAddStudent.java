package Controller.Student;


import Controller.Main;
import Model.ConnectSql.DriverSqlStudent;
import Model.Student.Student;
import View.Student.MessagePanelStudent;
import com.sun.javafx.image.IntPixelGetter;
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
    private RadioButton warunekTrue;

    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    private ChoiceBox<String> choiceBoxTitleStudy;

    @FXML
    private ToggleGroup warunek;

    @FXML
    private ChoiceBox<Integer> choiceBoxYear;

    @FXML
    private RadioButton warunekFalse;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClearFields;

    @FXML
    private Button btnExit;

    @FXML
    private ChoiceBox<String> choiceBoxTryb;

    @FXML
    private ChoiceBox<String> choiceStudentGroup;

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

    MessagePanelStudent messagePanelStudent = new MessagePanelStudent();
    DriverSqlStudent driverSqlStudent = new DriverSqlStudent();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGroupList();
        setListaKierunkow();
        setListaTrybow();
        setRocznik();
        setSemestr();
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
            messagePanelStudent.addStudentNoChoice("Nie wpisano imienia!");
        }
        return nameEmploye;
    }

    /**
     * metoda pobierająca nazwisko dodawanego pracownika
     * jeżeli pole jest puste wyświetlane jest okno z ostrzeżeniem o niewypełnieniu pola
     * @return employe surname
     */
    public String getStudentSurname(){
        String surnameEmplye = null;
        surnameEmplye = fieldSurname.getText();
        if(surnameEmplye == null){
            messagePanelStudent.addStudentNoChoice("Nie wpisano nazwiska!");
        }
        return surnameEmplye;
    }


    /**
     * Metoda zwraca datę urodzenia dodawanego pracownika
     * Jeżeli nic nie wybrano wyświetla komunikat z ostrzeżeniem, że nie wybrano opcji.
     * Nie można zapisać bez wyboru daty.
     * @return birthDate (YYYY-MM-DD)
     */
    public String getBornDate(){
        String birthDateStudent = null;

        if(bornDate.getValue() == null){
            messagePanelStudent.addStudentNoChoice("Nie wybrano daty urodzenia!");
        } else {
            birthDateStudent = bornDate.getValue().toString();
        }
        return birthDateStudent;
    }


    /**
     * Metoda sprawdza wybór czy dodawany pracownij jest kobietą czy mężczyzną.
     * Jeżeli nic nie wybrano wyświetla komunikat z ostrzeżeniem, że nie wybrano opcji.
     * Nie można zapisać bez wybrou jednej z dwóch opcji.
     * @return genderChoice
     */
    private char getGenderChoice(){
        char gender = 0;

        if (radioBtnFemale.isSelected()) gender = 'M';
        else if (radioBtnMale.isSelected()) gender = 'K';
        else messagePanelStudent.addStudentNoChoice("Ooops, nie wybrałeś płci" );
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
        choiceBoxYear.setValue(null);
        choiceBoxPeriod.setValue(null);
        warunekFalse.setSelected(false);
        warunekTrue.setSelected(false);
    }


    /**
     * metoda na podstawie wyboru nazwy grupy pobiera idGrupy z bazy
     * @return idGrzoup
     */
    private int getIdGroup(){
        int idGroup = 0;
        String nameGroup = choiceStudentGroup.getValue();
        if(nameGroup.isEmpty()){
            messagePanelStudent.showErrorMessage("Nie wybrano grupy");
        } else {
            idGroup = driverSqlStudent.getIdGroup(nameGroup);
        }
        return idGroup;
    }

    /**
     * metoda zwracająca id kierunku na podstawie wybranej nazwy
     * @return idKierunku
     */
    private int getIdKierunku() {
        int idKierunku = 0;
        String nazwaKierunku = choiceBoxTitleStudy.getValue();
        if(nazwaKierunku.isEmpty()) {
            messagePanelStudent.showErrorMessage("Nie wybrano kierunku");
        } else {
            idKierunku = driverSqlStudent.getIdKierunku(nazwaKierunku);
        }
        return idKierunku;
    }

    /**
     *
     * @return
     */
    private int getIdTrybu(){
        int idTrybu = 0;
        String nazwaTrybu = choiceBoxTryb.getValue();
        if(nazwaTrybu.isEmpty()){
            messagePanelStudent.showErrorMessage("Nie wybrano trybu");
        } else {
            idTrybu = driverSqlStudent.getIdTrybu(choiceBoxTryb.getValue());
        }
        return idTrybu;
    }

    private int getRok(){
        int rok = 0;
        rok = choiceBoxYear.getValue();
        if(rok == 0){
            messagePanelStudent.showErrorMessage("Nie wybrano rocznika");
        }
        return rok;
    }

    private int getSemestr(){
        int nrSemestru = 0;
        nrSemestru = choiceBoxYear.getValue();
        if(nrSemestru == 0){
            messagePanelStudent.showErrorMessage("Nie wybrano semestru");
        }
        return nrSemestru;
    }

    private boolean getCzyWarunek(){
        boolean czyMaWarunek = false;
        if(warunekTrue.isSelected()) czyMaWarunek = true;
        else if(warunekFalse.isSelected()) czyMaWarunek = false;
        else messagePanelStudent.addStudentNoChoice("Czy ma warunek?");
        return czyMaWarunek;
    }


    /**
     * metoda wykonująca czynność jeżeli użytkownik wybierze przycisk "Zapisz"
     */
    private void setDataInBase(){
        Student student = new Student(getFieldName()
                ,getStudentSurname()
                ,getBornDate()
                ,getGenderChoice()
                ,getIdTrybu()
                ,getIdKierunku()
                ,getIdGroup()
                ,getRok()
                ,getSemestr()
                ,getCzyWarunek());
        driverSqlStudent.insertStudent(student);
        clearFields();
        messagePanelStudent.showInformationMessage("Student został dodany");

    }

    /**
     * metoda pobierająca listę grup studenckich z bazy
     */
    private void setGroupList(){
        ArrayList<String> groupList;
        choiceStudentGroup.setTooltip(new Tooltip("Wybierz grupę dla studenta"));
        groupList = driverSqlStudent.getGroupList();
        ObservableList<String> groupObservableList = FXCollections.observableList(groupList);
        choiceStudentGroup.setItems(groupObservableList);
    }

    private void setListaKierunkow(){
        ArrayList<String> listaKierunkow;
        choiceBoxTitleStudy.setTooltip(new Tooltip("Wybierz kierunek dla studenta"));
        listaKierunkow = driverSqlStudent.getListeKierunkow();
        ObservableList<String> kierunkiObservableList = FXCollections.observableList(listaKierunkow);
        choiceBoxTitleStudy.setItems(kierunkiObservableList);
    }

    private void setListaTrybow(){
        ArrayList<String> listaTrybow;
        choiceBoxTryb.setTooltip(new Tooltip("Wybierz tryb"));
        listaTrybow = driverSqlStudent.getListeTrybow();
        ObservableList<String> trybyObservableList = FXCollections.observableList(listaTrybow);
        choiceBoxTryb.setItems(trybyObservableList);
    }

    private void setRocznik(){
        ArrayList<Integer> roczniki = new ArrayList<>();
        roczniki.add(1);
        roczniki.add(2);
        roczniki.add(3);
        roczniki.add(4);
        choiceBoxYear.setTooltip(new Tooltip("Wybierz rocznik"));
        ObservableList<Integer> rocznikObservableList = FXCollections.observableList(roczniki);
        choiceBoxYear.setItems(rocznikObservableList);
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
        choiceBoxPeriod.setTooltip(new Tooltip("wybierz semestr"));
        ObservableList<Integer> semestrObservableList = FXCollections.observableList(semestr);
        choiceBoxPeriod.setItems(semestrObservableList);

    }

}
