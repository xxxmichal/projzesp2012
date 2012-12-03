/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diyapp;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;

/**
 *
 * @author kamyk
 */
public class DIYapp {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws IOException, InterruptedException {
	    
         //pierwszy etap - stworzenie listy poleceń
            double godz_aktualna = 0.0;
            LinkList [] list = new LinkList[4];
            
            list[0] = new LinkList();
            list[1] = new LinkList();
            list[2] = new LinkList();
            list[3] = new LinkList();
            
	    list[0].insert(1, 12, 15, 3);//GPS rowne(3) 12 15
	    list[0].insert(2, 12.45, 1);//Godzina mniejsza niż 12.45
	    list[0].insert(3, "GPS jest rowne 12 15, i jest godzina miedzy 00.00, a 12.45");
            list[0].addClose();
            list[0].printList();
            
            
	    list[1].insert(1, 12, 15, 3);//GPS rowne(3) 12 15
	    list[1].insert(2, 10.12, 1);//Godzina mniejsza niz 10.12
	    list[1].insert(3, "GPS jest rowne 12 15, i jest przed godzina 10.12");
            list[1].addClose();
            list[1].printList();
            
            
	    list[2].insert(1, 12, 15, 3);//GPS rowne(3) 12 15
	    list[2].insert(2, 12.45, 2);//Godzina wieksza niż 12.45
	    list[2].insert(3, "GPS jest rowne 12 15, i jest godzina miedzy 12.45, a 00.00");
            list[2].addClose();
            list[2].printList();
            
            
	    list[3].insert(1, 12, 15, 3);//GPS rowne(3) 12 15
	    list[3].insert(2, 22.45, 1);//Godzina mniejsza niż 22.45
	    list[3].insert(3, "GPS jest rowne 12 15, i jest godzina miedzy 00.00, a 22.45");
            list[3].addClose();
            list[3].printList();
	 /*   while(!list.isEmpty()) {
		    Link deletedLink = list.delete();
		    System.out.print("deleted: ");
		    deletedLink.printLink();
		    System.out.println("");
	    }
	  list.printList();*/
          
         //drugi etap wykonywanie listy poleceń
          //  try{ //wypisanie listy z 1 sekundowym opuznieniem
                
	    
          //  }
           // catch(Exception ie){
            //If this thread was intrrupted by nother thread 
           // }
          
           
           int i =0;
           /*while(i<10){
               System.out.println("id/x/y/stosunek/warunek/godzina "+element.id+"/"+element.wsp_x+"/"+element.wsp_y+"/"+element.stosunek+"/"+element.warunek+"/"+element.godzina+"/");
               element = element.nextLink;
               i++;
           
           }*/
           boolean prawda = false;
           
           System.out.println("GPS wklepane na stałe jako 12 15, godzina się zmienia");
           while(true){
               if(i==4)
                   i=0;
               Link element = list[i].first;
               while(element.warunek){
                   prawda=false;
                   switch (element.id) {
                      case 1:
                        {
                        String s; 
                        int x;
                        int y;
                       //System.out.println("id/x/y/stosunek/warunek/godzina "+element.id+"/"+element.wsp_x+"/"+element.wsp_y+"/"+element.stosunek+"/"+element.warunek+"/"+element.godzina+"/");
                       /*System.out.println("GPS, podaj wsp x(prawidlowa to:"+element.wsp_x +")");
                        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1); 
                        s = stdin.readLine(); // odczyt danych ze strumienia wej. 
                        x = Integer.parseInt(s);
                        
                        System.out.println("GPS, podaj wsp y(prawidlowa to:"+element.wsp_y +")");
                        stdin = new BufferedReader(new InputStreamReader(System.in), 1); 
                        s = stdin.readLine(); // odczyt danych ze strumienia wej. 
                        y = Integer.parseInt(s);
                   */
                        
                        GPS adres = new GPS(element.wsp_x, element.wsp_y, element.stosunek, 12, 15);
                        prawda = adres.isTrue();
                        break;
                        }

                      case 2:
                       {
                     /* String s; 
                        double x;
                        //System.out.println("id/x/y/stosunek/warunek/godzina "+element.id+"/"+element.wsp_x+"/"+element.wsp_y+"/"+element.stosunek+"/"+element.warunek+"/"+element.godzina+"/");
                        System.out.println("Godzina, podaj godzine jako double(format 12.00). Żeby działało musi być mniejsza od:"+element.godzina +")");
                        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1); 
                        s = stdin.readLine(); // odczyt danych ze strumienia wej. 
                        x = Double.parseDouble(s);
                        */
                        Godzina godzina = new Godzina(element.godzina, element.stosunek, godz_aktualna);
                        prawda = godzina.isTrue();}
                          break;

                      default:
                        break;   
                    }
                    if(!prawda)
                        break;
                    element = element.nextLink;
               }
               if(i==0){
               
               Thread.sleep(2000);
               System.out.printf("Jest godzina: %.2f\n",godz_aktualna);
                   if(godz_aktualna <= 24)
                       godz_aktualna+=3.05;
                   else
                       godz_aktualna = 0.0;
               }
               if(prawda){
                   System.out.println(element.tekst_do_wyswietlenia);
               }
               
               
               i++;
               
           }
            
    }
}
