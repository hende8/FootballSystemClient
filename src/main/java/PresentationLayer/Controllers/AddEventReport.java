package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.HashMap;


public class AddEventReport extends ControllerGUI{

    @FXML
    TextArea report;

    @FXML
    public void setReport(Event event){
        Button btn = ((Button) event.getSource());
        if(report.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fill please the report");
            alert.show();
        }
        else {
            String[] str = (ScreenController.getInstance().getGameInfo()).split(",");
//            RefereeController.getInstance().postEventReport(ScreenController.getInstance().userName, str[1], report.getText());
            HashMap<String,String> hashDetails= new HashMap<>();
            hashDetails.put("user_name",username);
            hashDetails.put("game",str[1]);
            hashDetails.put("report",report.getText());

            postRequestHashMap("http://localHost:8090/api/referee/postEventReport",hashDetails);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Report added successfully");
            alert.show();
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void closeWindow(Event event){
        Button btn = ((Button) event.getSource());
        Stage stage  = (Stage) btn.getScene().getWindow();
        stage.close();
    }


}
