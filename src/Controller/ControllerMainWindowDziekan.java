package Controller;

import Controller.ScheduleSubject.ControllerCreateSchedule;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-06-19.
 */
public class ControllerMainWindowDziekan implements Initializable {

    @FXML
    private Button btnSubjectList;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnScheduleList;

    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnEmployee.setOnAction(event -> Main.setSceneEmployeeWindow());
        btnScheduleList.setOnAction(event ->Main.setSceneScheduleWindow());
        btnStudents.setOnAction(event -> Main.setSceneStudentWindow());
        btnSubjectList.setOnAction(event -> Main.setSceneScheduleWindow());
        btnExit.setOnAction(event -> Main.setSceneMainWindow());
    }
}
