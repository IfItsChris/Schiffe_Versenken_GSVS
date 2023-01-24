package de.chris.schiffeversenken.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDriver {

    private String connectionString;
    private String user;
    private String pass;
    private Connection connection;

    public MySQLDriver(String connectionString, String user, String pass) {
        this.connectionString = connectionString;
        this.user = user;
        this.pass = pass;
    }

    public MySQLDriver openConnection() {
        System.out.println("Verbinde zu MySQL Datenbank...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString, this.user, this.pass);
            System.out.println("Verbindung erfolgreich!");
        } catch (Exception e) {
            System.out.println("Verbindung fehlgeschlagen!");
            throw new RuntimeException(e);
        }
        return this;
    }

    public Connection getConnection() {
        return connection;
    }

    public MySQLDriver closeConnection() {
        try {
            connection.close();
            System.out.println("Verbindung zu MySQL geschlossen!");
        } catch (SQLException e) {
            System.out.println("Schließen der Verbindung fehlgeschlagen!");
            throw new RuntimeException(e);
        }
        return this;
    }

    public MySQLDriver createTables() {
        String SQL = "CREATE TABLE IF NOT EXSTS verlauf (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "datum TIMESTAMP," +
                "spieler1 VARCHAR(25)," +
                "spieler2 VARCHAR(25)," +
                "gewinner VARCHAR(25)," +
                "PRIMARY KEY (id))";

        try {
            System.out.println("Erstelle Tabellen...");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
            System.out.println("Die Tabellen wurden erstellt, sofern nicht vorhanden!");
        } catch (SQLException e) {
            System.out.println("Es ist ein Fehler mit dem Erstellen der Tabellen aufgetreten!");
            throw new RuntimeException(e);
        }

    return this;
    }

}

