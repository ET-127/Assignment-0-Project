import java.util.*;
import java.io.*;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class MainClass {
	
	static Finch red  = new Finch();
	static Finch green  = new Finch();
	//static Finch blue  = new Finch();
	//static Finch yellow  = new Finch();
	static boolean repeat = true;
	static int score;
	
	public static void main(String[] args) {
	
		FinchTest();
		
		String sequence = "";
		String i = "";
		
		char in = ' ';
		
		while(repeat) {
			
			i = "";
		
			sequence += String.valueOf(GenerateColour());
			//print(sequence);
			OutputSequence(sequence);
			
			print("Input Answer");
			do {
			
				in = ' ';
				
				while(in == ' ') {
				
					in = ReceiveInput();
					
				}
				
				i += in;
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			
			} while (i.length() != sequence.length());
		
			if(i.equals(sequence)) {
				
				score++;
				repeat = true;
				print("Correct!Next Round");
				
			} else {
				
				print("Your score was " + String.valueOf(score));
				repeat = false;
				
			}
			
			print("Input was " + i);
			
			//print(FinchInput());
			
		}
		
		
	}
	
	static void FinchTest() {
		
		if(red != null) {
			
			print("Red is live");
			red.setLED(255,0,0,1000);
			
		}
		
		if(green != null) {
			
			print("Green is live");
			green.setLED(0,255,0,1000);
			
		}
		
		/*if(blue != null) {
			
			print("Blue is live");
			
		}
		
		if(yellow != null) {
			
			print("Yellow is live");
			
		}*/
		
		SetToBlank();
		
	}
	
	static void print(Object s) {
		
		System.out.println(s);
		
	}

	//Will change in the future
	static char ReceiveInput() {

		char c = ' ';
		
		/*try {
			n = br.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
		
		if(red.isTapped() || green.isTapped() /*|| blue.isTapped() || yellow.isTapped()*/) {
			
			c = FinchInput();
		
			if(c != ' ') {
			
				printC(c);
				//SetColour(c,1000);
			
			}
			
		}
		
		return c;		
	}
	
	static void printC(char c) {
		
		switch(c) {
		
		
			case '0':
				
				//red
				print("RED");
				break;
				
				
			case '1':
				
				print("GREEN");
				break;
			
			/*case '2':
				
				return "BLUE";
				
			case '3':
				
				return "YELLOW";*/
		
		
		}
		
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
		
		red.setLED(0,0,0,500);
		green.setLED(0,0,0,500);
		/*blue.setLED(0,0,0,1000);
		yellow.setLED(0,0,0,1000);*/
		
	}
	
	static void SetColour(char id,int dur) {
		
		switch(id) {
		
			case '0':
				//red
				red.setLED(255,0,0,dur);
				break;
				
			case '1':
				//green.setLED(0,255,0,dur);
				green.setLED(0,255,0,dur);
				break;
			
			/*case '2':
				//blue.setLED(0,0,255,dur);
				blue.setLED(0,0,255,dur);
				break;
				
			case '3':
				//yellowsetLED(255,255,0,dur);
				yellow.setLED(255,255,0,dur);
				break;*/
		
		}
				
			
		
	}
	
	static char FinchInput() {
		
		if(red.isTapped()) {
			
			return '0';
			
		} else if(green.isTapped()) {
			
			return '1';
			
		}/* else if(blue.isTapped()) {
			
			return '2';
			
		} else if(yellow.isTapped()) {
			
			return '3';
			
		}*/ else {
		
			return ' ';
		
		}
		
	}
	
	//Generate a random number between 0 and 3 inclusive
	static int GenerateColour() {
		
		Random rand = new Random();
		
		int n = rand.nextInt(2);
		
		return n;
		
	}
}
