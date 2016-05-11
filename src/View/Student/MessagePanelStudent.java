package View.Student;

import javafx.scene.control.Alert;

/**
 * Created by Damian on 2016-05-07.
 */
public class MessagePanelStudent {

    public MessagePanelStudent(){

    }

    public void addStudentNoChoice(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ostrze¿enie");
        alert.setHeaderText("Wyst¹pi³ b³¹d podczas zapisu studenta");
        alert.setContentText(contentText);
        alert.showAndWait();
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
