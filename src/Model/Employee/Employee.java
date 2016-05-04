package Model.Employee;

/**
 * Created by Damian on 2016-04-21.
 */
public class Employee {

    private int idEmploye;
    private String name;
    private String surname;
    private String birthDate;
    private char gender;
    private boolean isManager;
    private int idCathedral;
    private int pensum;
    private String insert_time;
    private String change_time;

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public int getIdCathedral() {
        return idCathedral;
    }

    public void setIdCathedral(int idCathedral) {
        this.idCathedral = idCathedral;
    }

    public int getPensum() {
        return pensum;
    }

    public void setPensum(int pensum) {
        this.pensum = pensum;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getChange_time() {
        return change_time;
    }

    public void setChange_time(String change_time) {
        this.change_time = change_time;
    }

    public Employee(String name, String surname, String birthDate, char gender, boolean isManager, int idCathedral,int pensum){
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.isManager = isManager;
        this.idCathedral = idCathedral;
        this.pensum = pensum;
    }
    public Employee(int idEmploye, String name, String surname, String birthDate, char gender, boolean isManager, int idCathedral,int pensum){
        this(name,surname,birthDate,gender,isManager,idCathedral,pensum);
        this.idEmploye = idEmploye;
    }

    public Employee(int idEmploye, String name, String surname, String birthDate, char gender, boolean isManager, int idCathedral,int pensum, String insert_time) {
        this(idEmploye, name, surname,birthDate, gender,isManager,idCathedral,pensum);
        this.insert_time = insert_time;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmploye='" + idEmploye + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender=" + gender +
                ", isManager=" + isManager +
                ", idCathedral=" + idCathedral +
                ", pensum=" + pensum +
                ", insert_time='" + insert_time + '\'' +
                ", change_time='" + change_time + '\'' +
                '}';
    }
}
