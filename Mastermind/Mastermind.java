import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	static String[] colors = {"Red", "Blue", "Green", "Yellow"};
	static int GUESSES_REMAINING = 9;
	static int CODE_LENGTH = colors.length;
	static double EPSILON_BOUND = 0.1;
	static int POSITIONS_CORRECT = 0;


	public static String[] generateCode() {
		String[] code = {" ", " ", " ", " "};
		Random rand = new Random();

		for (int i = 0; i < CODE_LENGTH; i++) {
			int randInt = rand.nextInt(CODE_LENGTH);
			
			code[i] = colors[randInt];
		}

		return code;

	}

	public static void gamePlay(String[] code) {

		Scanner scan = new Scanner(System.in);
		String[] guessedCode = {" ", " ", " ", " "};
		String guess = "";

		for (int j = 0; j < GUESSES_REMAINING; j--) {

			for (int k = 0; k < CODE_LENGTH; k++) {
				System.out.printf("Guess a color from Red, Blue, Green, or Yellow!");

				while (Math.abs(guess.length() - 0) < EPSILON_BOUND) {
					guess = scan.next();
					scan.nextLine();
				}
				
				guessedCode[k] = guess;
				guess = "";

			}
			
			for (int l = 0; l < CODE_LENGTH; l++) {
				if (guessedCode[l].compareTo(code[l]) == 0) {
					POSITIONS_CORRECT++;
				}
			}

			if (Math.abs(POSITIONS_CORRECT - 4) < EPSILON_BOUND) {
				System.out.println("You won, player! Congratulations!");
				System.out.println("The code you solved was: ");
				for (String color : code) {
					System.out.print(color + " ");
				}
				scan.close();
				return;
			}
			
			else {
				System.out.println("Your latest guess had " + Integer.toString(POSITIONS_CORRECT) + " positions correct.");
				GUESSES_REMAINING--;
				System.out.println("You have " + Integer.toString(GUESSES_REMAINING) + " attempts left."); 
				POSITIONS_CORRECT = 0;
			}

		}
		
		System.out.println("You lost, player! Try again.");
		System.out.println("The code the computer generated was: ");
		for (String color : code) {
			System.out.print(color + " ");
		}
		scan.close();
		return;
		

	}
	
	public static void main(String[] args) {
		
		System.out.println("Hello! Welcome to Mastermind. The object of this game is to determine a random color sequence.");
		System.out.println("You may choose from one of four colors for each part of your guess' code, but any guess that is NOT a valid one WILL be accepted!");
		System.out.println("(You could use this to your advantage...)");
		
		System.out.println("Let's play!");
		
		String[] comp_solution = generateCode();
		gamePlay(comp_solution);
	}
}
