package Model.Subject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Damian on 2016-06-13.
 */
public class SubjectTable {
    private SimpleIntegerProperty idSubject;
    private SimpleStringProperty nameSubject;
    private SimpleStringProperty nameCathedral;
    private SimpleStringProperty insertTime;

    public int getIdSubject() {
        return idSubject.get();
    }

    public SimpleIntegerProperty idSubjectProperty() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject.set(idSubject);
    }

    public String getNameSubject() {
        return nameSubject.get();
    }

    public SimpleStringProperty nameSubjectProperty() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject.set(nameSubject);
    }

    public String getNameCathedral() {
        return nameCathedral.get();
    }

    public SimpleStringProperty NameCathedral() {
        return nameCathedral;
    }

    public void setNameCathedral(String nameCathedral) {
        this.nameCathedral.set(nameCathedral);
    }

    public String getInsertTime() {
        return insertTime.get();
    }

    public SimpleStringProperty insertTimeProperty() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime.set(insertTime);
    }

    public SubjectTable(int idSubject, String nameSubject, String nameCathedral) {
        this.idSubject = new SimpleIntegerProperty(idSubject);
        this.nameSubject = new SimpleStringProperty(nameSubject);
        this.nameCathedral = new SimpleStringProperty(nameCathedral);
    }

    @Override
    public String toString() {
        return "SubjectTable{" +
                "idSubject=" + idSubject +
                ", nameSubject=" + nameSubject +
                ", nameCathedral=" + nameCathedral +
                ", insertTime=" + insertTime +
                '}';
    }
}
