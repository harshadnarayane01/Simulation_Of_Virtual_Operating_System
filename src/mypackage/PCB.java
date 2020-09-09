package mypackage;

public class PCB 
{
     static int TTL,TTC,TLL,TLC,PTR,IC;                //static to use everywhere in other class
  void getCounters(String line)
   {

		TTL = Integer.parseInt(line.substring(8, 12));
		TLL = Integer.parseInt(line.substring(12, 16));
		
   }
}
  
