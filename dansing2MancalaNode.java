import java.util.ArrayList;
import java.util.Collections;

public class dansing2MancalaNode extends MancalaNode {

	// public static void main(String[] args) {
        
    //     int[] state = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
	// 	dansing1MancalaNode node = new dansing1MancalaNode(state);

    //     double utilityValue = node.utility();
    //     System.out.println("Utility value of the current state: " + utilityValue);
    // }

	/**
	 * See corresponding <code>MancalaNode</code> standard constructor documentation.
	 */
	public dansing2MancalaNode() {
		super();
	}

	/**
	 * See corresponding <code>MancalaNode</code> copy constructor documentation.
	 * @param node node to be copied
	 */
	public dansing2MancalaNode(MancalaNode node) {
		super(node);
	}

	/**
	 * See corresponding <code>MancalaNode</code> copy constructor documentation.
	 * @param node node to be copied
	 */
	public dansing2MancalaNode(int[] state) {
		super(state);
	}

	/**
	 * See corresponding <code>MancalaNode</code> FairKalah constructor documentation.
	 * @param stateIndex FairKalah initial state index 
	 */
	public dansing2MancalaNode(int stateIndex) {
		super(stateIndex);
	}

	/**
	 * Return the difference between current MAX and MIN score.
	 */
	public double utility() {
		return -0.7043 + (1.0697 * score_dif()) + (1.6145 * relativeMobility()) + (1.6765 * freeMoves()) + (-0.5751 * freeMovesO()) + (0.7141 * relative_capturable()) + (0.8774 * state[0]);
	}

    public int score_dif() {
        return state[MAX_SCORE_PIT] - state[MIN_SCORE_PIT];
    }
    
    public int relativeMobility() {
		int maxMoves = 0, minMoves = 0;
	
		for (int i = 0; i < PLAY_PITS; i++) {
			if (state[i] > 0) {
				maxMoves++;
			}
			if (state[i + PLAY_PITS + 1] > 0) {
				minMoves++;
			}
		}
	
		return maxMoves - minMoves;
	}

    public int freeMoves() {
        int freeMoves = 0;

		for (int i = 0; i < PLAY_PITS; i++) {
			if (state[i] + i == PLAY_PITS) {
				freeMoves++;
			}
		}
			
		return freeMoves;
    }

	public int freeMovesO() {
        int freeMoves = 0;

		for (int i = PLAY_PITS + 1; i < PLAY_PITS * 2 + 1; i++) {
			if (state[i] + i == PLAY_PITS * 2 + 1) {
				freeMoves++;
			}
		}
			
		return freeMoves;
    }

	public int relative_capturable() {
		return capturableStones() - vulnerableStones();
	}

	public int vulnerableStones() {
		int vulnerableStones = 0;
		for (int i = PLAY_PITS + 1; i < 2 * PLAY_PITS + 1; i++) {
			int mirroredPit = 2 * PLAY_PITS + 1 - i;
			boolean[] result = reachablePit(mirroredPit, true);
			if (state[i] == 0 && result[0]) {
				vulnerableStones += state[PLAY_PITS * 2 - i];
				if (result[1]) {
					vulnerableStones += 1;
				}
			}
		}
		return vulnerableStones;
	}
	
	public int capturableStones() {
		int capturableStones = 0;
		for (int i = 0; i < PLAY_PITS; i++) {
			boolean[] result = reachablePit(PLAY_PITS - i, false);
			if (state[i] == 0 && result[0]) {
				capturableStones += state[PLAY_PITS * 2 - i];
				if (result[1]) {
					capturableStones += 1;
				}
			}
		}
		return capturableStones;
	}
	
	public boolean[] reachablePit(int pit, boolean opponentSide) {
		int start = opponentSide ? PLAY_PITS + 1 : 0;
		int end = opponentSide ? 2 * PLAY_PITS + 1 : PLAY_PITS;
		int relativePit = opponentSide ? (2 * PLAY_PITS + 1 - pit) : PLAY_PITS - pit;
	
		for (int i = start; i < end; i++) {
			if (i == relativePit && state[i] == 13) {
				return new boolean[]{true, true}; // reachable = true, plusOne = true
			}
			if (opponentSide && i != relativePit && state[i] == Math.floorMod(relativePit - i, 13) && state[i] <= 13) {
				return new boolean[]{true, state[i] >= 8}; // plusOne is true if state[i] >= 8
			}
			if (!opponentSide && i != relativePit && state[i] == Math.floorMod(PLAY_PITS - i - pit, 13) && state[i] <= 13) {
				return new boolean[]{true, state[i] >= 8};
			}
		}
		return new boolean[]{false, false}; // Not reachable
	}

	/**
	 * <code>expand</code> - return an ArrayList of all possible next
	 * game states
	 *
	 * @return an <code>ArrayList</code> of all possible next game
	 * states */
	@Override
	public ArrayList<GameNode> expand() {
		ArrayList<GameNode> children = new ArrayList<GameNode>();
		for (int move : getLegalMoves()) {
			MancalaNode child = (MancalaNode) childClone();
			child.makeMove(move);
			children.add(child);
		}
		return children;
	}

	/**
	 * Return an <code>ArrayList</code> of integers, each designating a legal
	 * pit index to play from.
	 * @return an <code>ArrayList</code> of integers, each designating a legal
	 * pit index to play from
	 */
	@Override
	public ArrayList<Integer> getLegalMoves() 
	{
		ArrayList<Integer> legalMoves = new ArrayList<Integer>();
		final int PLAYER_OFFSET = (player == MAX) ? 0 : MAX_SCORE_PIT + 1;
		for (int i = PLAYER_OFFSET; i < PLAYER_OFFSET + PLAY_PITS; i++)
			if (state[i] > 0)
				legalMoves.add(i);

		Collections.reverse(legalMoves);
		return legalMoves;
	}
}



