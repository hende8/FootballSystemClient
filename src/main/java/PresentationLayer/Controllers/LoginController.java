package PresentationLayer.Controllers;

//import PresentationLayer.ScreenController;
//import ServiceLayer.*;
//
//import System.Exeptions.NoSuchAUserNamedException;
//import System.Exeptions.WrongPasswordException;
//import System.Users.Referee;
//import System.Users.User;

import FootballSystem.StageListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

@Component
@FxmlView("login.fxml")
public class LoginController extends ControllerGUI{

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
    @FXML
    Label usernameValidate;
    @FXML
    Label passValidate;


    /**
     * handle the log in to system
     * every kind of user connects to another controller
     * @throws Exception
     */
    @FXML
    @ResponseBody
        public void handleLogin() throws Exception {
        usernameValidate.setVisible(false);
        passValidate.setVisible(false);
        if(userName.getText().equals("")) {
            nameImg.setVisible(true);
        }
        if(userPass.getText().equals("")) {
            passImg.setVisible(true);
        }
        if(validateParameters()){
            String userN = userName.getText();
            String login = "http://132.72.65.99:8090/api/user/login";
            HashMap<String, String> hashmap = new HashMap();
            hashmap.put("password", userPass.getText());
            hashmap.put("user_name", userName.getText());
            ResponseEntity<String> responseEntity = postRequestHashMap(login,hashmap);
            if(responseEntity==null){
                showAlert("Something went wrong...try again!");
                if(userName.getText().equals("")) {
                    nameImg.setVisible(true);
                }
                if(userPass.getText().equals("")) {
                    passImg.setVisible(true);
                }
            }else {
                ScreenController.getInstance().userName = userName.getText();
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
        }

    /**
     * check validate parameters
     * @return
     */
    private boolean validateParameters() {
        boolean confirm =true;
        if (userPass.getText().length() == 0) {
            passValidate.setVisible(true);
            userPass.setStyle("-fx-prompt-text-fill: #6B6B6B ; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
            confirm=false;
        }
        if (userName.getText().length() == 0) {
            usernameValidate.setVisible(true);
            userName.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
            confirm=false;
        }
        return confirm;
    }

    /**
     * send info to the server about a fan that want to get a notification in future
      */
    public void sendInfo(){
        String ip="";
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }

//        String myIP ="93.172.204.95";
        String myIP ="132.72.65.99";
        String myPort = "8092";
        String userName = username;//ScreenController.getInstance().userName;
        String addListener = "http://132.72.65.99:8090/api/notification/register/"+myIP+"/"+myPort+"/"+""+userName+"";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> e = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(addListener, HttpMethod.GET, e , String.class);
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

}
