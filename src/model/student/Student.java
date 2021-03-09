package model.student;

public class Student {
    FIO fio;
    Group group;
    PublicWork[] semesters = new PublicWork[10];

    public Student(FIO fio, Group group) {
        this.fio = fio;
        this.group = group;
    }

    public Student(FIO fio, Group group, PublicWork[] semesters) {
        this.fio = fio;
        this.group = group;
        this.semesters = semesters;
    }

    public FIO getFio() {
        return fio;
    }

    public void setFio(FIO fio) {
        this.fio = fio;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public PublicWork[] getSemesters() {
        return semesters;
    }

    public void setSemesters(PublicWork[] semesters) {
        this.semesters = semesters;
    }
}
