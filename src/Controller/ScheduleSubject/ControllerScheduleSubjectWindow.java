package Controller.ScheduleSubject;
import Controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerScheduleSubjectWindow implements Initializable {

    @FXML
    private Button btnShowSchedule;

    @FXML
    private Button btnEditSchedule;

    @FXML
    private Button btnCreateSchedule;

    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCreateSchedule.setOnAction(event -> Main.setSceneCreateScheduleWindow());
        btnExit.setOnAction(event -> Main.setSceneMainWindow());

    }
}
