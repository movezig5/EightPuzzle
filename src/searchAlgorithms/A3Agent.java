package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import searchAlgorithms.Agent;

/* **************
 * Class: A3Agent
 * **************
 * Description:	A subclass of Agent that uses the A*3 algorithm. This algorithm uses
 * 				an original heuristic. The heuristic is the sum of the costs of each
 * 				potential action from a given state. (Plus the current path cost)
 */

public class A3Agent extends Agent{
	// Search
	// Overrides search so that it performs a priority search instead.
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth, int[] data)
	{
		return PrioritySearch(init, goal, solution, maxDepth, data);
	}
	
	// HeuristicFn
	// Sets the given node's Heuristic value to the sum of the costs
	// of all possible moves from that node, plus the node's path cost.
	@Override
	protected void HeuristicFn(Node node)
	{
		int zIdx = ZeroIndex(node);
		int totalCosts = 0;
		if (zIdx > -1 && zIdx < 6)
			totalCosts += node.State[zIdx + 3];
		if(zIdx > 2 && zIdx < 9)
			totalCosts += node.State[zIdx - 3];
		if((zIdx + 1) % 3 != 0)
			totalCosts += node.State[zIdx + 1];
		if(zIdx % 3 != 0)
			totalCosts += node.State[zIdx - 1];
		node.Heuristic = totalCosts + node.PathCost;
	}
}
