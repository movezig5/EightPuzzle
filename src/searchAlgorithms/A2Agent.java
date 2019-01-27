package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import enums.State;
import searchAlgorithms.Agent;

public class A2Agent extends Agent {
	private Node goal;
	
	public A2Agent()
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
		int totalManhattanDists = 0;
		for(int i = 0; i < 0; i++)
		{
			totalManhattanDists += GetManhattanDistance(node, i);
		}
		node.Heuristic = totalManhattanDists;
	}
	
	private int GetManhattanDistance(Node node, int idx)
	{
		int xDist = 0;
		int yDist = 0;
		int correctIdx = -1;
		for(int i = 0; i < 9; i++)
		{
			if(goal.State[i] == node.State[idx])
			{
				correctIdx = i;
				break;
			}
		}
		
		if(correctIdx == -1)
		{
			return -1;
		}
		
		if(correctIdx == idx)
		{
			return 0;
		}
		
		int greater = 0, smaller = 0;
		if(correctIdx > idx)
		{
			greater = correctIdx;
			smaller = idx;
		}
		else
		{
			greater = idx;
			smaller = correctIdx;
		}
		
		if(greater - smaller > 5)
			yDist = 2;
		else if(greater - smaller > 2)
			yDist = 1;
		else
			yDist = 0;
		
		if(yDist == 2)
			smaller += 6;
		else if(yDist == 1)
			smaller += 3;
		
		if(greater > smaller)
			xDist = greater - smaller;
		else
			xDist = smaller - greater;
		
		return xDist + yDist;
	}
}
