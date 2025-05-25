package vinnsla;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;

// Don't import java.time.Duration to avoid conflict with javafx.util.Duration

public class Starfsmadur {
    private String id;
    private LocalDateTime startTime;
    private Label timeLabel;
    private Timeline timer;

    public Starfsmadur(String id, Label timeLabel) {
        this.id = id;
        this.startTime = LocalDateTime.now();
        this.timeLabel = timeLabel;

        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void updateTime() {
        java.time.Duration elapsed = java.time.Duration.between(startTime, LocalDateTime.now());
        long hours = elapsed.toHours();
        long minutes = elapsed.toMinutes() % 60;
        long seconds = elapsed.getSeconds() % 60;
        timeLabel.setText(String.format("%d:%02d:%02d", hours, minutes, seconds));
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getId() {
        return id;
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
}
