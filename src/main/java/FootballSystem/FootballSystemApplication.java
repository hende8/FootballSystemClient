package FootballSystem;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import sun.tools.jar.CommandLine;

@SpringBootApplication
public class FootballSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FootballSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		String createPersonUrl = "http://localHost:8090/api/user/MAX";
		String updatePersonUrl = "http://localhost:8082/spring-rest/updatePerson";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type","application/json");
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		HttpEntity<String> e = new HttpEntity<>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(createPersonUrl, HttpMethod.GET, e , String.class);
		System.out.println(responseEntity);
	}
}
