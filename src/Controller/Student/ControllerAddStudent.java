package Controller.Student;
import Controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerAddStudent implements Initializable {

    /**
     * co jeszcze trzeba zrobic:
     * 1. metoda wyswietlająca kierunek, tryb, rok, semestr
     * 2. metoda zapisująca do bazy
     * 3. jeżeli wyjść do czyści wszystkie choiceBoxy
     */

    @FXML
    private TextField fieldName;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField fieldSurname;

    @FXML
    private RadioButton radioBtnMale;


    @FXML
    private ChoiceBox<?> choiceBoxPeriod;

    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    private ChoiceBox<?> choiceBoxTitleStudy;

    @FXML
    private ChoiceBox<?> choiceBoxYear;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClearFields;

    @FXML
    private Button btnExit;

    @FXML
    private ChoiceBox<?> choiceBoxMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnExit.setOnAction(event -> btnSelectExit());
        btnClearFields.setOnAction(event -> clearField());
    }


    /**
     * metoda wywołana po wyborze przycisku btnExit
     * przechodzi do okna StudentWindowPane
     * czysci pola w oknie AddStudent
     */
    private void btnSelectExit(){
        Main.setSceneStudentWindow();
        clearField();

    }

    /**
     * metoda czyszcząca pole po wybraniu przycisku btnClearFields
     */
    private void clearField(){
        fieldName.setText("");
        fieldSurname.setText("");
        radioBtnMale.setSelected(false);
        radioBtnFemale.setSelected(false);
        choiceBoxTitleStudy.setValue(null);
        choiceBoxMode.setValue(null);
        choiceBoxYear.setValue(null);
        choiceBoxPeriod.setValue(null);
    }
}
