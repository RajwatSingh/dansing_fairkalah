
/**
 * GameTreeSearcher.java - a simple interface for a game-tree
 * search class
 *
 * @author Todd W. Neller
 */
public interface GameTreeSearcher {

	/**
	 * <code>eval</code> - Returns the estimated minimax value of
	 * the given node.
	 *
	 * @param node a <code>GameNode</code> value
	 * @return a <code>double</code> value - estimated minimax
	 * value of node */
	double eval(GameNode node);

	/**
	 * <code>getBestMove</code> - Returns the best move for the
	 * node most recently evaluated.
	 *
	 * @return an <code>int</code> value encoding the move */
	int getBestMove();

	/**
	 * <code>getNodeCount</code> - Returns the number of nodes
	 * searched for the previous node evaluation
	 *
	 * @return an <code>int</code> value - number of nodes
	 * searched */
	int getNodeCount();

}// GameTreeSearcher
