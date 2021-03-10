import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application {
    static public void main(String[] Args) {
        launch(Args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Model model = new Model();
        Controller controller = new Controller(root, model, stage);
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Dialog based interface");
        stage.setScene(scene);
        stage.show();
    }
}
