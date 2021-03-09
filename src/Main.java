import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.student.PublicWork;
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
        for (int i = 0; i < 10; i++)
            test[i] = new PublicWork(i);
        model.addStudent("asdf", "adsf", "asdfasdf", 1234, test);

        Controller controller = new Controller(root, model);
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Dialog based interface");
        stage.setScene(scene);
        stage.show();
    }
}
