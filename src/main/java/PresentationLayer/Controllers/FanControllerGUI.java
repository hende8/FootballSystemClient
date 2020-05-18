//package PresentationLayer.Controllers;
//
//import PresentationLayer.ScreenController;
//import ServiceLayer.FootballAssosiationController;
//import System.Users.Fan;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.control.*;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//
////import java.awt.*;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.TextFlow;
//import java.util.List;
//
//public class FanControllerGUI {
//    @FXML
//    TextFlow textAlert;
//
//    @FXML
//    Button logOutBtn;
//
//    @FXML
//    VBox eventMenu;
//
//    @FXML
//    ScrollPane scrollAlerts;
//
//
//    @FXML
//    public void initialize() {
//        showAlert();
//    }
//    @FXML
//    public void showAlert(){
//        List<String> alerts= ScreenController.getInstance().getAlertsList();
//        if(alerts!=null) {
//            eventMenu.getChildren().removeAll(eventMenu.getChildren());
//            for (String a : alerts) {
//                Button bRemove= new Button();
//                bRemove.setText("X");
//                bRemove.setOnAction((click -> {
//                    removeAlert(bRemove);
//                }));
//                bRemove.setStyle("-fx-background-color :  #A73A33; -fx-text-fill :  white ");
//                Pane pane = new Pane();
//                pane.setPrefWidth(20);
//                pane.setPrefHeight(36);
//                pane.setStyle("-fx-background-color:  #F6F6F4 ; -fx-background-radius: 10 ;");
//                Text t = new Text(a);
//                t.setLayoutX(50);
//                t.setLayoutY(20);
//                t.setFill(Color.web("#444444"));
//                t.setStyle("-fx-font-size: 20px;-fx-font-family:Open Sans");
//                pane.getChildren().addAll(t,bRemove);
//                eventMenu.getChildren().addAll(pane);
//            }
//        }
//    }
//
//    @FXML
//    public void mouseInL(){
//        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #a60000 ; -fx-text-fill : white ");
//    }
//
//    @FXML
//    public void mouseOutL(){
//        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #A73A33 ; -fx-text-fill :  white ");
//    }
//
//    @FXML
//    public void handleLogOut() throws Exception {
//        ScreenController.getInstance().changeSenceLogOut();
//    }
//
//    @FXML
//    public void removeAlert(){
//        System.out.println("jsdjkcbsjhdcbjshdcbjhsdcbjhsdc");
//    }
//
//    public void removeAlert(Button bRemove){
//        Parent parent =  bRemove.getParent();
//        ObservableList<Node> childrenUnmodifiable = parent.getChildrenUnmodifiable();
//        Node alertToDelete= childrenUnmodifiable.get(0);
//        String alertM= ((Text)alertToDelete).	getText();
//        ScreenController.getInstance().removeAlert(alertM);
//        showAlert();
//    }
//}
