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
	
		//Figure out which finch is which colour
		FinchTest();
		
		String sequence = "";
		String i = "";
		
		char in = ' ';
		
		while(repeat) {
			
			i = "";
			
			//Add a random colour to the end of the sequence
			sequence += String.valueOf(GenerateColour());
			//Output the sequence onto the finches
			OutputSequence(sequence);
			
			print("Input Answer");
			//Ask for input until the player inputs the wrong or right answer
			do {
			
				in = ' ';
				
				while(in == ' ') {
				
					in = ReceiveInput();
					
				}
				
				i += in;
				
				/*//Wait for half a second
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}*/
				
				//Check if the last colour input is correct
				if(i.codePointAt(i.length() - 1) != sequence.codePointAt(i.length() - 1)) {
					
					break;
					
				}
			
			} while (i.length() != sequence.length());
		
			//Check if the whole answer input makes the sequence
			if(i.equals(sequence)) {
				
				score++;
				repeat = true;
				print("Correct!Next Round");
				
			} else {
				
				print("Your score was " + String.valueOf(score));
				repeat = false;
				
			}
			
			//Print in the console which colour was output
			print("Input was " + i);
			
		}
		
		
	}
	
	
	static void FinchTest() {
		
		//This function just ask each finch to flash its colour and make a sound
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
		
		//The character ' ' just means they havent touched anything
		char c = ' ';
		
		//Have any of the finches observed an obstacle and has the player already moved their hand away from the last finch they touched?
		if((red.isObstacle() || green.isObstacle() || blue.isObstacle() || yellow.isObstacle()) && hasMovedAway) {
			
			c = FinchInput();
		
			if(c != ' ') {
			
				printC(c);
				hasMovedAway = false;
				
			}
			
		} else if(!red.isObstacle() && !green.isObstacle() && !blue.isObstacle() && !yellow.isObstacle()){
			
			hasMovedAway = true;
			
		}
		
		//Send back finch the player touched
		return c;		
	}
	
	static void printC(char c) {
		
		//Depending on the finch requested flash its colour
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
			printC(n);
			SetToBlank();

		}
		
	}
	
	static void SetToBlank() {
		
		//Turn off the lights on all the finches for a fraction of a second(this is for clarity)
		red.setLED(0,0,0,50);
		green.setLED(0,0,0,50);
		blue.setLED(0,0,0,50);
		yellow.setLED(0,0,0,50);
		
	}
	
	//Checks if an of the finches have detected an obstacle in front of them
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
