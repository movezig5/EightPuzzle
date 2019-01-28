package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import enums.State;
import searchAlgorithms.Agent;

/* *********************
 * Class: BestFirstAgent
 * *********************
 * Description:	A subclass of Agent that executes the greedy
 * 				best-first search algorithm. The evaluation
 * 				function is the number of tiles that are not
 * 				in the correct position.
 */

public class BestFirstAgent extends Agent {
	// Search
	// Overrides the search function to use PrioritySearch instead.
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth, int[] data)
	{
		return PrioritySearch(init, goal, solution, maxDepth, data);
	}

	// HeuristicFn
	// Sets the given node's Heuristic value to the number of tiles
	// that are not in the correct position.
	@Override
	protected void HeuristicFn(Node node)
	{
		Node goal = new Node(State.GOAL);
		int numOutOfPlace = 0;
		for(int i = 0; i < 9; i++)
		{
			if(node.State[i] != goal.State[i])
				numOutOfPlace++;
		}
		node.Heuristic = numOutOfPlace;
	}

}
