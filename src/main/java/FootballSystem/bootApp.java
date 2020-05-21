package FootballSystem;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import PresentationLayer.LoginController;

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
