/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diyapp;

/**
 *
 * @author kamyk
 */
class LinkList {
    public Link first;

    //LinkList constructor
    public LinkList() {
	    first = null;
    }

    //Returns true if list is empty
    public boolean isEmpty() {
	    return first == null;
    }

    //Inserts a new Link at the first of the list
    public void addClose(){
        Link currentLink = first;
	    while(currentLink.nextLink != null) {
		    currentLink = currentLink.nextLink;
	    }            
	    Link link = new Link();
            currentLink.nextLink = link;
            link.nextLink = first;
            
    
    }
    public void insert(int id, double godzina, int rownosc) {
	    Link link = new Link(id, godzina, rownosc);
	    link.nextLink = first;
	    first = link;
    }
    public void insert(int id, int wsp_x, int wsp_y, int rownosc) {
	    Link link = new Link(id, wsp_x, wsp_y, rownosc);
	    link.nextLink = first;
	    first = link;
    }
    public void insert(int id, String tekst) {
            Link currentLink = first;
	    while(currentLink.nextLink != null) {
		    currentLink = currentLink.nextLink;
	    }            
	    Link link = new Link(id, tekst);
            currentLink.nextLink = link;
	    link.nextLink = null;
    }
    

    //Deletes the link at the first of the list
    public Link delete() {
	    Link temp = first;
	    first = first.nextLink;
	    return temp;
    }

    //Prints list data
    public void printList() /*throws InterruptedException */{
	    Link currentLink = first;
	    System.out.print("List: ");
	    while(!currentLink.lastLink) {
		    currentLink.printLink();
                  //  Thread.sleep(1000);
		    currentLink = currentLink.nextLink;
	    }
	    System.out.println("");
    }
}  

