/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binbaum;

/**
 *
 * @author maak
 */
public class Knot {

   public char data;                                  // Datenelement
   public Knot mother, sister, daughterLeft, daughterRight; // Zeiger auf verbunden Knoten
   public int knotDepth;              // Zähler für Tiefe und Folgeknoten
   public boolean sitLeft;

   //Konstructor 
   Knot(char n) {
      data = n;
      daughterLeft = null;
      daughterRight = null;
      sister = null;
      mother = null;
      sitLeft = true;
      knotDepth =0;
//      countFollowers=0;
   }
}