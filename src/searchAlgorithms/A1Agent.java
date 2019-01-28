package searchAlgorithms;

import java.util.LinkedList;

import dataStructures.Node;
import enums.Result;
import enums.State;
import searchAlgorithms.Agent;

/* **************
 * Class: A1Agent
 * **************
 * Description:	A subclass of Agent that executes the A*1 search algorithm.
 * 				The heuristic is the number of tiles that are not in
 * 				the correct position. (Plus the current path cost)
 * Variables:
 * 				goal:	A private node set to the goal state. Used in the
 * 						heuristic function, and set in the constructor so
 * 						it doesn't need to be created each time the function
 * 						is called.
 */

public class A1Agent extends Agent {
	private Node goal;
	
	// Constructor
	// Private goal node is initialized to the goal state.
	public A1Agent()
	{
		goal = new Node(State.GOAL);
	}
	
	// Search
	// Search is overridden here to perform a PrioritySearch instead.
	@Override
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		return PrioritySearch(init, goal, solution, maxDepth);
	}
	
	// HeuristicFn
	// Sets a given nodes "Heuristic" variable to the number of tiles
	// that are not in correct position, plus the node's path cost.
	// Does this by comparing the value of each tile to the value of
	// the same tile in the goal node.
	@Override
	protected void HeuristicFn(Node node)
	{
		int numOutOfPlace = 0;
		for(int i = 0; i < 9; i++)
		{
			if(node.State[i] != goal.State[i])
				numOutOfPlace++;
		}
		node.Heuristic = numOutOfPlace + node.PathCost;
	}
}
