package Controller.Employee;

import Controller.Main;
import Model.ConnectSql.DriverSqlCathedral;
import Model.ConnectSql.DriverSqlEmployee;
import Model.ConnectSql.DriverSqlSubject;
import Model.Employee.EmployeeTable;

import View.MessagePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerSearchEmployee implements Initializable {


    @FXML
    private Button btnSearch;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField fieldName;

    @FXML
    private ChoiceBox<String> choiceCathedral;

    @FXML
    private TableColumn<EmployeeTable, Integer> idEmployeeColumn;

    @FXML
    private TableColumn<EmployeeTable, String> cathedralColumn;

    @FXML
    private TableColumn<EmployeeTable, String> nameColumn;

    @FXML
    private TableColumn<EmployeeTable, String> surnameColumn;


    @FXML
    private TableView<EmployeeTable> searchTable;

    @FXML
    private TextField fieldSurname;

    @FXML
    void addValueToTable(ActionEvent event) {
        addValueToTable();
    }

    @FXML
    void selectBtnExit(ActionEvent event) {
        selectBtnExit();
    }

    @FXML
    void initializeEditWindow(ActionEvent event) {
        try {
            initliazeNewWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteEmployee(ActionEvent event) {
        Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Proces usuwania pracownika jest nieodwracalny!");
        alert.setContentText("Czy chcesz to zrobić?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            int idEmployee = getEditIdEmployee();
            driverSqlEmployee.deleteEmployee(idEmployee);
            driverSqlSubject.deleteEmployeeSubject(idEmployee);
            addValueToTable();
        } else {
            alert.close();
        }

    }


    ArrayList<EmployeeTable> employeeTableArrayList = new ArrayList<>();

    private Stage window;
    protected static int idEditEmployee;
    Parent parentEditEmployeeWindowPane;
    Scene sceneEditEmployeeWindow;
    DriverSqlEmployee driverSqlEmployee = new DriverSqlEmployee();
    DriverSqlCathedral driverSqlCathedral = new DriverSqlCathedral();
    DriverSqlSubject driverSqlSubject = new DriverSqlSubject();
    MessagePanel messagePanel = new MessagePanel();
    ArrayList<String> cathedralListChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCathedralToChoiceBox();
    }

    private String getFieldName(){
        return fieldName.getText();
    }


    private String getFieldSurname(){
        return fieldSurname.getText();
    }


    private String getChoiceCathedral(){
        return choiceCathedral.getValue();
    }


    private void selectBtnExit() {
        Main.setSceneEmployeeWindow();
        clearFields();

    }


    private void clearFields() {
        fieldName.setText("");
        fieldSurname.setText("");
        choiceCathedral.setValue(null);
        searchTable.setItems(null);
    }


    private void addValueToTable() {
        getDataFromSql();
        setDataToTable();
    }


    private void addCathedralToChoiceBox() {
        cathedralListChoiceBox = new ArrayList<>();
        choiceCathedral.setTooltip(new Tooltip("Wybierz katedre"));
        cathedralListChoiceBox = driverSqlCathedral.getCathedralList();
        ObservableList<String> cathedralObservableList = FXCollections.observableArrayList(cathedralListChoiceBox);
        choiceCathedral.setItems(cathedralObservableList);
    }



    public int getEditIdEmployee() {
        int idEmploye;
        EmployeeTable employeeTable = searchTable.getSelectionModel().getSelectedItem();
        if(employeeTable == null){
            idEmploye = 0;
        } else{
            idEmploye = employeeTable.getIdEmployee();
        }

        return  idEmploye;
    }



    private void getDataFromSql(){
        employeeTableArrayList = driverSqlEmployee.getSearchEmployeeTable(getSearchValues());

    }

    private void initliazeNewWindow() throws IOException {
        idEditEmployee = getEditIdEmployee();
        if(idEditEmployee == 0){
            messagePanel.showErrorMessage("Nie wskazano pracownika do edycji!");
        } else {
            String APP_NAME = "Pensum manager";
            window = new Stage();
            parentEditEmployeeWindowPane = (Parent) FXMLLoader.load(getClass().getResource("/View/Employee/EditEmployee.fxml"));
            sceneEditEmployeeWindow = new Scene(parentEditEmployeeWindowPane);
            window.setScene(sceneEditEmployeeWindow);
            window.setTitle(APP_NAME);
            window.show();
            Main.hideMainWIndow();
        }
    }


    /**
     * metoda sprawdzająca jakie pola są uzupełnione aby je przekazać do zapytania sql w where
     * @return parametry do zawężenia zbioru pracowników
     */
    private Map<String,String> getSearchValues(){
        Map<String,String> searchValues = new HashMap<>();

        if(!getFieldName().isEmpty()){
            searchValues.put("imie_pracownika", getFieldName());
        }
        if (!getFieldSurname().isEmpty()) {
            searchValues.put("nazwisko_pracownika", getFieldSurname());
        }
        if (!(getChoiceCathedral() == null)){
            searchValues.put("nazwa_katedry",getChoiceCathedral());
        }
        return searchValues;
    }


    private void setDataToTable(){
        ObservableList<EmployeeTable> data = FXCollections.observableArrayList(employeeTableArrayList);
        searchTable.setItems(data);
        idEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        cathedralColumn.setCellValueFactory(new PropertyValueFactory<>("cathedral"));
    }

}
