package view.mainComponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MyMenuBar extends MenuBar {
    public MyMenuBar(EventHandler<ActionEvent> save, EventHandler<ActionEvent> load, EventHandler<ActionEvent> add, EventHandler<ActionEvent> search,
                     EventHandler<ActionEvent> delete, EventHandler<ActionEvent> help) {
        fileMenuInit(save, load);
        editMenuInit(add, search, delete);
        helpMenuInit(help);
    }

    private void helpMenuInit(EventHandler<ActionEvent> help) {
        Menu mHelp = new Menu("Help");
        MenuItem miHelp = new MenuItem("Help me,HELP ME");
        miHelp.setOnAction(help);
        mHelp.getItems().add(miHelp);
        getMenus().add(mHelp);
    }

    private void editMenuInit(EventHandler<ActionEvent> add, EventHandler<ActionEvent> search, EventHandler<ActionEvent> delete) {
        Menu edit = new Menu("Edit");
        MenuItem mAdd = new MenuItem("Add student");
        MenuItem mSearch = new MenuItem("Search student");
        MenuItem mDelete = new MenuItem("Delete student");
        mAdd.setOnAction(add);
        mDelete.setOnAction(delete);
        mSearch.setOnAction(search);
        edit.getItems().addAll(mAdd, mSearch, mDelete);
        getMenus().add(edit);
    }

    private void fileMenuInit(EventHandler<ActionEvent> save, EventHandler<ActionEvent> load) {
        Menu file = new Menu("File");
        MenuItem mSave = new MenuItem("Save");
        MenuItem mLoad = new MenuItem("Load");
        mSave.setOnAction(save);
        mLoad.setOnAction(load);
        file.getItems().addAll(mSave, mLoad);
        getMenus().add(file);
    }


}
