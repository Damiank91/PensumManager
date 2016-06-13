package Controller;

import javafx.application.Application;
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



    //student
    private Parent parentStudentWindowPane;
    private Parent parentAddStudentWindowPane;
    private Parent parentSearchStudentWindowPane;

    //schedule
    private Parent parentCreateScheduleWindowPane;

    //subject
    private Parent parentSubjectWindowPane;
    private Parent parentAddSubjectWindowPane;
    private Parent parentSearchSubjectWindowPane;


    //okno w ktorym znajduje sie layout
    //emplyee
    private static Scene sceneMainWindow;
    private static Scene sceneEmployeeWindow;
    private static Scene sceneAddEmployeeWindow;
    private static Scene sceneSearchEmployeeWindow;


    //student
    private static Scene sceneStudentWindow;
    private static Scene sceneAddStudentWindow;
    private static Scene sceneSearchStudentWindow;

    //schedule
    private static Scene sceneCreateScheduleWindow;

    //subject
    private static Scene sceneSubjectWindow;
    private static Scene sceneAddStubjectWindow;
    private static Scene sceneSearchSubjectWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;

        initializeParent();


        sceneMainWindow = new Scene(parentMainWindow);

        //Emloyee
        sceneEmployeeWindow = new Scene(parentEmployeeWindowPane);
        sceneAddEmployeeWindow = new Scene(parentAddEmployeeWindowPane);
        sceneSearchEmployeeWindow = new Scene(parentSearchEmployeeWindowPane);


        //Student
        sceneStudentWindow = new Scene(parentStudentWindowPane);
        sceneAddStudentWindow = new Scene(parentAddStudentWindowPane);
        sceneSearchStudentWindow = new Scene(parentSearchStudentWindowPane);

        //Schedule
         sceneCreateScheduleWindow = new Scene(parentCreateScheduleWindowPane);

        //subject
        sceneSubjectWindow = new Scene(parentSubjectWindowPane);
        sceneAddStubjectWindow = new Scene(parentAddSubjectWindowPane);
        sceneSearchSubjectWindow = new Scene(parentSearchSubjectWindowPane);

        window.setScene(sceneMainWindow); // ustaw sceneMainWindow na koniec
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


            //student
            parentStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/StudentWindowPane.fxml"));
            parentAddStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/AddStudent.fxml"));
            parentSearchStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/SearchStudent.fxml"));

            //schedule
            parentCreateScheduleWindowPane = (Parent) FXMLLoader.load(getClass().getResource("/View/ScheduleSubject/CreateSchedule.fxml"));

            //subject
            parentSubjectWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/SubjectList/SubjectListWindowPane.fxml"));
            parentAddSubjectWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/SubjectList/AddSubject.fxml"));
            parentSearchSubjectWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/SubjectList/SearchSubject.fxml"));

        }catch (IOException ex){
            System.out.println("Nie uda³o siê za³adowaæ plików FXML");
        }
    }

    //main window
    public static void setSceneMainWindow(){
        window.setScene(sceneMainWindow);
        window.setX(220);
        window.setY(100);
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

    //schedule
    public static void setSceneScheduleWindow(){
        window.setScene(sceneCreateScheduleWindow);
        window.setX(10);
        window.setY(10);
    }

    //subject
    public static void setSceneSubjectWindow(){
        window.setScene(sceneSubjectWindow);
    }

    public static void setSceneAddStubjectWindow(){
        window.setScene(sceneAddStubjectWindow);
    }

    public static void setSceneSearchSubjectWindow(){
        window.setScene(sceneSearchSubjectWindow);
    }

}
