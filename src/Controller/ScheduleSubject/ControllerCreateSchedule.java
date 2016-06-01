package Controller.ScheduleSubject;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerCreateSchedule implements Initializable {

    @FXML
    private ChoiceBox<?> choiceBoxDay;

    @FXML
    private ChoiceBox<?> choiceBoxRoom;

    @FXML
    private ChoiceBox<?> choiceBoxTime;

    @FXML
    private Button btnSave;

    @FXML
    private GridPane gridPaneEmplyee;

    @FXML
    private ChoiceBox<?> choiceBoxEmployee;

    @FXML
    private GridPane gridPaneRoom;

    @FXML
    private Button btnClear;

    @FXML
    private ChoiceBox<?> choiceBoxSubject;

    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
