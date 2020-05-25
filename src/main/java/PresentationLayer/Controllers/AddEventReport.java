package PresentationLayer.Controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;


public class AddEventReport extends ControllerGUI{

    @FXML
    TextArea report;

    /**
     * set a report to game
     * @param event
     */
    @FXML
    public void setReport(Event event){
        Button btn = ((Button) event.getSource());
        if(report.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fill please the report");
            alert.show();
        }
        else {
            RefereeControllerGui g=ScreenController.getInstance().getRefereeControllerGui();
            String str = (g.gameInfo.get(ScreenController.getInstance().getRefereeControllerGui().currPane));
            HashMap<String,String> hashDetails= new HashMap<>();
            hashDetails.put("user_name",username);
            hashDetails.put("game_id",str);
            hashDetails.put("report",report.getText());

            HttpEntity<String> ans= postRequestHashMap("http://localHost:8090/api/referee/postEventReport",hashDetails);
            if(((ResponseEntity<String>) ans).getStatusCode()== HttpStatus.ACCEPTED){
                showAlert("Report added successfully");
            }else{
                showAlert("Something went wrong,try again");

            }
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * close add event report dialog
     * @param event
     */
    @FXML
    public void closeWindow(Event event){
        Button btn = ((Button) event.getSource());
        Stage stage  = (Stage) btn.getScene().getWindow();
        stage.close();
    }



}
