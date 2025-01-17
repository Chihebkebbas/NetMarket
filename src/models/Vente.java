package models;


import data.IData;

import data.fieldType;

import java.util.ArrayList;
import java.util.HashMap;

public class Vente  implements IData {
    private int id; 
    private String datevente; 
    private int quantite; 

    private String values;
    private HashMap<String, fieldType> map;

    private ArrayList<String> tab;

    public Vente(int id, String datevente, int quantite) {
        this.id = id;
        this.datevente = datevente;
        this.quantite = quantite;
        this.getStruct();
    }

    @Override
    public void getStruct() {
        map = new HashMap<>();
        this.tab = new ArrayList<>();
        map.put("id", fieldType.INT4);
        tab.add("id, ");
        map.put("datevente", fieldType.VARCHAR); // Format date
        tab.add("datevente, ");
        map.put("quantite", fieldType.INT4);
        tab.add("quantite");
        
        values = this.id + ",  '" + this.datevente + "', " + this.quantite; 
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
    public boolean check(HashMap<String, fieldType> tableStruct) { return map.equals(tableStruct); }
    
    @Override
    public String toString() { 
    	return "Vente [id=" + id + ", datevente=" + datevente + ", quantite=" + quantite + "]";
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getdatevente() { return datevente; }
    public void setDateAchat(String datevente) { this.datevente = datevente; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    
    @Override
    public ArrayList<String> getTableau() {
        return this.tab;
    }
}