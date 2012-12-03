/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diyapp;

/**
 *
 * @author kamyk
 */
public class GPS {
    private int wartosc_ocz_x;
    private int wartosc_ocz_y;
    private int stosunek_ocz;
    
    private int wartosc_obecna_x;
    private int wartosc_obecna_y;
    private int stosunek_obecny;
    
    public GPS(int wartosc_ocz_x, int wartosc_ocz_y, int stosunek_ocz, int wartosc_obecna_x, int wartosc_obecna_y){
        this.wartosc_ocz_x = wartosc_ocz_x;
        this.wartosc_ocz_y = wartosc_ocz_y;
        this.stosunek_ocz = stosunek_ocz;
        this.wartosc_obecna_x = wartosc_obecna_x;
        this.wartosc_obecna_y = wartosc_obecna_y;
    }
    
    private void obecny(){ 
            if(wartosc_obecna_x == wartosc_ocz_x && wartosc_obecna_y == wartosc_ocz_y){
                stosunek_obecny = 3;
            }
            else
                stosunek_obecny = 1;    
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
