package FootballSystem;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/api/notification")
@RestController
public class bootApp {

    static PresentationLayer.Controllers.FanControllerGUI listenerr;
    public static void main(String[] args) {
      Application.launch(FootballSystemApplication.class,args);
    }

    @PostMapping
    public void showEventAlert(@RequestBody String alert)  {
        listenerr.postNotification(alert);

    }

    public static  void addListener (PresentationLayer.Controllers.FanControllerGUI listener){
        listenerr = listener;
    }

}
