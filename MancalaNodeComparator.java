import java.util.Comparator;

public class MancalaNodeComparator implements Comparator<GameNode> {
	@Override
	public int compare(GameNode node1, GameNode node2) {
		double utility1 = node1.utility();
		double utility2 = node2.utility();
		return Double.compare(utility1, utility2);
	}
}