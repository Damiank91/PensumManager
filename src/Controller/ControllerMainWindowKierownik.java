package Controller;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-06-19.
 */

public class ControllerMainWindowKierownik implements Initializable {


    @FXML
    private Button btnSubjectList;

    @FXML
    private Button btnAddSubject;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnStudentList;

    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStudentList.setOnAction(event -> Main.setSceneSearchStudentWindow());
        btnSubjectList.setOnAction(event -> Main.setSceneSearchSubjectWindow());
        btnAddSubject.setOnAction(event -> Main.setSceneAddStubjectWindow());
        btnAddEmployee.setOnAction(event -> Main.setSceneAddEmployeeWindow());
        btnExit.setOnAction(event -> Main.setSceneMainWindow());
    }
}
