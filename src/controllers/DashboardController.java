package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class DashboardController {

    @FXML
    private TableView<?> topSalesTable; // Table des meilleures ventes

    @FXML
    public void initialize() {
        // Méthode appelée automatiquement après le chargement du fichier FXML
        initializeComponents();
    }

    /**
     * Initialise les composants de l'interface utilisateur.
     */
    private void initializeComponents() {
        System.out.println("Initialisation des composants...");
        loadTopSalesData();
    }

    /**
     * Charge les données dans la table des meilleures ventes.
     */
    private void loadTopSalesData() {
        // Exemple de données fictives pour le tableau
        System.out.println("Chargement des données pour les meilleures ventes...");

        // Vous pouvez ajouter ici le code pour remplir la table avec des données
        // Exemple : topSalesTable.setItems(observableList);
    }
}