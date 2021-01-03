package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import controller.ControllerIngredienti;
import model.Ingrediente;

public class BrewDayApplication {

	public static void main(String[] args) {
		Connection c = null;
	      Statement stmt = null;
	  	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS ingrediente" + 
	        		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
	        		  "nome VARCHAR(45) NOT NULL, " +
	        		  "unitaMisura VARCHAR(45) NOT NULL, " +
	        		  "disponibilita FLOAT NOT NULL)";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	
	      ControllerIngredienti control1 = new ControllerIngredienti();
	      /*control1.aggiungiIngrediente("cotoletta", 25);*/
	      Aggiunta_Ingrediente Finestra = new Aggiunta_Ingrediente(control1);
	      Finestra.open();
	      
	      ArrayList<Ingrediente> listaIngredienti = control1.getIngredienti();
	      
	      for (Ingrediente i: listaIngredienti) {
	    	  System.out.println(i);
	      }
	}
	
	

}