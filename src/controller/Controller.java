package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Model;
import model.student.PublicWork;
import model.student.Student;
import support.IntShell;
import support.MyVoid;
import view.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {
    IntShell currentPage = new IntShell(1);
    IntShell maxViewsPerPage = new IntShell(2);
    private View view;
    private Model model;
    StudentsView studentsView = new StudentsView();

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
        currentPage.setNumber(model.getStudentsCount() / maxViewsPerPage.getNumber());
        updateStudentsView();
    };

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.getChildren().add(studentsView);
        studentsView.prefHeightProperty().bind(view.heightProperty());
        view.button1.setOnAction(actionEvent -> {
            AddView toAdd = new AddView();
            if (toAdd.isStudentAdded()) {
                model.addStudent(toAdd.getSurname(), toAdd.getName(), toAdd.getPatronymic(), toAdd.getGroup(),
                        model.transform(toAdd.getWorks()));
                updateStudentsView();
            }
        });
        studentsView.getChildren().add(new Separator());
        view.getChildren().addAll(new Separator(), new BottomPanel(maxViewsPerPage, currentPage,
                updateStudentsViewWrapped, jumpStart, jumpEnd));
        updateStudentsView();
    }


    public void updateStudentsView() {
        studentsView.clear();
        for (int i = 0; i < maxViewsPerPage.getNumber(); i++) {
            Student currentStudent = model.getStudent(maxViewsPerPage.getNumber() * (currentPage.getNumber()-1) + i);
            if (currentStudent != null)
                new StudentView(studentsView, currentStudent.getFio().getSurname() + currentStudent.getFio().getName() +
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
