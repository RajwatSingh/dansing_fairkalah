import java.util.ArrayList;
import java.util.Scanner;

/**
 * <code>HumanMancalaPlayer</code> - an agent relying entirely
 * upon human intelligence for decision making through a simple
 * text
 *
 * @author Todd W. Neller
 */
public class HumanMancalaPlayer implements MancalaPlayer {
	/**
	 * Scanner for reading human input.
	 */
	public static Scanner scanner = new Scanner(System.in);

	/**
	 * Display remaining time, legal moves and query user until legal move
	 * is entered.
	 */
	public int chooseMove(MancalaNode node, long timeRemaining) {
		ArrayList<Integer> moves = node.getLegalMoves();
		System.out.println("PLAYER " + ((node.player == GameNode.MAX) ? "1:" : "2:"));
		System.out.println("  " + (timeRemaining / 1000) + " seconds remain.");

		// Print legal moves
		ArrayList<String> moveStrings = new ArrayList<String>();
		for (int move : moves)
			moveStrings.add(MancalaNode.moveToString(move));
		System.out.println("  Legal moves: " + moveStrings);
		String inputMove = "";
		boolean legalMove = false;
		while (!legalMove) {
			System.out.print("  Your move? ");
			inputMove = scanner.nextLine().trim();
			legalMove = moveStrings.contains(inputMove);
		}
		return moves.get(moveStrings.indexOf(inputMove));
	}

}








