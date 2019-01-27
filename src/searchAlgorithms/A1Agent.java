package searchAlgorithms;

import java.util.LinkedList;

import dataStructures.Node;
import enums.Result;
import enums.State;
import searchAlgorithms.Agent;

public class A1Agent extends Agent {
	private Node goal;
	
	public A1Agent()
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
		int numOutOfPlace = 0;
		for(int i = 0; i < 9; i++)
		{
			if(node.State[i] != goal.State[i])
				numOutOfPlace++;
		}
		node.Heuristic = numOutOfPlace;
	}
}
