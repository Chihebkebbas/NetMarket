package models;

import java.util.ArrayList;

import java.util.HashMap;

import data.IData;
import data.fieldType;

public class Fournisseur implements IData {

	private int id;
	private String nom;
	private int siret;
	private String adresse;
	private String mail;
	private int idcontact;
	
	
	private String values;
    private HashMap<String, fieldType> map;
    private ArrayList<String> tab;
	
	
    public Fournisseur (int id, String nom, int siret, String adresse, String mail, int idcontact) {
    	this.id = id;
    	this.nom = nom;
    	this.siret = siret;
    	this.adresse = adresse;
    	this.mail = mail;
    	this.idcontact = idcontact;
    	
    	this.getStruct();
    }
	

	@Override
	public void getStruct() {
		this.map = new HashMap<>();
		this.tab = new ArrayList<>();
		
		this.map.put("id", fieldType.INT4);
		this.tab.add("id, ");
		this.map.put("nom", fieldType.VARCHAR);
		this.tab.add("nom, ");
		this.map.put("siret", fieldType.INT4);
		this.tab.add("siret, ");
		this.map.put("adresse", fieldType.VARCHAR);
		this.tab.add("adresse, ");
		this.map.put("mail", fieldType.VARCHAR);
		this.tab.add("mail, ");
		this.map.put("idcontact", fieldType.INT4);
		this.tab.add("idcontact");
		
		this.values = this.id + ", '" + this.nom + "', " + this.siret + ", '" + this.adresse + "', '" + this.mail + "', " + this.idcontact;
	}

	@Override
	public String getValues() {
		return this.values;
	}

	@Override
	public HashMap<String, fieldType> getMap() {
		return this.map;
	}

	@Override
	public boolean check(HashMap<String, fieldType> tableStruct) {
		return map.equals(tableStruct);
	}

	@Override
	public ArrayList<String> getTableau() {
		return this.tab;
	}
	
	
	
	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + ", siret=" + siret + ", adresse=" + adresse + ", mail=" + mail
				+ ", idcontact=" + idcontact + "]";
	}


	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public int getSiret() { return siret; }
	public void setSiret(int siret) { this.siret = siret; }
	public String getAdresse() { return adresse; }
	public void setAdresse(String adresse) { this.adresse = adresse; }
	public String getMail() { return mail; }
	public void setMail(String mail) { this.mail = mail; }
	public int getidcontact() { return idcontact; }
	public void setidcontact(int idcontact) { this.idcontact = idcontact; }

}
