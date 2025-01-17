package gestion;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


import connexion.Connexion;
import data.*;


public class Gestion {


public Connexion conn;


	public Gestion(Connexion conn) {
		this.conn = conn;
	}

    public HashMap<String, fieldType> structTable(String table, boolean display) throws SQLException {
    	HashMap<String, fieldType> structure = new HashMap<>();

    	String query = "SELECT * FROM " + table + " LIMIT 0";
    	try (Statement s = this.conn.getConnection().createStatement();
    		 ResultSet result = s.executeQuery(query)) {

               ResultSetMetaData metaData = result.getMetaData();
               int columnCount = metaData.getColumnCount();

               System.out.println("Structure de la table " + table + ":");
               for (int i = 1; i <= columnCount; i++) {
                   String columnName = metaData.getColumnName(i);
                   String columnType = metaData.getColumnTypeName(i);

                   System.out.printf("Colonne: %s, Type: %s%n",
                           columnName, columnType);

                   structure.put(columnName, columnFieldType(columnType));

               }

               System.out.println("========================This is hashmap of base de données========================");

               structure.forEach((key, value) -> System.out.println("Clé: " + key + ", Valeur: " + value));

       } catch (SQLException e) {
           e.printStackTrace();
       }

    	if (display) {
    		String q = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = '" + table + "' ORDER BY ordinal_position;";
    		this.execute(q);
    	}


    	return structure;
    }

    private static fieldType columnFieldType(String s) {
    	try {
            switch (s.toUpperCase()) {
                case "NUMERIC":
                    return fieldType.NUMERIC;
                case "VARCHAR":
                case "CHARACTER VARYING":
                case "TEXT":
                case "DATE":
                    return fieldType.VARCHAR;
                case "FLOAT8":
                case "DOUBLE PRECISION":
                    return fieldType.FLOAT8;
                case "INT4":
                case "INTEGER":
                case "SERIAL":
                    return fieldType.INT4;
                default:
                    throw new IllegalArgumentException("Type non pris en charge : " + s);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du mappage du type PostgreSQL : " + s + " - " + e.getMessage());
            return null;
        }
    }


    public void execute(String query) throws SQLException {
        try (Statement stmt = this.conn.getConnection().createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(IData data, String table) throws SQLException {
        // Récupération de la structure de la table et de l'instance
        HashMap<String, fieldType> structTable = this.structTable(table, false);

        // Vérification que les structures correspondent
        if (!data.check(structTable)) {
            throw new IllegalArgumentException("Les structures de la table et de l'instance ne correspondent pas.");
        }

        // Construire les colonnes
        String column = "";
        for(String s : data.getTableau()) {
        	column += s;
        }

        //System.out.println("voici les columns: "+column);



        //System.out.println("values of our instance : \n" + data.getValues());
        String conflit = "";
        if (table == "produit_final") {
        	conflit = "ON CONFLICT (id) DO UPDATE SET \n"
                    + "price = " + table + ".price + EXCLUDED.price,\n"
                    + "descrip = " + table + ".descrip || ' ' || EXCLUDED.descrip;";
        }

        String query = "INSERT INTO " + table + " (" + column + ") " +
                "VALUES (" + data.getValues() + ") " + conflit;


        execute(query);

    }

    public void displayTable(String table) throws SQLException {
        String query = "SELECT * FROM " + table;
        try (Statement stmt = this.conn.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        }
    }


    public boolean tableExists(String tableName) throws SQLException {
        String query = "SELECT EXISTS (" +
                       "SELECT FROM information_schema.tables " +
                       "WHERE table_schema = 'public' AND table_name = '" + tableName.toLowerCase() + "');";
        try (Statement stmt = this.conn.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        }
        return false;
    }


}
