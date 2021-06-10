package com.everis.mainapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Formación
 * 
 * @author everis
 *
 */
public class App {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Conexión y ejecución de consulta a BBDD (Oracle).
		stablishODBConnection();
		
		// Conexión y ejecución de consulta a BBDD (MySQL).
		//stablishMDBConnection();
	}

	/**
	 * Establece la conexión con BBDD de Oracle.
	 */
	private static void stablishODBConnection() {
		
		try {

			// Se establece el driver de conexión a BBDD.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Apertura de conexión con BBDD (URL / Usuario / Contraseña)
			final Connection dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");

			// Realización de consulta.
			final Statement sentence = dbConnection.createStatement();
			final String query = "SELECT sp.name AS playerName, st.name AS teamName, sp.first_rol AS rol1, sp.second_rol AS rol2, sp.birth_date AS birthD FROM fpd_soccer_player sp JOIN fpd_soccer_team st ON sp.id_soccer_team = st.id_soccer_team";
			final ResultSet queryResult = sentence.executeQuery(query);

			// Iteración de resultados.
			StringBuilder playerInfo = new StringBuilder();
			while (queryResult.next()) {

				playerInfo.append("Nombre: ");
				playerInfo.append(queryResult.getString("playerName"));

				playerInfo.append(" | Equipo: ");
				playerInfo.append(queryResult.getString("teamName"));

				playerInfo.append(" | Demarcación: ");
				playerInfo.append(queryResult.getString("rol1"));

				playerInfo.append(" | Demarcación alternativa: ");
				playerInfo.append(queryResult.getString("rol2"));

				playerInfo.append(" | Fecha nacimiento: ");
				playerInfo.append(queryResult.getDate("birthD"));

				playerInfo.append("\n");
			}

			System.out.println(playerInfo.toString());

			// Cierre de conexión con BBDD.
			dbConnection.close();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("[ERROR] - Error en la conexión con BBDD.");
		}

	}
	
	/**
	 * Establece la conexión con BBDD de MySQL.
	 */
	private static void stablishMDBConnection() {
		
		try {

			// Se establece el driver de conexión a BBDD.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Apertura de conexión con BBDD (URL / Usuario / Contraseña)
			final Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/everis_fpdual2", "root", "rootroot");

			// Realización de consulta.
			final Statement sentence = dbConnection.createStatement();
			final String query = "SELECT sp.name AS playerName, st.name AS teamName, sp.firstRol AS rol1, sp.secondRol AS rol2, sp.birthDate AS birthD FROM fpdsoccerplayers sp JOIN fpdsoccerteams st ON sp.idSoccerTeam = st.idSoccerTeam";
			final ResultSet queryResult = sentence.executeQuery(query);

			// Iteración de resultados.
			StringBuilder playerInfo = new StringBuilder();
			while (queryResult.next()) {

				playerInfo.append("Nombre: ");
				playerInfo.append(queryResult.getString("playerName"));

				playerInfo.append(" | Equipo: ");
				playerInfo.append(queryResult.getString("teamName"));

				playerInfo.append(" | Demarcación: ");
				playerInfo.append(queryResult.getString("rol1"));

				playerInfo.append(" | Demarcación alternativa: ");
				playerInfo.append(queryResult.getString("rol2"));

				playerInfo.append("\n");
			}

			System.out.println(playerInfo.toString());

			// Cierre de conexión con BBDD.
			dbConnection.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
