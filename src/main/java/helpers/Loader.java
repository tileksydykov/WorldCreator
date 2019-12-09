package helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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

    public Loader getLoader(String sceneName){
        return new Loader(getClass().getResource("/scenes/"+ sceneName +".fxml"));
    }
}