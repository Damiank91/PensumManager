package Controller.Employee;

import Controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEmployeeWindow implements Initializable {

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnSearchEmplyee;

    @FXML
    private Button btnExit;

    public ControllerEmployeeWindow(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddEmployee.setOnAction(event -> Main.setSceneAddEmployeeWindow());
        btnSearchEmplyee.setOnAction(event2 -> Main.setSceneSearchEmployeeWindow());
        btnExit.setOnAction(event -> Main.setSceneMainWindow());
    }


}
