package ELAB;

import javafx.animation.PauseTransition;
import javafx.collections.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
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
    public VBox abmeldenBox;
    public HBox anmeldenBox;
    public TabPane contentTabPane;
    public Tab tabPersonenverwaltung;
    public Tab tabFertigungsverwaltung;
    public Tab tabFinanzverwaltung;
    public Tab tabBauteileverwaltung;
    public Tab tabBauteileverwaltungVerwaltung;
    public Tab tabBauteileverwaltungBauteile;
    public TabPane bauteileverwaltungTabPane;
    public TextField anmeldungNameField;
    public TextField anmeldungPasswortField;
    public Label anmeldungAngemeldetLabel;
    public Button anmeldungAnmeldenBtn;
    public Button anmeldungAbmeldenBtn;
    private Person angemeldetePerson = null;


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
    public Label fertigungsverwaltungAuftragbearbeiterLabel;
    private ArrayList<Integer> fertigungsverwaltungAuftragbearbeiterIDs = new ArrayList<>();
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
    public GridPane rechnungBearbeitungGrid;
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
    public GridPane produktGrid;
    public VBox produktProdukteBox;
    public ListView<String> kategorienListe;
    public ListView<String> produktListe;
    public Label produktNameLabel;
    public Hyperlink produktLink;
    public Label produktEinzelpreisLabel;
    public Spinner<Integer> produktMengeLagerndSpinner;
    public SpinnerValueFactory<Integer> produktMengeLagerndSpinnerValueFactory;
    public Label produktMengeBestelltLabel;
    public Label produktMengeGeplantLabel;
    public Label produktLagerortLabel;
    private int kategorieListID = -1;

    /**
     * Bauteileverwaltung Verwaltung
     */
    public GridPane produktverwaltungGrid;
    public VBox produktverwaltungProdukteBox;
    public VBox produktverwaltungKategorienBox;
    public ListView<String> kategorienverwaltungListe;
    public Button kategorienemoveBtn;
    public TextField kategorieNameField;
    public Button kategorieNewBtn;
    public ListView<String> produktverwaltungListe;
    public TextField produktverwaltungNameField;
    public TextField produktverwaltungLinkField;
    public TextField produktverwaltungEinzelpreisField;
    public Spinner<Integer> produktverwaltungMengeLagerndSpinner;
    public Spinner<Integer> produktverwaltungMengeBestelltSpinner;
    public Spinner<Integer> produktverwaltungMengeGeplantSpinner;
    public SpinnerValueFactory<Integer> produktverwaltungMengeLagerndSpinnerValueFactory;
    public SpinnerValueFactory<Integer> produktverwaltungMengeBestelltSpinnerValueFactory;
    public SpinnerValueFactory<Integer> produktverwaltungMengeGeplantSpinnerValueFactory;
    public TextField produktverwaltungLagerortField;
    public Button produktverwaltungNewBtn;
    public Button produktverwaltungRemoveBtn;
    public Button produktverwaltungSaveBtn;
    private int kategorieverwaltungListID = -1;
    private boolean neuesProduktModus = false;

    /**
     * Bauteileverwaltung Bestellungen
     */
    public TableView<Bestellung> bestellungenTable;
    public TableColumn<Bestellung, String> bestellungenNameCol;
    public TableColumn<Bestellung, String> bestellungenProduktCol;
    public TableColumn<Bestellung, String> bestellungenKategorieCol;
    public TableColumn<Bestellung, String> bestellungenEinzelpreisCol;
    public TableColumn<Bestellung, String> bestellungenMengeCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personenverwaltung = new Personenverwaltung();
        this.fertigungsverwaltung = new Fertigungsverwaltung();
        this.finanzverwaltung = new Finanzverwaltung();
        this.bauteileverwaltung = new Bauteileverwaltung();

        /**
         * Anmeldung INIT
         */
        this.setAuthorization();

        /**
         * INIT Components
         */
        this.initPersonenverwaltung();
        this.initFertigungsverwaltung();
        this.initFinanzverwaltungKontenUndToepfe();
        this.initFinanzverwaltungRechnungen();
        this.initBauteileverwaltungBauteile();
        this.initBauteileverwaltungVerwaltung();
        this.initBauteileverwaltungBestellungen();

        /**
         * Workaround for Spinner not updating value when editing manually without arrow navigation
         */
        for (Field field : getClass().getDeclaredFields()) {
            try {
                Object obj = field.get(this);
                if (obj != null && obj instanceof Spinner)
                    ((Spinner) obj).focusedProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue) {
                            ((Spinner) obj).increment(0);
                        }
                    });
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
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
     * Anmeldung
     */
    public void anmeldenAction() {
        if (this.anmeldungNameField.getText().equals("")) {
            showError(new ElabException("Keinen Anmeldenamen angegeben"));
            return;
        }
        if (this.anmeldungPasswortField.getText().equals("")) {
            showError(new ElabException("Kein Passwort angegeben"));
            return;
        }
        try {
            personenverwaltung.checkAnmeldeinfos(this.anmeldungNameField.getText(), this.anmeldungPasswortField.getText());
            this.angemeldetePerson = this.personenverwaltung.getPersonByName(this.anmeldungNameField.getText());
        } catch (ElabException e) {
            showError(e);
            return;
        }
        this.setAuthorization();
        showOk();
    }

    public void abmeldenAction() {
        this.angemeldetePerson = null;
        this.setAuthorization();
    }

    private void setAuthorization() {
        if (this.angemeldetePerson == null) {
            this.abmeldenBox.setDisable(true);
            this.anmeldenBox.setDisable(false);
            this.contentTabPane.setVisible(false);
            this.tabPersonenverwaltung.setDisable(true);
            this.tabFertigungsverwaltung.setDisable(true);
            this.tabFinanzverwaltung.setDisable(true);
            this.tabBauteileverwaltung.setDisable(true);
            this.tabBauteileverwaltungVerwaltung.setDisable(true);
            this.anmeldungAngemeldetLabel.setText("Abgemeldet");

        } else if (this.angemeldetePerson.getType().equals("Mitglied")) {
            this.abmeldenBox.setDisable(false);
            this.anmeldenBox.setDisable(true);
            this.contentTabPane.setVisible(true);
            this.tabPersonenverwaltung.setDisable(false);
            this.tabFertigungsverwaltung.setDisable(false);
            this.tabFinanzverwaltung.setDisable(false);
            this.tabBauteileverwaltung.setDisable(false);
            this.tabBauteileverwaltungVerwaltung.setDisable(false);
            this.anmeldungAngemeldetLabel.setText("Mitglied: " + this.angemeldetePerson.getName());

        } else if (this.angemeldetePerson.getType().equals("Kunde") || this.angemeldetePerson.getType().equals("Lehrstuhl bezogene Person")) {
            this.abmeldenBox.setDisable(false);
            this.anmeldenBox.setDisable(true);
            this.contentTabPane.setVisible(true);
            this.tabPersonenverwaltung.setDisable(true);
            this.tabFertigungsverwaltung.setDisable(true);
            this.tabFinanzverwaltung.setDisable(true);
            this.tabBauteileverwaltung.setDisable(false);
            this.tabBauteileverwaltungVerwaltung.setDisable(true);
            this.contentTabPane.getSelectionModel().select(this.tabBauteileverwaltung);
            this.bauteileverwaltungTabPane.getSelectionModel().select(this.tabBauteileverwaltungBauteile);
            this.anmeldungAngemeldetLabel.setText(this.angemeldetePerson.getType() + ": " + this.angemeldetePerson.getName());

        } else {
            showError(new ElabException("Internal Error when setting the authorization"));
            this.contentTabPane.setDisable(true);
        }
        this.anmeldungNameField.setText("");
        this.anmeldungPasswortField.setText("");
    }

    /**
     * Personenverwaltung
     */

    public void initPersonenverwaltung() {
        this.populatePersonenverwaltungList();
        ObservableList<String> types = FXCollections.observableArrayList("Mitglied", "Kunde", "Lehrstuhl bezogene Person");
        this.personenverwaltungTypeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(types);
        this.personenverwaltungTypSpinner.setValueFactory(personenverwaltungTypeValueFactory);
    }

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
                this.personenverwaltung.removePerson(personToRemove.getId(), this.angemeldetePerson.getId());
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
    public void initFertigungsverwaltung() {
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
        ObservableList<String> alleFilter = FXCollections.observableArrayList("Alles", "Angenommen", "Nicht Angenommen",
                "Gefertigt", "Nicht Gefertigt", "Kosten kalkuliert", "Kosten nicht kalkuliert", "Abgeholt", "Nicht Abgeholt",
                "Abgerechnet", "Nicht Abgerechnet", "Auf Material warten", "Material vorhanden", "Fertigung unterbrochen", "Fertigung nicht unterbrochen");
        this.fertigungsverwaltungFilerSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(alleFilter);
        this.fertigungsverwaltungFilterSpinner.setValueFactory(fertigungsverwaltungFilerSpinnerValueFactory);
        this.populateFertigungsverwaltungList();
    }

    public void populateFertigungsverwaltungList() {
        ArrayList<String> allTitel = new ArrayList<>();
        for (Auftrag auftrag : this.fertigungsverwaltung.getAuftragByStatus(this.fertigungsverwaltungFilterSpinner.getValue())) {
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
        this.fertigungsverwaltungAuftragbearbeiterLabel.setText("");
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
        this.fertigungsverwaltungAuftragbearbeiterLabel.setText("");
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
            Auftrag auftragToRemove = this.fertigungsverwaltung.getAuftragByStatus(this.fertigungsverwaltungFilterSpinner.getValue()).get(listId);
            this.fertigungsverwaltung.removeAuftrag(auftragToRemove.getId());
            this.populateFertigungsverwaltungList();
        }
    }

    public void saveAuftragAction() {
        if (this.fertigungsverwaltungAuftraggeberField.getText().equals("")) {
            showError(new ElabException("Auftraggeber darf nicht leer sein!"));
            return;
        }
        if (this.fertigungsverwaltungAuftragbearbeiterIDs.size() == 0) {
            showError(new ElabException("Auftragbearbeiter darf nicht leer sein!"));
            return;
        }
        if (this.neuerAuftragModus) {
            try {
                this.fertigungsverwaltung.addAuftrag(this.fertigungsverwaltungTitelField.getText(), this.fertigungsverwaltungFertigungsartField.getText(),
                        this.fertigungsverwaltungDateinameField.getText(), this.fertigungsverwaltungDateiortField.getText(),
                        this.fertigungsverwaltungKostenField.getText(), this.fertigungsverwaltungAuftraggeberField.getText(),
                        this.fertigungsverwaltungAuftragbearbeiterIDs);
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
                Auftrag auftrag = this.fertigungsverwaltung.getAuftragByStatus(this.fertigungsverwaltungFilterSpinner.getValue()).get(listId);
                this.fertigungsverwaltung.updateAuftrag(auftrag.getId(), this.fertigungsverwaltungTitelField.getText(),
                        this.fertigungsverwaltungFertigungsartField.getText(), this.fertigungsverwaltungDateinameField.getText(),
                        this.fertigungsverwaltungDateiortField.getText(), Float.parseFloat(this.fertigungsverwaltungKostenField.getText()),
                        this.fertigungsverwaltungAuftraggeberField.getText(), this.fertigungsverwaltungAuftragbearbeiterIDs); //todo hier fehlen auftragbearbeiter und geber
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
        Auftrag auftrag = this.fertigungsverwaltung.getAuftragByStatus(this.fertigungsverwaltungFilterSpinner.getValue()).get(listId);
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

            Auftrag auftrag = this.fertigungsverwaltung.getAuftragByStatus(this.fertigungsverwaltungFilterSpinner.getValue()).get(listId);
            this.fertigungsverwaltungTitelField.setText(auftrag.getTitel());
            this.fertigungsverwaltungFertigungsartField.setText(auftrag.getFertigungsart());
            this.fertigungsverwaltungDateinameField.setText(auftrag.getDateiname());
            this.fertigungsverwaltungDateiortField.setText(auftrag.getDateiort());
            this.fertigungsverwaltungKostenField.setText(String.valueOf(auftrag.getKosten()));
            this.fertigungsverwaltungTimestampLabel.setText(auftrag.getZeitstempelString());
            this.fertigungsverwaltungAuftraggeberField.setText(auftrag.getAuftraggeber().getName());
            StringBuilder auftragbearbeiterNamen = new StringBuilder();
            for (Person auftragbearbeiter : auftrag.getAuftragbearbeiter()) {
                auftragbearbeiterNamen.append(auftragbearbeiter.getName()).append(";");
            }
            this.fertigungsverwaltungAuftragbearbeiterLabel.setText(auftragbearbeiterNamen.toString());

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

    public void editAuftragbearbeiterAction() {
        this.fertigungsverwaltungAuftragbearbeiterIDs = PopupFertigungsverwaltungSetAuftraggeber.display("Auftraggeber wählen", this.fertigungsverwaltungAuftragbearbeiterIDs, this.personenverwaltung.getPersonen());
        StringBuilder auftragbearbeiterNamen = new StringBuilder();
        for (Integer id : fertigungsverwaltungAuftragbearbeiterIDs) {
            auftragbearbeiterNamen.append(personenverwaltung.getPersonByID(id).getName()).append(";");
        }
        this.fertigungsverwaltungAuftragbearbeiterLabel.setText(auftragbearbeiterNamen.toString());
    }

    /**
     * Finanzverwaltung Konten und Töpfe
     */
    public void initFinanzverwaltungKontenUndToepfe() {
        this.updateKontostaende();
        ObservableList<String> topfKasse = FXCollections.observableArrayList("Barkasse", "Konto", "Kostenstelle");
        this.topfKasseSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(topfKasse);
        this.topfKasseSpinner.setValueFactory(topfKasseSpinnerValueFactory);
        this.populateTopfverwaltungListe();
    }

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
                Topf topfToRemove = this.finanzverwaltung.getToepfe().get(listId);
                this.finanzverwaltung.removeTopf(topfToRemove.getId());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.populateTopfverwaltungListe();
        }
        this.updateKontostaende();
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
                Topf topf = this.finanzverwaltung.getToepfe().get(listId);
                this.finanzverwaltung.updateTopf(topf.getId(), this.topfNameField.getText(),
                        this.topfSollField.getText(), this.topfKasseSpinner.getValue());
            } catch (ElabException e) {
                showError(e);
                return;
            }
        }
        showOk();
        this.populateTopfverwaltungListe();
        this.updateKontostaende();
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
            this.topfKasseSpinnerValueFactory.setValue(topf.getKasse());
        }
    }

    public void exitFinanzverwaltungKontenUndToepfe() {
        try {
            this.initFinanzverwaltungRechnungen();
        } catch (NullPointerException e) {
            System.out.print("");
        }
    }

    /**
     * Finanzverwaltung Rechnungen
     */

    public void initFinanzverwaltungRechnungen() {
        ObservableList<String> bezahlarten = FXCollections.observableArrayList("Bar", "Überweisung", "Kostenstelle");
        this.rechnungBezahlartSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(bezahlarten);
        this.rechnungBezahlartSpinner.setValueFactory(rechnungBezahlartSpinnerValueFactory);

        ObservableList<String> stadien = FXCollections.observableArrayList("Ja", "Nein");
        this.rechnungStatusInBearbeitungSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusInBearbeitungSpinner.setValueFactory(rechnungStatusInBearbeitungSpinnerValueFactory);
        this.rechnungStatusEingereichtSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusEingereichtSpinner.setValueFactory(rechnungStatusEingereichtSpinnerValueFactory);
        this.rechnungStatusAbgewickeltSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusAbgewickeltSpinner.setValueFactory(rechnungStatusAbgewickeltSpinnerValueFactory);
        this.rechnungStatusAusstehendSpinnerValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(stadien);
        this.rechnungStatusAusstehendSpinner.setValueFactory(rechnungStatusAusstehendSpinnerValueFactory);

        this.populateTopfList();
    }

    private void populateTopfList() {
        ArrayList<String> allToepfe = new ArrayList<>();
        this.topfListID = -1;
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
        rechnungBearbeitungGrid.setDisable(disabled);
        rechnungAuftragListBox.setDisable(disabled);
    }

    public void rechnungUpdateTextFields() {
        int listId = this.rechnungenListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            this.rechnungDisableInputs(true);
        } else {
            this.rechnungDisableInputs(false);
            Rechnung rechnung = this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen().get(listId);

            this.rechnungNameField.setText(rechnung.getName());
            this.rechnungAuftraggeberField.setText(rechnung.getAuftraggeber().getName());
            this.rechnungAnsprechpartnerField.setText(rechnung.getAnsprechpartner().getName());
            this.rechnungBetragField.setText(String.valueOf(rechnung.getBetrag()));
            this.rechnungBezahlartSpinnerValueFactory.setValue(rechnung.getBezahlart());
            this.rechnungTimestampLabel.setText(rechnung.getZeitstempelString());

            this.rechnungStatusInBearbeitungSpinnerValueFactory.setValue(this.boolToJaNein(rechnung.isInBearbeitung()));
            this.rechnungStatusEingereichtSpinnerValueFactory.setValue(this.boolToJaNein(rechnung.isEingereicht()));
            this.rechnungStatusAbgewickeltSpinnerValueFactory.setValue(this.boolToJaNein(rechnung.isAbgewickelt()));
            this.rechnungStatusAusstehendSpinnerValueFactory.setValue(this.boolToJaNein(rechnung.isAusstehend()));

            this.rechnungTimestampInBearbeitungLabel.setText(rechnung.getStatusZeitstempel_inBearbeitungString());
            this.rechnungTimestampEingereichtLabel.setText(rechnung.getStatusZeitstempel_eingereichtString());
            this.rechnungTimestampAbgewickeltLabel.setText(rechnung.getStatusZeitstempel_abgewickeltString());
            this.rechnungTimestampAusstehendLabel.setText(rechnung.getStatusZeitstempel_ausstehendString());

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

            //this.rechnungAuftraggeberField.setText(auftrag.getAuftraggeber().getName()); // todo wieder einbinden!
            //this.rechnungAnsprechpartnerField.setText(auftrag.getAuftragbearbeiter().get(0).getName());
            this.rechnungBetragField.setText(String.valueOf(auftrag.getKosten()));
        } else {
            showError(new ElabException("Laden des Auftrages fehlgeschlagen..."));
        }
    }

    public void setRechungUpdateStatus() {
        int listId = this.rechnungenListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError(new ElabException("Keine Rechnung zum bearbeiten ausgewählt!"));
            return;
        }
        Rechnung rechnung = this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen().get(listId);
        rechnung.updateStatus(jaNeinToBool(this.rechnungStatusInBearbeitungSpinner.getValue()), jaNeinToBool(this.rechnungStatusEingereichtSpinner.getValue()),
                jaNeinToBool(this.rechnungStatusAbgewickeltSpinner.getValue()), jaNeinToBool(this.rechnungStatusAusstehendSpinner.getValue()));
        showOk();
    }

    public void moveRechnungAction() {
        int listId = this.rechnungenListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError(new ElabException("Keine Rechnung zum bearbeiten ausgewählt!"));
            return;
        }
        int newTopfID = PopupRechnungChangeTopf.display("Rechnung verschieben", this.finanzverwaltung.getToepfe());
        if (newTopfID >= 0) {
            Rechnung rechnung = this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen().get(listId);
            rechnung.setNewTopfID(newTopfID);
            this.populateRechnungList();
        } else if (newTopfID == -1) {
            showError(new ElabException("Es wurde kein Topf gewählt!"));
        }
    }

    public void exportRechnungAction() {
        int listId = this.rechnungenListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError(new ElabException("Keine Rechnung zum Exportieren ausgewählt!"));
            return;
        }
        Rechnung rechnung = this.finanzverwaltung.getToepfe().get(this.topfListID).getRechnungen().get(listId);
        rechnung.exportToPDF();
        showOk();
    }

    public void exitFinanzverwaltungRechnungen() {
        try {
            this.initFinanzverwaltungKontenUndToepfe();
        } catch (NullPointerException e) {
            System.out.print("");
        }
    }

    /**
     * Bauteileverwaltung Bauteile
     */
    private void initBauteileverwaltungBauteile() {
        this.produktMengeLagerndSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        this.produktMengeLagerndSpinner.setValueFactory(produktMengeLagerndSpinnerValueFactory);
        this.populateKategorienList();
    }

    private void populateKategorienList() {
        ArrayList<String> allKategorien = new ArrayList<>();
        this.kategorieListID = -1;
        for (Kategorie kategorie : bauteileverwaltung.getKategorien()) {
            allKategorien.add(kategorie.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allKategorien);
        kategorienListe.setItems(items);
        this.populateProdukteList();
    }

    private void populateProdukteList() {
        if (this.kategorieListID != -1) {
            ArrayList<String> allProdukte = new ArrayList<>();
            for (Produkt produkt : bauteileverwaltung.getKategorien().get(this.kategorieListID).getProdukte()) {
                allProdukte.add(produkt.getName());
            }
            ObservableList<String> items = FXCollections.observableArrayList(allProdukte);
            produktListe.setItems(items);
            this.produktProdukteBox.setDisable(false);
            this.produkteUpdateTextFields();
        } else {
            this.produkteDisableInputs(true);
            this.produktProdukteBox.setDisable(true);
        }
    }

    public void produkteKategorieChangedAction() {
        this.kategorieListID = this.kategorienListe.getFocusModel().getFocusedIndex();
        this.produkteDisableInputs(true);
        if (this.kategorieListID == -1) {
            this.produktListe.setDisable(true);
        } else {
            this.populateProdukteList();
        }
    }

    public void produkteUpdateTextFields() {
        int listId = this.produktListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            this.produkteDisableInputs(true);
        } else {
            this.produkteDisableInputs(false);
            Produkt produkt = this.bauteileverwaltung.getKategorien().get(this.kategorieListID).getProdukte().get(listId);

            this.produktNameLabel.setText(produkt.getName());
            this.produktLink.setText(produkt.getLink());
            this.produktEinzelpreisLabel.setText(String.valueOf(produkt.getEinzelpreis()));
            this.produktMengeLagerndSpinnerValueFactory.setValue(produkt.getMenge_lagernd());
            this.produktMengeBestelltLabel.setText(String.valueOf(produkt.getMenge_bestellt()));
            this.produktMengeGeplantLabel.setText(String.valueOf(produkt.getMenge_geplant()));
            this.produktLagerortLabel.setText(produkt.getLagerort());
        }
    }

    private void produkteDisableInputs(boolean disabled) {
        this.produktGrid.setDisable(disabled);
    }

    public void produktChangeMengeLagernd() {
        Produkt produktObj = this.bauteileverwaltung.getKategorien().get(this.kategorieListID).getProdukte().get(this.produktListe.getFocusModel().getFocusedIndex());
        int newMenge = this.produktMengeLagerndSpinner.getValue();
        String name = angemeldetePerson.getName();
        String produkt = produktObj.getName();
        String kategorien = this.bauteileverwaltung.getKategorien().get(this.kategorieListID).getName();
        String einzelpreis = String.valueOf(produktObj.getEinzelpreis()) + " \u20AC";
        String menge = String.valueOf(produktObj.getMenge_lagernd() - newMenge);
        if (ConfirmBox.display("Warnung", "Sind Sie sich sicher, dass Sie den Lagerbestand des ausgewählen Produktes auf "
                + newMenge + " ändern möchten?\nDabei erstellen Sie eine Bestellung mit der Differenz zurm vorherigen Lagerbestand.")) {
            produktObj.setMenge_lagernd(newMenge);
            this.bauteileverwaltung.addBestellung(name, produkt, kategorien, einzelpreis, menge);
            showOk();
        }
    }

    public void produktLinkAction() {
        try {
            URI uri = new URI(this.produktLink.getText());
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            showError(new ElabException("Probleme beim Öffnen des Links"));
            e.printStackTrace();
        }
    }

    public void exitBauteileverwaltungBauteile() {
        try {
            this.initBauteileverwaltungVerwaltung();
            this.initBauteileverwaltungBestellungen();
        } catch (NullPointerException e) {
            System.out.print("");
        }
    }

    /**
     * Bauteileverwaltung Verwaltung
     */

    private void initBauteileverwaltungVerwaltung() {
        this.produktverwaltungMengeLagerndSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        this.produktverwaltungMengeLagerndSpinner.setValueFactory(produktverwaltungMengeLagerndSpinnerValueFactory);
        this.produktverwaltungMengeBestelltSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        this.produktverwaltungMengeBestelltSpinner.setValueFactory(produktverwaltungMengeBestelltSpinnerValueFactory);
        this.produktverwaltungMengeGeplantSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        this.produktverwaltungMengeGeplantSpinner.setValueFactory(produktverwaltungMengeGeplantSpinnerValueFactory);
        this.populateKategorieverwaltungList();
    }

    private void populateKategorieverwaltungList() {
        ArrayList<String> allKategorien = new ArrayList<>();
        this.kategorieverwaltungListID = -1;
        for (Kategorie kategorie : this.bauteileverwaltung.getKategorien()) {
            allKategorien.add(kategorie.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allKategorien);
        kategorienverwaltungListe.setItems(items);
        this.populateProduktverwaltungList();
    }

    private void populateProduktverwaltungList() {
        ArrayList<String> allProdukte = new ArrayList<>();
        if (this.kategorieverwaltungListID != -1) {
            this.produktverwaltungProdukteBox.setDisable(false);
            for (Produkt produkt : this.bauteileverwaltung.getKategorien().get(this.kategorieverwaltungListID).getProdukte()) {
                allProdukte.add(produkt.getName());
            }
            ObservableList<String> items = FXCollections.observableArrayList(allProdukte);
            produktverwaltungListe.setItems(items);
        } else {
            this.produktverwaltungDisableInputs(true);
            this.produktverwaltungProdukteBox.setDisable(true);
        }
    }

    public void addProduktAction() {
        if (this.kategorieverwaltungListID != -1) {
            this.neuesProduktModus = true;
            this.produktverwaltungDisableInputs(false);
            this.produktverwaltungKategorienBox.setDisable(true);
            this.produktverwaltungProdukteBox.setDisable(true);
            this.produktverwaltungNameField.setText("");
            this.produktverwaltungLinkField.setText("");
            this.produktverwaltungEinzelpreisField.setText("");
            this.produktverwaltungMengeLagerndSpinnerValueFactory.setValue(0);
            this.produktverwaltungMengeBestelltSpinnerValueFactory.setValue(0);
            this.produktverwaltungMengeGeplantSpinnerValueFactory.setValue(0);
            this.produktverwaltungLagerortField.setText("");
            this.produktverwaltungRemoveBtn.setText("Abbrechen");
        } else {
            showError(new ElabException("Keine Kategorie für das Produkt ausgewählt!"));
        }
    }

    private void setNeuesProduktModusDisable() {
        this.neuesProduktModus = false;
        this.produktverwaltungDisableInputs(true);
        this.produktverwaltungKategorienBox.setDisable(false);
        this.produktverwaltungProdukteBox.setDisable(false);
        this.produktverwaltungNameField.setText("");
        this.produktverwaltungLinkField.setText("");
        this.produktverwaltungEinzelpreisField.setText("");
        this.produktverwaltungMengeLagerndSpinnerValueFactory.setValue(0);
        this.produktverwaltungMengeBestelltSpinnerValueFactory.setValue(0);
        this.produktverwaltungMengeGeplantSpinnerValueFactory.setValue(0);
        this.produktverwaltungLagerortField.setText("");
        this.produktverwaltungRemoveBtn.setText("Löschen");
    }

    public void removeProduktAction() {
        if (this.neuesProduktModus) {
            this.setNeuesProduktModusDisable();
        } else {
            int listId = this.produktverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Kein Produkt zum löschen ausgewählt!"));
                return;
            }
            Produkt produktToRemove = this.bauteileverwaltung.getKategorien().get(this.kategorieverwaltungListID).getProdukte().get(listId);
            try {
                this.bauteileverwaltung.getKategorien().get(this.kategorieverwaltungListID).removeProdukt(produktToRemove.getId());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.populateProduktverwaltungList();
        }
    }

    public void saveProduktAction() {
        if (this.produktverwaltungNameField.getText().equals("")) {
            showError(new ElabException("Produktname darf nicht leer sein!"));
            return;
        }
        if (this.produktverwaltungEinzelpreisField.getText().equals("")) {
            showError(new ElabException("Einzelpreis des Produkts darf nicht leer sein!"));
            return;
        }
        if (this.neuesProduktModus) {
            try {
                this.bauteileverwaltung.getKategorien().get(this.kategorieverwaltungListID).addProdukt(this.produktverwaltungNameField.getText(),
                        this.produktverwaltungLinkField.getText(), this.produktverwaltungEinzelpreisField.getText(),
                        this.produktverwaltungMengeLagerndSpinner.getValue(), this.produktverwaltungMengeBestelltSpinnerValueFactory.getValue(),
                        this.produktverwaltungMengeGeplantSpinnerValueFactory.getValue(), this.produktverwaltungLagerortField.getText());
            } catch (ElabException e) {
                showError(e);
                return;
            }
            this.setNeuesProduktModusDisable();
        } else {
            int listId = this.produktverwaltungListe.getFocusModel().getFocusedIndex();
            if (listId == -1) {
                showError(new ElabException("Kein Produkt zum Bearbeiten ausgewählt!"));
                return;
            }
            Produkt produkt = this.bauteileverwaltung.getKategorien().get(this.kategorieverwaltungListID).getProdukte().get(listId);
            try {
                this.bauteileverwaltung.getKategorien().get(this.kategorieverwaltungListID).updateProdukt(produkt.getId(),
                        this.produktverwaltungNameField.getText(), this.produktverwaltungLinkField.getText(),
                        this.produktverwaltungEinzelpreisField.getText(), this.produktverwaltungMengeLagerndSpinner.getValue(),
                        this.produktverwaltungMengeBestelltSpinnerValueFactory.getValue(),
                        this.produktverwaltungMengeGeplantSpinnerValueFactory.getValue(), this.produktverwaltungLagerortField.getText());
            } catch (ElabException e) {
                showError(e);
                return;
            }
        }
        showOk();
        this.populateProduktverwaltungList();
    }

    private void produktverwaltungDisableInputs(boolean disabled) {
        this.produktverwaltungGrid.setDisable(disabled);
    }

    public void produktverwaltungUpdateTextFields() {
        int listId = this.produktverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            this.produktverwaltungDisableInputs(true);
        } else {
            this.produktverwaltungDisableInputs(false);
            Produkt produkt = this.bauteileverwaltung.getKategorien().get(this.kategorieverwaltungListID).getProdukte().get(listId);

            this.produktverwaltungNameField.setText(produkt.getName());
            this.produktverwaltungLinkField.setText(produkt.getLink());
            this.produktverwaltungEinzelpreisField.setText(String.valueOf(produkt.getEinzelpreis()));
            this.produktverwaltungMengeLagerndSpinnerValueFactory.setValue(produkt.getMenge_lagernd());
            this.produktverwaltungMengeBestelltSpinnerValueFactory.setValue(produkt.getMenge_bestellt());
            this.produktverwaltungMengeGeplantSpinnerValueFactory.setValue(produkt.getMenge_geplant());
            this.produktverwaltungLagerortField.setText(produkt.getLagerort());
        }
    }

    public void produktverwaltungKategorieChangedAction() {
        this.kategorieverwaltungListID = this.kategorienverwaltungListe.getFocusModel().getFocusedIndex();
        this.produkteDisableInputs(true);
        if (this.kategorieverwaltungListID == -1) {
            this.produktProdukteBox.setDisable(true);
        } else {
            this.populateProduktverwaltungList();
        }
        this.produktverwaltungUpdateTextFields();
    }

    public void removeKategorieAction() {
        int listId = this.kategorienverwaltungListe.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError(new ElabException("Keine Kategorie zum Löschen ausgewählt!"));
        } else {
            try {
                Kategorie kategorie = this.bauteileverwaltung.getKategorien().get(listId);
                this.bauteileverwaltung.removeKategorie(kategorie.getId());
            } catch (ElabException e) {
                showError(e);
                return;
            }
        }
        this.populateKategorieverwaltungList();
    }

    public void addKategorieAction() {
        if (this.kategorieNameField.getText().equals("")) {
            showError(new ElabException("Name der Kategorie darf nicht leer sein!"));
            return;
        }
        try {
            this.bauteileverwaltung.addKategorie(this.kategorieNameField.getText());
        } catch (ElabException e) {
            showError(e);
            return;
        }
        showOk();
        populateKategorieverwaltungList();
    }

    public void exitBauteileverwaltungVerwaltung() {
        try {
            this.initBauteileverwaltungBauteile();
        } catch (NullPointerException e) {
            System.out.print("");
        }
    }

    /**
     * Bauteileverwaltung Bestellungen
     */

    public void initBauteileverwaltungBestellungen() {
        this.bestellungenNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.bestellungenProduktCol.setCellValueFactory(new PropertyValueFactory<>("produkt"));
        this.bestellungenKategorieCol.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        this.bestellungenEinzelpreisCol.setCellValueFactory(new PropertyValueFactory<>("einzelpreis"));
        this.bestellungenMengeCol.setCellValueFactory(new PropertyValueFactory<>("menge"));
        this.populateBestellungenTable();
    }

    private void populateBestellungenTable() {
        ObservableList<Bestellung> bestellungen = FXCollections.observableArrayList();
        bestellungen.addAll(this.bauteileverwaltung.getBestellungen());
        this.bestellungenTable.setItems(bestellungen);
    }

    public void bestellungenAbwickelnAction() {
        int listId = this.bestellungenTable.getFocusModel().getFocusedIndex();
        if (listId == -1) {
            showError(new ElabException("Keine Bestellung zum Abwickeln ausgewählt!"));
            return;
        }
        Bestellung bestellungToRemove = this.bauteileverwaltung.getBestellungen().get(listId);
        try {
            this.bauteileverwaltung.removeBestellung(bestellungToRemove.getId());
        } catch (ElabException e) {
            showError(e);
            return;
        }
        this.populateBestellungenTable();
    }
}
