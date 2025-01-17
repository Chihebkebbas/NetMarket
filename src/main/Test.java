package main;



import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.sql.SQLException;
import models.*;
import connexion.*;
import gestion.*;

public class Test {
    public static void main(String[] args) {
        Connexion connexion = new Connexion();
        Gestion gestion = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Connexion à la base de données
            connexion.connect();
            gestion = new Gestion(connexion);
            System.out.println("Connexion réussie à la base de données.");

            boolean exit = false;

            while (!exit) {
                // Affichage du menu
                System.out.println("==================================================================================================================");
                System.out.println("                             💼📊 Bienvenue dans NET MARKET 📊💼                                         ");
                System.out.println("==================================================================================================================");
                System.out.println("1. 🛠️  CREATE    - Créer une table");
                System.out.println("2. ✏️  INSERT    - Insérer une entité");
                System.out.println("3. 📋 DISPLAY   - Afficher le contenu d'une table");
                System.out.println("4. ❌ REMOVE    - Supprimer une entité");
                System.out.println("5. 🧩 STRUCT    - Afficher la structure d'une table");
                System.out.println("6. 🗑️  DROP      - Supprimer une table");
                System.out.println("7. 🚪 QUITTER   - Quitter l'application");
                System.out.print("\n👉 Choisissez une option : ");


                int choix = Integer.parseInt(reader.readLine().trim());

                switch (choix) {
                    case 1:
                        // CREATE - Créer une table
                        System.out.print("Nom de la table à créer : ");
                        String tableToCreate = reader.readLine() + "_final";
                        String createQuery = getCreateQuery(tableToCreate);
                        if (createQuery != null) {
                            gestion.execute(createQuery);
                            System.out.println("Table " + tableToCreate + " créée avec succès !");
                        } else {
                            System.out.println("Table inconnue ou non supportée pour la création automatique.");
                        }
                        break;

                    case 2:
                        // INSERT - Insérer une entité
                        System.out.print("Nom de la table pour insérer une entité : ");
                        String tableToInsert = reader.readLine() + "_final";
                        insertEntity(gestion, tableToInsert, reader);
                        break;

                    case 3:
                        // DISPLAY - Afficher le contenu d'une table
                        System.out.print("Nom de la table à afficher : ");
                        String tableToDisplay = reader.readLine() + "_final";
                        gestion.displayTable(tableToDisplay);
                        break;

                    case 4:
                        // REMOVE - Supprimer une entité
                        System.out.print("Nom de la table : ");
                        String tableToRemove = reader.readLine() + "_final";
                        System.out.print("ID de l'entité à supprimer : ");
                        int idToRemove = Integer.parseInt(reader.readLine().trim());
                        String removeQuery = "DELETE FROM " + tableToRemove + " WHERE id = " + idToRemove + ";";
                        gestion.execute(removeQuery);
                        System.out.println("Entité supprimée avec succès !");
                        break;

                    case 5:
                        // STRUCT - Afficher la structure d'une table
                        System.out.print("Nom de la table : ");
                        String tableToStruct = reader.readLine() + "_final";
                        gestion.structTable(tableToStruct, true);
                        break;

                    case 6:
                        // DROP - Supprimer une table
                        System.out.print("Nom de la table à supprimer : ");
                        String tableToDrop = reader.readLine() + "_final";
                        String dropQuery = "DROP TABLE IF EXISTS " + tableToDrop + " CASCADE;";
                        gestion.execute(dropQuery);
                        System.out.println("Table supprimée avec succès !");
                        break;

                    case 7:
                        // Quitter
                        exit = true;
                        System.out.println("Au revoir !");
                        break;

                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connexion.disconnect();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour retourner la requête CREATE appropriée avec suffixe "_final"
    private static String getCreateQuery(String tableName) {
        switch (tableName.toLowerCase()) {
            case "produit_final":
                return "CREATE TABLE produit_final (" +
                        "id INT4 PRIMARY KEY, " +
                        "lot INT4, " +
                        "name VARCHAR, " +
                        "descrip VARCHAR, " +
                        "cat VARCHAR, " +
                        "price FLOAT8" +
                        ");";

            case "fournisseur_final":
                return "CREATE TABLE fournisseur_final (" +
                        "id INT4 PRIMARY KEY, " +
                        "nom VARCHAR, " +
                        "siret INT4, " +
                        "adresse VARCHAR, " +
                        "mail VARCHAR, " +
                        "idcontact INT4 REFERENCES contact_final(id)" +
                        ");";

            case "achat_final":
                return "CREATE TABLE achat_final (" +
                        "id INT4 PRIMARY KEY, " +
                        "lot INT4, " +
                        "quantite INT4, " +
                        "dateachat VARCHAR, " +
                        "dateperemption VARCHAR, " +
                        "idproduit INT4 REFERENCES produit_final(id)" +
                        ");";

            case "vente_final":
                return "CREATE TABLE vente_final (" +
                        "id INT4 PRIMARY KEY, " +
                        "datevente VARCHAR, " +
                        "quantite INT4" +
                        ");";

            case "contrat_final":
                return "CREATE TABLE contrat_final (" +
                        "id INT4 PRIMARY KEY, " +
                        "datedebut VARCHAR, " +
                        "datefin VARCHAR, " +
                        "prixcontrat FLOAT8, " +
                        "quantite INT4, " +
                        "idfournisseur INT4 REFERENCES fournisseur_final(id), " +
                        "idproduit INT4 REFERENCES produit_final(id)" +
                        ");";

            case "contact_final":
                return "CREATE TABLE contact_final (" +
                        "id INT4 PRIMARY KEY, " +
                        "nom VARCHAR, " +
                        "prenom VARCHAR, " +
                        "fonction VARCHAR, " +
                        "mail VARCHAR, " +
                        "telephone INT4" +
                        ");";

            case "achat_vente_final":
                return "CREATE TABLE achat_vente_final (" +
                        "idachat INT4 REFERENCES achat_final(id), " +
                        "idvente INT4 REFERENCES vente_final(id), " +
                        "PRIMARY KEY (idachat, idvente)" +
                        ");";

            default:
                return null;
        }
    }

    // Méthode pour insérer une entité avec suffixe "_final"
    private static void insertEntity(Gestion gestion, String tableName, BufferedReader reader) throws Exception {
        switch (tableName.toLowerCase()) {
            case "produit_final":
                System.out.print("ID : ");
                int idprod = Integer.parseInt(reader.readLine().trim());
                System.out.print("Numéro de lot : ");
                int lotprod = Integer.parseInt(reader.readLine().trim());
                System.out.print("Nom : ");
                String nom = reader.readLine().trim();
                System.out.print("Description : ");
                String description = reader.readLine().trim();
                System.out.print("Catégorie : ");
                String categorie = reader.readLine().trim();
                System.out.print("Prix de vente : ");
                double prixVente = Double.parseDouble(reader.readLine().trim());

                Produit produit = new Produit(idprod , lotprod, nom, description, categorie, prixVente);
                gestion.insert(produit, "produit_final");
                System.out.println("Produit inséré avec succès !");
                break;

            case "fournisseur_final":
                System.out.print("ID : ");
                int idfour = Integer.parseInt(reader.readLine().trim());
                System.out.print("Nom : ");
                String fournisseurNom = reader.readLine().trim();
                System.out.print("SIRET : ");
                int siret = Integer.parseInt(reader.readLine().trim());
                System.out.print("Adresse : ");
                String adresse = reader.readLine().trim();
                System.out.print("Email : ");
                String mail = reader.readLine().trim();
                System.out.print("ID Contact : ");
                int idContact = Integer.parseInt(reader.readLine().trim());

                Fournisseur fournisseur = new Fournisseur(idfour, fournisseurNom, siret, adresse, mail, idContact);
                gestion.insert(fournisseur, "fournisseur_final");
                System.out.println("Fournisseur inséré avec succès !");
                break;

            case "achat_final":
                System.out.print("ID : ");
                int idachat = Integer.parseInt(reader.readLine().trim());
                System.out.print("Lot : ");
                int lot = Integer.parseInt(reader.readLine().trim());
                System.out.print("Quantité : ");
                int quantite = Integer.parseInt(reader.readLine().trim());
                System.out.print("Date d'achat (YYYY-MM-DD) : ");
                String dateAchat = reader.readLine().trim();
                System.out.print("Date de péremption (YYYY-MM-DD) : ");
                String datePeremption = reader.readLine().trim();
                System.out.print("ID Produit : ");
                int idProduit = Integer.parseInt(reader.readLine().trim());

                Achat achat = new Achat(idachat, lot, quantite, dateAchat, datePeremption, idProduit);
                gestion.insert(achat, "achat_final");
                System.out.println("Achat inséré avec succès !");
                break;

            case "vente_final":
                System.out.print("ID : ");
                int idvente = Integer.parseInt(reader.readLine().trim());
                System.out.print("Date de vente (YYYY-MM-DD) : ");
                String dateVente = reader.readLine().trim();
                System.out.print("Quantité : ");
                int quantiteVente = Integer.parseInt(reader.readLine().trim());

                Vente vente = new Vente(idvente, dateVente, quantiteVente);
                gestion.insert(vente, "vente_final");
                System.out.println("Vente insérée avec succès !");
                break;

            case "contrat_final":
                System.out.print("ID : ");
                int idcontrat = Integer.parseInt(reader.readLine().trim());
                System.out.print("Date de début (YYYY-MM-DD) : ");
                String dateDebut = reader.readLine().trim();
                System.out.print("Date de fin (YYYY-MM-DD) : ");
                String dateFin = reader.readLine().trim();
                System.out.print("Prix du contrat : ");
                double prixContrat = Double.parseDouble(reader.readLine().trim());
                System.out.print("Quantité : ");
                int quantiteContrat = Integer.parseInt(reader.readLine().trim());
                System.out.print("ID Fournisseur : ");
                int idFournisseur = Integer.parseInt(reader.readLine().trim());
                System.out.print("ID Produit : ");
                int idProduitContrat = Integer.parseInt(reader.readLine().trim());

                Contrat contrat = new Contrat(idcontrat, dateDebut, dateFin, prixContrat, quantiteContrat, idFournisseur, idProduitContrat);
                gestion.insert(contrat, "contrat_final");
                System.out.println("Contrat inséré avec succès !");
                break;


            case "achat_vente_final":
                System.out.print("ID Achat : ");
                int idAchat = Integer.parseInt(reader.readLine().trim());
                System.out.print("ID Vente : ");
                int idVente = Integer.parseInt(reader.readLine().trim());

                AchatVente achatVente = new AchatVente(idAchat, idVente);
                gestion.insert(achatVente, "achat_vente_final");
                System.out.println("Relation Achat-Vente insérée avec succès !");
                break;

            default:
                System.out.println("Table inconnue ou non supportée pour l'insertion.");
                break;

        }
    }
}
//uapv2401806
//jrNija