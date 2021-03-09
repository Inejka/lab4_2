package model;

import model.student.FIO;
import model.student.Group;
import model.student.PublicWork;
import model.student.Student;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private List<Student> students;

    public Model() {
        students = new LinkedList<>();
    }

    public void addStudent(FIO fio, Group group) {
        students.add(new Student(fio, group));
    }

    public void addStudent(String surname, String name, String patronymic, int number) {
        students.add(new Student(new FIO(surname, name, patronymic), new Group(number)));
    }

    public void addStudent(String surname, String name, String patronymic, int number, PublicWork[] publicWorks) {
        students.add(new Student(new FIO(surname, name, patronymic), new Group(number), publicWorks));
    }

    public Student getStudent(int position) {
        if (position < students.size())
            return students.get(position);
        else return null;
    }

    public int getStudentsCount() {
        return students.size();
    }

    public void removeStudent(int position) {
        if (position < students.size())
            students.remove(position);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public List<Student> searchStudent(String surname, int lowerLimit, int upperLimit) {
        List<Student> toReturn = new LinkedList<>();
        for (Student i : students)
            if (surname.equals(i.getFio().getSurname())) {
                boolean toAdd = true;
                for (PublicWork j : i.getSemesters())
                    if (!(lowerLimit <= j.getHours() && upperLimit >= j.getHours())) {
                        toAdd = false;
                        break;
                    }
                if (toAdd) toReturn.add(i);
            }
        return toReturn;
    }

    public List<Student> searchStudent(Group group, int lowerLimit, int upperLimit) {
        List<Student> toReturn = new LinkedList<>();
        for (Student i : students)
            if (group.equals(i.getGroup())) {
                boolean toAdd = true;
                for (PublicWork j : i.getSemesters())
                    if (!(lowerLimit <= j.getHours() && upperLimit >= j.getHours())) {
                        toAdd = false;
                        break;
                    }
                if (toAdd) toReturn.add(i);
            }
        return toReturn;
    }

    public void removeStudent(List<Student> toDeleteList) {
        for (Student i : toDeleteList)
            removeStudent(i);
    }

    public PublicWork[] transform(int[] toTransfom) {
        PublicWork[] toReturn = new PublicWork[10];
        for (int i = 0; i < 10; i++)
            toReturn[i] = new PublicWork(toTransfom[i]);
        return toReturn;
    }

}
