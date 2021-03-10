package view.tableComponents;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StudentView extends HBox {

    private final Text FIO = new Text();
    private final Text group = new Text();
    private final Text[] works = new Text[10];

    public StudentView(String FIO, String group, String[] works) {
        this.setStyle("-fx-background-color: #cdc1b9;");
        this.setStyle("-fx-border-color: #000000;");
        this.FIO.setText(FIO);
        this.group.setText(group);
        this.getChildren().addAll(this.FIO, this.group);
        for (int i = 0; i < 10; i++) {
            this.works[i] = new Text();
            this.works[i].setText(works[i] + " часов");
            getChildren().add(this.works[i]);
        }
        widthProperty().addListener((obs, oldVal, newVal) -> {
            this.FIO.setWrappingWidth(getWidth() / 8);
            this.group.setWrappingWidth(getWidth() / 10);
            for (int i = 0; i < 10; i++)
                this.works[i].setWrappingWidth(getWidth() * 31 / 400 - 2);
        });
    }
}
