/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binbaum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static Prog1Tools.IOTools.*;

/**
 *
 * @author Mathias Münscher (#531968)
 * 14.07.2011
 */
public class BinBaum {

   private Knot rootKnot = null;    // Baum ist leer

   // getter für Baumwurzel
   public Knot getRoot() {
      return rootKnot;
   }

   // Einfuegen eines Knotens
   public void insertKnot(char x) {
      if (rootKnot == null) {
         rootKnot = new Knot(x);
      } else {
         insertRecKnot(rootKnot, x);
      }
   }

   // sucht rekursiv die richtige Stelle im Baum
   private void insertRecKnot(Knot p, char x) {
      //Vegleich 
      if (x < p.data) {

         if (p.daughterLeft == null) {
            putKnotInPlace(p, true, x);
         } else {
            insertRecKnot(p.daughterLeft, x);
         }
      } else if (p.daughterRight == null) {
         putKnotInPlace(p, false, x);
      } else {
         insertRecKnot(p.daughterRight, x);
      }

   }

   // fügt neuen Knoten in Baum ein
   private Knot putKnotInPlace(Knot mother, boolean toLeft, char x) {
      if (toLeft) {
         mother.daughterLeft = new Knot(x);
         mother.daughterLeft.mother = mother;
         mother.daughterLeft.sister = mother.daughterRight;
         mother.daughterLeft.knotDepth = mother.knotDepth + 1;
         mother.daughterLeft.sitLeft = true;
         return mother.daughterLeft;
      } else {
         mother.daughterRight = new Knot(x);
         mother.daughterRight.mother = mother;
         mother.daughterRight.sister = mother.daughterLeft;
         mother.daughterRight.knotDepth = mother.knotDepth + 1;
         mother.daughterRight.sitLeft = false;
         return mother.daughterRight;
      }
   }

   // Suche eines Knotens
   public void searchKnot(char x) {
      if ((searchRecKnot(rootKnot, x)) != null) {
         System.out.println(x + " wurde gefunden");
      } else {
         System.out.println(x + " wurde nicht gefunden");
      }
   }
   // Rekursive Suche ab p

   public Knot searchRecKnot(Knot p, char x) {
      if (p == null) {
         return null;
      }
      if (p.data == x) {
         return p;
      } else if (p.data < x) {
         return searchRecKnot(p.daughterRight, x);
      } else {
         return searchRecKnot(p.daughterLeft, x);
      }
   }

   // Alle Knoten ab k (incl. k) werden neu im Baum angelegt
   private void reChainKnots(Knot k) {
      if (k != null) {
//         System.out.println("Rechain: "+k.data+"\n");
//         char c = readChar();
         insertKnot(k.data);
//         printTree();
         reChainKnots(k.daughterLeft);
         reChainKnots(k.daughterRight);
      }
   }

   // Sucht nach Knoten, kettet ihn aus und fügt alle flogenden Knote wieder neue ein.
   public boolean deleteKnot(char x) {
      Knot k = searchRecKnot(rootKnot, x);
      if (k == null) {
         return false;
      } else {
         // k ausketten
         if (k.mother != null) {
            if (k.sitLeft) {
               k.mother.daughterLeft = null;
            } else {
               k.mother.daughterRight = null;
            }
            if (k.sister != null) {
               k.sister.sister = null;
            }
         };
         if (k == rootKnot) {
            rootKnot = k.daughterLeft;
            reChainKnots(k.daughterRight);
         } else {

            reChainKnots(k.daughterLeft);
            reChainKnots(k.daughterRight);
         }
         return true;
      }
   }

   // Ausgabe des Baumes
   public void printTree() {
      System.out.println("\n --- Baum - Uebersicht --- \n");
      if (rootKnot == null) {
         System.out.println(" Baum ist leer ");
      }
      printRecTree(rootKnot, 0, 0);
   }

   // Rekursive Ausgabe des Baumes ab dem Knoten k
   private void printRecTree(Knot k, int depth, int knotsAbove) {
      if (k != null) {
         depth++;
         printRecTree(k.daughterRight, depth, knotsAbove + 1);
         depth--;
         for (int i = 0; i < depth; i++) {
            if (i == (depth - knotsAbove - 1)) {
               System.out.print("|   ");
            } else {
               System.out.print("    ");
            }
         }

         System.out.println("|-- " + k.data);
         depth++;
         printRecTree(k.daughterLeft, depth, -1);
         depth--;
      }
   }

   // Ausgabe aller Knoten als sortierete Liste (kleinste Elemente zuerst)
   public void sortPrintUp(Knot p) {
      if (p != null) {
         sortPrintUp(p.daughterLeft);
         System.out.print(" - " + p.data);
         sortPrintUp(p.daughterRight);
      }
   }

   // Ausgabe aller Knoten als sortierete Liste (größte Elemente zuerst)
   public void sortPrintDown(Knot p) {
      if (p != null) {
         sortPrintDown(p.daughterRight);
         System.out.print(" - " + p.data);
         sortPrintDown(p.daughterLeft);
      }
   }

   // Initialisiert Baum
   public void deleteTree() {
      rootKnot = null;
   }

   // Wandelt den Baum in einen linearen String um
   private String deflateTree(Knot k, String output) {
      if (k != null) {
         output = output + k.data + ",";
         output = deflateTree(k.daughterLeft, output);
         output = deflateTree(k.daughterRight, output);
      } //System.out.println("\n" + output);
      else {
         output = output + "-,";
      }
      return output;
   }

   // Umkehrfunktion zu deflateTree wandelt einen linearen String in einen Baum um
   private void inflateTree(String input) {
      if (input.charAt(0) != '-') {
         rootKnot = new Knot(input.charAt(0));
         inflateRecusiveTree(input.substring(2), rootKnot);
      }
   }

   // Rekursives Abarbeiten eines Strings und einfügen in den Baum
   private String inflateRecusiveTree(String input, Knot mother) {
      Knot me = null;
      System.out.println("Input Links: " + input + "\n");
      if (input.charAt(0) != '-') {
         me = putKnotInPlace(mother, true, input.charAt(0));
         input = inflateRecusiveTree(input.substring(2), me);
      } else {
         input = input.substring(2);
      }
      System.out.println("Input: Rechts: " + input + "\n");
      if (input.charAt(0) != '-') {
         me = putKnotInPlace(mother, false, input.charAt(0));
         input = inflateRecusiveTree(input.substring(2), me);
      } else {
         input = input.substring(2);
      }
      return input;
   }

   // Speichert Baum in einer Datei
   public void saveTree(String filename) {
      try {
         BufferedWriter filestream = new BufferedWriter(
                 new FileWriter(filename));
         Knot k = rootKnot;
         String treeString = "";
         treeString = deflateTree(k, (treeString));
         //treeString = reverseString(treeString) + "\n#";
         filestream.write(treeString + "\n#");
         filestream.newLine();
         filestream.close();
      } catch (IOException e) {
         System.out.println("Fehler beim Erstellen der Datei");
      }
   }

   // Lädt Baum aus einer Datei
   public void readTree(String filename) {
      try {
         BufferedReader filestream = new BufferedReader(
                 new FileReader(filename));
         rootKnot = null;
         String treeString = "";
         if ((treeString = filestream.readLine()) != null) {
            inflateTree(treeString);
         }
         System.out.println("\n readLine: " + treeString);
         filestream.close();
      } catch (IOException e) {
         System.out.println("Fehler beim Lesen der Datei");
      }
   }
}
