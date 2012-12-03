/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diyapp;

/**
 *
 * @author kamyk
 */
public class Godzina {
    private double godzina;
    private int stosunek_ocz;
    
    private double godzina_obecna;
    private int stosunek_obecny;
    
    public Godzina(double godzina, int stosunek_ocz, double godzina_obecna){
        this.godzina = godzina;
        this.stosunek_ocz = stosunek_ocz;
        this.godzina_obecna = godzina_obecna;
    }
    
    private void obecny(){ 
        
            if(Math.abs(godzina-godzina_obecna)<0.01){
                stosunek_obecny = 3;
            }
            else if (godzina-godzina_obecna>0)
                stosunek_obecny = 1;    
            else if (godzina-godzina_obecna<0)
                stosunek_obecny = 2; 
            //nie sprawdzam czy adres jest wiekszy czy mniejszy bo tu trzeba sprawdzić czy punkt o takich i takich wsp znajduje sie w pewnym obszarze
    }       //dla uproszczenia przyjmijmy ze jak nie jest rowne, to jest mniejsze (1)
            
    public boolean isTrue(){
        this.obecny();
        if(stosunek_obecny == stosunek_ocz)
            return true;
        else
            return false;
    }
}
