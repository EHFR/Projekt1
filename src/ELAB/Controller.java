package ELAB;

import javafx.animation.PauseTransition;
import javafx.collections.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    /**
     * Allgemein/Logik
     */
    private Personenverwaltung personenverwaltung;
    private Fertigungsverwaltung fertigungsverwaltung;
    private Finanzverwaltung finanzverwaltung;
    private Bauteileverwaltung bauteileverwaltung;

    public Label error;

    /**
     * Anmeldung
     */
    public TextField anmeldungNameField;
    public TextField anmeldungPasswortField;
    public Label anmeldungAngemeldetLabel;
    public Button anmeldungAnmeldenBtn;
    public Button anmeldungAbmeldenBtn;

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
    public Spinner<String> fertigungsverwaltungFilterSpinner;
    private SpinnerValueFactory<String> fertigungsverwaltungFilerSpinnerValueFactory;
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

    /**
     * Finanzverwaltung Konten und Töpfe
     */
    public Label kontoBarkasseIstLabel;
    public Label kontoBarkasseSollLabel;
    public Label kontoKontoIstLabel;
    public Label kontoKontoSollLabel;
    public Label kontoKostenstelleIstLabel;
    public Label kontoKostenstelleSollLabel;

    public GridPane topfBearbeitungGrid;
    public ListView<String> topfverwaltungListe;
    public TextField topfNameField;
    public TextField topfSollField;
    public Label topfIstField;
    public Spinner<String> topfKasseSpinner;
    private SpinnerValueFactory<String> topfKasseSpinnerValueFactory;
    public Button topfverwaltungNeuBtn;
    public Button topfRemoveBtn;
    public Button topfSaveBtn;
    private boolean neuerTopfModus = false;

    /**
     * Finanzverwaltung Rechnungen
     */
    public GridPane rechnungBearbeitenGrid;
    public VBox rechnungTopfListBox;
    public VBox rechnungRechnungListBox;
    public VBox rechnungAuftragListBox;
    public VBox rechnungStatusBox;
    public ListView<String> rechnungenListe;
    public ListView<String> topfListe;
    public ListView<String> auftragsListe;
    public TextField rechnungNameField;
    public TextField rechnungAuftraggeberField;
    public TextField rechnungAnsprechpartnerField;
    public TextField rechnungBetragField;
    public Spinner<String> rechnungBezahlartSpinner;
    private SpinnerValueFactory<String> rechnungBezahlartSpinnerValueFactory;
    public Label rechnungTimestampLabel;
    public Button rechnungNeuBtn;
    public Button rechnungRemoveBtn;
    public Button rechnungSaveBtn;
    private boolean neueRechnungModus = false;
    private int topfListID = -1;

    public Spinner<String> rechnungStatusInBearbeitungSpinner;
    private SpinnerValueFactory<String> rechnungStatusInBearbeitungSpinnerValueFactory;
    public Spinner<String> rechnungStatusEingereichtSpinner;
    private SpinnerValueFactory<String> rechnungStatusEingereichtSpinnerValueFactory;
    public Spinner<String> rechnungStatusAbgewickeltSpinner;
    private SpinnerValueFactory<String> rechnungStatusAbgewickeltSpinnerValueFactory;
    public Spinner<String> rechnungStatusAusstehendSpinner;
    private SpinnerValueFactory<String> rechnungStatusAusstehendSpinnerValueFactory;
    public Label rechnungTimestampInBearbeitungLabel;
    public Label rechnungTimestampEingereichtLabel;
    public Label rechnungTimestampAbgewickeltLabel;
    public Label rechnungTimestampAusstehendLabel;

    /**
     * Bauteileverwaltung Bauteile
     */
    public ListView<String> kategorienListe;
    public ListView<String> produktListe;
    public Label produktNameLabel;
    public Hyperlink produktLink;
    public Label produktEinzelpreisLabel;
    public Spinner<Integer> produktMengeLagerndSpinner;
    public Label produktMengeBestelltLabel;
    public Label produktMengeGeplantLabel;
    public Label produktLagerortLabel;

    /**
     * Bauteileverwaltung Verwaltung
     */
    public ListView<String> kategorienverwaltungListe;
    public Button kategorienemoveBtn;
    public TextField kategorieNameField;
    public Button kategorieNewBtn;
    public ListView<String> produktverwaltungListe;
    public TextField produktverwaltungNameField;
    public TextField produktverwaltungLinkField;
    public Spinner<Integer> produktverwaltungEinzelpreisSpinner;
    public Spinner<Integer> produktverwaltungMengeLagerndSpinner;
    public Spinner<Integer> produktverwaltungMengeBestelltSpinner;
    public Spinner<Integer> produktverwaltungMengeGeplantSpinner;
    public TextField produktverwaltungLagerortField;
    public Button produktverwaltungNewBtn;
    public Button produktverwaltungRemoveBtn;
    public Button produktverwaltungSaveBtn;


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
        ObservableList<String> alleFilter = FXCollections.observableArrayList("Alles", "Angenommen", "Gefertigt", "Kosten kalkuliert",
                "Abgeholt", "Abgerechnet", "Auf Material warten", "Fertigung unterbrochen");
        this.fertigungsverwaltungFilerSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(alleFilter);
        this.fertigungsverwaltungFilterSpinner.setValueFactory(fertigungsverwaltungFilerSpinnerValueFactory);
        this.populateFertigungsverwaltungList();

        /**
         * Finanzverwaltung Konten und Töpfe
         */
        this.updateKontostaende();
        ObservableList<String> topfKasse = FXCollections.observableArrayList("Barkasse", "Konto", "Kostenstelle");
        this.topfKasseSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(topfKasse);
        this.topfKasseSpinner.setValueFactory(topfKasseSpinnerValueFactory);

        /**
         * Finanzverwaltung Rechnungen
         */
        ObservableList<String> bezahlarten = FXCollections.observableArrayList("Bar", "Überweisung", "Kostenstelle");
        this.rechnungBezahlartSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(bezahlarten);
        this.rechnungBezahlartSpinner.setValueFactory(rechnungBezahlartSpinnerValueFactory);

        this.rechnungStatusInBearbeitungSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusInBearbeitungSpinner.setValueFactory(rechnungStatusInBearbeitungSpinnerValueFactory);
        this.rechnungStatusEingereichtSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusEingereichtSpinner.setValueFactory(rechnungStatusEingereichtSpinnerValueFactory);
        this.rechnungStatusAbgewickeltSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusAbgewickeltSpinner.setValueFactory(rechnungStatusAbgewickeltSpinnerValueFactory);
        this.rechnungStatusAusstehendSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusAusstehendSpinner.setValueFactory(rechnungStatusAusstehendSpinnerValueFactory);

        this.populateTopfList();

        /**
         * Bauteileverwaltung Bauteile
         */

        /**
         * Bauteileverwaltung Verwaltung
         */
    }

    private void showError(ElabException error) {
        String errorText = error.getErrorText();
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
     * Anmeldung
     */
    public void anmeldenAction() {

    }

    public void abmeldenAction() {

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
                showError(new ElabException("Keine Person zum löschen ausgewählt!"));
                return;
            }
            Person personToRemove = personenverwaltung.getPersonen().get(listId);
            try {
                this.personenverwaltung.removePerson(personToRemove.getId());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.populatePersonenverwaltungList();
        }
    }

    public void savePersonAction() {
        if (this.personenverwaltungPasswortField.getText().equals("")) {
            showError(new ElabException("Passwort darf nicht leer sein!"));
            return;
        }
        if (this.personenverwaltungNameField.getText().equals("")) {
            showError(new ElabException("Name darf nicht leer sein!"));
            return;
        }
        if (this.neuePersonModus) {
            try {
                this.personenverwaltung.addPerson(this.personenverwaltungNameField.getText(),
                        this.personenverwaltungAdresseField.getText(), this.personenverwaltungTelefonField.getText(),
                        this.personenverwaltungEmailField.getText(), this.personenverwaltungTypSpinner.getValue(),
                        this.personenverwaltungPasswortField.getText());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.neuePersonModusDisable();
        } else {
            int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Keine Person zum bearbeiten ausgewählt!"));
                return;
            }
            Person person = personenverwaltung.getPersonen().get(listId);
            try {
                this.personenverwaltung.updatePerson(person.getId(), this.personenverwaltungNameField.getText(),
                        this.personenverwaltungAdresseField.getText(), this.personenverwaltungTelefonField.getText(),
                        this.personenverwaltungEmailField.getText(), this.personenverwaltungTypSpinner.getValue(),
                        this.personenverwaltungPasswortField.getText());
            } catch (ElabException e) {
                showError(e);
                return;
            }
        }
        showOk();
        this.populatePersonenverwaltungList();
    }

    private void personenverwaltungDisableInputs(boolean disabled) {
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
        switch (this.fertigungsverwaltungFilterSpinner.getValue()) {
            case "Alles":
                for (Auftrag auftrag : fertigungsverwaltung.getAuftraege()) {
                    allTitel.add(auftrag.getTitel());
                }

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
        this.fertigungsverwaltungRemoveBtn.setText("Abbrechen");
    }

    private void neuerAuftragModusDisable() {
        this.neuerAuftragModus = false;
        this.fertigungsverwaltungDisableInputs(true);
        this.fertigungsverwaltungStatusGrid.setDisable(true);
        this.fertigungsverwaltungListe.setDisable(false);
        this.fertigungsverwaltungTitelField.setText("");
        this.fertigungsverwaltungFertigungsartField.setText("");
        this.fertigungsverwaltungDateinameField.setText("");
        this.fertigungsverwaltungDateiortField.setText("");
        this.fertigungsverwaltungKostenField.setText("");
        this.fertigungsverwaltungTimestampLabel.setText("---");
        this.fertigungsverwaltungAuftraggeberField.setText("");
        this.fertigungsverwaltungAuftragbearbeiterArea.setText("");
        this.fertigungsverwaltungRemoveBtn.setText("Löschen");
    }


    public void removeAuftragAction() {
        if (this.neuerAuftragModus) {
            this.neuerAuftragModusDisable();
        } else {
            int listId = this.fertigungsverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Keine Person zum löschen ausgewählt!"));
                return;
            }
            Auftrag auftragToRemove = fertigungsverwaltung.getAuftraege().get(listId);
            this.fertigungsverwaltung.removeAuftrag(auftragToRemove.getId());
            this.populateFertigungsverwaltungList();
        }
    }

    public void saveAuftragAction() {
        if (this.fertigungsverwaltungAuftraggeberField.getText().equals("")) {
            showError(new ElabException("Auftraggeber darf nicht leer sein!"));
            return;
        }
        if (this.fertigungsverwaltungAuftragbearbeiterArea.getText().equals("")) {
            showError(new ElabException("Auftragbearbeiter darf nicht leer sein!"));
            return;
        }
        if (this.neuerAuftragModus) {
            try {
                this.fertigungsverwaltung.addAuftrag(this.fertigungsverwaltungTitelField.getText(), this.fertigungsverwaltungFertigungsartField.getText(),
                        this.fertigungsverwaltungDateinameField.getText(), this.fertigungsverwaltungDateiortField.getText(),
                        this.fertigungsverwaltungKostenField.getText(), this.fertigungsverwaltungAuftraggeberField.getText(),
                        this.fertigungsverwaltungAuftragbearbeiterArea.getText());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.neuerAuftragModusDisable();
        } else {
            int listId = this.fertigungsverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Keinen Auftrag zum bearbeiten ausgewählt!"));
                return;
            }
            try {
                Auftrag auftrag = fertigungsverwaltung.getAuftraege().get(listId);
                this.fertigungsverwaltung.updateAuftrag(auftrag.getId(), this.fertigungsverwaltungTitelField.getText(),
                        this.fertigungsverwaltungFertigungsartField.getText(), this.fertigungsverwaltungDateinameField.getText(),
                        this.fertigungsverwaltungDateiortField.getText(), Float.parseFloat(this.fertigungsverwaltungKostenField.getText()));
            } catch (ElabException e) {
                showError(e);
                return;
            }
        }
        showOk();
        this.populateFertigungsverwaltungList();
    }

    public void setFertigungsverwaltungUpdateStatus() {
        int listId = this.fertigungsverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError(new ElabException("Keinen Auftrag zum bearbeiten ausgewählt!"));
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

            this.fertigungsverwaltungTimestampAngenommenLabel.setText(auftrag.getStatusZeitstempel_angenommenString());
            this.fertigungsverwaltungTimestampGefertigtLabel.setText(auftrag.getStatusZeitstempel_gefertigtString());
            this.fertigungsverwaltungTimestampKostenLabel.setText(auftrag.getStatusZeitstempel_kosten_kalkuliertString());
            this.fertigungsverwaltungTimestampAbgeholtLabel.setText(auftrag.getStatusZeitstempel_abgeholtString());
            this.fertigungsverwaltungTimestampAbgerechnetLabel.setText(auftrag.getStatusZeitstempel_abgerechnetString());
            this.fertigungsverwaltungTimestampMaterialLabel.setText(auftrag.getStatusZeitstempel_wartenAufMaterialString());
            this.fertigungsverwaltungTimestampUnterbrochenLabel.setText(auftrag.getStatusZeitstempel_fertigungFehlgeschlagenString());
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

    /**
     * Finanzverwaltung Konten und Töpfe
     */
    private void updateKontostaende() {
        this.kontoBarkasseIstLabel.setText(this.finanzverwaltung.getIstbestandBarkasse());
        this.kontoBarkasseSollLabel.setText(this.finanzverwaltung.getSollbestandBarkasse());
        this.kontoKontoIstLabel.setText(this.finanzverwaltung.getIstbestandKonto());
        this.kontoKontoSollLabel.setText(this.finanzverwaltung.getSollbestandKonto());
        this.kontoKostenstelleIstLabel.setText(this.finanzverwaltung.getIstbestandKostenstelle());
        this.kontoKostenstelleSollLabel.setText(this.finanzverwaltung.getSollbestandKostenstelle());
    }

    private void populateTopfverwaltungListe() {
        ArrayList<String> allToepfe = new ArrayList<>();
        for (Topf topf : this.finanzverwaltung.getToepfe()) {
            allToepfe.add(topf.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allToepfe);
        topfverwaltungListe.setItems(items);
        this.topfverwaltungUpdateTextFields();
    }

    public void addTopfAction() {
        this.neuerTopfModus = true;
        this.topfBearbeitungGrid.setDisable(false);
        this.topfverwaltungListe.setDisable(true);
        this.topfNameField.setText("");
        this.topfSollField.setText("");
        this.topfIstField.setText("");
        this.topfKasseSpinnerValueFactory.setValue("Barkasse");
        this.topfRemoveBtn.setText("Abbrechen");
    }

    private void neuerTopfModusDisable() {
        this.neuerTopfModus = false;
        this.topfBearbeitungGrid.setDisable(true);
        this.topfverwaltungListe.setDisable(false);
        this.topfNameField.setText("");
        this.topfSollField.setText("");
        this.topfIstField.setText("");
        this.topfKasseSpinnerValueFactory.setValue("Barkasse");
        this.topfRemoveBtn.setText("Löschen");
    }

    public void removeTopfAction() {
        if (this.neuerTopfModus) {
            this.neuerTopfModusDisable();
        } else {
            int listId = this.topfverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Keinen Topf zum löschen ausgewählt!"));
                return;
            }
            try {
                this.finanzverwaltung.removeTopf(listId);
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.populateTopfverwaltungListe();
        }
    }

    public void saveTopfAction() {
        if (this.topfNameField.getText().equals("")) {
            showError(new ElabException("Topfname darf nicht leer sein!"));
            return;
        }
        if (this.topfSollField.getText().equals("")) {
            showError(new ElabException("Soll-Bestand darf nicht leer sein!"));
            return;
        }
        if (this.neuerTopfModus) {
            try {
                this.finanzverwaltung.addTopf(this.topfNameField.getText(),
                        this.topfSollField.getText(), this.topfKasseSpinner.getValue());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.neuerTopfModusDisable();
        } else {
            int listId = this.topfverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Keinen Topf zum Bearbeiten ausgewählt!"));
                return;
            }
            try {
                this.finanzverwaltung.updateTopf(listId, this.topfNameField.getText(),
                        this.topfSollField.getText(), this.topfKasseSpinner.getValue());
            } catch (ElabException e) {
                showError(e);
                return;
            }
        }
        showOk();
        this.populateTopfverwaltungListe();
    }

    public void topfverwaltungUpdateTextFields() {
        int listId = this.topfverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            this.topfBearbeitungGrid.setDisable(true);
        } else {
            this.topfBearbeitungGrid.setDisable(false);

            Topf topf = finanzverwaltung.getToepfe().get(listId);
            this.topfNameField.setText(topf.getName());
            this.topfSollField.setText(String.valueOf(topf.getSollbestand()));
            this.topfIstField.setText(String.valueOf(topf.getIstbestand()));
            this.topfKasseSpinnerValueFactory.setValue(topf.getKonto());
        }
    }

    /**
     * Finanzverwaltung Rechnungen
     */

    private void populateTopfList() {
        ArrayList<String> allToepfe = new ArrayList<>();
        for (Topf topf : this.finanzverwaltung.getToepfe()) {
            allToepfe.add(topf.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allToepfe);
        topfListe.setItems(items);
    }

    private void populateRechnungList() {
        ArrayList<String> allRechnungen = new ArrayList<>();
        for (Rechnung rechnung : this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen()) {
            allRechnungen.add(rechnung.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allRechnungen);
        rechnungenListe.setItems(items);
    }

    private void populateAuftragList() {
        ArrayList<String> allAuftraege = new ArrayList<>();
        for (Auftrag auftrag : this.fertigungsverwaltung.getAuftraege()) {
            allAuftraege.add(auftrag.getTitel());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allAuftraege);
        auftragsListe.setItems(items);
    }

    public void addRechnungAction() {
        if (this.topfListID != -1) {
            this.neueRechnungModus = true;
            this.rechnungDisableInputs(false);
            this.topfListe.setDisable(true);
            this.rechnungenListe.setDisable(true);
            this.rechnungNameField.setText("");
            this.rechnungAuftraggeberField.setText("");
            this.rechnungAnsprechpartnerField.setText("");
            this.rechnungBetragField.setText("");
            this.rechnungBezahlartSpinnerValueFactory.setValue("Bar");
            this.rechnungTimestampLabel.setText("---");
            this.rechnungRemoveBtn.setText("Abbrechen");
        } else {
            showError(new ElabException("Keinen Topf für die Rechnung ausgewählt!"));
        }
    }

    private void neueRechnungModusDisable() {
        this.neueRechnungModus = false;
        this.rechnungDisableInputs(true);
        this.topfListe.setDisable(false);
        this.rechnungenListe.setDisable(false);
        this.rechnungNameField.setText("");
        this.rechnungAuftraggeberField.setText("");
        this.rechnungAnsprechpartnerField.setText("");
        this.rechnungBetragField.setText("");
        this.rechnungBezahlartSpinnerValueFactory.setValue("Bar");
        this.rechnungTimestampLabel.setText("---");
        this.rechnungRemoveBtn.setText("Löschen");
    }

    public void removeRechnungAction() {
        if (this.neueRechnungModus) {
            this.neueRechnungModusDisable();
        } else {
            int listId = this.rechnungenListe.getFocusModel().getFocusedIndex();
            //listId = this.finanzverwaltung.getRechnung HMMMMM
            if (listId == -1) {
                showError(new ElabException("Keine Rechnung zum löschen ausgewählt!"));
                return;
            }
            Rechnung rechnungToRemove = this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen().get(listId);
            try {
                this.finanzverwaltung.getToepfe().get(this.topfListID).removeRechnung(rechnungToRemove.getId());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.populateRechnungList();
        }
    }

    public void saveRechnungAction() {
        if (this.rechnungNameField.getText().equals("")) {
            showError(new ElabException("Rechnungsname darf nicht leer sein!"));
            return;
        }
        if (this.rechnungAuftraggeberField.getText().equals("")) {
            showError(new ElabException("Auftraggeber darf nicht leer sein!"));
            return;
        }
        if (this.rechnungAnsprechpartnerField.getText().equals("")) {
            showError(new ElabException("Ansprechpartner darf nicht leer sein!"));
            return;
        }
        if (this.rechnungBetragField.getText().equals("")) {
            showError(new ElabException("Betrag darf nicht leer sein!"));
            return;
        }
        if (this.neueRechnungModus) {
            try {
                this.finanzverwaltung.getToepfe().get(this.topfListID).addRechnung(this.rechnungNameField.getText(),
                        this.rechnungAuftraggeberField.getText(), this.rechnungAnsprechpartnerField.getText(),
                        this.rechnungBetragField.getText(), this.rechnungBezahlartSpinner.getValue());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.neueRechnungModusDisable();
        } else {
            int listId = this.rechnungenListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Keine Rechnung zum Bearbeiten ausgewählt!"));
                return;
            }
            Rechnung rechnung = this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen().get(listId);
            try {
                this.finanzverwaltung.getToepfe().get(this.topfListID).updateRechnung(rechnung.getId(), this.rechnungNameField.getText(),
                        this.rechnungAuftraggeberField.getText(), this.rechnungAnsprechpartnerField.getText(),
                        this.rechnungBetragField.getText(), this.rechnungBezahlartSpinner.getValue());
            } catch (ElabException e) {
                showError(e);
                return;
            }
        }
        showOk();
        this.populateRechnungList();
    }

    private void rechnungDisableInputs(boolean disabled) {
        rechnungBearbeitenGrid.setDisable(disabled);
        rechnungAuftragListBox.setDisable(disabled);
    }

    public void rechnungUpdateTextFields() {
        int listId = this.rechnungenListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            this.rechnungDisableInputs(true);
        } else {
            this.rechnungDisableInputs(false);

            //Rechnung rechnung = this.finanzverwaltung.getRechungenFromTopf(this.topfListID).get(listId);
            Rechnung rechnung = this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen().get(listId);

            this.rechnungNameField.setText(rechnung.getName());
            this.rechnungAuftraggeberField.setText(rechnung.getAuftraggeber().getName());
            this.rechnungAnsprechpartnerField.setText(rechnung.getAnsprechpartner().getName());
            this.rechnungBetragField.setText(String.valueOf(rechnung.getBetrag()));
            this.rechnungBezahlartSpinnerValueFactory.setValue(rechnung.getBezahlart());
            this.rechnungTimestampLabel.setText(rechnung.getZeitstempelString());

            this.populateAuftragList();
        }
    }

    public void rechnungTopfChangedAction() {
        this.topfListID = this.topfListe.getFocusModel().getFocusedIndex();
        this.rechnungDisableInputs(true);
        if (this.topfListID == -1) {
            this.rechnungRechnungListBox.setDisable(true);
        } else {
            this.populateRechnungList();
        }
        this.rechnungUpdateTextFields();
    }

    public void rechnungLoadFromAuftrag() {
        int listId = this.auftragsListe.getFocusModel().getFocusedIndex();
        if (listId != -1) {
            Auftrag auftrag = this.fertigungsverwaltung.getAuftraege().get(listId);

            this.rechnungAuftraggeberField.setText(auftrag.getAuftraggeber().getName());
            this.rechnungAnsprechpartnerField.setText(auftrag.getAuftragbearbeiter().get(0).getName());
            this.rechnungBetragField.setText(String.valueOf(auftrag.getKosten()));
        } else {
            showError(new ElabException("Laden des Auftrages fehlgeschlagen..."));
        }
    }


    /**
     * Bauteileverwaltung Bauteile
     */


    /**
     * Bauteileverwaltung Verwaltung
     */
    public void removeKategorieAction() {

    }

    public void addKategorieAction() {

    }

    public void addProduktAction() {

    }

    public void removeProduktAction() {

    }

    public void saveProduktAction() {

    }
}