package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;

/* ************************
 * Class: BreadthFirstAgent
 * ************************
 * Description:	A subclass of Agent that performs
 * 				the breadth-first search algorithm.
 */
public class BreadthFirstAgent extends Agent {
	// QueuingFn
	// Adds successor nodes to the back of the queue.
	@Override
	protected void QueuingFn(LinkedList<Node> nodes, LinkedList<Node> expanded)
	{
		for(Node n : expanded)
		{
			nodes.add(n);
		}
	}
}
