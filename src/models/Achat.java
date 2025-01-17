package models;


import data.IData;

import data.fieldType;

import java.util.ArrayList;
import java.util.HashMap;

public class Achat implements IData {
    private int id; // Identifiant de l'achat
    private int lot; // Lot de l'achat
    private int quantite; // Quantité
    private String dateachat; // Date d'achat (format String pour simplification)
    private String dateperemption; // Date de péremption (format String pour simplification)
    private int idproduit; // Identifiant du produit

    private String values;
    private HashMap<String, fieldType> map;

    private ArrayList<String> tab;

    public Achat(int id, int lot, int quantite, String dateachat, String dateperemption, int idproduit) {
        this.id = id;
        this.lot = lot;
        this.quantite = quantite;
        this.dateachat = dateachat;
        this.dateperemption = dateperemption;
        this.idproduit = idproduit;
        getStruct();
    }

    @Override
    public void getStruct() {
        map = new HashMap<>();
        this.tab = new ArrayList<>();
        map.put("id", fieldType.INT4);
        tab.add("id, ");
        map.put("lot", fieldType.INT4);
        tab.add("lot, ");
        map.put("quantite", fieldType.INT4);
        tab.add("quantite, ");
        map.put("dateachat", fieldType.VARCHAR); // Format date
        tab.add("dateachat, ");
        map.put("dateperemption", fieldType.VARCHAR); // Format date
        tab.add("dateperemption, ");
        map.put("idproduit", fieldType.INT4);
        tab.add("idproduit");
        values = this.id + ", " + this.lot + ", " + this.quantite + ", '" + this.dateachat + "', '" + this.dateperemption + "', " + this.idproduit;
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
        return "Achat [id=" + id + ", lot=" + lot + ", quantite=" + quantite + ", dateachat=" + dateachat 
                + ", dateperemption=" + dateperemption + ", idproduit=" + idproduit + "]";
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getLot() { return lot; }
    public void setLot(int lot) { this.lot = lot; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public String getdateachat() { return dateachat; }
    public void setdateachat(String dateachat) { this.dateachat = dateachat; }
    public String getdateperemption() { return dateperemption; }
    public void setdateperemption(String dateperemption) { this.dateperemption = dateperemption; }
    public int getidproduit() { return idproduit; }
    public void setidproduit(int idproduit) { this.idproduit = idproduit; }

    @Override
    public ArrayList<String> getTableau() {
        return this.tab;
    }
}