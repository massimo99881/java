import java.util.StringTokenizer;
import java.util.Random; 


//gioco sudoku, Galasi Massimo 
//matricola 747112

public class Griglia {
	
	final static int N=9;
	
	public static boolean val=false;
	public static boolean vinto=false;
	public static boolean sospendi=false;
	
	//griglia di gioco di partenza
	public static char partenza[][] = new char [N][N];
	
	//griglia di gioco corrente aggiornata per inserimenti
	public static char copia_partenza[][] = new char [N][N];
	
	//griglia risolta
	private static char soluzione[][] = new char [N][N];
	
	
	//costruttore
	
	
	Griglia(String matrice,String s){
		
		//costruisco la matrice dalla stringa passata come primo parametro,
		//e definisco il tipo di matrice da costruire con il secondo parametro
		//..
		StringTokenizer token = new StringTokenizer(matrice," ");
		 
		if(s.equals("soluzione")){
		 
			for(int i=0;i<soluzione.length;i++)
			   {
			   	int y=0;
			   	 while(token.hasMoreTokens() && y<N)
				   {
				   	soluzione[i][y]=new Character(token.nextToken().charAt(0));
			   	    y++;
			   	   }
			   }
	    }
	    
	    if(s.equals("partenza")){
			 
			 for(int i=0;i<partenza.length;i++)
			   {
			   	int y=0;
			   	 while(token.hasMoreTokens() && y<N)
				   {
				   	partenza[i][y]=new Character(token.nextToken().charAt(0));
				   	copia_partenza[i][y]=partenza[i][y];
				   	  y++;
			   	   }
			   }
			   
			   
	    }
	
	    val=true;
		
	}
	
	
	//..
	//metodi di stampa
	
	public String toString(){
	   
	   String stampa="";
	  
	   	stampa+="\n                     ";
	   	for(int u=0;u<N;u++) stampa+=(u+1)+" ";
	     
	     stampa+="\n                     _ _ _ _ _ _ _ _ _";
	    
	     for(int i=0;i<N;i++){
	   	  stampa+="\n                  "+(i+1)+" |";
	       for(int y=0;y<N;y++)
	        if((y+1)%3==0) stampa+=copia_partenza[i][y]+"|";
	        else stampa+=copia_partenza[i][y]+" ";
	        
	      if((i+1)%3==0 && (i+1)!=9) stampa+="\n                    |- - -+- - -+- - -|";
	      if((i+1)==9) stampa+="\n   ";
	       
	     }
	     
	        
	     stampa+="                  - - - - - - - - - \n";
	     return stampa;
	   
           	
	}
	
	//..
	public String stampaSoluzione(){
	   
	   
	   String stampa="soluzione..\n";
	  
	   	stampa+="\n    ";
	   	for(int u=0;u<N;u++) stampa+=(u+1)+" ";
	     
	     stampa+="\n    _ _ _ _ _ _ _ _ _";
	    
	     for(int i=0;i<N;i++){
	   	  stampa+="\n"+(i+1)+"  |";
	       for(int y=0;y<N;y++)
	        if((y+1)%3==0) stampa+=soluzione[i][y]+"|";
	        else stampa+=soluzione[i][y]+" ";
	        
	      if((i+1)%3==0 && (i+1)!=9) stampa+="\n   |- - -+- - -+- - -|";
	      if((i+1)==9) stampa+="\n   ";
	       
	     }
	     
	        
	     stampa+=" - - - - - - - - - \n";
	     return stampa;
	   
           	
	}
	
	 
	//..
	//metodi di controllo e gestione
		
	public static boolean isCaricata(){
		
		return val;
	}
	
	public static boolean isSospesa(){
		
		return sospendi;
	}
	
	public void inserisciSuggerimento(){
		
	  
	        Random Casuale = new Random();
	      	int r = Casuale.nextInt(9)+1;  
	      	int c = Casuale.nextInt(9)+1; 
	      	char v = soluzione[(r-1)][(c-1)];
	      	      	  
	      	
	     try{
	     	 	//1] considero il caso di suggerimento che corregge il valore precedentemente inserito dall'utente
	     	 	//2] se utente vuole inserire valore su un suggerimento precedentemente inserito sollevo GrigliaException
	     	 	
	      	if(partenza[(r-1)][(c-1)]=='0' && partenza[(r-1)][(c-1)]!='X')  
	        {  
	            //caso di suggerimento ripetuto
	     	    copia_partenza[(r-1)][(c-1)]=v;
	     	    partenza[(r-1)][(c-1)]='X'; 
	     	    System.out.println("\n\n(il suggerimento ha inserito correttamente il valore "+v+" in posizione "+r+":"+c+")\n\n");
	     	    vinto=controlloSeVinto();
	     	    if(vinto) 
	     	      {
	     	      	System.out.println("\n\n\n\n\n");
	     	        System.out.println("complimenti hai risolto il gioco.\n\n");
	     	        return;
	     	      }
	     	       	     	    
	        }
		    else throw new GrigliaException("impossibile modificare valore corretto precaricato");
			     
	     }
	     catch(GrigliaException e)
	     {
	     	inserisciSuggerimento();
	     }
	     
	  //e' possibile cambiare i numeri precedentemente inseriti dall'utente
	  //non e' possibile cambiare i numeri prestabiliti dalla griglia di partenza
	  //(*GrigliaException )
	  		
	}	
	
	
	public void inserisciValore(int r, int c, int v, boolean logica){
		
	  //apportare alla griglia le modifiche richieste
	     String s=Integer.toString(v);
	       boolean vinto=false;
	    	       	
	     try{
	     	 	
	      	if(partenza[(r-1)][(c-1)]=='0')  
	        {  
	     	         	    
	     	    if(!logica){
	     	       if(soluzione[(r-1)][(c-1)]!=s.charAt(0))
	     	         throw new GrigliaException("il valore inserito non corrisponde alla soluzione\n");
	     	       else 
	     	         System.out.println("il valore inserito e' corretto. (corrisponde alla soluzione)"); 
	     	    }
	     	    
	     	    System.out.println("\n\n(inserito correttamente il valore "+v+" in posizione "+r+":"+c+")\n");
	     	    
	     	    copia_partenza[(r-1)][(c-1)]=s.charAt(0);
	     	    
	     	        vinto=controlloSeVinto();
	     	        if(vinto) System.out.println("complimenti hai risolto il gioco.");
	     	    	     	    
	        }
		    else throw new GrigliaException("\nmodifica non consentita al valore di cella prefissato in posizione "+r+":"+c+"\n");
		    
		    
			     
	     }
	     catch(GrigliaException e)
	     {
	     	//getMessage();
	     	System.out.println("\n\n"+e);
	     }    
	 
	}
	
	
	
	public boolean controlloSeVinto(){
		
		int x=0;
		for(int i=0;i<partenza.length;i++)
		  for(int y=0;y<partenza[i].length;y++)
		    if(soluzione[i][y]!=copia_partenza[i][y]) x=1;
		if(x==0) return true;
		else return false;
	}
 	 
	
} //fine classe