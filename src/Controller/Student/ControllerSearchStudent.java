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
    private Button btnSearch;

    @FXML
    private TextField fieldSurname;

    @FXML
    private ChoiceBox<Integer> choiceBoxPeriod;

    @FXML
    private ChoiceBox<String> choiceBoxTitleStudy;

    @FXML
    private ChoiceBox<Integer> choiceBoxYear;

    @FXML
    private TableView<StudentTable> tableStudent;

    @FXML
    private TableColumn<String, StudentTable> colSurname;

    @FXML
    private TableColumn<String, StudentTable> colTitleStudy;

    @FXML
    private TableColumn<Integer, StudentTable> colYear;

    @FXML
    private TableColumn<Boolean, StudentTable> colWarunek;

    @FXML
    private TableColumn<String, StudentTable> colName;

    @FXML
    private TableColumn<Integer, StudentTable> colPeriod;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnExit;

    @FXML
    private ChoiceBox<String> choiceBoxMode;

    @FXML
    void searchBtnAction(ActionEvent event) {
        getSearchStudent();
        setSearchStudentToTable();

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
        alert.setContentText("Czy chcesz to zrobiæ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            driverSqlStudent.deleteStudent(getEditIdStudent());
        } else {
            alert.close();
        }
    }

    DriverSqlStudent driverSqlStudent = new DriverSqlStudent();
    ArrayList<StudentTable> studentTableArrayList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListaKierunkow();
        setListaTrybow();
        setRocznik();
        setSemestr();
    }

    /**
     * metoda wywolana po wybraniu przycisku wyjdŸ
     * wyswietla g³ówne okno student i czysci wszystkie pola
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
        choiceBoxMode.setValue(null);
        choiceBoxPeriod.setValue(null);
        choiceBoxYear.setValue(null);
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

    private String getTryb(){
        return choiceBoxMode.getValue();
    }

    private String getRokString(){
        return choiceBoxYear.getValue().toString();
    }

    private String getPeriodString(){
        return choiceBoxPeriod.getValue().toString();
    }


    private void setListaKierunkow(){
        ArrayList<String> listaKierunkow = driverSqlStudent.getListeKierunkow();
        ObservableList<String> kierunkoObservableList = FXCollections.observableList(listaKierunkow);
        choiceBoxTitleStudy.setItems(kierunkoObservableList);
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

    private void setListaTrybow(){
        ArrayList<String> listaTrybow;
        choiceBoxMode.setTooltip(new Tooltip("Wybierz tryb"));
        listaTrybow = driverSqlStudent.getListeTrybow();
        ObservableList<String> trybyObservableList = FXCollections.observableList(listaTrybow);
        choiceBoxMode.setItems(trybyObservableList);
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
            searchValue.put("imie_studenta", getFieldName());
        }
        if(!getFieldSurname().isEmpty()){
            searchValue.put("nazwisko_studenta",getFieldSurname());
        }
        if(!(getKierunek() == null)){
            searchValue.put("nazwa_kierunku", getKierunek());
        }
        if(!(getTryb() == null)){
            searchValue.put("name_trybu",getTryb());
        }
        if(!(getRokString()== null)) {
            searchValue.put("rok",getRokString());
        }
        if(!(getPeriodString() == null)){
            searchValue.put("semestr",getPeriodString());
        }

        return searchValue;
    }

    private void setSearchStudentToTable(){
        ObservableList<StudentTable> data = FXCollections.observableArrayList(studentTableArrayList);
        tableStudent.setItems(data);
        colName.setCellValueFactory(new PropertyValueFactory<>("nameStudent"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surnameStudent"));
        colTitleStudy.setCellValueFactory(new PropertyValueFactory<>("kierunek"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("rok"));
        colPeriod.setCellValueFactory(new PropertyValueFactory<>("semestr"));
        colWarunek.setCellValueFactory(new PropertyValueFactory<>("czyMaWarunek"));
    }
}

