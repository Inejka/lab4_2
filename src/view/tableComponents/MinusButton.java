package view.tableComponents;

import support.IntShell;
import support.MyVoid;
import support.ImageButton;
import support.TextLinkedToNumber;

import java.io.FileNotFoundException;

public class MinusButton extends ImageButton {

    public MinusButton(IntShell toWorkWith, TextLinkedToNumber toSync, MyVoid update) throws FileNotFoundException {
        super("minus.png", null);
        setOnAction(e -> {
            toWorkWith.dec();
            toSync.update();
            update.performAction();
        });
    }

}
