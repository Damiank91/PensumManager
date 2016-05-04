package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainWindow implements Initializable {

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnSubjectList;

    @FXML
    private Button btnGroupStudents;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnScheduleList;

    @FXML
    private Button btnRooms;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnEmployee.setOnAction(event -> Main.setSceneEmployeeWindow());
        btnStudents.setOnAction(event -> Main.setSceneStudentWindow());

    }
}