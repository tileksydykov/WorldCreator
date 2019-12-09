package helpers;

import app.App;
import controllers.ControllerBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class Loader extends FXMLLoader {

    public Loader() { }

    public Loader(URL location) {
        super(location);
    }

    public Scene getScene(String sceneName) throws Exception{
        Loader loader = new Loader(getClass().getResource("/scenes/"+ sceneName +".fxml"));
        Parent scene = loader.load();
        return new Scene(scene);
    }

    public FXMLLoader getLoader(String sceneName){
        return new Loader(getClass().getResource("/scenes/"+ sceneName +".fxml"));
    }
}