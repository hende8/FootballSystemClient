package PresentationLayer.Controllers;

//import PresentationLayer.ScreenController;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

public class ControllerGUI extends ImageView {
    static String username;
    @FXML
    Pane footballAssociationMenuPane;
    @FXML
    Button currentButton;

    @FXML
    Button homeBtn;

    @FXML
    Button gamesBtn;

    @FXML
    Button stadiumsBtn;

    @FXML
    Button teamsBtn;

    @FXML
    Button playersBtn;

    @FXML
    Button refereeBtn;
    @FXML
    Button createTeamsBtn;

    @FXML
    Text userInfo;

    @FXML
    Button logOutBtn;
    @FXML
    Button postEvent;
    protected void barButtonPushed(Button btn) {
        btn.setOnMouseExited(null);
        if(currentButton!=null){
            currentButton.setOnMouseExited(this::mouseOut);
            currentButton.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
        }
        currentButton=btn;
    }
    protected void showAlert(String msg){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();

    }




    @FXML
    public void mouseOut(Event e) {
        Button btn = (Button) e.getSource();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }
    @FXML
    public void mouseIn(Event e) {
        Button btn = (Button) e.getSource();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");

    }

    @FXML
    public void mouseInL() {
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #a60000 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutL() {
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #A73A33 ; -fx-text-fill :  white ");
    }
    @FXML
    public void handleLogOut() throws Exception {
//        ScreenController.getInstance().changeSenceLogOut();
    }
    @FXML
    public void handleMouseClickedPass() {
        postEvent.setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10; -fx-text-fill: white");
    }

    @FXML
    public void onHover() {
        postEvent.setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10; -fx-text-fill: white");
    }

    @FXML
    public void OnExit() {
        postEvent.setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10; -fx-text-fill: white");

    }

    @FXML
    public void OnHover(Event e){
        ((Button)e.getSource()).setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10");
    }

    @FXML
    public void OnExit(Event e){
        ((Button)e.getSource()).setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10");
    }
    @FXML
    public void OnPress(Event e){
        ((Button)e.getSource()).setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10");
    }

    public ResponseEntity<String> postRequestHashMap(String url, HashMap hashmap) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        final HttpEntity<HashMap<String, String>> entity = new HttpEntity<HashMap<String, String>>(hashmap , headers);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity<String> responseEntity=null;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        }catch (Exception e){
            return null;

        }
        return responseEntity ;
    }


    public List<String> getListRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<String> responseEntity = null;
        try {
            responseEntity = restTemplate.getForObject(url, List.class);
        }catch (Exception e){
            return null;

        }
        return responseEntity ;
    }


}