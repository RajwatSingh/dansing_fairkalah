/**
 * Interface for a MancalaPlayer object that, given a MancalaNode 
 * and milliseconds remaining in the game, returns a legal move 
 * integer.
 * @author Todd W. Neller
 */
public interface MancalaPlayer {
	/**
	 * <code>chooseMove</code> - This is where your code takes
	 * over as a MancalaPlayer.  You must implement this method.
	 * See ScoreDiffMancalaNode for an example implementation with
	 * minimax.  You are given the current game node and the time
	 * remaining (in milliseconds).  You are to return a legal
	 * move integer.  Your game clock runs until you return your
	 * legal move, so budget your time well.  You can need to
	 * first create a new node of your own type (say
	 * UniqueIDMancalaNode), and have your entire search work
	 * with your own node types and thus your own evaluation
	 * function.
	 *
	 * @param node a <code>MancalaNode</code> value - the node to
	 * choose a move from
	 * @param timeRemaining a <code>long</code> value - your
	 * entire game time remaining in milliseconds.  If your time
	 * runs out, you lose.
	 * @return an <code>int</code> value - the encoding of the
	 * legal move to take at the current node */
	int chooseMove(MancalaNode node, long timeRemaining);
}

