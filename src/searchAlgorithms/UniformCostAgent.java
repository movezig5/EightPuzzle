package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;

/* ***********************
 * Class: UniformCostAgent
 * ***********************
 * Description:	A subclass of Agent that executes the uniform cost search algorithm.
 */

public class UniformCostAgent extends Agent {
	// Search
	// Overrides Search to use PrioritySearch instead.
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth, int[] data)
	{
		return PrioritySearch(init, goal, solution, maxDepth, data);
	}
	
	// HeuristicFn
	// Sets a given node's Heuristic value to the node's path cost.
	@Override
	protected void HeuristicFn(Node node)
	{
		node.Heuristic = node.PathCost;
	}
}
