package FootballSystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {
    private final String title;
    private static org.springframework.core.io.Resource fxml = null;
    private ApplicationContext ac;
    private  static Stage primaryStage;
    public StageListener(@Value("classpath:/login.fxml") org.springframework.core.io.Resource r,ApplicationContext ac){
        title="hole";
        fxml=r;
        this.ac=ac;
        primaryStage=null;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
             primaryStage=stageReadyEvent.getStage();
            URL url=fxml.getURL();
            FXMLLoader fxmlLoader=new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root,1440,844);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void changeScene(String userName, String fxml) throws Exception{
//        this.userName = userName;
        Parent root = FXMLLoader.load(StageListener.class.getClassLoader().getResource(fxml));
        Scene scene = new Scene(root,1440,844);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
