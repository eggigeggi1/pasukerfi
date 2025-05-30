package vidmot;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vinnsla.PasukerfiVinnsla;
import vinnsla.Starfsmadur;

import java.time.LocalDate;
import java.util.Map;

public class PasukerfiController {
    public Label fxNext;
    public TextField fxInput;
    public Label fx1;
    public Label fx2;
    public Label fx3;
    public Label fx4;
    public Label fx5;
    public Label fx6;
    public Label fx7;
    public Label fxT1;
    public Label fxT2;
    public Label fxT3;
    public Label fxT4;
    public Label fxT5;
    public Label fxT6;
    public Label fxT7;
    public Label[] labels;
    public Label[] tLabels;
    public Starfsmadur[] starfsmennAGolfi;
    public Starfsmadur vinnaLengst = null;
    public PasukerfiVinnsla vinnsla;
    public StackPane scalingRoot;
    public Label infoLabel;

    public void initialize() {
        fxNext.setText("");
        fx1.setText("");
        fx1.setVisible(false);
        fx2.setText("");
        fx2.setVisible(false);
        fx3.setText("");
        fx3.setVisible(false);
        fx4.setText("");
        fx4.setVisible(false);
        fx5.setText("");
        fx5.setVisible(false);
        fx6.setText("");
        fx6.setVisible(false);
        fx7.setText("");
        fx7.setVisible(false);
        fxT1.setText("");
        fxT2.setText("");
        fxT3.setText("");
        fxT4.setText("");
        fxT5.setText("");
        fxT6.setText("");
        fxT7.setText("");
        labels = new Label[] {fx1, fx2, fx3, fx4, fx5, fx6, fx7};
        tLabels = new Label[] {fxT1, fxT2, fxT3, fxT4, fxT5, fxT6, fxT7};
        vinnsla = new PasukerfiVinnsla();
        starfsmennAGolfi = new Starfsmadur[7];
        checkBirthday();
    }

    public void onInput(ActionEvent actionEvent) {
        String input = fxInput.getText();
        String[] inputs = input.trim().split("\\s+");
        boolean[] founds = new boolean[3];

        if (input.equalsIgnoreCase("rem all") || input.equalsIgnoreCase("reset")) {
            for (int i = 0; i < starfsmennAGolfi.length; i++) {
                if (starfsmennAGolfi[i] != null) {
                    removeID(i);
                    i = -1; // Restart loop because removeID shifts everyone left
                }
            }
            vinnaLengst = null;
            vinnsla.next = "";
            fxNext.setText("");
        }

        for (int i = 0; i < inputs.length; i++) {
            if (vinnsla.starfsmennID.containsKey(inputs[i].toUpperCase())) {
                founds[i] = true;
            }
        }

        if (inputs[0].equalsIgnoreCase("add")) {
            String newID = inputs[1].toUpperCase();
            if (founds[1] && vinnsla.starfsmennAGolfi < 7 && !isWorking(newID)) {
                addID(newID);
            }
        }

        if (inputs[0].equalsIgnoreCase("rem")) {
            if (founds[1] && vinnsla.starfsmennAGolfi > 0) {
                String remID = inputs[1].toUpperCase();
                int remIndex = 0;
                for (int i = 0; i < starfsmennAGolfi.length; i++) {
                    if (starfsmennAGolfi[i] == null) continue;
                    if (starfsmennAGolfi[i].getId().equalsIgnoreCase(remID)) {
                        remIndex = i;
                    }
                }
                removeID(remIndex);
                vinnaLengst = null;
            }

        }
        if (founds[0]) {
            if (vinnaLengst == null) return;
            if (isWorking(inputs[0])) {
                fxInput.clear();
                return;
            }

            int vinnaLengstIndex = 0;
            for (int i = 0; i < starfsmennAGolfi.length; i++) {
                if (starfsmennAGolfi[i] != null) {
                    if (starfsmennAGolfi[i].getId().equalsIgnoreCase(vinnaLengst.getId())) {
                        vinnaLengstIndex = i;
                    }
                }
            }
            removeID(vinnaLengstIndex);
            addID(inputs[0].toUpperCase());
            vinnaLengst = null;
            fxInput.clear();
        }

        for (Starfsmadur s : starfsmennAGolfi) {
            if (s == null) continue;

            if (vinnaLengst == null || s.getStartTime().isBefore(vinnaLengst.getStartTime())) {
                vinnaLengst = s;
            }
        }
        if (vinnaLengst != null) {
            vinnsla.next = vinnaLengst.getId();
        }
        checkBirthday();
        if (vinnsla.starfsmennAGolfi == 0) {
            vinnsla.next = "";
        }
        fxNext.setText(vinnsla.next);
        fxInput.clear();
    }

    public boolean isWorking(String id) {
        for (Starfsmadur s : starfsmennAGolfi) {
            if (s == null) continue;

            if (s.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public void removeID(int remIndex) {
        if (vinnsla.starfsmennAGolfi > 0) vinnsla.starfsmennAGolfi--;
        starfsmennAGolfi[remIndex].stopTimer();
        starfsmennAGolfi[remIndex] = null;
        labels[remIndex].setText("");
        tLabels[remIndex].setText("");
        for (int i = remIndex; i < 6; i++) {
            if (starfsmennAGolfi[i + 1] != null) {
                starfsmennAGolfi[i] = starfsmennAGolfi[i + 1];
                starfsmennAGolfi[i+1] = null;
                labels[i].setText(labels[i + 1].getText());
                starfsmennAGolfi[i].setTimeLabel(tLabels[i]);
                tLabels[i+1].setText("");
            } else {
                break; // Nothing more to shift
            }
        }
        int lastIndex = vinnsla.starfsmennAGolfi;
        starfsmennAGolfi[lastIndex] = null;
        labels[lastIndex].setText("");
        labels[lastIndex].setVisible(false);
    }

    public void addID(String newID) {
        vinnsla.starfsmennAGolfi++;
        Starfsmadur newStarfsmadur = new Starfsmadur(newID, tLabels[vinnsla.starfsmennAGolfi - 1]);
        labels[vinnsla.starfsmennAGolfi - 1].setText(newStarfsmadur.getId());
        labels[vinnsla.starfsmennAGolfi - 1].setVisible(true);
        newStarfsmadur.getTimeLabel().setText("0:00:00");
        starfsmennAGolfi[vinnsla.starfsmennAGolfi - 1] = newStarfsmadur;
    }

    public void onClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void checkBirthday() {
        LocalDate today = LocalDate.now();

        for(Map.Entry<String, LocalDate> entry : vinnsla.starfsmennID.entrySet()) {
            LocalDate birthday = entry.getValue();

            if (birthday.getMonth() == today.getMonth() &&
                    birthday.getDayOfMonth() == today.getDayOfMonth()) {

                infoLabel.setText("🎉 Til hamingju með afmælið " + entry.getKey() + "! 🎉");
                return;
            }
            else {
                infoLabel.setText("");
            }
        }
    }

    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leiðbeiningar");
        alert.setHeaderText(null); // No header
        alert.setContentText("Notið add skipun til að bæta við starfsmanni. \nDæmi: add EO <Enter>\n\nNotið rem skipun " +
                "til að taka út starfsmann. \nDæmi: rem EO <Enter>\n\nSláið inn einkennisstafi starfsmann einungis til" +
                " að láta starfsmann leysa af þann sem er næst/ur í pásu. \nDæmi: EO <Enter>\n\nNotið 'rem all' eða " +
                "'reset' til að fjarlægja alla starfsmenn");

        alert.showAndWait();
    }

    public void setupScaling(Stage stage) {
        double baseWidth = 866;
        double baseHeight = 300;

        ChangeListener<Number> resizeListener = (obs, oldVal, newVal) -> {
            double scaleX = stage.getWidth() / baseWidth;
            double scaleY = stage.getHeight() / baseHeight;
            double scale = Math.min(scaleX, scaleY);
            scalingRoot.setScaleX(scale);
            scalingRoot.setScaleY(scale);
        };

        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
    }

    public void onLightMode(ActionEvent actionEvent) {
        Scene scene = fxNext.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/vidmot/css/light.css").toExternalForm());

    }

    public void onDarkMode(ActionEvent actionEvent) {
        Scene scene = fxNext.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/vidmot/css/dark.css").toExternalForm());
    }

    public void onIsaviaMode(ActionEvent actionEvent) {
        Scene scene = fxNext.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/vidmot/css/isavia.css").toExternalForm());
    }

    public void onDarkIsaviaMode(ActionEvent actionEvent) {
        Scene scene = fxNext.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/vidmot/css/isavia-dark.css").toExternalForm());
    }
}
