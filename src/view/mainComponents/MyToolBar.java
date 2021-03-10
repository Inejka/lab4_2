package view.mainComponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;
import support.ImageButton;

import java.io.FileNotFoundException;

public class MyToolBar extends ToolBar {
    public MyToolBar(EventHandler<ActionEvent> add, EventHandler<ActionEvent> search, EventHandler<ActionEvent> delete) {
        try {
            getItems().addAll(new ImageButton("add.png", add), new ImageButton("search.png", search), new ImageButton("delete.png", delete));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
