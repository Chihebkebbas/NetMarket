package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NetMarketApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Vérifiez le fichier FXML
        if (getClass().getResource("/views/MainView.fxml") == null) {
            throw new RuntimeException("Fichier FXML introuvable : /views/MainView.fxml");
        }

        // Charge le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        BorderPane root = loader.load(); // Assurez-vous que MainView.fxml utilise BorderPane

        // Vérifiez le fichier CSS
        if (getClass().getResource("/styles/styles.css") == null) {
            System.err.println("Fichier CSS introuvable : /styles/styles.css");
        } else {
            root.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        }

        // Configure la scène
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NetMarket - Interface Graphique");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Lance l'application JavaFX
    }
}