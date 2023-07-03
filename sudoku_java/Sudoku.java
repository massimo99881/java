//non puo' essere utilizzata la prog
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

//il programma prevede due modalita di gioco: con logica del sudoku o senza 

class Sudoku
{
   public static void main(String args[]) throws IOException {
   	
   int scegli_griglia=0;
   int scelta=0; 
    
   //costruzione degli stream di caratteri
    InputStreamReader sr = new InputStreamReader(System.in);
    BufferedReader sorg = new BufferedReader(sr);
    
    Griglia utente=null; //griglia di gioco corrente

    boolean usalogica=false; 
    char var='0';
    String v;
    System.out.println("\n\nBenvenuto. Vuoi giocare utilizzando la difficolta' reale del sudoku? "+
                            "\n(Inserisci s oppure n): ");

    do{
         
         v=sorg.readLine();

         try{
           var = v.charAt(0);
         }
         catch(StringIndexOutOfBoundsException e)
         {
           v="Se l'utente inserisce una stringa vuota, viene generata l' eccezione                    "+
             "StringIndexOutOfBoundsException la quale modifica la stringa per far ripetere il ciclo. ";
         }

         if(v.length()>1 || (var!='s' && var!='S' && var!='n' && var!='N'))
           System.out.println("(valore inserito non corretto) Riprova: ");

    }while(v.length()>1 || (var!='s' && var!='S' && var!='n' && var!='N'));

    if(var=='s' || var=='S') { 
     usalogica=true;
     System.out.println("\n\nHai scelto modalita' di gioco reale.\n\n"); 
    }
    else System.out.println("\n\nHai scelto modalita' di gioco facile.\n\n");
   
   do{
   	
   	
   	System.out.println("\n       *****************************************************");
   	System.out.println("       *               menu principale                     *");
   	System.out.println("       *                                                   *");
   	System.out.println("       *   1) Inizia/riprendi partita con griglia corrente *");
   	System.out.println("       *   2) Scegli griglia                               *");
   	System.out.println("       *   3) Estrai griglia a caso                        *");
   	System.out.println("       *   4) Esci                                         *");
    System.out.println("       *                                                   *");
    System.out.println("       *****************************************************\n");
   	
   	
    
    
    try{
    	System.out.println("\n                   inserisci la tua scelta: ");
        String s=sorg.readLine();
        scelta = Integer.parseInt(s);
   
   	
	   	if(scelta<1 || scelta>4)
	   	  System.out.println("( da 1 a 4. grazie)");
	   	else{
   	         
		   	switch(scelta){
		   	
		   	  case 1 : //se non e' stata impostata alcuna griglia corrente il pgr visualizza
		   	           //un messaggio d'errore e torna al menu principale
		   	           if(Griglia.isCaricata()){
		   	           	
						int scelta2=0;   
                               
						do{
							
						//..presentazione della griglia..
						System.out.println("\t\t  * Griglia corrente ("+scegli_griglia+") *");
						System.out.println(utente);
						//..
							
						System.out.println("	**********************************************");
						System.out.println("	*               @ menu di gioco              *");
						System.out.println("	*                                            *");
						System.out.println("	*  1) Mossa             3) Termina partita   *");
						System.out.println("	*  2) Suggerimento      4) Sospendi partita  *"); 
						System.out.println("	*                                            *");  
						System.out.println("	**********************************************");
														  
						try{
						    System.out.println("                   Inserisci la tua scelta: ");
						    s=sorg.readLine();
						    scelta2 = Integer.parseInt(s);
						   }
						   catch(NumberFormatException e)
						   { 
						    System.out.println("\n(inserimento non valido)\n");  
						    scelta2=0; 
						   }
										    
						   if(scelta2<1 || scelta2>4)
						     System.out.println("\n(inserisci un valore compreso tra 1 e 4. grazie)\n");
  
						   switch(scelta2){
							
							 case 1 : //mossa
							         int riga=0,colonna=0,numero=0;
  							         boolean next=true,next2=true; 
										          
							         do{  
							          try{	
							               do{
							                  System.out.println("Inserisci riga: "); 
									          String r=sorg.readLine();
									          riga = Integer.valueOf(r).intValue();
												           
									          if(riga<1 || riga>9){
									             System.out.println("(inserire un valore compreso tra 1 e 9)");
									             next=false;
									          }
									          else next=true;
												           
									        }while(riga<1 || riga>9);
											           
									      }									               
								          catch(NumberFormatException e)
							              { 
							                //se il numero non e' corretto il pgr chiede all'utente di reinserire
					   		                next=false;
							                System.out.println("\n(inserimento non valido, valore non intero)\n\n");  
							              }
							              catch(StringIndexOutOfBoundsException e)
							              {
							                System.out.println("\n(inserimento non valido)\n\n"); 
							                next=false;								                    	 
							              }
							           }while(!next);
							                       
							           do{ 
							              try{
							                   do{
												    System.out.println("Inserisci colonna: "); 
													String c=sorg.readLine();
													colonna = Integer.valueOf(c).intValue();
														   
													if(colonna<1 || colonna>9){
													  System.out.println("(inserire un valore compreso tra 1 e 9)");
													  next2=false;  
													}
													else next2=true;
														   
												}while(colonna<1 || colonna>9);
													 			               
									      }									               
								          catch(NumberFormatException e)
							              { 
							                //se il numero non e' corretto il pgr chiede all'utente di reinserire
					   		                next2=false;
							                System.out.println("\n(inserimento non valido, valore non intero)\n\n");  
							              }
							              catch(StringIndexOutOfBoundsException e)
							              {
							               System.out.println("\n(inserimento non valido)\n\n"); 
							               next2=false;								                    	 
							              }
							            }while(!next2);							   
									    				  
										System.out.println("       inserisci il valore da inserire nella griglia: ");		 
												 
										//obbligo l'utente ad inserire correttamente i valori 
										do{
										    String s2 = sorg.readLine();
										    try{
										   	    numero = Integer.parseInt(s2);
										   	    if(numero<1 || numero>9)
										          System.out.println("\n(inserimento non valido, inserisci un valore compreso tra 1 e 9)\n\nReinserire valore:");
										    }
											catch(NumberFormatException e)
									           { System.out.println("\n(inserimento non valido)\n\nReinserire valore:");  }
									                    												    
										}while(numero<1 || numero>9);
												 
										//..invocare un metodo della classe Griglia passandogli i dati acquisiti
										utente.inserisciValore(riga,colonna,numero,usalogica);
												 
										break;
											  
								case 2 : //suggerimento 
									     utente.inserisciSuggerimento();
										            
										 if(utente.vinto) {
										    System.out.println("         primi invio per tornare al menu principale..");
											sorg.readLine();
											
											//quando si vince il gioco o quando lo si termina, si ripristina la griglia corrente
											//e si torna al menu principale
											utente=prelevaGriglia(scegli_griglia , utente); 
											scelta2=3;
		                                 }
										 
										 break;
											 
								case 3 : System.out.println("\n\n                       partita terminata\n");
									     System.out.println("            premi invio per tornare al menu principale..");
										 sorg.readLine();
										 utente=prelevaGriglia(scegli_griglia , utente);   
										 scelta2=3;
									     //termina partita
									     //..ripristino situazione di partenza della griglia selezionata
											 
									     break;
										 
							    case 4 : //sospendi partita
									     //..imposta come "griglia corrente" lo stato attuale della griglia.
									     //  Il contenuto del file "db_sudoku" rimane pero invariato
									     System.out.println("\n\n                       partita sospesa\n");
									     System.out.println("       ..primi invio per tornare al menu \n"+
									                        "                 e riprendere la partita in un secondo momento ..");
										 sorg.readLine();
	          					         Griglia.sospendi=true;								          
									     scelta2=3;
									     break;
								
								} //fine switch
								 
							  }while(scelta2!=3);
							  //se scelta2!=3 stampa la griglia aggiornata, torna al menu di gioco
		   	           }
		   	           else{ System.out.println("\n(griglia non ancora caricata)\n"); } 
		   	             	
		   	           break;
		   	           
		   	  case 2 : //scegliere la griglia con cui giocare: viene fornita una lista con il numero di valori predefiniti di ciascuna griglia
		   	           scegli_griglia=0;
		   	           System.out.println("                Scegli la griglia di partenza:\n"+
											"                   1) griglia di 30 valori\n"+
											"                   2) griglia di 27 valori\n"+
											"                   3) griglia di 25 valori\n"+
											"                   4) griglia di 23 valori\n"+
											"                   5) griglia di 20 valori\n"+
									        "\n                          Inserisci: ");
	   	           
		   	            try{
		   	            	            
		   	                do{    
						        try{
						              String s2=sorg.readLine();
						              scegli_griglia = Integer.parseInt(s2);
						                      
						              if(scegli_griglia<6 && scegli_griglia>0)
						                utente = prelevaGriglia(scegli_griglia , utente);
				   		              else
						                System.out.println("\n(reinserire il numero della griglia desiderata)\n\n                      Inserisci (da 1 a 5): ");
				                          
						        }
						        catch(NumberFormatException e)
						        { 
						          System.out.println("\n(inserimento non valido)\n\n                      Reinserire valore intero: ");  
						        }
			                   
			                }while (scegli_griglia<1 || scegli_griglia>5);  
			               		                   	
				          }
				          catch(FileNotFoundException e)
				          {
				          	//se il file non esiste , allora ne crea uno vuoto
		   	                //informa l'utente del fatto che non vi sono griglie memorizzate
		   	                //nel file e torna al menu princ.
		   	                   
		   	                 try {
						      new FileOutputStream("db_sudoku");
						      System.out.println("\n(Non vi sono griglie memorizzate nel file)\n");
						     }
						     catch (IOException er) {
						      System.out.println("Errore nella creazione del file db_sudoku: " + er);
						     }

				          }
		   	        
		   		        break;
		   		       
		   		       
		      case 3 :
		               try{ 	            	
		   	            	
		   	            	Random Casuale =  new Random(); 
		   	            	scegli_griglia= Casuale.nextInt(5)+1;
		                    
					        utente = prelevaGriglia(scegli_griglia, utente);
					     
					   }
		               catch(FileNotFoundException e)
				          {
				          	try {
						      new FileOutputStream("db_sudoku");
						      System.out.println("\n(Non vi sono griglie memorizzate nel file)\n");
						     }
						     catch (IOException er) {
						      System.out.println("Errore nella creazione del file db_sudoku: " + er);
						     }

				          }
		               
		               break;
		               
		       case 4: System.out.println("                        Sudoku by Galasi Massimo"); break;
		       
		      
		     //fine switch  
		    }
     //fine else
    }
    //fine try
   }
   catch(NumberFormatException e)
	{ 
	  System.out.println("(inserimento non valido)");  
	}
   	
  }while(scelta!=4);
   	
   	
 }//fine main	



  public static Griglia prelevaGriglia(int scegli_griglia, Griglia x) throws FileNotFoundException,IOException{
	
	
	FileReader frd = new FileReader("db_sudoku");
	int i; 
    String file="";
    
    while ((i = frd.read()) != -1)
       file+=((char)i);
       
    if(file.length()>0)
    { 
       
       System.out.println();
       
       int inizio_partenza=0;
       int fine_partenza=0;
       int inizio_soluzione=0;
       int fine_soluzione=0;
	                           
	                           
         inizio_partenza=file.indexOf("partenza"+scegli_griglia);
         fine_partenza=file.indexOf("fine_partenza"+scegli_griglia);
         String file_partenza=file.substring((inizio_partenza+9),fine_partenza);
         
         inizio_soluzione=file.indexOf("soluzione"+scegli_griglia);
         fine_soluzione=file.indexOf("fine_soluzione"+scegli_griglia);
         String file_soluzione=file.substring((inizio_soluzione+10),fine_soluzione);
         
         x = new Griglia(file_partenza,"partenza");
         Griglia soluzione = new Griglia(file_soluzione,"soluzione");
         
         
         //prova di stampa soluzione da oggetto Griglia 
         //..
         //System.out.println("prova di stampa da oggetto Griglia (tipo soluzione)");
         //System.out.println(soluzione);
         
         
         
         if(x.isCaricata())
             System.out.println("\n\n(caricata griglia "+scegli_griglia+")");
           					        
    }
    else
       System.out.println("(Non sono presenti griglie nel file)"); 
           
       
	return x;
	
	
} //fine metodo

	
	
} 
//fine classe Sudoku..

