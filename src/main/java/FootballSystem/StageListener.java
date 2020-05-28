package FootballSystem;

import PresentationLayer.Controllers.ControllerGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener extends ControllerGUI implements ApplicationListener<StageReadyEvent>  {
    private final String title;
    static String ipServer = "132.72.65.99:8090";
    private static org.springframework.core.io.Resource fxml = null;
    private ApplicationContext ac;
    private  static Stage primaryStage;
    public StageListener(@Value("classpath:/login.fxml") org.springframework.core.io.Resource r,ApplicationContext ac){
        title="";
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

    /**
     * switch between scenes
     * @param userName
     * @param fxml
     * @throws Exception
     */
        public static void changeScene(String userName, String fxml) throws Exception{
        Parent root = FXMLLoader.load(StageListener.class.getClassLoader().getResource(fxml));
        Scene scene=null;
        if(fxml.equals("login.fxml")){
            scene = new Scene(root,1440,844);

        }else{
            scene = new Scene(root,1440,885);

        }
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/json");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> e1 = new HttpEntity<>(headers);
            restTemplate.exchange("http://"+ipServer+"/api/user/logOut/"+userName, HttpMethod.GET, e1 , String.class);

        });
            Text lblData = (Text) root.lookup("#userNameLabel");
            if (lblData!=null) lblData.setText(userName);    }


}
