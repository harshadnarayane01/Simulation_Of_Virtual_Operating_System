package mypackage;
import java.io.*;
import java.util.*;
import mypackage.*;
public class OS 
{
	static BufferedReader br;//Static to use everywhere in program with its current Value
	static FileOutputStream fos;//Static to use everywhere in program with its current Value
	static int page_table_number;
	
	public static void main(String[] args) throws Exception 
	{
		long time=System.currentTimeMillis();//Storing initial Time
		
		Memory mem = new Memory();
		
		int no=1;//Counter for Job Completed
		CPU cp = new CPU();
		int new_index, pageno;
		
		File f = new File("code.txt");//creating File Connections
		File fout_obj=new File("output.txt");//Creating Output File Connections
		FileInputStream fis = new FileInputStream(f);//InputStream To read the File
		fos=new FileOutputStream(fout_obj,true);//Output File's Stream opened in Append Mode
		InputStreamReader isr = new InputStreamReader(fis);
		
		OS.br = new BufferedReader(isr);
		
		PCB pcb =new PCB();
		
		String line;
		
		while((line = br.readLine())!= null)//Loop for reading the File Line by line
		{
			
			
			if(line.startsWith("$AMJ"))
			{
				pcb.getCounters(line);
				PCB.TLC=0;    //Initialise Counters
			    PCB.TTC=0;
				mem.initialise_freespace();
				page_table_number=mem.allocatepage();
				PCB.PTR = page_table_number;
				new_index=page_table_number*10;
				Memory.freespace[page_table_number]=false;
				//store data in PCB's variables
				for(int l=0;l<10;l++)
				{
					Memory.memory[new_index][1]='0';
					new_index++;
				}
				Memory.counter = PCB.PTR * 10;
				continue;
			}
			else if (line.startsWith("$DTA")) 
			{
				
				cp.start_Execution();		//As line Started with $DTA executing user Program
				cp.terminate=false;			//Making terminate flag false so as to read next Job refer loop of CPU class
				System.out.println("Memory Cleared....");
				if(no==1)					//logic to Display Job Count
				{
					System.out.println(" 1st Job completed");
					no++;
				}
				else if(no==2)
				{
					System.out.println(" 2nd Job completed ");
					no++;
				}
				else if(no==3)
				{
					System.out.println(no+"rd Job Completed");
					no++;
				}
				else
				{
					System.out.println(no+"th Job Completed");
					no++;
				}
				Thread.sleep(380);
				
				continue;   		
			}
			else if (line.startsWith("$END"))
			{
				
				fos.write("\n-------------------------------------------------------\n".getBytes());  //Seperating Output by ---line
				//OS.fos.write(endline.getBytes()); 
				CPU.fos1.write("------------------------------\n\n".getBytes());
				CPU.ICcounter=0;
		        CPU.ServiceInterupt=CPU.time_Interupt=CPU.program_interupt=0;
				continue;
			}
			else
			{
				
				System.out.println("Loading....");//Loading to memory and loading function calls
				pageno = mem.allocatepage();//allocate page
				mem.store_card_to_memory_location(pageno*10, line);	//store program card in that page
				mem.update_pagetable(pageno);//update page table
			    continue;
			}
			
		}
		long time2=System.currentTimeMillis();//Storing final Time
		System.out.println("Exiting...the Program");
		System.out.println("Time Required for Execution is:"+(time2-time)/1000+" seconds");//Displaying  Time Required
	}

}
