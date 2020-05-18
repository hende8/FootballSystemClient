package PresentationLayer;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<Main.StageReadyEvent> {
    @Override
    public void onApplicationEvent(Main.StageReadyEvent stageReadyEvent) {
        Stage s = stageReadyEvent.getStage();
    }
}
