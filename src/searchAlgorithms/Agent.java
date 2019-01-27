package searchAlgorithms;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Scanner;
import dataStructures.Node;
import enums.Result;

public abstract class Agent {
	private boolean verbose = false;
	
	public Result Search(Node init, Node goal, LinkedList<Node> solution)
	{
		return Search(init, goal, solution, 100);
	}
	public Result Search(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		Scanner scanner = new Scanner(System.in);
		LinkedList<Node> nodes = new LinkedList<Node>();
		LinkedList<Node> history = new LinkedList<Node>();
		LinkedList<Node> expanded;
		nodes.add(init);
		Node current;
		while(!nodes.isEmpty())
		{
			current = nodes.pop();
			if(verbose)
			{
				System.out.println("Next node expanded (depth : " + current.Depth + "):");
				current.PrintState();
				scanner.nextLine();
			}
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
			if(verbose)
			{
				System.out.println("Results of expansion:");
				PrintNodes(expanded);
				scanner.nextLine();
			}
			QueuingFn(nodes, expanded);
			QueuingFn(nodes, expanded);
			history.add(current);
		}
		return Result.FAILURE;
	}
	
	protected Result PrioritySearch(Node init, Node goal, LinkedList<Node> solution, int maxDepth)
	{
		Scanner scanner = new Scanner(System.in);
		LinkedList<Node> expanded;
		PriorityQueue<Node> nodes = new PriorityQueue<Node>();
		LinkedList<Node> history = new LinkedList<Node>();
		nodes.add(init);
		Node current;
		while(!nodes.isEmpty())
		{
			current = nodes.remove();
			if(verbose)
			{
				System.out.println("Next node expanded (depth : " + current.Depth + "):");
				current.PrintState();
				scanner.nextLine();
			}
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
			if(verbose)
			{
				System.out.println("Results of expansion:");
				PrintNodes(expanded);
				scanner.nextLine();
			}
			QueuingFn(nodes, expanded);
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
	
	protected int ZeroIndex(Node node)
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
	
	protected void QueuingFn(LinkedList<Node> nodes, LinkedList<Node> expanded) {}
	protected void HeuristicFn(Node node)
	{
		node.Heuristic = 0;
	}
	
	private void PrintNodes(LinkedList<Node> nodes)
	{
		for(Node n : nodes)
			System.out.print("_____________ ");
		System.out.print("\n");
		for(Node n : nodes)
			System.out.print("| " + n.State[0] + " | " + n.State[1] + " | " + n.State[2] + " | ");
		System.out.print("\n");
		for(Node n : nodes)
			System.out.print("|___|___|___| ");
		System.out.print("\n");
		for(Node n : nodes)
			System.out.print("| " + n.State[3] + " | " + n.State[4] + " | " + n.State[5] + " | ");
		System.out.print("\n");
		for(Node n : nodes)
			System.out.print("|___|___|___| ");
		System.out.print("\n");
		for(Node n : nodes)
			System.out.print("| " + n.State[6] + " | " + n.State[7] + " | " + n.State[8] + " | ");
		System.out.print("\n");
		for(Node n : nodes)
			System.out.print("|___|___|___| ");
		System.out.print("\n");
	}
}
