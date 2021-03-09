package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class SearchDialog extends VBox {
    private Stage stage;
    private ToggleGroup toggleGroup = new ToggleGroup();
    private TextField lowerLimit = new TextField(), upperLimit = new TextField();
    private TextField student_input = new TextField();
    private ChoiceBox group_input = new ChoiceBox();
    CheckBox limitsON = new CheckBox("ON");
    HBox first, second, third, fourth;

    public SearchDialog(List<Integer> groupsId, String stageName) {
        stageInit(stageName);

        first = firtBoxInit(groupsId);

        second = secondBoxInit();

        third = thirdBoxInit();


        widthBindMathParent(first, second, third);
        getChildren().addAll(first, second, third);
    }

    public void start() {
        stage.show();
    }

    public void fourthBoxInit(Button actionButton) {
        HBox fourth = new HBox();
        Button cancel = new Button("Close");
        cancel.setOnAction(e -> {
            stage.close();
        });
        fourth.setSpacing(50);
        fourth.setAlignment(Pos.CENTER);
        fourth.setPadding(new Insets(20, 0, 0, 0));
        fourth.getChildren().addAll(actionButton, cancel);
        getChildren().addAll(fourth);
        this.fourth = fourth;
    }

    private void widthBindMathParent(HBox first, HBox second, HBox third) {
        first.prefWidthProperty().bind(widthProperty());
        second.prefWidthProperty().bind(widthProperty());
        third.prefWidthProperty().bind(widthProperty());
    }

    private HBox thirdBoxInit() {
        HBox third = new HBox();
        VBox third_first = new VBox(), third_second = new VBox();

        third_first.getChildren().addAll(new Label("Нижний предел"), lowerLimit);
        third_second.getChildren().addAll(new Label("Верхний предел"), upperLimit);
        third.getChildren().addAll(third_first, third_second);
        third_first.prefWidthProperty().bind(third.widthProperty());
        third_second.prefWidthProperty().bind(third.widthProperty());
        lowerLimit.setDisable(true);
        upperLimit.setDisable(true);
        return third;
    }

    private HBox secondBoxInit() {
        HBox second = new HBox();

        VBox second_first = new VBox();
        second_first.getChildren().addAll(new Label("Включить поиск по работе"), limitsON);
        second_first.setAlignment(Pos.CENTER);
        second.getChildren().addAll(second_first);
        second.setAlignment(Pos.CENTER);
        limitsON.setOnAction(actionEvent -> {
            if (limitsON.isSelected()) {
                lowerLimit.setDisable(false);
                upperLimit.setDisable(false);
            } else {
                lowerLimit.setDisable(true);
                upperLimit.setDisable(true);
            }
        });
        return second;
    }

    private HBox firtBoxInit(List<Integer> groupsId) {
        HBox first = new HBox();
        RadioButton radioButton1 = new RadioButton("Студент");
        RadioButton radioButton2 = new RadioButton("Группа");
        radioButton2.setToggleGroup(toggleGroup);
        radioButton1.setToggleGroup(toggleGroup);
        VBox first_first = new VBox(), first_second = new VBox();
        student_input.setDisable(true);
        for (Integer i : groupsId)
            group_input.getItems().add(i);
        group_input.setDisable(true);
        first_second.setSpacing(20);
        first_second.getChildren().addAll(student_input, group_input);
        first_first.getChildren().addAll(radioButton1, radioButton2);
        first.getChildren().addAll(first_first, first_second);
        first_first.setSpacing(20);
        first.setPadding(new Insets(25, 0, 0, 25));
        first.setSpacing(20);
        radioButton1.setOnAction(e -> {
            student_input.setDisable(false);
            group_input.setDisable(true);
        });
        radioButton2.setOnAction(e -> {
            student_input.setDisable(true);
            group_input.setDisable(false);
        });
        first_first.prefWidthProperty().bind(first.widthProperty());
        first_second.prefWidthProperty().bind(first.widthProperty());
        return first;
    }

    private void stageInit(String stageName) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(stageName);
        Scene view = new Scene(this, 400, 400);
        stage.setScene(view);
    }

    public boolean isStudentSelected() {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText().equals("Студент");
    }

    public boolean isGroupSelected() {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText().equals("Группа");
    }

    public boolean isLimitsSelected() {
        return limitsON.isSelected();
    }

    public String getSurname() {
        return student_input.getText();
    }

    public int getGroup() {
        return (Integer) group_input.getValue();
    }

    public int getUpperLimit() {
        return Integer.parseInt(upperLimit.getText());
    }


    public int getLowerLinit() {
        return Integer.parseInt(lowerLimit.getText());
    }

    public void updateUI() {
        getChildren().clear();
        getChildren().addAll(first, second, third, fourth);
    }
}
