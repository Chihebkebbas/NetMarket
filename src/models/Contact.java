package models;


import data.IData;

import data.fieldType;

import java.util.ArrayList;
import java.util.HashMap;



public class Contact implements IData {
    private int  id;
    private String nom;
    private String prenom;
    private String fonction;
    private String mail;
    private int telephone;

    private String values;
    private HashMap<String, fieldType> map;

    private ArrayList<String> tab;

    public Contact(int id, String nom, String prenom, String fonction, String mail, int telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.mail = mail;
        this.telephone = telephone;
        getStruct();
    }

    @Override
    public void getStruct() {
        map = new HashMap<>();
        this.tab = new ArrayList<>();
        map.put("id", fieldType.INT4);
        tab.add("id, ");
        map.put("nom", fieldType.VARCHAR);
        tab.add("nom, ");
        map.put("prenom", fieldType.VARCHAR);
        tab.add("prenom, ");
        map.put("fonction", fieldType.VARCHAR);
        tab.add("fonction, ");
        map.put("mail", fieldType.VARCHAR);
        tab.add("mail, ");
        map.put("telephone", fieldType.INT4);
        tab.add("telephone");
        values = this.id + ", '" + this.nom + "', '" + this.prenom + "', '" + this.fonction + "', '" + this.mail + "', " + this.telephone;
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
        return "Contact [id_contact=" + id + ", nom=" + nom + ", prenom=" + prenom 
                + ", fonction=" + fonction + ", mail=" + mail + ", telephone=" + telephone + "]";
    }

    // Getters et Setters
    public int  getId() { return id; }
    public void setId(int  id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getFonction() { return fonction; }
    public void setFonction(String fonction) { this.fonction = fonction; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public int  getTelephone() { return telephone; }
    public void setTelephone(int telephone) { this.telephone = telephone; }

    @Override
    public ArrayList<String> getTableau() {
        return this.tab;
    }
}