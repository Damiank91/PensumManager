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
    private Parent parentScheduleWindowPane;
    private Parent parentCreateScheduleWindowPane;


    //okno w ktorym znajduje sie leyaut
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
    private static Scene sceneScheduleWindow;
    private static Scene sceneCreateScheduleWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;

        initializeParent();


       // sceneMainWindow = new Scene(parentMainWindow);

        //Emloyee
       // sceneEmployeeWindow = new Scene(parentEmployeeWindowPane);
       // sceneAddEmployeeWindow = new Scene(parentAddEmployeeWindowPane);
       // sceneSearchEmployeeWindow = new Scene(parentSearchEmployeeWindowPane);


        //Student
       // sceneStudentWindow = new Scene(parentStudentWindowPane);
      //  sceneAddStudentWindow = new Scene(parentAddStudentWindowPane);
     //   sceneSearchStudentWindow = new Scene(parentSearchStudentWindowPane);

        //Schedule
      //   sceneScheduleWindow = new Scene(parentScheduleWindowPane);
         sceneCreateScheduleWindow = new Scene(parentCreateScheduleWindowPane);

        window.setScene(sceneCreateScheduleWindow); // ustaw sceneMainWindow na koniec
        window.setTitle(APP_NAME);
        window.show();

    }

    //metoda inicjalizuj¹ca wszystkie pliki fxml w celu ich wyœwietlenia
    private void initializeParent(){
        try{
            //employee
          //  parentMainWindow = (Parent) FXMLLoader.load(getClass().getResource("/View/MainWindowPane.fxml"));
         //   parentEmployeeWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Employee/EmployeeWindowPane.fxml"));
        //    parentAddEmployeeWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Employee/AddEmployee.fxml"));
         //   parentSearchEmployeeWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Employee/SearchEmployee.fxml"));


            //student
         //   parentStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/StudentWindowPane.fxml"));
         //   parentAddStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/AddStudent.fxml"));
       //     parentSearchStudentWindowPane = (Parent)FXMLLoader.load(getClass().getResource("/View/Student/SearchStudent.fxml"));

            //schedule
         //   parentScheduleWindowPane = (Parent) FXMLLoader.load(getClass().getResource("/View/ScheduleSubject/ScheduleSubjectWindowPane.fxml"));
            parentCreateScheduleWindowPane = (Parent) FXMLLoader.load(getClass().getResource("/View/ScheduleSubject/CreateSchedule.fxml"));

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
        window.setScene(sceneScheduleWindow);
    }

    public static void setSceneCreateScheduleWindow(){
        window.setScene(sceneCreateScheduleWindow);
    }

}
