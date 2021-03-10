package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Model;
import model.student.Group;
import model.student.Student;
import support.IntShell;
import support.Loader;
import support.Saver;
import view.*;

import java.util.List;

public class Controller {
    IntShell currentPage = new IntShell(1, Integer.MAX_VALUE);
    IntShell maxViewsPerPage = new IntShell(30, 40);
    private View view;
    private Model model;
    StudentsView studentsView = new StudentsView();
    Stage parent;

    public Controller(View view, Model model, Stage parent) {
        StudentViewController test = new StudentViewController(view, model, 40);
        this.parent = parent;
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
            ChooseDialog search = new ChooseDialog(model.getGroupsId(), "Search dialog");
            Button searchButton = new Button("Search");
            searchButton.setOnAction(e -> {
                search.updateUI();
                List<Student> toShow = getStudents(model, search);
                StudentViewController studentViewController = new StudentViewController(search, new Model(toShow), 10);
            });
            search.fourthBoxInit(searchButton);
            search.start();
        });
        view.button3.setOnAction(actionEvent -> {
            ChooseDialog delete = new ChooseDialog(model.getGroupsId(), "Delete dialog");
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
                List<Student> toDelete = getStudents(model, delete);
                Alert toShow = new Alert((toDelete.size() == 0) ? Alert.AlertType.WARNING : Alert.AlertType.INFORMATION);
                toShow.setTitle("Delete information");
                toShow.setContentText((toDelete.size() == 0) ? "Не найдено записей,удовлетворющих критериям" :
                        "Удалено " + toDelete.size() + " записей"
                );
                toShow.setHeaderText("Информация о удалении записей");
                model.removeStudent(toDelete);
                test.updateStudentsView();
                toShow.show();
                delete.stop();
            });
            delete.fourthBoxInit(deleteButton);
            delete.start();
        });
        view.save.setOnAction(e -> {
            Saver saver = new Saver(model.getStudents(), parent);
            saver.save();
        });
        view.load.setOnAction(e -> {
            model.setStudents(new Model().getStudents());
            Loader loader = new Loader(model.getStudents(), parent);
            loader.load();
            test.updateStudentsView();
        });
    }

    private List<Student> getStudents(Model model, ChooseDialog search) {
        List<Student> toShow;
        if (search.isGroupSelected()) {
            if (search.isLimitsSelected())
                toShow = (model.searchStudent(new Group(search.getGroup()), search.getLowerLinit(), search.getUpperLimit()));
            else toShow = (model.searchStudent(new Group(search.getGroup())));
        } else if (search.isLimitsSelected())
            toShow = (model.searchStudent(search.getSurname(), search.getLowerLinit(), search.getUpperLimit()));
        else
            toShow = (model.searchStudent(search.getSurname()));
        return toShow;
    }
}
