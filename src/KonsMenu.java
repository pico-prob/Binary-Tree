
package binbaum;
import static Prog1Tools.IOTools.*;

/**
 * Programmierung 3: Belegarbeit 1
 * Magnus Gomes-Fonfara (s0523478), Mathias Münscher (s0531968)
 * 19.05.2011 (Berlin)
 * Java 1.6.0_24; Java HotSpot(TM) 64-Bit Server VM 19.1-b02-334
 */


// Die Klasse KonsMenu steuert den Menu-Dialog mit dem User an der Konsole

public class KonsMenu {
    private String [] mPunkte;
    private int mSize=0;

    // Konstruktor: Legt Hilfsvariablen und String-Array für Menüpunkte an
    KonsMenu (String [] liste) {
        int auswahl=0;
        mSize=liste.length-1; // Anzahl der Menüeinträg (beginnend mit 0!)
        mPunkte=new String[mSize+1];
        for (int i=0;i<=mSize;i++) mPunkte [i]= liste [i];
    }

    // Gibt das Menü auf der Konsole aus
    private void writeMenu () {
        for (int i=0;i<=mSize;i++) System.out.println(" ("+i+".) "+ mPunkte[i]);
    }

    // Schreibt x Leerzeilen (Abstandshalter)
    private void emptySpace (int zeilen) {
        for (int i=0;i<=zeilen;i++) System.out.println();
    }


    // Schreibt das Menü und bittet um Auswahl durch den User
    // Kontolliert die Eingabe und gibt sie zurück
    public int userBefehl () {
        int input=0;
        boolean validerBefehl =false;
        while (!validerBefehl) {
            emptySpace (3);
            writeMenu ();
            input=readInteger("\n Ihre Wahl> ");
            if ((input<0)||(input>mSize)) {
                System.out.println("\n *** Kein gültiger Befehl ***\n");
                }
                else validerBefehl=true;
        }
        return input;
    }

}
