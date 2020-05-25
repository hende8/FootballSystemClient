package PresentationLayer.Controllers;

import FootballSystem.bootApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;


public class FanControllerGUI extends ControllerGUI {
    @FXML
    TextFlow textAlert;
    @FXML
    Button logOutBtn;
    @FXML
    VBox eventMenu;
    @FXML
    ScrollPane scrollAlerts;

    /**
     * initialize the controller
     */
    @FXML
    public void initialize() {
        bootApp.addListener(this);
        //showing alert for user
        List<String> alerts=getListRequest("http://localhost:8090/api/notification/getMyAlerts/"+username);
        if(alerts!=null){
            for (String alert:alerts){
                showEventAlert(alert);
            }
        }
    }

    /**
     * show alert to fan screen
     * @param alert
     */
    @FXML
    public void showEventAlert(String alert){
        Button bRemove= new Button();
        bRemove.setText("X");
        bRemove.setOnAction((click -> {
            removeAlert(bRemove);
        }));
        bRemove.setStyle("-fx-background-color :  #A73A33; -fx-text-fill :  white ");
        Pane pane = new Pane();
        pane.setPrefWidth(20);
        pane.setPrefHeight(36);
        pane.setStyle("-fx-background-color:  #F6F6F4 ; -fx-background-radius: 10 ;");
        Text t = new Text(alert);
        t.setLayoutX(50);
        t.setLayoutY(20);
        t.setFill(Color.web("#444444"));
        t.setStyle("-fx-font-size: 20px;-fx-font-family:Open Sans");
        pane.getChildren().addAll(t,bRemove);
        eventMenu.getChildren().addAll(pane);

    }


    /**
     * remove alert from fan list of events
     * @param bRemove
     */
    public void removeAlert(Button bRemove) {
        Parent parent = bRemove.getParent();
        eventMenu.getChildren().remove(parent);
    }

    /**
     * show notification when the fan connects
     * @param alert
     */
    @FXML
    public void postNotification(String alert ){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showEventAlert(alert);
            }
        });

    }
}
