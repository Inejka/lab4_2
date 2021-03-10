package model;

import model.student.FIO;
import model.student.Group;
import model.student.PublicWork;
import model.student.Student;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Model {


    public void setStudents(List<Student> students) {
        this.students = students;
    }

    private List<Student> students;

    public Model() {
        students = new LinkedList<>();
    }

    public Model(List<Student> students) {
        this.students = students;
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

    public List<Student> searchStudent(Group group) {
        List<Student> toReturn = new LinkedList<>();
        for (Student i : students)
            if (i.getGroup().equals(group))
                toReturn.add(i);
        return toReturn;
    }

    public List<Student> searchStudent(String surname) {
        List<Student> toReturn = new LinkedList<>();
        for (Student i : students)
            if (i.getFio().getSurname().equals(surname))
                toReturn.add(i);
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

    public PublicWork[] transform(int[] toTransform) {
        PublicWork[] toReturn = new PublicWork[10];
        for (int i = 0; i < 10; i++)
            toReturn[i] = new PublicWork(toTransform[i]);
        return toReturn;
    }

    public List<Integer> getGroupsId() {
        Set<Integer> set = new LinkedHashSet<>();
        for (Student i : students)
            set.add(i.getGroup().getNumber());
        return new LinkedList<>(set);
    }

    public List<Student> getStudents() {
        return students;
    }

}
