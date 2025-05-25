package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PasukerfiApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PasukerfiApplication.class.getResource("/vidmot/pasukerfi-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 866, 299);
        stage.setTitle("Pásukerfi BICC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("App started");
        launch();
    }
}
