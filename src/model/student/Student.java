package model.student;

public class Student {
    FIO fio;
    Group group;
    PublicWork[] semesters = new PublicWork[10];

    public Student(FIO fio, Group group, PublicWork[] semesters) {
        this.fio = fio;
        this.group = group;
        this.semesters = semesters;
    }

    public FIO getFio() {
        return fio;
    }

    public Group getGroup() {
        return group;
    }

    public PublicWork[] getSemesters() {
        return semesters;
    }

}
