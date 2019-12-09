package helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneLoader {
    public SceneLoader() { }
    public Scene getScene(String sceneName) throws Exception{
        Parent scene = FXMLLoader.load(getClass().getResource("/scenes/"+ sceneName +".fxml"));
        return new Scene(scene);
    }
}