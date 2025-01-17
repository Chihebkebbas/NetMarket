package models;


import data.IData;

import data.fieldType;

import java.util.ArrayList;
import java.util.HashMap;

public class AchatVente implements IData {
    private int idachat; // Identifiant de l'achat
    private int idvente; // Identifiant de la vente

    private String values;
    private HashMap<String, fieldType> map;

    private ArrayList<String> tab;

    public AchatVente(int idachat, int idvente) {
        this.idachat = idachat;
        this.idvente = idvente;
        getStruct();
    }

    @Override
    public void getStruct() {
        map = new HashMap<>();
        this.tab = new ArrayList<>();
        map.put("idachat", fieldType.INT4);
        tab.add("idachat, ");
        map.put("idvente", fieldType.INT4);
        tab.add("idvente");
        values = this.idachat + ", " + this.idvente;
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
        return "AchatVente [idachat=" + idachat + ", idvente=" + idvente + "]";
    }

    // Getters et Setters
    public int getidachat() { return idachat; }
    public void setidachat(int idachat) { this.idachat = idachat; }
    public int getidvente() { return idvente; }
    public void setidvente(int idvente) { this.idvente = idvente; }

    @Override
    public ArrayList<String> getTableau() {
        return this.tab;
    }
}