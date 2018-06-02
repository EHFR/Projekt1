package ELAB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PopupRechnungChangeTopf {

    //Create variable
    static int newTopfID;

    public static int display(String title, ArrayList<Topf> toepfe) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Wähle neuen Topf:");

        //List
        ListView<String> list = new ListView<>();
        ArrayList<String> toepfeName = new ArrayList<>();
        for (Topf topf : toepfe) {
            toepfeName.add(topf.getName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(toepfeName);
        list.setItems(items);

        //Create two buttons
        Button yesButton = new Button("Speichern");
        Button noButton = new Button("Abbrechen");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            newTopfID = toepfe.get(list.getFocusModel().getFocusedIndex()).getId();
            window.close();
        });
        noButton.setOnAction(e -> {
            newTopfID = -2;
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
        return newTopfID;
    }

}