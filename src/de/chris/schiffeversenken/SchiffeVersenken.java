package de.chris.schiffeversenken;

import de.chris.schiffeversenken.collector.Helper;
import de.chris.schiffeversenken.mysql.MySQLDriver;
import de.chris.schiffeversenken.mysql.VerlaufDriver;
import de.chris.schiffeversenken.objects.Spiel;
import de.chris.schiffeversenken.objects.Spieler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SchiffeVersenken {

    private static MySQLDriver mySQLDriver;
    public static void main(String[] args) {

        mySQLDriver = new MySQLDriver("jdbc:mysql://localhost:3306/schiffeversenken", "root", "")
                .openConnection()
                .createTables();
       menu();
    }

    private static void menu() {
        System.out.println("1 -> Spiel starten");
        System.out.println("2 -> Verlauf zeigen");
        System.out.println();
        System.out.println("Bitte wähle einer der Menüpunkte aus");

        Scanner scanner = new Scanner(System.in);
        byte choise = (byte) scanner.nextInt();

        switch(choise) {
            case 1:
                Spieler spieler1 = new Spieler(), spieler2 = new Spieler();
                new Spiel(spieler1, spieler2).startSpiel();
                break;
            case 2:
                List<String> verlauf = VerlaufDriver.getVerlauf();

                if(verlauf.isEmpty()) {
                    Helper.leereKonsole();
                    System.out.println("Der Verlauf ist leer!");
                    menu();
                    return;
                }

                verlauf.forEach((v) -> {
                    System.out.println(v);
                });
                break;
        }
    menu();
    }

    public static MySQLDriver getMySQLDriver() {
        return mySQLDriver;
    }

}