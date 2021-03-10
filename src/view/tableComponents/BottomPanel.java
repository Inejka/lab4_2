package view.tableComponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import support.IntShell;
import support.MyVoid;
import support.ImageButton;
import support.TextLinkedToNumber;

import java.io.FileNotFoundException;

public class BottomPanel extends HBox {

    public BottomPanel(IntShell maxViewsPerPage, IntShell currentPage, MyVoid updateStudentsViewWrapped, EventHandler
            <ActionEvent> jumpStart, EventHandler<ActionEvent> jumpEnd) {
        HBox left = new HBox(), right = new HBox();
        setAlignment(Pos.CENTER);
        leftAndRightPartsInit(left, right);
        try {
            getLeftPart(maxViewsPerPage, updateStudentsViewWrapped, left);
            getRightPart(currentPage, updateStudentsViewWrapped, jumpStart, jumpEnd, right);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getLeftPart(IntShell maxViewsPerPage, MyVoid updateStudentsViewWrapped, HBox left) throws FileNotFoundException {
        TextLinkedToNumber leftText = new TextLinkedToNumber(maxViewsPerPage);
        left.getChildren().addAll(new MinusButton(maxViewsPerPage, leftText, updateStudentsViewWrapped),
                leftText, new PlusButton(maxViewsPerPage, leftText, updateStudentsViewWrapped));
    }

    private void getRightPart(IntShell currentPage, MyVoid updateStudentsViewWrapped, EventHandler<ActionEvent> jumpStart, EventHandler<ActionEvent> jumpEnd, HBox right) throws FileNotFoundException {
        TextLinkedToNumber rightText = new TextLinkedToNumber(currentPage);
        right.getChildren().addAll(
                new ImageButton("goStart.png", actionEvent -> {
                    jumpStart.handle(actionEvent);
                    rightText.update();
                }),
                new MinusButton(currentPage, rightText, updateStudentsViewWrapped
                ), rightText, new PlusButton(currentPage, rightText, updateStudentsViewWrapped),
                new ImageButton("goEnd.png", actionEvent -> {
                    jumpEnd.handle(actionEvent);
                    rightText.update();
                }));
    }

    private void leftAndRightPartsInit(HBox left, HBox right) {
        right.prefWidthProperty().bind(widthProperty());
        left.prefWidthProperty().bind(widthProperty());
        right.setAlignment(Pos.CENTER_RIGHT);
        left.setAlignment(Pos.CENTER_LEFT);
        left.setSpacing(5);
        right.setSpacing(5);
        getChildren().addAll(left, right);
    }
}
