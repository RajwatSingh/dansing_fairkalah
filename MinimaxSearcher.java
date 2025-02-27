import java.util.ArrayList;

/**
 * MinimaxSearcher.java - a depth-limited minimax searcher
 * without alpha-beta pruning
 *
 * @author Todd W. Neller
 */
public class MinimaxSearcher implements GameTreeSearcher {
	/**
	 * variable <code>depthLimit</code> - the depth limit of
	 * minimax search */
	private int depthLimit;

	/**
	 * variable <code>nodeCount</code> - the number of nodes
	 * searched in the most recent minimax evaluation */
	private int nodeCount;

	/**
	 * variable <code>bestMove</code> - the best move from the
	 * most recently evaluated node according to depth-limited
	 * minimax and the node's utility function */
	private int bestMove = GameNode.UNDEFINED_MOVE;

	/**
	 * Creates a new <code>MinimaxSearcher</code> instance with
	 * the given depth limit.
	 *
	 * @param depthLimit an <code>int</code> value - the depth of
	 * the minimax search tree*/
	public MinimaxSearcher(int depthLimit) {
		this.depthLimit = depthLimit;
	}

	/**
	 * <code>eval</code> - return the depth-limited minimax value
	 * of the given node
	 *
	 * @param node a <code>GameNode</code> value
	 * @return a <code>double</code> value - depth-limited
	 * minimax value */
	public double eval(GameNode node) 
	{
		nodeCount = 0;
		return minimaxEval(node, depthLimit);
	}

	/**
	 * <code>maximize</code> - MAX node evaluation of minimax
	 * procedure.
	 *
	 * @param node a <code>GameNode</code> value
	 * @param depthLeft an <code>int</code> value
	 * @return a <code>double</code> value */
	public double minimaxEval(GameNode node, int depthLeft) {
		int localBestMove = GameNode.UNDEFINED_MOVE;
		boolean maximizing = (node.getPlayer() == GameNode.MAX);
		double bestUtility = 
				maximizing ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
		nodeCount++;

		// Return utility if game over or depth limit reached
		if (node.gameOver() || depthLeft == 0)
			return node.utility();

		// Otherwise, generate children
		ArrayList<GameNode> children = node.expand();

		// Evaluate the depth-limited minimax value for each
		// child, keeping track of the best
		for (GameNode child : children) {
			double childUtility = minimaxEval(child, depthLeft - 1);
			// update best utility and move if appropriate
			if ((maximizing && childUtility > bestUtility)
					|| (!maximizing && childUtility < bestUtility)) {
				bestUtility = childUtility;
				localBestMove = child.prevMove;
			}
		}

		// Before returning utility, assign local best move to
		// instance variable.  The last value assigned in the
		// recursive evaluation will be the best move from the
		// root node.
		bestMove = localBestMove;
		return bestUtility;
	}

	/**
	 * <code>getBestMove</code> - Return the best move for the
	 * node most recently evaluated.
	 *
	 * @return an <code>int</code> value encoding the move */
	public int getBestMove() 
	{
		return bestMove;
	}

	/**
	 * <code>getNodeCount</code> - returns the number of nodes
	 * searched for the previous node evaluation
	 *
	 * @return an <code>int</code> value - number of nodes
	 * searched */
	public int getNodeCount() 
	{
		return nodeCount;
	}

}// MinimaxSearcher
