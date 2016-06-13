package Model.ScheduleSubject;

/**
 * Created by Damian on 2016-06-02.
 */
public class ScheduleSubject {
    private int id;
    private int idEmployee;
    private int idRoom;
    private int idSubject;
    private int idDaysWeek;
    private int idTimeWork;
    private String insert_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdDaysWeek() {
        return idDaysWeek;
    }

    public void setIdDaysWeek(int idDaysWeek) {
        this.idDaysWeek = idDaysWeek;
    }

    public int getIdTimeWork() {
        return idTimeWork;
    }

    public void setIdTimeWork(int idTimeWork) {
        this.idTimeWork = idTimeWork;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }


    public ScheduleSubject(int idEmployee,int idRoom, int idSubject, int idDaysWeek, int idTimeWork) {
        this.idEmployee = idEmployee;
        this.idRoom = idRoom;
        this.idSubject = idSubject;
        this.idDaysWeek = idDaysWeek;
        this.idTimeWork = idTimeWork;
    }



    public ScheduleSubject(int id, int idEmployee, int idRoom, int idSubject, int idDaysWeek, int idTimeWork) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idRoom = idRoom;
        this.idSubject = idSubject;
        this.idDaysWeek = idDaysWeek;
        this.idTimeWork = idTimeWork;

    }

    @Override
    public String toString() {
        return "ScheduleSubject{" +
                "id=" + id +
                ", idEmployee=" + idEmployee +
                ", idSubject=" + idSubject +
                ", idDaysWeek=" + idDaysWeek +
                ", idTimeWork=" + idTimeWork +
                '}';
    }
}
