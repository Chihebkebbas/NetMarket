package models;


import data.IData;
import data.fieldType;

import java.util.ArrayList;
import java.util.HashMap;

public class Contrat implements IData {
    private int id; // Identifiant du contrat
    private String dateDebut; // Date de début
    private String dateFin; // Date de fin
    private double prixContrat; // Prix du contrat
    private int quantite; // Quantité
    private int idfournisseur; // Identifiant du fournisseur
    private int idproduit; // Identifiant du produit

    private String values;
    private HashMap<String, fieldType> map;

    private ArrayList<String> tab;

    public Contrat(int id, String dateDebut, String dateFin, double prixContrat, int quantite, int idfournisseur, int idproduit) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixContrat = prixContrat;
        this.quantite = quantite;
        this.idfournisseur = idfournisseur;
        this.idproduit = idproduit;
        getStruct();
    }

    @Override
    public void getStruct() {
        map = new HashMap<>();
        this.tab = new ArrayList<>();
        map.put("id", fieldType.INT4);
        tab.add("id, ");
        map.put("dateDebut", fieldType.VARCHAR); // Format date
        tab.add("dateDebut, ");
        map.put("dateFin", fieldType.VARCHAR); // Format date
        tab.add("dateFin, ");
        map.put("prixContrat", fieldType.FLOAT8); // Format double
        tab.add("prixContrat, ");
        map.put("quantite", fieldType.INT4);
        tab.add("quantite, ");
        map.put("idfournisseur", fieldType.INT4);
        tab.add("idfournisseur, ");
        map.put("idproduit", fieldType.INT4);
        tab.add("idproduit");
        values = this.id + ", '" + this.dateDebut + "', '" + this.dateFin + "', " + this.prixContrat + ", " + this.quantite + ", " + this.idfournisseur + ", " + this.idproduit;
    }

    @Override
    public String getValues() {
        return values;
    }

    @Override
    public HashMap<String, fieldType> getMap() {
        return map;
    }

    @Override
    public boolean check(HashMap<String, fieldType> tableStruct) {
        return map.equals(tableStruct);
    }

    @Override
    public String toString() {
        return "Contrat [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin 
                + ", prixContrat=" + prixContrat + ", quantite=" + quantite 
                + ", idfournisseur=" + idfournisseur + ", idproduit=" + idproduit + "]";
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }
    public double getPrixContrat() { return prixContrat; }
    public void setPrixContrat(double prixContrat) { this.prixContrat = prixContrat; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public int getidfournisseur() { return idfournisseur; }
    public void setidfournisseur(int idfournisseur) { this.idfournisseur = idfournisseur; }
    public int getidproduit() { return idproduit; }
    public void setidproduit(int idproduit) { this.idproduit = idproduit; }

    @Override
    public ArrayList<String> getTableau() {
        return this.tab;
    }
}