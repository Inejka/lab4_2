package support;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageButton extends Button {
    public ImageButton(String image, EventHandler<ActionEvent> clickReaction) throws FileNotFoundException {
        super("", new ImageView(new Image(new FileInputStream("images/" + image))));
        this.setOnAction(clickReaction);
    }
}
