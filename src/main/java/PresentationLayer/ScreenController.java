package PresentationLayer;

//import PresentationLayer.Controllers.RefereeControllerGui;
//import System.Users.Referee;
//import System.Users.User;

import PresentationLayer.Controllers.RefereeControllerGui;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class ScreenController  {

    Stage primaryStage;

    String gameInfo;

    public String userName;
    public HashMap<String, List<String>> fanAlerts;

    RefereeControllerGui refereeControllerGui;

    private static ScreenController ourInstance = new ScreenController();

    public static ScreenController getInstance() {
        return ourInstance;
    }

    private ScreenController() {
        fanAlerts=new HashMap<>();
    }

    public RefereeControllerGui getRefereeControllerGui() {
        return refereeControllerGui;
    }
    public void setRefereeControllerGui(RefereeControllerGui ref) {
        refereeControllerGui=ref;
    }


    public void saveGameInfo(String homeTeam , String awayTeam , String gameID , RefereeControllerGui refereeControllerGui){
        gameInfo = gameID+","+homeTeam+","+awayTeam;
        this.refereeControllerGui = refereeControllerGui;
    }

    public String getGameInfo(){
        return gameInfo;
    }

    public List<String> getAlertsList(){
        return this.fanAlerts.get(userName);
    }

    public void setAlert(String userName,String alert){
        List<String> alerts= this.fanAlerts.get(userName);
        if(alerts!=null){
            alerts.add(alert);
            this.fanAlerts.replace(userName,alerts);
        }
        else{
            List<String> newAlerts= new LinkedList<>();
            newAlerts.add(alert);
            this.fanAlerts.put(userName,newAlerts);
        }
    }

}
