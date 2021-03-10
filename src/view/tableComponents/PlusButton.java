package view.tableComponents;

import support.IntShell;
import support.MyVoid;
import support.ImageButton;
import support.TextLinkedToNumber;

import java.io.FileNotFoundException;

public class PlusButton extends ImageButton {

    public PlusButton(IntShell toWorkWith, TextLinkedToNumber toSync, MyVoid update) throws FileNotFoundException {
        super("plus.png",null);
        setOnAction(e -> {
            toWorkWith.inc();
            toSync.update();
            update.performAction();
        });
    }

}
