package controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.Model;
import model.student.PublicWork;
import model.student.Student;
import view.tableComponents.MyTableView;
import view.tableComponents.StudentView;

public class StudentViewControllerTest extends TableController {
    final Model model;

    public StudentViewControllerTest(int maxViewsPerPage,int currentMaxViewsPerPage, MyTableView tableView, Pane parent, Model model) {
        super(maxViewsPerPage,currentMaxViewsPerPage, tableView, parent);
        this.model = model;
    }

    @Override
    int getPageCount() {
        int left = model.getStudentsCount() - maxViewsPerPage.getNumber() * (currentPage.getNumber() - 1);
        return Math.min(left, maxViewsPerPage.getNumber());
    }

    @Override
    int getCount() {
        return model.getStudentsCount();
    }

    @Override
    Region getView(int pos) {
        Student currentStudent = model.getStudent(maxViewsPerPage.getNumber() * (currentPage.getNumber() - 1) + pos);
        return new StudentView(currentStudent.getFio().getSurname() + ' ' + currentStudent.getFio().getName() + ' ' +
                currentStudent.getFio().getPatronymic(), String.valueOf(currentStudent.getGroup().getNumber()),
                transform(currentStudent.getSemesters())
        );
    }

    private String[] transform(PublicWork[] toTransform) {
        String[] toReturn = new String[10];
        for (int i = 0; i < 10; i++)
            toReturn[i] = String.valueOf(toTransform[i].getHours());
        return toReturn;
    }
}
