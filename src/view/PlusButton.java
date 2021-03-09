package view;

import support.IntShell;
import support.MyVoid;

import java.io.FileNotFoundException;

public class PlusButton extends ImageButton {

    IntShell toWorkWith;
    TextLinkedToNumber toSync;
    MyVoid update;

    public PlusButton(IntShell toWorkWith, TextLinkedToNumber toSync, MyVoid update) throws FileNotFoundException {
        super("plus.png",null);
        this.toWorkWith = toWorkWith;
        this.toSync = toSync;
        this.update = update;
        setOnAction(e -> {
            toWorkWith.inc();
            toSync.update();
            update.performAction();
        });
    }

}
