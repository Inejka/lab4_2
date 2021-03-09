package view;

import support.IntShell;
import support.MyVoid;

import java.io.FileNotFoundException;

public class MinusButton extends ImageButton {

    IntShell toWorkWith;
    TextLinkedToNumber toSync;
    MyVoid update;

    public MinusButton(IntShell toWorkWith, TextLinkedToNumber toSync, MyVoid update) throws FileNotFoundException {
        super("minus.png", null);
        this.toWorkWith = toWorkWith;
        this.toSync = toSync;
        this.update = update;
        setOnAction(e -> {
            toWorkWith.dec();
            toSync.update();
            update.performAction();
        });
    }

}
