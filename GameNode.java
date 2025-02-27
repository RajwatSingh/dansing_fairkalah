import java.util.ArrayList;

/**
 * A general representation for a discrete game node.
 * @author Todd W. Neller
 */
public abstract class GameNode implements Cloneable {
	/** constant integers designating player MAXimizing and MINimizing utility */
	public static final int MAX = 0, MIN = 1;

	/** undefined move constant */
	public static final int UNDEFINED_MOVE = -1;

	/** first player MAXimizer by default */
	protected int player = MAX;

	/** previous move applied to reach this node. */
	public int prevMove = UNDEFINED_MOVE;

	/** variable <code>parent</code> - parent of this game node */
	public GameNode parent = null;

	/**
	 * <code>getPlayer</code> - return the current player
	 * (GameNode.MAX or GameNode.MIN).
	 *
	 * @return an <code>int</code> value GameNode.MAX or
	 * GameNode.MIN */
	public int getPlayer() {
		return player;
	}

	/**
	 * <code>expand</code> - return an ArrayList of all possible next
	 * game states
	 *
	 * @return a <code>Vector</code> of all possible next game
	 * states */
	public abstract ArrayList<GameNode> expand();

	/**
	 * <code>childClone</code> - returns a clone of this node
	 * that has been made a child of this node and has a depth
	 * one greater than this.
	 *
	 * @return a <code>SearchNode</code> value */
	public GameNode childClone() 
	{
		GameNode child = (GameNode) clone();
		child.parent = this;
		return child;
	}

	/**
	 * <code>clone</code> - deep copy of game node; This will
	 * generally need to be overridden.
	 *
	 * @return an <code>Object</code> value
	 */
	public Object clone() 
	{
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new RuntimeException ("This class does not implement Cloneable.");
		}
	}    

	/**
	 * Return result of terminal state test.
	 *
	 * @return boolean
	 */
	public abstract boolean gameOver();

	/**
	 * Return the estimated utility of the current game position,
	 * unless game is over.  If game is over, return actual utility.
	 * Player MAX maximizes this; player MIN minimizes this.
	 *
	 * @return double
	 */
	public abstract double utility();

}
