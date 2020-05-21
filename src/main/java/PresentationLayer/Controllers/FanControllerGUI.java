package PresentationLayer.Controllers;

import FootballSystem.bootApp;
import PresentationLayer.ScreenController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import java.awt.*;

//@RequestMapping("/api/notification")
//@RestController
public class FanControllerGUI extends ControllerGUI {
    @FXML
    TextFlow textAlert;

    @FXML
    Button logOutBtn;

    @FXML
    VBox eventMenu;

    @FXML
    ScrollPane scrollAlerts;


    @FXML
    public void initialize() {
//        showAlert();
        bootApp.addListener(this);
    }
    @FXML
    public void showAlert1(String alert){


            eventMenu.getChildren().removeAll(eventMenu.getChildren());
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


    @FXML
    public void mouseInL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #a60000 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #A73A33 ; -fx-text-fill :  white ");
    }


    @FXML
    public void removeAlert(){
        System.out.println("jsdjkcbsjhdcbjshdcbjhsdcbjhsdc");
    }

    public void removeAlert(Button bRemove){
        Parent parent =  bRemove.getParent();
        ObservableList<Node> childrenUnmodifiable = parent.getChildrenUnmodifiable();
        Node alertToDelete= childrenUnmodifiable.get(0);
        String alertM= ((Text)alertToDelete).	getText();
//        ScreenController.getInstance().removeAlert(alertM);
//        showAlert();
    }

    @FXML
    public void postNotification(String alert ){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showAlert1(alert);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION,alert1);
//                alert.show();
            }
        });

    }
}
