//package PresentationLayer;
//
////import PresentationLayer.Controllers.RefereeControllerGui;
////import System.Users.Referee;
////import System.Users.User;
//import javafx.event.Event;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.SnapshotResult;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//
//
//public class ScreenController {
//
//    Stage primaryStage;
//
//    String gameInfo;
//
//    public String userName;
//    public HashMap<String, List<String>> fanAlerts;
//
//    RefereeControllerGui refereeControllerGui;
//
//    private static ScreenController ourInstance = new ScreenController();
//
//    public static ScreenController getInstance() {
//        return ourInstance;
//    }
//
//    private ScreenController() {
//        fanAlerts=new HashMap<>();
//    }
//
//    public RefereeControllerGui getRefereeControllerGui() {
//        return refereeControllerGui;
//    }
//
//    public void setPrimaryStage(Stage stage){
//        this.primaryStage = stage;
//
//
//    }
//
//    public void changeScene(String userName, String fxml) throws Exception{
//        this.userName = userName;
//        Parent root = FXMLLoader.load(getClass().getResource(fxml));
//        primaryStage.setScene(new Scene(root, 1440, 895) );
//        primaryStage.show();
//    }
//
//    public void changeSenceLogOut() throws Exception{
//        userName = "";
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        primaryStage.setScene(new Scene(root, 1440, 844) );
//        primaryStage.show();
//    }
//    public void changeSceneFootballAssociation(String footballAssociation) throws Exception{
//        userName = footballAssociation;
//        Parent root = FXMLLoader.load(getClass().getResource("MainFootballAssociationMenu.fxml"));
//        primaryStage.setScene(new Scene(root, 1440, 895) );
//        primaryStage.show();
//    }
//
//
//    public void saveGameInfo(String homeTeam , String awayTeam , String gameID , RefereeControllerGui refereeControllerGui){
//        gameInfo = gameID+","+homeTeam+","+awayTeam;
//        this.refereeControllerGui = refereeControllerGui;
//    }
//
//    public String getGameInfo(){
//        return gameInfo;
//    }
//
//    public List<String> getAlertsList(){
//        return this.fanAlerts.get(userName);
//    }
//
//    public void setAlert(String userName,String alert){
//        List<String> alerts= this.fanAlerts.get(userName);
//        if(alerts!=null){
//            alerts.add(alert);
//            this.fanAlerts.replace(userName,alerts);
//        }
//        else{
//            List<String> newAlerts= new LinkedList<>();
//            newAlerts.add(alert);
//            this.fanAlerts.put(userName,newAlerts);
//        }
//    }
//
//    public void removeAlert(String alert){
//        List<String> newList= this.fanAlerts.get(userName);
//        newList.removeIf(item->item.equals(alert));
//        fanAlerts.replace(userName,newList);
//
//    }
//}
