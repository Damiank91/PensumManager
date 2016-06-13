package Controller.Student;

import Controller.Main;
import Model.ConnectSql.DriverSqlStudent;
import Model.Student.StudentTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class ControllerSearchStudent implements Initializable {


    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSurname;

    @FXML
    private ChoiceBox<String> choiceBoxPeriod;

    @FXML
    private ChoiceBox<String> choiceBoxTitleStudy;


    @FXML
    private TableView<StudentTable> tableStudent;

    @FXML
    private TableColumn<String, StudentTable> colSurname;

    @FXML
    private TableColumn<String, StudentTable> colTitleStudy;

    @FXML
    private TableColumn<String, StudentTable> colName;

    @FXML
    private TableColumn<Integer, StudentTable> colPeriod;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnSearch;



    @FXML
    void searchBtnAction(ActionEvent event) {
        setValueToTable();
    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        selectBtnExit();
    }

    @FXML
    void deleteBtnAction(ActionEvent event) {
        Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Proces usuwania studenta jest nieodwracalny!");
        alert.setContentText("Czy chcesz to zrobi�?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            driverSqlStudent.deleteStudent(getEditIdStudent());
            setValueToTable();
        } else {
            alert.close();
        }
    }


    DriverSqlStudent driverSqlStudent = new DriverSqlStudent();
    ArrayList<StudentTable> studentTableArrayList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListaKierunkow();
        setSemestr();
    }

    private void setValueToTable(){
        getSearchStudent();
        setSearchStudentToTable();
    }

    /**
     * metoda wywolana po wybraniu przycisku wyjd�
     * wyswietla g��wne okno student i czysci wszystkie pola
     */
    private void selectBtnExit(){
        Main.setSceneStudentWindow();
        clearFields();
    }

    /**
     * metoda czyszczaca wszystkie pola
     */
    private void clearFields(){
        fieldName.setText("");
        fieldSurname.setText("");
        choiceBoxPeriod.setValue(null);
        choiceBoxTitleStudy.setValue(null);
    }

    private String getFieldName(){
        return fieldName.getText();
    }

    private String getFieldSurname(){
        return fieldSurname.getText();
    }

    private String getKierunek(){
        return choiceBoxTitleStudy.getValue();
    }

    private String getPeriodString(){
        return choiceBoxPeriod.getValue();
    }


    private void setListaKierunkow(){
        ArrayList<String> listaKierunkow = driverSqlStudent.getListeKierunkow();
        listaKierunkow.add(0,"");
        ObservableList<String> kierunkoObservableList = FXCollections.observableList(listaKierunkow);
        choiceBoxTitleStudy.setItems(kierunkoObservableList);
    }


    private void setSemestr(){
        ArrayList<String> semestr = new ArrayList<>();
        semestr.add("");
        semestr.add("1");
        semestr.add("2");
        semestr.add("3");
        semestr.add("4");
        semestr.add("5");
        semestr.add("6");
        semestr.add("7");
        choiceBoxPeriod.setTooltip(new Tooltip("wybierz semestr"));
        ObservableList<String> semestrObservableList = FXCollections.observableList(semestr);
        choiceBoxPeriod.setItems(semestrObservableList);
    }

    private int getEditIdStudent(){
        int idStudent = 0;
        StudentTable studentTable = tableStudent.getSelectionModel().getSelectedItem();
        idStudent = studentTable.getIdStudent();
        return idStudent;

    }

    private void getSearchStudent(){
        studentTableArrayList = driverSqlStudent.searchStudent(getSearchValue());
    }

    private Map<String,String> getSearchValue(){
        Map<String, String> searchValue = new HashMap<>();

        if(!getFieldName().isEmpty()){
            searchValue.put("s.imie_studenta", getFieldName());
        }
        if(!getFieldSurname().isEmpty()){
            searchValue.put("s.nazwisko_studenta",getFieldSurname());
        }
        if(!(getKierunek() == null)){
            searchValue.put("k.nazwa_kierunku", getKierunek());
        }
        if(!(getPeriodString() == null)){
            searchValue.put("s.semestr_studiow",getPeriodString());
        }

        return searchValue;
    }

    private void setSearchStudentToTable(){
        ObservableList<StudentTable> data = FXCollections.observableArrayList(studentTableArrayList);
        tableStudent.setItems(data);
        colName.setCellValueFactory(new PropertyValueFactory<>("nameStudent"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surnameStudent"));
        colTitleStudy.setCellValueFactory(new PropertyValueFactory<>("kierunek"));
        colPeriod.setCellValueFactory(new PropertyValueFactory<>("semestr"));
    }
}

