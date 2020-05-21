package FootballSystem;

import PresentationLayer.Controllers.RefereeControllerGui;
//import PresentationLayer.LoginController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/api/notification")
@RestController
public class bootApp {

    static PresentationLayer.Controllers.LoginController listenerr;
    public static void main(String[] args) {
     //   SpringApplication.run(FootballSystemApplication.class, args);
      Application.launch(FootballSystemApplication.class,args);
    }

    @GetMapping
    public void aaa( )  {
        listenerr.update();

    }

    public static  void addListener (PresentationLayer.Controllers.LoginController listener){
        listenerr = listener;
    }

}
