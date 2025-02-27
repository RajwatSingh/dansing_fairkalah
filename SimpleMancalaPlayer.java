/**
 * SimpleMancalaPlayer - A simple example implementation of a Mancala
 * player with simple, poor time management.
 *
 * @author Todd W. Neller
 */

public class SimpleMancalaPlayer implements MancalaPlayer {

	/**
	 * Choose a move for the given game situation given play time
	 * remaining.  */
	public int chooseMove(MancalaNode node, long timeRemaining) {
		// TODO - WARNING: This is a simple time management effort 
		// to distribute search time over course of game.  
		// It under-utilizes time, so you should design better time management.
		final double DEPTH_FACTOR = 1.3;
		int depthLimit = (int) (DEPTH_FACTOR * Math.log((double) timeRemaining 
							/ piecesRemaining(node)));
		if (depthLimit < 1) depthLimit = 1;

		// Create a minimax searcher.
		dansingAlphaBetaSearcher searcher = new dansingAlphaBetaSearcher(depthLimit);

		// Create a new copy of the input node that uses the
		// score difference heuristic evaluation function. 
		ScoreDiffMancalaNode searchNode = new ScoreDiffMancalaNode(node);

		searcher.eval(searchNode);
		return searcher.getBestMove();
	}


	/**
	 * Returns the number of pieces not yet captured.
	 * @return int - uncaptured pieces
	 * @param node MancalaNode - node to check
	 */
	public int piecesRemaining(MancalaNode node) {
		int pieces = 0;
		for (int i = 0; i < 6; i++) pieces += node.state[i];
		for (int i = 7; i < 13; i++) pieces += node.state[i];
		return pieces;
	}

}
