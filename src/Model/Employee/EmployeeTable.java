package Model.Employee;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Damian on 2016-05-03.
 */
public class EmployeeTable {

    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty cathedral;
    private SimpleIntegerProperty idEmployee;


    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getCathedral() {
        return cathedral.get();
    }

    public void setCathedral(String cathedral) {
        this.cathedral.set(cathedral);
    }

    public int getIdEmployee() {
        return idEmployee.get();
    }

    public SimpleIntegerProperty idEmployeeProperty() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee.set(idEmployee);
    }

    public EmployeeTable(String name, String surname, String cathedral) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.cathedral = new SimpleStringProperty(cathedral);
    }

    public EmployeeTable(int idEmployee,String name, String surname, String cathedral){
        this(name,surname,cathedral);
        this.idEmployee = new SimpleIntegerProperty(idEmployee);
    }

    @Override
    public String toString() {
        return "EmployeeTable{" +
                "name=" + name +
                ", surname=" + surname +
                ", cathedral=" + cathedral +
                ", idEmployee=" + idEmployee +
                '}';
    }
}
