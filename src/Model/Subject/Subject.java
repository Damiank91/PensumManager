package Model.Subject;

/**
 * Created by Damian on 2016-05-30.
 */
public class Subject {
    private int id_subject;
    private String name_subject;
    private int id_cathedral;
    private String insert_time;

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public int getId_cathedral() {
        return id_cathedral;
    }

    public void setId_cathedral(int id_cathedral) {
        this.id_cathedral = id_cathedral;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public Subject() {
    }

    public Subject(int id_subject, String name_subject) {
        this.id_subject = id_subject;
        this.name_subject = name_subject;
    }

    public Subject(int id_subject, String name_subject, int id_cathedral) {
        this.id_subject = id_subject;
        this.name_subject = name_subject;
        this.id_cathedral = id_cathedral;
    }

    public Subject(int id_subject, String name_subject, int id_cathedral, String insert_time) {
        this.id_subject = id_subject;
        this.name_subject = name_subject;
        this.id_cathedral = id_cathedral;
        this.insert_time = insert_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

        if (getId_subject() != subject.getId_subject()) return false;
        if (getId_cathedral() != subject.getId_cathedral()) return false;
        if (getName_subject() != null ? !getName_subject().equals(subject.getName_subject()) : subject.getName_subject() != null)
            return false;
        return !(getInsert_time() != null ? !getInsert_time().equals(subject.getInsert_time()) : subject.getInsert_time() != null);

    }

    @Override
    public int hashCode() {
        int result = getId_subject();
        result = 31 * result + (getName_subject() != null ? getName_subject().hashCode() : 0);
        result = 31 * result + getId_cathedral();
        result = 31 * result + (getInsert_time() != null ? getInsert_time().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id_subject=" + id_subject +
                ", name_subject='" + name_subject + '\'' +
                ", id_cathedral=" + id_cathedral +
                ", insert_time='" + insert_time + '\'' +
                '}';
    }
}
