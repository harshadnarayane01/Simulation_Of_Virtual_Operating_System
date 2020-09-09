package mypackage;
import java.util.*;
public class Memory
{
	static char[][] memory = new char [300][4];// static to create Single copy of memory in each Object
	char card[][] = new char[10][4];//Array to read/Write Card
	static char word[] = new char [4];//static to use everywhere in program to access word from memory
	String c;
	static String data;
	static int counter;
	static boolean freespace[]=new boolean[30];
	
	void initialise_freespace()
	{
		for(int i=0;i<freespace.length;i++)
		{
			freespace[i]= true;
		}
	}
	public void store_card_to_memory_location (int loc, String card1)//stores a card/line to memory location
	{
		int index = 0;
		while(index<37)
		{
			c = card1.substring(index, index+4);
			store_to_word(loc,c);
			loc++;
			index = index + 4;
		}
	}

    public void store_to_word(int loc1, String c2) //converts a word to character array and stores to row No loc1
    {
    	word = c2.toCharArray();
    	
		for(int col=0; col<=3; col++)
		{
			memory[loc1][col] = word[col];
		}
	}
    
	public char[] getMemory(int loc) //returns char array from required row No
	{
		int col;
		char word[]=new char[4];
		for(col=0;col<=3;col++)
		{
			word[col]=memory[loc][col];
		}
		return word;
	}
	
	public String getCard(int loc)//returns the card converting to it to String
	{
		String card="";
		for(int i=loc;i<loc+10;i++)
		{
		   card=card+String.valueOf(getMemory(i));
			
		}
		return card;
	}
	//Allocate the Page Table using Random class
	public int allocatepage()
	{
		int random_number;
		Random random_obj=new Random();
		random_number=random_obj.nextInt(30);
		while(freespace[random_number]!=true)
		{
			random_number=random_obj.nextInt(30);
		}
		freespace[random_number]=false;
		return random_number;
	}
	
	public void update_pagetable(int pageno)
	{
		String no;
		//String no1=String.valueOf(CPU.IR[2]);
		//int IR = Integer.parseInt(no1);
		  
		char arr[]=new char[2];
	    no=String.valueOf(pageno);
	    arr=no.toCharArray();
	    if(pageno>=0 && pageno<=9)
	    {
	    	memory[counter][1]='1';
		    memory[counter][2]='0';
		    memory[counter][3]=arr[0];
		   	
	    }
	    else
	    	{
	    	  memory[counter][1]='1';
	    	  memory[counter][2]=arr[0];
	  	      memory[counter][3]=arr[1];
	  	 
	    	}
	       counter++;
	}
	
}

