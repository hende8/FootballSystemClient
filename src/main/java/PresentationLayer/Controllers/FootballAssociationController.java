//package PresentationLayer.Controllers;
//
//import PresentationLayer.ScreenController;
////import System.FootballObjects.Game;
//import ServiceLayer.FootballAssosiationController;
//import ServiceLayer.SystemManagerController;
//import System.FootballObjects.League;
//import System.FootballObjects.LeagueInformation;
//import System.Users.Referee;
//import System.Users.SystemManager;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.Text;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextField;
//
//import java.util.Date;
//
//
//public class FootballAssociationController extends ControllerGUI {
//
//    //////////////////league policy\\\\\\\\\\\\\\\\\\\\\\
//    @FXML
//    ComboBox<String> leagueComboBox;
//    @FXML
//    ComboBox<String> seasonComboBox;
//    @FXML
//    ComboBox<String> scoreComboBox;
//    @FXML
//    ComboBox<String> schedulingPolicyComboBox;
//    @FXML
//    Button doneButton;
//    @FXML
//    Button cancelButton;
//    @FXML
//    Pane policyLeaguePane;
//    @FXML
//    Label schedulingPolicyValidate;
//    @FXML
//    Label scoreValidate;
//    @FXML
//    Label leagueValidate;
//    @FXML
//    Label seasonValidate;
//    //////////////////register team\\\\\\\\\\\\\\\\\\\\\\
//    @FXML
//    Pane teamRegistrationPane;
//    @FXML
//    ComboBox<String> teamOwnerComboBox;
//    @FXML
//    TextField teamNameTextField;
//    @FXML
//    Label teamValidate;
//    @FXML
//    Label teamOwnerValidate;
//    @FXML
//    Button cancelButtonRegister;
//    @FXML
//    Button registerTeam;
//
//    public void initialize() {
//        for (String l : FootballAssosiationController.getInstance().getAllLeagueString()) {
//            leagueComboBox.getItems().add(l);
//        }
//        //score policy
//        for (String s : FootballAssosiationController.getInstance().getScorePolicyString()) {
//            scoreComboBox.getItems().add(s);
//        }
//        //scheduling policy
//        for (String s : FootballAssosiationController.getInstance().getAllocatePolicyString()) {
//            schedulingPolicyComboBox.getItems().add(s);
//        }
//        for (String s : FootballAssosiationController.getInstance().getAllTeamOwnerString()) {
//            teamOwnerComboBox.getItems().add(s);
//        }
//    }
//    @FXML
//    private void leagueComboBoxOnAction(ActionEvent event) {
//        String chosenLeague = leagueComboBox.getValue();
//        for (String l : FootballAssosiationController.getInstance().getAllLeagueString()) {
//            if (chosenLeague!=null && chosenLeague.equals(l)) {
//                for (String li : FootballAssosiationController.getInstance().getLeagueInformationString(l)) {
//                    if(!seasonComboBox.getItems().contains(li)) {
//                        seasonComboBox.getItems().add(li);
//                    }
//                }
//                break;
//            }
//        }
//    }
//
//    @FXML
//    private void cancelButtonOnAction(ActionEvent a) {
//        policyLeaguePane.setVisible(false);
//        leagueValidate.setVisible(false);
//        seasonValidate.setVisible(false);
//        scoreValidate.setVisible(false);
//        schedulingPolicyValidate.setVisible(false);
//        leagueComboBox.setValue(null);
//        seasonComboBox.setValue(null);
//        scoreComboBox.setValue(null);
//        schedulingPolicyComboBox.setValue(null);
//    }
//
//    @FXML
//    private void teamRegistrationCancelButton(ActionEvent a) {
//        teamRegistrationPane.setVisible(false);
//        teamValidate.setVisible(false);
//        teamOwnerValidate.setVisible(false);
//        teamOwnerComboBox.setValue(null);
//        teamNameTextField.setText(null);
//        footballAssociationMenuPane.setVisible(true);
//
//    }
//    @FXML
//    private void setPolicyButton(Event e) {
//        restoreValidate();
//        teamRegistrationPane.setVisible(false);
//        footballAssociationMenuPane.setVisible(true);
//        policyLeaguePane.setVisible(true);
//        barButtonPushed((Button)e.getSource());
//    }
//    private void restoreValidate() {
//        teamValidate.setVisible(false);
//        schedulingPolicyValidate.setVisible(false);
//        seasonValidate.setVisible(false);
//        leagueValidate.setVisible(false);
//        scoreValidate.setVisible(false);
//        teamOwnerValidate.setVisible(false);
//    }
//    @FXML
//    private void registerTeam() {
//        boolean confirm = true;
//        teamOwnerValidate.setVisible(false);
//        teamValidate.setVisible(false);
//        if (teamOwnerComboBox.getValue() == null) {
//            confirm = false;
//            teamOwnerValidate.setVisible(true);
//        }
//        if (teamNameTextField.getText().equals("")) {
//            confirm = false;
//            teamValidate.setVisible(true);
//        }
//        if (confirm) {
//            FootballAssosiationController.getInstance().createTeam(teamNameTextField.getText(), teamOwnerComboBox.getValue());
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("A new team registered to the system successfully");
//            alert.showAndWait();
//            teamNameTextField.setText(teamNameTextField.getPromptText());
//            teamOwnerComboBox.valueProperty().set(teamOwnerComboBox.getPromptText());
//        }
//    }
//    @FXML
//    public void createTeamButton(Event e) {
//        restoreValidate();
//        policyLeaguePane.setVisible(false);
//        footballAssociationMenuPane.setVisible(true);
//        teamRegistrationPane.setVisible(true);
//        barButtonPushed((Button)e.getSource());
//    }
//
//
//    @FXML
//    private void doneButtonOnAction(ActionEvent a) {
//        leagueValidate.setVisible(false);
//        seasonValidate.setVisible(false);
//        scoreValidate.setVisible(false);
//        schedulingPolicyValidate.setVisible(false);
//        boolean confirm = true;
////        String s = leagueComboBox.getValue();
////        String s1 = seasonComboBox.getValue();
////        String s2 = scoreComboBox.getValue();
////        String s3 = schedulingPolicyComboBox.getValue();
//        if (leagueComboBox.getValue() == null || leagueComboBox.getValue().equals("Choose league") ) {
//            confirm = false;
//            leagueValidate.setVisible(true);
//        }
//        if (seasonComboBox.getValue() == null  || seasonComboBox.getValue().equals("Choose season")) {
//            confirm = false;
//            seasonValidate.setVisible(true);
//        }
//        if (scoreComboBox.getValue() == null || scoreComboBox.getValue().equals("Choose policy")) {
//            confirm = false;
//            scoreValidate.setVisible(true);
//        }
//        if (schedulingPolicyComboBox.getValue() == null || schedulingPolicyComboBox.getValue().equals("Choose policy")) {
//            confirm = false;
//            schedulingPolicyValidate.setVisible(true);
//        }
//        if (confirm) {
//            FootballAssosiationController.getInstance().editLeaguePolicy(leagueComboBox.getValue(), seasonComboBox.getValue(), scoreComboBox.getValue(), schedulingPolicyComboBox.getValue());
//            showAlert("League policy set successfully");
//            leagueComboBox.valueProperty().set(leagueComboBox.getPromptText());
//            seasonComboBox.valueProperty().set(seasonComboBox.getPromptText());
//            scoreComboBox.valueProperty().set(scoreComboBox.getPromptText());
//            schedulingPolicyComboBox.valueProperty().set(schedulingPolicyComboBox.getPromptText());
//        }
//
//
//    }
//}
//
