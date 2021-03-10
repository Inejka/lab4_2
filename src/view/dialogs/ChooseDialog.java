package view.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class ChooseDialog extends VBox {
    private Stage stage;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final TextField lowerLimit = new TextField();
    private final TextField upperLimit = new TextField();
    private final TextField studentInput = new TextField();
    private final ChoiceBox groupInput = new ChoiceBox();
    final CheckBox limitsON = new CheckBox("ON");
    final HBox first;
    final HBox second;
    final HBox third;
    HBox fourth;

    public ChooseDialog(List<Integer> groupsId, String stageName) {
        stageInit(stageName);

        first = firstBoxInit(groupsId);

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


    private HBox firstBoxInit(List<Integer> groupsId) {
        HBox first = new HBox();
        Collections.sort(groupsId);
        RadioButton radioButton1 = new RadioButton("Студент");
        RadioButton radioButton2 = new RadioButton("Группа");
        radioButton2.setToggleGroup(toggleGroup);
        radioButton1.setToggleGroup(toggleGroup);
        VBox first_first = new VBox(), first_second = new VBox();
        studentInput.setDisable(true);
        for (Integer i : groupsId)
            groupInput.getItems().add(i);
        groupInput.setDisable(true);
        first_second.setSpacing(20);
        first_second.getChildren().addAll(studentInput, groupInput);
        first_first.getChildren().addAll(radioButton1, radioButton2);
        first.getChildren().addAll(first_first, first_second);
        first_first.setSpacing(20);
        first.setPadding(new Insets(25, 0, 0, 25));
        first.setSpacing(20);
        radioButton1.setOnAction(e -> {
            studentInput.setDisable(false);
            groupInput.setDisable(true);
        });
        radioButton2.setOnAction(e -> {
            studentInput.setDisable(true);
            groupInput.setDisable(false);
        });
        first_first.prefWidthProperty().bind(first.widthProperty());
        first_second.prefWidthProperty().bind(first.widthProperty());
        return first;
    }

    private void stageInit(String stageName) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(stageName);
        Scene view = new Scene(this, 400, 250);
        stage.setScene(view);
    }

    public boolean isGroupSelected() {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText().equals("Группа");
    }

    public boolean isLimitsSelected() {
        return limitsON.isSelected();
    }

    public String getSurname() {
        return studentInput.getText();
    }

    public int getGroup() {
        return (Integer) groupInput.getValue();
    }

    public int getUpperLimit() {
        return Integer.parseInt(upperLimit.getText());
    }


    public int getLowerLimit() {
        return Integer.parseInt(lowerLimit.getText());
    }

    public void updateUI() {
        getChildren().clear();
        getChildren().addAll(first, second, third, fourth);
    }

    public void stop() {
        stage.close();
    }
}
