package ELAB;

import javafx.animation.PauseTransition;
import javafx.collections.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    public GridPane personenverwaltungBearbeitungGrid;
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
    public GridPane fertigungsverwaltungBearbeitungGrid;
    public TextField fertigungsverwaltungTitelField;
    public TextField fertigungsverwaltungFertigungsartField;
    public TextField fertigungsverwaltungDateinameField;
    public TextField fertigungsverwaltungDateiortField;
    public TextField fertigungsverwaltungKostenField;
    public TextField fertigungsverwaltungAuftraggeberField;
    public Label fertigungsverwaltungTimestampLabel;
    public TextArea fertigungsverwaltungAuftragbearbeiterArea;
    public Button fertigungsverwaltungRemoveBtn;
    public Button fertigungsverwaltungSaveBtn;
    private boolean neuerAuftragModus = false;
    public GridPane fertigungsverwaltungStatusGrid;
    public Spinner<String> fertigungsverwaltungAngenommenSpinner;
    public Spinner<String> fertigungsverwaltungGefertigtSpinner;
    public Spinner<String> fertigungsverwaltungKostenSpinner;
    public Spinner<String> fertigungsverwaltungAbgeholtSpinner;
    public Spinner<String> fertigungsverwaltungAbgerechnetSpinner;
    public Spinner<String> fertigungsverwaltungMaterialSpinner;
    public Spinner<String> fertigungsverwaltungUnterbrochenSpinner;
    private SpinnerValueFactory<String> fertigungsverwaltungAngenommenSpinnerValueFactory;
    private SpinnerValueFactory<String> fertigungsverwaltungGefertigtSpinnerValueFactory;
    private SpinnerValueFactory<String> fertigungsverwaltungKostenSpinnerValueFactory;
    private SpinnerValueFactory<String> fertigungsverwaltungAbgeholtSpinnerValueFactory;
    private SpinnerValueFactory<String> fertigungsverwaltungAbgerechnetSpinnerValueFactory;
    private SpinnerValueFactory<String> fertigungsverwaltungMaterialSpinnerValueFactory;
    private SpinnerValueFactory<String> fertigungsverwaltungUnterbrochenSpinnerValueFactory;
    public Label fertigungsverwaltungTimestampAngenommenLabel;
    public Label fertigungsverwaltungTimestampGefertigtLabel;
    public Label fertigungsverwaltungTimestampKostenLabel;
    public Label fertigungsverwaltungTimestampAbgeholtLabel;
    public Label fertigungsverwaltungTimestampAbgerechnetLabel;
    public Label fertigungsverwaltungTimestampMaterialLabel;
    public Label fertigungsverwaltungTimestampUnterbrochenLabel;

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
        ObservableList<String> stadien = FXCollections.observableArrayList("Ja", "Nein");
        this.fertigungsverwaltungAngenommenSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.fertigungsverwaltungAngenommenSpinner.setValueFactory(fertigungsverwaltungAngenommenSpinnerValueFactory);
        this.fertigungsverwaltungGefertigtSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.fertigungsverwaltungGefertigtSpinner.setValueFactory(fertigungsverwaltungGefertigtSpinnerValueFactory);
        this.fertigungsverwaltungKostenSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.fertigungsverwaltungKostenSpinner.setValueFactory(fertigungsverwaltungKostenSpinnerValueFactory);
        this.fertigungsverwaltungAbgeholtSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.fertigungsverwaltungAbgeholtSpinner.setValueFactory(fertigungsverwaltungAbgeholtSpinnerValueFactory);
        this.fertigungsverwaltungAbgerechnetSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.fertigungsverwaltungAbgerechnetSpinner.setValueFactory(fertigungsverwaltungAbgerechnetSpinnerValueFactory);
        this.fertigungsverwaltungMaterialSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.fertigungsverwaltungMaterialSpinner.setValueFactory(fertigungsverwaltungMaterialSpinnerValueFactory);
        this.fertigungsverwaltungUnterbrochenSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.fertigungsverwaltungUnterbrochenSpinner.setValueFactory(fertigungsverwaltungUnterbrochenSpinnerValueFactory);
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
        this.personenverwaltungRemoveBtn.setText("Abbrechen");
    }

    private void neuePersonModusDisable() {
        this.neuePersonModus = false;
        this.personenverwaltungDisableInputs(true);
        this.personenverwaltungListe.setDisable(false);
        this.personenverwaltungNameField.setText("");
        this.personenverwaltungAdresseField.setText("");
        this.personenverwaltungTelefonField.setText("");
        this.personenverwaltungEmailField.setText("");
        this.personenverwaltungTimestampLabel.setText("---");
        this.personenverwaltungTypeValueFactory.setValue("Kunde");
        this.personenverwaltungPasswortField.setText("");
        this.personenverwaltungRemoveBtn.setText("Löschen");
    }

    public void removePersonAction() {
        if (this.neuePersonModus) {
            this.neuePersonModusDisable();
        } else {
            int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError("Keine Person zum löschen ausgewählt!");
                return;
            }
            Person personToRemove = personenverwaltung.getPersonen().get(listId);
            this.personenverwaltung.removePerson(personToRemove.getId());
            this.populatePersonenverwaltungList();
        }
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
            this.neuePersonModusDisable();
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
        //this.personenverwaltungNameField.setDisable(disabled);
        //this.personenverwaltungAdresseField.setDisable(disabled);
        //this.personenverwaltungTelefonField.setDisable(disabled);
        //this.personenverwaltungEmailField.setDisable(disabled);
        //this.personenverwaltungTimestampLabel.setDisable(disabled);
        //this.personenverwaltungTypSpinner.setDisable(disabled);
        //this.personenverwaltungPasswortField.setDisable(disabled);
        //this.personenverwaltungSaveBtn.setDisable(disabled);
        //this.personenverwaltungRemoveBtn.setDisable(disabled);
        personenverwaltungBearbeitungGrid.setDisable(disabled);
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
        this.fertigungsverwaltungStatusGrid.setDisable(true);
        this.fertigungsverwaltungListe.setDisable(true);
        this.fertigungsverwaltungTitelField.setText("");
        this.fertigungsverwaltungFertigungsartField.setText("");
        this.fertigungsverwaltungDateinameField.setText("");
        this.fertigungsverwaltungDateiortField.setText("");
        this.fertigungsverwaltungKostenField.setText("");
        this.fertigungsverwaltungTimestampLabel.setText("---");
        this.fertigungsverwaltungAuftraggeberField.setText(""); //todo aktuell angemeldeter
        this.fertigungsverwaltungAuftragbearbeiterArea.setText("");
    }


    public void removeAuftragAction() {
        int listId = this.fertigungsverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError("Keine Person zum löschen ausgewählt!");
            return;
        }
        Auftrag auftragToRemove = fertigungsverwaltung.getAuftraege().get(listId);
        this.fertigungsverwaltung.removeAuftrag(auftragToRemove.getId());
        this.populateFertigungsverwaltungList();
    }

    public void saveAuftragAction() {
        if (this.fertigungsverwaltungAuftraggeberField.getText().equals("")) {
            showError("Auftraggeber darf nicht leer sein!");
            return;
        }
        if (!this.personenverwaltung.personAlreadyExists(this.fertigungsverwaltungAuftraggeberField.getText())) {
            showError("Auftraggeber existiert nicht!");
            return;
        }
        for (String name : fertigungsverwaltungAuftragbearbeiterArea.getText().split("\r")) {
            if (!this.personenverwaltung.personAlreadyExists(name)) {
                showError("Auftragbearbeiter " + name + " existiert nicht!");
                return;
            }
        }
        try {
            Float.parseFloat(this.fertigungsverwaltungKostenField.getText());
        } catch (NumberFormatException e) {
            showError("Kosten wurden nicht als korrekte Kommazahl angegeben! (float)");
            return;
        }
        if (this.neuerAuftragModus) {
            this.fertigungsverwaltung.addAuftrag(this.fertigungsverwaltungTitelField.getText(), this.fertigungsverwaltungFertigungsartField.getText(),
                    this.fertigungsverwaltungDateinameField.getText(), this.fertigungsverwaltungDateiortField.getText(),
                    Float.parseFloat(this.fertigungsverwaltungKostenField.getText()));
            this.neuerAuftragModus = false;
        } else {
            int listId = this.fertigungsverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError("Keinen Auftrag zum bearbeiten ausgewählt!");
                return;
            }
            Auftrag auftrag = fertigungsverwaltung.getAuftraege().get(listId);
            this.fertigungsverwaltung.updateAuftrag(auftrag.getId(), this.fertigungsverwaltungTitelField.getText(),
                    this.fertigungsverwaltungFertigungsartField.getText(), this.fertigungsverwaltungDateinameField.getText(),
                    this.fertigungsverwaltungDateiortField.getText(), Float.parseFloat(this.fertigungsverwaltungKostenField.getText()));
        }
        showOk();
        this.populatePersonenverwaltungList();
    }

    public void setFertigungsverwaltungUpdateStatus() {
        int listId = this.fertigungsverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError("Keinen Auftrag zum bearbeiten ausgewählt!");
            return;
        }
        Auftrag auftrag = fertigungsverwaltung.getAuftraege().get(listId);
        this.fertigungsverwaltung.updateStatus(auftrag.getId(), jaNeinToBool(this.fertigungsverwaltungAngenommenSpinner.getValue()),
                jaNeinToBool(this.fertigungsverwaltungGefertigtSpinner.getValue()), jaNeinToBool(this.fertigungsverwaltungKostenSpinner.getValue()),
                jaNeinToBool(this.fertigungsverwaltungAbgeholtSpinner.getValue()), jaNeinToBool(this.fertigungsverwaltungAbgerechnetSpinner.getValue()),
                jaNeinToBool(this.fertigungsverwaltungMaterialSpinner.getValue()), jaNeinToBool(this.fertigungsverwaltungUnterbrochenSpinner.getValue()));
        showOk();
    }

    private void fertigungsverwaltungDisableInputs(boolean disabled) {
        //this.fertigungsverwaltungTitelField.setDisable(disabled);
        //this.fertigungsverwaltungFertigungsartField.setDisable(disabled);
        //this.fertigungsverwaltungDateinameField.setDisable(disabled);
        //this.fertigungsverwaltungDateiortField.setDisable(disabled);
        //this.fertigungsverwaltungTimestampLabel.setDisable(disabled);
        //this.fertigungsverwaltungKostenField.setDisable(disabled);
        //this.fertigungsverwaltungSaveBtn.setDisable(disabled);
        //this.fertigungsverwaltungRemoveBtn.setDisable(disabled);
        //this.fertigungsverwaltungAuftraggeberField.setDisable(disabled);
        //this.fertigungsverwaltungAuftragbearbeiterArea.setDisable(disabled);
        this.fertigungsverwaltungBearbeitungGrid.setDisable(disabled);
        this.fertigungsverwaltungStatusGrid.setDisable(disabled);
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
            this.fertigungsverwaltungTimestampLabel.setText(auftrag.getZeitstempelString());
            this.fertigungsverwaltungAuftraggeberField.setText(auftrag.getAuftraggeber().getName());
            this.fertigungsverwaltungAuftragbearbeiterArea.setText(auftrag.getAuftragbearbeiterString());

            this.fertigungsverwaltungAngenommenSpinnerValueFactory.setValue(this.boolToJaNein(auftrag.isAngenommen()));
            this.fertigungsverwaltungGefertigtSpinnerValueFactory.setValue(this.boolToJaNein(auftrag.isGefertigt()));
            this.fertigungsverwaltungKostenSpinnerValueFactory.setValue(this.boolToJaNein(auftrag.isKosten_kalkuliert()));
            this.fertigungsverwaltungAbgeholtSpinnerValueFactory.setValue(this.boolToJaNein(auftrag.isAbgeholt()));
            this.fertigungsverwaltungAbgerechnetSpinnerValueFactory.setValue(this.boolToJaNein(auftrag.isAbgerechnet()));
            this.fertigungsverwaltungMaterialSpinnerValueFactory.setValue(this.boolToJaNein(auftrag.isWartenAufMaterial()));
            this.fertigungsverwaltungUnterbrochenSpinnerValueFactory.setValue(this.boolToJaNein(auftrag.isFertigungFehlgeschlagen()));

            //this.fertigungsverwaltungTimestampAngenommenLabel.setText(auftrag.getStatusZeitstempel_angenommenString());//todo auftrag Timestamp als String holen
            //this.fertigungsverwaltungTimestampGefertigtLabel.setText(auftrag.getStatusZeitstempel_gefertigtString());
            //this.fertigungsverwaltungTimestampKostenLabel.setText(auftrag.getStatusZeitstempel_kosten_kalkuliertString());
            //this.fertigungsverwaltungTimestampAbgeholtLabel.setText(auftrag.getStatusZeitstempel_abgeholtString());
            //this.fertigungsverwaltungTimestampAbgerechnetLabel.setText(auftrag.getStatusZeitstempel_abgerechnetString());
            //this.fertigungsverwaltungTimestampMaterialLabel.setText(auftrag.getStatusZeitstempel_wartenAufMaterialString());
            //this.fertigungsverwaltungTimestampUnterbrochenLabel.setText(auftrag.getStatusZeitstempel_fertigungFehlgeschlagenString());
        }
    }

    private String boolToJaNein(boolean bool) {
        if (bool) {
            return "Ja";
        }
        return "Nein";
    }

    private boolean jaNeinToBool(String jaNein) {
        return jaNein.equals("Ja");
    }
}