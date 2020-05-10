import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OnePlayerGame {

	static Piece _one;
	static Piece _two;
	static Piece _three;
	static Piece _four;
	static Piece _five;
	static Piece _six;
	static Piece _seven;
	static Piece _eight;
	static Piece _nine;
	
	static List<Piece> _pieces = new ArrayList<Piece>();
	static String winner = "";
	
	
	private static final String PLAYER_SYMBOL = "X";
	private static final String COMPUTER_SYMBOL = "O";
	
	
	public static void main(String[] args) {
		
		_one = new Piece(0);
		_two = new Piece(1);
		_three = new Piece(2);
		_four = new Piece(3);
		_five = new Piece(4);
		_six = new Piece(5);
		_seven = new Piece(6);
		_eight = new Piece(7);
		_nine = new Piece(8);
		
		_pieces.add(_one);
		_pieces.add(_two);
		_pieces.add(_three);
		_pieces.add(_four);
		_pieces.add(_five);
		_pieces.add(_six);
		_pieces.add(_seven);
		_pieces.add(_eight);
		_pieces.add(_nine);
		
		int _numTurns = -1;
		boolean noWinner = true;
		Scanner scan = new Scanner(System.in);
		
		List<Integer> allPositions = new ArrayList<Integer>();
		List<Integer> takenPositions = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++) {
			allPositions.add(i);
		}
		
		
		List<Integer> remainingPositions = new ArrayList<Integer>(allPositions);
		remainingPositions.removeAll(takenPositions);
		
		while (noWinner) {
			_numTurns++;
			
			if (_numTurns == 0) {
				System.out.println("Welcome to TicTacToe!");
				System.out.println("The board looks like this: ");
				
				System.out.println("  1  |  2  |  3  ");
				System.out.println("------------------");
				System.out.println("  4  |  5  |  6  ");
				System.out.println("------------------");
				System.out.println("  7  |  8  |  9  ");
				
				
				System.out.println("When you want to make a move,");
				System.out.println("simply type the number of the spot you wish to choose.");
				System.out.println("Your symbol for the game is X.");
				
				System.out.println("First to connect three of their ");
				System.out.println("pieces in a row, column, or diagonal wins!");
				System.out.println("--------------------------------------------");
				
			}
			
			if (_numTurns % 2 == 0) {
				System.out.println("Player, it is your move (1-9)!");
				
				Integer next = scan.nextInt() - 1;
				while (!remainingPositions.contains(next)) {
					System.out.println("You did not select an open position.");
					System.out.println("The remaining positions are:");
					
					for (int position : remainingPositions) {
						System.out.print(position + 1 + " ");
					}
					
					System.out.println("                  ");
					System.out.println("Player, it is your move (1-9)!");
					next = scan.nextInt() - 1;
				}
				
				int playerMove = next;
				
				takenPositions.add(playerMove);
				remainingPositions = new ArrayList<Integer>(allPositions);
				remainingPositions.removeAll(takenPositions);
 				
				_pieces.get(playerMove).setPiece(PLAYER_SYMBOL);
			}
			
			else {
				System.out.println("                          ");
				System.out.println("It is the computer's move.");
				Random random = new Random();
				int computerMove = remainingPositions.get(random.nextInt(remainingPositions.size()));
				takenPositions.add(computerMove);
				remainingPositions = new ArrayList<Integer>(allPositions);
				remainingPositions.removeAll(takenPositions);
				
				_pieces.get(computerMove).setPiece(COMPUTER_SYMBOL);
			}
			
			boardMethod();
			noWinner = isThereAWinner();
			if (!noWinner) {
				if (winner.equals("X")) {
					System.out.println("Player, you have won!");
					scan.close();
				}
				
				else {
					System.out.println("The computer has won.");

					scan.close();
				}
			}
			
			if ((takenPositions.size() == 9) && (winner.equals(""))) {
				System.out.println("The game is a tie!");
				scan.close();
			}
		}
		
		
	}
	
	public static void boardMethod() {
		System.out.println("                 ");
		System.out.println("  " + _pieces.get(0).getPiece() + "  |  " + _pieces.get(1).getPiece() + "  |  " + _pieces.get(2).getPiece() + " ");
		System.out.println("-----------------");
		System.out.println("  " + _pieces.get(3).getPiece() + "  |  " + _pieces.get(4).getPiece() + "  |  " + _pieces.get(5).getPiece() + " ");
		System.out.println("-----------------");
		System.out.println("  " + _pieces.get(6).getPiece() + "  |  " + _pieces.get(7).getPiece() + "  |  " + _pieces.get(8).getPiece() + " ");
	}
	
	public static boolean isThereAWinner() {

		BoardEdge topRow = new BoardEdge(_one, _two, _three);
		BoardEdge midRow = new BoardEdge(_four, _five, _six);
		BoardEdge botRow = new BoardEdge(_seven, _eight, _nine);
		BoardEdge leftCol = new BoardEdge(_one, _four, _seven);
		BoardEdge midCol = new BoardEdge(_two, _five, _eight);
		BoardEdge rightCol = new BoardEdge(_three, _six, _nine);
		BoardEdge tlbrDiag = new BoardEdge(_one, _five, _nine);
		BoardEdge trblDiag = new BoardEdge(_three, _five, _seven);

		List<BoardEdge> edges = new ArrayList<BoardEdge>();
		edges.add(topRow);
		edges.add(midRow);
		edges.add(botRow);
		edges.add(leftCol);
		edges.add(midCol);
		edges.add(rightCol);
		edges.add(tlbrDiag);
		edges.add(trblDiag);

		for (BoardEdge edge : edges) {

			if (edge.isEdgeSameType()) {
				winner = edge.getBeginning().getPiece();
				return false;
			}
		}

		return true;
	}
}
