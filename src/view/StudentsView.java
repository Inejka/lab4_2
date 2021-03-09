package view;

import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StudentsView extends VBox {

    private HBox hat = new HBox();
    private Text FIO = new Text();
    private Text group = new Text();
    private Text[] works = new Text[10];

    public StudentsView() {
        hatInit();
        widthProperty().addListener((obs, oldVal, newVal) -> {
            FIO.setWrappingWidth(getWidth() / 8);
            group.setWrappingWidth(getWidth() / 10);
            for (int i = 0; i < 10; i++)
                works[i].setWrappingWidth(getWidth() * 31 / 400 - 2);
        });
    }

    private void hatInit() {
        FIO.setText("ФИО студента");
        group.setText("Группа");
        hat.getChildren().addAll(FIO, group);
        for (int i = 0; i < 10; i++) {
            works[i] = new Text();
            works[i].setText(String.valueOf(i + 1) + " семестр");
            hat.getChildren().add(works[i]);
        }
        getChildren().add(hat);
    }

    public void clear() {
        getChildren().clear();
        getChildren().add(hat);

        Separator separator = new Separator();

        separator.setStyle("-fx-border-style: solid;\n" +
                "-fx-border-width: 1px;" +
                "-fx-color-label-visible: black;");
        getChildren().add(separator);
    }


}
