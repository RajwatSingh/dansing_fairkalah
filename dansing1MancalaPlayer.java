public class dansing1MancalaPlayer implements MancalaPlayer {

    /**
	 * Choose a move for the given game situation given play time
	 * remaining.  */
	public int chooseMove(MancalaNode node, long timeRemaining) {
		final double DEPTH_FACTOR = 2.2;
		// final double STD_DEV_FACTOR = 0.05;
		// double stdDev = calculateStandardDeviation(node);
	
		int depthLimit = (int) (DEPTH_FACTOR * obvious_factor(node) * Math.log((double) timeRemaining 
							/ piecesRemaining(node)));
	
		// depthLimit = (int) (depthLimit / (1 + STD_DEV_FACTOR * stdDev));
	
		depthLimit = Math.max(depthLimit, (timeRemaining > 5000) ? 5 : 1);
		depthLimit = Math.min(depthLimit, 15);

		System.out.println("Depth limit: " + depthLimit);
		System.out.println("Time remaining: " + timeRemaining);
	
		dansing2AlphaBetaSearcher searcher = new dansing2AlphaBetaSearcher(depthLimit);
		dansing1MancalaNode searchNode = new dansing1MancalaNode(node);
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

	public double obvious_factor(MancalaNode node) {
		double factor = 1.0;
		dansing1MancalaNode searchNode = new dansing1MancalaNode(node);

		if (node.player == GameNode.MAX) {
			if (searchNode.freeMoves() > 0) {
				return 0.5;
			}
		} else {
			if (searchNode.freeMovesO() > 0) {
				return 0.5;
			}
		}

		for (int i = 0; i < Math.abs(searchNode.relative_capturable()); i++) {
			factor *= 0.95;
		}

		return factor;
	}
    
	private double calculateStandardDeviation(MancalaNode node) {
		int[] pits = node.getState();
		double mean = 0;
		int totalPits = node.PLAY_PITS;
	
		for (int stones : pits) {
			mean += stones;
		}
		mean /= totalPits;
	
		double variance = 0;
		for (int stones : pits) {
			variance += Math.pow(stones - mean, 2);
		}

		variance /= totalPits;
		return Math.sqrt(variance);
	}
}
