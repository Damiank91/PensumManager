package Controller.SubjectList;

import Controller.Main;
import Model.ConnectSql.DriverSqlCathedral;
import Model.ConnectSql.DriverSqlSubject;
import Model.Student.StudentTable;
import Model.Subject.SubjectTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerSearchSubList implements Initializable {
    @FXML
    private TableView<SubjectTable> tableSubject;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField fieldName;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<Integer, SubjectTable> colIdSubject;

    @FXML
    private TableColumn<String, SubjectTable> colCathedry;

    @FXML
    private TableColumn<String, SubjectTable> colNameSubjext;

    @FXML
    private ChoiceBox<String> choiceCathedry;

    @FXML
    private Button btnExit;

    ArrayList<String> cathedralListChoiceBox = new ArrayList<>();
    ArrayList<SubjectTable> subjectTables = new ArrayList<>();
    DriverSqlCathedral driverSqlCathedral = new DriverSqlCathedral();
    DriverSqlSubject driverSqlSubject = new DriverSqlSubject();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCathedralToChoiceBox();
        btnSearch.setOnAction(event -> setBtnSearchSubjectAction());
        btnExit.setOnAction(event -> backToMainSubjectWindow());
        btnDelete.setOnAction(event -> deleteSubject());
    }

    /**
     * metoda pobiera z bazy listê katedr to choiceBox
     */
    private void addCathedralToChoiceBox(){
        choiceCathedry.setTooltip(new Tooltip("Wybierz katedre"));
        cathedralListChoiceBox = driverSqlCathedral.getCathedralList();
        ObservableList<String> cathedralObservableList = FXCollections.observableArrayList(cathedralListChoiceBox);
        choiceCathedry.setItems(cathedralObservableList);
    }

    private void setBtnSearchSubjectAction(){
        getSearchSubject();
        setSearchSubjectToTable();
    }

    private void getSearchSubject(){
        subjectTables = driverSqlSubject.searchSubject(getSearchValue());
    }

    private Map<String,String> getSearchValue(){
        Map<String, String> searchValue = new HashMap<>();

        if(!getFieldName().isEmpty()){
            searchValue.put("z.nazwa_zajec", getFieldName());
        }
        if(!(getChoiceCathedra() == null)){
            searchValue.put("k.nazwa_katedry",getChoiceCathedra());
        }

        return searchValue;
    }

    private String getChoiceCathedra(){
        return choiceCathedry.getValue();
    }

    private String getFieldName(){
        return fieldName.getText();
    }

    private void setSearchSubjectToTable(){
        ObservableList<SubjectTable> data = FXCollections.observableArrayList(subjectTables);
        tableSubject.setItems(data);
        colIdSubject.setCellValueFactory(new PropertyValueFactory<>("idSubject"));
        colNameSubjext.setCellValueFactory(new PropertyValueFactory<>("nameSubject"));
        colCathedry.setCellValueFactory(new PropertyValueFactory<>("nameCathedral"));

    }

    private void deleteSubject(){
        Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Proces usuwania studenta jest nieodwracalny!");
        alert.setContentText("Czy chcesz to zrobi??");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            driverSqlSubject.deleteSubject(getEditIdSubject());
            setBtnSearchSubjectAction();
        } else {
            alert.close();
        }
    }

    private int getEditIdSubject(){
        int idSubject = 0;
        SubjectTable subjectTable = tableSubject.getSelectionModel().getSelectedItem();
        idSubject = subjectTable.getIdSubject();
        return idSubject;
    }

    private void backToMainSubjectWindow(){
        clearFields();
        Main.setSceneSubjectWindow();
    }

    private void clearFields(){
        fieldName.setText("");
        choiceCathedry.setValue(null);
        tableSubject.setItems(null);
    }
}
