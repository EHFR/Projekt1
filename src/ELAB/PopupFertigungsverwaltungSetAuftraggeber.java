package ELAB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PopupFertigungsverwaltungSetAuftraggeber {

    //Create variable
    static ArrayList<Integer> selected;

    public static ArrayList<Integer> display(String title, ArrayList<Integer> preselected, ArrayList<Person> personen) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Markiere alle Auftragbearbeiter:");

        //List
        ListView<String> list = new ListView<>();
        ArrayList<String> mitgliederNamen = new ArrayList<>();
        ArrayList<Person> mitglieder = new ArrayList<>();
        for (Person person : personen) {
            if (person.getType().equals("Mitglied")) {
                mitglieder.add(person);
            }
        }
        for (Person mitglied : mitglieder) {
            mitgliederNamen.add(mitglied.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(mitgliederNamen);
        list.setItems(items);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Integer id : preselected) {
            list.getSelectionModel().select(id);
        }
        //Create two buttons
        Button yesButton = new Button("Speichern");
        Button noButton = new Button("Abbrechen");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            ObservableList<Integer> listIDs = list.getSelectionModel().getSelectedIndices();
            for (Integer listID : listIDs) {
                selected.add(mitglieder.get(listID).getId());
            }
            window.close();
        });
        noButton.setOnAction(e -> {
            selected = preselected;
            window.close();
        });

        VBox vLayout = new VBox(10);
        vLayout.setPadding(new Insets(5));
        HBox hLayout = new HBox(10);

        //Add buttons
        hLayout.getChildren().addAll(yesButton, noButton);
        hLayout.setAlignment(Pos.CENTER);
        vLayout.getChildren().addAll(label, list, hLayout);
        vLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vLayout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return selected;
    }

}