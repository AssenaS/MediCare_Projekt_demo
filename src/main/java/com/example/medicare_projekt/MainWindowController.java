package com.example.medicare_projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class MainWindowController {
    private Stage stage;
    private Scene hauptScene;
    PatientModel patientModel;
    MedicationListController medicationListController;

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private Button addButton;

    @FXML
    private Button viewMedicationListButton;

    @FXML
    private Button addReminderButton; // Add this line

    @FXML
    private TextField PatientenNameTextFeld;

    @FXML
    private DatePicker GeburtsdatumFeld;

    @FXML
    private TextField indexLabel;

    @FXML
    private AnchorPane hauptFenster;

    @FXML
    private TableView<Patient> tabelle;

    @FXML
    private TableColumn<Patient, TextField> tabelleName;

    @FXML
    private TableColumn<Patient, DatePicker> tabelleGeburtsdatum;

    @FXML
    private TableColumn<Patient, TextField> tabelleIndex;

    private ArrayList<Patient> arrayPatientList;

    public void initialize() {
        patientModel = new PatientModel();
        patientModel.loadDataFromFile();
        arrayPatientList = PatientModel.getPatients();

        if (tabelleName != null) {
            tabelleName.setCellValueFactory(new PropertyValueFactory<>("name"));
        }
        if (tabelleGeburtsdatum != null) {
            tabelleGeburtsdatum.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        }
        if (tabelleIndex != null) {
            tabelleIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        }
        updateTableViewPatient();
    }

    @FXML
    private void handlePatientHinzufuegenButton(ActionEvent event) {
        String name = PatientenNameTextFeld.getText();
        LocalDate birthday = GeburtsdatumFeld.getValue();
        String indexString = indexLabel.getText();

        if(indexString == null || indexString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText(null);
            alert.setContentText("Bitte geben Sie einen Index ein.");
            alert.showAndWait();
            return;

        }
        if (name != null && !name.isEmpty() && birthday != null && indexString != null) {
            ArrayList<Integer> medikamenteIndex = new ArrayList<>();
            for (String indexStr : indexString.split(",")) {
                try {
                    int index = Integer.parseInt(indexStr.trim());
                    medikamenteIndex.add(index);
                } catch (NumberFormatException e) {
                    System.out.println("Fehler beim Hinzufügen des Patienten");
                }
            }
            patientModel.addPatient(name,birthday, medikamenteIndex);
            patientModel.patientSerialize();
            PatientenNameTextFeld.clear();
            GeburtsdatumFeld.setValue(null);
            indexLabel.clear();
            tabelleName = new TableColumn<>();
            updateTableViewPatient();
        }
    }

    @FXML
    private void updateTableViewPatient() {
        if (tabelleName != null && tabelleGeburtsdatum != null && tabelleIndex != null) {
            tabelle.getItems().clear();
            tabelle.getItems().addAll(PatientModel.getPatients());
        }
        else {
            System.out.println("Alle Felder müssen ausgefüllt sein");
        }
    }

    public void handleMedikamentenListeAnsehen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MedikamentAnsicht.fxml"));
        Parent medikamentenAnsicht = loader.load();
        Scene scene = new Scene(medikamentenAnsicht);

        SceneManager.getInstance().setPreviousScene(((Node) actionEvent.getSource()).getScene());

        Stage medicationStage = new Stage();
        medicationStage.setScene(scene);
        medicationStage.setTitle("Medikamentenliste");

        MedicationListController controller = loader.getController();
        controller.setStage(stage);
        controller.setPreviousScene(SceneManager.getInstance().getPreviousScene());

        double mainWindowX = stage.getX();
        double mainWindowY = stage.getY();
        double mainStageWidth = stage.getWidth();

        double medicationStageX = mainWindowX + mainStageWidth + 10;
        double medicationStageY = mainWindowY;

        medicationStage.setX(medicationStageX);
        medicationStage.setY(medicationStageY);

        medicationStage.show();
    }

    public void setMainScene(Scene hauptseiteScene) {
        this.scene = scene;
    }

    public void handleMedikamenteBearbeiten(MouseEvent mouseEvent) {
        Patient selectedPatient = (Patient) tabelle.getSelectionModel().getSelectedItem();

        if(selectedPatient != null) {

        }
    }

    @FXML
    private void handleAddReminder() {
        Patient selectedPatient = tabelle.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText(null);
            alert.setContentText("Bitte wählen Sie einen Patienten aus.");
            alert.showAndWait();
            return;
        }

        TextInputDialog messageDialog = new TextInputDialog();
        messageDialog.setTitle("Add Reminder");
        messageDialog.setHeaderText("Enter reminder message:");
        Optional<String> messageResult = messageDialog.showAndWait();

        if (messageResult.isPresent()) {
            TextInputDialog timeDialog = new TextInputDialog("yyyy-MM-dd HH:mm");
            timeDialog.setTitle("Add Reminder");
            timeDialog.setHeaderText("Enter reminder time (yyyy-MM-dd HH:mm):");
            Optional<String> timeResult = timeDialog.showAndWait();

            if (timeResult.isPresent()) {
                LocalDateTime time = LocalDateTime.parse(timeResult.get(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                selectedPatient.addReminder(messageResult.get(), time);
            }
        }
    }
}
