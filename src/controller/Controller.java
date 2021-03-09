package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import model.Model;
import model.student.Group;
import model.student.PublicWork;
import model.student.Student;
import support.IntShell;
import view.*;

public class Controller {
    IntShell currentPage = new IntShell(1, Integer.MAX_VALUE);
    IntShell maxViewsPerPage = new IntShell(30, 40);
    private View view;
    private Model model;
    StudentsView studentsView = new StudentsView();

    public Controller(View view, Model model) {
        StudentViewController test = new StudentViewController(view, model, 40);
        this.model = model;
        this.view = view;
        view.button1.setOnAction(actionEvent -> {
            AddDialog toAdd = new AddDialog();
            if (toAdd.isStudentAdded()) {
                model.addStudent(toAdd.getSurname(), toAdd.getName(), toAdd.getPatronymic(), toAdd.getGroup(),
                        model.transform(toAdd.getWorks()));
                test.updateStudentsView();
            }
        });
        view.button2.setOnAction(actionEvent -> {
            SearchDialog search = new SearchDialog(model.getGroupsId(), "Search dialog");
            Button searchButton = new Button("Search");
            searchButton.setOnAction(e -> {
                search.updateUI();
                Model toShow;
                if (search.isGroupSelected()) {
                    if (search.isLimitsSelected())
                        toShow = new Model(model.searchStudent(new Group(search.getGroup()), search.getLowerLinit(), search.getUpperLimit()));
                    else toShow = new Model(model.searchStudent(new Group(search.getGroup())));
                } else if (search.isLimitsSelected())
                    toShow = new Model(model.searchStudent(search.getSurname(), search.getLowerLinit(), search.getUpperLimit()));
                else
                    toShow = new Model(model.searchStudent(search.getSurname()));
                StudentViewController studentViewController = new StudentViewController(search, toShow, 10);
            });
            search.fourthBoxInit(searchButton);
            search.start();
        });
    }
}
