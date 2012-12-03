/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diyapp;

/**
 *
 * @author kamyk
 */
class Link {
    public int id = -1;
    public double godzina = -1;//dla uproszczenia godzina w double(w postaci np. 12.00)
    
    //dla wsp GPS
    public int wsp_x = -1;
    public int wsp_y= -1;
    public int stosunek = -1;//czy nasza wartosc ma byc wieksza od aktualnej (1), mniejsza (2), rowna(3)
    public boolean warunek = false;//sprawdzenie czy jest to instrukcja warunku, czy komenda do wykonania po spełnieniu waruków np. tekst ma false
    public String tekst_do_wyswietlenia = null;
    public boolean lastLink = false;
    public Link nextLink;

    //Konstruktor dla czasu
    public Link(int id, double godzina, int stosunek) {
	    this.id = id;
	    this.godzina = godzina;
            warunek=true;            
            this.stosunek = stosunek;
            wsp_x = -1;
    }
    public Link(int id, int wsp_x, int wsp_y, int stosunek) {
	    this.id = id;
	    this.wsp_x = wsp_x;
	    this.wsp_y = wsp_y;
            warunek=true;            
            this.stosunek = stosunek;
    }
    public Link(int id, String tekst) {
	    this.id = id;
	    tekst_do_wyswietlenia = tekst;    
    }
    public Link() {
	    lastLink = true;    
    }
    //Print Link data
    public void printLink() {
        if(wsp_x != -1 && wsp_y !=-1)
	    System.out.print("{ GPS, id GPS=" + id + ", " + wsp_x + " " + wsp_y + ", warunek " + stosunek + "} ");
        else if(godzina != -1)
	    System.out.print("{ Godzina, id godziny=" + id + ", " + godzina + ", warunek " + stosunek + "} ");
        else if(tekst_do_wyswietlenia != null)
	    System.out.print("{ Tekst, id tekstu=" + id + ", " + tekst_do_wyswietlenia + "} ");
        System.out.print("\n");
    }
}
