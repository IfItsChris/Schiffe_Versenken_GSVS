package de.chris.schiffeversenken.mysql;

import de.chris.schiffeversenken.SchiffeVersenken;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VerlaufDriver {

    public static List<String> getVerlauf() {
        List<String> verlauf = new ArrayList<String>();

        String SQL = "SELECT * FROM verlauf ORDER BY id DESC";

        try {
            PreparedStatement preparedStatement = SchiffeVersenken.getMySQLDriver().getConnection().prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Timestamp datum = resultSet.getTimestamp("datum");
                String spieler1 = resultSet.getString("spieler1");
                String spieler2 = resultSet.getString("spieler2");
                String gewinner = resultSet.getString("gewinner");

                String s = datum.toString() + " | " + spieler1 + "vs" + spieler2 + " - Gewonnen: " + gewinner;
                verlauf.add(s);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return verlauf;

    }

    public static void insertVerlauf(String spieler1, String spieler2, String gewinner) {
        String SQL = "INSERT INTO verlauf (datum, spieler1, spieler2, gewinner) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = SchiffeVersenken.getMySQLDriver().getConnection().prepareStatement(SQL);
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(2, spieler1);
            preparedStatement.setString(3, spieler2);
            preparedStatement.setString(4, gewinner);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
