package dataStructures;

import java.util.LinkedList;
import enums.State;

public class Node implements Comparable<Node> {
	public Node Parent;
	public LinkedList<Node> Children;
	public int[] State;
	public int PathCost;
	public int Depth;
	public int Heuristic;
	
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
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
	public Node(int[] inState)
	{
		State = new int[9];
		for(int i = 0; i < 9; i++)
		{
			State[i] = inState[i];
		}
		Parent = null;
		Children = new LinkedList<Node>();
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
	public Node(Node rhs)
	{
		State = new int[9];
		for(int i = 0; i < 9; i++)
		{
			State[i] = rhs.State[i];
		}
		Parent = null;
		Children = new LinkedList<Node>();
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
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
		PathCost = 0;
		Depth = 0;
		Heuristic = 0;
	}
	
	public boolean Equals(Node rhs)
	{
		for(int i = 0; i < 9; i++)
		{
			if(State[i] != rhs.State[i])
				return false;
		}
		return true;
	}
	
	public void PrintState()
	{
		System.out.println("_____________");
		System.out.println("| " + State[0] + " | " + State[1] + " | " + State[2] + " |");
		System.out.println("|___|___|___|");
		System.out.println("| " + State[3] + " | " + State[4] + " | " + State[5] + " |");
		System.out.println("|___|___|___|");
		System.out.println("| " + State[6] + " | " + State[7] + " | " + State[8] + " |");
		System.out.println("|___|___|___|");
	}

	@Override
	public int compareTo(Node n) {
		return Heuristic - n.Heuristic;
	}
}
