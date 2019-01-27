package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;

public class DepthFirstAgent extends Agent {
	@Override
	protected void QueuingFn(LinkedList<Node> nodes, LinkedList<Node> expanded)
	{
		for(Node n : expanded)
		{
			nodes.push(n);
		}
	}
}
