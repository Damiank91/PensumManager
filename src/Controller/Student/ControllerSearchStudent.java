package Controller.Student;

import Controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSearchStudent implements Initializable {

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPeriod;

    @FXML
    private TextField fieldName;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField fieldSurname;

    @FXML
    private TableColumn<?, ?> colSurname;

    @FXML
    private ChoiceBox<?> choiceBoxPeriod;

    @FXML
    private ChoiceBox<?> choiceBoxTitleStudy;

    @FXML
    private ChoiceBox<?> choiceBoxYear;

    @FXML
    private TableColumn<?, ?> colTitleStudy;

    @FXML
    private TableColumn<?, ?> colYear;

    @FXML
    private Button btnExit;

    @FXML
    private ChoiceBox<?> choiceBoxMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnExit.setOnAction(event -> selectBtnExit());
    }

    /**
     * metoda wywolana po wybraniu przycisku wyjdü
     * wyswietla g≥Ûwne okno student i czysci wszystkie pola
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

}
