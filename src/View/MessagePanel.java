package View;

import javafx.scene.control.Alert;

/**
 * Created by Damian on 2016-06-13.
 */
public class MessagePanel {

    public MessagePanel(){

    }

    public void addNoChoice(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ostrzeżenie");
        alert.setHeaderText("Wystąpił błąd podczas zapisu danych");
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
