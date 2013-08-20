/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binbaum;

import static Prog1Tools.IOTools.*;

/**
 *
 * @author maak
 */
public class BinBaumTestApp {

   private static String[] mainMenuPunkte = {
      "Baum befüllen",
      "Baum ausgeben",
      "Baum initialisieren",
      "Einzelnen Knoten löschen",
      "Baum aufwärts sortiert ausgeben",
      "Baum abwärts sortiert ausgeben",
      "Knoten suchen",
      "Baum speichern",
      "Baum laden",
      "Memory 1",
      "Programm-Ende"
   };

   public static void main(String[] args) {
      BinBaum b1 = new BinBaum();
      KonsMenu mainMenu = new KonsMenu(mainMenuPunkte);
      boolean ende = false; // Programm-Abbruch

      while (!ende) {
         switch (mainMenu.userBefehl()) {
            case 0: // Baum neu eingeben
            {
               while (true) {
                  char c;
                  System.out.println("Buchstabe/ Ziffern in Baum eingeben (#=ENDE): ");
                  c = readChar();
                  if (c == '#') {
                     break;
                  } else {
                     b1.insertKnot(c);
               b1.printTree();
                  }
               }
            }
            ;
            break;
            case 1: // Baum ausgeben
            {
               b1.printTree();
            }
            ;
            break;
            case 2: // Baum initialisieren
            {
               b1.deleteTree();
               b1.printTree();
            }
            ;
            break;
            case 3: // Knoten löschen
            {
               while (true) {
                  char c;
                  c = readChar("Welcher Buchstabe/ Ziffer soll gelöscht werden (#=ENDE): ");
                  if (c == '#') {
                     break;
                  } else {
                     b1.deleteKnot(c);
                     b1.printTree();
                     System.out.println();
                  }
               }
            }
            ;
            break;
            case 4: // Baum  sortiert ausgeben - up
            {
               b1.sortPrintUp(b1.getRoot());
            }
            ;
            break;
            case 5: // Baum  sortiert ausgeben - down
            {
               b1.sortPrintDown(b1.getRoot());
            }
            ;
            break;
            case 6: // Baum speichern
            {
               while (true) {
                  char c;
                  c = readChar("Welcher Buchstabe/ Ziffer soll gesucht werden (#=ENDE): ");
                  if (c == '#') {
                     break;
                  } else {
                     b1.searchKnot(c);
                  }
               }
            }
            ;
            break;
            case 7: // Baum speichern
            {
               b1.saveTree("sample_tree.txt");
            }
            ;
            break;
            case 8: // Baum laden
            {
               b1.readTree("sample_tree.txt");
               b1.printTree();
            }
            ;
            break;
            case 9: // Memory 1
            {
               b1.deleteTree();
               b1.insertKnot('p');
               b1.insertKnot('d');
               b1.insertKnot('h');
               b1.insertKnot('a');
               b1.insertKnot('k');
               b1.insertKnot('g');
               b1.insertKnot('z');
               b1.insertKnot('a');
               b1.printTree();
            }
            ;
            break;
            case 10: // Abbruch-Befehl
               ende = true;
         }
      }

   }
}
