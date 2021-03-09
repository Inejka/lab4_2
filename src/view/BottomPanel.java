package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import support.IntShell;
import support.MyVoid;

import java.io.FileNotFoundException;

public class BottomPanel extends HBox {
    public BottomPanel(IntShell maxViewsPerPage, IntShell currentPage, MyVoid updateStudentsViewWrapped, EventHandler
            <ActionEvent> jumpStart, EventHandler<ActionEvent> jumpEnd) {
        HBox left = new HBox(), right = new HBox();
        setAlignment(Pos.CENTER);
        leftAndRightPartsInit(left, right);
        try {
            TextLinkedToNumber leftText = new TextLinkedToNumber(maxViewsPerPage);
            leftText.setFont(new Font(30));
            left.getChildren().addAll(new MinusButton(maxViewsPerPage, leftText, updateStudentsViewWrapped),
                    leftText, new PlusButton(maxViewsPerPage, leftText, updateStudentsViewWrapped));
            TextLinkedToNumber rightText = new TextLinkedToNumber(currentPage);
            rightText.setFont(new Font(30));
            right.getChildren().addAll(
                    new ImageButton("goStart.png", actionEvent -> {
                        jumpStart.handle(actionEvent);
                        rightText.update();
                    }),
                    new MinusButton(currentPage, rightText, updateStudentsViewWrapped
                    ), rightText, new PlusButton(currentPage, rightText, updateStudentsViewWrapped),
                    new ImageButton("goEnd.png", actionEvent -> {
                        jumpStart.handle(actionEvent);
                        rightText.update();
                    }));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
