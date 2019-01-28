package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import searchAlgorithms.DepthFirstAgent;

/* ******************************
 * Class: IterativeDeepeningAgent
 * ******************************
 * Description:	A subclass of DepthFirstAgent that executes the
 * 				iterative deepening search algorithm.
 */

public class IterativeDeepeningAgent extends DepthFirstAgent {
	// Search
	// For every max depth value up to a given cutoff point,
	// executes depth-first search with the given max depth.
	// If at any point it finds a solution, it returns the
	// solution and stops running the search.
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int cutoff, int[] data)
	{
		Result result;
		int numPopped = 0;
		int maxQueueLength = 0;
		for(int i = 0; i < cutoff; i++)
		{
			result = super.Search(init, goal, solution, i, data);
			numPopped += data[0];
			maxQueueLength += data[1];
			if(result == Result.SUCCESS)
			{
				data[0] = numPopped;
				data[1] = maxQueueLength;
				return result;
			}
		}
		return Result.FAILURE;
	}
}
