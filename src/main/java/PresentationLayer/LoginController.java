package PresentationLayer;
//
//import PresentationLayer.ScreenController;
//import ServiceLayer.*;
//
//import System.Exeptions.NoSuchAUserNamedException;
//import System.Exeptions.WrongPasswordException;
//import System.Users.Referee;
//import System.Users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("login.fxml")
public class LoginController extends ImageView{

    @FXML
    TextField userName;
    @FXML
    TextField userPass;
    @FXML
    ImageView nameImg;
    @FXML
    ImageView passImg;
    @FXML
    Button loginBtn;


    @FXML
    public void handleLogin(){
//        if(userPass.getText().length() == 0) {
//            passImg.setVisible(true);
//            userPass.setStyle("-fx-prompt-text-fill: #6B6B6B ; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
//        }
//        if(userName.getText().length() == 0) {
//            nameImg.setVisible(true);
//            userName.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
//        }
//        String userN = userName.getText();
//        String userP = userPass.getText();
//        try {
//            int userType = FanController.getInstance().getUserType(userN, userP);
//            if(userType == 1) {//fan
//                ScreenController.getInstance().changeScene(userN,"MainFanMenu.fxml");
//            }else if(userType == 2) {//referee
//                ScreenController.getInstance().changeScene(userN,"MainRefereeMenu.fxml");
//            }else if(userType==5){//footballAssociation
//                ScreenController.getInstance().changeScene(userN,"MainFootballAssociationMenu.fxml");
//            }
//        }
//        catch (WrongPasswordException | NoSuchAUserNamedException e){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("אחד או יותר מהפרטים אינם נכונים");
//
//            alert.showAndWait();
//        }
//        catch (Exception e){
//
//            System.out.println(e.toString());
//        }
    }

    @FXML
    public void OnHover(){
        loginBtn.setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10");
    }

    @FXML
    public void OnExit(){
        loginBtn.setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10");
    }
    @FXML
    public void OnPress(){
        loginBtn.setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10");
    }

    @FXML
    public void initialize() {
        userName.setStyle("-fx-prompt-text-fill: transparent; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
    }

    @FXML
    public void handleMouseClickedName(){
        userName.setStyle("-fx-prompt-text-fill: transparent; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        nameImg.setVisible(false);
        if(userPass.getText().length() == 0) {
            passImg.setVisible(true);
            userPass.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        }
    }

    @FXML
    public void handleMouseClickedPass(){
        userPass.setStyle("-fx-prompt-text-fill: transparent; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
        passImg.setVisible(false);
        if(userName.getText().length() == 0) {
            userName.setStyle("-fx-prompt-text-fill: #6B6B6B; -fx-background-radius: 10;-fx-background-color: transparent;-fx-border-color: black;-fx-border-radius: 10");
            nameImg.setVisible(true);
        }

    }

}
