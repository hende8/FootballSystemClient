package PresentationLayer.Controllers;

//import PresentationLayer.Controllers.ScreenController;

import FootballSystem.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

public class ControllerGUI extends ImageView {
    static String username;

    String ipServer = "132.72.65.99:8090";
  //  String ipServer = "132.72.200.186:8090";
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

    /**
     * button pushed in the main buttons
     * @param btn
     */
    protected void barButtonPushed(Button btn) {
        btn.setOnMouseExited(null);
        if(currentButton!=null){
            currentButton.setOnMouseExited(this::mouseOut);
            currentButton.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
        }
        currentButton=btn;
    }

    /**
     * show alert information
     * @param msg
     */
    protected void showAlert(String msg){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
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

    /**
     * log out a user
     * @throws Exception
     */
    @FXML
    public void handleLogOut() throws Exception {
        getStringRequest("http://"+ipServer+"/api/user/logOut/"+username);
        StageListener.changeScene(username,"login.fxml");
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

    /**
     * a post request to server
     * @param url
     * @param hashmap hash map with a details to send
     * @return
     */
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

    /**
     * get list request from the server
     * @param url
     * @return
     */

    public List<String> getListRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> responseEntity = null;
        try {
            responseEntity = restTemplate.getForObject(url, List.class);
        }catch (Exception e){
            return null;

        }
        return responseEntity ;
    }

    /**
     * get a String request
     * @param url
     * @return
     */
    public ResponseEntity<String> getStringRequest(String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> e = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, e , String.class);

    }

    /**
     * check validation of player name
     * @param str
     * @return
     */
    protected boolean checkPlayerName(String str){
        char [] chars = str.toCharArray();
        for(char c : chars){
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')||(c == ' ')))
                return false;
        }
        return true;
    }

    /**
     * check validation of minute in the game
     * @param str
     * @return
     */
    protected boolean checkMin(String str){
        char [] chars = str.toCharArray();
        if(chars.length > 3 || chars.length < 1)
            return false;
        for(char c : chars){
            if (!((c >= '0' && c <= '9')))
                return false;
        }
        if(Integer.parseInt(str) > 120 || Integer.parseInt(str) < 1){
            return false;
        }
        return true;
    }
}
