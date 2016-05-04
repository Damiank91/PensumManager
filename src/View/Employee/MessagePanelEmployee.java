package View.Employee;

import javafx.scene.control.Alert;

/**
 * Created by Damian on 2016-04-25.
 */
public class MessagePanelEmployee {

    public MessagePanelEmployee(){

    }

    public void addEmployeNoChoice(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ostrzeżenie");
        alert.setHeaderText("Wystąpił błąd podczas zapisu pracownika");
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
