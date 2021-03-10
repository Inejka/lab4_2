package view;

import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StudentView extends HBox {

    private VBox parent;
    private Text FIO = new Text();
    private Text group = new Text();
    private Text[] works = new Text[10];

    public StudentView(VBox parent, String FIO, String group, String[] works) {
        this.setStyle("-fx-background-color: #cdc1b9;");
        this.setStyle("-fx-border-color: #000000;");
        this.parent = parent;
        this.FIO.setText(FIO);
        this.group.setText(group);
        this.getChildren().addAll(this.FIO, this.group);
        for (int i = 0; i < 10; i++) {
            this.works[i] = new Text();
            this.works[i].setText(works[i]+" часов");
            getChildren().add(this.works[i]);
        }
        widthProperty().addListener((obs, oldVal, newVal) -> {
            this.FIO.setWrappingWidth(getWidth() / 8);
            this.group.setWrappingWidth(getWidth() / 10);
            for (int i = 0; i < 10; i++)
                this.works[i].setWrappingWidth(getWidth() * 31 / 400 - 2);
        });
        parent.getChildren().add(this);
    }
}
