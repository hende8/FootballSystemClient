package PresentationLayer.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;


public class FootballAssociationController extends ControllerGUI {

    //////////////////league policy\\\\\\\\\\\\\\\\\\\\\\
    @FXML
    ComboBox<String> leagueComboBox;
    @FXML
    ComboBox<String> seasonComboBox;
    @FXML
    ComboBox<String> scoreComboBox;
    @FXML
    ComboBox<String> schedulingPolicyComboBox;
    @FXML
    Button doneButton;
    @FXML
    Button cancelButton;
    @FXML
    Pane policyLeaguePane;
    @FXML
    Label schedulingPolicyValidate;
    @FXML
    Label scoreValidate;
    @FXML
    Label leagueValidate;
    @FXML
    Label seasonValidate;
    //////////////////register team\\\\\\\\\\\\\\\\\\\\\\
    @FXML
    Pane teamRegistrationPane;
    @FXML
    ComboBox<String> teamOwnerComboBox;
    @FXML
    TextField teamNameTextField;
    @FXML
    Label teamValidate;
    @FXML
    Label teamOwnerValidate;
    @FXML
    Button cancelButtonRegister;
    @FXML
    Button registerTeam;
    List<String> leagues;
    String url="http://localHost:8090/api/FootballAssociation";

    /**
     * initialize the football association controller
     */
    public void initialize() {
        leagues=getListRequest(url+"/getAllLeague");
        for (String l : leagues) {
            leagueComboBox.getItems().add(l);
        }
        //score policy
        List<String> policies=getListRequest(url+"/getScorePolicy");
        for (String s : policies) {
            scoreComboBox.getItems().add(s);
        }
        //scheduling policy

        List<String> policiesAllocate=getListRequest(url+"/getAllocatePolicy");
        for (String s : policiesAllocate) {
            schedulingPolicyComboBox.getItems().add(s);
        }
        List<String> teamOwners=getListRequest(url+"/getAllTeamOwner");

        for (String s : teamOwners) {
            teamOwnerComboBox.getItems().add(s);
        }
    }

    /**
     * get the season by the league
     * @param event
     */
    @FXML
    private void leagueComboBoxOnAction(ActionEvent event) {
        String chosenLeague = leagueComboBox.getValue();
        for (String l : leagues) {
            if (chosenLeague!=null && chosenLeague.equals(l)) {
                List<String> leagueInfo=getListRequest(url+"/getLeagueInformation/"+l);
                for (String li : leagueInfo) {
                    if(!seasonComboBox.getItems().contains(li)) {
                        seasonComboBox.getItems().add(li);
                    }
                }
                break;
            }
        }
    }

    /**
     * cancel the window and back to default situation
     * @param a
     */
    @FXML
    private void cancelButtonOnAction(ActionEvent a) {
        policyLeaguePane.setVisible(false);
        leagueValidate.setVisible(false);
        seasonValidate.setVisible(false);
        scoreValidate.setVisible(false);
        schedulingPolicyValidate.setVisible(false);
        leagueComboBox.setValue(null);
        seasonComboBox.setValue(null);
        scoreComboBox.setValue(null);
        schedulingPolicyComboBox.setValue(null);
    }

    /**
     * cancel team register window
     * @param a
     */
    @FXML
    private void teamRegistrationCancelButton(ActionEvent a) {
        teamRegistrationPane.setVisible(false);
        teamValidate.setVisible(false);
        teamOwnerValidate.setVisible(false);
        teamOwnerComboBox.setValue(null);
        teamNameTextField.setText(null);
        footballAssociationMenuPane.setVisible(true);

    }

    /**
     * set policy after fill the fields
     * @param e
     */
    @FXML
    private void setPolicyButton(Event e) {
        restoreValidate();
        teamRegistrationPane.setVisible(false);
        footballAssociationMenuPane.setVisible(true);
        policyLeaguePane.setVisible(true);
        barButtonPushed((Button)e.getSource());
    }

    /**
     * cancel all validation signs
     */
    private void restoreValidate() {
        teamValidate.setVisible(false);
        schedulingPolicyValidate.setVisible(false);
        seasonValidate.setVisible(false);
        leagueValidate.setVisible(false);
        scoreValidate.setVisible(false);
        teamOwnerValidate.setVisible(false);
    }

    /**
     * register team
     */
    @FXML
    private void registerTeam() {
        boolean confirm = true;
        teamOwnerValidate.setVisible(false);
        teamValidate.setVisible(false);
        if (teamOwnerComboBox.getValue() == null) {
            confirm = false;
            teamOwnerValidate.setVisible(true);
        }
        if (teamNameTextField.getText().equals("")) {
            confirm = false;
            teamValidate.setVisible(true);
        }
        if (confirm) {
            HashMap<String,String> hashDetails = new HashMap<>();
            hashDetails.put("team_owner",teamOwnerComboBox.getValue());
            hashDetails.put("team_name",teamNameTextField.getText());
            postRequestHashMap(url+"/createTeam",hashDetails);
            showAlert("A new team registered to the system successfully");
            teamNameTextField.clear();
            teamOwnerComboBox.valueProperty().set(teamOwnerComboBox.getPromptText());
        }
    }

    /**
     * create team button
     * @param e
     */
    @FXML
    public void createTeamButton(Event e) {
        restoreValidate();
        policyLeaguePane.setVisible(false);
        footballAssociationMenuPane.setVisible(true);
        teamRegistrationPane.setVisible(true);
        barButtonPushed((Button)e.getSource());
    }

    /**
     * set policy done button. confirm the process
     * @param a
     */
    @FXML
    private void doneButtonOnAction(ActionEvent a) {
        leagueValidate.setVisible(false);
        seasonValidate.setVisible(false);
        scoreValidate.setVisible(false);
        schedulingPolicyValidate.setVisible(false);
        boolean confirm = true;
        if (leagueComboBox.getValue() == null || leagueComboBox.getValue().equals("Choose league") ) {
            confirm = false;
            leagueValidate.setVisible(true);
        }
        if (seasonComboBox.getValue() == null  || seasonComboBox.getValue().equals("Choose season")) {
            confirm = false;
            seasonValidate.setVisible(true);
        }
        if (scoreComboBox.getValue() == null || scoreComboBox.getValue().equals("Choose policy")) {
            confirm = false;
            scoreValidate.setVisible(true);
        }
        if (schedulingPolicyComboBox.getValue() == null || schedulingPolicyComboBox.getValue().equals("Choose policy")) {
            confirm = false;
            schedulingPolicyValidate.setVisible(true);
        }
        if (confirm) {
            HashMap<String,String> hashDetails = new HashMap<>();
            hashDetails.put("league_name",leagueComboBox.getValue());
            hashDetails.put("season_year",seasonComboBox.getValue());
            hashDetails.put("scoreMethodPolicy",scoreComboBox.getValue());
            hashDetails.put("schedulingPolicy",schedulingPolicyComboBox.getValue());
            postRequestHashMap(url+"/editLeaguePolicy",hashDetails);
            showAlert("League policy set successfully");
            leagueComboBox.valueProperty().set(leagueComboBox.getPromptText());
            seasonComboBox.valueProperty().set(seasonComboBox.getPromptText());
            scoreComboBox.valueProperty().set(scoreComboBox.getPromptText());
            schedulingPolicyComboBox.valueProperty().set(schedulingPolicyComboBox.getPromptText());
        }
    }
}

