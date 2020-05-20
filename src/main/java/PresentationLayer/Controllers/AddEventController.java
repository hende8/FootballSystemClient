package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;


public class AddEventController extends ControllerGUI {

    @FXML
    ComboBox<String> comboEventBox;

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



    String gameID;

    @FXML
    public void initialize() {

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
        String str ="something goes wrong";
        try {
            String eventType = comboEventBox.getValue().replace(" ", "");
            String playerName = playerNameText.getText();
            if(!checkPlayerName(playerName)){
                str = "The player name should contain only letters";
                throw new Exception();
            }
            String time = timeEvent.getText();
            if(!(checkMin(time))) {
                str = "The min should be number between 1 to 120";
                throw new Exception();
            }
            int time2 = Integer.parseInt(time);
            String team = "";
            if(home.isSelected()){
                team = teamNameHome.getText();
            }
           else if(away.isSelected()){
               team = teamNameAway.getText();
            }
           else{
                str ="You have to choose a team";
                throw new Exception();
            }

//            RefereeController.getInstance().addEventDuringGame(ScreenController.getInstance().userName, gameID, eventType, time2, playerName, team);
            HashMap <String,String> hashDetails= new HashMap<>();
            hashDetails.put("user_name",username);
            hashDetails.put("game",gameID);
            hashDetails.put("type",eventType);
            hashDetails.put("min",String.valueOf(time2));
            hashDetails.put("playerName",playerName);
            hashDetails.put("team",team);

           postRequestHashMap("http://localHost:8090/api/referee/addEventDuringGame",hashDetails);
           if (eventType.equals("Goal")||eventType.equals("YellowCard")||eventType.equals("RedCard")) {
              ScreenController.getInstance().getRefereeControllerGui().updateEvent("Score",gameID);
           }
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Event added successfully");
            alert.show();
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();

        }
        catch (Exception e){
            e.printStackTrace();
            showAlert("Something went wrong...");
        }
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
}
