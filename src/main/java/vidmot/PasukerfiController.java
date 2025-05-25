package vidmot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vinnsla.PasukerfiVinnsla;
import vinnsla.Starfsmadur;

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

    public void initialize() {
        fxNext.setText("");
        fx1.setText("");
        fx2.setText("");
        fx3.setText("");
        fx4.setText("");
        fx5.setText("");
        fx6.setText("");
        fx7.setText("");
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
    }
    public void onInput(ActionEvent actionEvent) {
        String input = fxInput.getText();
        String[] inputs = input.trim().split("\\s+");
        boolean[] founds = new boolean[3];

        for (int i = 0; i < inputs.length; i++) {
            for(int k = 0; k < vinnsla.starfsmennID.length; k++) {
                if (inputs[i].equalsIgnoreCase(vinnsla.starfsmennID[k])) {
                    founds[i] = true;
                    break;
                }
            }
        }


        if (inputs[0].equalsIgnoreCase("add")) {
            String newID = inputs[1].toUpperCase();
            if (founds[1] && vinnsla.starfsmennAGolfi < 7 && !isWorking(newID)) {
                vinnsla.starfsmennAGolfi++;
                Starfsmadur newStarfsmadur = new Starfsmadur(newID, tLabels[vinnsla.starfsmennAGolfi - 1]);
                labels[vinnsla.starfsmennAGolfi - 1].setText(newStarfsmadur.getId());
                newStarfsmadur.getTimeLabel().setText("0:00:00");
                starfsmennAGolfi[vinnsla.starfsmennAGolfi - 1] = newStarfsmadur;
            }
        }

        if (inputs[0].equalsIgnoreCase("rem")) {
            String remID = inputs[1].toUpperCase();
            int remIndex = 0;
            for (int i = 0; i < starfsmennAGolfi.length; i++) {
                if (starfsmennAGolfi[i] == null) continue;
                if (starfsmennAGolfi[i].getId().equalsIgnoreCase(remID)) {
                    remIndex = i;
                }
            }
            removeID(remIndex);
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


            vinnaLengst = null;
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
            Starfsmadur newStarfsmadur = new Starfsmadur(inputs[0].toUpperCase(), tLabels[vinnaLengstIndex]);
            labels[vinnaLengstIndex].setText(newStarfsmadur.getId());
            newStarfsmadur.getTimeLabel().setText("0:00:00");
            starfsmennAGolfi[vinnaLengstIndex].stopTimer();
            starfsmennAGolfi[vinnaLengstIndex] = newStarfsmadur;
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
    }

    public void onClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leiðbeiningar");
        alert.setHeaderText(null); // No header
        alert.setContentText("Notið add skipun til að bæta við starfsmanni. \nDæmi: add EO <Enter>\n\nNotið rem skipun til að taka út starfsmann. \nDæmi: rem EO <Enter>\n\nSláið inn einkennisstafi starfsmann einungis til að láta starfsmann leysa af þann sem er næst/ur í pásu. \nDæmi: EO <Enter>");

        alert.showAndWait();
    }
}
