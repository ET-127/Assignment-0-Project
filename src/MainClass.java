import java.util.*;
import java.io.*;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class MainClass {
	
	static Finch red  = new Finch();
	//Finch green  = new Finch();
	//Finch blue  = new Finch();
	//Finch yellow  = new Finch();
	static boolean repeat = true;
	static int score;
	

	
	public static void main(String[] args) {
	
		//FinchTest();
		
		String sequence = "";
		String i = "";
		
		while(repeat) {
		
			sequence += String.valueOf(GenerateColour());
			print(sequence);
			OutputSequence(sequence);
			
			i = ReceiveInput();
		
			if(i.equals(sequence)) {
				
				score++;
				repeat = true;
				print("Correct!Next Round");
				
			} else {
				
				print("Your score was " + String.valueOf(score));
				repeat = false;
				
			}
			
			//print(FinchInput());
			
		}
		
		
	}
	
	static void print(Object s) {
		
		System.out.println(s);
		
	}

	//Will change in the future
	static String ReceiveInput() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String n = "";
		
		print("Input Answer");
		
		try {
			n = br.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return n;		
	}
	
	static void OutputSequence(String sequence) {
		
		char[] seq= sequence.toCharArray();
		
		for(int i = 0; i < seq.length;i++) {
			
			char n = seq[i];
			SetColour(n,1000);
			SetToBlank();

		}
		
	}
	
	static void SetToBlank() {
		
		red.setLED(0,0,0,1000);
		//blue.setLED(0,0,0,1000);
		//green.setLED(0,0,0,1000);
		//yellow.setLED(0,0,0,1000);
		
	}
	
	static void SetColour(char id,int dur) {
		
		switch(id) {
		
			case '0':
				//red
				red.setLED(255,0,0,dur);
				break;
				
			case '1':
				//green
				red.setLED(0,255,0,dur);
				break;
			
			case '2':
				//blue
				red.setLED(0,0,255,dur);
				break;
				
			case '3':
				//yellow
				red.setLED(255,255,0,dur);
				break;
		
		}
				
			
		
	}
	
	/*static String FinchInput() {
		
		if(red.isTapped()) {
			
			return "0";
			
		} else {
			
			return "";
			
		}
		
	}*/
	
	//Generate a random number between 0 and 3 inclusive
	static int GenerateColour() {
		
		Random rand = new Random();
		
		int n = rand.nextInt(4);
		
		return n;
		
	}
}
