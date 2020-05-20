package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;


public class AddEventController extends ControllerGUI {

    @FXML
    ComboBox<String> comboEventBox;

    @FXML
    AnchorPane anchorPane;

    @FXML
    TextField playerNameText;

    @FXML
    TextField timeEvent;

    @FXML
    Text teamNameHome;

    @FXML
    Text teamNameAway;

    @FXML
    RadioButton home;

    @FXML
    RadioButton away;

    @FXML
            Label eventTypeValidate;
    @FXML
            Label minuteValidate;
    @FXML
            Label playerNameValidate;

    @FXML
            Button closeButton;
    String gameID;

    @FXML
    public void initialize() {
        eventTypeValidate.setVisible(false);
        minuteValidate.setVisible(false);
        playerNameValidate.setVisible(false);
        comboEventBox.getItems().addAll("Red Card", "Yellow Card", "Offside", "Goal", "Injury", "Offense");
        String[] str = (ScreenController.getInstance().getGameInfo()).split(",");
        teamNameHome.setText(str[0]);
        teamNameAway.setText(str[2]);
        gameID = str[1];
    }

    @FXML
    public void clickHomeRadio(){
        away.setSelected(false);
    }
    @FXML
    public void clickAwayRadio(){
        home.setSelected(false);
    }


    @FXML
    public void postEvent(Event event) {
        Button btn = ((Button) event.getSource());
        if (addEventValidate()) {
            String eventType = comboEventBox.getValue().replace(" ", "");
            int time2 = Integer.parseInt(timeEvent.getText());
            String playerName = playerNameText.getText();
            String team = "";
            if (home.isSelected()) {
                team = teamNameHome.getText();
            } else if (away.isSelected()) {
                team = teamNameAway.getText();
            }
            HashMap<String, String> hashDetails = new HashMap<>();
            hashDetails.put("user_name", username);
            hashDetails.put("game", gameID);
            hashDetails.put("type", eventType);
            hashDetails.put("min", String.valueOf(time2));
            hashDetails.put("playerName", playerName);
            hashDetails.put("team", team);

            postRequestHashMap("http://localHost:8090/api/referee/addEventDuringGame", hashDetails);
            if (eventType.equals("Goal") || eventType.equals("YellowCard") || eventType.equals("RedCard")) {
                ScreenController.getInstance().getRefereeControllerGui().updateEvent("Score", gameID);
            }
            showAlert("Event added successfully");
        }


        }


    private boolean addEventValidate() {
        eventTypeValidate.setVisible(false);
        minuteValidate.setVisible(false);
        playerNameValidate.setVisible(false);
        boolean confirm = true;
        String msg="";
        if(comboEventBox.getValue()==null ||comboEventBox.getValue().equals("Event Type") ){
            confirm=false;
            eventTypeValidate.setVisible(true);

            msg +="Choose event type"+"\n";
        }
        String time = timeEvent.getText();
        if(timeEvent.getText()==null || timeEvent.getText().equals("") ||!(checkMin(time)) ){
            confirm=false;
            minuteValidate.setVisible(true);
            msg +="The minute should be number between 1 to 120"+"\n";
        }

        String playerName = playerNameText.getText();
        if( playerName.equals("") || !checkPlayerName(playerName)){
            confirm=false;
            playerNameValidate.setVisible(true);
            msg+="The player name should contain only letters"+"\n";
        }

        if(!home.isSelected() && !away.isSelected()){
            msg+="You have to choose a team"+"\n";
            confirm=false;
        }
        showAlert(msg);
        return confirm;

    }

    private boolean checkPlayerName(String str){
        char [] chars = str.toCharArray();
        for(char c : chars){
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))
                return false;
        }
        return true;
    }

    private boolean checkMin(String str){
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
    @FXML
    public void closeAddEvent(){
        eventTypeValidate.setVisible(false);
        minuteValidate.setVisible(false);
        playerNameValidate.setVisible(false);
        playerNameText.clear();
        timeEvent.clear();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        home.fire();
        away.fire();
    }
}
