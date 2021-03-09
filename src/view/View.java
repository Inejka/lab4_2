package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class View extends VBox {
    private MenuBar menuBar = new MenuBar();
    public MenuItem button1;
    public MenuItem button2;

    public View() {
        menuBarInit();
    }

    private void menuBarInit() {
        menuBar.prefWidthProperty().bind(widthProperty());
        Menu file = new Menu("File"), edit = new Menu("Edit"), help = new Menu("Help");
        MenuItem newPane = new MenuItem("New");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        MenuItem helpMe = new MenuItem("Help me,HELP ME");
        help.getItems().add(helpMe);
        button1 = new MenuItem("Add");
        button2 = new MenuItem("Delete");
        MenuItem button3 = new MenuItem("Algorithm");
        MenuItem button4 = new MenuItem("Clear");

        file.getItems().addAll(newPane, save, load);
        edit.getItems().addAll(button1, button2, button3, button4);
        menuBar.getMenus().addAll(file, edit, help);
        getChildren().add(menuBar);
    }
}
