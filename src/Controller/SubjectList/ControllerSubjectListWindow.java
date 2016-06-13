package Controller.SubjectList;
import Controller.Main;
import Model.Student.StudentTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damian on 2016-04-21.
 */
public class ControllerSubjectListWindow implements Initializable {

    @FXML
    private Button btnSearchSubject;

    @FXML
    private Button btnAddSubject;

    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddSubject.setOnAction(event -> Main.setSceneAddStubjectWindow());
        btnSearchSubject.setOnAction(event1 -> Main.setSceneSearchSubjectWindow());
        btnExit.setOnAction(event -> Main.setSceneMainWindow());
    }


}
