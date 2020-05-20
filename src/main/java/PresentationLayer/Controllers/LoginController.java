package PresentationLayer.Controllers;

//import PresentationLayer.ScreenController;
//import ServiceLayer.*;
//
//import System.Exeptions.NoSuchAUserNamedException;
//import System.Exeptions.WrongPasswordException;
//import System.Users.Referee;
//import System.Users.User;

import FootballSystem.StageListener;
import FootballSystem.bootApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
@FxmlView("login.fxml")
public class LoginController extends ControllerGUI{

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
    @ResponseBody
        public void handleLogin() throws Exception {

        if (userPass.getText().length() == 0) {
            passImg.setVisible(true);
            userPass.setStyle("-fx-prompt-text-fill: #6B6B6B ; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        }
        if (userName.getText().length() == 0) {
            nameImg.setVisible(true);
            userName.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        }
        String userN = userName.getText();
        String login = "http://localHost:8090/api/user/login";
        HashMap<String, String> hashmap = new HashMap();
        hashmap.put("password", userPass.getText());
        hashmap.put("user_name", userName.getText());
        ResponseEntity<String> responseEntity = postRequestHashMap(login,hashmap);
        if(responseEntity==null){
            showAlert("Something went wrong...try again!");
        }else {
            username=userName.getText();
            if (responseEntity.getBody().equals("1")) {//fan
                sendInfo();
                StageListener.changeScene(userN, "MainFanMenu.fxml");
            } else if (responseEntity.getBody().equals("2")) {//referee
                StageListener.changeScene(userN, "MainRefereeMenu.fxml");
            } else if (responseEntity.getBody().equals("5")) {//footballAssociation
                StageListener.changeScene(userN, "MainFootballAssociationMenu.fxml");
            }
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
        bootApp.addListener(this);
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
        String userName = "Max";//ScreenController.getInstance().userName;
        String addListener = "http://localHost:8090/api/notification/register/"+myIP+"/"+myPort+"/"+""+userName+"";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> e = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(addListener, HttpMethod.GET, e , String.class);
        System.out.println("dd");
    }

    @FXML
    public void update(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"AAA");
                alert.show();
            }
        });

    }

}
