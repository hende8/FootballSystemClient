package FootballSystem;

import com.sun.javaws.jnl.JavaFXAppDesc;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import sun.tools.jar.CommandLine;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import PresentationLayer.*;

@SpringBootApplication
public class FootballSystemApplication extends Application {
	ConfigurableApplicationContext context;
	public static void main(String[] args) {

		SpringApplication.run(FootballSystemApplication.class, args);
		Application.launch();

	}
	@Override
	public void init(){

		ApplicationContextInitializer<GenericApplicationContext> initializer= ac -> {
			ac.registerBean(Application.class, () -> FootballSystemApplication.this);
			ac.registerBean(Parameters.class, () -> getParameters());
			ac.registerBean(HostServices.class, () -> getHostServices());
		};

				Object o=initializer;
				context=new SpringApplicationBuilder().sources(bootApp.class)
				.initializers(initializer)
				.run(getParameters().getRaw().toArray(new String[0]));
	}

//	@Override
//	public void run(String... args) throws Exception{
//
////		String createPersonUrl = "http://localHost:8090/api/user/MAX";
////		String updatePersonUrl = "http://localhost:8082/spring-rest/updatePerson";
////
////		RestTemplate restTemplate = new RestTemplate();
////		HttpHeaders headers = new HttpHeaders();
////		headers.set("Content-Type","application/json");
////		headers.setContentType(MediaType.APPLICATION_JSON);
////		JSONObject personJsonObject = new JSONObject();
////		HttpEntity<String> e = new HttpEntity<>(headers);
////		ResponseEntity<String> responseEntity = restTemplate.exchange(createPersonUrl, HttpMethod.GET, e , String.class);
////		System.out.println(responseEntity);
//	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		context.publishEvent(new StageReadyEvent(primaryStage));
	}
	@Override
	public void stop() throws Exception {
		this.context.close();
		Platform.exit();
	}
}
class StageReadyEvent extends ApplicationEvent{

	public StageReadyEvent(Stage source) {
		super(source);
	}
	public Stage getStage(){return Stage.class.cast(getSource());}
}
