package Controller.Student;

import Controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerStudentWindow implements Initializable {


    @FXML
    private Button btnSearchStudent;


    @FXML
    private Button btnExit;

    @FXML
    private Button btnAddStudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddStudent.setOnAction(event -> Main.setSceneAddStudentWindow());
        btnSearchStudent.setOnAction(event1 -> Main.setSceneSearchStudentWindow());
        btnExit.setOnAction(event -> Main.setSceneMainWindow());
    }
}
