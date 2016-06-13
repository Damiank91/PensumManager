package Model.TimeWork;

/**
 * Created by Damian on 2016-06-02.
 */
public class TimeWork {

    private int idTomeWork;
    private String name;
    private String insert_time;

    public int getIdTomeWork() {
        return idTomeWork;
    }

    public void setIdTomeWork(int idTomeWork) {
        this.idTomeWork = idTomeWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public TimeWork(){

    }

    public TimeWork(int idTomeWork, String name) {
        this.idTomeWork = idTomeWork;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TimeWork{" +
                "idTomeWork=" + idTomeWork +
                ", name='" + name + '\'' +
                ", insert_time='" + insert_time + '\'' +
                '}';
    }
}
