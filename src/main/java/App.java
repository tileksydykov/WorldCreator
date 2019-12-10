import database.WorldCreatorDatabase;
import helpers.Loader;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private WorldCreatorDatabase database;
    Loader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        createDatabase();
        loader  = new Loader();
        primaryStage.setTitle("World Creator");
        primaryStage.setScene(loader.getScene("MainScene"));
        primaryStage.setMaximized(true);
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
