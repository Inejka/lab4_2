package view.tableComponents;

import controller.TableController;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MyTableView extends VBox {

    private Region hat;
    private TableController controller;


    public MyTableView() {

    }

    public void setController(TableController controller) {
        this.controller = controller;
        prefHeightProperty().bind(controller.getTableParent().heightProperty());
    }

    public void bottomPanelInit() {
        if (controller != null) {
            controller.getTableParent().getChildren().add(new BottomPanel(controller.getMaxViewsPerPage(), controller.getCurrentPage(),
                    controller.getUpdateTable(), controller.getJumpStart(), controller.getJumpEnd()));
        }
    }


    public void clear() {
        getChildren().clear();
        getChildren().add(hat);
    }

    public void setHat(Region hat) {
        this.hat = hat;
        getChildren().addAll(hat);
        hat.prefWidthProperty().bind(widthProperty());
    }

}
