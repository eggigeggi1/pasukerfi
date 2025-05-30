package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PasukerfiApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PasukerfiApplication.class.getResource("/vidmot/pasukerfi-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 866, 300);
        scene.getStylesheets().add(getClass().getResource("/vidmot/css/light.css").toExternalForm());
        PasukerfiController controller = fxmlLoader.getController();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("isavia-icon.png")));
        stage.setTitle("Pásukerfi BICC");
        stage.setScene(scene);
        stage.show();

        controller.setupScaling(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
