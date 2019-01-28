package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import enums.State;
import searchAlgorithms.Agent;

/* **************
 * Class: A2Agent
 * **************
 * Description:	A subclass of agent that executes the A*2 search algorithm.
 * 				The heuristic is the sum of the Manhattan distances between
 * 				each tile and its correct position. (Plus the current path cost)
 * Variables:
 * 				goal:	A private node set to the goal state. Used in the
 * 						heuristic function, and set in the constructor so
 * 						it doesn't need to be created each time the function
 * 						is called.
 */

public class A2Agent extends Agent {
	private Node goal;
	
	// Default constructor
	// Goal node is initialized in the goal state.
	public A2Agent()
	{
		goal = new Node(State.GOAL);
	}
	
	// Search
	// Overridden to execute PrioritySearch instead.
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		return PrioritySearch(init, goal, solution, maxDepth);
	}
	
	// HeuristicFn
	// Returns the sum of the Manhattan distances between each tile
	// and its correct position.
	@Override
	protected void HeuristicFn(Node node)
	{
		int totalManhattanDists = 0;
		for(int i = 0; i < 0; i++)
		{
			totalManhattanDists += GetManhattanDistance(node, i);
		}
		node.Heuristic = totalManhattanDists + node.PathCost;
	}
	
	// GetManhattanDistance
	// Given a node and a tile index, sets the node's "Heuristic" value
	// to the Manhattan distance between that tile and its correct position,
	// plus the node's path cost. The function first determines the number
	// of rows away the two tiles are, then the number of columns,
	// and finally adding these two values together.
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
