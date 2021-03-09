package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddDialog {
    private VBox root = new VBox();
    private Stage stage = new Stage();
    private boolean isStudentAdded = false;
    private String surname;
    private String name;
    private String patronymic;
    private int group;
    private int[] works = new int[10];

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getGroup() {
        return group;
    }

    public int[] getWorks() {
        return works;
    }

    private TextField surnameInput = new TextField();
    private TextField nameInput = new TextField();
    private TextField patronymicInput = new TextField();
    private TextField groupInput = new TextField();
    private GridPane gridPane = new GridPane();
    private TextField[] worksInputs = new TextField[10];
    private HBox buttonsLayer = new HBox();

    private EventHandler<ActionEvent> okButtonReaction = e -> {
        try {
            stage.close();
            isStudentAdded = true;
            surname = surnameInput.getText();
            name = nameInput.getText();
            patronymic = patronymicInput.getText();
            group = Integer.parseInt(groupInput.getText());
            for (int i = 0; i < 10; i++)
                works[i] = Integer.parseInt(worksInputs[i].getText());

        } catch (Exception v) {
            isStudentAdded = false;
        }
    };

    public AddDialog() {
        stage.setTitle("Add student");
        Scene view = new Scene(root, 400, 400);
        widthBind();
        buttonsLayerInit();
        gridPaneInit();
        root.getChildren().addAll(new Label("Фамилия"), surnameInput, new Label("Имя"),
                nameInput, new Label("Отчество"), patronymicInput, new Label("Группа"), groupInput, gridPane,
                buttonsLayer);
        stage.setScene(view);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void widthBind() {
        groupInput.prefWidthProperty().bind(root.widthProperty());
        nameInput.prefWidthProperty().bind(root.widthProperty());
        patronymicInput.prefWidthProperty().bind(root.widthProperty());
        groupInput.prefWidthProperty().bind(root.widthProperty());
        gridPane.prefWidthProperty().bind(root.widthProperty());
    }

    private void buttonsLayerInit() {
        Button ok = new Button("Добавить"), cancel = new Button("Отмена");
        cancel.setOnAction(e -> stage.close());
        ok.setOnAction(okButtonReaction);
        buttonsLayer.setAlignment(Pos.CENTER);
        buttonsLayer.getChildren().addAll(ok, cancel);
        buttonsLayer.setSpacing(50);
        buttonsLayer.setPadding(new Insets(100, 0, 0, 0));
    }

    private void gridPaneInit() {
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 5; j++) {
                gridPane.add(new Label(String.valueOf(j + 1 + i * 5) + " семестр"), j, 2 * i);
                worksInputs[j + 5 * i] = new TextField();
                gridPane.add(worksInputs[j + 5 * i], j, i * 2 + 1);

            }
    }

    public boolean isStudentAdded() {
        return isStudentAdded;
    }
}
