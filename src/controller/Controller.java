package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.student.Group;
import model.student.Student;
import support.fileWorkers.Loader;
import support.fileWorkers.Saver;
import view.dialogs.AddDialog;
import view.dialogs.ChooseDialog;
import view.mainComponents.MyMenuBar;
import view.mainComponents.MyToolBar;
import view.tableComponents.MyTableView;
import view.tableComponents.TableHat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Controller {
    private VBox view;
    private Model model;
    Stage parent;
    StudentViewControllerTest studentViewControllerTest;

    final EventHandler<ActionEvent> addStudentEvent = actionEvent -> {
        AddDialog toAdd = new AddDialog();
        if (toAdd.isStudentAdded()) {
            model.addStudent(toAdd.getSurname(), toAdd.getName(), toAdd.getPatronymic(), toAdd.getGroup(),
                    model.transform(toAdd.getWorks()));
            studentViewControllerTest.updateTable();
        }
    };

    final EventHandler<ActionEvent> searchEvent = actionEvent -> {
        ChooseDialog search = new ChooseDialog(model.getGroupsId(), "Search dialog");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            search.updateUI();
            List<Student> toShow = getStudents(model, search);
            MyTableView tableView = new MyTableView();
            StudentViewControllerTest innerController = new StudentViewControllerTest(10, 10, tableView, search, new Model(toShow));
            tableView.setController(innerController);
            search.getChildren().add(tableView);
            tableView.bottomPanelInit();
            tableView.setHat(new TableHat());
            innerController.updateTable();
        });
        search.fourthBoxInit(searchButton);
        search.start();
    };

    final EventHandler<ActionEvent> deleteEvent = actionEvent -> {
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
            studentViewControllerTest.updateTable();
            toShow.show();
            delete.stop();
        });
        delete.fourthBoxInit(deleteButton);
        delete.start();
    };

    final EventHandler<ActionEvent> saveEvent = actionEvent -> {
        Saver saver = new Saver(model.getStudents(), parent);
        saver.save();
    };

    final EventHandler<ActionEvent> loadEvent = actionEvent -> {
        model.setStudents(new Model().getStudents());
        Loader loader = new Loader(model.getStudents(), parent);
        loader.load();
        studentViewControllerTest.updateTable();
    };

    final EventHandler<ActionEvent> helpEvent = actionEvent -> {
        parent.close();
        Stage stage = new Stage();
        try {
            ImageView imageView = new ImageView(new Image(new FileInputStream("images/img.png")));
            Pane pane = new Pane();
            pane.getChildren().add(imageView);
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            imageView.fitHeightProperty().bind(pane.heightProperty());
            imageView.fitWidthProperty().bind(pane.widthProperty());
            stage.setTitle("Ты чиво наделал.............");
            stage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    };


    public Controller(VBox view, Model model, Stage parent) {
        this.parent = parent;
        this.model = model;
        this.view = view;
        viewInit();
        MyTableView myTableView = new MyTableView();
        studentViewControllerTest = new StudentViewControllerTest(40, 20, myTableView, view, model);
        myTableView.setController(studentViewControllerTest);
        view.getChildren().add(myTableView);
        myTableView.bottomPanelInit();
        myTableView.setHat(new TableHat());
    }

    private void viewInit() {
        view.getChildren().add(new MyMenuBar(saveEvent, loadEvent, addStudentEvent, searchEvent, deleteEvent, helpEvent));
        view.getChildren().addAll(new MyToolBar(addStudentEvent, searchEvent, deleteEvent));
    }

    private List<Student> getStudents(Model model, ChooseDialog search) {
        List<Student> toShow;
        if (search.isGroupSelected()) {
            if (search.isLimitsSelected())
                toShow = (model.searchStudent(new Group(search.getGroup()), search.getLowerLimit(), search.getUpperLimit()));
            else toShow = (model.searchStudent(new Group(search.getGroup())));
        } else if (search.isLimitsSelected())
            toShow = (model.searchStudent(search.getSurname(), search.getLowerLimit(), search.getUpperLimit()));
        else
            toShow = (model.searchStudent(search.getSurname()));
        return toShow;
    }
}
