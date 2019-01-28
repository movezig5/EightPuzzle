package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;

/* **********************
 * Class: DepthFirstAgent
 * **********************
 * Description:	A subclass of Agent that executes the depth-first search algorithm.
 */

public class DepthFirstAgent extends Agent {
	// QueuingFn
	// Add successor nodes to the front of the queue.
	@Override
	protected void QueuingFn(LinkedList<Node> nodes, LinkedList<Node> expanded)
	{
		for(Node n : expanded)
		{
			nodes.push(n);
		}
	}
}
