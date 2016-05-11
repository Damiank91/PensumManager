package Model.Student;

/**
 * Created by Damian on 2016-04-21.
 */
public class Student {
    private int idStudent;
    private String nameStudent;
    private String surnameStudent;
    private String bornDate;
    private char gender;
    private int idTrybu;
    private int idKierunku;
    private int idGrupySzkoleniowej;
    private int rok;
    private int semestrStudiow;
    private boolean czyStudentMaWarunek;

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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getIdTrybu() {
        return idTrybu;
    }

    public void setIdTrybu(int idTrybu) {
        this.idTrybu = idTrybu;
    }

    public int getIdKierunku() {
        return idKierunku;
    }

    public void setIdKierunku(int idKierunku) {
        this.idKierunku = idKierunku;
    }

    public int getIdGrupySzkoleniowej() {
        return idGrupySzkoleniowej;
    }

    public void setIdGrupySzkoleniowej(int idGrupySzkoleniowej) {
        this.idGrupySzkoleniowej = idGrupySzkoleniowej;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getSemestrStudiow() {
        return semestrStudiow;
    }

    public void setSemestrStudiow(int semestrStudiow) {
        this.semestrStudiow = semestrStudiow;
    }

    public boolean isCzyStudentMaWarunek() {
        return czyStudentMaWarunek;
    }

    public void setCzyStudentMaWarunek(boolean czyStudentMaWarunek) {
        this.czyStudentMaWarunek = czyStudentMaWarunek;
    }

    public Student(String nameStudent, String surnameStudent, String bornDate, char gender,
                   int idTrybu, int idKierunku, int idGrupySzkoleniowej, int rok, int semestrStudiow, boolean czyStudentMaWarunek) {
        this.nameStudent = nameStudent;
        this.surnameStudent = surnameStudent;
        this.bornDate = bornDate;
        this.gender = gender;
        this.idTrybu = idTrybu;
        this.idKierunku = idKierunku;
        this.idGrupySzkoleniowej = idGrupySzkoleniowej;
        this.rok = rok;
        this.semestrStudiow = semestrStudiow;
        this.czyStudentMaWarunek = czyStudentMaWarunek;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", nameStudent='" + nameStudent + '\'' +
                ", surnameStudent='" + surnameStudent + '\'' +
                ", bornDate='" + bornDate + '\'' +
                ", gender='" + gender + '\'' +
                ", idTrybu=" + idTrybu +
                ", idKierunku=" + idKierunku +
                ", idGrupySzkoleniowej=" + idGrupySzkoleniowej +
                ", rok=" + rok +
                ", semestrStudiow=" + semestrStudiow +
                ", czyStudentMaWarunek=" + czyStudentMaWarunek +
                '}';
    }
}
