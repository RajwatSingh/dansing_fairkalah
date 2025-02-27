/**
 * An extension of MancalaNode with a simple, score-difference utility evaluation function.
 * @author Todd W. Neller
 */
public class ScoreDiffMancalaNode extends MancalaNode {

	/**
	 * See corresponding <code>MancalaNode</code> standard constructor documentation.
	 */
	public ScoreDiffMancalaNode() {
		super();
	}

	/**
	 * See corresponding <code>MancalaNode</code> copy constructor documentation.
	 * @param node node to be copied
	 */
	public ScoreDiffMancalaNode(MancalaNode node) {
		super(node);
	}

	/**
	 * See corresponding <code>MancalaNode</code> FairKalah constructor documentation.
	 * @param stateIndex FairKalah initial state index 
	 */
	public ScoreDiffMancalaNode(int stateIndex) {
		super(stateIndex);
	}

	/**
	 * Return the difference between current MAX and MIN score.
	 */
	public double utility() {
		return state[MAX_SCORE_PIT] - state[MIN_SCORE_PIT];
	}

}
