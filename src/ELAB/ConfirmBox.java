package ELAB;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    //Create variable
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        label.setWrapText(true);

        //Create two buttons
        Button yesButton = new Button("Ja");
        Button noButton = new Button("Abbrechen");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox vLayout = new VBox(10);
        vLayout.setPadding(new Insets(5));
        HBox hLayout = new HBox(10);

        //Add buttons
        hLayout.getChildren().addAll(yesButton, noButton);
        hLayout.setAlignment(Pos.CENTER);
        vLayout.getChildren().addAll(label, hLayout);
        vLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vLayout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }

}