package de.chris.schiffeversenken.objects;

import de.chris.schiffeversenken.collector.Helper;

import java.util.Scanner;

public class Spieler {


    private String name;
    private String[][] spielFeld;
    private String[][] bombenFeld;

    public boolean gewonnen = false;

    public final int anzahlSchiffe = 5;
    public int anzahlTreffer = 0;

    private Scanner scanner;

    public Spieler() {
        spielFeld = new String[10][10];
        bombenFeld = new String[10][10];

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                spielFeld[i][j] = "0";
                bombenFeld[i][j] = "0";
            }
        }

        scanner = new Scanner(System.in);
    }

    public Spieler setName(String name) {
        this.name = name;
        return this;
    }

    public boolean hatGewonnen() {
        return gewonnen;
    }

    public Spieler setzeTreffer(byte x, byte y) {
        anzahlTreffer++;
        bombenFeld[x][y] = "T";

        if(anzahlTreffer == anzahlSchiffe) {
            gewonnen = true;
        }

        return this;
    }

    public void eigenesSchiffgetroffen(byte x, byte y) {
        spielFeld[x][y] = "X";
    }
    public void danebenGetroffen(byte x, byte y) {
        bombenFeld[x][y] = "D";
    }

    public String getName() {
        return name;
    }

    public Spieler namensEingabe() {

        System.out.println("Hallo Spieler, wie lautet dein Name?");
        this.name = scanner.next();
        return this;
    }

    public Spieler setzeSchiffe() {

        for(int i = 0; i < anzahlSchiffe; i++) {
            System.out.println("Bitte setze dein " + (i + 1) + ". Schiff:");
            System.out.println("X-Position: ");
            byte x = (byte) scanner.nextInt();
            System.out.println("Y-Position: ");
            byte y = (byte) scanner.nextInt();
            if(hatSchiffAuf(x, y)) {
                Helper.leereKonsole();
                System.out.println("Auf " + x + ":" + y + " schwimmt schon einer deiner Schiffe");
                i--;
                continue;
            }
            spielFeld[x][y] = "S";
            Helper.leereKonsole();
            ausgabeSpielfeld();

        }
        return this;
    }

    public boolean hatSchiffAuf(byte x, byte y) {
        return spielFeld[x][y] == "S";
    }

    public Spieler ausgabeSpielfeld() {
        for(int i = 0; i < 10; i++) {
            System.out.print(i + " | ");
            for(int j = 0; j < 10; j++) {
                System.out.print(spielFeld[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
        System.out.print("  | ");
        for(int i = 0; i < 10; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
        return this;
    }
    public Spieler ausgebeBombenkarte() {
        for(int i = 0; i < 10; i++) {
            System.out.print(i + " | ");
            for(int j = 0; j < 10; j++) {
                System.out.print(bombenFeld[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
        System.out.print("  | ");
        for(int i = 0; i < 10; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
        return this;
    }

}