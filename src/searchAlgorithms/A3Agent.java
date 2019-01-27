package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import enums.State;
import searchAlgorithms.Agent;

public class A3Agent extends Agent{
	private Node goal;
	
	public A3Agent()
	{
		goal = new Node(State.GOAL);
	}
	
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		return PrioritySearch(init, goal, solution, maxDepth);
	}
	
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
		node.Heuristic = totalCosts;
	}
}
