package models;


import java.util.ArrayList;

import java.util.HashMap;

import data.IData;
import data.fieldType;

public class Produit implements IData {
	private int id;
    private int lot;
    private String name;
    private String descrip;
    private String cat;
    private double price;
    
    private String values;
    private HashMap<String, fieldType> map;
    
    private ArrayList<String> tab;

    public Produit(int id, int lot, String name, String descrip, String cat, double price) {
        this.id = id;
        this.lot = lot;
        this.name = name;
        this.descrip = descrip;
        this.cat = cat;
        this.price = price;
        getStruct();
    }

    @Override
    public void getStruct() {
        map = new HashMap<>();
        this.tab = new ArrayList<String>();
        map.put("id", fieldType.INT4);
        tab.add("id, ");
        map.put("lot", fieldType.INT4);
        tab.add("lot, ");
        map.put("name", fieldType.VARCHAR);
        tab.add("name, ");
        map.put("descrip", fieldType.VARCHAR);
        tab.add("descrip, ");
        map.put("cat", fieldType.VARCHAR);
        tab.add("cat, ");
        map.put("price", fieldType.FLOAT8);
        tab.add("price");
        values = this.id + ", " + this.lot + ", '" + this.name + "', '" + this.descrip + "', '" + this.cat + "', " + this.price;
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
        return "Produit [id=" + id + ", lot=" + lot + ", name=" + name + ", descrip=" + descrip
                + ", cat=" + cat + ", price=" + price + "]";
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getlot() { return lot; }
    public void setlot(int lot) { this.lot = lot; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getdescrip() { return descrip; }
    public void setdescrip(String descrip) { this.descrip = descrip; }
    public String getcat() { return cat; }
    public void setcat(String cat) { this.cat = cat; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

	@Override
	public ArrayList<String> getTableau() {
		return this.tab;
	}
}