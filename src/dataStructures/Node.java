package dataStructures;

import java.util.LinkedList;
import enums.Operator;
import enums.State;

/* ***********
 * Class: Node
 ************
 *	Description:	A class representing a single node in the search tree.
 *					It implements the "Comparable" interface. This is done so
 *					that it can be stored in a priority queue.
 *	Variables:
 *		Parent:		The parent of the current node
 *		Children:	A linked list containing the children of this node
 *		State:		An array of nine integers representing the state of the puzzle at this node
 *					The numbers are in left to right, top to bottom order; 0 represents the empty space
 *		Action:		An enum representing the operator used to reach this node
 *		PathCost:	The total cost of the path to this node
 *		Depth:		The depth of the node; this is equal to 1 + the depth of the parent
 *		Heuristic:	The value given by the evaluation function, if there is one
 */

public class Node implements Comparable<Node> {
	public Node Parent;
	public LinkedList<Node> Children;
	public int[] State;
	public Operator Action;
	public int PathCost;
	public int Depth;
	public int Heuristic;
	
	// Default constructor -- node is set to goal state by default
	public Node()
	{
		State = new int[9];
		State[0] = 1;
		State[1] = 2;
		State[2] = 3;
		State[3] = 8;
		State[4] = 0;
		State[5] = 4;
		State[6] = 7;
		State[7] = 6;
		State[8] = 5;
		Parent = null;
		Children = new LinkedList<Node>();
		Action = null;
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
	// Constructor that takes an array of integers;
	// These are copied into the state array
	public Node(int[] inState)
	{
		State = new int[9];
		for(int i = 0; i < 9; i++)
		{
			State[i] = inState[i];
		}
		Parent = null;
		Children = new LinkedList<Node>();
		Action = null;
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
	// Copy constructor;
	// ONLY copies the state, and nothing else
	public Node(Node rhs)
	{
		State = new int[9];
		for(int i = 0; i < 9; i++)
		{
			State[i] = rhs.State[i];
		}
		Parent = null;
		Children = new LinkedList<Node>();
		Action = null;
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
	// Constructor that takes a state enum;
	// There are enums for the easy, medium, hard,
	// and goal states
	public Node(State stateEnum)
	{
		State = new int[9];
		switch(stateEnum)
		{
		case EASY:
			State[0] = 1;
			State[1] = 3;
			State[2] = 4;
			State[3] = 8;
			State[4] = 6;
			State[5] = 2;
			State[6] = 7;
			State[7] = 0;
			State[8] = 5;
			break;
		case MEDIUM:
			State[0] = 2;
			State[1] = 8;
			State[2] = 1;
			State[3] = 0;
			State[4] = 4;
			State[5] = 3;
			State[6] = 7;
			State[7] = 6;
			State[8] = 5;
			break;
		case HARD:
			State[0] = 5;
			State[1] = 6;
			State[2] = 7;
			State[3] = 4;
			State[4] = 0;
			State[5] = 8;
			State[6] = 3;
			State[7] = 2;
			State[8] = 1;
			break;
		case GOAL:
			State[0] = 1;
			State[1] = 2;
			State[2] = 3;
			State[3] = 8;
			State[4] = 0;
			State[5] = 4;
			State[6] = 7;
			State[7] = 6;
			State[8] = 5;
			break;
		}
		Parent = null;
		Children = new LinkedList<Node>();
		Action = null;
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
	// Equals
	// Checks if the state arrays of two nodes are the same
	public boolean Equals(Node rhs)
	{
		for(int i = 0; i < 9; i++)
		{
			if(State[i] != rhs.State[i])
				return false;
		}
		return true;
	}
	
	// PrintState
	// Prints out the current state of the puzzle
	// Use UTF-8 encoding to see the cool box characters!
	public void PrintState()
	{
		System.out.println("╔═══╦═══╦═══╗");
		System.out.println("║ " + State[0] + " ║ " + State[1] + " ║ " + State[2] + " ║");
		System.out.println("╠═══╬═══╬═══╣");
		System.out.println("║ " + State[3] + " ║ " + State[4] + " ║ " + State[5] + " ║");
		System.out.println("╠═══╬═══╬═══╣");
		System.out.println("║ " + State[6] + " ║ " + State[7] + " ║ " + State[8] + " ║");
		System.out.println("╚═══╩═══╩═══╝");
		System.out.print("Operator: ");
		switch(Action)
		{
		case UP:
			System.out.print("UP");
			break;
		case DOWN:
			System.out.print("DOWN");
			break;
		case LEFT:
			System.out.print("LEFT");
			break;
		case RIGHT:
			System.out.println("RIGHT");
			break;
		default:
			System.out.println("NONE");
		}
		System.out.print(", Cost: ");
		if(Parent != null)
			System.out.println(PathCost - Parent.PathCost + ", ");
		else
			System.out.println("0");
		System.out.println("Total cost: " + PathCost);
	}
	
	// compareTo
	// Compares the current node to another node
	// The larger node is the one with the higher value for its
	// evaluation function--i.e., the one with the larger
	// "Heuristic" variable.
	@Override
	public int compareTo(Node n) {
		return Heuristic - n.Heuristic;
	}
}
