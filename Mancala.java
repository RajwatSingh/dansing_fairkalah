/**
 * Mancala - Timed mancala game manager.  To play two programs
 * against each other, change the classes at (*1*) and (*2*).
 * Each player has a maximum of 2.5 minutes of game time, so each
 * game will last at most 5 minutes.  In your testing, you may
 * choose to compete against your program.  Or you may play your
 * program against itself.  In the end, your program will compete
 * as both MAX and MIN against other programs.
 *
 * @author: Todd W. Neller
 */
public class Mancala {

	/**
	 * <code>main</code> - manage a timed Mancala game
	 *
	 * @param args a <code>String[]</code> value - unused
	 */
	public static void main(String[] args) {
		// Create players
		MancalaPlayer[] player = new MancalaPlayer[2];

		// TODO (*1*) put player one class here
		// player[GameNode.MAX] = new HumanMancalaPlayer(); 
		player[GameNode.MAX] = new dansing1MancalaPlayer();
		// player[GameNode.MAX] = new SimpleMancalaPlayer();

		// TODO (*2*) put player two class here
		// player[GameNode.MIN] = new HumanMancalaPlayer(); 
		// player[GameNode.MIN] = new SimpleMancalaPlayer();
		player[GameNode.MIN] = new dansing2MancalaPlayer();

		// Create times
		final long MILLISECONDS_PER_GAME = 300000L; // 5 minutes
		long[] playerMillisRemaining = {MILLISECONDS_PER_GAME / 2L, MILLISECONDS_PER_GAME / 2L};

		// Create a clock
		Stopwatch clock = new Stopwatch();
		long timeTaken;

		// Create an random initial node of a FairKalah (fair Mancala) game
		MancalaNode node = new ScoreDiffMancalaNode(-1);
		System.out.println(node);

		// While game is on...
		int move;
		String winner = "DRAW";
		while (!node.gameOver()) {
			// Request move from current player
			long timeRemaining = playerMillisRemaining[node.player];
			clock.reset();
			clock.start();
			move = player[node.player].chooseMove(node, timeRemaining);
			timeTaken = clock.stop();

			// Deduct time taken
			playerMillisRemaining[node.player] -= timeTaken;
			if (playerMillisRemaining[node.player] < 0) {
				if (node.player == GameNode.MAX) {
					System.out.println("Player 1 game timer expired.");
					winner = "PLAYER 2 WINS";
				} else {
					System.out.println("Player 2 game timer expired.");
					winner = "PLAYER 1 WINS";
				}
				break;
			}

			// Update game state
			System.out.println("Player " 
					+ ((node.player == GameNode.MAX) ? "1" : "2") 
					+ " makes move " 
					+ MancalaNode.moveToString(move) + ".");
			node.makeMove(move);

			// Display Progress
			System.out.println(node);
		}

		// Display winner and statistics
		if (node.gameOver())
			if (node.utility() > 0)
				winner = "PLAYER 1 WINS";
			else if (node.utility() < 0)
				winner = "PLAYER 2 WINS";
			else
				winner = "DRAW";
		System.out.println("Time Taken (ms): ");
		System.out.println("Player 1: " + (MILLISECONDS_PER_GAME / 2L - playerMillisRemaining[GameNode.MAX]));
		System.out.println("Player 2: " + (MILLISECONDS_PER_GAME / 2L - playerMillisRemaining[GameNode.MIN]));
		System.out.println(winner);
	}

}

