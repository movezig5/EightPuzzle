package searchAlgorithms;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import dataStructures.Node;
import enums.Operator;
import enums.Result;

/* *********************
 * Abstract Class: Agent
 * *********************
 * Description:	An abstract class that performs a search algorithm.				
 */
public abstract class Agent {
	// Search
	// Calls the general search function with a maximum depth of 50.
	public Result Search(Node init, Node goal, LinkedList<Node> solution)
	{
		return Search(init, goal, solution, 50);
	}
	
	// Search
	// A general search function that keeps nodes in a queue (represented
	// here by a linked list). The algorithm varies based on the implementation
	// of the queuing function.
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		LinkedList<Node> nodes = new LinkedList<Node>();
		LinkedList<Node> history = new LinkedList<Node>();
		LinkedList<Node> expanded;
		nodes.add(init);
		Node current;
		while(!nodes.isEmpty())
		{
			current = nodes.pop();
			if(current.Depth >= maxDepth)
			{
				return Result.MAXDEPTH;
			}
			if(IsGoal(current, goal))
			{
				Stack<Node> nodeStack = new Stack<Node>();
				nodeStack.push(current);
				while(current.Parent != null)
				{
					current = current.Parent;
					nodeStack.push(current);
				}
				while(!nodeStack.isEmpty())
				{
					solution.add(nodeStack.pop());
				}
				return Result.SUCCESS;
			}
			expanded = Expand(current, history);
			QueuingFn(nodes, expanded);
			AddToHistory(current, history);
		}
		return Result.FAILURE;
	}
	
	// PrioritySearch
	// A modified search algorithm that uses a priority queue instead
	// of a queue. The priority queue stores nodes in ascending order
	// of their evaluation (the "Heuristic" variable). The algorithm
	// varies based on the implementation of the heuristic function.
	protected Result PrioritySearch(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		LinkedList<Node> expanded;
		PriorityQueue<Node> nodes = new PriorityQueue<Node>();
		LinkedList<Node> history = new LinkedList<Node>();
		nodes.add(init);
		Node current;
		while(!nodes.isEmpty())
		{
			current = nodes.remove();
			if(current.Depth >= maxDepth)
			{
				return Result.MAXDEPTH;
			}
			if(IsGoal(current, goal))
			{
				Stack<Node> nodeStack = new Stack<Node>();
				nodeStack.push(current);
				while(current.Parent != null)
				{
					current = current.Parent;
					nodeStack.push(current);
				}
				while(!nodeStack.isEmpty())
				{
					solution.add(nodeStack.pop());
				}
				return Result.SUCCESS;
			}
			expanded = Expand(current, history);
			QueuingFn(nodes, expanded);
			AddToHistory(current, history);
		}
		return Result.FAILURE;
	}
	
	// IsGoal
	// Determines whether a node is in a specified goal state.
	protected boolean IsGoal(Node node, Node goal)
	{
		for(int i = 0; i < 9; i++)
		{
			if(node.State[i] != goal.State[i])
				return false;
		}
		return true;
	}
	
	// SuccessorFn
	// Generates and returns a list of successor nodes to the node
	// passed in. Ignores nodes that have already been visited
	// (i.e., those that are in the "history" list).
	private LinkedList<Node> SuccessorFn(Node node, LinkedList<Node> history)
	{
		LinkedList<Node> result = new LinkedList<Node>();
		Node outNode = Up(node);
		if(outNode != null && !Visited(outNode, history))
			result.add(outNode);
		outNode = Down(node);
		if(outNode != null && !Visited(outNode, history))
			result.add(outNode);
		outNode = Left(node);
		if(outNode != null && !Visited(outNode, history))
			result.add(outNode);
		outNode = Right(node);
		if(outNode != null && !Visited(outNode, history))
			result.add(outNode);
		return result;
	}
	
	// Expand
	// Expands a node, sets its children to the list generated,
	// then returns the list. (Not sure why this had to be its
	// own method.)
	protected LinkedList<Node> Expand(Node node, LinkedList<Node> history)
	{
		LinkedList<Node> result = SuccessorFn(node, history);
		node.Children = result;
		return result;
	}
	
	// Up
	// Returns the node resulting from performing the "Up"
	// operator. Returns null if the operator can't be performed.
	protected Node Up(Node node)
	{
		int zIdx = ZeroIndex(node);
		if(zIdx > -1 && zIdx < 6)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx + 3];
			outNode.State[zIdx + 3] = 0;
			outNode.Action = Operator.UP;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	// Down
	// Returns the node resulting from performing the "Down"
	// operator. Returns null if the operator can't be performed.
	protected Node Down(Node node)
	{
		int zIdx = ZeroIndex(node);
		if(zIdx > 2 && zIdx < 9)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx - 3];
			outNode.State[zIdx - 3] = 0;
			outNode.Action = Operator.DOWN;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	// Left
	// Returns the node resulting from performing the "Left"
	// operator. Returns null if the operator can't be performed.
	protected Node Left(Node node)
	{
		int zIdx = ZeroIndex(node);
		if((zIdx + 1) % 3 != 0)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx + 1];
			outNode.State[zIdx + 1] = 0;
			outNode.Action = Operator.LEFT;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	// Right
	// Returns the node resulting from performing the "Right"
	// operator. Returns null if the operator can't be performed.
	protected Node Right(Node node)
	{
		int zIdx = ZeroIndex(node);
		if(zIdx % 3 != 0)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx - 1];
			outNode.State[zIdx - 1] = 0;
			outNode.Action = Operator.RIGHT;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	// ZeroIndex
	// Returns the first index in the nodes State array
	// containing the value 0. Used in the four operator
	// functions, Up, Down, Left, and Right.
	// Returns -1 if there is no 0 value.
	protected int ZeroIndex(Node node)
	{
		for(int i = 0; i < 9; i++)
		{
			if(node.State[i] == 0)
				return i;
		}
		return -1;
	}
	
	// Visited
	// Returns true if the given node is in the given "history"
	// list. This list contains all states previously visited.
	// Used for repeated state checking.
	private boolean Visited(Node node, LinkedList<Node> history)
	{
		for(Node n : history)
		{
			if(node.Equals(n))
				return true;
		}
		return false;
	}
	
	// QueuingFn
	// The queuing function for PrioritySearch. All algorithms
	// that use this search have the same queuing function, but
	// differ in heuristic functions.
	protected void QueuingFn(PriorityQueue<Node> nodes, LinkedList<Node> expanded)
	{
		for(Node n : expanded)
		{
			nodes.add(n);
		}
	}
	
	// AddToHistory
	// Adds the given node to the given history queue if the queue
	// doesn't already contain that node.
	private void AddToHistory(Node node, LinkedList<Node> history)
	{
		for(Node n : history)
		{
			if(node.Equals(n))
				return;
		}
		history.add(node);
	}
	
	// QueuingFn
	// Meant to be overridden by subclasses that do not use
	// the priority search. Unused in classes that do use the
	// priority search, so it isn't abstract. This way it doesn't
	// need to be overridden in every subclass.
	protected void QueuingFn(LinkedList<Node> nodes, LinkedList<Node> expanded) {}
	
	// HeuristicFn
	// Sets a given nodes "Heuristic" value to one determined by
	// the function. Unused in nodes that don't use PrioritySearch,
	// so it isn't abstract. This way it doesn't need to be overridden
	// in every subclass.
	protected void HeuristicFn(Node node) {}
}
