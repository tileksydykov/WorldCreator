import controllers.EditCharacterController;
import database.WorldCreatorDatabase;
import helpers.Loader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private WorldCreatorDatabase database;
    Loader loader = new Loader();

    @Override
    public void start(Stage primaryStage) throws Exception {
        createDatabase();
        primaryStage.setTitle("World Creator");
        primaryStage.setScene(loader.getScene("HelloScene"));
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void createDatabase(){
        this.database = new WorldCreatorDatabase();
    }

    public WorldCreatorDatabase getDatabase() {
        return database;
    }

    public Loader getLoader() {
        return loader;
    }
}
