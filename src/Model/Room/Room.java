package Model.Room;

/**
 * Created by Damian on 2016-06-02.
 */
public class Room {
    private int idRoom;
    private String nrRoom;
    private String insert_time;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNrRoom() {
        return nrRoom;
    }

    public void setNrRoom(String nrRoom) {
        this.nrRoom = nrRoom;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public Room(int idRoom, String nrRoom) {
        this.idRoom = idRoom;
        this.nrRoom = nrRoom;
    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", nrRoom='" + nrRoom + '\'' +
                ", insert_time='" + insert_time + '\'' +
                '}';
    }
}
