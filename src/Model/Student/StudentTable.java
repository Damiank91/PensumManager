package Model.Student;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Damian on 2016-05-10.
 */
public class StudentTable {
    private SimpleIntegerProperty idStudent;
    private SimpleStringProperty nameStudent;
    private SimpleStringProperty surnameStudent;
    private SimpleStringProperty kierunek;
    private SimpleIntegerProperty semestr;

    public int getIdStudent() {
        return idStudent.get();
    }

    public SimpleIntegerProperty idStudentProperty() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent.set(idStudent);
    }

    public String getNameStudent() {
        return nameStudent.get();
    }

    public SimpleStringProperty nameStudentProperty() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent.set(nameStudent);
    }

    public String getSurnameStudent() {
        return surnameStudent.get();
    }

    public SimpleStringProperty surnameStudentProperty() {
        return surnameStudent;
    }

    public void setSurnameStudent(String surnameStudent) {
        this.surnameStudent.set(surnameStudent);
    }

    public String getKierunek() {
        return kierunek.get();
    }

    public SimpleStringProperty kierunekProperty() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek.set(kierunek);
    }

    public int getSemestr() {
        return semestr.get();
    }

    public SimpleIntegerProperty semestrProperty() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr.set(semestr);
    }


    public StudentTable(int idStudenta, String nameStudent, String surnameStudent,String kierunek, int semestr){
        this.idStudent = new SimpleIntegerProperty(idStudenta);
        this.nameStudent = new SimpleStringProperty(nameStudent);
        this.surnameStudent = new SimpleStringProperty(surnameStudent);
        this.kierunek = new SimpleStringProperty(kierunek);
        this.semestr = new SimpleIntegerProperty(semestr);
    }
}
