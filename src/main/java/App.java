import helpers.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneLoader sceneLoader = new SceneLoader();
        primaryStage.setTitle("World Creator");
        primaryStage.setScene(sceneLoader.getScene("MainScene"));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
