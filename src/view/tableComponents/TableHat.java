package view.tableComponents;

import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TableHat extends VBox {
    private final HBox hat = new HBox();
    private final Text FIO = new Text();
    private final Text group = new Text();
    private final Text[] works = new Text[10];


    public TableHat() {

        hatInit();
        widthProperty().addListener((obs, oldVal, newVal) -> {
            FIO.setWrappingWidth(newVal.doubleValue() / 8);
            group.setWrappingWidth(newVal.doubleValue() / 10);
            for (int i = 0; i < 10; i++)
                works[i].setWrappingWidth(newVal.doubleValue() * 31 / 400 - 2);
        });
    }


    private void hatInit() {
        FIO.setText("ФИО студента");
        group.setText("Группа");
        hat.getChildren().addAll(FIO, group);
        for (int i = 0; i < 10; i++) {
            works[i] = new Text();
            works[i].setText((i + 1) + " семестр");
            hat.getChildren().add(works[i]);
        }
        getChildren().add(hat);
        Separator separator = new Separator();

        separator.setStyle("-fx-border-style: solid;\n" +
                "-fx-border-width: 1px;" +
                "-fx-color-label-visible: black;");
        getChildren().add(separator);
    }


}
