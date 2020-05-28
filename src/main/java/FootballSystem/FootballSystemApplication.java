package FootballSystem;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class FootballSystemApplication extends Application {
	ConfigurableApplicationContext context;
	public static void main(String[] args) {

		SpringApplication.run(FootballSystemApplication.class, args);


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
