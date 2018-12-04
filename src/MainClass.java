import java.util.*;
import java.io.*;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class MainClass {
	
	static Finch red  = new Finch();
	static Finch green  = new Finch();
	static Finch blue  = new Finch();
	static Finch yellow  = new Finch();
	static boolean repeat = true;
	static int score;
	static boolean hasMovedAway = true;
	
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
				
				if(i.codePointAt(i.length() - 1) != sequence.codePointAt(i.length() - 1)) {
					
					break;
					
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
			red.buzz(200, 1000);
			red.setLED(255,0,0,1000);
			
			
		}
		
		if(green != null) {
			
			print("Green is live");
			green.buzz(300, 1000);
			green.setLED(0,255,0,1000);
			
			
		}
		
		if(blue != null) {
			
			print("Blue is live");
			blue.buzz(400, 1000);
			blue.setLED(0,0,255,1000);
			
		}
		
		if(yellow != null) {
			
			print("Yellow is live");
			yellow.buzz(500, 1000);
			yellow.setLED(255,255,0,1000);
			
			
		}
		
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
		
		if((red.isObstacle() || green.isObstacle() || blue.isObstacle() || yellow.isObstacle()) && hasMovedAway) {
			
			c = FinchInput();
		
			if(c != ' ') {
			
				printC(c);
				
				//SetColour(c,1000);
				hasMovedAway = false;
			}
			
		} else if(!red.isObstacle() && !green.isObstacle() && !blue.isObstacle() && !yellow.isObstacle()){
			
			hasMovedAway = true;
			
		}
		
		return c;		
	}
	
	static void printC(char c) {
		
		switch(c) {
		
		
			case '0':
				
				//red
				print("RED");
				red.buzz(200, 1000);
				red.setLED(255,0,0,1000);
				break;
				
				
			case '1':
				
				print("GREEN");
				green.buzz(300, 1000);
				green.setLED(0,255,0,1000);
				break;
			
			case '2':
				
				print("BLUE");
				blue.buzz(400, 1000);
				blue.setLED(0,0,255,1000);
				break;
				
			case '3':
				
				print("YELLOW");
				yellow.buzz(500, 1000);
				yellow.setLED(255,255,1000);
				break;
		
		
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
		
		red.setLED(0,0,0,50);
		green.setLED(0,0,0,50);
		blue.setLED(0,0,0,50);
		yellow.setLED(0,0,0,50);
		
	}
	
	static void SetColour(char id,int dur) {
		
		switch(id) {
		
			case '0':
				//red
				red.buzz(200, 1000);
				red.setLED(255,0,0,dur);
				
				break;
				
			case '1':
				//green.setLED(0,255,0,dur);
				green.buzz(300, 1000);
				green.setLED(0,255,0,dur);
				
				break;
			
			case '2':
				//blue.setLED(0,0,255,dur);
				blue.buzz(400, 1000);
				blue.setLED(0,0,255,dur);
				
				
				break;
				
			case '3':
				//yellowsetLED(255,255,0,dur);
				yellow.buzz(500, 1000);
				yellow.setLED(255,255,0,dur);
				
				break;
		
		}
				
			
		
	}
	
	void indicate(int id,int dur) {
		
		
	}
	
	static char FinchInput() {
		
		if(red.isObstacle()) {
			
			return '0';
			
		} else if(green.isObstacle()) {
			
			return '1';
			
		} else if(blue.isObstacle()) {
			
			return '2';
			
		} else if(yellow.isObstacle()) {
			
			return '3';
			
		} else {
		
			return ' ';
		
		}
		
	}
	
	//Generate a random number between 0 and 3 inclusive
	static int GenerateColour() {
		
		Random rand = new Random();
		
		int n = rand.nextInt(4);
		
		return n;
		
	}
}
