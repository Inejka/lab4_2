package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.Model;
import model.student.PublicWork;
import model.student.Student;
import support.IntShell;
import support.MyVoid;
import view.BottomPanel;
import view.StudentView;
import view.StudentsView;

public class StudentViewController {
    private IntShell currentPage = new IntShell(1, Integer.MAX_VALUE);
    private IntShell maxViewsPerPage;
    private Model model;
    private StudentsView studentsView = new StudentsView();
    private MyVoid updateStudentsViewWrapped = new MyVoid() {
        @Override
        public void performAction() {
            updateStudentsView();
        }
    };

    EventHandler<ActionEvent> jumpStart = e -> {
        currentPage.setNumber(1);
        updateStudentsView();
    };

    EventHandler<ActionEvent> jumpEnd = e -> {
        if (maxViewsPerPage.getNumber() > model.getStudentsCount()) currentPage.setNumber(1);
        else
            currentPage.setNumber(model.getStudentsCount() / maxViewsPerPage.getNumber() + 1);
        updateStudentsView();
    };

    public StudentViewController(Pane addTo, Model model, int maxViewsPerPageInit) {
        maxViewsPerPage = new IntShell(20, maxViewsPerPageInit);
        this.model = model;
        addTo.getChildren().add(studentsView);
        studentsView.prefHeightProperty().bind(addTo.heightProperty());
        addTo.getChildren().addAll(new Separator(), new BottomPanel(maxViewsPerPage, currentPage,
                updateStudentsViewWrapped, jumpStart, jumpEnd));
        updateStudentsView();
    }

    public void updateStudentsView() {
        studentsView.clear();
        for (int i = 0; i < maxViewsPerPage.getNumber(); i++) {
            Student currentStudent = model.getStudent(maxViewsPerPage.getNumber() * (currentPage.getNumber() - 1) + i);
            if (currentStudent != null)
                new StudentView(studentsView, currentStudent.getFio().getSurname() +' '+ currentStudent.getFio().getName() +' '+
                        currentStudent.getFio().getPatronymic(), String.valueOf(currentStudent.getGroup().getNumber()),
                        transform(currentStudent.getSemesters())
                );
        }
    }

    private String[] transform(PublicWork[] toTransform) {
        String[] toReturn = new String[10];
        for (int i = 0; i < 10; i++)
            toReturn[i] = String.valueOf(toTransform[i].getHours());
        return toReturn;
    }
}
