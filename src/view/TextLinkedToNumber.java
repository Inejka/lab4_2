package view;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import support.IntShell;

public class TextLinkedToNumber extends Text {
    IntShell value;

    public TextLinkedToNumber(IntShell value) {
        this.value = value;
        setFont(new Font(20));
        update();
    }

    public void update() {
        setText(String.valueOf(value.getNumber()));
    }
}
