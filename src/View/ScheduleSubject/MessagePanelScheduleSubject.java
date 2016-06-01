package View.ScheduleSubject;

import javafx.scene.control.Alert;

/**
 * Created by Damian on 2016-06-01.
 */
public class MessagePanelScheduleSubject {

    public MessagePanelScheduleSubject(){

    }

    public void showErrorMessage(String messageText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(messageText);
        alert.showAndWait();
    }

    public void showInformationMessage(String messageText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setHeaderText("Gratulacje");
        alert.setContentText(messageText);
        alert.showAndWait();
    }
}
