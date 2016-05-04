package Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Damian on 2016-04-26.
 */
public class Main extends Application {

    public static final String APP_NAME = "Pensum manager";

    //okno aplikacji
    private static Stage window;

    //okno dla scene
    //employee
    private Parent parentMainWindow;
    private Parent parentEmployeeWindowPane;
    private Parent parentAddEmployeeWindowPane;
    private Parent parentSearchEmployeeWindowPane;
    //private Parent parentEditEmployeeWindowPane;


    //student
    private Parent parentStudentWindowPane;
    private Parent parentAddStudentWindowPane;
    private Parent parentSearchStudentWindowPane;


    //okno w ktorym znajduje sie leyaut
    //emplyee
    private static Scene sceneMainWindow;
    private static Scene sceneEmployeeWindow;
    private static Scene sceneAddEmployeeWindow;
    private static Scene sceneSearchEmployeeWindow;
   // private static Scene sceneEditEmployeeWindow;

    //student
    private static Scene sceneStudentWindow;
    private static Scene sceneAddStudentWindow;
    private static Scene sceneSearchStudentWindow;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;

        initializeParent();

        //Emloyee
        sceneMainWindow = new Scene(parentMainWindow);
        sceneEmployeeWindow = new Scene(parentEmployeeWindowPane);
        sceneAddEmployeeWindow = new Scene(parentAddEmployeeWindowPane);
        sceneSearchEmployeeWindow = new Scene(parentSearchEmployeeWindowPane);
       // sceneEditEmployeeWindow = new Scene(parentEditEmployeeWindowPane);

        //Student
        sceneStudentWindow = new Scene(parentStudentWindowPane);
        sceneAddStudentWindow = new Scene(parentAddStudentWindowPane);
        sceneSearchStudentWindow = new Scene(parentSearchStudentWindowPane);


        window.setScene(sceneMainWindow);
        window.setTitle(APP_NAME);
        window.show();

    }

    //metoda inicjalizuj¹ca wszystkie pliki fxml w celu ich wyœwietlenia
    private void initializeParent(){
        try{
            //employee
            parentMainWindow = (Parent) FXMLLoader.load(getClass().getResource("/View/MainWindowPane.fxml"));
            parentEmployeeWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Employee/EmployeeWindowPane.fxml"));
            parentAddEmployeeWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Employee/AddEmployee.fxml"));
            parentSearchEmployeeWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Employee/SearchEmployee.fxml"));
         //   parentEditEmployeeWindowPane = (Parent) FXMLLoader.load(getClass().getResource("/View/Employee/EditEmployee.fxml"));

            //student
            parentStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/StudentWindowPane.fxml"));
            parentAddStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/AddStudent.fxml"));
            parentSearchStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/SearchStudent.fxml"));


        }catch (IOException ex){
            System.out.println("Nie uda³o siê za³adowaæ plików FXML");
        }
    }

    //main window
    public static void setSceneMainWindow(){
        window.setScene(sceneMainWindow);
    }


    //employee windows
    public static void setSceneEmployeeWindow(){
        window.setScene(sceneEmployeeWindow);
    }

    public static void setSceneAddEmployeeWindow(){
        window.setScene(sceneAddEmployeeWindow);
    }

    public static void setSceneSearchEmployeeWindow(){
        window.setScene(sceneSearchEmployeeWindow);
    }

 //   public static void setSceneEditEmployeeWindow(){ window.setScene(sceneEditEmployeeWindow);}
    public static void hideMainWIndow(){
        window.hide();
    }

    public static void showMainWindow(){
        window.show();
    }


    //student windows
    public static void setSceneStudentWindow(){
        window.setScene(sceneStudentWindow);
    }

    public static void setSceneAddStudentWindow(){
        window.setScene(sceneAddStudentWindow);
    }

    public static void setSceneSearchStudentWindow(){
        window.setScene(sceneSearchStudentWindow);
    }

}
