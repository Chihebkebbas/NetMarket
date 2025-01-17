package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane centerPane; // Zone centrale où les vues seront affichées

    // Méthode utilitaire pour charger une vue
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();
            centerPane.setCenter(view); // Charge la vue dans la zone centrale
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de la vue : " + fxmlPath);
        }
    }

    @FXML
    private void onDashboardClicked() {
        loadView("/views/Dashboard.fxml");
    }

    @FXML
    private void onFournisseursClicked() {
        loadView("/views/Fournisseurs.fxml");
    }

    @FXML
    private void onContratsClicked() {
        loadView("/views/Contrats.fxml");
    }

    @FXML
    private void onAchatsClicked() {
        loadView("/views/Achats.fxml");
    }

    @FXML
    private void onStocksClicked() {
        loadView("/views/Stocks.fxml");
    }

    @FXML
    private void onVentesClicked() {
        loadView("/views/Ventes.fxml");
    }

    @FXML
    private void onCommandesClicked() {
        loadView("/views/Commandes.fxml");
    }
}