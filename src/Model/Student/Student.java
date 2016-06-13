package Model.Student;

/**
 * Created by Damian on 2016-04-21.
 */
public class Student {
    private int idStudent;
    private String nameStudent;
    private String surnameStudent;
    private String bornDate;
    private String gender;
    private int idKierunku;
    private String tryb;
    private int semestrStudiow;
    private String insert_time;



    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getSurnameStudent() {
        return surnameStudent;
    }

    public void setSurnameStudent(String surnameStudent) {
        this.surnameStudent = surnameStudent;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTrybu() {
        return tryb;
    }

    public void setIdTrybu(String idTrybu) {
        this.tryb = tryb;
    }

    public int getIdKierunku() {
        return idKierunku;
    }

    public void setIdKierunku(int idKierunku) {
        this.idKierunku = idKierunku;
    }


    public int getSemestrStudiow() {
        return semestrStudiow;
    }

    public void setSemestrStudiow(int semestrStudiow) {
        this.semestrStudiow = semestrStudiow;
    }


    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public Student(String nameStudent, String surnameStudent, String bornDate, String gender,
                   String tryb, int idKierunku, int semestrStudiow) {
        this.nameStudent = nameStudent;
        this.surnameStudent = surnameStudent;
        this.bornDate = bornDate;
        this.gender = gender;
        this.tryb = tryb;
        this.idKierunku = idKierunku;
        this.semestrStudiow = semestrStudiow;
    }


    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", nameStudent='" + nameStudent + '\'' +
                ", surnameStudent='" + surnameStudent + '\'' +
                ", bornDate='" + bornDate + '\'' +
                ", gender=" + gender +
                ", tryb='" + tryb + '\'' +
                ", idKierunku=" + idKierunku +
                ", semestrStudiow=" + semestrStudiow +
                ", insert_time='" + insert_time + '\'' +
                '}';
    }
}
