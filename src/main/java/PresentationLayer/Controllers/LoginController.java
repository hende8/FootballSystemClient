package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
//import ServiceLayer.*;
//
//import System.Exeptions.NoSuchAUserNamedException;
//import System.Exeptions.WrongPasswordException;
//import System.Users.Referee;
//import System.Users.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@FxmlView("login.fxml")
public class LoginController extends ImageView{

    String url = "http://localHost:8090/login";
    @FXML
    TextField userName;
    @FXML
    TextField userPass;
    @FXML
    ImageView nameImg;
    @FXML
    ImageView passImg;
    @FXML
    Button loginBtn;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @FXML
    public void handleLogin() {

        if (userPass.getText().length() == 0) {
            passImg.setVisible(true);
            userPass.setStyle("-fx-prompt-text-fill: #6B6B6B ; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        }
        if (userName.getText().length() == 0) {
            nameImg.setVisible(true);
            userName.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        }
        String userN = userName.getText();
//        String userP = userPass.getText();

        String login = "http://localHost:8090/api/user/login";
        try {
            URL url = new URL("http://localHost:8090/api/user/login");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{user_name: "+userN+",+ password: "+userPass.getText()+"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            try {
                ScreenController.getInstance().changeScene("fan", "MainFanMenu.fxml");
                e1.printStackTrace();
            }
            catch (Exception e){

            }
            e1.printStackTrace();
        }
//        headers.set("Content-Type", "application/json");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> e1 = new HttpEntity<>(headers);
//        ParameterizedTypeReference<HashMap<String, String>> typeRef
//                = new ParameterizedTypeReference<HashMap<String, String>>() {};
//        HashMap<String, String> responseType = new HashMap();
//        responseType.put("password",userPass.getText());
//        responseType.put("user_name",userName.getText());
//        ResponseEntity<HashMap<String,String>> responseEntity = restTemplate.exchange(login,HttpMethod.GET,e1,typeRef);
//
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type","application/json");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        JSONObject personJsonObject = new JSONObject();
//        HttpEntity<String> e = new HttpEntity<>(headers);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(login, HttpMethod.GET, e , String.class);
//        System.out.println(responseEntity);

//        }
//        catch (WrongPasswordException | NoSuchAUserNamedException e){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("אחד או יותר מהפרטים אינם נכונים");
//
//            alert.showAndWait();
//        }
        catch(Exception e){
                e.printStackTrace();
            }
        }
private void parseBody(String s,String val){


}
    @FXML
    public void OnHover(){
        loginBtn.setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10");
    }

    @FXML
    public void OnExit(){
        loginBtn.setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10");
    }
    @FXML
    public void OnPress(){
        loginBtn.setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10");
    }

    @FXML
    public void initialize() {
        userName.setStyle("-fx-prompt-text-fill: transparent; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
    }

    @FXML
    public void handleMouseClickedName(){
        userName.setStyle("-fx-prompt-text-fill: transparent; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        nameImg.setVisible(false);
        if(userPass.getText().length() == 0) {
            passImg.setVisible(true);
            userPass.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        }
    }

    @FXML
    public void handleMouseClickedPass(){
        userPass.setStyle("-fx-prompt-text-fill: transparent; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        passImg.setVisible(false);
        if(userName.getText().length() == 0) {
            userName.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
            nameImg.setVisible(true);
        }

    }

    public void sendInfo(){
        String myIP ="localHost";
        String myPort = "8091";
        String userName = ScreenController.getInstance().userName;
        String addListener = "http://localHost:8090/api/notification/register/{"+myIP+"}/{"+myPort+"}/"+"{"+userName+"}";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> e = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(addListener, HttpMethod.GET, e , String.class);
    }

}
