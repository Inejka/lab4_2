import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.student.FIO;
import model.student.Group;
import model.student.PublicWork;
import model.student.Student;
import view.View;

public class Main extends Application {
    static public void main(String[] Args) {
        launch(Args);
    }

    @Override
    public void start(Stage stage) {
        View root = new View();
        Model model = new Model();
        PublicWork[] test = new PublicWork[10];
        Controller controller = new Controller(root, model, stage);
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Dialog based interface");
        stage.setScene(scene);
        stage.show();
    }
}
