package Controller.SubjectList;

import Controller.Main;
import Model.ConnectSql.DriverSqlCathedral;
import Model.ConnectSql.DriverSqlSubject;
import Model.Subject.Subject;
import View.MessagePanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerAddSubject implements Initializable {
    @FXML
    private Button btnSave;

    @FXML
    private TextField fieldSubjectName;

    @FXML
    private ChoiceBox<String> choiceBoxCathedral;

    @FXML
    private Button btnExit;

    DriverSqlSubject driverSqlSubject = new DriverSqlSubject();
    DriverSqlCathedral driverSqlCathedral = new DriverSqlCathedral();
    ArrayList<String> cathedralListChoiceBox = new ArrayList<>();
    MessagePanel messagePanel = new MessagePanel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCathedralToChoiceBox();
        btnSave.setOnAction(event -> saveSubject());
        btnExit.setOnAction(event -> backToMainSubjectWindow());
    }

    private void saveSubject(){
        int idCathedral = driverSqlCathedral.getIdCathedral(choiceBoxCathedral.getValue());
        String nameSubject = fieldSubjectName.getText();
        Subject subject = new Subject(nameSubject, idCathedral);
        saveInBase(subject);
    }

    private void backToMainSubjectWindow(){
        Main.setSceneSubjectWindow();
        clearFields();
    }

    /**
     * metoda pobiera z bazy listê katedr to choiceBox
     */
    private void addCathedralToChoiceBox(){
        choiceBoxCathedral.setTooltip(new Tooltip("Wybierz katedre"));
        cathedralListChoiceBox = driverSqlCathedral.getCathedralList();
        ObservableList<String> cathedralObservableList = FXCollections.observableArrayList(cathedralListChoiceBox);
        choiceBoxCathedral.setItems(cathedralObservableList);
    }

    private void saveInBase(Subject subject){
        driverSqlSubject.saveSubject(subject);
        messagePanel.showInformationMessage("Dodano przedmiot");
        clearFields();
    }

    private void clearFields(){
        choiceBoxCathedral.setValue(null);
        fieldSubjectName.setText("");
    }
}
