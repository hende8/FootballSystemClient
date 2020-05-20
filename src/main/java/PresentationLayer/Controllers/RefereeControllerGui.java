package PresentationLayer.Controllers;
import FootballSystem.StageListener;
import PresentationLayer.ScreenController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;


public class RefereeControllerGui extends ControllerGUI{


    @FXML
    Button homeBtn;

    Pane currPane;

    @FXML
    Text userInfo;

    @FXML
    Button logOutBtn;

    @FXML
    Text time1;

    @FXML
    Text date1;

    @FXML
    Text teamHomeName1;

    @FXML
    Text teamAwayName1;

    @FXML
    Text time2;

    @FXML
    Text date2;

    @FXML
    Text teamHomeName2;

    @FXML
    Text teamAwayName2;

    @FXML
    Text time3;

    @FXML
    Text date3;

    @FXML
    Text teamHomeName3;

    @FXML
    Text teamAwayName3;

    @FXML
    Text time4;

    @FXML
    Text date4;

    @FXML
    Text teamHomeName4;

    @FXML
    Text teamAwayName4;

    @FXML
    Text time5;

    @FXML
    Text date5;

    @FXML
    Text teamHomeName5;

    @FXML
    Text teamAwayName5;

    @FXML
    Pane info1;

    @FXML
    Pane info2;

    @FXML
    Pane info3;

    @FXML
    Pane info4;

    @FXML
    Pane info5;

    @FXML
    Text teamNameAway;

    @FXML
    Text teamNameHome;

    @FXML
    Pane rightPane;

    HashMap<Pane, String> gameInfo;

    @FXML
    Text score;

    @FXML

    Pane liveInfo;

    @FXML
    Button postEvent;

    @FXML
    VBox eventMenu;
    String url="http://localhost:8090/api/referee";
    @FXML
    public void initialize() {
        gameInfo = new HashMap<>();
        userInfo.setText(ScreenController.getInstance().userName);
        String name = username;
        List<String> myGames = getListRequest(url+"/getMyGames/"+name);
        if (myGames.size() > 0) {
            String str = myGames.get(0);
            String[] arrayInfo = str.split(",");
            changeText(info1, teamHomeName1, teamAwayName1, time1, date1, arrayInfo);
            gameInfo.put(info1, arrayInfo[4]);
        }
        if (myGames.size() > 1) {
            String str = myGames.get(1);
            String[] arrayInfo = str.split(",");
            changeText(info2, teamHomeName2, teamAwayName2, time2, date2, arrayInfo);
            gameInfo.put(info2, arrayInfo[4]);
        }

//
        if (myGames.size() > 2) {
            String str = myGames.get(2);
            String[] arrayInfo = str.split(",");
            changeText(info3, teamHomeName3, teamAwayName3, time3, date3, arrayInfo);
            gameInfo.put(info3, arrayInfo[4]);
        }
//
        if (myGames.size() > 3) {
            String str = myGames.get(3);
            String[] arrayInfo = str.split(",");
            changeText(info4, teamHomeName4, teamAwayName4, time4, date4, arrayInfo);
            gameInfo.put(info4, arrayInfo[4]);
        }
//
        if (myGames.size() > 4) {
            String str = myGames.get(4);
            String[] arrayInfo = str.split(",");
            changeText(info5, teamHomeName5, teamAwayName5, time5, date5, arrayInfo);
            gameInfo.put(info5, arrayInfo[4]);
        }
    }
    @FXML
    public void handleLogOutttt() throws Exception {
        System.out.println(getStringRequest("http://localhost:8090/api/user/logOut/"+username).getBody());
        StageListener.changeScene(username,"login.fxml");
    }
//
    private void changeText(Pane info, Text teamHomeName, Text teamAwayName, Text time, Text date, String[] arrayInfo) {
        info.setVisible(true);
        teamHomeName.setText(arrayInfo[0]);
        teamAwayName.setText(arrayInfo[1]);
        time.setText(arrayInfo[2]);
        date.setText(arrayInfo[3]);
    }


    public void updateEvent(String type, String info) {
        updateEvents();
        if (type.equals("Score")) {
            String refereeName = username;
            //String gameId = gameInfo.get(info);
            ResponseEntity<String> result = getStringRequest(url+"/getScore/"+info+"/"+refereeName);
            score.setText(result.getBody());
        }
    }

    @FXML
    public void postEvent(Event event) {
        try {
            String str;
            Button btn = ((Button) event.getSource());
            if(btn.getText().equals("Add event")){
                str = "AddEvent.fxml";
            }
            else{
                str = "AddEventReport.fxml";
            }
            Stage stage = new Stage();
            ScreenController.getInstance().saveGameInfo(gameInfo.get(currPane), teamNameHome.getText(), teamNameAway.getText(), this);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(str));
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
        }
    }


//    @FXML
    public void onEnteredPane(Event event) {
        try {
            Pane info = ((Pane) event.getSource());
            if (info != currPane) {
                info.setStyle("-fx-background-radius:10 ; -fx-background-color: #2060E4");
                for (Node node : info.getChildren()) {
                    if (node instanceof Text) {
                        node.setStyle("-fx-fill:white");
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    @FXML
    public void onExitPane(Event event) {
        try {
            Pane info = ((Pane) event.getSource());
            if (info != currPane) {
                info.setStyle("-fx-background-radius:10 ; -fx-background-color: #F6F6F4");
                for (Node node : info.getChildren()) {
                    if (node instanceof Text) {
                        node.setStyle("-fx-fill: #444444");
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    @FXML
    public void showRightMenu(Event event) {

        if (currPane != null) {
            currPane.setStyle("-fx-background-radius:10 ; -fx-background-color: #F6F6F4");
            for (Node node : currPane.getChildren()) {
                if (node instanceof Text) {
                    if (node instanceof Text) {
                        node.setStyle("-fx-fill: #444444");
                    }
                }
            }
        }
        rightPane.setVisible(true);
        Pane info = ((Pane) event.getSource());
        currPane = info;
        int i = 0;
        info.setStyle("-fx-background-radius:10 ; -fx-background-color:#2060E4");
        for (Node node : info.getChildren()) {
            if (node instanceof Text) {
                node.setStyle("-fx-fill:white");
                if (i == 3) {
                    teamNameHome.setText(((Text) node).getText());
                }
                if (i == 4) {
                    teamNameAway.setText(((Text) node).getText());
                }
                i++;
            }
        }



        String refereeName = username;
        String gameId = gameInfo.get(info);
        ResponseEntity<String> r =getStringRequest(url+"/getScore/"+gameId+"/"+refereeName);
        score.setText(r.getBody());
        ResponseEntity<String> result1= getStringRequest(url+"/isGameLive/"+gameId+"/"+refereeName);
        if (result1.getBody().equals("true")) {
            liveInfo.setVisible(true);
        } else {
            liveInfo.setVisible(false);
        }
        updateEvents();

    }

    @FXML
    public void mouseIn(Event event) {
        Button btn = ((Button) event.getSource());
        event.getTarget();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOut(Event event) {
        Button btn = ((Button) event.getSource());
        event.getTarget();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
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
    public void handleMouseClickedPass(Event event) {
        Button btn = ((Button) event.getSource());
        btn.setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10; -fx-text-fill: white");
    }

    @FXML
    public void onHover(Event event) {
        Button btn = ((Button) event.getSource());
        btn.setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10; -fx-text-fill: white");

    }

    @FXML
    public void OnExit(Event event) {
        Button btn = ((Button) event.getSource());
        btn.setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10; -fx-text-fill: white");
    }

    public void updateEvents() {
        eventMenu.getChildren().removeAll(eventMenu.getChildren());
        Pane pane2 = new Pane();
        pane2.setPrefWidth(600);
        pane2.setPrefHeight(15);
        pane2.setStyle("-fx-background-color:  White ; -fx-background-radius: 10 ;");

        eventMenu.getChildren().addAll(pane2);
        List<String> events = getListRequest(url+"/getEvents/"+gameInfo.get(currPane)+"/"+ username);
        for (String str : events) {
            if(!(str.contains("Goal")||str.contains("Yellow")||str.contains("Red"))){
                continue;
            }
            String[] output = str.split(",");
            String firstField;
            String secField;
            if(teamNameAway.getText().equals(output[3])){
                firstField = output[1];
                secField = output[2];
            }
            else{
                firstField = output[2];
                secField = output[1];
            }
            Pane pane = new Pane();
            pane.setPrefWidth(600);
            pane.setPrefHeight(50);
            pane.setStyle("-fx-background-color:  #F6F6F4 ; -fx-background-radius: 10 ;");

            Text text = new Text(firstField);
            text.setLayoutX(136);
            text.setLayoutY(30);
            text.setFill(Color.web("#444444"));
            text.setStyle("-fx-font-size: 20px;-fx-font-family:Open Sans");
            ImageView image;
            if (output[0].contains("Goal")) {
//                image = new ImageView(new Image("\\pictures\\goal.png"));
////                image = new ImageView(new ImageIcon(this.getClass().getResource("/images/bell-icon16.png")).getImage());
//                image.setFitWidth(20);
//                image.setFitHeight(20);
            } else if (output[0].contains("YellowCard")) {
//                image = new ImageView(new Image("\\pictures\\yellowCard.png"));
//                image.setFitWidth(14);
//                image.setFitHeight(20);
            } else if (output[0].contains("RedCard")) {
//                System.out.println(this.getClass().getResource("/pictures/redCard.png").toString());
                System.out.println("Working Directory = " + System.getProperty("user.dir"));

//                Image o=new Image("System.getProperty(\"user.dir\")+\\src\\main\\java\\pictures\\redCard.png");
//                image = new ImageView(new Image("\\src\\main\\java\\pictures\\redCard.png"));
//                image.setFitWidth(14);
//                image.setFitHeight(20);
            } else {
                break;
            }


//            image.setLayoutX(314);
//            image.setLayoutY(13);

            Text text2 = new Text(secField);
            text2.setLayoutX(394);
            text2.setLayoutY(30);
            text2.setFill(Color.web("#444444"));
            text2.setStyle("-fx-font-size: 20px;-fx-font-family:Open Sans");

//            pane.getChildren().addAll(text, image, text2);

            pane2 = new Pane();
            pane2.setPrefWidth(600);
            pane2.setPrefHeight(15);
            pane2.setStyle("-fx-background-color:  White ; -fx-background-radius: 10 ;");

            eventMenu.getChildren().addAll(pane, pane2);
        }
    }

    public void update(){}


}





