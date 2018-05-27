package ELAB;

import javafx.animation.PauseTransition;
import javafx.collections.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    /**
     * Allgemein
     */
    private Personenverwaltung personenverwaltung;
    private Fertigungsverwaltung fertigungsverwaltung;
    private Finanzverwaltung finanzverwaltung;
    private Bauteileverwaltung bauteileverwaltung;

    public Label error;

    /**
     * Personenverwaltung
     */
    public ListView<String> personenverwaltungListe;
    public TextField personenverwaltungNameField;
    public TextField personenverwaltungAdresseField;
    public TextField personenverwaltungTelefonField;
    public TextField personenverwaltungEmailField;
    public PasswordField personenverwaltungPasswortField;
    public Spinner<String> personenverwaltungTypSpinner;
    private SpinnerValueFactory<String> personenverwaltungTypeValueFactory;
    public Label personenverwaltungTimestampLabel;
    public Button personenverwaltungRemoveBtn;
    public Button personenverwaltungSaveBtn;
    private boolean neuePersonModus = false;

    /**
     * Fertigungsverwaltung
     */
    public ListView<String> fertigungsverwaltungListe;
    public TextField fertigungsverwaltungTitelField;
    public TextField fertigungsverwaltungFertigungsartField;
    public TextField fertigungsverwaltungDateinameField;
    public TextField fertigungsverwaltungDateiortField;
    public TextField fertigungsverwaltungKostenField;
    public Spinner<String> fertigungsverwaltungStatusSpinner;
    private SpinnerValueFactory<String> fertigungsverwaltungStatusValueFactory;
    public TextField fertigungsverwaltungAuftraggeberField;
    public Label fertigungsverwaltungTimestampLabel;
    public TextArea fertigungsverwaltungAuftragbearbeiterArea;
    public Button fertigungsverwaltungRemoveBtn;
    public Button fertigungsverwaltungSaveBtn;
    private boolean neuerAuftragModus = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personenverwaltung = new Personenverwaltung();
        this.fertigungsverwaltung = new Fertigungsverwaltung();
        this.finanzverwaltung = new Finanzverwaltung();
        this.bauteileverwaltung = new Bauteileverwaltung();

        /**
         * Personenverwaltung INIT
         */
        this.populatePersonenverwaltungList();
        ObservableList<String> types = FXCollections.observableArrayList("Mitglied", "Kunde", "Lehrstuhl bezogene Person");
        this.personenverwaltungTypeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(types);
        this.personenverwaltungTypSpinner.setValueFactory(personenverwaltungTypeValueFactory);

        /**
         * Fertigungsverwaltung INIT
         */
        this.populateFertigungsverwaltungList();
        //ObservableList<String> stadien = FXCollections.observableArrayList("Angenommen", "Gefertigt", "Kosten kalkuliert", "Abgeholt", "Abgerechnet", "Auf Material warten", "Fertigung unterbrochen");
        //this.fertigungsverwaltungStatusValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        //this.fertigungsverwaltungStatusSpinner.setValueFactory(fertigungsverwaltungStatusValueFactory);
    }

    private void showError(String errorText) {
        System.out.println("Error: creating Label with -> " + errorText);
        this.error.setVisible(true);
        this.error.setText(errorText);
        this.error.setStyle("-fx-background-color: #f44336");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> hideErrorLabel());
        delay.play();
    }

    private void showOk() {
        System.out.println("okok");
        error.setVisible(true);
        error.setText("OK");
        error.setStyle("-fx-background-color: #4caf50");
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> hideErrorLabel());
        delay.play();
    }

    private void hideErrorLabel() {
        error.setVisible(false);
    }

    /**
     * Personenverwaltung
     */

    private void populatePersonenverwaltungList() {
        ArrayList<String> allNames = new ArrayList<>();
        for (Person person : personenverwaltung.getPersonen()) {
            allNames.add(person.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allNames);
        personenverwaltungListe.setItems(items);
        this.personenverwaltungUpdateTextFields();
    }

    public void addPersonAction() {
        this.neuePersonModus = true;
        this.personenverwaltungDisableInputs(false);
        this.personenverwaltungListe.setDisable(true);
        this.personenverwaltungNameField.setText("");
        this.personenverwaltungAdresseField.setText("");
        this.personenverwaltungTelefonField.setText("");
        this.personenverwaltungEmailField.setText("");
        this.personenverwaltungTimestampLabel.setText("---");
        this.personenverwaltungTypeValueFactory.setValue("Kunde");
        this.personenverwaltungPasswortField.setText("");
    }

    public void removePersonAction() {
        int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError("Keine Person zum löschen ausgewählt!");
            return;
        }
        Person personToRemove = personenverwaltung.getPersonen().get(listId);
        this.personenverwaltung.removePerson(personToRemove.getId());
        this.populatePersonenverwaltungList();
    }

    public void savePersonAction() {
        if (this.personenverwaltungPasswortField.getText().equals("")) {
            showError("Passwort darf nicht leer sein!");
            return;
        }
        if (this.personenverwaltungNameField.getText().equals("")) {
            showError("Name darf nicht leer sein!");
            return;
        }
        if (this.personenverwaltung.personAlreadyExists(this.personenverwaltungNameField.getText())) {
            showError("Name existiert schon, bitte einen anderen wählen!");
            return;
        }
        if (this.neuePersonModus) {
            this.personenverwaltung.addPerson(this.personenverwaltungNameField.getText(),
                    this.personenverwaltungAdresseField.getText(), this.personenverwaltungTelefonField.getText(),
                    this.personenverwaltungEmailField.getText(), this.personenverwaltungTypSpinner.getValue(),
                    this.personenverwaltungPasswortField.getText()); //spinner oder factory
            this.neuePersonModus = false;
        } else {
            int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError("Keine Person zum bearbeiten ausgewählt!");
                return;
            }
            Person person = personenverwaltung.getPersonen().get(listId);
            this.personenverwaltung.updatePerson(person.getId(), this.personenverwaltungNameField.getText(),
                    this.personenverwaltungAdresseField.getText(), this.personenverwaltungTelefonField.getText(),
                    this.personenverwaltungEmailField.getText(), this.personenverwaltungTypSpinner.getValue(),
                    this.personenverwaltungPasswortField.getText()); //spinner oder factory
        }
        showOk();
        this.populatePersonenverwaltungList();
    }

    private void personenverwaltungDisableInputs(boolean disabled) {
        this.personenverwaltungNameField.setDisable(disabled);
        this.personenverwaltungAdresseField.setDisable(disabled);
        this.personenverwaltungTelefonField.setDisable(disabled);
        this.personenverwaltungEmailField.setDisable(disabled);
        this.personenverwaltungTimestampLabel.setDisable(disabled);
        this.personenverwaltungTypSpinner.setDisable(disabled);
        this.personenverwaltungPasswortField.setDisable(disabled);
        this.personenverwaltungSaveBtn.setDisable(disabled);
        this.personenverwaltungRemoveBtn.setDisable(disabled);
    }

    public void personenverwaltungUpdateTextFields() {
        int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            this.personenverwaltungDisableInputs(true);
        } else {
            this.personenverwaltungDisableInputs(false);

            Person person = personenverwaltung.getPersonen().get(listId);
            this.personenverwaltungNameField.setText(person.getName());
            this.personenverwaltungAdresseField.setText(person.getAdresse());
            this.personenverwaltungTelefonField.setText(person.getTelefonnr());
            this.personenverwaltungEmailField.setText(person.getEmail());
            this.personenverwaltungTimestampLabel.setText(person.getZeitstempelString());
            this.personenverwaltungTypeValueFactory.setValue(person.getType());
            this.personenverwaltungPasswortField.setText(person.getPasswort());
        }
    }

    /**
     * Fertigungsverwaltung
     */
    private void populateFertigungsverwaltungList() {
        ArrayList<String> allTitel = new ArrayList<>();
        for (Auftrag auftrag : fertigungsverwaltung.getAuftraege()) {
            allTitel.add(auftrag.getTitel());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allTitel);
        fertigungsverwaltungListe.setItems(items);
        this.fertigungsverwaltungUpdateTextFields();
    }

    public void addAuftragAction() {
        this.neuerAuftragModus = true;
        this.fertigungsverwaltungDisableInputs(false);
        this.fertigungsverwaltungListe.setDisable(true);
        this.fertigungsverwaltungTitelField.setText("");
        this.fertigungsverwaltungFertigungsartField.setText("");
        this.fertigungsverwaltungDateinameField.setText("");
        this.fertigungsverwaltungDateiortField.setText("");
        this.fertigungsverwaltungKostenField.setText("");
        //this.fertigungsverwaltungStatusField.setText(""); // todo Spinner machen
        this.fertigungsverwaltungTimestampLabel.setText("---");
        this.fertigungsverwaltungAuftraggeberField.setText(""); //todo aktuell angemeldeter
        this.fertigungsverwaltungAuftragbearbeiterArea.setText("");
    }


    public void removeAuftragAction() {
        /*int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError("Keine Person zum löschen ausgewählt!");
            return;
        }
        Person personToRemove = personenverwaltung.getPersonen().get(listId);
        this.personenverwaltung.removePerson(personToRemove.getId());
        this.populatePersonenverwaltungList();*/
    }

    public void saveAuftragAction() {
        /*if (this.personenverwaltungPasswortField.getText().equals("")) {
            showError("Passwort darf nicht leer sein!");
            return;
        }
        if (this.personenverwaltungNameField.getText().equals("")) {
            showError("Name darf nicht leer sein!");
            return;
        }
        if (this.personenverwaltung.personAlreadyExists(this.personenverwaltungNameField.getText())) {
            showError("Name existiert schon, bitte einen anderen wählen!");
            return;
        }
        if (this.neuePersonModus) {
            this.personenverwaltung.addPerson(this.personenverwaltungNameField.getText(),
                    this.personenverwaltungAdresseField.getText(), this.personenverwaltungTelefonField.getText(),
                    this.personenverwaltungEmailField.getText(), this.personenverwaltungTypSpinner.getValue(),
                    this.personenverwaltungPasswortField.getText()); //spinner oder factory
            this.neuePersonModus = false;
        } else {
            int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError("Keine Person zum bearbeiten ausgewählt!");
                return;
            }
            Person person = personenverwaltung.getPersonen().get(listId);
            this.personenverwaltung.updatePerson(person.getId(), this.personenverwaltungNameField.getText(),
                    this.personenverwaltungAdresseField.getText(), this.personenverwaltungTelefonField.getText(),
                    this.personenverwaltungEmailField.getText(), this.personenverwaltungTypSpinner.getValue(),
                    this.personenverwaltungPasswortField.getText()); //spinner oder factory
        }
        showOk();
        this.populatePersonenverwaltungList();*/
    }

    private void fertigungsverwaltungDisableInputs(boolean disabled) {
        this.fertigungsverwaltungTitelField.setDisable(disabled);
        this.fertigungsverwaltungFertigungsartField.setDisable(disabled);
        this.fertigungsverwaltungDateinameField.setDisable(disabled);
        this.fertigungsverwaltungDateiortField.setDisable(disabled);
        this.fertigungsverwaltungTimestampLabel.setDisable(disabled);
        this.fertigungsverwaltungKostenField.setDisable(disabled);
        //this.fertigungsverwaltungStatusField.setDisable(disabled);
        this.fertigungsverwaltungSaveBtn.setDisable(disabled);
        this.fertigungsverwaltungRemoveBtn.setDisable(disabled);
        this.fertigungsverwaltungAuftraggeberField.setDisable(disabled);
        this.fertigungsverwaltungAuftragbearbeiterArea.setDisable(disabled);
    }

    public void fertigungsverwaltungUpdateTextFields() {
        int listId = this.fertigungsverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            this.fertigungsverwaltungDisableInputs(true);
        } else {
            this.fertigungsverwaltungDisableInputs(false);

            Auftrag auftrag = fertigungsverwaltung.getAuftraege().get(listId);
            this.fertigungsverwaltungTitelField.setText(auftrag.getTitel());
            this.fertigungsverwaltungFertigungsartField.setText(auftrag.getFertigungsart());
            this.fertigungsverwaltungDateinameField.setText(auftrag.getDateiname());
            this.fertigungsverwaltungDateiortField.setText(auftrag.getDateiort());
            this.fertigungsverwaltungKostenField.setText(String.valueOf(auftrag.getKosten()));
            //this.fertigungsverwaltungStatusField.setText(auftrag.getStatus()); //todo Spinner machen
            this.fertigungsverwaltungTimestampLabel.setText(auftrag.getZeitstempelString());
            this.fertigungsverwaltungAuftraggeberField.setText(auftrag.getAuftraggeber().getName());
            this.fertigungsverwaltungAuftragbearbeiterArea.setText(auftrag.getAuftragbearbeiterString());
        }
    }
}