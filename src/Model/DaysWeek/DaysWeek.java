package Model.DaysWeek;

/**
 * Created by Damian on 2016-06-02.
 */
public class DaysWeek {

    private int idDaysWeek;
    private String nameDays;
    private String insert_Time;

    public int getIdDaysWeek() {
        return idDaysWeek;
    }

    public void setIdDaysWeek(int idDaysWeek) {
        this.idDaysWeek = idDaysWeek;
    }

    public String getNameDays() {
        return nameDays;
    }

    public void setNameDays(String nameDays) {
        this.nameDays = nameDays;
    }

    public String getInsert_Time() {
        return insert_Time;
    }

    public void setInsert_Time(String insert_Time) {
        this.insert_Time = insert_Time;
    }

    public DaysWeek() {
    }

    public DaysWeek(int idDaysWeek, String nameDays) {
        this.idDaysWeek = idDaysWeek;
        this.nameDays = nameDays;
    }

    @Override
    public String toString() {
        return "DaysWeek{" +
                "idDaysWeek=" + idDaysWeek +
                ", nameDays='" + nameDays + '\'' +
                ", insert_Time='" + insert_Time + '\'' +
                '}';
    }
}
