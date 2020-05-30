package FootballSystem;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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
