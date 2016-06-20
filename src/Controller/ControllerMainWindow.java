package Controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainWindow implements Initializable {


    @FXML
    private Button btnDziekan;

    @FXML
    private Button btnKierownik;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDziekan.setOnAction(event -> Main.setSceneMainWindowDziekan());
        btnKierownik.setOnAction(event -> Main.setSceneMainWindowKierownik());

    }


}