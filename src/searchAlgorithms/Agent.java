package searchAlgorithms;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import enums.Result;
import dataStructures.Node;

public abstract class Agent {
	public Result Search(Node init, Node goal, LinkedList<Node> solution)
	{
		return Search(init, goal, solution, 50);
	}
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		LinkedList<Node> nodes = new LinkedList<Node>();
		LinkedList<Node> history = new LinkedList<Node>();
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
			QueuingFn(nodes, Expand(current, history));
			history.add(current);
		}
		return Result.FAILURE;
	}
	
	protected Result PrioritySearch(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
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
			QueuingFn(nodes, Expand(current, history));
			history.add(current);
		}
		return Result.FAILURE;
	}
	
	protected boolean IsGoal(Node node, Node goal)
	{
		for(int i = 0; i < 9; i++)
		{
			if(node.State[i] != goal.State[i])
				return false;
		}
		return true;
	}
	
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
	
	protected LinkedList<Node> Expand(Node node, LinkedList<Node> history)
	{
		LinkedList<Node> result = SuccessorFn(node, history);
		node.Children = result;
		return result;
	}
	
	protected Node Up(Node node)
	{
		int zIdx = ZeroIndex(node);
		if(zIdx > -1 && zIdx < 6)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx + 3];
			outNode.State[zIdx + 3] = 0;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	protected Node Down(Node node)
	{
		int zIdx = ZeroIndex(node);
		if(zIdx > 2 && zIdx < 9)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx - 3];
			outNode.State[zIdx - 3] = 0;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	protected Node Left(Node node)
	{
		int zIdx = ZeroIndex(node);
		if((zIdx + 1) % 3 != 0)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx + 1];
			outNode.State[zIdx + 1] = 0;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	protected Node Right(Node node)
	{
		int zIdx = ZeroIndex(node);
		if(zIdx % 3 != 0)
		{
			Node outNode = new Node(node);
			outNode.State[zIdx] = outNode.State[zIdx - 1];
			outNode.State[zIdx - 1] = 0;
			outNode.Parent = node;
			outNode.Depth = node.Depth + 1;
			outNode.PathCost = node.PathCost + outNode.State[zIdx];
			HeuristicFn(outNode);
			return outNode;
		}
		return null;
	}
	
	private int ZeroIndex(Node node)
	{
		for(int i = 0; i < 9; i++)
		{
			if(node.State[i] == 0)
				return i;
		}
		return -1;
	}
	
	private boolean Visited(Node node, LinkedList<Node> history)
	{
		for(Node n : history)
		{
			if(node.Equals(n))
				return true;
		}
		return false;
	}
	
	protected void QueuingFn(PriorityQueue<Node> nodes, LinkedList<Node> expanded)
	{
		for(Node n : expanded)
		{
			nodes.add(n);
		}
	}
	
	protected abstract void QueuingFn(LinkedList<Node> nodes, LinkedList<Node> expanded);
	protected abstract void HeuristicFn(Node node);
}
