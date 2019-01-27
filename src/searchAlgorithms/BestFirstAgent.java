package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import searchAlgorithms.Agent;

public class BestFirstAgent extends Agent {
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		return PrioritySearch(init, goal, solution, maxDepth);
	}

	@Override
	protected void HeuristicFn(Node node) {
		node.Heuristic = node.PathCost;
	}

}
