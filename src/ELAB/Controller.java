package ELAB;

import javafx.collections.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Personenverwaltung personenverwaltung;
    private Fertigungsverwaltung fertigungsverwaltung;
    private Finanzverwaltung finanzverwaltung;
    private Bauteileverwaltung bauteileverwaltung;

    public ListView<String> personenverwaltungListe;
    public TextField personenverwaltungNameField;
    public TextField personenverwaltungAdresseField;
    public TextField personenverwaltungTelefonField;
    public TextField personenverwaltungEmailField;
    public TextField personenverwaltungPasswortField;
    public Spinner<String> personenverwaltungTypSpinner;
    private SpinnerValueFactory<String> personenverwaltungTypeValueFactory;
    public Label personenverwaltungTimestampLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personenverwaltung = new Personenverwaltung();
        this.fertigungsverwaltung = new Fertigungsverwaltung();
        this.finanzverwaltung = new Finanzverwaltung();
        this.bauteileverwaltung = new Bauteileverwaltung();

        // init Commands
        this.populatePersonenverwaltungList();
        ObservableList<String> types = FXCollections.observableArrayList("Mitglied", "Kunde", "Lehrstuhl bezogene Person");
        SpinnerValueFactory<String> personenverwaltungTypeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(types);
        this.personenverwaltungTypSpinner.setValueFactory(personenverwaltungTypeValueFactory);
    }

    private void populatePersonenverwaltungList() {
        ArrayList<String> allNames = new ArrayList<>();
        for (Person person : personenverwaltung.getPersonen()) {
            allNames.add(person.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(allNames);
        personenverwaltungListe.setItems(items);
    }

    public void removePersonAction() {
        int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
        Person personToRemove = personenverwaltung.getPersonen().get(listId);
        this.personenverwaltung.removePerson(personToRemove.getId());
    }

    public void savePersonAction() {

    }

    public void personenverwaltungUpdateTextFields() {
        int listId = this.personenverwaltungListe.getFocusModel().getFocusedIndex();
        Person person = personenverwaltung.getPersonen().get(listId);
        this.personenverwaltungNameField.setText(person.getName());
        this.personenverwaltungAdresseField.setText(person.getAdresse());
        this.personenverwaltungTelefonField.setText(person.getTelefonnr());
        this.personenverwaltungEmailField.setText(person.getEmail());
        this.personenverwaltungTimestampLabel.setText(person.getZeitstempelString());
        //this.personenverwaltungTypeValueFactory.setValue(person.getType());
    }
}
