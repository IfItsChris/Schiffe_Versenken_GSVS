package de.chris.schiffeversenken.objects;

import de.chris.schiffeversenken.collector.Helper;
import de.chris.schiffeversenken.mysql.VerlaufDriver;

import java.util.Scanner;

public class Spiel {

    private Spieler spieler1;
    private Spieler spieler2;

    public Spiel(Spieler spieler1, Spieler spieler2) {
        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
        startSpiel();
    }

    public void startSpiel() {
        spieler1.namensEingabe().setzeSchiffe();
        spieler2.namensEingabe().setzeSchiffe();
        Helper.leereKonsole();
        starteZuege();
    }


    private boolean zug(Spieler spieler, Spieler gegner) {
        System.out.println("Bombenkarte");
        spieler.ausgebeBombenkarte();
        System.out.println("Spielfeld");
        spieler.ausgabeSpielfeld();
        System.out.println(spieler.getName() + ", du bist am Zug!");
        System.out.println("Bitte gib eine X-Koordinate ein (0-9):");
        Scanner scanner = new Scanner(System.in);
        byte x = scanner.nextByte();
        System.out.println("Bitte gib eine Y-Koordinate ein (0-9):");
        byte y = scanner.nextByte();

        Helper.leereKonsole();

        if(gegner.hatSchiffAuf(x, y)) {
            gegner.eigenesSchiffgetroffen(x, y);
            if(spieler.hatGewonnen()) {
                glueckwunschSpieler(spieler);
            } else {
                System.out.println("Super, das war ein Treffer! :)");
            }
            spieler.setzeTreffer(x, y);



            return true;
        } else {
            System.out.println("Der ging leider daneben. :(");

            return false;
        }
    }

    private void glueckwunschSpieler(Spieler spieler) {
        Helper.leereKonsole();
        System.out.println("Herzlichen Glückwunsch, " + spieler.getName() + ", du hast das Spiel gewonnen!");
        System.out.println();
        System.out.println("Bitte starte das Programm neu, um eine neue Runde zu beginnen.");
        VerlaufDriver.insertVerlauf(spieler1.getName(), spieler2.getName(), spieler.getName());
    }

    private void starteZuege() {
        while(!spieler1.hatGewonnen() && !spieler2.hatGewonnen()) {
            boolean treffer = zug(spieler1, spieler2);

            if(!treffer) {
                Spieler temp = spieler2;
                spieler2 = spieler1;
                spieler1 = temp;
            }

        }
    }

}
